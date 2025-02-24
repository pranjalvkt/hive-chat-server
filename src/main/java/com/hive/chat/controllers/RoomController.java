package com.hive.chat.controllers;

import com.hive.chat.entities.Message;
import com.hive.chat.entities.Room;
import com.hive.chat.payload.MessageDTO;
import com.hive.chat.payload.ProfilePicDTO;
import com.hive.chat.payload.UserDTO;
import com.hive.chat.repositories.RoomRepository;
import com.hive.chat.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/rooms")
@CrossOrigin(origins = {"https://chatinhive.netlify.app", "https://connectinhive.netlify.app", "http://localhost:3000", "http://localhost:4000"})
public class RoomController {
    private RoomRepository roomRepository;
    private final UserService userService;

    public RoomController(RoomRepository roomRepository,  UserService userService) {
        this.roomRepository = roomRepository;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> createRoom(@RequestBody String roomId) {
        if(roomRepository.findByRoomId(roomId) != null) {
            return ResponseEntity.badRequest().body("Room already exists!");
        }

        Room room = new Room();
        room.setRoomId(roomId);
        Room saveRoom = roomRepository.save(room);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveRoom);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<?> joinRoom(@PathVariable String roomId) {
        Room room = roomRepository.findByRoomId(roomId);

        if(room == null) {
            return ResponseEntity.badRequest().body("Room does not exist!");
        }
        return ResponseEntity.ok(room);
    }

    @GetMapping("/{roomId}/messages")
    public ResponseEntity<List<MessageDTO>> getMessages(
            @PathVariable String roomId,
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "20", required = false) int size
    ) {
        Room room = roomRepository.findByRoomId(roomId);
        if (room == null) {
            return ResponseEntity.badRequest().build();
        }

        List<Message> messages = room.getMessages();
        int start = Math.max(0, messages.size() - (page + 1) * size);
        int end = Math.min(messages.size(), start + size);
        List<Message> paginatedMessages = messages.subList(start, end);

        List<MessageDTO> dtos = paginatedMessages.stream().map(message -> {
            String senderId = message.getSender();

            UserDTO userDetails = userService.getUserDetails(senderId);
            String senderName = (userDetails != null && userDetails.getName() != null)
                    ? userDetails.getName() : "Unknown";
            ProfilePicDTO profilePic = (userDetails != null) ? userDetails.getProfilePic() : null;

            return new MessageDTO(
                message.getContent(),
                message.getTimeStamp(),
                senderId,
                senderName,
                profilePic
            );
        }).collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

}

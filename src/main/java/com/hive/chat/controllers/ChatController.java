package com.hive.chat.controllers;

import com.hive.chat.entities.Message;
import com.hive.chat.entities.Room;
import com.hive.chat.payload.MessageDTO;
import com.hive.chat.payload.MessageRequest;
import com.hive.chat.payload.UserDTO;
import com.hive.chat.repositories.RoomRepository;
import com.hive.chat.services.UserService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@CrossOrigin(origins = {"https://chatinhive.netlify.app", "http://localhost:3000", "http://localhost:4000"})
public class ChatController {
    private RoomRepository roomRepository;
    private final UserService userService;

    public ChatController(RoomRepository roomRepository, UserService userService) {
        this.roomRepository = roomRepository;
        this.userService = userService;
    }

    @MessageMapping("/sendMessage/{roomId}")
    @SendTo("/topic/room/{roomId}")
    public MessageDTO sendMessage(
            @DestinationVariable String roomId,
            @RequestBody MessageRequest request
    ) {
        Room room = roomRepository.findByRoomId(request.getRoomId());
        Message message = new Message(request.getContent(), request.getSender());

        UserDTO userDetails = userService.getUserDetails(request.getSender());

        if(room != null) {
            room.getMessages().add(message);
            roomRepository.save(room);
        }

        return new MessageDTO(
            message.getContent(),
            message.getTimeStamp(),
            userDetails.getUserId(),
            userDetails.getName(),
            userDetails.getProfilePic()
        );
    }
}

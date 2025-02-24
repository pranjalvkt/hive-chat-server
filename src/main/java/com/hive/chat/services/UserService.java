package com.hive.chat.services;

import com.hive.chat.payload.UserDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String nodeUserApiUrl = "https://hive-server-tpz5.onrender.com/api/getUserDetails/";

    public UserDTO getUserDetails(String userId) {
        try {
            return restTemplate.getForObject(nodeUserApiUrl + userId, UserDTO.class);
        } catch (Exception e) {
            System.out.println("Error fetching user details: " + e.getMessage());
            return new UserDTO("Unknown","Unknown", null);
        }
    }
}

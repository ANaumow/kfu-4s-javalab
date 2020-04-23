package ru.naumow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.naumow.dto.RoomDto;
import ru.naumow.dto.RoomForm;
import ru.naumow.services.MultiRoomWebSocketChatService;

@Controller
public class RoomController {
    @Autowired private MultiRoomWebSocketChatService multiRoomChatService;

    @GetMapping("/rooms")
    public String getRoomList(Model model) {
        model.addAttribute("list", multiRoomChatService.getRoomList(false));
        return "rooms";
    }

    @GetMapping("/rooms/{room-id}")
    public String getRoom(Model model, @PathVariable("room-id") Long roomId) {
        RoomDto currentRoom = multiRoomChatService.getRoomById(roomId, true);
        model.addAttribute("room", currentRoom);
        System.out.println(currentRoom);
        return "room";
    }

    @PostMapping("/rooms")
    public String createRoom(RoomForm roomForm) {
        multiRoomChatService.createRoom(roomForm);
        return "redirect:/rooms";
    }


}

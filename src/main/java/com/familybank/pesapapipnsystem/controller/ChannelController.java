/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.familybank.pesapapipnsystem.controller;

import com.familybank.pesapapipnsystem.dto.ResponseObject;
import com.familybank.pesapapipnsystem.exceptions.ChannelAlreadyExistException;
import com.familybank.pesapapipnsystem.model.Channel;
import com.familybank.pesapapipnsystem.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ekaranja
 */
@RestController
@RequestMapping("/v1/channel")
public class ChannelController {

    @Autowired
    private ChannelService channelService;

    @PostMapping("/create")
    public ResponseEntity createChannel(@RequestBody Channel channel) {
        try {
            return channelService.createChannel(channel);
        } catch (ChannelAlreadyExistException ex) {
            return ResponseEntity.badRequest().body(new ResponseObject(null, ex.getMessage()));
        }
    }

}

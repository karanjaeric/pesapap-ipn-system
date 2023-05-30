/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.familybank.pesapapipnsystem.service;

import com.familybank.pesapapipnsystem.exceptions.ChannelAlreadyExistException;
import com.familybank.pesapapipnsystem.model.Channel;
import com.familybank.pesapapipnsystem.repository.ChannelRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author ekaranja
 */
@Service
@Slf4j
public class ChannelService {
    
    @Autowired
    private ChannelRepository channelRepository;
    
    public ResponseEntity createChannel(Channel channel) throws ChannelAlreadyExistException {
        log.info("Received Channel Payload {}", channel.toString());
        if (channelRepository.findByCode(channel.getCode()) != null) {
            throw new ChannelAlreadyExistException("Channel Already Exist");
        }
        channel.setActive(true);
        channel = channelRepository.save(channel);
        log.info("Channel Created Successfully");
        return ResponseEntity.status(200).body(channel);
        
    }
    
}

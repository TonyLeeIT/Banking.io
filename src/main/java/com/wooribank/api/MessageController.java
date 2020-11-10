package com.wooribank.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wooribank.entity.Message;
import com.wooribank.repository.MessageRepository;

@RestController
@RequestMapping("/bank/")
public class MessageController {

	@Autowired
	private MessageRepository messageRepository;
	
	@GetMapping("/message")
	public List<Message> getMessage(){
		return messageRepository.findAll();
	}
	
	@PostMapping("/message")
	public Message saveMessage(@RequestBody Message message) {
		return messageRepository.save(message);
	}
}

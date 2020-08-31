package com.pluralsight.conferencedemo.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pluralsight.conferencedemo.models.Speaker;
import com.pluralsight.conferencedemo.repositories.SpeakerRepository;


@RestController
@RequestMapping("/api/v1/speakers")
public class SpeakerController {
	
	@Autowired
	private SpeakerRepository speakerRepository;
	
	@GetMapping
	public List<Speaker> list(){
		return speakerRepository.findAll();
	}
	
	@GetMapping("{id}")
	public Speaker get(@PathVariable Long id) {
		return speakerRepository.getOne(id);
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public Speaker create(@RequestBody Speaker speaker) {
		return speakerRepository.saveAndFlush(speaker);
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Speaker update(@PathVariable Long id, @RequestBody Speaker speaker) {
		Speaker exisitingSpeaker = speakerRepository.getOne(id);
		BeanUtils.copyProperties(speaker, exisitingSpeaker,"speaker_id");
		return speakerRepository.saveAndFlush(speaker);
	}
	
	@DeleteMapping("{id")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void delete(@PathVariable Long id) {
		speakerRepository.deleteById(id);
	}

}

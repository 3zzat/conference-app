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

import com.pluralsight.conferencedemo.models.Session;
import com.pluralsight.conferencedemo.repositories.SessionRepository;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionController {

	@Autowired
	private SessionRepository sessionRepository;

	@GetMapping
	public List<Session> list() {
		return sessionRepository.findAll();
	}
	
	@GetMapping
	@RequestMapping("{id}")
	Session get(@PathVariable Long id){
		return sessionRepository.getOne(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Session create(@RequestBody final Session session) {
		return sessionRepository.saveAndFlush(session);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void delete(@PathVariable Long id) {
		sessionRepository.deleteById(id);
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Session update(@PathVariable Long id, @RequestBody Session session ){
		Session exisitingSession = sessionRepository.getOne(id);
		BeanUtils.copyProperties(session, exisitingSession,"session_id");
		return sessionRepository.saveAndFlush(exisitingSession);
	}
}

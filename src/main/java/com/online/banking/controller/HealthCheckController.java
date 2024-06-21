package com.online.banking.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HealthCheckController {

	@GetMapping("health-check")
	public ResponseEntity<String> healthCheck() {
		String s = null;
		s.length();
		return ResponseEntity.status(HttpStatus.OK).body("{\"status \" :\"ok\"}");
	}

}

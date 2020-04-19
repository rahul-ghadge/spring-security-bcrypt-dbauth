package com.arya.security.bcrypt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	
	@GetMapping("/")
    public String welcome() {
        return "<h1>This is simple welcome page..!\n Visible to any one...!</h1>";
    }
	
	
	@GetMapping("/user")
	public String user() {
		return ("<h1>Welcome User</h1>");
	}

	
	@GetMapping("/admin")
	public String admin() {
		return ("<h1>Welcome Admin</h1>");
	}

	
	@GetMapping("/error")
	public String error() {
		return ("<h1>Something went wrong</h1>");
	}
}

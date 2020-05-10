package com.example.demo.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DispCongroller {

	@Autowired
	private MessageSource messageSource;

	@RequestMapping( name = "/", method = RequestMethod.GET )
	public String helloWorld(Model model) {
		model.addAttribute("message", messageSource.getMessage("hello",  new String[] {},  Locale.getDefault()));
		return "hello";
	}


/*
	@RequestMapping( value="/html" , method=RequestMethod.POST)
	public String dispDbSelect( @RequestParam("name") String name, Model model ) {
		return "hello.html";
	}
*/
}

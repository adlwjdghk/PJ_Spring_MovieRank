package com.movierank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class indexController {
	@RequestMapping("/")
	public String index(Model model) {
		log.info(">>>>>>> INDEX PAGE VIEW");
		
		return "index";
	}
}

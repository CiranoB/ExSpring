package com.generation.helloworld.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/helloworld")
public class HelloworldController {

	@GetMapping
	public String helloworld () {
		return "Responsabilidade Pessoal, Mentalidade de Crescimento, Orientação ao futuro"
				+ "Persistência, Comunicação, Adaptabilidade, Trabalho em Equipe e "
				+ "Proatividade";
	}

}

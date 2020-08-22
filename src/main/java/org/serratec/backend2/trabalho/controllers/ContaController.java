package org.serratec.backend2.trabalho.controllers;

import java.util.List;

import org.serratec.backend2.trabalho.banco.domain.Conta;
import org.serratec.backend2.trabalho.banco.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/conta"})
public class ContaController {
	@Autowired
	private ContaService contaService;

	@GetMapping
	public List<Conta> listConta(){
		return contaService.listaConta();
	}
	
	

	// @GetMapping
	// @PutMapping
	// @PostMapping
	// @DeleteMapping
}

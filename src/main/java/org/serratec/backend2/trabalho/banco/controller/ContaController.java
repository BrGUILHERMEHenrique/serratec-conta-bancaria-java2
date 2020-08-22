package org.serratec.backend2.trabalho.banco.controller;

import org.serratec.backend2.trabalho.banco.domain.Conta;
import org.serratec.backend2.trabalho.banco.exceptions.AccountNotFoundException;
import org.serratec.backend2.trabalho.banco.exceptions.InsufficientFundsException;
import org.serratec.backend2.trabalho.banco.exceptions.InvalidNumberException;
import org.serratec.backend2.trabalho.banco.exceptions.NotAllowedCreditException;
import org.serratec.backend2.trabalho.banco.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/conta" })
public class ContaController {

	@Autowired
	private ContaService contaService;

	@GetMapping
	public ResponseEntity<?> listar() {
		return ResponseEntity.ok(contaService.listaConta());
	}

	@GetMapping("/{numero}")
	public ResponseEntity<?> pegaPorNumero(@PathVariable Integer numero) throws InvalidNumberException, AccountNotFoundException {
		return ResponseEntity.ok(contaService.recuperarPorNumero(numero));
	}
	
	@PostMapping
	public ResponseEntity<?> adcionarConta(@RequestBody Conta conta) {
		return ResponseEntity.ok(contaService.adicionar(conta));
	}

	@PutMapping("/{numero}")
	public ResponseEntity<?> atualizarConta(@PathVariable Integer numero, @RequestBody Conta conta) throws InvalidNumberException, AccountNotFoundException {
		Conta contaAtualizada = contaService.atualizarConta(conta, numero);
		return ResponseEntity.ok(contaAtualizada);
	}

	@DeleteMapping("/{numero}")
	public ResponseEntity<?> apagarConta(@PathVariable Integer numero) throws InvalidNumberException, AccountNotFoundException {
		if (contaService.apagarConta(numero) == true) {
			return ResponseEntity.ok("Conta apagada com sucesso!!");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao apagar a conta!!");
	}

	@PostMapping("/{numero}/{operacao}")
	public ResponseEntity<?> operacao(@PathVariable("numero") Integer numero, @PathVariable("operacao") String operacao,
			@RequestParam Double valor) throws InvalidNumberException, AccountNotFoundException, InsufficientFundsException, NotAllowedCreditException {
		return ResponseEntity.ok(contaService.operacao(operacao, valor, numero));
	}

}

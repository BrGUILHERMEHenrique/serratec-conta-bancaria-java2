package org.serratec.backend2.trabalho.banco.controller;

import org.serratec.backend2.trabalho.banco.exceptions.AccountNotFoundException;
import org.serratec.backend2.trabalho.banco.exceptions.InsufficientFundsException;
import org.serratec.backend2.trabalho.banco.exceptions.InvalidNumberException;
import org.serratec.backend2.trabalho.banco.exceptions.NotAllowedCreditException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<String> tratarAccountNotFound(AccountNotFoundException exception) {
		String msg = String.format(" A conta com o numero %d nao foi encontrada ", exception.getNumero());
		return ResponseEntity.notFound()
				.header("x-accounterro-msg", msg)
				.header("x-accounterro-code", "ACCOUNT_NOT_FOUND")
				.header("x-accounterro-value", exception.getNumero().toString()).build();
	}

	@ExceptionHandler(InvalidNumberException.class)
	public ResponseEntity<String> tratarInvalidNumberExceptiopn(InvalidNumberException exception) {
		String msg = "ID inválido, por favor insira um número maior que 0";
		return ResponseEntity.badRequest()
				.header("x-invalidnumber-msg", msg)
				.header("x-invalidnumber-code", "INVALID_NUMBER")
				.header("x-invalidnumber-value", exception.getNumero().toString()).build();
	}
	
	@ExceptionHandler(NotAllowedCreditException.class)
	public ResponseEntity<String> tratarNotAllowedCreditException(NotAllowedCreditException exception) {
		String msg = String.format("A operação de credito nao pode ser concluida pelo valor passado menor que 50!");
		return ResponseEntity.badRequest()
				.header("x-notallowed-msg", msg)
				.header("x-notallowed-code", "NOT_ALLOWED")
				.header("x-notallowed-value", exception.getValor().toString())
				.build();
	}
	
	@ExceptionHandler(InsufficientFundsException.class)
	public ResponseEntity<String> tratarInsuficientFundsExceptions(InsufficientFundsException exception) {
		String msg = String.format("O saldo da conta passada não tem fundos suficientes para a operação");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.header("x-insufficientfund-msg", msg)
				.header("x-insufficientfund-code", "INSUFFICIENT_FUNDS")
				.header("x-insufficientfunds-value", exception.getValor().toString())
				.build();
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> tratarGenericException(Exception exception) {
		String msg = String.format("Erro inesperado no servidor, se possível verifique as informaçoes passadas.");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.header("x-generic-msg", msg)
				.header("x-generic-code", exception.getMessage())
				.build();
	}
}
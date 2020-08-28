package org.serratec.backend2.trabalho.banco.controller;

import org.serratec.backend2.trabalho.banco.exceptions.AccountNotFoundException;
import org.serratec.backend2.trabalho.banco.exceptions.DuplicateAccountException;
import org.serratec.backend2.trabalho.banco.exceptions.InsufficientFundsException;
import org.serratec.backend2.trabalho.banco.exceptions.InvalidNumberException;
import org.serratec.backend2.trabalho.banco.exceptions.NotAllowedCreditException;
import org.serratec.backend2.trabalho.banco.exceptions.NotAlowedValueException;
import org.serratec.backend2.trabalho.banco.exceptions.OperationNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<String> tratarAccountNotFound(AccountNotFoundException exception) {
		String msg = String.format(" A conta com o numero %d nao foi encontrada ", exception.getNumero());
		return ResponseEntity.notFound().header("x-accounterro-msg", msg)
				.header("x-accounterro-code", "ACCOUNT_NOT_FOUND")
				.header("x-accounterro-value", exception.getNumero().toString()).build();
	}

	@ExceptionHandler(InvalidNumberException.class)
	public ResponseEntity<String> tratarInvalidNumberExceptiopn(InvalidNumberException exception) {
		String msg = "ID inválido, por favor insira um número maior que 0";
		return ResponseEntity.badRequest().header("x-invalidnumber-msg", msg)
				.header("x-invalidnumber-code", "INVALID_NUMBER")
				.header("x-invalidnumber-value", exception.getNumero().toString()).build();
	}

	@ExceptionHandler(NotAllowedCreditException.class)
	public ResponseEntity<String> tratarNotAllowedCreditException(NotAllowedCreditException exception) {
		String msg = String.format("A operação de credito nao pode ser concluida pelo valor passado menor que 50!");
		return ResponseEntity.badRequest().header("x-notallowed-msg", msg).header("x-notallowed-code", "NOT_ALLOWED")
				.header("x-notallowed-value", exception.getValor().toString()).build();
	}

	@ExceptionHandler(InsufficientFundsException.class)
	public ResponseEntity<String> tratarInsuficientFundsExceptions(InsufficientFundsException exception) {
		String msg = String.format("O saldo da conta passada não tem fundos suficientes para a operação");
		return ResponseEntity.badRequest().header("x-insufficientfund-msg", msg)
				.header("x-insufficientfund-code", "INSUFFICIENT_FUNDS")
				.header("x-insufficientfunds-value", exception.getValor().toString()).build();
	}

	@ExceptionHandler(OperationNotFoundException.class)
	public ResponseEntity<String> tratarOperationNotFoundException(OperationNotFoundException exception) {
		String msg = String.format("A operação %s não existe, por favor insira uma operação válida(credito/debito)", exception.getOperacao().toUpperCase());
		return ResponseEntity.badRequest()
				.header("x-operationnotfound-msg", msg)
				.header("x-operationnotfound-code", "OPERATION_NOT_FOUND")
				.header("x-operationnotfound-value", exception.getOperacao()).build();
	}
	
	@ExceptionHandler(NotAlowedValueException.class)
	public ResponseEntity<String> tratarOperationNotFoundException(NotAlowedValueException exception) {
		return ResponseEntity.badRequest()
				.header("x-operationnotfound-msg", exception.getMsg())
				.header("x-operationnotfound-code", "OPERATION_NOT_FOUND").build();
	}
	@ExceptionHandler(DuplicateAccountException.class)
	public ResponseEntity<String> tratarOperationNotFoundException(DuplicateAccountException exception) {
		return ResponseEntity.badRequest()
				.header("x-operationnotfound-msg", exception.getMsg())
				.header("x-operationnotfound-code", "DUPLICATE_ACCOUNT").build();
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> tratarGenericException(Exception exception) {
		String msg = String.format("Erro inesperado no servidor, se possível verifique as informaçoes passadas.");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.header("x-generic-msg", msg)
				.header("x-generic-code", exception.getMessage()).build();
	}
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<String> tratarNullPointerException(NullPointerException exception){
		String msg = "ALGUM PARAMETRO OU INFORMAÇÃO ESTÁ ERRADA POR FAVOR CONFIRA A URL";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("x-TITLLE-msg", msg)
				.header("Error", exception.getMessage()).build();
	}
}
package org.serratec.backend2.trabalho.banco.service;

import java.util.ArrayList;
import java.util.List;

import org.serratec.backend2.trabalho.banco.domain.Conta;
import org.serratec.backend2.trabalho.banco.exceptions.AccountNotFoundException;
import org.serratec.backend2.trabalho.banco.exceptions.InsufficientFundsException;
import org.serratec.backend2.trabalho.banco.exceptions.InvalidNumberException;
import org.serratec.backend2.trabalho.banco.exceptions.NotAllowedCreditException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContaService {
	
	@Autowired
	private OperacaoService operacaoService;

	private List<Conta> banco;
	private int nextnumero;

	public ContaService() {
		banco = new ArrayList<Conta>();
		banco.add(new Conta(1, "Bill Gates", 5000.00));
		banco.add(new Conta(2, "Mahatma Gandhi", 10000.00));
		banco.add(new Conta(3, "Kobe Bryant", 2000.00));
		banco.add(new Conta(4, "João Bizerra", 500.00));
		nextnumero = 5;
	}
	
	private void validarNumero(Integer numero) throws InvalidNumberException {
		if(numero <= 0) {
			throw new InvalidNumberException(numero);
		}
		
	}

	public List<Conta> listaConta() {
		return banco;
	}

	public Conta recuperarPorNumero(Integer numero) throws InvalidNumberException, AccountNotFoundException {
		validarNumero(numero);
		for (Conta conta : banco) {
			if (conta.getNumero().equals(numero)) {
				return conta;
			}
		}
		throw new AccountNotFoundException(numero);
	}

	public Conta adicionar(Conta conta) {
		conta.setNumero(nextnumero);
		banco.add(conta);
		nextnumero++;
		return conta;

	}

	public Conta atualizarConta(Conta conta, Integer numero) throws InvalidNumberException, AccountNotFoundException {
		Conta contaAntiga = recuperarPorNumero(numero);

		if (!(null == conta.getTitular()) && !"".equals(conta.getTitular())) {
			contaAntiga.setTitular(conta.getTitular());

		}
		if (!(null == conta.getNumero()) && !"".equals(conta.getNumero())) {
			contaAntiga.setNumero(conta.getNumero());
		}

		Conta contaAtualizada = contaAntiga;

		return contaAtualizada;
	}

	public boolean apagarConta(Integer numero) throws InvalidNumberException, AccountNotFoundException {

		Conta conta = recuperarPorNumero(numero);
		if (conta == null) {
			return false;
		} else {
			banco.remove(conta);
			return true;
		}

	}
	
	public Double operacao(String operacao, Double valor, Integer numero) throws InvalidNumberException, AccountNotFoundException, InsufficientFundsException, NotAllowedCreditException {
		Conta conta = recuperarPorNumero(numero);
		switch (operacao) {
		case "debito":
			return operacaoService.debito(conta, valor);
		case "credito":
			return operacaoService.credito(conta, valor);
			
		default:
			return null;
		}
	}
}

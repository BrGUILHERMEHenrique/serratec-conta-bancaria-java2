package org.serratec.backend2.trabalho.banco.service;

import org.serratec.backend2.trabalho.banco.domain.Conta;
import org.serratec.backend2.trabalho.banco.exceptions.InsufficientFundsException;
import org.serratec.backend2.trabalho.banco.exceptions.NotAllowedCreditException;
import org.springframework.stereotype.Service;

@Service
public class OperacaoService {
	
	
	public Conta debito(Conta conta, Double valor) throws InsufficientFundsException{
		if(conta.getSaldo() < valor) {
			throw new InsufficientFundsException(conta.getSaldo(), valor);
		}else {
			conta.setSaldo(conta.getSaldo() - valor);
			return conta;
		}
	}
	
	public Conta credito(Conta conta, Double valor) throws NotAllowedCreditException{
		if(valor >= 50) {
			conta.setSaldo(conta.getSaldo() + valor);
		} else {
			throw new NotAllowedCreditException(valor);
		}
		return conta;
	}
}

package org.serratec.backend2.trabalho.banco.service;

import org.serratec.backend2.trabalho.banco.domain.Conta;
import org.serratec.backend2.trabalho.banco.exceptions.InsufficientFundsException;
import org.serratec.backend2.trabalho.banco.exceptions.NotAllowedCreditException;
import org.springframework.stereotype.Service;

@Service
public class OperacaoService {
	
	
	public Double debito(Conta conta, Double valor) throws InsufficientFundsException{
		if(conta.getSaldo() < valor) {
			throw new InsufficientFundsException(conta.getSaldo(), valor);
		}else {
			conta.setSaldo(conta.getSaldo() - valor);
			return conta.getSaldo();
		}
	}
	
	public Double credito(Conta conta, Double valor) throws NotAllowedCreditException, InsufficientFundsException {
		if(valor >= 50) {
			if(conta.getSaldo() < valor) {
				throw new InsufficientFundsException(conta.getSaldo(), valor);
			} else {
				conta.setSaldo(conta.getSaldo() - valor);
			}
		} else {
			throw new NotAllowedCreditException(valor);
		}
		return conta.getSaldo();
	}
}

package org.serratec.backend2.trabalho.banco.service;

import org.serratec.backend2.trabalho.banco.domain.Conta;
import org.springframework.stereotype.Service;

@Service
public class OperacaoService {
	
	
	public Double debito(Conta conta, Double valor) {
		if(conta.getSaldo() < valor) {
			return null;
		}else {
			conta.setSaldo(conta.getSaldo() - valor);
			return conta.getSaldo();
		}
	}
	
	public Double credito(Conta conta, Double valor) {
		if(valor >= 50) {
			if(conta.getSaldo() < valor) {
				return null;
			}else {
				conta.setSaldo(conta.getSaldo() - valor);
			}
		}
		return conta.getSaldo();
	}
}

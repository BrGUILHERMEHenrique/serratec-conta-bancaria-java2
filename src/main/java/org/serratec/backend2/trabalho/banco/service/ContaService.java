package org.serratec.backend2.trabalho.banco.service;

import java.util.ArrayList;
import java.util.List;

import org.serratec.backend2.trabalho.banco.domain.Conta;
import org.springframework.stereotype.Service;

@Service
public class ContaService {

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

	public List<Conta> listaConta() {
		return banco;
	}

	public Conta recuperarPorNumero(Integer numero) {
		for (Conta conta : banco) {
			if (conta.getNumero().equals(numero)) {
				return conta;
			}
		}
		return null;
	}

	public Conta adicionar(Conta conta) {
		conta.setNumero(nextnumero);
		banco.add(conta);
		nextnumero++;
		return conta;

	}

	public Conta atualizarConta(Conta conta, Integer numero) {
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

	public boolean apagarConta(Integer numero) {

		Conta conta = recuperarPorNumero(numero);
		if (conta == null) {
			return false;
		} else {
			banco.remove(conta);
			return true;
		}

	}
	
}

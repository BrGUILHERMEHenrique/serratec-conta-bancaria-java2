package org.serratec.backend2.trabalho.banco.service;

import java.util.ArrayList;
import java.util.List;

import org.serratec.backend2.trabalho.banco.domain.Conta;
import org.serratec.backend2.trabalho.banco.domain.Operacao;
import org.serratec.backend2.trabalho.banco.domain.TipoOperacao;
import org.serratec.backend2.trabalho.banco.exceptions.AccountNotFoundException;
import org.serratec.backend2.trabalho.banco.exceptions.DuplicateAccountException;
import org.serratec.backend2.trabalho.banco.exceptions.InsufficientFundsException;
import org.serratec.backend2.trabalho.banco.exceptions.InvalidNumberException;
import org.serratec.backend2.trabalho.banco.exceptions.NotAllowedCreditException;
import org.serratec.backend2.trabalho.banco.exceptions.NotAlowedValueException;
import org.serratec.backend2.trabalho.banco.exceptions.OperationNotFoundException;
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
		if (numero <= 0) {
			throw new InvalidNumberException(numero);
		}

	}

	public List<Conta> listaConta() {
		return banco;
	}

	public Conta recuperarPorNumero(Integer numero) throws InvalidNumberException, AccountNotFoundException {
		validarNumero(numero);
		for (Conta conta : banco) {
			if (numero == conta.getNumero()) {
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

	/**
	 * TODO Antes de atualizar o número da conta, devemos verificar se não existe
	 * outra conta com o mesmo número. Caso exista, deveríamos ter uma Exception
	 * para tratar a duplicidade de número de contas.
	 * 
	 * FEITO
	 * 
	 * @throws DuplicateAccountException
	 */
	public Conta atualizarConta(Conta conta, Integer numero)
			throws InvalidNumberException, AccountNotFoundException, DuplicateAccountException {
		Conta contaAtualizada = recuperarPorNumero(numero);
		if (!(null == conta.getNumero()) && conta.getNumero() > 0) {
			for (Conta c : banco) {
				if (c.getNumero() == conta.getNumero()) {
					throw new DuplicateAccountException();
				}
			}
			contaAtualizada.setNumero(conta.getNumero());
		}

		if (!(null == conta.getTitular()) && !"".equals(conta.getTitular())) {
			contaAtualizada.setTitular(conta.getTitular());

		}

		/**
		 * FIXME Esta lógica semanticamente está um pouco "confusa". Não seria melhor
		 * criar o objeto com o nome "contaAtualizada" e depois dos sets para modificar
		 * as propriedades retornar o objeto?
		 * 
		 * Foi criado uma nova instância apenas para dar um novo nome ao objeto, pois
		 * nenhuma operação é realizada nele antes de retorná-lo na função
		 * 
		 * FEITO
		 * 
		 */

		return contaAtualizada;
	}

	public void apagarConta(Integer numero) throws InvalidNumberException, AccountNotFoundException {
		Conta conta = recuperarPorNumero(numero);
		banco.remove(conta);
	}

	// TODO NÃO PERMITIR FAZER UMA OPERAÇÃO COM VALOR 0 FEITO
	public List operacao(String operacao, Double valor, Integer numero)
			throws InvalidNumberException, AccountNotFoundException, InsufficientFundsException,
			NotAllowedCreditException, OperationNotFoundException, NotAlowedValueException {
		if (valor <= 0) {
			throw new NotAlowedValueException();
		}
		Conta conta = recuperarPorNumero(numero);
		Operacao operacaoClass = new Operacao();
		switch (operacao) {
		case "debito":
			operacaoClass.setTipo(TipoOperacao.valueOf(operacao.toUpperCase()));
			operacaoClass.setValor(valor);
			return operacaoService.debito(conta, operacaoClass);
		case "credito":
			operacaoClass.setTipo(TipoOperacao.valueOf(operacao.toUpperCase()));
			operacaoClass.setValor(valor);
			return operacaoService.credito(conta, operacaoClass);
		default:
			throw new OperationNotFoundException(operacao);
		}
	}
}

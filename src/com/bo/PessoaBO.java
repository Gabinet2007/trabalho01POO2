package com.bo;

import java.util.List;

import com.dao.PessoaDAO;
import com.model.Pessoa;

public class PessoaBO {
	
	private PessoaDAO pessoaDAO = new PessoaDAO(); 

	public Pessoa inserir(Pessoa pessoa) {
		int pessoaId = pessoaDAO.inserir(pessoa);
		
		pessoa.setId(pessoaId);
		
		return pessoa;
	}
	
	public void inserirLista(List<Pessoa> pessoas) {
		pessoaDAO.inserirLista(pessoas);
	}
}

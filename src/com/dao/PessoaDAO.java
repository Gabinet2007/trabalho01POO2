package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.model.Pessoa;

import util.Conexao;

public class PessoaDAO {
	final String TABELA = "pessoa";
	
	public int inserir(Pessoa pessoa) {
		String sql = "INSERT INTO " + TABELA + " (nome, idade, cidade, profissao, salario, altura, peso) VALUES (?, ?, ?, ?, ?, ?, ?);";
		
		try (				
			Connection conn = Conexao.conectar();
			PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		) {
			
			ps.setString(1, pessoa.getNome());
			ps.setInt(2, pessoa.getIdade());
			ps.setString(3, pessoa.getCidade());
			ps.setString(4, pessoa.getProfissao());
			ps.setDouble(5, pessoa.getSalario());
			ps.setDouble(6, pessoa.getAltura());
			ps.setDouble(7, pessoa.getPeso());
			
			int rows = ps.executeUpdate();
			
			if (rows == 0) {
				return 0;
			}
			
			try (
				ResultSet rs = ps.getGeneratedKeys();
			) {				
				if (rs.next()) {
					return rs.getInt(1);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DEBUG: Exception no inserir pessoa: " + e.getMessage());
		}
		
		return 0;
	}
	
	public void inserirLista(List<Pessoa> pessoas) {
		String sql = "INSERT INTO " + TABELA + " (nome, idade, cidade, profissao, salario, altura, peso) VALUES (?, ?, ?, ?, ?, ?, ?);";
		
		try (				
			Connection conn = Conexao.conectar();
			PreparedStatement ps = conn.prepareStatement(sql);
		) {
			
			for (Pessoa pessoa : pessoas) {		
				
				ps.setString(1, pessoa.getNome());
				ps.setInt(2, pessoa.getIdade());
				ps.setString(3, pessoa.getCidade());
				ps.setString(4, pessoa.getProfissao());
				ps.setDouble(5, pessoa.getSalario());
				ps.setDouble(6, pessoa.getAltura());
				ps.setDouble(7, pessoa.getPeso());
				
				ps.addBatch();
			}
			
			ps.executeBatch();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DEBUG: Exception no inserir pessoas: " + e.getMessage());
		}
	}
}

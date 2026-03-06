package com.model;

public class Pessoa {
	private int id = 0;
	private String nome;
	private int idade;
	private String cidade;
	private String profissao;
	private double salario;
	private double altura;
	private double peso;
	
	public Pessoa(String nome, int idade, String cidade, String profissao, double salario, double altura, double peso) {
		this.nome = nome;
		this.idade = idade;
		this.cidade = cidade;
		this.profissao = profissao;
		this.salario = salario;
		this.altura = altura;
		this.peso = peso;
	}
	
	public Pessoa(int id, String nome, int idade, String cidade, String profissao, double salario, double altura, double peso) {
		this.id = id;
		this.nome = nome;
		this.idade = idade;
		this.cidade = cidade;
		this.profissao = profissao;
		this.salario = salario;
		this.altura = altura;
		this.peso = peso;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getIdade() {
		return idade;
	}
	
	public void setIdade(int idade) {
		this.idade = idade;
	}
	
    public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public String getPrimeiroNome() {
        return nome.split(" ")[0];
    }
}

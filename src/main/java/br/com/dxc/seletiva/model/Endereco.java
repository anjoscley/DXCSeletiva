package br.com.dxc.seletiva.model;

import javax.persistence.Embeddable;

import br.com.dxc.seletiva.dto.endereco.EnderecoDTOAtualizacao;
import br.com.dxc.seletiva.dto.endereco.EnderecoDTOCadastro;

@Embeddable
public class Endereco {

    private String logradouro;
    private String bairro;
    private String cep;
    private String cidade;
    private String uf;
    private Integer numero;
    private String complemento;
    
    public Endereco() {}

    public Endereco(String logradouro, String bairro, String cep, String cidade, String uf, Integer numero, String complemento) {
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.uf = uf;
		this.numero = numero;
		this.complemento = complemento;
	}

	public Endereco(EnderecoDTOCadastro dto) {
        logradouro = dto.getLogradouro();
        bairro = dto.getBairro();
        cep = dto.getCep();
        cidade = dto.getCidade();
        uf = dto.getUf();
        numero = dto.getNumero();
        complemento = dto.getComplemento();
    }

    public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public void atualizarCampos(EnderecoDTOAtualizacao dados) {
        if (dados.getLogradouro() != null) logradouro = dados.getLogradouro();
        if (dados.getBairro() != null) bairro = dados.getBairro();
        if (dados.getCep() != null) cep = dados.getCep();
        if (dados.getCidade() != null) cidade = dados.getCidade();
        if (dados.getUf() != null) uf = dados.getUf();
        if (dados.getNumero() != null) numero = dados.getNumero();
        if (dados.getComplemento() != null) complemento = dados.getComplemento();
    }

}

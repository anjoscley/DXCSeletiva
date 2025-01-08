package br.com.dxc.seletiva.dto.endereco;

import br.com.dxc.seletiva.model.Endereco;

public class EnderecoDTODetalhe{
	
    private String logradouro;
    private String bairro;
    private String cep;
    private String cidade;
    private String uf;
    private Integer numero;
    private String complemento;
        
    public EnderecoDTODetalhe(Endereco endereco) {
        logradouro = endereco.getLogradouro();
        bairro = endereco.getBairro();
        cep = endereco.getCep();
        cidade = endereco.getCidade();
        uf = endereco.getUf();
        numero = endereco.getNumero();
        complemento = endereco.getComplemento();
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
    
}

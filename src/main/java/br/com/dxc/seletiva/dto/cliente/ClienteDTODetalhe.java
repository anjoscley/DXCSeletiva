package br.com.dxc.seletiva.dto.cliente;

import br.com.dxc.seletiva.dto.endereco.EnderecoDTODetalhe;
import br.com.dxc.seletiva.model.Cliente;

public class ClienteDTODetalhe{
	
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private EnderecoDTODetalhe endereco;
    private Boolean status; 

    public ClienteDTODetalhe(Cliente cliente) {
        id = cliente.getId();
        nome = cliente.getNome();
        email = cliente.getEmail();
        telefone = cliente.getTelefone();
        endereco = new EnderecoDTODetalhe(cliente.getEndereco());
        status = cliente.getStatus();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public EnderecoDTODetalhe getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoDTODetalhe endereco) {
		this.endereco = endereco;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
  
}

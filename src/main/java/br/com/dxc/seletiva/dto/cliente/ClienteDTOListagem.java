package br.com.dxc.seletiva.dto.cliente;

import br.com.dxc.seletiva.model.Cliente;

public class ClienteDTOListagem{
	
    private Long id;
    private String nome;
    private String email;

    public ClienteDTOListagem(Long id, String nome, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
	}

	public ClienteDTOListagem(Cliente cliente) {
        id = cliente.getId();
        nome = cliente.getNome();
        email = cliente.getEmail();
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
    
    
}

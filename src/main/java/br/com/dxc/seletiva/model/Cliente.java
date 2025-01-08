package br.com.dxc.seletiva.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.dxc.seletiva.dto.cliente.ClienteDTOAtualizacao;
import br.com.dxc.seletiva.dto.cliente.ClienteDTOCadastro;

@Table(name = "cliente")
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    @Embedded
    private Endereco endereco;
    private Boolean status = true;
    
    public Cliente() {}

    public Cliente(Long id, String nome, String email, String telefone, Endereco endereco, Boolean status) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.endereco = endereco;
		this.status = status;
	}

	public Cliente(ClienteDTOCadastro dto) {
        nome = dto.getNome();
        email = dto.getEmail();
        telefone = dto.getTelefone();
        endereco = new Endereco(dto.getEndereco());
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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	public void atualizarCampos(ClienteDTOAtualizacao dados) {
        if (dados.getNome() != null) nome = dados.getNome();
        if (dados.getTelefone() != null) telefone = dados.getTelefone();
        if (dados.getEndereco() != null) endereco.atualizarCampos(dados.getEndereco());
    }

    public void inativar() {
        status = false;
    }

}

package br.com.dxc.seletiva.dto.cliente;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import br.com.dxc.seletiva.dto.endereco.EnderecoDTOAtualizacao;

public class ClienteDTOAtualizacao{
	
        @NotNull
        private Long id;
        private String nome;
        @Pattern(regexp = "\\d{2}9\\d{8}")
        private String telefone;
        @Valid
        private EnderecoDTOAtualizacao endereco;
        
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
		public String getTelefone() {
			return telefone;
		}
		public void setTelefone(String telefone) {
			this.telefone = telefone;
		}
		public EnderecoDTOAtualizacao getEndereco() {
			return endereco;
		}
		public void setEndereco(EnderecoDTOAtualizacao endereco) {
			this.endereco = endereco;
		}
		public ClienteDTOAtualizacao(Long id, String nome, String telefone, EnderecoDTOAtualizacao endereco) {
			super();
			this.id = id;
			this.nome = nome;
			this.telefone = telefone;
			this.endereco = endereco;
		}
     
}

package br.com.dxc.seletiva.dto.cliente;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import br.com.dxc.seletiva.dto.endereco.EnderecoDTOCadastro;

public class ClienteDTOCadastro{
	
        @NotBlank
        private String nome;
        @NotBlank
        private String email;
        @NotBlank @Pattern(regexp = "\\d{2}9\\d{8}")
        private String telefone;
        @NotNull @Valid
        private EnderecoDTOCadastro endereco;
        
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
		public EnderecoDTOCadastro getEndereco() {
			return endereco;
		}
		public void setEndereco(EnderecoDTOCadastro endereco) {
			this.endereco = endereco;
		}

}

package br.com.dxc.seletiva.dto.endereco;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class EnderecoDTOCadastro{
	
        @NotBlank
        private String logradouro;
        @NotBlank
        private String bairro;
        @NotBlank @Pattern(regexp = "\\d{8}")
        private String cep;
        @NotBlank
        private String cidade;
        @NotBlank
        private String uf;
        private Integer numero;
        private String complemento;
        
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

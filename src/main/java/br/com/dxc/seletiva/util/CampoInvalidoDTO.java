package br.com.dxc.seletiva.util;

import org.springframework.validation.FieldError;

public class CampoInvalidoDTO{
	
	private String campo; 
	private String mensagem;
	
	public CampoInvalidoDTO(String campo, String mensagem) {
		this.campo = campo;
		this.mensagem = mensagem;
	}
	
    public CampoInvalidoDTO(FieldError erro) {
        this(erro.getField(), erro.getDefaultMessage());
    }

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
 
}

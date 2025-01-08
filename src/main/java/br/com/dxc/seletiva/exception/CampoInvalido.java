package br.com.dxc.seletiva.exception;

import org.springframework.validation.FieldError;

public class CampoInvalido{
	
	private String campo; 
	private String mensagem;
	
	public CampoInvalido(String campo, String mensagem) {
		this.campo = campo;
		this.mensagem = mensagem;
	}
	
    public CampoInvalido(FieldError erro) {
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

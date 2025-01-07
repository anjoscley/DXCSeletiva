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
}

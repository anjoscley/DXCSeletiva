package br.com.dxc.seletiva.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.dxc.seletiva.dto.cliente.ClienteDTOAtualizacao;
import br.com.dxc.seletiva.dto.cliente.ClienteDTOCadastro;
import br.com.dxc.seletiva.dto.cliente.ClienteDTODetalhe;
import br.com.dxc.seletiva.dto.cliente.ClienteDTOListagem;
import br.com.dxc.seletiva.model.Cliente;
import br.com.dxc.seletiva.repository.ClienteDAO;
import br.com.dxc.seletiva.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
    private ClienteRepository repository;
    @Autowired
    private ClienteDAO dao;
    
    public ClienteDTODetalhe registrar(ClienteDTOCadastro dados) {
    	Cliente cliente = new Cliente(dados);

        repository.save(cliente);
        
        return new ClienteDTODetalhe(cliente);
    }
    
    public Page<ClienteDTOListagem> listar(Pageable pageable) {
    	return repository.findByStatusTrue(pageable);
    }
    
    public List<ClienteDTOListagem> buscarPorNome(String nome) {
    	return repository.findByName(nome).stream().map(ClienteDTOListagem::new).collect(Collectors.toList());
    }
    
    public List<ClienteDTOListagem> buscarPorEndereco(String logradouro) {
    	return dao.findByAdress(logradouro);
    }
    
    public ClienteDTODetalhe detalhar(Long id) {
    	return new ClienteDTODetalhe(repository.getReferenceById(id));
    }
    
    public ClienteDTODetalhe atualizar(ClienteDTOAtualizacao dados) {
        Cliente cliente = repository.getReferenceById(dados.getId());
        cliente.atualizarCampos(dados);
    	return new ClienteDTODetalhe(cliente);
    }
    
    public void excluir(Long id) {
    	repository.deleteById(id);
    }
}

package br.com.dxc.seletiva.controller;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.dxc.seletiva.dto.cliente.ClienteDTOAtualizacao;
import br.com.dxc.seletiva.dto.cliente.ClienteDTOCadastro;
import br.com.dxc.seletiva.dto.cliente.ClienteDTODetalhe;
import br.com.dxc.seletiva.dto.cliente.ClienteDTOListagem;
import br.com.dxc.seletiva.model.Cliente;
import br.com.dxc.seletiva.repository.ClienteRepository;


@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<ClienteDTODetalhe> registrar(@RequestBody @Valid ClienteDTOCadastro dados, 
    		UriComponentsBuilder uriBuilder) {
    	
        Cliente cliente = new Cliente(dados);

        repository.save(cliente);
        URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();

        return ResponseEntity.created(uri).body(new ClienteDTODetalhe(cliente));
    }

	
	@GetMapping public ResponseEntity<Page<ClienteDTOListagem>> listar(@PageableDefault(sort = {"nome"}) Pageable pageable) {
	  
		Page<ClienteDTOListagem> page = repository.findByStatusTrue(pageable);
	  
		return ResponseEntity.ok(page); 
	}

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTODetalhe> detalhar(@PathVariable Long id) {
        Cliente cliente = repository.getReferenceById(id);

        return ResponseEntity.ok(new ClienteDTODetalhe(cliente));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ClienteDTODetalhe> atualizar(@RequestBody @Valid ClienteDTOAtualizacao dados) {
        Cliente cliente = repository.getReferenceById(dados.getId());
        cliente.atualizarCampos(dados);

        return ResponseEntity.ok(new ClienteDTODetalhe(cliente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
    	repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}

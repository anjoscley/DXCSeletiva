package br.com.dxc.seletiva.controller;

import java.net.URI;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.dxc.seletiva.dto.cliente.ClienteDTOAtualizacao;
import br.com.dxc.seletiva.dto.cliente.ClienteDTOCadastro;
import br.com.dxc.seletiva.dto.cliente.ClienteDTODetalhe;
import br.com.dxc.seletiva.dto.cliente.ClienteDTOListagem;
import br.com.dxc.seletiva.service.ClienteService;


@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
    ClienteService service;

    @PostMapping
    @Transactional
    public ResponseEntity<ClienteDTODetalhe> registrar(@RequestBody @Valid ClienteDTOCadastro dados, 
    		UriComponentsBuilder uriBuilder) {
    	
    	ClienteDTODetalhe cliente = service.registrar(dados);
    	
        URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();

        return ResponseEntity.created(uri).body(cliente);
    }
	
	@GetMapping public ResponseEntity<Page<ClienteDTOListagem>> listar(@PageableDefault(sort = {"nome"}) Pageable pageable) {
		return ResponseEntity.ok(service.listar(pageable)); 
	}

	@GetMapping("buscarPorNome") 
	public ResponseEntity<List<ClienteDTOListagem>> buscarPorNome(@RequestParam String nome) {
		return ResponseEntity.ok(service.buscarPorNome(nome)); 
	}
	
	@GetMapping("buscarPorEndereco") 
	public ResponseEntity<List<ClienteDTOListagem>> buscarPorEndereco(String logradouro) {
		return ResponseEntity.ok(service.buscarPorEndereco(logradouro)); 
	}
	
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTODetalhe> detalhar(@PathVariable Long id) {
        return ResponseEntity.ok(service.detalhar(id));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ClienteDTODetalhe> atualizar(@RequestBody @Valid ClienteDTOAtualizacao dados) {
        return ResponseEntity.ok(service.atualizar(dados));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
    	service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}

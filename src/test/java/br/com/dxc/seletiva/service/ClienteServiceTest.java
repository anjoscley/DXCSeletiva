package br.com.dxc.seletiva.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import br.com.dxc.seletiva.dto.cliente.ClienteDTOAtualizacao;
import br.com.dxc.seletiva.dto.cliente.ClienteDTOCadastro;
import br.com.dxc.seletiva.dto.cliente.ClienteDTODetalhe;
import br.com.dxc.seletiva.dto.cliente.ClienteDTOListagem;
import br.com.dxc.seletiva.dto.endereco.EnderecoDTOAtualizacao;
import br.com.dxc.seletiva.dto.endereco.EnderecoDTOCadastro;
import br.com.dxc.seletiva.model.Cliente;
import br.com.dxc.seletiva.model.Endereco;
import br.com.dxc.seletiva.repository.ClienteRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @Mock
    private ClienteRepository repository;

    @InjectMocks
    private ClienteService service;
    
    @Test
    public void testeListarClientes() {
    	ClienteDTOListagem clienteHomem = new ClienteDTOListagem(new Long(1), "Cley Anjos", "anjoscley@gmail.com");
    	ClienteDTOListagem clienteMulher = new ClienteDTOListagem(new Long(2), "Ellen Anjos", "anjosellen@gmail.com");

        when(repository.findByStatusTrue(null)).thenReturn(new PageImpl(Arrays.asList(clienteHomem,
        		clienteMulher)));

        Page<ClienteDTOListagem> paginaClientes = service.listar(null);
        
        verify(repository, times(1)).findByStatusTrue(null);
        assertTrue(paginaClientes.hasContent());
        assertEquals(2, paginaClientes.getNumberOfElements());
        assertTrue(paginaClientes.getContent().contains(clienteHomem));
        assertTrue(paginaClientes.getContent().contains(clienteMulher));
    }
    
    @Test
    public void testeDetalharCliente() {
        Long clienteId = Long.valueOf(1L);
        Endereco enderecoCliente = new Endereco("Costa Oeste", "Malhado", "45651440", "Ilheus", "BA", 232, "Travessa 14");
        Cliente cliente = new Cliente(clienteId, "Cley Anjos", "anjoscley@gmail.com", "73991242576",
        		enderecoCliente, true);
        
        when(repository.getReferenceById(clienteId)).thenReturn(cliente);

        ClienteDTODetalhe clienteDetalhe = service.detalhar(clienteId);

        verify(repository, times(1)).getReferenceById(clienteId);
        assertEquals(clienteId, clienteDetalhe.getId());
        assertEquals(cliente.getNome(), clienteDetalhe.getNome());
        assertEquals(enderecoCliente.getCep(), clienteDetalhe.getEndereco().getCep());
    }

    @Test
    public void testeRegistrarCliente() {    	
        ClienteDTOCadastro dadosCliente = new ClienteDTOCadastro("Cley Anjos", "anjoscley@gmail.com", "73991242576",
        		new EnderecoDTOCadastro("Costa Oeste", "Malhado", "45651440", "Ilheus", "BA", 232,
        				"Em frente a igreja catolica"));
                
        Cliente novoCliente = new Cliente(dadosCliente);

        when(repository.save(any())).thenReturn(novoCliente);

        ClienteDTODetalhe clienteCadastrado = service.registrar(dadosCliente);

        verify(repository, times(1)).save(any());
        assertEquals(novoCliente.getNome(), clienteCadastrado.getNome());
        assertEquals(novoCliente.getTelefone(), clienteCadastrado.getTelefone());
        assertEquals(novoCliente.getEmail(), clienteCadastrado.getEmail());
        assertEquals(novoCliente.getEndereco().getCep(), clienteCadastrado.getEndereco().getCep());
    }
    
    @Test
    public void testeAtualizarCliente() {
        Long clienteId = Long.valueOf(1);

        ClienteDTOAtualizacao atualizarCliente = new ClienteDTOAtualizacao(clienteId, "Dario Anjos", "73991010203",
        		new EnderecoDTOAtualizacao());

        when(repository.getReferenceById(clienteId)).thenReturn(new Cliente(clienteId, "Cley Anjos", "anjoscley@gmail.com", "73991242576",
        		new Endereco("Costa Oeste", "Malhado", "45651440", "Ilheus", "BA", 232, "Travessa 14"), true));

        ClienteDTODetalhe clienteAtualizado = service.atualizar(atualizarCliente);

        verify(repository, times(1)).getReferenceById(clienteId);
        assertEquals(atualizarCliente.getNome(), clienteAtualizado.getNome());
        assertEquals(atualizarCliente.getTelefone(), clienteAtualizado.getTelefone());
    }
    
    @Test
    public void testeExcluirCliente() {
        Long clienteId = Long.valueOf(1);
        
        doNothing().when(repository).deleteById(clienteId);

        service.excluir(clienteId);

        verify(repository, times(1)).deleteById(clienteId);
    }

}

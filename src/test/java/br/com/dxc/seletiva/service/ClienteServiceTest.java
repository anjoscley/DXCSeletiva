package br.com.dxc.seletiva.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import br.com.dxc.seletiva.dto.cliente.ClienteDTOCadastro;
import br.com.dxc.seletiva.dto.cliente.ClienteDTODetalhe;
import br.com.dxc.seletiva.dto.cliente.ClienteDTOListagem;
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

    public void testeListarClientes() {
    	ClienteDTOListagem clienteHomem = new ClienteDTOListagem(new Long(1), "Cley Anjos", "anjoscley@gmail.com");
    	ClienteDTOListagem clienteMulher = new ClienteDTOListagem(new Long(2), "Ellen Anjos", "anjosellen@gmail.com");

        when(repository.findByStatusTrue(null)).thenReturn(new PageImpl(Arrays.asList(clienteHomem,
        		clienteMulher)));

        Page<ClienteDTOListagem> paginaClientes = service.listar(null);

        assertTrue(paginaClientes.hasContent());
        assertEquals(2, paginaClientes.getNumberOfElements());
        assertTrue(paginaClientes.getContent().contains(clienteHomem));
        assertTrue(paginaClientes.getContent().contains(clienteMulher));
    }
    
    public void testeDetalharCliente() {
        Long clienteId = Long.valueOf(1L);
        Endereco enderecoCliente = new Endereco("Costa Oeste", "Malhado", "45651440", "Ilheus", "BA", 232, "Travessa 14");
        Cliente cliente = new Cliente(clienteId, "Cley Anjos", "anjoscley@gmail.com", "73991242576",
        		enderecoCliente, true);
        
        when(repository.getReferenceById(clienteId)).thenReturn(cliente);

        ClienteDTODetalhe clienteDetalhe = service.detalhar(clienteId);

        assertEquals(clienteId, clienteDetalhe.getId());
        assertEquals(cliente.getNome(), clienteDetalhe.getNome());
        assertEquals(enderecoCliente.getCep(), clienteDetalhe.getEndereco().getCep());
    }

    public void testeRegistrarCliente() {
        ClienteDTOCadastro dadosCliente = new ClienteDTOCadastro("Cley Anjos", "anjoscley@gmail.com", "73991242576",
        		new EnderecoDTOCadastro("Costa Oeste", "Malhado", "45651440", "Ilheus", "BA", 232,
        				"Em frente a igreja catolica"));
        
        Cliente novoCliente = new Cliente(dadosCliente);
        
        Cliente novoClienteCadastrado = novoCliente;
        novoClienteCadastrado.setId(Long.valueOf(1l));

        lenient().when(repository.save(novoCliente)).thenReturn(novoClienteCadastrado);

        ClienteDTODetalhe clienteCadastrado = service.registrar(dadosCliente);

        assertEquals(novoCliente.getNome(), clienteCadastrado.getNome());
        assertEquals(novoCliente.getTelefone(), clienteCadastrado.getTelefone());
        assertEquals(novoCliente.getEmail(), clienteCadastrado.getEmail());
        assertEquals(novoCliente.getEndereco().getCep(), clienteCadastrado.getEndereco().getCep());
    }

}

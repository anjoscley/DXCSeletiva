package br.com.dxc.seletiva.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


@SpringBootTest 
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public  class  ClienteControllerTest { 

    @Autowired 
    private MockMvc mockMvc; 

    @Test
    @Order(1)
    public void testeRegistrarCliente()  throws Exception { 
        String  clienteJson  =  "{\"nome\":\"Cley Anjos\",\"email\":\"anjoscley@gmail.com\", "
        		+ "\"telefone\":\"73991242576\",\"endereco\":{\"logradouro\":\"Rua da Amendoeira\", " 
        		+ "\"bairro\":\"Malhado\",\"cep\":\"45651440\",\"cidade\":\"Ilheus\", \"uf\": \"BA\", "
        		+ "\"numero\": 232, \"complemento\":\"Em frente a igreja catolica\"} }";
                		        
        ResultActions  result  = mockMvc.perform(post("/clientes")
        		.with(httpBasic("aprovado", "0xA")) 
                .contentType(MediaType.APPLICATION_JSON) 
                .content(clienteJson)); 

         result.andExpect(status().isCreated()) 
              .andExpect(header().string( "Location" , "http://localhost/clientes/1" )); 
    } 
    
    @Test
    @Order(2)
    public void testeDetalharCliente() throws Exception {
        long clienteId = 1L;

        ResultActions result = mockMvc.perform(get("/clientes/{id}", clienteId)
        		.with(httpBasic("aprovado", "0xA")));

        result.andExpect(status().isOk())
              .andExpect(content().contentType(MediaType.APPLICATION_JSON))
              .andExpect(jsonPath("$.id").value(clienteId))
              .andExpect(jsonPath("$.nome").value("Cley Anjos"))
              .andExpect(jsonPath("$.email").value("anjoscley@gmail.com"));
    }
    
    @Test
    @Order(3)
    public void testeAtualizarCliente() throws Exception {
        long clienteId = 1L;
        String  clienteJson  =  "{\"id\":1,\"nome\":\"Dari Anjos\", "
        		+ "\"telefone\":\"73981545698\",\"endereco\":{\"logradouro\":\"Rua da Amendoeira\", " 
        		+ "\"bairro\":\"Malhado\",\"cep\":\"45651440\",\"cidade\":\"Ilheus\", \"uf\": \"BA\", "
        		+ "\"numero\": 232, \"complemento\":\"Em frente a igreja catolica\"} }";
        
        ResultActions result = mockMvc.perform(put("/clientes", clienteId)
        		.with(httpBasic("aprovado", "0xA"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(clienteJson));

        result.andExpect(status().isOk())
              .andExpect(jsonPath("$.id").value(clienteId))
              .andExpect(jsonPath("$.nome").value("Dari Anjos"))
              .andExpect(jsonPath("$.telefone").value("73981545698"));
    }
    
    @Test
    @Order(4)
    public void testeExcluirCliente() throws Exception {
        long clienteId = 1L;

        ResultActions result = mockMvc.perform(delete("/clientes/{id}", clienteId));

        result.andExpect(status().isNoContent());
    }
    
    @Test
    @Order(5)
    public void testeBuscarClienteExcluido() throws Exception {
        long clienteId = 1L;

        ResultActions result = mockMvc.perform(get("/clientes/{id}", clienteId)
        		.with(httpBasic("aprovado", "0xA")));

        result.andExpect(status().isOk())
              .andExpect(content().contentType(MediaType.APPLICATION_JSON))
              .andExpect(jsonPath("$.id").value(clienteId));
    }

}

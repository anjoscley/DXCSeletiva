package br.com.dxc.seletiva.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dxc.seletiva.dto.cliente.ClienteDTOListagem;
import br.com.dxc.seletiva.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Page<ClienteDTOListagem> findByStatusTrue(Pageable pageable);
}

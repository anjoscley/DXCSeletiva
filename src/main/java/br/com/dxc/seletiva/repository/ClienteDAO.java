package br.com.dxc.seletiva.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.dxc.seletiva.dto.cliente.ClienteDTOListagem;

@Repository
public class ClienteDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<ClienteDTOListagem> findByAdress(String logradouro){
		String sql = "SELECT id, nome, email FROM cliente c WHERE c.logradouro ilike ?";

		List<ClienteDTOListagem> clientes = jdbcTemplate.query(sql, (rs, row) ->{
				ClienteDTOListagem dto = new ClienteDTOListagem(rs.getLong(1), rs.getString(2), rs.getString(3));
				return dto;
			}, "%"+logradouro+"%");
		
		return clientes;
	}
	
}

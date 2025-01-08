package br.com.dxc.seletiva.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SeletivaSecurityConfig {
	
	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		
		UserDetails user = User.withDefaultPasswordEncoder()
				.username("aprovado")
				.password("0xA")
				.roles("USER")
				.build();
		
		return new InMemoryUserDetailsManager(user);
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
		httpSecurity.authorizeHttpRequests( (authz) -> authz 
				.antMatchers("/clientes")
				.authenticated()).httpBasic();
		
		return httpSecurity.build();
	}
	
	

}

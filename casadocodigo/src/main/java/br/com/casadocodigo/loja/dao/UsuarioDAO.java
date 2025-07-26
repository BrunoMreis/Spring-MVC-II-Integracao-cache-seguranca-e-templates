package br.com.casadocodigo.loja.dao;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.casadocodigo.loja.models.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class UsuarioDAO implements UserDetailsService{

	
	@PersistenceContext
	private EntityManager manager;
	
	
	
	public Usuario loadUserByUsername(String email) {
		List<Usuario> usuarios = manager.createQuery("SELECT u from Usuario u WHERE u.email = :email", Usuario.class)
				.setParameter("email", email)
				.getResultList();
		
	    if(usuarios.isEmpty()) {
            throw new UsernameNotFoundException("Usuário " + email + " não foi encontrado");
        }
		
		
		return usuarios.get(0); 
		
	}
	
}

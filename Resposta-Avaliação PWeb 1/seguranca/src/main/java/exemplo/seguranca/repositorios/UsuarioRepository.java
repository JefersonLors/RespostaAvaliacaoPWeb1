package exemplo.seguranca.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import exemplo.seguranca.entidades.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	UserDetails findByLogin(String username);

}

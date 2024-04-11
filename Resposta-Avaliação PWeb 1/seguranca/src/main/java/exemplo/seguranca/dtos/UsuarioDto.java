package exemplo.seguranca.dtos;

import java.util.List;

import exemplo.seguranca.entidades.Usuario;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UsuarioDto(
		Long id,
		@NotNull(message="O nome não pode ser nulo") String nome, 
		@NotNull String login,
		@NotNull String senha,
		@NotNull List<RoleDto> roles) {
	
	public UsuarioDto(Usuario usuario) {
		this(usuario.getId(), usuario.getNome(), usuario.getLogin(), usuario.getSenha(), 
				usuario.getRoles().stream().map(RoleDto::new).toList());
	}

}

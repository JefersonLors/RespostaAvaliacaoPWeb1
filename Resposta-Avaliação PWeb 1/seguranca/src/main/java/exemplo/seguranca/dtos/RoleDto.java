package exemplo.seguranca.dtos;

import exemplo.seguranca.entidades.Role;

public record RoleDto(Long id, String role) {
	
	public RoleDto(Role role) {
		this(role.getId(), role.getRole());
	}

}

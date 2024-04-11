package exemplo.seguranca.entidades;

import org.springframework.security.core.GrantedAuthority;

import exemplo.seguranca.dtos.RoleDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role implements GrantedAuthority {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String role;
	
	public Role(RoleDto roleDto) {
		this.id = roleDto.id();
		this.role = roleDto.role();
	}
	
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return role;
	}

}

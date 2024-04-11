package exemplo.seguranca.entidades;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import exemplo.seguranca.dtos.UsuarioDto;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity(name = "usuarios")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements UserDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String login;
	private String senha;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
            name = "usuarios_roles",
            joinColumns = @JoinColumn(name = "usuarios_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id")
            )
	private List<Role> roles= new ArrayList<Role>();
	
	public Usuario(UsuarioDto usuarioDto) {
		this.nome = usuarioDto.nome();
		this.login = usuarioDto.login();
		this.senha = usuarioDto.senha();
		this.roles = usuarioDto.roles().stream().map(Role::new).toList();
	}

	public Usuario(String nome, String login, String senhaEncripatada, List<Role> roles) {
		this.nome =  nome;
		this.login = login;
		this.senha = senhaEncripatada;
		this.roles = roles;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		//return List.of(new SimpleGrantedAuthority("ROLE_USER"));
		return roles;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return senha;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return login;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
}

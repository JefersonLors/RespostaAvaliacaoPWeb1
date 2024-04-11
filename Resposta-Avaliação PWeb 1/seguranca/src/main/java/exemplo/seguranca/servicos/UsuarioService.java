package exemplo.seguranca.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import exemplo.seguranca.dtos.UsuarioDto;
import exemplo.seguranca.entidades.Role;
import exemplo.seguranca.entidades.Usuario;
import exemplo.seguranca.repositorios.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	
	public Page<UsuarioDto> listar(Pageable pageble) {
		
		Page<Usuario> usuarios = usuarioRepository.findAll(pageble);
		return usuarios.map(UsuarioDto::new);
	}
	
	public ResponseEntity<UsuarioDto> salvar(UsuarioDto usuarioDto) {

		Usuario usuario = new Usuario(usuarioDto);
		usuarioRepository.save(usuario);
		return ResponseEntity.ok(new UsuarioDto(usuario));
	}

	public ResponseEntity<UsuarioDto> deletar(Long id) {
		var usuario = usuarioRepository.findById(id);
		if (usuario.isPresent()) {
			usuarioRepository.delete(usuario.get());
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}

package exemplo.seguranca.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import exemplo.seguranca.dtos.UsuarioDto;
import exemplo.seguranca.servicos.UsuarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios") 
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping
	public Page<UsuarioDto> listar(Pageable pageble) {
			
		return usuarioService.listar(pageble);
	}
	
	@PostMapping
	public ResponseEntity<UsuarioDto> salvar(@RequestBody @Valid UsuarioDto usuarioDto) {

		return usuarioService.salvar(usuarioDto);
	}
		

	@DeleteMapping("/{id}")
	@Transactional
	@Secured("ROLE_ADMIN")
	public ResponseEntity<UsuarioDto> deletar(@PathVariable Long id) {

		return usuarioService.deletar(id);
	}
	
}

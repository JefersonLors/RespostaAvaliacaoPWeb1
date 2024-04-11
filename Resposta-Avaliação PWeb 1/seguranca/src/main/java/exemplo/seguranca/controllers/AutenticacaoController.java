package exemplo.seguranca.controllers;

import exemplo.seguranca.dtos.DadosRegistro;
import exemplo.seguranca.entidades.Role;
import exemplo.seguranca.repositorios.UsuarioRepository;
import exemplo.seguranca.servicos.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import exemplo.seguranca.dtos.DadosAutenticacao;
import exemplo.seguranca.dtos.DadosTokenJWT;
import exemplo.seguranca.entidades.Usuario;
import exemplo.seguranca.servicos.JWTokenService;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private JWTokenService tokenService;

	@Autowired
	private UsuarioRepository usuarioRepository;
	@PostMapping
	public ResponseEntity efetuarLogin(@RequestBody DadosAutenticacao dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }

	@PostMapping("/registrar")
	public ResponseEntity efetuarRegistro(@RequestBody DadosRegistro dados) {
		if( this.usuarioRepository.findByLogin(dados.login()) != null )
			return ResponseEntity.badRequest().body("usuário ja cadastrado");

		String senhaEncripatada = new BCryptPasswordEncoder().encode(dados.senha());
		Usuario usuario = new Usuario(dados.nome(), dados.login(), senhaEncripatada, dados.roles().stream().map(Role::new).toList() );

		this.usuarioRepository.save(usuario);

		return ResponseEntity.ok("usuário registrado");
	}

}

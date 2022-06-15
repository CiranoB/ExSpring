package org.generation.blogPessoal.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.generation.blogPessoal.model.Tema;
import org.generation.blogPessoal.model.UserLogin;
import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.generation.blogPessoal.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioRepository repository;
	
	
	@GetMapping
	public ResponseEntity<List<Usuario>> listar(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{idUsuario}")
	public ResponseEntity<Usuario> buscarId(@PathVariable Long idUsuario){
		return repository.findById(idUsuario)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	
	@PostMapping("/logar")
	public ResponseEntity<UserLogin> Autentication (@RequestBody Optional<UserLogin> user){
		return usuarioService.autenticarUsuario(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	

	
	@PutMapping("/atualizar")
	public ResponseEntity<Usuario> putUsuario(@Valid @RequestBody Usuario usuario) {
		return usuarioService.atualizarUsuario(usuario)
			.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
			.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> Post (@RequestBody Usuario usuario){
		return usuarioService.cadastrarUsuario(usuario)
				.map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
						.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
				//ResponseEntity.status(HttpStatus.CREATED)
				//.body(usuarioService.CadastrarUsuario(usuario))
				//.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}
	
	
}

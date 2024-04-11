package exemplo.seguranca.controllers;

import exemplo.seguranca.dtos.LivroDto;
import exemplo.seguranca.servicos.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {
    @Autowired
    private LivroService livroService;

    @GetMapping
    public ResponseEntity<List<LivroDto>> getAllLivros(){
        return this.livroService.getAllLivros();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroDto> getLivroById(@PathVariable long id){
        return this.livroService.getLivroById(id);
    }

    @GetMapping("/titulo")
    public ResponseEntity<List<LivroDto>> getLivroByTitulo(@RequestParam String titulo){
        return this.livroService.getByTitulo(titulo);
    }

}

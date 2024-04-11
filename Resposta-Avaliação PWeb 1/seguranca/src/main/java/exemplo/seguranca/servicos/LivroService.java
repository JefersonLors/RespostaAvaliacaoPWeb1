package exemplo.seguranca.servicos;

import exemplo.seguranca.dtos.LivroDto;
import exemplo.seguranca.entidades.Livro;
import exemplo.seguranca.repositorios.LivroRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {
    @Autowired
    private LivroRepositoryInterface livroRepository;

    public ResponseEntity<List<LivroDto>> getAllLivros(){
        List<LivroDto> livros = this.livroRepository.findAll().stream().map(LivroDto::new).toList();
        return ResponseEntity.ok(livros);
    }

    public ResponseEntity<LivroDto> getLivroById(Long id){
        Optional<Livro> livro = this.livroRepository.findById(id);
        return livro.map(l -> ResponseEntity.ok(new LivroDto(l))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<List<LivroDto>> getByTitulo(String titulo){
        List<LivroDto> livros = this.livroRepository.findByTitulo(titulo).stream().map(LivroDto::new).toList();
        return ResponseEntity.ok(livros);
    }
}

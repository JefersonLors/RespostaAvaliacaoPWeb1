package exemplo.seguranca.repositorios;

import exemplo.seguranca.entidades.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LivroRepositoryInterface extends JpaRepository<Livro, Long> {

    List<Livro> findByTitulo(String titulo);
}


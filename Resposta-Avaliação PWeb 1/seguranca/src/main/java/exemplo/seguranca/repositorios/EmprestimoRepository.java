package exemplo.seguranca.repositorios;

import exemplo.seguranca.entidades.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
}

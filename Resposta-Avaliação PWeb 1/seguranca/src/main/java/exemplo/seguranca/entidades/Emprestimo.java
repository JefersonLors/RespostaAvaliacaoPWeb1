package exemplo.seguranca.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity(name="emprestimo")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Emprestimo {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private Long idUsuario;

    @NonNull
    private Long idLivro;

    @NonNull
    private boolean status;

    public Emprestimo(Long idUsuario, Long idLivro, boolean status) {
        this.idUsuario = idUsuario;
        this.idLivro = idLivro;
        this.status = status;
    }
}

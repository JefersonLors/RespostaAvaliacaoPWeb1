package exemplo.seguranca.entidades;

import exemplo.seguranca.dtos.LivroDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "livros")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Livro {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String autor;

    public Livro(LivroDto livroDto){
        this.id = livroDto.id();
        this.titulo = livroDto.titulo();
    }

}

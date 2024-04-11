package exemplo.seguranca.dtos;

import exemplo.seguranca.entidades.Livro;

public record LivroDto(Long id, String titulo, String autor) {

    public LivroDto(Livro livro) {
        this(livro.getId(), livro.getTitulo(), livro.getAutor());
    }
}

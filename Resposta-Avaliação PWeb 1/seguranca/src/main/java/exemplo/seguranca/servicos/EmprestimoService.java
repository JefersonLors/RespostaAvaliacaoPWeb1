package exemplo.seguranca.servicos;

import exemplo.seguranca.dtos.CheckOutData;
import exemplo.seguranca.dtos.CheckInData;
import exemplo.seguranca.dtos.LivroDto;
import exemplo.seguranca.entidades.Emprestimo;
import exemplo.seguranca.repositorios.EmprestimoRepository;
import exemplo.seguranca.repositorios.LivroRepositoryInterface;
import exemplo.seguranca.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmprestimoService {
    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private LivroRepositoryInterface livroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ResponseEntity<String> checkOutLivro(CheckOutData checkOutData){
        if( !this.livroRepository.existsById(checkOutData.livro_id()))
            return new ResponseEntity<>("Livro não existe.", HttpStatus.NOT_FOUND);

        if(!this.usuarioRepository.existsById(checkOutData.usuario_id()))
            return new ResponseEntity<>("Usuário não existe.", HttpStatus.NOT_FOUND);

        this.emprestimoRepository.save(new Emprestimo(checkOutData.livro_id(), checkOutData.usuario_id(), true));
        return ResponseEntity.ok("Livro emprestado com sucesso!");
    }

    public ResponseEntity<String> checkInLivro(Long id, CheckInData checkInData){
        Optional<Emprestimo> emprestimo = this.emprestimoRepository.findById(id);

        if(!emprestimo.isPresent())
            return new ResponseEntity<>("Esse empréstimo não aconteceu", HttpStatus.NOT_FOUND);

        if( checkInData.status() != null
                && !checkInData.status().isEmpty()
                && checkInData.status().equalsIgnoreCase("devolvido")){

            Emprestimo emprestimoConteudo = emprestimo.get();

            if( emprestimoConteudo.isStatus() == false)
                return new ResponseEntity<>("O livro já foi devolvido", HttpStatus.BAD_REQUEST);

            emprestimoConteudo.setStatus(false);
            this.emprestimoRepository.save(emprestimoConteudo);
            return ResponseEntity.ok("Livro devolvido com sucesso!");
        }

        return new ResponseEntity<>("status desconhecido", HttpStatus.BAD_REQUEST);
    }
}

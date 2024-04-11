package exemplo.seguranca.controllers;

import exemplo.seguranca.dtos.CheckOutData;
import exemplo.seguranca.dtos.CheckInData;
import exemplo.seguranca.dtos.LivroDto;
import exemplo.seguranca.servicos.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {
    @Autowired
    private EmprestimoService emprestimoService;

    @PostMapping
    public ResponseEntity<String> checkOutLivro(@RequestBody CheckOutData checkOutData){
        return this.emprestimoService.checkOutLivro(checkOutData);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> checkInLivro(@RequestBody CheckInData status, @PathVariable Long id){
        return this.emprestimoService.checkInLivro(id, status);
    }
}

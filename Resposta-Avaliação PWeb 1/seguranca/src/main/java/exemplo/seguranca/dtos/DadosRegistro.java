package exemplo.seguranca.dtos;

import java.util.List;

public record DadosRegistro (String nome, String login, String senha, List<RoleDto> roles) {
}

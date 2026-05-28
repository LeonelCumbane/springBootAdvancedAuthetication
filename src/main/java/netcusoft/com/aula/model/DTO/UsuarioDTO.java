package netcusoft.com.aula.model.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;

public class UsuarioDTO {
    @Column(unique = true, nullable = false)
    @Size(min = 4,message = "Nome muito curto")
    private String username;

    public UsuarioDTO() {
    }

    public UsuarioDTO(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

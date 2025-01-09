package foro.hub.api.domain.Usuario;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosRegistroUsuario(

        @NotBlank
        String nombre,
        @JsonAlias("correoElectronico")
        @NotBlank
        @Email
        String correo,
        @NotBlank
        String contrasena
) {
}

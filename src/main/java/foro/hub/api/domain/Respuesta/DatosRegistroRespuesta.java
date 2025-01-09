package foro.hub.api.domain.Respuesta;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.time.LocalDateTime;

public record DatosRegistroRespuesta(

        @NotBlank
        String mensaje,
        @NotNull
        LocalDateTime fecha,
        @NotNull
        Long idTopico,
        @NotNull
        Long idUsuario
) {
}

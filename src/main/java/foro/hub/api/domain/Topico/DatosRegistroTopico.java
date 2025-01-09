package foro.hub.api.domain.Topico;

import foro.hub.api.domain.Respuesta.Respuesta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public record DatosRegistroTopico(

        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotNull
        LocalDateTime fecha,
        @NotBlank
        String status,
        @NotNull
        Long idUsuario,
        @NotNull
        Long idCurso,
        @NotNull
        List<Respuesta> respuestas

) {
}

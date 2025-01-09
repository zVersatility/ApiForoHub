package foro.hub.api.domain.Topico;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosActualizaTopico(

        @NotNull
        Long idTopico,
        String titulo,
        String mensaje,
        LocalDateTime fecha,
        String status,
        Long idUsuario,
        Long idCurso


) {
}

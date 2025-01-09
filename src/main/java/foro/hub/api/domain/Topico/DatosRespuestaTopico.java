package foro.hub.api.domain.Topico;

import foro.hub.api.domain.Respuesta.DatosRespuesta;


import java.time.LocalDateTime;
import java.util.List;

public record DatosRespuestaTopico(
        Long idTopico,
        String titulo,
        String mensaje,
        LocalDateTime fecha,
        String status,
        String usuario,
        String curso,
        boolean activo,
        List<DatosRespuesta> respuestas

) {
}

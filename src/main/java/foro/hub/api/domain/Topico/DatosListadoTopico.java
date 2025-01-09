package foro.hub.api.domain.Topico;
import foro.hub.api.domain.Respuesta.DatosRespuesta;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record DatosListadoTopico(
        Long idTopico,
        String titulo,
        String mensaje,
        LocalDateTime fecha,
        String status,
        String nombreUsuario,
        String nombreCurso,
        List<DatosRespuesta> respuestas) {

    public DatosListadoTopico(Topico topico) {
        this(
                topico.getId_Topico(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFecha_De_Creacion(),
                topico.getStatus(),
                topico.getUsuario().getNombre_Usuario(),
                topico.getCurso().getNombre_Curso(),
                topico.getRespuestas().stream()
                        .map(DatosRespuesta::new)
                        .collect(Collectors.toList())
        );
    }
}

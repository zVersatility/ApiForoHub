package foro.hub.api.domain.Respuesta;

import foro.hub.api.domain.Topico.Topico;
import foro.hub.api.domain.Usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;


@Table(name = "respuestas")
@Entity(name = "Respuesta")
@AllArgsConstructor
@Getter
//@NoArgsConstructor
@EqualsAndHashCode(of = "id_Respuesta")
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_Respuesta;
    private String mensaje;
    private LocalDateTime fecha_De_Creacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id", nullable = false)
    private Topico topico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
    private boolean activo;



    public Respuesta(@NotBlank String mensaje, @NotNull LocalDateTime fecha, Topico topico, Usuario usuario) {
            this.mensaje=mensaje;
            this.fecha_De_Creacion=fecha;
            this.topico=topico;
            this.usuario=usuario;
            this.activo=true;
    }

    public Respuesta() {
    }

    public long getId_Respuesta() {
        return id_Respuesta;
    }

    public void setId_Respuesta(long id_Respuesta) {
        this.id_Respuesta = id_Respuesta;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFecha_De_Creacion() {
        return fecha_De_Creacion;
    }

    public void setFecha_De_Creacion(LocalDateTime fecha_De_Creacion) {
        this.fecha_De_Creacion = fecha_De_Creacion;
    }

    public Topico getTopico() {
        return topico;
    }

    public void setTopico(Topico topico) {
        this.topico = topico;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}

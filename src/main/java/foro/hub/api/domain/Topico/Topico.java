package foro.hub.api.domain.Topico;

import foro.hub.api.domain.Curso.Curso;
import foro.hub.api.domain.Curso.CursoRepository;
import foro.hub.api.domain.Respuesta.Respuesta;
import foro.hub.api.domain.Usuario.Usuario;
import foro.hub.api.domain.Usuario.UsuarioRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;


@Table(name = "topicos")
@Entity(name = "Topico")
@AllArgsConstructor
@Getter
//@NoArgsConstructor
@EqualsAndHashCode(of = "id_Topico")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_Topico;
    @Column(unique = true)
    private String titulo;
    @Column(unique = true)
    private String mensaje;
    @Column(name = "fecha_De_Creacion")
    private LocalDateTime fechaDeCreacion;
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;
    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Respuesta> respuestas;
    private  boolean activo;


    public Topico(String titulo, String mensaje, LocalDateTime fechaDeCreacion, String status, Usuario usuario, Curso curso, List<Respuesta> respuestas) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fechaDeCreacion = fechaDeCreacion;
        this.status = status;
        this.usuario = usuario;
        this.curso = curso;
        this.respuestas = respuestas;
        this.activo = true;
    }


    public void actualizarDatos(DatosActualizaTopico datosActualizaTopico, UsuarioRepository usuarioRepository, CursoRepository cursoRepository) {
        if (!this.activo) {
            throw new IllegalArgumentException("El tópico no está activo y no puede ser actualizado.");
        }

        if (datosActualizaTopico.idUsuario() != null) {
            Usuario usuario = usuarioRepository.findById(datosActualizaTopico.idUsuario())
                    .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con ID: " + datosActualizaTopico.idUsuario()));
            if (!usuario.getActivo()) {
                throw new IllegalArgumentException("El usuario especificado no está activo.");
            }
            this.usuario = usuario;
        }

        if (datosActualizaTopico.idCurso() != null) {
            Curso curso = cursoRepository.findById(datosActualizaTopico.idCurso())
                    .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado con ID: " + datosActualizaTopico.idCurso()));
            if (!curso.isActivo()) {
                throw new IllegalArgumentException("El curso especificado no está activo.");
            }
            this.curso = curso;
        }

        if (datosActualizaTopico.titulo() != null) {
            this.titulo = datosActualizaTopico.titulo();
        }
        if (datosActualizaTopico.mensaje() != null) {
            this.mensaje = datosActualizaTopico.mensaje();
        }
        if (datosActualizaTopico.fecha() != null) {
            this.fechaDeCreacion = datosActualizaTopico.fecha();
        }
        if (datosActualizaTopico.status() != null) {
            this.status = datosActualizaTopico.status();
        }
    }

    public void deshabilitarTopico() {
        this.activo=false;
    }



    public long getId_Topico() {
        return id_Topico;
    }

    public Topico() {
    }

    public void setId_Topico(long id_Topico) {
        this.id_Topico = id_Topico;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFecha_De_Creacion() {
        return fechaDeCreacion;
    }

    public void setFecha_De_Creacion(LocalDateTime fecha_De_Creacion) {
        this.fechaDeCreacion = fecha_De_Creacion;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<Respuesta> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<Respuesta> respuestas) {
        this.respuestas = respuestas;
    }

    public LocalDateTime getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(LocalDateTime fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }


}

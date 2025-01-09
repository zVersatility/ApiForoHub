package foro.hub.api.domain.Curso;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Curso")
@Table(name = "cursos")
@AllArgsConstructor
@Getter
//@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(of = "id_Curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_Curso;
    @Column(unique = true)
    private String nombre_Curso;
    @Enumerated(EnumType.STRING)
    private Categoria categoria_Curso;
    private boolean activo;



    public Curso(DatosRegistroCurso datosRegistroCurso) {
        this.nombre_Curso=datosRegistroCurso.nombreCurso();
        this.categoria_Curso=datosRegistroCurso.categoria();
        this.activo=true;
    }

    public Curso() {
        // Constructor sin parámetros
    }

    public long getId_Curso() {
        return id_Curso;
    }

    public void setId_Curso(long id_Curso) {
        this.id_Curso = id_Curso;
    }

    public String getNombre_Curso() {
        return nombre_Curso;
    }

    public void setNombre_Curso(String nombre_Curso) {
        this.nombre_Curso = nombre_Curso;
    }

    public Categoria getCategoria_Curso() {
        return categoria_Curso;
    }

    public void setCategoria_Curso(Categoria categoria_Curso) {
        this.categoria_Curso = categoria_Curso;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}

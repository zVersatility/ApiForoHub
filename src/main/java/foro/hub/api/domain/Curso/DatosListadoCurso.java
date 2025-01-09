package foro.hub.api.domain.Curso;



public record DatosListadoCurso(String nombreCurso, Categoria categoria) {

    public DatosListadoCurso(Curso curso){
        this(curso.getNombre_Curso(),curso.getCategoria_Curso());
    }
}

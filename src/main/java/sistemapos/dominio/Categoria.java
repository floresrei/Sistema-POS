package sistemapos.dominio;


public class Categoria {

    private int idCategoria;
    private String nombre;

    // Constructor vacío requerido para mapeos genéricos
    public Categoria() {}

    // Constructor lleno para instanciar rápidamente
    public Categoria(int idcategoria, String nombre) {
        this.idCategoria = idcategoria;
        this.nombre = nombre;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "idCategoria=" + idCategoria +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
package persistencia;

import dominio.Categoria;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    private final List<Categoria> categorias = new ArrayList<>();

    public boolean guardar(Categoria categoria) {
        return categorias.add(categoria);
    }

    public List<Categoria> listar() {
        return categorias;
    }

    public Categoria buscarPorId(int idCategoria) {
        for (Categoria c : categorias) {
            if (c.getIdCategoria() == idCategoria) {
                return c;
            }
        }
        return null;
    }
}
package persistencia;

import dominio.Categoria;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoriaDAOTest {

    @Test
    void guardarCategoria() {
        CategoriaDAO dao = new CategoriaDAO();

        Categoria categoria = new Categoria(1, "Electrónicos");

        boolean resultado = dao.guardar(categoria);

        assertTrue(resultado);
    }
}
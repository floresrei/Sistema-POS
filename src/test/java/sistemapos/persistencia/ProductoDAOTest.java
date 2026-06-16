package sistemapos.persistencia;

import sistemapos.dominio.Categoria;
import sistemapos.dominio.Producto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

public class ProductoDAOTest {

    @Test
    public void testInsertarYBuscarProducto() {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        ProductoDAO productoDAO = new ProductoDAO();

        try {
            // Primero creamos una categoría padre para evitar errores de llave foránea (FK)
            Categoria cat = new Categoria();
            cat.setNombre("Snacks");
            categoriaDAO.insertar(cat);

            // Creamos el producto asociado a esa categoría
            Producto prod = new Producto();
            prod.setNombre("Papas Fritas");
            prod.setPrecio(1.25);
            prod.setStock(50);
            prod.setIdCategoria(cat.getIdCategoria());

            // 1. Probar inserción
            boolean insertado = productoDAO.insertar(prod);
            assertTrue(insertado, "El producto debería haberse insertado.");
            assertTrue(prod.getId() > 0, "El ID del producto debe generarse.");

            // 2. Probar búsqueda
            Producto encontrado = productoDAO.buscarPorId(prod.getId());
            assertNotNull(encontrado, "El producto debe encontrarse en la base de datos.");
            assertEquals("Papas Fritas", encontrado.getNombre());
            assertEquals(1.25, encontrado.getPrecio());

        } catch (SQLException e) {
            fail("Error en la prueba de Producto: " + e.getMessage());
        }
    }
}

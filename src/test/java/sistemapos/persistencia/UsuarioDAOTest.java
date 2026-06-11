package persistencia;

import sistemapos.dominio.Usuario;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

public class UsuarioDAOTest {

    @Test
    public void testFlujoLoginCompleto() {
        UsuarioDAO dao = new UsuarioDAO();

        // Creamos un usuario de prueba único
        String userPrueba = "cajero_test_" + System.currentTimeMillis();
        Usuario nuevoUsuario = new Usuario(0, userPrueba, "Clave123*", "Cajero");

        try {
            // 1. Registramos el usuario en la BD
            boolean registrado = dao.insertar(nuevoUsuario);
            assertTrue(registrado, "El usuario debería registrarse sin problemas.");

            // 2. CASO EXITOSO: Probar login con credenciales correctas
            Usuario usuarioAutenticado = dao.autenticar(userPrueba, "Clave123*");
            assertNotNull(usuarioAutenticado, "El login debería ser exitoso con credenciales correctas.");
            assertEquals("Cajero", usuarioAutenticado.getRol(), "El rol recuperado debe ser 'Cajero'.");

            // 3. CASO FALLIDO: Probar login con contraseña incorrecta
            Usuario loginFallidoContrasenia = dao.autenticar(userPrueba, "PasswordErroneo");
            assertNull(loginFallidoContrasenia, "El login debería denegarse si la contraseña está mal.");

            // 4. CASO FALLIDO: Probar login con usuario inexistente
            Usuario loginFallidoUsuario = dao.autenticar("usuario_fantasma", "Clave123*");
            assertNull(loginFallidoUsuario, "El login debería denegarse si el usuario no existe.");

        } catch (SQLException e) {
            fail("Error de base de datos durante la prueba de Login: " + e.getMessage());
        }
    }
}
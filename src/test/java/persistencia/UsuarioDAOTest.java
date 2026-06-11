package persistencia;

import dominio.Usuario;
import persistencia.UsuarioDAO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class UsuarioDAOTest {

    @Test
    public void testFlujoLoginCompleto() {
        UsuarioDAO dao = new UsuarioDAO();

        // Genera un nombre aleatorio dinámico para evitar errores de clave duplicada al repetir la prueba
        String usuarioAleatorio = "user_" + System.currentTimeMillis();
        Usuario nuevo = new Usuario(0, usuarioAleatorio, "admin123", "Administrador");

        // 1. Validar el registro en base de datos
        boolean registrado = dao.registrarUsuario(nuevo);
        Assertions.assertTrue(registrado);

        // 2. Validar credenciales correctas
        Usuario correcto = dao.autenticar(usuarioAleatorio, "admin123");
        Assertions.assertNotNull(correcto);
        Assertions.assertEquals("Administrador", correcto.getRol());

        // 3. Validar control de accesos incorrectos
        Usuario incorrecto = dao.autenticar(usuarioAleatorio, "clave_falsa");
        Assertions.assertNull(incorrecto);
    }
}
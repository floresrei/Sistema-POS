package sistemapos.Presentación;

import sistemapos.Presentación.LoginForm;
import sistemapos.Presentación.MainForm;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            // Utiliza el hilo de despacho de eventos (Event Dispatch Thread - EDT) para asegurar
            // que todas las operaciones relacionadas con la interfaz gráfica de usuario (Swing)
            // se realicen de forma segura y sin bloqueos.

            MainForm mainForm = new MainForm(); // Crea una nueva instancia del formulario principal de la aplicación.
            mainForm.setVisible(false); // IMPORTANTE: El menú principal inicia oculto hasta que el login sea exitoso.

            LoginForm loginForm = new LoginForm(); // Crea una nueva instancia del formulario de inicio de sesión.
            loginForm.setVisible(true); // Hace visible la ventana de inicio de sesión para solicitar credenciales.
        });
    }
}

package sistemapos.Presentación;
import sistemapos.dominio.Producto;
import sistemapos.persistencia.ProductoDAO;

import javax.swing.*;


    public class ProductForm extends JFrame {
        private JPanel mainPanel;
        private JTextField txtNombre;
        private JTextField txtPrecio;
        private JTextField txtStock;
        private JTextField txtIdCategoria;
        private JButton btnGuardar;
        private ProductoDAO productoDAO;

        public ProductForm() {
            productoDAO = new ProductoDAO();
            setContentPane(mainPanel);
            setTitle("Gestión de Productos");
            setSize(400, 300);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);

            btnGuardar.addActionListener(e -> {
                try {
                    Producto p = new Producto(0, txtNombre.getText(), Double.parseDouble(txtPrecio.getText()), Integer.parseInt(txtStock.getText()), Integer.parseInt(txtIdCategoria.getText()));
                    if (productoDAO.insertar(p)) {
                        JOptionPane.showMessageDialog(this, "Producto registrado correctamente.");
                        txtNombre.setText(""); txtPrecio.setText(""); txtStock.setText(""); txtIdCategoria.setText("");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Revisa los datos ingresados.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
        }
    }
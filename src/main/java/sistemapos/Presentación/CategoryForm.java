package sistemapos.Presentación;

import javax.swing.*;

public class CategoryForm extends JFrame{
    private JTextField txtNombreCategoria;
    private JTextArea txtDescripcion;
    private JButton btnGuardar;
    private JPanel mainPanel;

    public CategoryForm() {
            setContentPane(mainPanel);
            setTitle("Nueva Categoría");
            setSize(380, 250);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);

            btnGuardar.addActionListener(e -> {
                if (txtNombreCategoria.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "El nombre es obligatorio.");
                    return;
                }
                JOptionPane.showMessageDialog(this, "Categoría registrada de forma temporal.");
                txtNombreCategoria.setText(""); txtDescripcion.setText("");
            });
        }
    }


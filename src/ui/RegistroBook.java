package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import BLL.BookBll;
import Model.Book;

public class RegistroBook extends JFrame {

    private JButton guardar;
    private JButton buscar;
    private JButton nuevo;
    private JButton eliminar;
    private JTextField bookIdTextField;
    private JTextField nombreTextField;
    private JTextField NombreAutorTextField;
    private JTextField EdiccionTextField;
    private JTextField precioTextField;
    private ImageIcon icono;

    public RegistroBook() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Registro Book");
        this.setLayout(null);
        this.setBounds(500, 200, 450, 450);
        this.setContentPane(panelPricipal());
        this.setVisible(true);
    }

    private JPanel panelPricipal() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel label = texto("Book ID ");
        label.setBounds(25, 10, 100, 30);
        panel.add(label);

        bookIdTextField = textField();
        bookIdTextField.setBounds(90, 10, 190, 30);
        panel.add(bookIdTextField);

        nombreTextField = textField();
        panel.add(panel("Nombre ", 25, 60, nombreTextField, new BorderLayout()));

        NombreAutorTextField = textField();
        panel.add(panel("Nombre Autor ", 25, 110, NombreAutorTextField, new BorderLayout()));

        EdiccionTextField = textField();
        panel.add(panel("Ediccion ", 25, 160, EdiccionTextField, new BorderLayout()));

        precioTextField = textField();
        panel.add(panel("Precio ", 25, 210, precioTextField, new BorderLayout()));

        panelButton();
        panel.add(guardar);
        panel.add(buscar);
        panel.add(nuevo);
        panel.add(eliminar);
        return panel;
    }

    private JPanel panel(String _texto, int x, int y, JTextField jTextField, LayoutManager mgr) {
        JPanel panel = new JPanel();
        panel.setLayout(mgr);
        panel.add(texto(_texto), BorderLayout.WEST);
        panel.add(jTextField);
        panel.setBounds(x, y, 340, 30);
        return panel;
    }

    private JLabel texto(String _texto) {
        JLabel texto = new JLabel(_texto);
        texto.setForeground(Color.BLACK);
        texto.setFont(new Font("arial", 1, 16));
        return texto;
    }

    private JTextField textField() {
        JTextField jTextField = new JTextField();
        jTextField.setForeground(Color.BLACK);
        jTextField.setFont(new Font("arial", 1, 16));
        return jTextField;
    }

    private void panelButton() {
        int posicionY = 300;
        
        buscar = new JButton("");
        icono = new ImageIcon("src/Resources/BuscarIcono.png");
        buscar.setIcon(new ImageIcon(icono.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
        buscar.setBounds(290, 10, 75, 30);
        buscar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                opcionBuscar();
            }
        });

        nuevo = new JButton("");
        icono = new ImageIcon("src/Resources/NewIcono.png");
        nuevo.setIcon(new ImageIcon(icono.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
        nuevo.setBounds(60, posicionY, 75, 30);
        nuevo.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                opcionNuevo();
            }
        });

        guardar = new JButton("");
        icono = new ImageIcon("src/Resources/GuardarIcono.png");
        guardar.setIcon(new ImageIcon(icono.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
        guardar.setBounds(170, posicionY, 75, 30);
        guardar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                opcionGuardar();
            }
        });

        

        eliminar = new JButton("");
        icono = new ImageIcon("src/Resources/EliminarIcono.png");
        eliminar.setIcon(new ImageIcon(icono.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
        eliminar.setBounds(280, posicionY, 75, 30);
        eliminar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                opcionEliminar();
            }
        });
    }

    private void opcionGuardar() {
        if (!validar()) {
            JOptionPane.showMessageDialog(this, "Hay datos con error", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Book book = new Book(
                Integer.parseInt(bookIdTextField.getText()),
                nombreTextField.getText(),
                NombreAutorTextField.getText(),
                EdiccionTextField.getText(),
                Double.parseDouble(precioTextField.getText()));
        if (BookBll.GuardarBook(book) == true) {
            JOptionPane.showMessageDialog(this, "Libro Guardado", "Información", JOptionPane.INFORMATION_MESSAGE);
            limpiarView();
        } else
            JOptionPane.showMessageDialog(this, "Libro No Guardado", "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    private void opcionBuscar() {

        if (bookIdTextField.getText().length() != 0) {
            if (!validarNumero(bookIdTextField.getText())) {
                JOptionPane.showMessageDialog(this, "El Book Id no es un numero !!!",
                        "Información", JOptionPane.INFORMATION_MESSAGE);
                limpiarView();
                return;
            }

            Book book = BookBll.Buscar(Integer.parseInt(bookIdTextField.getText()));

            if (book != null) {
                llenarView(book);
                JOptionPane.showMessageDialog(this, "Libro encotrado", "Información", JOptionPane.INFORMATION_MESSAGE);
            } else
                JOptionPane.showMessageDialog(this, "No se encotro el libro con id " + bookIdTextField.getText(),
                        "Información", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "El BookId esta vacio !!!" + bookIdTextField.getText(),
                    "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void opcionNuevo() {
        limpiarView();
    }

    private void opcionEliminar() {
        if (!validar()) {
            JOptionPane.showMessageDialog(this, "Hay datos con error", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (BookBll.Eliminar(Integer.parseInt(bookIdTextField.getText()))) {
            JOptionPane.showMessageDialog(this, "Se elimino el libro private " + nombreTextField.getText(), "Información",
                    JOptionPane.INFORMATION_MESSAGE);
            limpiarView();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo eliminar el libro " + nombreTextField.getText(),
                    "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private boolean validarNumero(String numero) {
        try {
            Double.parseDouble(numero);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private boolean validar() {

        if (bookIdTextField.getText().length() == 0)
            return false;
        if (nombreTextField.getText().length() == 0)
            return false;
        if (NombreAutorTextField.getText().length() == 0)
            return false;
        if (EdiccionTextField.getText().length() == 0)
            return false;
        if (precioTextField.getText().length() == 0)
            return false;
        if (!validarNumero(bookIdTextField.getText())) {
            JOptionPane.showMessageDialog(this, "Book Id no es un numero", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!validarNumero(precioTextField.getText())) {
            JOptionPane.showMessageDialog(this, "Precio no es numero", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void llenarView(Book book) {
        nombreTextField.setText(book.getNombre());
        NombreAutorTextField.setText(book.getNombreAutor());
        EdiccionTextField.setText(book.getEdicion());
        precioTextField.setText(String.valueOf(book.getPrecio()));
    }

    private void limpiarView() {
        bookIdTextField.setText("");
        nombreTextField.setText("");
        NombreAutorTextField.setText("");
        EdiccionTextField.setText("");
        precioTextField.setText("");
    }
}
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

    public RegistroBook() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Registro Book");
        this.setLayout(null);
        this.setBounds(300, 300, 600, 500);
        this.setContentPane(panelPricipal());
        this.setVisible(true);
    }

    JPanel panelPricipal() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        bookIdTextField = textField();
        panel.add(panel("Book ID ", 25, 10, bookIdTextField));
        nombreTextField = textField();
        panel.add(panel("Nombre ", 25, 60, nombreTextField));
        NombreAutorTextField = textField();
        panel.add(panel("Nombre Autor ", 25, 110, NombreAutorTextField));
        EdiccionTextField = textField();
        panel.add(panel("Ediccion ", 25, 160, EdiccionTextField));
        precioTextField = textField();
        panel.add(panel("Precio ", 25, 210, precioTextField));
        panelButton();
        panel.add(guardar);
        panel.add(buscar);
        panel.add(nuevo);
        panel.add(eliminar);
        return panel;
    }

    JPanel panel(String _texto, int x, int y, JTextField jTextField) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(texto(_texto), BorderLayout.WEST);
        panel.add(jTextField);
        panel.setBounds(x, y, 310, 30);
        return panel;
    }

    JLabel texto(String _texto) {
        JLabel texto = new JLabel(_texto);
        texto.setForeground(Color.BLACK);
        texto.setFont(new Font("arial", 1, 18));
        return texto;
    }

    JTextField textField() {
        JTextField jTextField = new JTextField();
        jTextField.setForeground(Color.BLACK);
        jTextField.setFont(new Font("arial", 1, 20));
        return jTextField;
    }

    void panelButton() {
        int posicionY = 300;
        guardar = new JButton("Guardar");
        guardar.setBounds(150, posicionY, 100, 30);
        guardar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                opcionGuardar();
            }
        });
        guardar.setVisible(true);

        buscar = new JButton("Buscar");
        buscar.setBounds(335, 10, 100, 30);
        buscar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                opcionBuscar();
            }
        });
        buscar.setVisible(true);

        nuevo = new JButton("Nuevo");
        nuevo.setBounds(50, posicionY, 100, 30);
        nuevo.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                opcionNuevo();
            }
        });
        nuevo.setVisible(true);

        eliminar = new JButton("Eliminar");
        eliminar.setBounds(250, posicionY, 100, 30);
        eliminar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                opcionEliminar();
            }
        });
        eliminar.setVisible(true);
    }

    void opcionGuardar() {

        Book book = new Book(
                Integer.parseInt(bookIdTextField.getText()),
                nombreTextField.getText(),
                NombreAutorTextField.getText(),
                EdiccionTextField.getText(),
                Double.parseDouble(precioTextField.getText()));
        if (BookBll.GuardarBook(book) == true) {
            JOptionPane.showMessageDialog(this, "Libro Guardado", "Info", JOptionPane.INFORMATION_MESSAGE);
            limpiarView();
        } else
            JOptionPane.showMessageDialog(this, "Libro No Guardado", "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    void opcionBuscar() {
        llenarView(BookBll.Buscar(Integer.parseInt(bookIdTextField.getText())));
        JOptionPane.showMessageDialog(this, "yo Busco", "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    void opcionNuevo() {
        limpiarView();
    }

    void opcionEliminar() {
        JOptionPane.showMessageDialog(this, "yo Elimino", "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    void llenarView(Book book) {
        nombreTextField.setText(book.getNombre());
        NombreAutorTextField.setText(book.getNombreAutor());
        EdiccionTextField.setText(book.getEdicion());
        precioTextField.setText(String.valueOf(book.getPrecio()));
    }

    void limpiarView() {
        bookIdTextField.setText("");
        nombreTextField.setText("");
        NombreAutorTextField.setText("");
        EdiccionTextField.setText("");
        precioTextField.setText("");
    }
}
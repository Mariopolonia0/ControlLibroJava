package ui;


import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import Model.Book;

public class ListaBooks extends JFrame {
    private JButton agregar;
    private JButton eliminar;

    JScrollPane scrollPane = new JScrollPane();
    JList<Book> lista;

    public ListaBooks() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Lista De Book");
        this.setLayout(null);
        this.setBounds(600, 200, 480, 500);
        this.setContentPane(PanelPrincipal());
        this.setVisible(true);
    }

    private JPanel PanelPrincipal() {
        JPanel panel = new JPanel();
        ConfigurarButton();
        llenarLista();
        panel.setLayout(null);
        panel.add(scrollPane);
        panel.add(agregar);
        panel.add(eliminar);
        return panel;
    }

    private void llenarLista() {

        DefaultListModel model = new DefaultListModel<Book>();

        for (int contador = 1; contador <= 50; contador++) {
            Book book = new Book();
            book.setBookId(contador);
            book.setNombre("La semana laborar de 4 hora");
            book.setNombreAutor("Tim Ferriss");
            model.addElement(book);
        }

        lista = new JList(model);
        scrollPane.setBounds(0, 0, 330, 450);
        scrollPane.setBorder(new LineBorder(Color.CYAN, 5, true));
        lista.setCellRenderer(new BookRenderer());

        scrollPane.setViewportView(lista);

    }

    private void ConfigurarButton() {
        agregar = new JButton("");
        ImageIcon icono = new ImageIcon("src/Resources/AgregarIcono.png");
        agregar.setIcon(new ImageIcon(icono.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
        agregar.setBounds(350, 10, 75, 30);
        agregar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                OpcionAgregar();
            }
        });

        eliminar = new JButton("");
        icono = new ImageIcon("src/Resources/EliminarIcono.png");
        eliminar.setIcon(new ImageIcon(icono.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
        eliminar.setBounds(350, 50, 75, 30);
        eliminar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                OpcionEliminar();
            }
        });
    }

    private void OpcionAgregar() {
        new RegistroBook();
    }

    private void OpcionEliminar() {
        System.out.println(lista.getSelectedValue().getBookId());
    }
}
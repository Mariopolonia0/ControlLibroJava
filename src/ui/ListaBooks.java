package ui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.*;
import java.awt.*;

public class ListaBooks extends JFrame {
    private JButton agregar;
    
    public ListaBooks() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Registro Book");
        this.setLayout(null);
        this.setBounds(500, 200, 450, 450);
        this.setContentPane(PanelPrincipal());
        this.setVisible(true);
    }

    private JPanel PanelPrincipal() {
        JPanel panel = new JPanel();
        ConfigurarButton();
        panel.setLayout(null);
        panel.add(agregar);
        return panel;
    }

    private void ConfigurarButton() {
        agregar = new JButton("");
        ImageIcon icono = new ImageIcon("src/Resources/AgregarIcono.png");
        agregar.setIcon(new ImageIcon(icono.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
        agregar.setBounds(290, 10, 75, 30);
        agregar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                OpcionAgregar();
            }
        });
    }

    private void OpcionAgregar() {
        new RegistroBook();
    }
}

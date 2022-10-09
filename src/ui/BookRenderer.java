package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Model.Book;

public class BookRenderer extends JPanel implements ListCellRenderer<Book> {
 
    private JLabel lbName = new JLabel();
    private JLabel lbAuthor = new JLabel();
    JPanel panelText = new JPanel(new GridLayout(0, 1));
 
    public BookRenderer() {
        setLayout(new BorderLayout(0,0));
        
        panelText.add(lbName);
        panelText.add(lbAuthor);
        //panelText.setBorder(new LineBorder(Color.CYAN, 5, true));
        this.setBorder(new EmptyBorder(5, 5, 5, 5));
        add(panelText, BorderLayout.CENTER);
    }
 
    @Override
    public Component getListCellRendererComponent(JList<? extends Book> list,
            Book book, int index, boolean isSelected, boolean cellHasFocus) {
 
        lbName.setText(book.getNombre());
        lbName.setBorder(new EmptyBorder(5, 5, 0, 0));
        lbAuthor.setBorder(new EmptyBorder(5, 5, 5, 0));
        lbAuthor.setText(book.getNombreAutor());

        if(cellHasFocus){
            panelText.setBackground(Color.GREEN);
        }else{
            panelText.setBackground(Color.CYAN);
        }
        return this;
    }
}

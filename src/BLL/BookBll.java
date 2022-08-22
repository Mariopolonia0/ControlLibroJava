package BLL;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Model.Book;
import database.Contexto;

public class BookBll {
    static public void GuardarBook(Book book) {
        Contexto contexto = new Contexto();
        try {
            contexto.connect();
            PreparedStatement sql = contexto.connect.prepareStatement(
                    "insert into Books (BookId,Nombre,NombreAutor,Edicion,Precio) VALUES(?,?,?,?,?)");
            sql.setInt(1, book.getBookId());
            sql.setString(2, book.getNombre());
            sql.setString(3, book.getNombreAutor());
            sql.setString(4, book.getEdicion());
            sql.setDouble(5, book.getPrecio());
            sql.execute();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}

package BLL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.Book;
import database.Contexto;

public class BookBll {

    static public boolean GuardarBook(Book book) {
        Contexto contexto = new Contexto();
        Boolean guardo = false;
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
            contexto.close();
            guardo = true;
        } catch (SQLException ex) {
            guardo = ActualizarBook(book);
            System.err.println(ex.getMessage());
        }
        return guardo;
    }

    // UPDATE Books
    // set Nombre = "Historia de Ramon",NombreAutor = "Ramon",Edicion =
    // "Tercera",Precio=60.0
    // WHERE BookId = 1
    static public boolean ActualizarBook(Book book) {
        Contexto contexto = new Contexto();
        Boolean guardo = false;
        try {
            contexto.connect();
            String consulta = "UPDATE Books SET Nombre=?, NombreAutor=?, Edicion=?, Precio=?"
                    + "WHERE BookId=?";

            PreparedStatement sql = contexto.connect.prepareStatement(consulta);
            sql.setString(1, book.getNombre());
            sql.setString(2, book.getNombreAutor());
            sql.setString(3, book.getEdicion());
            sql.setDouble(4, book.getPrecio());
            sql.setInt(5, book.getBookId());
            sql.execute();
            contexto.close();
            guardo = true;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return guardo;
    }

    static public Book Buscar(int bookId) {
        Contexto contexto = new Contexto();

        Book book = new Book();
        try {
            contexto.connect();

            String consulta = "SELECT * FROM Books WHERE BookId = " + String.valueOf(bookId);

            ResultSet resultSet = contexto.connect.createStatement().executeQuery(consulta);
            book.setNombre(resultSet.getString("Nombre"));
            book.setNombreAutor(resultSet.getString("NombreAutor"));
            book.setEdicion(resultSet.getString("Edicion"));
            book.setPrecio(Double.parseDouble(resultSet.getString("Precio")));
            contexto.close();
        } catch (SQLException ex) {
            book = null;
            System.err.println(ex.getMessage());
        }
        return book;
    }

    static public boolean Eliminar(int bookId) {
        Contexto contexto = new Contexto();
        String consulta = "DELETE FROM Books WHERE BookId = ?";
        boolean elimino = false;

        try {
            contexto.connect();
            PreparedStatement sql = contexto.connect.prepareStatement(consulta);

            // set the corresponding param
            sql.setInt(1, bookId);
            // execute the delete statement
            sql.executeUpdate();
            contexto.close();
            elimino = true;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return elimino;
    }
}

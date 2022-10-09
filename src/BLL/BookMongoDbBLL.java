package BLL;

import org.bson.Document;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import Model.Book;
import database.ContextoMongoDb;

public class BookMongoDbBLL {

    private static boolean Existe(String bookID) {
        ContextoMongoDb contextoMongoDb = new ContextoMongoDb();
        boolean encotrado = true;

        try {

            MongoCollection<Document> collection = contextoMongoDb.getMongodb().getCollection("books");
            //collection.find(new BasicDBObject("bookId", bookID));
            
        } catch (Exception e) {
            encotrado = false;
            System.out.println("ERROR en Existe() : " + e.toString());
        }
        return encotrado;
    }

    private static boolean Insertar(Book book) {
        ContextoMongoDb contextoMongoDb = new ContextoMongoDb();
        boolean guardado = true;

        MongoCollection<Document> collection = contextoMongoDb.getMongodb().getCollection("books");
        String id = String.valueOf(book.getBookId());
        Document document = new Document(new BasicDBObject("bookId", id));

        try {
            document.append("Nombre", book.getNombre());
            document.append("NombreAutor", book.getNombreAutor());
            document.append("Edicion", book.getEdicion());
            document.append("Precio", book.getPrecio());

            collection.insertOne(document);
        } catch (Exception e) {
            guardado = false;
            System.out.println("ERROR en Insertar(Book book)  : " + e.toString());
        }

        return guardado;
    }

    private static boolean Modificar(Book book) {
        ContextoMongoDb contextoMongoDb = new ContextoMongoDb();
        boolean guardado = true;

        MongoCollection<Document> collection = contextoMongoDb.getMongodb().getCollection("books");
        String id = String.valueOf(book.getBookId());
        Document document = new Document(new BasicDBObject("bookId", id));

        try {
            document.append("Nombre", book.getNombre());
            document.append("NombreAutor", book.getNombreAutor());
            document.append("Edicion", book.getEdicion());
            document.append("Precio", book.getPrecio());

            collection.updateOne(new BasicDBObject("bookId", id), document);
        } catch (Exception e) {
            guardado = false;
            System.out.println("ERROR en Modificar(Book book)  : " + e.toString());
        }
        return guardado;
    }

    public static boolean Guardar(Book book) {
        boolean guardado = true;

        String id = String.valueOf(book.getBookId());

        Insertar(book);

        // if (Existe(id)) {
        //     guardado = Modificar(book);
        // } else {
        //     guardado = Insertar(book);
        // }
        return guardado;
    }
}

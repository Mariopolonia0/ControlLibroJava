package Model;
public class Book {
    private int BookId;
    private String Nombre;
    private String NombreAutor;
    private String Edicion;
    private double Precio;

    public Book(int bookId, String nombre, String nombreAutor, String edicion, double precio) {
        this.BookId = bookId;
        this.Nombre = nombre;
        this.NombreAutor = nombreAutor;
        this.Edicion = edicion;
        this.Precio = precio;
    }

    public Book() {
        this.BookId = 0;
        this.Nombre = "";
        this.NombreAutor = "";
        this.Edicion = "";
        this.Precio = 0;
    }

    public int getBookId() {
        return BookId;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getNombreAutor() {
        return NombreAutor;
    }

    public String getEdicion() {
        return Edicion;
    }

    public double getPrecio() {
        return Precio;
    }

    public void setBookId(int bookId) {
        BookId = bookId;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public void setNombreAutor(String nombreAutor) {
        NombreAutor = nombreAutor;
    }

    public void setEdicion(String edicion) {
        Edicion = edicion;
    }

    public void setPrecio(double precio) {
        Precio = precio;
    }

}

package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Contexto {
    String url = "src\\database\\Books.db";
    public Connection connect;

    public void connect(){
        try {
            connect = DriverManager.getConnection("jdbc:sqlite:"+url);
            if (connect!=null) {
                System.out.println("Conectado");
            }
        }catch (SQLException ex) {
            System.err.println("No se ha podido conectar a la base de datos\n"+ex.getMessage());
        }
       }
        public void close(){
               try {
                   connect.close();
               } catch (SQLException ex) {
                System.err.println("No se ha podido Cerrar la conexion a la base de datos\n"+ex.getMessage());
               }
        }
}

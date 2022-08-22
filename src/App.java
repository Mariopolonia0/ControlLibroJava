import database.Contexto;
import ui.RegistroBook;

public class App {
    public static void main(String[] args) throws Exception {
        RegistroBook registroBook = new RegistroBook();
        
        
        Contexto contexto = new Contexto();
        contexto.connect();
    }
}

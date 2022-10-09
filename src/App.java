import org.mongodb.scala.MongoClient;

import ui.RegistroBook;

public class App {
    public static void main(String[] args) throws Exception {
        new RegistroBook();
        MongoClient mongo = null;
    }
}

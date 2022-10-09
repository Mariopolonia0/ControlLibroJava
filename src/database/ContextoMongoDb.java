package database;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

//para que la conexion no de error hay que instalar mongoDB
public class ContextoMongoDb {

    private MongoClient mongoClient;
    private MongoDatabase mongodb;

    public ContextoMongoDb() {
        setMongoClient(new MongoClient());
        setMongodb(getMongoClient().getDatabase("Books"));
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    private void setMongoClient(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    private void setMongodb(MongoDatabase mongodb) {
        //mongodb.createCollection("books");
        this.mongodb = mongodb;
    }

    public MongoDatabase getMongodb() {
        return mongodb;
    }

}
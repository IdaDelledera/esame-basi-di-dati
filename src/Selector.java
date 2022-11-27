import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Selector {

    public static DBCollection collection;

    public static void main(String[] args) {

        Interface face = new Interface();
        face.GUI();

        Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
        mongoLogger.setLevel(Level.SEVERE);

        try{
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            DB db = mongoClient.getDB("test");
            collection = db.getCollection("myCollection");
            System.out.println("Connected to database");
        }catch(Exception ex){
            System.out.println(ex);
        }
        System.out.println("Server is ready");
    }

}

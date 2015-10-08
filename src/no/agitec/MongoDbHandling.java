package no.agitec;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import static java.util.Arrays.asList;

import org.bson.Document;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Sorts.ascending;
import static java.util.Arrays.asList;

/**
 * Created by cke on 07/10/15.
 */
public class MongoDbHandling {

    public void insert(Collection<Person> persons) {
        MongoClient mongoClient = new MongoClient();
        MongoDatabase db = mongoClient.getDatabase("personas");

        for (Person person : persons) {
            db.getCollection("person").insertOne(
                    new Document().
                            append("id", person.getId()).
                            append("firstname", person.getFirstname()).
                            append("lastname", person.getLastname())
            );
        }
        mongoClient.close();
    }

    public Collection<Person> getPersons() {
        MongoClient mongoClient = new MongoClient();
        MongoDatabase db = mongoClient.getDatabase("personas");

        FindIterable<Document> iterable = db.getCollection("person").find();
        final Collection<Person> persons = new ArrayList<>();

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                Person p = new Person();
                p.setId(document.getInteger("id"));
                p.setFirstname(document.getString("firstname"));
                p.setLastname(document.getString("lastname"));
                persons.add(p);
            }
        });
        return persons;
    }
}

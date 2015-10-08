package no.agitec;

import java.util.Collection;

public class Main {

    public static void main(String[] args) {
        try {
            MySqlHandling mySqlHandling = new MySqlHandling();
            Collection<Person> mySqlPersons = mySqlHandling.getPersons();
            System.out.println("Persons found in MySQL (" + mySqlPersons.size() + ")");
            for (Person person : mySqlPersons) {
                System.out.println(person);
            }
            System.out.println("-----");

            MongoDbHandling mongoDbHandling = new MongoDbHandling();
            mongoDbHandling.insert(mySqlPersons);

            Collection<Person> mongoPersons = mongoDbHandling.getPersons();
            System.out.println("Persons found in MongoDB (" + mongoPersons.size() + ")");
            for (Person person : mongoPersons) {
                System.out.println(person);
            }
            System.out.println("-----");

        } catch (Exception ex) {
            System.out.println("Shit: " + ex);
        }
    }
}

package no.agitec;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by cke on 07/10/15.
 */
public class MySqlHandling {
    public Collection<Person> getPersons() {
        Statement st = null;
        ResultSet rs = null;
        Collection<Person> persons = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/personas?" + "user=java&password=java");
            st = conn.createStatement();
            rs = st.executeQuery("Select * from person");
            persons = getPersons(rs);
        } catch (Exception ex) {
            System.out.println("Shit: " + ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception ignored) {
                }
            }

            if (st != null) {
                try {
                    st.close();
                } catch (Exception ignored) {
                }
            }
        }
        return persons;
    }

    private Collection<Person> getPersons(ResultSet resultSet) throws SQLException {
        Collection<Person> persons = new ArrayList<>();
        while (resultSet.next()) {
            Person p = new Person();
            p.setId(resultSet.getInt("id"));
            p.setFirstname(resultSet.getString("firstname"));
            p.setLastname(resultSet.getString("lastname"));
            persons.add(p);
        }
        return persons;
    }
}

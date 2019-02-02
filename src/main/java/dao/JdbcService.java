package dao;

import model.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class JdbcService {

    private JdbcAdapter jc;

    public JdbcService() {
        this.jc = JdbcAdapter.getIdbc();
    }

    public Person[] selectById(int id) {
        return null;

    }

    public Person[] selectAll() {
        ResultSet rs = jc.executeQuery("SELECT * FROM people");
        ArrayList<Person> persons = new ArrayList<>();

        if (rs != null) {
            try {
                while (rs.next()) {
                    persons.add(new Person(Integer.parseInt(rs.getString("id")), rs.getString("firstname"), rs.getString("lastname")));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return persons.toArray(new Person[persons.size()]);
    }
}
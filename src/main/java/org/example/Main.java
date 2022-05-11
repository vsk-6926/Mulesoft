package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Connection connection = null;
        try
        {
            connection = DriverManager.getConnection("jdbc:sqlite:movie1.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            statement.executeUpdate("drop table if exists Movies");
            statement.executeUpdate("create table Movies (id integer, name string, actor string,actress string,director string,year_of_release integer)");
            statement.executeUpdate("insert into Movies values(1, 'Bahubali' , 'Paresh Rawal' , 'Katrina Kaif','Rahul',2010)");
            statement.executeUpdate("insert into Movies values(2, 'Vikings','Ragnar Lothbrok','Lagertha','Ankit',1990)");
            statement.executeUpdate("insert into Movies values(3,'Hangover','Alan','Anita','Rahul1',2001)");
            statement.executeUpdate("insert into Movies values(4,'Heropanti','Tiger','Kriti','Karan',2012)");
            ResultSet rs = statement.executeQuery("select * from Movies");
            ResultSet ra = statement.executeQuery("select name,actor,actress,director from Movies where actor='Alan'");
            while(rs.next())
            {
                System.out.println("id = " + rs.getInt("id"));
                System.out.println("name = " + rs.getString("name"));
                System.out.println("actor ="+ rs.getString("actor"));
            }
            while(ra.next()){
                System.out.println("name = " + ra.getString("name"));
                System.out.println("actor = "+ra.getString("actor"));
                System.out.println("actress = "+ra.getString("actress"));
                System.out.println("director ="+ra.getString("director"));
            }
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }
        finally
        {
            try
            {
                if(connection != null)
                    connection.close();
            }
            catch(SQLException e)
            {
                System.err.println(e);
            }
        }
    }
}
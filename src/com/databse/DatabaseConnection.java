package com.databse;

import com.mysql.cj.protocol.Resultset;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.time.LocalDate;

public class DatabaseConnection {
    Connection connection;
    LocalDate date;
    public DatabaseConnection(){
        try{
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/self_journal",
                    "root",
                    "1234");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void InsertIntoDatabase (String newThought){
        try {
            date = LocalDate.now();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO thoughts (Thoughts, InsertDate) VALUES (?, ?)");
            statement.setString(1, newThought);
            statement.setDate(2, Date.valueOf(date));

            int resulst = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void ReadLastThought (){
        try{
            Statement statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery("SELECT * FROM thoughts");

            while(resultset.next()){
                if(resultset.isLast()){
                    System.out.println(resultset.getString("Thoughts"));
                    System.out.println(resultset.getString("InsertDate"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void ReadAllThoughts(){
        try{
            Statement statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery("SELECT * FROM thoughts");

            while(resultset.next()){
                System.out.println(resultset.getString("Thoughts"));
                System.out.println(resultset.getString("InsertDate"));
                if(!resultset.isLast()){
                    System.out.println("\n -------------------------------------------------------------------------- \n");
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

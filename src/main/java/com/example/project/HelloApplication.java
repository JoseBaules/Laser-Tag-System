/*
    This is the main class that starts the whole game.
    Hello application calls other classes for game to run.
    when you run the game you will need to run the hello application for the whole game to run
 */

// Importing packages
package com.example.project;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;

public class HelloApplication extends Application
{
    private static final int COUNT_LIMIT = 10;

    @Override
    public void start(Stage stage) throws Exception
    {

        Parent root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    // Calling the preloader class for the preloader bar
    @Override
    public void init() throws Exception
    {
        for (int i = 0; i < COUNT_LIMIT; i++)
        {
            double progress = (double) (i) / COUNT_LIMIT;
            notifyPreloader(new Preloader.ProgressNotification(progress));
            Thread.sleep(300);
        }

    }

    // the main method calling the preloader
    public static void main(String[] args) throws SQLException { PersistenceHandler persistenceHandler = PersistenceHandler.getInstance();
        try {
            if (persistenceHandler.deletingAllRows_Green()) {
                System.out.println("Green Player deleted from database!!");
            } else {
                System.out.println("Error while deleting player into database!!");
            }

            if (persistenceHandler.deletingAllRows_Red()) {
                System.out.println("Red Player deleted into database!!");
            } else {
                System.out.println("Error while deleting player into database!!");
            }

            //PersistenceHandler.connection.close();
            System.setProperty("javafx.preloader", HelloPreloader.class.getCanonicalName());
            launch(args);

            System.out.println("From HelloApplication Class");
            PersistenceHandler.connection.close();
        }finally {
            PersistenceHandler.connection.close();
        }

    }
}
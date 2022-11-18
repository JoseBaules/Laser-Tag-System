/*
 * Overview: Implementation for GameActionController class. This class controls
 *           the game action scene presented in the Laser Tag game. This scene shows a Red, Green, and log  tables.
 *           Additionally, it shows a session timer in the lower-left corner
 *
 * Purpose: To display all players from both teams. To keep track of the game's time constrains. The maximum time
 *          for a game sessions is 6:00 minutes.
 * @version 1.0
 * @since 11/04/22
 * */

package com.example.project;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

public class GameActionController implements Initializable {

    static int i;
    static Timer t;
    String nm;

    @FXML
    private Label label;
    public static Label static_label;

    PlayerPersistenceInterface persistenceHandler = PersistenceHandler.getInstance();
    Scene2Controller scene2Controller = new Scene2Controller();
    TrafficGenerator trafficGenerator = new TrafficGenerator();
    private Stage stage;

    private Scene scene;

    private Parent root;

    ObservableList<Player> redPlayerData;

    ObservableList<Player> greenPlayerData;

    @FXML
    public TableView RedTeamTable;
    @FXML
    private TableColumn<Player, Integer> redIdColumn;
    @FXML
    private TableColumn<Player, String>  redCodeNameColumn;

    @FXML
    public TableView GreenTeamTable;
    @FXML
    private TableColumn<Player, Integer> greenIdColumn;
    @FXML
    private TableColumn<Player, String>  greenCodeNameColumn;


    @FXML
    public TableView game_action;
    @FXML
    private TableColumn<Player, String> actionColumn;

    @FXML
    void addContinue(ActionEvent event) throws IOException {

        System.setProperty("javafx.preloader", HelloPreloader.class.getCanonicalName());

        Parent root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        scene = new Scene(root);

        stage.setScene(scene);

        stage.show();

        updateUI();
    }

    private void updateUI(){

    }
    public static void startTimer() {

        final int MIN = 6;
        final int SEC = 60;
        final int[] num = {MIN};
        Timer timer = new Timer();
        final int[] count = {SEC};
        timer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                Platform.runLater
                        (new Runnable()
                         {
                             public void run()
                             {
                                 String c = String.format(num[0] +":"+"%02d",count[0]);
                                 static_label.setText(c);
                                 count[0]--;

                                 if (count[0] == 0 && num[0] !=0)
                                 {
                                     num[0]--;
                                     count[0] = SEC;
                                 }
                                 if (count[0] < 0 && num[0] == 0)
                                 {
                                     System.out.println("GAME FINISH");
                                     timer.cancel();
                                 }
                                 if (count[0] == 29 && num[0] == 0)
                                 {
                                     //ALERT: Time's running out
                                     static_label.setTextFill(Color.RED);
                                 }




                             }
                         }
                        );
            }
        }, 1000, 1000); //Every 1 second

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {


        static_label = label;

        static_label.setText("");

        startTimer();

        try {
            searchAllPlayers();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        redIdColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        redCodeNameColumn.setCellValueFactory(cellData -> cellData.getValue().codenameProperty());

        greenIdColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        greenCodeNameColumn.setCellValueFactory(cellData -> cellData.getValue().codenameProperty());

        //Map<String, Object> redIdMap = new HashMap<>();
        //You can use for loop instead to make a map of String, Integer.
        //IntStream.rangeClosed(0, 14).forEach(i -> redIdMap.put(i +"", redPlayerData.get(i).getId()));//Map of 10 numbers.


        try {
            System.out.println("Calling methods from trafficGenerator - " + redPlayerData.get(0).getId());
            List<String> IdAsString = new ArrayList<>();
            for(int i = 0; i < redPlayerData.size(); i++) {
                IdAsString.add(String.valueOf(redPlayerData.get(i).getId()));
            }
            List<String> keys = trafficGenerator.getRandomKeys(IdAsString);
            String allKeys = trafficGenerator.combineKeys(keys, ":");
            System.out.println("Please return allKeys from traffic generator");
            System.out.println(allKeys);

            System.out.println("GameActionController: Inside try block");

            new EchoServer().start();
            System.out.println("Server Started");

            EchoClient client = new EchoClient();
            System.out.println("Client Created ");

           // String echo = client.sendEcho("hello server");
            //Sending message to server for counter 10
            String echo;
            for(int counter=0; counter<10; counter++) {
                echo = client.sendEcho(allKeys);
                System.out.println("hello server: " + echo);
            }

            //echo = client.sendEcho("2:3");

            //System.out.println(echo.equals("hello server"));
            //System.out.println(echo.equals("1:2"));
           // actionColumn.setCellValueFactory(cellData -> finalEcho);
            //client.sendEcho("end");
            client.close();

        } catch (Exception exception) {
            System.out.println("[-] UDPBaseServer has encountered an exception:");
            exception.printStackTrace();
        }

    }


    //Search all players
    @FXML
    private void searchAllPlayers() throws SQLException, ClassNotFoundException {
        try {
            //Get all red team Players information
            redPlayerData = PersistenceHandler.searchRedPlayers();
            //Populate red team Players on TableView
            populateRedPlayers(redPlayerData);

            //Get all green team Players information
            greenPlayerData = PersistenceHandler.searchGreenPlayers();
            //Populate green team Players on TableView
            populateGreenPlayers(greenPlayerData);

        } catch (SQLException e){
            System.out.println("Error occurred while getting players information from DB.\n" + e);
            throw e;
        }
    }
    //Populate Player
    @FXML
    private void populatePlayer (Player player) throws ClassNotFoundException {
        //Declare and ObservableList for table view
        ObservableList<Player> redTeamData = FXCollections.observableArrayList();
        //Add player to the ObservableList
        redTeamData.add(player);
        //Set items to the Table
        RedTeamTable.setItems(redTeamData);
    }

    //Populate Red team Players for TableView
    @FXML
    private void populateRedPlayers (ObservableList<Player> redTeamData) throws ClassNotFoundException {
        //Set items to the red_team Table
        RedTeamTable.setItems(redTeamData);
    }

    //Populate Green team Players for TableView
    @FXML
    private void populateGreenPlayers (ObservableList<Player> greenTeamData) throws ClassNotFoundException {
        //Set items to the green_team Table
        GreenTeamTable.setItems(greenTeamData);
    }

    public GameActionController() {
    }

    @FXML

    public void EditGame (ActionEvent event) throws IOException
    {

    }


    //TODO: to be implemented in next Sprint
    public void StartGame (ActionEvent event) throws IOException
    {
//        System.setProperty("javafx.preloader", HelloPreloader.class.getCanonicalName());
//
//        Parent root = FXMLLoader.load(getClass().getResource("GameAction.fxml"));
//
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//
//        scene = new Scene(root);
//
//        stage.setScene(scene);
//
//        stage.show();

    }
    public void ViewGame (ActionEvent event) throws IOException
    {

    }
    public void PreEnteredGames (ActionEvent event) throws IOException
    {

    }
    public void FlickSync (ActionEvent event) throws IOException
    {

    }

    public void ClearGame (ActionEvent event) throws IOException
    {

    }

    public class Controller
    {
        public void move_up ()
        {

        }
        public void move_down()
        {

        }
        public void move_left ()
        {

        }
        public void move_right()
        {

        }
    }
    public void closeGame(ActionEvent event) throws IOException
    {
        System.exit(0);

    }

}
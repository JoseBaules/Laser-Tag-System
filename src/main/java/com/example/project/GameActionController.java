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
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

public class GameActionController implements Initializable {

    static int i;
    static Timer t;
    static int dummyTimer = 5;
    static ArrayList<String> actions = new ArrayList<String>();
    @FXML
    private Label action1;
    public static Label static_action1;
    @FXML
    private Label action4;
    public static Label static_action4;
    @FXML
    private Label action2;
    public static Label static_action2;
    @FXML
    private Label action3;
    public static Label static_action3;
    @FXML
    private Label action5;
    public static Label static_action5;
    @FXML
    private Label action6;
    public static Label static_action6;
    @FXML
    private Label action7;
    public static Label static_action7;

    @FXML
    private Label action8;
    public static Label static_action8;

    @FXML
    private Label action9;
    public static Label static_action9;

    @FXML
    private Label action10;
    public static Label static_action10;

    @FXML
    private Label redScore;

    public static Label static_redScore;

    @FXML
    private Label greenScore;

    public static Label static_greenScore;
    @FXML
    private TableColumn<Player,Integer> redPoints;
    @FXML
    private TableColumn<Player, Integer> greenPoints;

    /*@note: the individual player scores are stored in these hash maps in the format -> playerID:score
     *       these are static and modified by the server
     *       total team score can be computed by adding all values
     *
     * */
    static HashMap<Integer, Integer> GreenPlayerScores = new HashMap<>();
    static HashMap<Integer, Integer> RedPlayerScores = new HashMap<>();


    static boolean ShooterIsGreen = false;
    static boolean ShooterIsRed = false;

    static String greenPlayerNameScore = "";
    static String redPlayerNameScore = "";

    static String greenTotalScore ="";
    static String redTotalScore ="";

    @FXML
    private Label label;
    public static Label static_label;

    PlayerPersistenceInterface persistenceHandler = PersistenceHandler.getInstance();

    TrafficGenerator trafficGenerator = new TrafficGenerator();

    // create a alert
    Alert alert = new Alert(Alert.AlertType.NONE);

    private Stage stage;

    private Scene scene;

    private Parent root;

    static ObservableList<Player> redPlayerData;

    static ObservableList<Player> greenPlayerData;

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
                                     Alert a = new Alert(Alert.AlertType.NONE);
                                     a.setAlertType(Alert.AlertType.WARNING);
                                     // set content text
                                     a.setContentText("30 Seconds Gaming Time Remaining\n");
                                     // show the dialog
                                     a.show();
                                     static_label.setTextFill(Color.RED);
                                 }


                             }
                         }
                        );
            }
        }, 1000, 1000); //Every 1 second

    }

    public static void displayActions() {
        Label labels[];

        labels = new Label[]
                {
                        static_action1,static_action2,static_action3,static_action4,static_action5,static_action6,static_action7,static_action8,static_action9,static_action10
                };

        Timer timer = new Timer();
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

                                 if (i>(actions.size()-1))
                                 {
                                     timer.cancel();
                                 }
                                 else
                                 {
                                     labels[i].setText(actions.get(i));
                                     i++;
                                 }




                             }
                         }
                        );
            }
        }, 1000, 3000); //Every 1 second

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

        static_label = label;
        static_label.setText(" ");
        static_redScore = redScore;
        static_redScore.setText("0000");

        static_action1 = action1;
        static_action1.setText("");

        static_action2 = action2;
        static_action2.setText(" ");

        static_action3 = action3;
        static_action3.setText(" ");

        static_action4 = action4;
        static_action4.setText(" ");

        static_action5 = action5;
        static_action5.setText(" ");

        static_action6 = action6;
        static_action6.setText(" ");

        static_action7 = action7;
        static_action7.setText(" ");

        static_action8 = action8;
        static_action8.setText(" ");

        static_action9 = action9;
        static_action9.setText(" ");

        static_action10 = action10;
        static_action10.setText(" ");

        static_greenScore = greenScore;
        static_greenScore.setText("0000");


//        startTimer();


        try {
            searchAllPlayers();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        redIdColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        redCodeNameColumn.setCellValueFactory(cellData -> cellData.getValue().codenameProperty());
        redPoints.setCellValueFactory(new PropertyValueFactory<Player,Integer>("score"));





        greenIdColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        greenCodeNameColumn.setCellValueFactory(cellData -> cellData.getValue().codenameProperty());
        greenPoints.setCellValueFactory(new PropertyValueFactory<Player,Integer>("score"));




        try {

            ViewGameAction ();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Printing the values

        //Closing DB connection
        try {
            PersistenceHandler.connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        startTimer();
        displayActions();
    }
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
    @FXML
    public void StartGame (ActionEvent event) throws IOException
    {

    }
    @FXML
    public void ViewGame (ActionEvent event) throws IOException {
    }

    public void ViewGameAction () throws IOException
    {
        try {
            List<String> RedIdString = new ArrayList<>();
            List<String> GreenIdToString = new ArrayList<>();

            //get IDs of current red/green and set all scores to zero
            for(int i = 0; i < redPlayerData.size(); i++) {
                RedIdString.add(String.valueOf(redPlayerData.get(i).getId()));
                RedPlayerScores.put(redPlayerData.get(i).getId(),0);
            }

            for(int i = 0; i < greenPlayerData.size(); i++) {
                GreenIdToString.add(String.valueOf(greenPlayerData.get(i).getId()));
                GreenPlayerScores.put(greenPlayerData.get(i).getId(),0);

            }


            //shuffle both lists to get a random order
            List<String> RedKeys = trafficGenerator.getRandomKeys(RedIdString);
            List<String> GreenKeys = trafficGenerator.getRandomKeys(GreenIdToString);
            System.out.println("Red players = " + RedKeys + " Green players = " + GreenKeys);

            //start server-client
            new EchoServer().start();
            EchoClient client = new EchoClient();

            /*@note:
                    this loop is the one that sends packet from client -> server
                    possibly a good place to ask for input (who do you want to play as ? Green or Red)
                     also, could include timer as the loop limit
            */
            while(dummyTimer != 0) {
                String PlayersKey = "";
                String PersonalizedPlayersKey = "";
                boolean BadInputFlag = false;

                //collect and validate user input
                System.out.println("\n[ LOG ] ----- ROUNDS LEFT: " + dummyTimer);
                do {
                    try {
                        Scanner sc = new Scanner(System.in);
                        System.out.println("\n[ LOG ] ----- SELECT ATTACKING TEAM [Type G for green or R for red or end for closing the game] : ");
                        String userTeamChoice = sc.nextLine();
                        String userRedPlayerChoice;
                        String userGreenPlayerChoice;
                        BadInputFlag = false;

                        //check user input
                        if(userTeamChoice.equalsIgnoreCase("g")){
                            System.out.println("\n[ LOG ] ----- GREEN TEAM SHOOTS");
                            //get a random green player from GreenIdToString
                            PlayersKey = trafficGenerator.combineKeys(GreenKeys, RedKeys,":");
                            //get specific players

                            PersonalizedPlayersKey = trafficGenerator.generateCustomUserKeys(GreenKeys,RedKeys);

                            ShooterIsGreen = true;
                            ShooterIsRed = false;

                        }
                        else if(userTeamChoice.equalsIgnoreCase("r")){
                            System.out.println("\n[ LOG ] ----- RED TEAM SHOOTS");
                            //get a random RED player from RedIdToString
                            PlayersKey = trafficGenerator.combineKeys(RedKeys, GreenKeys,":");
                            //get specific players
                            PersonalizedPlayersKey = trafficGenerator.generateCustomUserKeys(RedKeys,GreenKeys);


                            ShooterIsRed = true;
                            ShooterIsGreen = false;

                        }

                        else if (userTeamChoice.equalsIgnoreCase("end")){
                            PersonalizedPlayersKey = "end";
                            ShooterIsRed = ShooterIsGreen = false;
                            BadInputFlag = false;
                        }

                        else{
                            System.out.print("[ LOG ] ----- Please enter G or R ");
                            BadInputFlag = true;
                        }

                    } catch (InputMismatchException e) {
                        System.out.print("[ LOG ] ----- Please enter G or R ");
                        BadInputFlag = true;
                    }
                }
                while (BadInputFlag);

                //send to server
                client.sendEcho(PersonalizedPlayersKey);
                //saveInfo(greenPlayerNameScore);


                dummyTimer--;

            }
            client.close();
            System.out.print("[ LOG ] ----- GAME OVER: All ROUNDS ARE ALREADY PLAYED!! ");
            for (int i = 0; i < actions.size(); i++) {
                System.out.println("Actions: "+actions.get(i));

            }
            if(dummyTimer == 0) {
                // set alert type
                alert.setAlertType(Alert.AlertType.WARNING);
                // set content text
                alert.setContentText("GAME OVER: All ROUNDS ARE ALREADY PLAYED!!!!\n");
                // show the dialog
                alert.show();
            }




        } catch (Exception exception) {
            System.out.println("[-] UDPBaseServer has encountered an exception:");
            exception.printStackTrace();
        }
    }

    public static void saveInfo()
    {
        int redScoreV = 0;
        int greenScoreV =0;
        int  tmpRScores[] = new int[redPlayerData.size()];
        int  tmpGcores[] = new int[greenPlayerData.size()];
        int counter=0;

        //SETTING TEAM SCORES
        for (Integer i:RedPlayerScores.values())
        {
            redScoreV = redScoreV + i;
            tmpRScores[counter] = i;
            counter++;
        }
        counter = 0;
        for (Integer i:GreenPlayerScores.values())
        {
            greenScoreV = greenScoreV + i;
            tmpGcores[counter] = i;
            counter++;
        }


        for(int i = 0; i < redPlayerData.size(); i++)
        {
            redPlayerData.get(i).setScore(tmpRScores[i]);
        }

        for(int i = 0; i < greenPlayerData.size(); i++)
        {
            greenPlayerData.get(i).setScore(tmpGcores[i]);

        }

        if (redScoreV > greenScoreV)
        {
            static_redScore.setText(String.valueOf(redScoreV));
            static_greenScore.setText(String.valueOf(greenScoreV));
            static_greenScore.setTextFill(Color.WHITE);
            static_redScore.setTextFill(Color.RED);
        }
        else
        {
            static_greenScore.setText(String.valueOf(greenScoreV));
            static_redScore.setText(String.valueOf(redScoreV));
            static_redScore.setTextFill(Color.WHITE);
            static_greenScore.setTextFill(Color.RED);
        }

    }

    public void PreEnteredGames (ActionEvent event) throws IOException
    {

    }
    public void prueba (ActionEvent event) throws IOException
    {
        System.out.printf("PRRRRRR");
    }
    public void FlickSync (ActionEvent event) throws IOException
    {

    }

    public void ClearGame (ActionEvent event) throws IOException
    {

    }

    public static void setActions(String action)
    {
        actions.add(action);
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

    public HBox addHBox() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");

        Text greenPlayerText = new Text(greenPlayerNameScore);
        greenPlayerText.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        greenPlayerText.setFill(Color.WHITE);

        Text redPlayerText = new Text(redPlayerNameScore);
        greenPlayerText.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        greenPlayerText.setFill(Color.WHITE);

        hbox.getChildren().addAll(greenPlayerText, redPlayerText);

        return hbox;
    }

}
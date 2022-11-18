/*
 * Overview: Implementation for the Persistence Handler class. Handles
 *           database connections and manages the flow of data in the Java application.

 * @version 1.1
 * @since 11/04/22
 * */

package com.example.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;


public class PersistenceHandler extends PlayerPersistenceInterface {

    private static PersistenceHandler instance;

    //Database Credentials/Connection details

    private String url = "ec2-54-174-31-7.compute-1.amazonaws.com";
    private final String username = "inambnipuqgeeb";
    private final String password = "32f4a6737e7836883af199972e5730472321154383d0561466908eefac20d063";
    private int port = 5432;
    private String databaseName = "dc62tnlursroj2";

    private static Connection connection = null;

    private PersistenceHandler(){
        initializePostgresqlDatabase();
    }

    public static PersistenceHandler getInstance(){
        if (instance == null) {
            instance = new PersistenceHandler();
        }
        return instance;
    }

    /*
     * Initializes data base connection
     */

    private void initializePostgresqlDatabase() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://" + url + ":" + port + "/" + databaseName, username, password);
            System.out.println("DB connected");
        } catch (SQLException | IllegalArgumentException | ClassNotFoundException ex) {
            ex.printStackTrace(System.err);
        } finally {
            if (connection == null) {
                System.exit(-1);
            }
        }
    }

    /*
     * Retrieves a player's data from database
     * @return a String containing the player's name and ID
     * @return NULL if no record found
     */
    public static ObservableList<Player> getGreenTeamPlayer(int playerId) {
        ObservableList<Player> playerList = null;
        try {
            String sql = "SELECT id, codename "
                    + "FROM green_team "
                    + "WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1,playerId);
            ResultSet sqlReturnValues = stmt.executeQuery();
            if(sqlReturnValues != null) {
                playerList = getPlayerList(sqlReturnValues);
            }

        } catch (SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
        }
        return playerList;
    }


    /*
     * Retrieves a player's data from database
     * @return a String containing the player's name and ID
     * @return NULL if no record found
     */
    public static ObservableList<Player> getRedTeamPlayer(int playerId) {
        ObservableList<Player> playerList = null;
        try {
            String sql = "SELECT id, codename "
                    + "FROM red_team "
                    + "WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1,playerId);
            ResultSet sqlReturnValues = stmt.executeQuery();
            if(sqlReturnValues != null) {
                playerList = getPlayerList(sqlReturnValues);
            }

        } catch (SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
        }
        return playerList;
    }

    /*
     * Adds a player's data to database in red_team Table
     * @param player The player object containing data to be stored
     * @return FALSE if data insertion failed
     * @return TRUE if data insertion was successful
     */
    @Override
    public boolean createRedPlayer(Player player)  {
        try {
            PreparedStatement insertStatement = connection.prepareStatement(
                    "INSERT INTO red_team (id, codename) VALUES (?,?);");
            insertStatement.setInt(1, player.getId());
            insertStatement.setString(2, player.getCodename());
            insertStatement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return false;
        }
        return true;
    }

    /*
     * Adds a player's data to database in green_team Table
     * @param player The player object containing data to be stored
     * @return FALSE if data insertion failed
     * @return TRUE if data insertion was successful
     */
    @Override
    public boolean createGreenPlayer(Player player)  {
        try {
            PreparedStatement insertStatement = connection.prepareStatement(
                    "INSERT INTO green_team (id, codename) VALUES (?,?);");
            insertStatement.setInt(1, player.getId());
            insertStatement.setString(2, player.getCodename());
            insertStatement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return false;
        }
        return true;
    }


    /* Uses ResultSet from DB as parameter and sets Player Object's attributes
     * Adds a player's data to database in green_team Table
     * @param Resultset representing DB result set
     * @return a Player obejct
     *
     */
    private static Player getPlayerFromResultSet(ResultSet rs) throws SQLException
    {
        Player player = null;
        if (rs.next()) {
            player = new Player();
            player.setId(rs.getInt("id"));
            player.setCodename(rs.getString("codename"));
        }
        return player;
    }


    /* Executes a SELECT SQL operation against the red_team table. Once it gets the data from BD, it stores it
     * in a ResultSet object. Finally, it stores the ResultSet into an ObservableList object
     *
     *
     * @return a Player object
     * @return SQLException if SQL operation fails
     *
     */
    public static ObservableList<Player> searchRedPlayers() throws SQLException, ClassNotFoundException {
        try {
            PreparedStatement selectStatement = connection.prepareStatement(
                    "SELECT * FROM red_team;");

            ResultSet rsPlayers = selectStatement.executeQuery();
            ObservableList<Player> playerList = getPlayerList(rsPlayers);
            return playerList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }


    /* Executes a SELECT SQL operation against the green_team table. Once it gets the data from BD, it stores it
     * in a ResultSet object. Finally, it stores the ResultSet into an ObservableList object
     *
     *
     * @return a Player object
     * @return SQLException if SQL operation fails
     *
     */
    public static ObservableList<Player> searchGreenPlayers() throws SQLException, ClassNotFoundException {
        try {
            PreparedStatement selectStatement = connection.prepareStatement(
                    "SELECT * FROM green_team;");
            ResultSet rsPlayers = selectStatement.executeQuery();
            ObservableList<Player> playerList = getPlayerList(rsPlayers);
            return playerList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }

    /* Uses ResultSet from DB to extract Player Object's attributes.
     * Declare an observable List which comprises Player objects and add Player objects to it
     *
     * @param ResultSet representing DB result set
     * @return  playerList ObservableList<Player> object
     */
    //Select * from red_team operation
    private static ObservableList<Player> getPlayerList(ResultSet rs) throws SQLException, ClassNotFoundException {

        ObservableList<Player> playerList = FXCollections.observableArrayList();
        while (rs.next()) {
            Player player = new Player();
            player.setId(rs.getInt("id"));
            player.setCodename(rs.getString("codename"));
            playerList.add(player);
        }
        return playerList;
    }
}
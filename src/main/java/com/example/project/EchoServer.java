package com.example.project;

import javafx.collections.ObservableList;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class EchoServer extends Thread {

    private DatagramSocket socket;
    private boolean running;
    private byte[] buf = new byte[256];

    public static boolean  red_signal;
    public static boolean  grn_signal;
    public static String   to_print;

    PlayerPersistenceInterface persistenceHandler = PersistenceHandler.getInstance();

    public EchoServer() throws SocketException {
        socket = new DatagramSocket(4445);
    }

    public void run() {
        running = true;

        while (running) {
            DatagramPacket packet
                    = new DatagramPacket(buf, buf.length);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }

            InetAddress address = packet.getAddress();
            int port = packet.getPort();
            packet = new DatagramPacket(buf, buf.length, address, port);
            String received
                    = new String(packet.getData(), 0, packet.getLength());


            if (!received.equals("end")) {
                processData(received);
            }

            System.out.println("Inside EchoServer: run: processData - ");
            /*if (received.equals("end")) {
                running = false;
                //continue;
            }*/
            try {
                socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        socket.close();
    }


    private String processData(String data){
        System.out.println("Inside processData() data - "+data);
        // Grab the size of the codename arrays
        //int numRedPlayers = View.blueCode.size();
        // int numBluePlayers = View.redCode.size();

        // Split string on the colon
        String[] data2 = data.split(":");

        // Find the ID's of the players in event
        System.out.println("Inside processData() data2[0] - "+data2[0]);
        int player1 = Integer.parseInt(data2[0]);
        System.out.println("Inside processData() data2[1] - "+data2[1]);
        int player2 = Integer.parseInt(data2[1].trim());

        // Create a string to hold the action
        String action = "";

        // If green player hits red player
        if(searchInGreenTeamTable(player1)){
            grn_signal=true;
            // Double check that player two is a red player
            if(searchInRedTeamTable(player2)){
                // Grab blue player and red player code name;

                action = (player1) + " hit " + (player2);
                System.out.println("Inside processData(): trying to print action- green player hits red player: "+action);

                // Update the score of the player and team
                //View.blueScores[player1] += 10;
                //View.blueScore += 10;

            }
        }

        // Same as above but if red player hits green player
        else if(searchInRedTeamTable(player1)){
            red_signal=true;
            if(searchInGreenTeamTable(player2)){

                action = (player1) + " hit " + (player2);
                System.out.println("Inside processData(): trying to print action- red player hits green player:"+action);

                // Update the score of the player an team
                //View.redScores[player1-15] += 10;
                //View.redScore += 10;

            }
        }

        to_print = action;
        return action;

    }

    private boolean searchInGreenTeamTable(int playerId ){
        System.out.println("Inside searchInGreenTeamTable");

        ObservableList<Player> greenPlayer = PersistenceHandler.getGreenTeamPlayer(playerId);
        System.out.println("Inside searchInGreenTeamTable(): greenPlayer list size:  "+greenPlayer.size());
        int i=0;
        do{
            if(greenPlayer.get(i).getId() == (playerId) )
            {
                return true;
            }
            i++;
        }while(greenPlayer.size()>i);
        return false;
    }

    private boolean searchInRedTeamTable(int playerId ){
        System.out.println("Inside searchInRedTeamTable");
        ObservableList<Player> redPlayer = PersistenceHandler.getRedTeamPlayer(playerId);
        System.out.println("Inside searchInRedTeamTable(): RedPlayer list size:  "+redPlayer.size());
        int i=0;
        do{
            if(redPlayer.get(i).getId() == (playerId) )
            {
                return true;
            }
            i++;
        }while(redPlayer.size()>i);
        return false;
    }

    // A utility method to convert the byte array
    // data into a string representation.
    private StringBuilder data(byte[] a)
    {
        if (a == null)
            return null;
        StringBuilder ret = new StringBuilder();
        int i = 0;
        while (a[i] != 0)
        {
            ret.append((char) a[i]);
            i++;
        }
        return ret;
    }
}
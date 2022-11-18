package com.example.project;

import java.io.IOException;
import java.net.*;

public class EchoClient {


    private DatagramSocket socket;
    private InetAddress address;

    private byte[] buf;


    public EchoClient() throws SocketException, UnknownHostException {
        socket = new DatagramSocket();
        address = InetAddress.getByName("localhost");
    }

    public String sendEcho(String msg) throws IOException {
        buf = msg.getBytes();
        DatagramPacket packet
                = new DatagramPacket(buf, buf.length, address, 4445);
        socket.send(packet);
        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        String received = new String(
                packet.getData(), 0, packet.getLength());
        System.out.println("Inside Echo Client: received - "+received);
        return received;
    }

    public void close() {
        socket.close();
    }
}

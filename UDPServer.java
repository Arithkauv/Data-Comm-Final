/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.georgiasouthern.csci5332;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author hzhang
 */
public class UDPServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(5332);
        byte[] buf = new byte[256];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        while (true) {
            socket.receive(packet);
            InetAddress ip = packet.getAddress();
            int port = packet.getPort();
            System.out.println("ip addr: " + ip);
            System.out.println("port: " + port);
            String msg = new String(buf, StandardCharsets.UTF_8);
            System.out.println("msg: " + msg);
            byte[] sbuf = ("Server: " + msg).getBytes();
            DatagramPacket spacket = new DatagramPacket(sbuf, sbuf.length, ip, port);
            socket.send(spacket);
            
        }
    }
    
}

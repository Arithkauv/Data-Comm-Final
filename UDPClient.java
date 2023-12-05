/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.georgiasouthern.csci5332;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author hzhang
 */
public class UDPClient {

    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        byte[] buf = "Hello, world!".getBytes();
        InetAddress ip = InetAddress.getLocalHost();
        DatagramPacket packet = new DatagramPacket(buf, buf.length, ip, 5332);

        socket.send(packet);

        byte[] rbuf = new byte[256];
        DatagramPacket rpacket = new DatagramPacket(rbuf, rbuf.length);
        socket.receive(rpacket);

        InetAddress rip = rpacket.getAddress();
        int rport = rpacket.getPort();
        System.out.println("ip addr: " + rip);
        System.out.println("port: " + rport);
        String msg = new String(rbuf, StandardCharsets.UTF_8);
        System.out.println("msg: " + msg);

    }

}

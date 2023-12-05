/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.georgiasouthern.csci5332;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author hzhang
 */
public class TCPClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 5332);
        PrintWriter pw = new PrintWriter(socket.getOutputStream());
        pw.println("Savannah");
        pw.flush();
        BufferedReader br = new BufferedReader(new InputStreamReader(
            socket.getInputStream()));
        String line = br.readLine();
        System.out.println(line + "beep boop");
        br.close();
        pw.close();
        socket.close();
    }
}

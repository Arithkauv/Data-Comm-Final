package edu.georgiasouthern.csci5332;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import com.google.gson.Gson;

public class Assignment6 {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(5332);

        while (true) {
            Socket socket = server.accept();

            Handler handler = new Handler(socket);
            handler.start();
        }
    }
}

class Handler extends Thread {
    Socket socket;
    private static String apiKey = "d591f2e6ddde104a7d3cefa614f24ebb";

    public Handler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        GeoInfo gi = null;

        try {
            InputStream input1 = socket.getInputStream();
            BufferedReader reader1 = new BufferedReader(new InputStreamReader(input1));

            String city = reader1.readLine();
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + city + "&APPID=" + apiKey);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            InputStream input2 = conn.getInputStream();
            BufferedReader reader2 = new BufferedReader(new InputStreamReader(input2));
            Gson gson = new Gson();

            gi = gson.fromJson(reader2, GeoInfo.class);
            gi.dataSetter();

            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output);

            writer.println("Temperature: " + getTemperature(gi) + " (Fahrenheit)");

            reader1.close();
            reader2.close();
            writer.flush();
            writer.close();
            socket.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static double getTemperature(GeoInfo gi) {
        return gi.temp;
    }

    class GeoInfo {
        Coord coord;
        Main main;
        String name;
        double lat;
        double lon;
        double temp;

        public void dataSetter() {
            lat = coord.lat;
            lon = coord.lon;
            temp = main.temp;
        }
    }

    class Coord {
        double lat;
        double lon;
    }

    class Main {
        double temp;
        double feels_like;
        double temp_min;
        double temp_max;
        int pressure;
        int humidity;
    }
}
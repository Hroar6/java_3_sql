package client;

import java.io.*;

public class History {
    private static PrintWriter out;
    private static String path;

    public static File getHistoryFile(String login) {
        path = "txt/history_" + login + ".txt";
        return new File(path);
    }

    public static void start(String login) {
        File history = getHistoryFile(login);
        if (history.exists()){
            try {
                history.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            out = new PrintWriter(new FileOutputStream(path, true), true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void writeMsg(String msg) {
        out.println(msg);
    }

    public static void stop() {
        if (out != null) {
            out.close();
        }
    }



}
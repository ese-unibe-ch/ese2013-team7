package com.ese2013.mensaunibe.model.api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import android.content.Context;

public class CacheRequest {

    public static String readAllCachedText(Context context, String filename) {
        File file = new File(context.getCacheDir(), filename);
        return readAllText(file);
    }

    public static String readAllText(File file) {
        try {
            FileInputStream inputStream = new FileInputStream(file);
            return readAllText(inputStream);
        } catch(Exception ex) {
            return null;
        }
    }

    public static String readAllText(InputStream inputStream) {
        InputStreamReader inputreader = new InputStreamReader(inputStream);
        BufferedReader buffreader = new BufferedReader(inputreader);

        String line;
        StringBuilder text = new StringBuilder();

        try {
            while (( line = buffreader.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
        } catch (IOException e) {
            return null;
        }

        return text.toString();
    }

    public static boolean writeAllCachedText(Context context, String filename, String text) {
        File file = new File(context.getCacheDir(), filename);
        return writeAllText(file, text);
    }

    public static boolean writeAllText(File file, String text) {
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            return writeAllText(outputStream, text);
        } catch(Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean writeAllText(OutputStream outputStream, String text) {
        OutputStreamWriter outputWriter = new OutputStreamWriter(outputStream);
        BufferedWriter bufferedWriter = new BufferedWriter(outputWriter);

        try {
            bufferedWriter.write(text);
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                bufferedWriter.close();
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }

        return false;
    }

}
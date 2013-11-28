package com.ese2013.mensaunibe.model.data;

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

/**
 * @author group7
 * @author Jan Binzegger
 */

public class CacheRequest {

	/**
	 * returns the whole content of one file
	 * @param context
	 * @param filename
	 * @return file content as a string
	 */
    public static String readAllCachedText(Context context, String filename) {
        File file = new File(context.getCacheDir(), filename);
        return readAllText(file);
    }

    /**
     * actually reads the whole file and returns it as string
     * @param file
     * @return the file content as a string
     */
    public static String readAllText(File file) {
        try {
            FileInputStream inputStream = new FileInputStream(file);
            return readAllText(inputStream);
        } catch(Exception ex) {
            return null;
        }
    }

    /**
     * read the text from an input stream
     * @param inputStream
     * @return string of read text
     */
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

    /**
     * write text to cache
     * @param context
     * @param filename
     * @param text
     * @return true, if it was fine, other else false
     */
    public static boolean writeAllCachedText(Context context, String filename, String text) {
        File file = new File(context.getCacheDir(), filename);
        return writeAllText(file, text);
    }
    
    /**
     * writes the text to a file with an outputstream
     * @param file
     * @param text
     * @return true, if it worked, otherelse false
     */
    public static boolean writeAllText(File file, String text) {
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            return writeAllText(outputStream, text);
        } catch(Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * write all text to an output stream
     * @param outputStream
     * @param text
     * @return true if it worked, otherelse false
     */
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
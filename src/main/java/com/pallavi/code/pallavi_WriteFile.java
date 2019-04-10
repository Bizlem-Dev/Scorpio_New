package com.pallavi.code;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class pallavi_WriteFile {

    /**
     * This class shows how to write file in java
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) {
    	
        String data = "";
        int noOfLines = 10000;
        
        writeUsingBufferedWriter(data, noOfLines);
        
        System.out.println("DONE");
    }

    /**
     * Use Streams when you are dealing with raw data
     * @param data
     */
    static void writeUsingOutputStream(String data, String path) {
        OutputStream os = null;
        try {
            os = new FileOutputStream(new File(path));
            os.write(data.getBytes(), 0, data.length());
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    
    /**
     * Use BufferedWriter when number of write operations are more
     * It uses internal buffer to reduce real IO operations and saves time
     * @param data
     * @param noOfLines
     */
    private static void writeUsingBufferedWriter(String data, int noOfLines) {
        File file = new File("/Users/pankaj/BufferedWriter.txt");
        FileWriter fr = null;
        BufferedWriter br = null;
        String dataWithNewLine=data+System.getProperty("line.separator");
        try{
            fr = new FileWriter(file);
            br = new BufferedWriter(fr);
            for(int i = noOfLines; i>0; i--){
                br.write(dataWithNewLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                br.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}
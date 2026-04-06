package com.ch11;

import java.io.*;

public class CopyFileContents {
    public static void main(String[] args) {
        File file = new File("D:\\idea-workspaces\\learn-java\\src\\com\\ch11\\cost.txt");
        File targetfile = new File("D:\\idea-workspaces\\learn-java\\src\\com\\ch11\\cost1.txt");
        Reader reader = null;
        Writer writer = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            reader = new FileReader(file);
            writer = new FileWriter(targetfile);
            bufferedReader = new BufferedReader(reader);
            bufferedWriter = new BufferedWriter(writer);

            String s;
            while((s=bufferedReader.readLine())!=null){
                bufferedWriter.write(s);
                bufferedWriter.newLine();
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                bufferedWriter.close();
                bufferedReader.close();
                writer.close();
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}

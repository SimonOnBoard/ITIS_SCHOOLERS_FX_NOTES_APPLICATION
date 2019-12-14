package sample.services;

import sample.Main;
import sample.models.Note;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileWorker {
    public static void write(Note note) {
        FileWriter fileWriter1 = null;
        try {
            fileWriter1 = new FileWriter("data.txt", true);
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter1);
            String text = getCurrentText(note.getText());
            String resultLine = note.getId() + " " + note.getName() + " " + text + " " + note.getCategory() + "\n";
            Main.lengths.add(resultLine.length());
            bufferWriter.write(resultLine);
            bufferWriter.close();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private static String getCurrentText(String text) {
        String a = "";
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '\n') {
                a += "\t";
            } else if (text.charAt(i) == ' ') {
                a += "\f";
            } else {
                a += text.charAt(i);
            }
        }
        return a;
    }

    public static String delete = " delete\n";

    public static void deleteNoteFromFile(Note note) {
        Long lastNum = note.getId();
        long sum = getCurrentSum(lastNum);
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile("data.txt", "rw");
            randomAccessFile.seek(sum);
            randomAccessFile.write(delete.getBytes());
            randomAccessFile.close();
        } catch (FileNotFoundException e) {
            throw new IllegalStateException(e);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

    }

    private static long getCurrentSum(Long lastNum) {
        long sum = 0;
        for (int i = 0; i < lastNum; i++) {
            sum += Main.lengths.get(i);
            sum += 1;
        }
        sum -= 1;
        return sum;
    }
}

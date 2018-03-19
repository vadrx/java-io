package ru.novsu.povt.vas;

import java.io.*;
import java.util.Arrays;

public class Main{
    private final static String pathSin = "./sin.txt";
    private final static String pathInput = "./input.txt";
    private final static String pathSin2 = "./sin2.dat";

    private static File sin = new File(pathSin);
    private static File input = new File(pathInput);
    private static File sin2 = new File(pathSin2);

    private static double[] array = new double[361];
    private static double[] array2;
    private static BufferedReader bufferedReader;
    private static String stringValue;
    private static int value;

    public static File getSin() {
        return sin;
    }

    public static File getInput() {
        return input;
    }

    public static File getSin2() {
        return sin2;
    }

    public static void createSinFile(){
        try {
            if (sin.createNewFile()){
                System.out.println("sin.txt has created!");
            } else {
                System.out.println("Error while creating sin.txt, file already exists in specified path!");
            }

        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void writeSinFile(){
        double x;
        try (PrintWriter printWriter = new PrintWriter(getSin())){
            for (int i=0; i <= 360; i++){
                x = Math.sin(Math.toRadians(i));
                printWriter.println(Double.toString(x));
            }
            printWriter.flush();
            printWriter.close();
            System.out.println("sin.txt writed!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void readSinFile(){
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(getSin())));
            try {
                for (int i=0; (stringValue = bufferedReader.readLine()) != null; i++){
                    array[i] = Double.parseDouble(stringValue);
                }
                bufferedReader.close();
                System.out.println("sin.txt readed!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void readInputFile(){
        value = 0;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(getInput())));
            try {
                while ((stringValue = bufferedReader.readLine()) != null){
                    value = Integer.parseInt(stringValue);
                }
                bufferedReader.close();
                System.out.println("input.txt readed!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void createSin2File(){
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(getSin2()));
            if (sin2.exists()){
                if (sin2.createNewFile()){
                    System.out.println("ser.dat has created!");
                } else {
                    System.out.println("Error while creating ser.txt, file already exists in specified path!");
                }
            }
            outputStream.writeObject(array);
            System.out.println("Array writed in sin2.dat");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void readSin2File(){
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(getSin2()));
            try {
                array2 = (double[]) inputStream.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println("sin2.dat readed!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        //Sin
        createSinFile();
        writeSinFile();
        //Input
        readSinFile();
        readInputFile();
        System.out.println("Value from input.txt = " + value);
        System.out.println("Correspond sin = " + array[value-1]);
        //Serialize
        createSin2File();
        readSin2File();
        //Output
        System.out.println("Array from sin.txt: ");
        System.out.println(Arrays.toString(array));
        System.out.println("Array from sin2.dat: ");
        System.out.println(Arrays.toString(array2));
    }
}

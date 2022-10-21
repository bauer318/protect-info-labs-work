package ru.rsreu.kibamba;

import java.util.Locale;
import java.util.Scanner;

public class Runner {

    private static final String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    public static void main(String[] args){
        String word;
        String wordKey;
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите слово-лозунг: ");
        wordKey = sc.nextLine();
        if(!wordKey.isEmpty()){
            System.out.print("Введите слово: ");
            word = sc.nextLine();
            if(!word.isEmpty()){
                System.out.println("Шифрующая таблица");
                printLine(ALPHABET);
                long startTime = System.nanoTime();
                printLine(getSecondLine(ALPHABET,wordKey.toLowerCase()));
                String encryptedWord = encryptDecryptWord(true,word.toLowerCase(),getSecondLine(ALPHABET,wordKey), ALPHABET);
                System.out.println("зашифрованное слово : "+encryptedWord.toLowerCase());
                System.out.println("дешифрованное слово : "+encryptDecryptWord(false,encryptedWord,getSecondLine(ALPHABET,wordKey), ALPHABET).toLowerCase());
                System.out.println(System.nanoTime()-startTime +" nanoseconds ");
            }
        }else {
            System.out.println("Error!!! Incorrect input data");
        }
        sc.close();

    }
    private static String getSecondLine(String alphabet, String wordKey){
        String str = "";
        String wordKeyAndAlphabet = wordKey+alphabet;
        for(int i=0; i<wordKeyAndAlphabet.length();i++){
            if(!str.contains(wordKeyAndAlphabet.substring(i,i+1))){
                str+=wordKeyAndAlphabet.substring(i,i+1);
            }
        }
        return str;
    }
    private static String encryptDecryptWord(boolean isEncryptMode,String word, String secondLine, String alphabet){
        String result ="";
        for(int i=0; i<word.length();i++){
            String currentLetter = word.substring(i,i+1);
            result += isEncryptMode?encryptHelper(alphabet,secondLine,currentLetter):encryptHelper(secondLine,alphabet,currentLetter);
        }
        return result;
    }
    
    private static String encryptHelper(String alphabet,String secondLine, String currentLetter){
        int currentLetterPosition = alphabet.indexOf(currentLetter);
        return secondLine.substring(currentLetterPosition,currentLetterPosition+1);
    }

    private static void printLine(String line){
        for(int i=0;i<line.length();i++){
            System.out.print(line.substring(i,i+1)+" ");
        }
        System.out.println();
    }
}

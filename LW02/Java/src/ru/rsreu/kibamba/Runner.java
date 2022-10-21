package ru.rsreu.kibamba;

import java.util.Map;

public class Runner {
    private static int[] key = null;

    private static void setKey(int[] parKey){
        key = new int[parKey.length];
        for(int i=0; i<parKey.length;i++){
            key[i] = parKey[i];
        }
    }
    private static void setKey(String[] parKey){
        key = new int[parKey.length];
        for(int i =0; i<parKey.length;i++){
            key[i] = Integer.parseInt(parKey[i]);
        }
    }
    private static void setKey(String parKey){
        setKey(parKey.split(" "));
    }

    private static String encrypt(String input){
        String result ="";
        for(int i = 0; i<input.length()%key.length;i++){
            input += input.substring(i,i+1);
        }
        for(int i=0; i<input.length();i+=key.length){
            char[] transposition = new char[key.length];
            for(int j=0; j<key.length;j++){
                transposition[key[j]-1] = input.charAt(i+j);
            }
            for(int j = 0; j<key.length;j++){
                result += transposition[j];
            }
        }
        return result;
    }

    private static String decrypt(String input){
        String result="";
        for(int i=0; i<input.length();i+= key.length){
            char[] transposition = new char[key.length];
            for(int j=0; j<key.length;j++){
                int index = i+key[j]-1;
                transposition[j] = input.charAt(index);
            }
            for(int j = 0; j<key.length;j++){
                result += transposition[j];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        int[] k = {2,3,1,4};
        setKey(k);
        String textEncrypted = encrypt("перестановка");
        String textDecrypted = decrypt(textEncrypted);
        System.out.println("Message : перестановка; key 2 3 1 4");
        System.out.println("Encrypted message " +textEncrypted);
        System.out.println("Decrypted message " +textDecrypted);
        System.out.println("Time: "+(System.nanoTime()-startTime)+ " nanoseconds");
    }
}

public class Main {
    public static void main(String[] args) {
        String toCode = "Шифргамма";
        toCode = extendToCode(toCode);
        int m = 4096*4;
        int y0 = m-5;
        int y1 = 4091;
        int j = 0;
        int gamma[] = getGamma(m,y0,y1);
        long startTime = System.currentTimeMillis();
        String encryptedText = encryptDecryptText(toCode, gamma);
        String decryptedText = encryptDecryptText(encryptedText, gamma);
        long stopTime = System.currentTimeMillis();
        System.out.println("Message to encrypted : "+toCode);
        System.out.println("Encrypted message : "+encryptedText);
        System.out.println("Decrypted message : "+decryptedText);
        System.out.println("Time "+(stopTime - startTime)+ " ms");
    }

    private static String encryptDecryptText(String text, int[] gamma){
        int[] textSh = new int[8];
        int[] buff = new int[8];
        char ch;
        String result ="";
        int j = 0;
        do{
            for(int i=0; i<8;i++){
                ch = text.charAt(i+j);
                buff[i] = (int)ch;
            }
            for(int i=0; i<8;i++){
                textSh[i]=buff[i]^gamma[i];
                result += Character.toString((char)textSh[i]);
            }
            j+=8;
        }while (j<text.length());

        return  result;
    }
    public static int[] getGamma(int m, int y0, int y1){
        int[] result = new int[9];
        result[0] = y0;
        result[1] = y1;
        for(int i=2;i<8;i++){
            result[i] = (result[i-1]+result[i-2])%m;
        }
        return result;
    }
    private static String extendToCode(String toCode){
        int sizeTo = 8-(toCode.length()%8);
        for(int i=0;i<sizeTo;i++){
            toCode +=" ";
        }
        return toCode;
    }
}
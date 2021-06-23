package it.polimi.ingsw.client.view.CLI;

public class Utils {

    public static int getStringLengthWithoutANSI(String str) {
        return str.replaceAll("(\\x9B|\\x1B\\[)[0-?]*[ -\\/]*[@-~]", "").length();
    }

    public static String getAlignedToLeft(String string, int length){
        StringBuffer temp = new StringBuffer(string);
        for(int i = 0; i < length-getStringLengthWithoutANSI(string); i++){
            temp.append(" ");
        }
        return temp.toString();
    }
    public static String getAlignedToCenter(String string, int length){
        int numberOfSpaces = (length - getStringLengthWithoutANSI(string))/2;
        StringBuffer temp = new StringBuffer();
        for(int i = 0; i < numberOfSpaces; i++){
            temp.append(" ");
        }
        temp.append(string);
        for(int i = 0; i < length - numberOfSpaces -getStringLengthWithoutANSI(string); i++){
            temp.append(" ");
        }
        return temp.toString();
    }
}

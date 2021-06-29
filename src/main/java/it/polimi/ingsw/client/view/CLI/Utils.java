package it.polimi.ingsw.client.view.CLI;

public class Utils {

    public static int getStringLengthWithoutANSI(String str) {
        return str.replaceAll("(\\x9B|\\x1B\\[)[0-?]*[ -\\/]*[@-~]", "").length();
    }

    public static String getAlignedToLeft(String string, int width){
        StringBuilder temp = new StringBuilder(string);
        for(int i = 0; i < width-getStringLengthWithoutANSI(string); i++){
            temp.append(" ");
        }
        return temp.toString();
    }
    public static String getAlignedToCenter(String string, int width){
        int numberOfSpaces = (width - getStringLengthWithoutANSI(string))/2;
        StringBuilder temp = new StringBuilder();
        for(int i = 0; i < numberOfSpaces; i++){
            temp.append(" ");
        }
        temp.append(string);
        for(int i = 0; i < width - numberOfSpaces -getStringLengthWithoutANSI(string); i++){
            temp.append(" ");
        }
        return temp.toString();
    }


    public static String getAlignedToBothSide(String string, int width ){
        int numberOfSpaces = (width - 2  - (getStringLengthWithoutANSI(string)*2));
        StringBuilder temp = new StringBuilder();
        temp.append(" ").append(string);
        for(int i = 0; i < numberOfSpaces; i++){
            temp.append(" ");
        }
        temp.append(string).append(" ");
        return temp.toString();
    }
}

package project;

import java.io.*;
import java.util.*;

public class FileService {
    private String filePath;
    private List<String> singleWords;//List contains separated words from a file

    public FileService(String filePath) {
        this.filePath = filePath;
    }

    private List<String> reading() {
        /*The method read every line of a file and interpret it
        * as the String and the position of the "readLines" list*/
        List<String> readLines = new ArrayList<>();
        File file = new File(filePath);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                readLines.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return readLines;
    }

    private List<Character> stringToChars(List<String> readLines) {
        /*The method cut every String of read line, by method reading(), for single chars and add them
        to list "characters"*/
        List<Character> characters = new ArrayList<>();
        for (int i = 0; i < readLines.size(); i++) {
            for (int j = 0; j < readLines.get(i).length(); j++) {
                characters.add(readLines.get(i).charAt(j));
            }
        }
        return characters;
    }

    private List<String> readWords(List<Character> c) {
        /*The method use list of collected chars to define a words.
        * To define, method scan every char in the list "c*
        and replace every non letter char, define by method letter(),
        *with #, then remove duplicated hashes in the neighbourhood's positions.
        * Then, the method reads every # as the end of the word, and convert collected chars
        * to one String, and add the String to the List "words"
        * FE '#'D'o'g'#'l'i'k'e's'#'e'a't'i'n'g' method will convert to [Dog, likes, eating]*/

        for (int i = 0; i < c.size(); i++){
            if(!letter(charToInt(c.get(i)))){
                c.set(i,'#');
                /*the loop scan every position of the char list and overwrite
                previous non letter char with #*/
            }
        }
        removeDuplicatedMarks(c,'#');
        //removing duplicated # to one #
        List<String> words = new ArrayList<>();
        List<Character> word = new ArrayList<>();
        for (int i = 0; i < c.size(); i++) {
            if (c.get(i) == '#') {
                //find # and add convert collected chars to String
                //and add the String to the list "words"
                words.add(charsToString(word));
                word = new ArrayList<>();
            }else if (i == c.size()-1) {
                /*Over of searching in the last index of the list*/
                word.add(c.get(c.size()-1));
                //adding last char to the word
                words.add(charsToString(word));
                //adding last word to the "words" list
                break;
            }
            else{
                //adding chars
                word.add(c.get(i));
            }
        }

        return words;
    }



    private List<Character> removeDuplicatedMarks(List<Character> c, char toDelete){
        /*The method delete every duplicated char "toDelete*/
        int a = Character.valueOf(toDelete);
        //set ascii code of char "toDelete"
        List<Integer> ascii = listCharsToIntList(c);
        //ascii contains ascii code of every char in list "c"
        List<Integer> multipleSpacesIndexes = new ArrayList<>();
        //list will contain every index of duplicated "toDelete" in the neighbour's position
        for(int i = 0; i < ascii.size()-1; i++){
            if(ascii.get(i) == a && ascii.get(i+1) == a){
                //searching for duplications in the neighbour's positions
                multipleSpacesIndexes.add(i);
                //adding the index to the list for later deleting duplicated position
            }
        }
        for(int i = multipleSpacesIndexes.size()-1; i >= 0; i--){
            int index = multipleSpacesIndexes.get(i);
            //deleting every duplication position
            c.remove(index);
        }

        return c;
    }

    private List<Integer> listCharsToIntList(List<Character> c){
        //method convert collected chars to list of theirs ascii codes
        List<Integer> ascii = new ArrayList<>();
        for(int i =0; i < c.size(); i++){
            int a = Character.valueOf(c.get(i));
            ascii.add(a);
        }
        return  ascii;
    }



    private static boolean letter(int mark){
        //method define letter by ascii code
        boolean yes = false;
        if((mark > 64 && mark < 91 ) || (mark > 96 && mark < 123 ) || mark > 128){
            yes = true;
        }
        return yes;
    }


    private String charsToString(List<Character> c) {
        //building String from a char's list
        StringBuilder sb = new StringBuilder();
        for (Character ch : c) {
            sb.append(ch);
        }
        String word = sb.toString();
        return word;
    }



    private static int charToInt(char ch){
        //getting an ascii code
        int i = Character.valueOf(ch);
        return i;
    }

    private void charsDisplay(List<String> str){
        //displaying String's list as a chars
        int start = 0;
        int charsInLine = 50;
        //setting quantity of chars in one line
        List<Character> characters = stringToChars(str);
        int len = characters.size();
        while(start+charsInLine <= characters.size()){
            System.out.println();
            for (int i = start ; i < start + charsInLine; i++){
                System.out.print(characters.get(i));
            }
            start = start + charsInLine;
        }
        if(len%charsInLine != 0) {
            int over = start;
            System.out.println();
            for (int i = over; i < characters.size(); i++) {
                System.out.print(characters.get(i));
            }
        }
    }


    public void betterDisplay(List<String> str){
        //method define how many positions of a list will be displayed
        int start =0;
        int obejcts = 7;
        //setting display for 7 objects
        while(start+obejcts < str.size()){
            System.out.println();
            for(int i =start; i < obejcts+start; i++){
                System.out.print(str.get(i) + " ");
            }
            start = start+obejcts;
        }
        if(str.size() % obejcts !=0){
            System.out.println();
            for (int i = start; i < str.size(); i++) {
                System.out.print(str.get(i) + " ");
            }
        }
    }

    private List<String> readMe() {
        //method use classe's methods to construct list of words for the file
        singleWords = readWords(stringToChars(reading()));
        return singleWords;
    }

    public void display(){
        readMe();
        betterDisplay(singleWords);
    }


}



package project;

import java.util.List;
import java.util.Scanner;

public class DisplayBox {
    //this class only contains messages for display and Scanner

    private static Scanner scan = new Scanner(System.in);

    protected static String scanCom(){
        String com = scan.nextLine();
        return com;
    }

    protected static int scanInt(){
        int com = scan.nextInt();
        return com;
    }

    protected static void closeScanning(){
        scan.close();
    }


    protected static void menu1(){
        System.out.println("/start ----> Start the app");
        System.out.println("/close ----> Close the app");
    }

    protected static void separation(){
        int q = 55;
        System.out.println("");
        for(int i =0 ; i <=q ; i++){
            System.out.print("-");
        }
        System.out.println("");
    }

}

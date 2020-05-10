package project;

import java.io.*;

public class Main extends DisplayBox {

    public static void main(String[] args) {
	// write your code here
        FileService example = new FileService("src/exampleFile.txt");
        separation();
        example.display();
        separation();
    }
}

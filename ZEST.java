/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.zest;

import java.io.BufferedReader; //Allows the user to read data from input related to IOException ,BufferredReader and InputStreamReader 
import java.io.IOException;  //input and output  exception 
import java.io.InputStreamReader; // Reads bytes from an input stream  deacodes and return character using a specified charset(set of a characters) or just reads input
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


// I'm writing a scanner which takes a stream of characters and return line of tokens 
public class ZEST{
    static boolean hadError = false;
    
    
    
    public static void main(String[] args)throws IOException{
        if(args.length >1){  // check the amount of cli arg passed
            System.out.println("Usage: jlox [script]");
            System.exit(64);  
            
        }
        else if(args.length == 1){
            runFile(args[0]);
        }
        else {
            runPrompt();
        }
        
    }
    
    // I think this one is for writing in CLI
    private static void runPrompt() throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);  //Similar to scanner
        BufferedReader reader = new BufferedReader(input);      //similar scanner
        for(;;){   //An infinite loop  
            System.out.print("> ");   // print >  most command line start with this 
            String line = reader.readLine();   // .nextInt
            if(line == null) break;    // if doesn't have data break
            run(line);
            hadError = false;       //1st error
                
        }
    }
    // I think this one is for running source code
   private static void runFile(String path) throws IOException {
      byte[] bytes = Files.readAllBytes(Paths.get(path));   // read source file in byte form array 
      run(new String(bytes, Charset.defaultCharset())); // turn that source file into string type
      if(hadError) System.exit(65);     //2nd error
          
   }
   
   private static void run(String source){
       Scanner scanner = new Scanner(source);   // instantiate the Scanner object 
       List<Token> tokens = scanner.scanTokens(); // turn the tokens returned into list form
       
       for(Token token : tokens){
           System.out.println(token);  // print the tokens returned interratively
       }
   }
   //reporting error
   static void error(int line , String message){
       report(line, "" , message);     

}
   // Reporting error
   private static void report(int line , String where , String message){
       System.err.print("[line " + line + "] Error" + where + ":" + message);
       hadError = true;
   }
       
   }




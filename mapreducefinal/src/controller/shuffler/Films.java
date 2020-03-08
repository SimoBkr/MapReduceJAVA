package controller.shuffler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import controller.homePage.ChartController;

public class Films {
   public static void main(String[] args) {
       readFile("C:\\Users\\toshiba\\Desktop\\map\\mapreduceV1\\Film\\Film.txt");
   }

   public static String readFile(String pathToFile,String...strings){
       String ligne = "";
       String r= "";
       try {
           BufferedReader reader = new BufferedReader(new FileReader(new File(pathToFile)));
           while((ligne = reader.readLine()) != null){
               if(ligne.contains("2019") && ligne.contains(strings[1]) && ligne.contains(strings[2])){
            	   String[] t = ligne.split("[|]+");
            	   r = t[1].toString();
            	   System.out.println(r);
               }
           }
       } catch (Exception ex){
           System.err.println("Error. "+ex.getMessage());
       }
       System.out.println(r);
	return ligne;
   }
}
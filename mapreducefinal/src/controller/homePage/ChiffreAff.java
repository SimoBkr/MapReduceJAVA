package controller.homePage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ChiffreAff {
	
   public static float readFile(String pathToFile,String...strings){
	   float max = 0;
       try {
		BufferedReader reader = new BufferedReader(new FileReader(new File(pathToFile)));
           String ligne;
    	   List<Float> list = new ArrayList<Float>();
           while((ligne = reader.readLine()) != null){
               if(ligne.contains(strings[0])){
            	   String[] t = ligne.split("[|]+");
            	   float f = Integer.parseInt(t[3])*Float.parseFloat(t[4]); 
            	   list.add(f);
               }
           }
           
            max = list.get(0);
           for (int i = 1; i < list.size(); ++i) {
				max = list.get(i)+max;
		}
           System.out.println(max);
       } catch (Exception ex){
           System.err.println("Error. "+ex.getMessage());
       }
	return max;
   }
}
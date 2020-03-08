package Threads;

import controller.shuffler.*;
import controller.homePage.*;


public class CstmThread extends Thread {
 String pathToFile;
	String theme;
	String[] s ;
	
	public CstmThread(String pathToFile,String theme,String...strings) {
		super();
		this.pathToFile = pathToFile;
		this.theme = theme ;
		s =strings;
	}

	public String getPathToFile() {
		return pathToFile;
	}

	public void setPathToFile(String pathToFile) {
		this.pathToFile = pathToFile;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	@Override
	public void run() {
		switch (theme) {
		case "Chiffre d'affaire" : 
			if(Thread.currentThread().getName() == "c") {
				ChartController.fileCA1 = ChiffreAff.readFile(pathToFile,s[0]);
				System.out.println(ChartController.fileCA1);}
			else if(Thread.currentThread().getName() == "d")
				ChartController.fileCA2 = ChiffreAff.readFile(pathToFile,s[0]);
			else if(Thread.currentThread().getName() == "e")
				ChartController.fileCA3 = ChiffreAff.readFile(pathToFile,s[0]);
			break;
		}
	}

}

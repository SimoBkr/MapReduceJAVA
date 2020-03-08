package filehandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileHandler {
	public static Map<String,ArrayList<String>> lines = new HashMap<String, ArrayList<String>>();
	public static String mainPath = "C:\\Users\\toshiba\\Desktop\\MapReduce\\mapreducefinal\\tmp";
	public static URL icons = FileHandler.class.getResource("/icons");
	private final static int NUMBER_OF_FILES = 3;
	public static boolean fileExists(File file) {
		return file.exists();
	}
	
	public static void deleteFile(ArrayList<String> file) throws IOException {
		for (String string : file) {
			Path path = Paths.get(string);
			Files.delete(path);
		}
	}
	public static ArrayList<String> ListeFichier(String input) throws IOException
	{
		String fileNameWithOutExtention = getFileNameWithoutExtension(new File(input));
		ArrayList<String> ListeFile = null;
		try (Stream<Path> walk = Files.walk(Paths.get(mainPath)))
		{
		 ListeFile = (ArrayList<String>) walk.filter(Files::isRegularFile).map(x->x.toString()).filter(x -> x.contains(fileNameWithOutExtention)).collect(Collectors.toList());
		 
		}
		return ListeFile;
		
	}
	public static int linesCount(File file) throws IOException {
		int no_of_lines = 0;
		LineNumberReader reader = new LineNumberReader(new FileReader(file));
		try{
			reader.skip(Integer.MAX_VALUE);
			no_of_lines = reader.getLineNumber() + 1;	
		}
		finally {
			reader.close();
		}
		return no_of_lines;
	}

	public static void writeToFile(File inputfile, ArrayList<String> lines) throws IOException {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(inputfile);
			for (String line : lines) {
				fileWriter.append(line+System.lineSeparator());
			}
		}finally {
			fileWriter.close();
		}	
	}

	private static ArrayList<String> readFileToList(File inputFile) {
		Scanner myReader = null;
		ArrayList<String> lines = null;
		try {
			if(!fileExists(inputFile)) {
				throw new FileNotFoundException("File name provided not found");
			}
			lines = new ArrayList<String>();
			myReader = new Scanner(inputFile);
			while(myReader.hasNextLine()) {
				lines.add(myReader.nextLine().toString());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally {
			myReader.close();
		}
		return lines;
	}
	
	
	public static void fileSplitter(String inputFile) throws IOException {
		Path tmp = Paths.get("C:\\Users\\toshiba\\Desktop\\MapReduce\\mapreducefinal\\tmp");
		ArrayList<String> fileLines = FileHandler.readFileToList(new File(inputFile));
		int lineCount = fileLines.size();
		
		int count = 0;
		int fileNumber = 1;
		int lineCounter = (lineCount%NUMBER_OF_FILES == 0)?lineCount/NUMBER_OF_FILES :(lineCount/NUMBER_OF_FILES + 1);
		if(!Files.exists(tmp)) {
			Files.createDirectories(tmp);
		}
		String fileNameWithOutExtention = getFileNameWithoutExtension(new File(inputFile));
		String filePartName = String.format("%s%s%s.%03d.txt",tmp.toString(),FileSystems.getDefault().getSeparator(), fileNameWithOutExtention, 1);
		File newFile = new File(filePartName);
		if(!newFile.exists()) {
			newFile.createNewFile();
		}else {
			newFile.delete();
		}
		PrintWriter pw = new PrintWriter(newFile);
		Iterator<String> it = fileLines.iterator();
		while(it.hasNext()) {
			if(count == lineCounter && fileNumber <= NUMBER_OF_FILES) {
				count = 0;
				fileNumber++;
				filePartName = String.format("%s%s%s.%03d.txt",tmp.toString(),FileSystems.getDefault().getSeparator(), fileNameWithOutExtention,fileNumber);
				newFile = new File(filePartName);
				if(!newFile.exists()) {
					newFile.createNewFile();
				}else {
					newFile.delete();
				}
				pw.close();
				pw = new PrintWriter(newFile);
			}else {
				pw.println(it.next());
				count++;
			}
		}
		pw.close();
	}
	
	private static String getFileNameWithoutExtension(File file) {
        String fileName = "";
        try {
            if (file != null && file.exists()) {
                String name = file.getName();
                fileName = name.replaceFirst("[.][^.]+$", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
            fileName = "";
        }
        return fileName;
    }

}
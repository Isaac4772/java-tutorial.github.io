package fileio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DeleteFile {
	public static void main(String[] args) throws IOException {
		
		//delete file
//		Path path = Path.of("src/readme.txt");
//		
//		boolean deleted = Files.deleteIfExists(path);
//		
//		if(deleted)
//			System.out.println("File Deleted");
//		else
//			System.out.println("File does not exist");
		
		//delete directories
		Path path = Path.of("src/test");
		if(Files.deleteIfExists(path)) {
			System.out.println("Directory deleted");
		}
		else
			System.out.println("Directory does not exist");
	}
}

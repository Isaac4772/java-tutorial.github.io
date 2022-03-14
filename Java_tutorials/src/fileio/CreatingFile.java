package fileio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CreatingFile {

	public static void main(String[] args) throws IOException {

		// Create file
		Path path = Path.of("src/readme.txt");

		if (Files.exists(path))
			System.out.println("This file already exists!");
		else {
			Files.createFile(path);
			System.out.println("File is created!");
		}

		// Create directory
		path = Path.of("src/test");
		if (Files.notExists(path))
			Files.createDirectories(path);
		
		System.out.println(path.toAbsolutePath());
	}

}

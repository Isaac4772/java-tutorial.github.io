package fileio;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ConstructionPath {

	public static void main(String[] args) {
		// < java11
		Path path = Paths.get("D:\\ProgrammingFundamentals\\Files\\readme.txt");
		System.out.println("Path: " + path);

		// java11+
		path = Path.of("D:/ProgrammingFundamentals/Files/readme.txt");
		System.out.println("Path: " + path);
	}

}

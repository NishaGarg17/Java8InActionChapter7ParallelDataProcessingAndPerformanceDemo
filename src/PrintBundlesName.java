import java.nio.file.Files;
import java.nio.file.Paths;

public class PrintBundlesName {

	public static void main(String[] args) {
		try {
			long count = Files.list(Paths.get("D:\\DiamondDev\\Project\\EY\\SVN\\trunk\\2.5.0.193\\Configuration\\PR-000930\\Bundles"))
	        .count();
			System.out.println("count is: " + count);
			Files.list(Paths.get("D:\\DiamondDev\\Project\\EY\\SVN\\trunk\\2.5.0.193\\Configuration\\PR-000930\\Bundles"))
	        .forEach(System.out::println);
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

}

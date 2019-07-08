import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class CreateSectorList {
	public static void main(String[] args) throws Exception {
		Set<String> sectors = new HashSet<>();
		BufferedReader fin = new BufferedReader(new FileReader("companiesWithSector.csv"));
		String line = "", sector = "";
		while ((line=fin.readLine())!=null) {
			if (line.contains("global2000,")) {
				sector = line.split("global2000")[1];
				sectors.add(sector.substring(1));
			}
		}
		fin.close();
		PrintWriter fout = new PrintWriter(new FileWriter("sectorsNew.csv"));
		for (String s : sectors) {
			System.out.println(s+",");
			fout.println(s+",");
		}
		fout.close();
	}
}

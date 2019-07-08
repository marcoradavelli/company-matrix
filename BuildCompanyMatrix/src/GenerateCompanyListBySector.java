import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenerateCompanyListBySector {
	public static void main(String[] args) throws Exception {
		Map<String,String> sectors = new HashMap<>();
		Map<String,List<String>> companiesBySector = new HashMap<>();
		BufferedReader fin = new BufferedReader(new FileReader("sectors.csv"));
		String line = "", sector = "";
		while ((line=fin.readLine())!=null) {
			sector = line.substring(line.lastIndexOf(',')+1);
			sectors.put(line.split(",")[0], sector);
			companiesBySector.put(sector, new ArrayList<>());
		}
		fin.close();
		System.out.println(sectors);
		
		fin = new BufferedReader(new FileReader("companiesWithSector.csv"));
		String companyName = "", company = "";
		while ((line=fin.readLine())!=null) {
			if (line.contains("global2000,")) {
				sector = line.split("global2000")[1].substring(1);
				companyName = line.split(",")[0]+","+line.split(",")[1];
				company = line;
				System.out.println(sector+" "+companyName);
			}
			companiesBySector.get(sectors.get(sector)).add(company);
		}
		fin.close();
		
		PrintWriter fout = new PrintWriter(new FileWriter("companiesBySector.txt"));
		for (String s : companiesBySector.keySet()) {
			fout.println("Sector: "+s);
			List<String> companies = companiesBySector.get(s);
			for (String c : companies) {
				fout.println(c);
			}
		}
		fout.close();
		
		fout = new PrintWriter(new FileWriter("companiesBySectorTable.csv"));
		for (String s : companiesBySector.keySet()) {
			line = s;
			List<String> companies = companiesBySector.get(s);
			for (String c : companies) {
				line+=","+c.split(",")[0]+"-"+c.split(",")[1]+"-"+c.split(",")[2];
			}
			fout.println(line);
		}
		fout.close();
	}
}

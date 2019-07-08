import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

public class AddSector {
	public static void main(String[] args) throws Exception {
		//BufferedReader fin = new BufferedReader(new FileReader("Fujitsu on the Forbes Global 2000 List.html"));
		BufferedReader fin = new BufferedReader(new FileReader("companies.csv"));
		PrintWriter fout = new PrintWriter(new FileWriter("companiesWithSector.csv"));
		String line = "", url = "";
		while ((line=fin.readLine())!=null) {
			if (line.contains("https://")) {
				url = "https"+line.split(",https")[1];
				String sector = getSector(url);
				System.out.println(line+","+sector);
				fout.println(line+","+sector);
			}
		}
		fin.close();
		fout.close();
	}
	
	public static String getSector(String urlString) throws Exception {
		URL url = new URL(urlString);
		URLConnection conn = url.openConnection();
		InputStream is = conn.getInputStream();
		BufferedReader html = new BufferedReader(new InputStreamReader(is));
		String line = "", sector = "";
		while ((line=html.readLine())!=null) {
			if (line.contains("<span class=\"profile-row--type\">Industry</span><span class=\"profile-row--value\">")) {
				sector = line.split("<span class=\"profile-row--type\">Industry</span><span class=\"profile-row--value\">")[1].split("</span>")[0];
				//System.out.println(sector);
				break;
			}
		}
		return sector;
	}
}

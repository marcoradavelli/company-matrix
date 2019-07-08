import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class ForbesTableParser {
	public static void main(String[] args) throws Exception {
		BufferedReader fin = new BufferedReader(new FileReader("The World’s Largest Public Companies List.html"));
		PrintWriter fout = new PrintWriter(new FileWriter("companies.csv"));
		String line = "", record = null, url = "";
		boolean lastOne = false;
		while ((line=fin.readLine())!=null) {
			if (line.contains("<tr class=\"data\">")) {
				if (record!=null) {
					System.out.println(record+","+url);
					fout.println(record+","+url);
					if (lastOne) break;
				}
				record = "";
			}
			else if (line.contains("<td class=\"name\"><a href=\"")) {
				url = line.split("href=\"")[1].split("\"")[0];
				record += ","+line.split("class=\"exit_trigger_set\">")[1].split("</a>")[0];
			}
			else if (line.contains("<td class=\"rank\">")) {
				record = line.split("#")[1].split(" ")[0];
			}
			else if (line.contains("<td>")) {
				record += ","+line.split("<td>")[1].split("</td>")[0];
			}
			else if (line.contains("<!-- End: #list_table -->")) lastOne=true;
		}
		fin.close();
		fout.close();
	}
	
	
}

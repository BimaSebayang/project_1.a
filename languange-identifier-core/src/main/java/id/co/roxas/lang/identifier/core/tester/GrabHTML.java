package id.co.roxas.lang.identifier.core.tester;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class GrabHTML {

	public static void Connect() throws Exception {
 
		for(String bh : buatHuruf(SemuaAbjad(), 1)) {
		// Set URL
		URL url = new URL("https://kbbi.web.id/"+bh);
		URLConnection spoof = url.openConnection();

		// Spoof the connection so we look like a web browser
		spoof.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0; H010818)");
		BufferedReader in = new BufferedReader(new InputStreamReader(spoof.getInputStream()));
		String strLine = "";

		// Loop through every line in the source
		while ((strLine = in.readLine()) != null) {

			// Prints each line to the console
			System.out.println(strLine);
		}
		}
		System.out.println("End of page.");
	}

	public static void main(String[] args) {

		try {
			// Calling the Connect method
			Connect();
		} catch (Exception e) {

		}
	}

	public static List<String> SemuaAbjad() {
		List<String> arrays = new ArrayList<>();
		arrays.add("A");
		arrays.add("B");
		arrays.add("C");
		arrays.add("D");
		arrays.add("E");
		arrays.add("F");
		arrays.add("G");
		arrays.add("H");
		arrays.add("I");
		arrays.add("J");
		arrays.add("K");
		arrays.add("L");
		arrays.add("M");
		arrays.add("N");
		arrays.add("O");
		arrays.add("P");
		arrays.add("Q");
		arrays.add("R");
		arrays.add("S");
		arrays.add("T");
		arrays.add("U");
		arrays.add("V");
		arrays.add("W");
		arrays.add("X");
		arrays.add("Y");
		arrays.add("Z");

		return arrays;
	}

	public static List<String> buatHuruf(List<String> arrays, int jumlahHuruf) {
		jumlahHuruf = jumlahHuruf - 1;

		List<String> lastArr = new ArrayList<>();
		lastArr.addAll(arrays);
		while (jumlahHuruf-- > 0) {
			List<String> newArr = new ArrayList<>();
			for (String la : lastArr) {
				for (String ar : arrays) {
					newArr.add(la.concat(ar));
				}
			}
			lastArr.clear();
			lastArr.addAll(newArr);
		}

		return lastArr;
	}
}

package id.co.roxas.lang.identifier.core.tester;

import java.util.ArrayList;
import java.util.List;

public class HurufDanEjaan {

	public static void main(String[] args) {
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
		for (String hur : buatHuruf(arrays,5)) {
			System.out.println(hur);
		}
		
	}
	
	public static List<String> buatHuruf(List<String> arrays,int jumlahHuruf) {
		jumlahHuruf = jumlahHuruf-1;
		
		List<String> lastArr = new ArrayList<>();
		lastArr.addAll(arrays);
		while(jumlahHuruf -- > 0) {
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

package id.co.roxas.lang.identifier.core.tester;

public class regexR {

	public static void main(String[] args) {
		String pol = "ABC00012";
		String numPol = pol.replaceAll("[^\\d.]", "");
		int p = Integer.parseInt(numPol);
		System.err.println(p);
	}

}

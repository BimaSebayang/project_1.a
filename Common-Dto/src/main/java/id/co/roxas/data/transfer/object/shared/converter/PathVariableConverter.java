package id.co.roxas.data.transfer.object.shared.converter;

public class PathVariableConverter {
	public static String getVariablePathWithCod(String cod) {
		String[] h = cod.split("%20%");
		StringBuilder stringBuilder = new StringBuilder();

		for (String s : h) {
			stringBuilder.append(s.concat(" "));
		}
		return stringBuilder.toString().trim();
	}
	
	public static String setVariablePathWithCod(String plainText) {
		String newText = plainText.replace(" ", "%20%");
		return newText;
	}
}

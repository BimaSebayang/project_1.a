package id.co.roxas.user.data.activation.core.config;

public class PasswordDefactor {
    public static void main(String[] args) {
    	defactor("super_admin");
    }
    
    public static void defactor(String refactorPass) {
    	System.out.println(defactorChar(refactorPass));
    }
    
    public static String defactorChar(String pass) {
    	StringBuilder sb = new StringBuilder();
    	for (char c : pass.toCharArray()) {
			int ascii = c;
			int defactorAscii = 2 * ascii + 10;
			sb.append(Character.toString(defactorAscii));
		}
    	//®èúÌðjpj|f
    	//ðôêÔîÈÌÒäÜæ
    	return sb.toString();
    }
}

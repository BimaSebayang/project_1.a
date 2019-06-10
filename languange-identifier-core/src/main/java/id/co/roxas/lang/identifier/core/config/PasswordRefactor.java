package id.co.roxas.lang.identifier.core.config;

public class PasswordRefactor {
	
	    public static void main(String[] args) {
	    	System.out.println(refactorChar("âÌæØôæÌØÔlnp"));
	    }    
	
	    public static String refactorChar(String pass) {
	    	StringBuilder sb = new StringBuilder();
	    	for (char c : pass.toCharArray()) {
				int ascii =  c;
				int refactorAscii = (ascii - 10)/2;
				sb.append(Character.toString((char)refactorAscii));
			}
	    	//®èúÌðjpj|f
	    	return sb.toString();
	    }
}

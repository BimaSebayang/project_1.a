package id.co.roxas.data.transfer.object.shared.ticket;

public class PasswordRefactor {
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

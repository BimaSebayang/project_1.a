package id.co.roxas.lang.identifier.core.tester;

public class regexR {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(changeChar(";&lt;sup&gt;1&lt;/sup&gt;A, a&lt;/b&gt; &lt;i&gt;n&lt;/i&gt", 
				new String[] {"&lt;/b&gt; &lt;i&gt"}));
        //;&lt;sup&gt;1&lt;/sup&gt;A, a&lt;/b&gt; &lt;i&gt;n&lt;/i&gt;
	}

	
	private static String changeChar(String words,String[]chs)
	{
		for (String ch : chs) {
			System.err.println("replace " + words + " regex " + ch + " dengan _/partition/_ ");
			words = words.replace(ch, " _/partition/_ ");
		}
		
		return words;
	}
	
	private static String removalChar(String words,String[]chs)
	{
		for (String ch : chs) {
			System.err.println("replace " + words + " regex " + ch + " dengan nullspace ");
			words = words.replace(ch, "");
		}
		
		return words;
	}
}

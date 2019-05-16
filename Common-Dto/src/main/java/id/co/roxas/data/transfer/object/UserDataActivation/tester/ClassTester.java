package id.co.roxas.data.transfer.object.UserDataActivation.tester;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClassTester {
     public static void main(String[] args) {
    	 List<String> a1 = new ArrayList<String>();
    	 List<String> a2 = new ArrayList<String>();
    	
    	 
    	 System.out.println(!Collections.disjoint(a1, a2));
     }
}

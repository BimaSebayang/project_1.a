package id.co.roxas.lang.identifier.core.lib;

import com.google.gson.Gson;

public class LevensteinDistance {

	private static int getMinValue(int...i) {
		int temp = Integer.MAX_VALUE;
		
		System.err.println("nilai yang ingin dibandingkan " + new Gson().toJson(i));
		
		for (int j : i) {
			if(temp>j) {
				temp = j;
			}
		}
		return temp;
	}
	
	public static void main(String[] args) {
		System.out.println(LevenstheinValue("aram","mAlamram"));
	}
	
	public static int LevenstheinValue(String w1, String w2) {
		char[] chW1 = w1.toLowerCase().toCharArray();
		char[] chW2 = w2.toLowerCase().toCharArray();
		int[][] dist = new int[chW1.length+1][chW2.length+1];
		
		for (int i = 0; i < chW1.length+1; i++) {
			for(int j=0; j < chW2.length+1;j++) {
				int val = 0;
				
				if(i!=0 && j!=0) {
					System.err.println("==== untuk (i,j)= ("+i+","+j+") =====");
					  int cost = 0;
					  if(chW1[i-1]!=chW2[j-1]) {
						  cost = 1;
					  }
					 val  = getMinValue(dist[i-1][j-1]+cost,
							   dist[i-1][j]+1,dist[i][j-1]+1);
				}
				else {
					if(i==0) {
						val = j;
					}
					else if(j==0) {
						val = i;
					}
				}
				
				dist[i][j] = val;
				
			}
		}
		
		for (int i = 0; i < chW1.length+1; i++) {
			for(int j=0; j < chW2.length+1;j++) {
                System.err.print(dist[i][j] + " ");
			}
			System.err.println();
		}
		
		return dist[chW1.length][chW2.length];
	}
	
}

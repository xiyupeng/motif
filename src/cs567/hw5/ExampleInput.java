package cs567.hw5;

import java.util.List;

public class ExampleInput {
	/*
	 * For l = 8, The median string is TGACGATT
	 * and the motif is [2,5,1,2,4,5,8]
	 */
	public static String INPUT1 = "ATTGACGATTGACT"+ "\n"+
								  "GAACTTGACGATTGCAG"+ "\n"+
								  "GTGACGATTTACCGACT"+ "\n"+
								  "CATGACGATTGGAC"+ "\n"+
								  "GGACTGACGATTAT"+ "\n"+
								  "GATCTTGACGATTCTAT"+ "\n"+
								  "CATAGATATGACGATTA";
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		int l=8;
		List<Integer> Motif=BranchAndBound.motifSearch(INPUT1, l);
		 System.out.println("the motif is:"+Motif);
		 String medianString=BranchAndBound.medianString(INPUT1,l);
		 System.out.println("the median string is:"+medianString);
		 long endTime = System.currentTimeMillis();
		 System.out.println("time:"+(endTime-startTime)+"ms");
		 System.out.println("length of the motif:"+l);
	}
}

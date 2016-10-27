package cs567.hw5;

import cs567.EnumerationScheme;
import java.util.List;
import java.util.ArrayList;

public class BranchAndBound {
	
	/**
	 * See problem 1
	 * @param dna A String representing a collection of DNA sequences.
	 * Each DNA sequence is delimited by a newline character '\n'.
	 * 
	 * @param l The length of the pattern to find
	 * 
	 * @return a List of t starting positions s maximizing score(s, dna, l)
	 */
	public static List<Integer> motifSearch(String dna, int l){
		String[] dnaArray =new String[]{};
		dnaArray=dna.split("\n");
		List<String> dnalist = java.util.Arrays.asList(dnaArray);      //DNA lists
		int t=dnalist.size();                    //the number of DNA sequences
		List<Integer> dnasize = new ArrayList<Integer>();   //The length of each DNA
		List<Integer> s = new ArrayList<Integer>();  //s=(1,1.....1)
		List<Integer> bestMotif=new ArrayList<Integer>();
		for (int j=0;j<t ;j++ ) {
			String dnas=dnalist.get(j);
			dnasize.add(j,dnas.length()-l+1);
			s.add(j,1);	
			bestMotif.add(j, 1);
		}		
		int bestScore=0;
		int k=1;
		while (k>0) {
			if (k<t) {
				//s=EnumerationScheme.nextVertex(s,t,dnasize);	
				int optimisticScore=score(s,dna,l,k)+(t-k)*l;
				if (optimisticScore<bestScore) {
					s=EnumerationScheme.byPass(s,t,dnasize,k);
				}else{
					s=EnumerationScheme.nextVertex(s,t,dnasize);
				}
				}else{
					//System.out.println(bestScore);
					if (score(s,dna,l,t)>bestScore) {
						bestScore=score(s,dna,l,t);
						//System.out.println(bestScore);
						for (int o=0;o<t ;o++ ) {
							int snum=s.get(o)-1;
							bestMotif.set(o,snum);	
						}			
					//	System.out.println(bestMotif);
					}
					s=EnumerationScheme.nextVertex(s,t,dnasize);
			}
			//System.out.println(s);
			for (int i=0;i<t;i++ ) {
			    k=t;
			    if (s.get(i)==0) {
				    k=i; //0-t levels
				    break;
			    }			
		    }
		}
		//System.out.println(bestMotif);
		return bestMotif;
	}
	/**
	 * 
	 * @param s A list of integer starting positions in dna (one for each sequence), representing a profile of l-mers
	 * 
	 * @param dna A String representing a collection of DNA sequences
	 * 
	 * @param l The length of each l-mer
	 *
	 * @param t the number of dna Sequences
	 *
	 * @return the profile score
	 */
	public static int score(List<Integer> s, String dna, int l,int t){
		String[] dnaArray =new String[]{};
		dnaArray=dna.split("\n");
		List<String> dnalist = java.util.Arrays.asList(dnaArray);      //DNA lists
		
		int score=0;
		for (int i=0;i<l;i++ ) {
			int counta=0;
			int countt=0;
			int countg=0;
			int countc=0;
			for (int j=0;j<t ;j++ ) {
			    String dnas=dnalist.get(j);
			    int begin=s.get(j)-1;
			    if (dnas.substring(begin+i,begin+i+1).equals("A")){
			    	counta++;
			    }
			     if (dnas.substring(begin+i,begin+i+1).equals("T")){
			    	countt++;
			    }
			     if (dnas.substring(begin+i,begin+i+1).equals("G")){
			    	countg++;
			    }
			     if (dnas.substring(begin+i,begin+i+1).equals("C")){
			    	countc++;
			    }
		    }
		    score=score+Math.max(Math.max(counta,countt),Math.max(countc,countg));
		}
		return score;
	}

	/**
	 * See problem 1
	 * @param dna A String representing a collection of DNA sequences
	 * @param l
	 * @return medianString
	 */
	public static String medianString(String dna, int l){
		List<Integer> s = new ArrayList<Integer>();  //s=(1,1.....1)
		List<Integer> sym = new ArrayList<Integer>();
		for (int j=0;j<l;j++ ) {
			s.add(j,1);	
			sym.add(j,4);
		}	
		int bestDistance=100000;
		int k=1;
		String bestWord="";
		while (k>0) {
			String word="";
				for (int j=0;j<k;j++ ) {             //A=1, T=2, G=3, C=4
			        if (s.get(j)==1){
			        	word=word.concat("A");
			            }else if (s.get(j)==2) {
			            	word=word.concat("T");
			            }else if (s.get(j)==3) {
			            	word=word.concat("G");
			            }else if (s.get(j)==4) {
			            	word=word.concat("C");
			            }
			    }
			if (k<l) {
				int optimisticDistance=totalDistance(word,dna);
				if (optimisticDistance>bestDistance) {
					s=EnumerationScheme.byPass(s,l,sym,k);
				}else{
					s=EnumerationScheme.nextVertex(s,l,sym);
				}					
			}else{
				//System.out.println(s);
				//System.out.println(word);
			    if (totalDistance(word,dna)<bestDistance) {
			        	bestDistance=totalDistance(word,dna);
			        	bestWord=word;
			        	//System.out.println(bestWord);
			        	//System.out.println(bestDistance);
		            }
		        s=EnumerationScheme.nextVertex(s,l,sym);	
		       // System.out.println(s);
				//System.out.println(word);
			}
			//System.out.println(bestWord);
			for (int i=0;i<l;i++ ) {
			    k=l;
			    if (s.get(i)==0) {
				    k=i; //0-t levels
				    break;
			    }			
		    }
				
	    }
	    return bestWord;	
	}
	/**
	 * Returns the hamming distance between two Strings of the same length
	 * @param x
	 * @param y
	 * @return The hamming distance between x and y
	 */
	public static int hd(String x, String y){
		int distance=0;
		for (int j=0;j<x.length();j++ ) {
			if (x.substring(j,j+1).equals(y.substring(j,j+1))) {
				distance=distance+0;
			}else{
				distance=distance+1;
			}
		}
		return distance;
	}
	
	/**
	 * Let l = v.length()
	 * Returns the minimum hamming distance from v across all Strings of length l in dna 
	 * @param v The candidate string
	 * @param dna A String representing a collection of DNA sequences
	 * @return for all substrings s in dna, the minimum hd(v,s)
	 */
	public static int totalDistance(String v, String dna){
		String[] dnaArray =new String[]{};
		dnaArray=dna.split("\n");
		List<String> dnalist = java.util.Arrays.asList(dnaArray);      //DNA lists
		int t=dnalist.size();                    //the number of DNA sequences
		int totalDistance=0;
		for (int p=0;p<t; p++) {
			int distance=1000000;
			String dnas=dnalist.get(p);
					for (int q=0;q<(dnas.length()-v.length()+1);q++ ) {
						int stringdist=hd(v,dnas.substring(q,(q+v.length())));
						if (stringdist<distance) {
							distance=stringdist;
							if (distance==0) {
								break;
							}
						}
					}
			totalDistance=totalDistance+distance;		
		}
		//System.out.println(v);
		//System.out.println(totalDistance);
		return totalDistance;
	}

}

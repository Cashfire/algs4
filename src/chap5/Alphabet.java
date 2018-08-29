package chap5;

public class Alphabet {
    // The DNA alphabet { A, C, T, G }.
    public static final Alphabet DNA = new Alphabet("ACGT");
	public static final Alphabet LOWERCASE = new Alphabet("abcdefghijklmnopqrstuvwxyz");
	private char[] alphabet;
	private int[] inverse; //indices
	private final int R; //the radix
	private Alphabet(String s){
		boolean[] unicode = new boolean[Character.MAX_VALUE];
		//check that the input string contains no duplicate chars.
		for(int i = 0; i < s.length(); i++){
			char c = s.charAt(i);
			if(unicode[c]){
				throw new IllegalArgumentException("illegal string: repeated character = '"+c+"'");
			}
			unicode[c] = true;
		}
		alphabet = s.toCharArray();
		R = s.length();
		inverse = new int[Character.MAX_VALUE];
		for(int i = 0; i < inverse.length; i++){
			inverse[i] = -1;
		}
		//can't use char since R can be as big as 65,536
		for(int c = 0; c < R; c++){
			
			inverse[alphabet[c]] = c;
		}
	}
	private Alphabet(int radix){
		this.R = radix;
		alphabet = new char[R];
		inverse = new int[R];
		//can't use char since R can be as big as 65,536????
		for(int i = 0; i < R; i++){
			alphabet[i] = (char) i;
		}
		for(int i = 0; i < R; i++){
			inverse[i] = i;
		}
	}
	public Alphabet(){ this(256); }
	
	public int toIndex(char c){
		if(c >= inverse.length || inverse[c]== -1){
			throw new IllegalArgumentException("Character "+c+" not in alphabet");
		}
		return inverse[c];
	}
	public int[] toIndices(String s){
		char[] source = s.toCharArray();
		int[] target = new int[s.length()];
		for(int i = 0; i < source.length; i++){
			target[i] = toIndex(source[i]);
		}
		return target;
	}
	public char toChar(int index){
		if(index < 0 || index >= R){
			throw new IllegalArgumentException("index must be between 0 and "+R+" : "+index);
		}
		return alphabet[index];
	}
	public String toChars(int[] indices){
		StringBuilder str= new StringBuilder(indices.length);
		for(int i = 0; i < indices.length; i++){
			str.append(toChar(indices[i]));
		}
		return str.toString();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] encoded1 = Alphabet.LOWERCASE.toIndices("nowitismytimetosleep");
		String decode1 = Alphabet.LOWERCASE.toChars(encoded1);
		System.out.println(decode1);
		int[] encoded2 = Alphabet.DNA.toIndices("AACGAACGGTTTACCCCG");
		String decode2 = Alphabet.DNA.toChars(encoded2);
		System.out.println(decode2);
		
	}

}














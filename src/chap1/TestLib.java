package chap1;

import edu.princeton.cs.algs4.StdIn;

public class TestLib {
	public static void main(String[] args) {
		while (!StdIn.isEmpty()) {
			String item = StdIn.readString();
			System.out.println(item);
		}
	}
}

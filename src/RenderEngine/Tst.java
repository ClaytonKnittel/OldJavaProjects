package RenderEngine;

import java.util.EnumSet;

public class Tst {
	
	public static void main(String args[]) {
		for (Tuna p: Tuna.values()) {
			System.out.printf("%s\t%s\t%s\t", p, p.getDescription(), p.getYear());
		}
		
		System.out.println("\n\nAnd now for the range of constants");
		
		for (Tuna p: EnumSet.range(Tuna.kelsey, Tuna.julia)) {
			System.out.printf("%s\t%s\t%s\t", p, p.getDescription(), p.getYear());
		}
	}
}

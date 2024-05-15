package primerasPruebas;

import java.util.Scanner;

public class PrimeraClase {

	public static void main(String[] args) {
		System.out.println("Hola Mundo");
		Scanner sc = new Scanner(System.in);
		String variable = "";
		int variableEntera = 0;
		boolean comprobacion = false;
		System.out.println("Dame algo por consola:\n");
		while (!sc.hasNextInt()) {
			System.out.println("introduce un entero");
			sc.nextLine();
		}
		variableEntera = sc.nextInt();
		System.out.println("variable entera = "+variableEntera);
		System.out.println("Fin Programa");
	}

}

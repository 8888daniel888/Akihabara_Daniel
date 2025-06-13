package productos.view;

import java.util.Scanner;

public class Utilidades {
	public static int leerInt(String mensaje) {
		Scanner scan = new Scanner(System.in);
		int valor = 0;
		boolean error = false;
		do {
			error = false;
			System.out.print(mensaje);
			try {
				valor = Integer.parseInt(scan.nextLine());
			}catch (Exception e) {
				System.out.println("[ERROR] Valor incorrecto");
				error = true;
			}
		}while(error);
		return valor;
	}
	
	public static int leerTipo(String mensaje, int min, int max) {
		Scanner scan = new Scanner(System.in);
		int valor = 0;
		boolean error = false;
		do {
			error = false;
			System.out.print(mensaje);
			try {
				valor = Integer.parseInt(scan.nextLine());
				if (valor < min || valor > max) {
					System.out.println("[ERROR] Valor incorrecto");
					error = true;
				}
			}catch (Exception e) {
				System.out.println("[ERROR] Valor incorrecto");
				error = true;
			}
		}while(error);
		return valor;
	}
	
	public static double leerDouble(String mensaje) {
		Scanner scan = new Scanner(System.in);
		double valor = 0;
		boolean error = false;
		do {
			error = false;
			System.out.print(mensaje);
			try {
				valor = Double.parseDouble(scan.nextLine());
			}catch (Exception e) {
				System.out.println("[ERROR] Valor incorrecto");
				error = true;
			}
		}while(error);
		return valor;
	}
	
	public static String leerString(String mensaje) {
		Scanner scan = new Scanner(System.in);
		String valor;
		boolean error = false;
		 do {
		        error = false;
		        System.out.print(mensaje);
		        valor = scan.nextLine().trim();

		        if (valor.isEmpty()) {
		            System.out.println("[ERROR] El valor no puede estar vacío.");
		            error = true;
		        }
		    } while (error);
		    return valor;
		}
	
	public static boolean leerBoolean(String mensaje) {
		Scanner scan = new Scanner(System.in);
		boolean valor = false;
		boolean error = false;
		do {
			error = false;
			System.out.print(mensaje + "(sí/no): ");
			String entrada = scan.nextLine().trim().toLowerCase();

	        if (entrada.equals("si") || entrada.equals("sí") || entrada.equals("s") || entrada.equals("true")) {
	            valor = true;
	        } else if (entrada.equals("no") || entrada.equals("n") || entrada.equals("false")) {
	            valor = false;
	        } else {
	            System.out.println("[ERROR] Valor no válido. Escriba sí o no.");
	            error = true;
	        }
	    } while (error);
	    return valor;
	}
	
}

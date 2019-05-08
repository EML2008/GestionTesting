package core;

import java.util.Scanner;

public class GestorArchivo {

	public GestorArchivo(String ruta) {
		Scanner entrada = new Scanner(ruta);
		System.out.println(entrada.next());
		entrada.close();
	}
	

}

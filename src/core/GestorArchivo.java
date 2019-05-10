package core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class GestorArchivo {

	List<String> texto = new LinkedList<String>();
	
	public GestorArchivo(String ruta) {
		Scanner sc;
		try {
			sc = new Scanner(new File(ruta));
			while(sc.hasNextLine()) {
				this.texto.add(sc.nextLine());
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Buscar las clases en el archivo
	 */
	public String findClass() {
		
		for (int i = 0; i < texto.size(); i++) {
			System.out.println("line " + i + " : " + texto.get(i));			
		}
		return texto.get(0);
	}
	
	/**
	 * Busca los metodos de la clase
	 * @param clase
	 */
	public void findMethod(String clase) {
		
	}

}

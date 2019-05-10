package core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class GestorArchivo {

	List<String> texto = new LinkedList<String>();
	String clase = "";
	private static final String CLASS = "class ";
	private static final String KEY_CLOSE = " {";
	private static final String CLASS_EXTENDS = " extends ";
	private static final String CLASS_IMPLEMENTS = " implements ";

	public GestorArchivo(String ruta) {
		Scanner sc;
		try {
			sc = new Scanner(new File(ruta));
			while (sc.hasNextLine()) {
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
			if (texto.get(i).lastIndexOf(CLASS) > 0) {
				String inicioClase = texto.get(i).substring(
						texto.get(i).lastIndexOf(CLASS));

				if (inicioClase.lastIndexOf(KEY_CLOSE) > 0) {

					this.clase = inicioClase.substring(CLASS.length(),
							inicioClase.lastIndexOf(KEY_CLOSE));

					if (inicioClase.lastIndexOf(CLASS_EXTENDS) > 0) {
						this.clase = inicioClase.substring(CLASS.length(),
								inicioClase.lastIndexOf(CLASS_EXTENDS));

					} else {

						if (inicioClase.lastIndexOf(CLASS_IMPLEMENTS) > 0) {
							this.clase = inicioClase.substring(CLASS.length(),
									inicioClase.lastIndexOf(CLASS_IMPLEMENTS));
						}
					}
				}

			}
		}
		return this.clase;
	}

	/**
	 * Busca los metodos de la clase
	 * 
	 * @param clase
	 */
	public void findMethod(String clase) {

	}

}

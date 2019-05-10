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
	private static final String KEY_OPEN = " {";
	private static final String CLASS_EXTENDS = " extends ";
	private static final String CLASS_IMPLEMENTS = " implements ";
	/**
	 * como deberia cerrar un metodo, si lo cierra sin espacio no es programador.
	 */
	private static final String PARENTHESIS_KEY_OPEN = ") {";

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

		String inicioClase;
		for (int i = 0; i < texto.size(); i++) {
			if (texto.get(i).lastIndexOf(CLASS) > 0) {
				inicioClase = texto.get(i).substring(
						texto.get(i).lastIndexOf(CLASS));

				if (inicioClase.lastIndexOf(KEY_OPEN) > 0) {

					this.clase = inicioClase.substring(CLASS.length(),
							inicioClase.lastIndexOf(KEY_OPEN));

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
	public LinkedList<String> findMethods(String clase) {
		LinkedList<String> methods = new LinkedList<String>();
		String inicioMetodo;
		for (int i = 0; i < texto.size(); i++) {
			if (texto.get(i).lastIndexOf(PARENTHESIS_KEY_OPEN) > 0) {
				inicioMetodo = texto.get(i).substring(0,
						texto.get(i).lastIndexOf(PARENTHESIS_KEY_OPEN));

				String reemplazo[] = inicioMetodo.split(" ");
				for (int j = 0; j < reemplazo.length; j++) {
					if (reemplazo[j].matches("\\w+\\(\\w*")) {
						
						methods.add(reemplazo[j].substring(0, reemplazo[j].indexOf("(")));
					}
				}
			}
		}
		return methods;
	}

}

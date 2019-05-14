package core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestorArchivo {

	List<String> texto = new ArrayList<String>();
	private List<Clase> classes = new ArrayList<Clase>();
	private static final String CLASS = "class ";
	private static final String CLASS_EXTENDS = " extends ";
	private static final String CLASS_IMPLEMENTS = " implements ";
	private static final String CLASS_KEY_OPEN = " {";
	private static final String KEY_CLOSE = "}";
	private static final String KEY_OPEN = "{";

	private static final String ASTERISCO = "*";
	private static final String DOBLE_BARRA = "//";

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

	public List<String> getTexto() {
		return texto;
	}

	/**
	 * Buscar las clases en el archivo
	 */
	public List<Clase> findClass() {

		String inicioClase;
		String clase = null;
		int llaves_abiertas = 0;
		ArrayList<String> bufferClass = new ArrayList<String>();
		boolean dentroClase = false;
		for (int i = 0; i < texto.size(); i++) {
			if (dentroClase == false && texto.get(i).lastIndexOf(CLASS) != -1) {
				inicioClase = texto.get(i).substring(
						texto.get(i).lastIndexOf(CLASS));

				if (inicioClase.lastIndexOf(CLASS_KEY_OPEN) != -1 || (texto.size() >= i + 1 && texto.get(i + 1).lastIndexOf(KEY_OPEN) != -1)) {
					
					dentroClase = true;
					if (inicioClase.lastIndexOf(CLASS_KEY_OPEN) != -1) {
						// porque puede ser que cierre en el otro renglon
						clase = inicioClase.substring(CLASS.length(), inicioClase.lastIndexOf(CLASS_KEY_OPEN));
					}

					if (inicioClase.lastIndexOf(CLASS_EXTENDS) != -1) {
						clase = inicioClase.substring(CLASS.length(),
								inicioClase.lastIndexOf(CLASS_EXTENDS));

					} else {
						if (inicioClase.lastIndexOf(CLASS_IMPLEMENTS) != -1) {
							clase = inicioClase.substring(CLASS.length(),
									inicioClase.lastIndexOf(CLASS_IMPLEMENTS));
						}
					}
					bufferClass.add(texto.get(i));
					continue;
				}

			}
			if (dentroClase) {
				bufferClass.add(texto.get(i));
			}
			
			if (dentroClase && texto.get(i).indexOf(CLASS_KEY_OPEN) >= 0 ) {
				llaves_abiertas++;
			} else {
				if (dentroClase && texto.get(i).indexOf(KEY_OPEN) >= 0) {
					llaves_abiertas++;
				}
			}

			if (dentroClase && texto.get(i).indexOf(KEY_CLOSE) >= 0) {
				llaves_abiertas--;
			}
			if (dentroClase && llaves_abiertas == 0) {
				classes.add(new Clase(clase, bufferClass));
				bufferClass = new ArrayList<String>();
				dentroClase = false;
			}
		}
		return this.classes;
	}

	/**
	 * Cuenta las lineas comentadas
	 * 
	 * @return
	 */
	public int lineasComentadas() {
		int lineasComentadas = 0;
		for (int i = 0; i < texto.size(); i++) {
			if (texto.get(i).indexOf(ASTERISCO) != -1) {
				lineasComentadas++;
			}
			if (texto.get(i).indexOf(DOBLE_BARRA) != -1) {
				lineasComentadas++;
			}
		}
		return lineasComentadas;
	}

	public List<Clase> getClasses() {
		return classes;
	}

}

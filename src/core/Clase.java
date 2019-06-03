package core;

import java.util.ArrayList;
import java.util.List;

public class Clase {

	private String nombre = "";
	private ArrayList<String> texto = new ArrayList<String>();
	private ArrayList<Clase> otrasClasesEnElArchivo = new ArrayList<Clase>();

	public Clase(String nombre, ArrayList<String> texto) {
		super();
		this.nombre = nombre;
		this.texto = texto;
	}

	public String getNombre() {
		return nombre;
	}

	public ArrayList<String> getTexto() {
		return texto;
	}

	public void setOtrasClasesEnElArchivo(List<Clase> otrasClasesEnElArchivo) {
		this.otrasClasesEnElArchivo = (ArrayList<Clase>) otrasClasesEnElArchivo;
	}

	/**
	 * Busca los metodos de la clase
	 * 
	 * @param dentro_metodo == false && clase
	 */
	public ArrayList<Metodo> findMethods() {
		ArrayList<Metodo> methods = new ArrayList<Metodo>();
		String inicioMetodo;
		String metodo = null;
		int llaves_abiertas = 0;
		ArrayList<String> bufferMethod = new ArrayList<String>();
		boolean dentro_metodo = false;

		for (int i = 0; i < texto.size(); i++) {
			if (dentro_metodo == false && texto.get(i).indexOf(Constantes.PARENTHESIS_KEY_OPEN) >= 0) {
				inicioMetodo = texto.get(i).substring(0, texto.get(i).indexOf(Constantes.PARENTHESIS_KEY_OPEN));

				String reemplazo[] = inicioMetodo.split(" ");
				for (int j = 0; j < reemplazo.length; j++) {

					if (reemplazo[j].matches("\\w+\\(.*")) {
						dentro_metodo = true;
						metodo = reemplazo[j].substring(0, reemplazo[j].indexOf("("));
					}
				}
			}
			if (dentro_metodo) {
				bufferMethod.add(texto.get(i));
			}
			if (dentro_metodo && texto.get(i).indexOf(Constantes.KEY_OPEN) >= 0) {
				llaves_abiertas++;
			}

			if (dentro_metodo && texto.get(i).indexOf(Constantes.KEY_CLOSE) >= 0) {
				llaves_abiertas--;
			}
			if (dentro_metodo && llaves_abiertas == 0) {
				methods.add(new Metodo(metodo, bufferMethod));
				bufferMethod = new ArrayList<String>();
				dentro_metodo = false;
			}
		}
		for (Metodo m : methods) {
			m.setOtrosMetodosDeLaClase(methods);
			m.setOtrasClasesEnElArchivo(this.otrasClasesEnElArchivo);
		}
		return methods;
	}

	/**
	 * Cuenta las lineas comentadas
	 * 
	 * @return
	 */
	public int lineasComentadas() {
		int lineasComentadas = 0;
		for (int i = 0; i < texto.size(); i++) {
			if (texto.get(i).indexOf(Constantes.ASTERISCO) != -1) {
				lineasComentadas++;
			}
			if (texto.get(i).indexOf(Constantes.DOBLE_BARRA) != -1) {
				lineasComentadas++;
			}
		}
		return lineasComentadas;
	}

	@Override
	public String toString() {
		return "Clase [nombre=" + nombre + "]";
	}
}

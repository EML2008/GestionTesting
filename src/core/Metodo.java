package core;

import java.util.LinkedList;

public class Metodo {

	private static final String IF = "if ";
	private static final String AND = "&&";
	private static final String OR = "||";
	private String nombre = "";
	private LinkedList<String> texto = new LinkedList<String>();

	public Metodo(String nombre, LinkedList<String> texto) {
		super();
		this.nombre = nombre;
		this.texto = texto;
	}

	public String getNombre() {
		return nombre;
	}

	public LinkedList<String> getTexto() {
		return texto;
	}

	/**
	 * Pienso que contar la cantiada de if y dentro de cada if ver si tiene OR o AND
	 * y hacer la cuenta.
	 * 
	 * @return
	 */
	public int predicados() {
		int predicados = 0;
		int fromIndex = 0;
		for (int i = 0; i < texto.size(); i++) {

			fromIndex = 0;
			while ((fromIndex = texto.get(i).indexOf(IF, fromIndex)) != -1) {
				predicados++;
				fromIndex++;
			}
			fromIndex = 0;
			while ((fromIndex = texto.get(i).indexOf(AND, fromIndex)) != -1) {
				predicados++;
				fromIndex++;
			}
			fromIndex = 0;
			while ((fromIndex = texto.get(i).indexOf(OR, fromIndex)) != -1) {
				predicados++;
				fromIndex++;
			}
		}

		return predicados;
	}

	@Override
	public String toString() {
		return "Metodo [nombre=" + nombre + "]";
	}
}

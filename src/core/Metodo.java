package core;

import java.util.LinkedList;

public class Metodo {

	private static final String IF = "if ";
	private static final String AND = "&&";
	private static final String OR = "||";
	private String nombre = "";
	private LinkedList<String> texto = new LinkedList<String>();
	private int cantidadIf = 0;

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
	public LinkedList<String> findIf() {
		int count = 0;
		for (int i = 0; i < texto.size(); i++) {
			if (texto.get(i).indexOf(IF) >= 0) {
				cantidadIf++;

				int fromIndex = texto.get(i).indexOf(IF);
				while ((fromIndex = texto.get(i).indexOf(AND, fromIndex)) != -1) {
					count++;
					fromIndex++;
				}
				fromIndex = texto.get(i).indexOf(IF);
				while ((fromIndex = texto.get(i).indexOf(OR, fromIndex)) != -1) {
					count++;
					fromIndex++;
				}
				System.out.println("tiene " + count + " and");
			}
		}
		
		if(count > 0) {
			
		}

		System.out.println("hay " + cantidadIf + " if");
		return null;
	}

	@Override
	public String toString() {
		return "Metodo [nombre=" + nombre + "]";
	}
}

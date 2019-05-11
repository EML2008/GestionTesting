package core;

import java.util.Iterator;
import java.util.LinkedList;

public class Metodo {
	//TODO: El puma va a reemplazar todo por sin espacio y tiene que buscar donde se usa
	//TODO: hacer un promedio de
	private static final String IF = "if(";
	private static final String IF_CON_ESPACIO = "if (";
	private static final String WHILE = "while(";
	private static final String WHILE_CON_ESPACIO = "while (";
	private static final String FOR = "for(";
	private static final String FOR_CON_ESPACIO = "for (";
	private static final String AND = "&&";
	private static final String OR = "||";
	private static final String TERNARIO = "?";
	private static final String ASTERISCO = "*";
	private static final String DOBLE_BARRA = "//";
	
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
		for (int i = 0; i < texto.size(); i++) {
			// TODO: alta complejidad hermano
			predicados += contarPalabrasClave(i, IF);
			predicados += contarPalabrasClave(i, IF_CON_ESPACIO);
			predicados += contarPalabrasClave(i, WHILE);
			predicados += contarPalabrasClave(i, WHILE_CON_ESPACIO);
			predicados += contarPalabrasClave(i, FOR);
			predicados += contarPalabrasClave(i, FOR_CON_ESPACIO);
			predicados += contarPalabrasClave(i, AND);
			predicados += contarPalabrasClave(i, OR);
			predicados += contarPalabrasClave(i, TERNARIO);
		}
		return predicados;
	}

	private int contarPalabrasClave(int i, final String keyword) {
		int fromIndex = 0;
		int predicados = 0;
		while ((fromIndex = texto.get(i).indexOf(keyword, fromIndex)) != -1) {
			predicados++;
			fromIndex++;
		}
		return predicados;
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

	@Override
	public String toString() {
		String ret = "";
		for (Iterator iterator = texto.iterator(); iterator.hasNext();) {
			ret += (String) iterator.next() + "\n";
			
		}
		return ret;
	}

}

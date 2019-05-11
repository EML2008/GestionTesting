package core;

import java.util.LinkedList;

public class Metodo {
	// TODO: El puma va a reemplazar todo por sin espacio y tiene que buscar donde
	// se usa
	// TODO: hacer un promedio de

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
			predicados += contarPalabrasClave(i, Constantes.IF);
			predicados += contarPalabrasClave(i, Constantes.IF_CON_ESPACIO);
			predicados -= contarPalabrasClave(i, Constantes.IF_FALSO);
			predicados += contarPalabrasClave(i, Constantes.WHILE);
			predicados += contarPalabrasClave(i, Constantes.WHILE_CON_ESPACIO);
			predicados += contarPalabrasClave(i, Constantes.FOR);
			predicados += contarPalabrasClave(i, Constantes.FOR_CON_ESPACIO);
			predicados += contarPalabrasClave(i, Constantes.AND);
			predicados += contarPalabrasClave(i, Constantes.OR);
			predicados += contarPalabrasClave(i, Constantes.TERNARIO);
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
		String ret = "";
		for (int i = 0; i < texto.size(); i++) {
			ret += (i + 1) + " " + texto.get(i) + "\n";

		}
		return ret;
	}

	public String toHtml() {
		String ret = "";
		for (int i = 0; i < texto.size(); i++) {
			ret += (i + 1) + " " + texto.get(i) + "\n";

		}
		ret = ret.replace(Constantes.IF, "<font color=\"red\">" + Constantes.IF + "</font>");
		// OJO con el falso if()
		ret = ret.replace("<font color=\"red\">" + Constantes.IF + "</font>)", "<font color=\"#58FF33\">" + Constantes.IF + "</font>)");
		ret = ret.replace(Constantes.IF_CON_ESPACIO, "<font color=\"red\">" + Constantes.IF_CON_ESPACIO + "</font>");
		ret = ret.replace(Constantes.FOR, "<font color=\"red\">" + Constantes.FOR + "</font>");
		ret = ret.replace(Constantes.FOR_CON_ESPACIO, "<font color=\"red\">" + Constantes.FOR_CON_ESPACIO + "</font>");
		ret = ret.replace(Constantes.WHILE, "<font color=\"red\">" + Constantes.WHILE + "</font>");
		ret = ret.replace(Constantes.WHILE_CON_ESPACIO,
				"<font color=\"red\">" + Constantes.WHILE_CON_ESPACIO + "</font>");
		ret = ret.replace(Constantes.AND, "<font color=\"red\">" + Constantes.AND + "</font>");
		ret = ret.replace(Constantes.OR, "<font color=\"red\">" + Constantes.OR + "</font>");
		ret = ret.replace(Constantes.TERNARIO, "<font color=\"red\">" + Constantes.TERNARIO + "</font>");

		ret = ret.replace("\n", "<br>");
		ret = ret.replace("\t", "&nbsp;&nbsp;");
		return "<html><font color=\"#58FF33\">" + ret + "</font></html>";
	}

}

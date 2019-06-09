package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Metodo {
	private String nombre = "";
	private ArrayList<String> texto = new ArrayList<String>();
	private int operandosEncontradosTotales = 0;
	private int operadoresEncontradosTotales = 0;
	private int operandosEncontradosUnicos = 0;
	private int operadoresEncontradosUnicos = 0;
	private HashMap<String, Integer> operandos = new HashMap<String, Integer>();
	private HashMap<String, Integer> operadores = new HashMap<String, Integer>();
	private ArrayList<Metodo> otrosMetodosDeLaClase = new ArrayList<Metodo>();
	private ArrayList<Clase> otrasClasesEnElArchivo = new ArrayList<Clase>();

	public Metodo(String nombre, ArrayList<String> texto) {
		this.nombre = nombre;
		this.texto = texto;
	}

	public String getNombre() {
		return nombre;
	}

	public ArrayList<String> getTexto() {
		return texto;
	}

	public void setOtrasClasesEnElArchivo(ArrayList<Clase> otrasClasesEnElArchivo) {
		this.otrasClasesEnElArchivo = otrasClasesEnElArchivo;
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

	public int contarOperandos() {
		this.operandosEncontradosTotales = 0;
		boolean esPalabraClave;
		boolean esPalabraOperador;
		String palabraActual;
		for (String linea : this.texto) {

			String[] palabras = linea.replaceAll("\"[a-zA-Z0-9| |\\:|\\;]+\"", " ")
					.replaceAll("[\\.]*[A-Za-z0-9]+\\(", " ").replaceAll("[a-zA-Z0-9]+\\(\\)", " ")
					.split("[ \t\\(\\)\\[\\]\\{\\}\\,\\.\\;\\+\\-\\=]+");

			for (int i = 0; i < palabras.length; i++) {
				esPalabraClave = false;
				esPalabraOperador = false;
				palabraActual = palabras[i];
				for (int j = 0; j < Constantes.PALABRAS_RESERVADAS.length; j++) {
					if (palabraActual.trim().equals(Constantes.PALABRAS_RESERVADAS[j])) {
						esPalabraClave = true;
						break;
					}
				}
				if (!esPalabraClave) {
					for (int j = 0; j < Constantes.PALABRAS_OPERADORES.length; j++) {
						if (palabraActual.trim().equals(Constantes.PALABRAS_OPERADORES[j])) {
							esPalabraOperador = true;
							break;
						}
					}
				}
				for (Metodo m : this.otrosMetodosDeLaClase) {
					if (m.getNombre().equals(palabraActual))
						esPalabraClave = true;
				}

				for (Clase c : this.otrasClasesEnElArchivo) {
					if (c.getNombre().equals(palabraActual))
						esPalabraClave = true;
				}

				if (!esPalabraClave && !esPalabraOperador && !palabraActual.trim().equals("")) {
					this.operandosEncontradosTotales++;
					if (this.operandos.containsKey(palabraActual)) {
						this.operandos.put(palabraActual, this.operandos.get(palabraActual) + 1);
					} else {
						this.operandos.put(palabraActual, Integer.valueOf(0));
					}
				}
			}
		}
//		System.out.println("Claves: " + this.operandos.keySet().size());
//		System.out.println("Claves: " + this.operandos.keySet());
//		System.out.println(this.operandos.values().size());
//		System.out.println(this.operandosEncontradosTotales);
		this.operandosEncontradosUnicos = this.operandos.keySet().size();
		return this.operandosEncontradosTotales;
	}

	public int contarOperadores() {
		this.operadoresEncontradosTotales = 0;
		boolean esPalabraOperador = false;
		String palabraActual = "";
		for (String linea : this.texto) {

			String[] palabras = linea.split("[ \t\\(\\)\\[\\]\\{\\}\\,\\.\\;]+");

			for (int i = 0; i < palabras.length; i++) {
				esPalabraOperador = false;
				palabraActual = palabras[i];
				for (int j = 0; j < Constantes.PALABRAS_OPERADORES.length; j++) {
					if (palabraActual.trim().equals(Constantes.PALABRAS_OPERADORES[j])) {
						esPalabraOperador = true;
						break;
					}
				}

				if (esPalabraOperador) {
					this.operadoresEncontradosTotales++;
					if (this.operadores.containsKey(palabraActual)) {
						this.operadores.put(palabraActual, this.operadores.get(palabraActual) + 1);
					} else {
						this.operadores.put(palabraActual, Integer.valueOf(0));
					}
				}
			}
		}

//		System.out.println(operadoresEncontradosTotales);
//		System.out.println(this.operadores.keySet().size());
//		System.out.println("Claves" + this.operadores.keySet());
		this.operadoresEncontradosUnicos = this.operadores.keySet().size();
		return this.operadoresEncontradosTotales;
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
//		String ret = "";
//		for (int i = 0; i < texto.size(); i++) {
//			ret += (i + 1) + " " + texto.get(i) + "\n";
//
//		}
//		ret = ret.replace(Constantes.IF, "<font color=\"red\">" + Constantes.IF + "</font>");
//		// OJO con el falso if()
//		ret = ret.replace("<font color=\"red\">" + Constantes.IF + "</font>)",
//				"<font color=\"#58FF33\">" + Constantes.IF + "</font>)");
//		ret = ret.replace(Constantes.IF_CON_ESPACIO, "<font color=\"red\">" + Constantes.IF_CON_ESPACIO + "</font>");
//		ret = ret.replace(Constantes.FOR, "<font color=\"red\">" + Constantes.FOR + "</font>");
//		ret = ret.replace(Constantes.FOR_CON_ESPACIO, "<font color=\"red\">" + Constantes.FOR_CON_ESPACIO + "</font>");
//		ret = ret.replace(Constantes.WHILE, "<font color=\"red\">" + Constantes.WHILE + "</font>");
//		ret = ret.replace(Constantes.WHILE_CON_ESPACIO,
//				"<font color=\"red\">" + Constantes.WHILE_CON_ESPACIO + "</font>");
//		ret = ret.replace(Constantes.AND, "<font color=\"red\">" + Constantes.AND + "</font>");
//		ret = ret.replace(Constantes.OR, "<font color=\"red\">" + Constantes.OR + "</font>");
//		ret = ret.replace(Constantes.TERNARIO, "<font color=\"red\">" + Constantes.TERNARIO + "</font>");
//
//		ret = ret.replace("\n", "<br>");
//		ret = ret.replace("\t", "&nbsp;&nbsp;");
//		return "<html><font color=\"#58FF33\">" + ret + "</font></html>";
		String ret = "";
		String lineaAuxiliar[];
		boolean pintarLinea = false;
		int contadorDeLinea = 1;
		for (String lineaActual : this.texto) {
			pintarLinea = false;
			lineaAuxiliar = lineaActual.split("[ \t\\(\\)\\[\\]\\{\\}\\,\\.\\;]+");
			for (int i = 0; i < lineaAuxiliar.length; i++) {
				if (lineaAuxiliar[i].toUpperCase().equals("IF") || lineaAuxiliar[i].toUpperCase().equals("FOR")
						|| lineaAuxiliar[i].toUpperCase().equals("WHILE")) {
					pintarLinea = true;
					break;
				}
			}
			ret += contadorDeLinea + " ";
			if (pintarLinea) {
				ret += "<font color=\"red\">" + lineaActual + "</font>" + "\n";
			} else {
				ret += lineaActual + "\n";
			}
//			ret += ret + "<br>";
			contadorDeLinea++;
		}
		ret = ret.replace("\n", "<br>").replace("\t", "&nbsp;&nbsp;");
		return "<html><font color=\"#58FF33\">" + ret + "</font></html>";
	}

	public Double getLongitud() {

		return (double) (this.operadoresEncontradosTotales + this.operandosEncontradosTotales);
	}

	public Double getVolumen() {

		return this.getLongitud()
				* (Math.log(this.operandosEncontradosUnicos + this.operadoresEncontradosUnicos) / Math.log(2));
	}

	public Set<String> getOperadores() {
		return this.operadores.keySet();
	}

	public Set<String> getOperandos() {
		return this.operandos.keySet();
	}

	public void setOtrosMetodosDeLaClase(ArrayList<Metodo> otrosMetodosDeLaClase) {
		this.otrosMetodosDeLaClase = otrosMetodosDeLaClase;
	}

	public int calcularFanOut() {
		int fanOut = 0;
		String palabraActual = "";
		for (String linea : this.texto) {

			String[] palabras = linea.split("[ \t\\(\\)\\[\\]\\{\\}\\,\\.\\;]+");

			for (int i = 0; i < palabras.length; i++) {
				palabraActual = palabras[i];
				for (Metodo m : this.otrosMetodosDeLaClase) {
					if (m.getNombre().equals(palabraActual) && !m.getNombre().equals(this.nombre)) {
						fanOut++;
//						System.out.println(palabraActual);
						break;
					}
				}
			}
		}
		return fanOut;
	}

	public int calcularCantidadDeVecesQueSeUsaUnMetodo(Metodo m) {
		int fanOut = 0;
		String palabraActual = "";
		for (String linea : this.texto) {

			String[] palabras = linea.split("[ \t\\(\\)\\[\\]\\{\\}\\,\\.\\;]+");

			for (int i = 0; i < palabras.length; i++) {
				palabraActual = palabras[i];
				if (palabraActual.equals(m.getNombre())) {
					fanOut++;
				}
			}
		}
		return fanOut;
	}
}

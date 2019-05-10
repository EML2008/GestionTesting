package core;

import java.util.LinkedList;

public class Metodo {

	private String nombre = "";
	private LinkedList<String> texto = new LinkedList<String>();
	
	private static final String IF = "if ";
	private static final String ELSE = "else ";
	private static final String ELSEIF = "elseif ";
	
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
	
	public LinkedList<String> findIf() {
		for (int i = 0; i < texto.size(); i++) {
			if (texto.get(i).indexOf(IF) >= 0) {
				System.out.println("tiene if " + texto.get(i));
			}
			
			if (texto.get(i).indexOf(ELSE) >= 0) {
				System.out.println("tiene else " + texto.get(i));
			}
			
			if (texto.get(i).indexOf(ELSEIF) >= 0) {
				System.out.println("tiene elseif " + texto.get(i));
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "Metodo [nombre=" + nombre + "]";
	}
}

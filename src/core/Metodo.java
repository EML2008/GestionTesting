package core;

import java.util.LinkedList;

public class Metodo {

	private static final String IF = "if ";
	private static final String ELSE = "else ";
	private static final String ELSEIF = "elseif ";
	private static final String AND = "and ";
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
	 * Pienso que contar la cantiada de if y dentro de cada if ver si tiene OR o AND y hacer la cuenta.
	 * 
	 * @return
	 */
	public LinkedList<String> findIf() {
		for (int i = 0; i < texto.size(); i++) {
			if (texto.get(i).indexOf(IF) >= 0) {
				cantidadIf++;
				System.out.println("tiene if " + texto.get(i));
				
				int fromIndex = 0;
				int count = 0;
				 while ((fromIndex = texto.get(i).indexOf(IF, fromIndex)) != -1 ){
			            System.out.println("Found at index: " + fromIndex);
			            count++;
					fromIndex++;

				}
				 
				if (texto.get(i).indexOf(AND) >= 0) {
					System.out.println("tiene and " + texto.get(i));
					
				}
			}
			
			if (texto.get(i).indexOf(ELSE) >= 0) {
				System.out.println("tiene else " + texto.get(i));
			}
			
			if (texto.get(i).indexOf(ELSEIF) >= 0) {
				System.out.println("tiene elseif " + texto.get(i));
			}
		}
		
		System.out.println("hay " + cantidadIf + " if");
		return null;
	}

	@Override
	public String toString() {
		return "Metodo [nombre=" + nombre + "]";
	}
}

package core;

public class Metodo {

	private String nombre = "";
	private String texto = "";
	
	public Metodo(String nombre, String texto) {
		super();
		this.nombre = nombre;
		this.texto = texto;
	}

	public String getNombre() {
		return nombre;
	}

	public String getTexto() {
		return texto;
	}

	@Override
	public String toString() {
		return "Metodo [nombre=" + nombre + "]";
	}
}

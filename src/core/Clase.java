package core;

public class Clase {

	private String nombre = "";
	private String texto = "";

	public Clase(String nombre, String texto) {
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
		return "Clase [nombre=" + nombre + "]";
	}
}

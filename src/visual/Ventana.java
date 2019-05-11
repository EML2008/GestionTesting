package visual;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

import core.Clase;
import core.GestorArchivo;
import core.Metodo;

public class Ventana extends JFrame {

	private static final long serialVersionUID = -1838218182953168733L;
	private static JComboBox<String> comboBoxClase;
	private static JComboBox<String> comboBoxMetodo;
	private List<Clase> clases;
	private JTextPane textArea;
	private LinkedList<Metodo> metodos;
	private JTextPane textLineasComentadas;
	private JTextPane textPredicados;
	private JTextPane textLineasComentadasTotales;
	private JTextPane textLineasComentadasClase;
	private JTextPane textLineasTotales;
	
	public Ventana() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JButton btnSeleccionarArchivo = new JButton("Seleccionar Archivo");
		btnSeleccionarArchivo.setBounds(10, 11, 151, 23);
		getContentPane().add(btnSeleccionarArchivo);

		comboBoxClase = new JComboBox<String>();
		comboBoxClase.setBounds(10, 74, 151, 22);
		getContentPane().add(comboBoxClase);

		comboBoxMetodo = new JComboBox<String>();
		comboBoxMetodo.setBounds(175, 74, 151, 22);
		getContentPane().add(comboBoxMetodo);

		JLabel lblClase = new JLabel("Clases");
		lblClase.setBounds(10, 49, 48, 14);
		getContentPane().add(lblClase);

		JLabel lblMetodo = new JLabel("Metodos");
		lblMetodo.setBounds(175, 49, 92, 14);
		getContentPane().add(lblMetodo);

		textArea = new JTextPane();
		textArea.setEditable(false);
		textArea.setBounds(10, 107, 316, 143);
		getContentPane().add(textArea);
		
		textLineasComentadas = new JTextPane();
		textLineasComentadas.setBounds(338, 159, 43, 22);
		getContentPane().add(textLineasComentadas);
		
		textPredicados = new JTextPane();
		textPredicados.setBounds(338, 109, 43, 22);
		getContentPane().add(textPredicados);
		
		textLineasComentadasTotales = new JTextPane();
		textLineasComentadasTotales.setBounds(336, 74, 43, 22);
		getContentPane().add(textLineasComentadasTotales);
		
		textLineasComentadasClase = new JTextPane();
		textLineasComentadasClase.setBounds(336, 190, 43, 22);
		getContentPane().add(textLineasComentadasClase);
		
		textLineasTotales = new JTextPane();
		textLineasTotales.setBounds(338, 223, 43, 22);
		getContentPane().add(textLineasTotales);
		btnSeleccionarArchivo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				leerArchivo(e);
			}

		});

		comboBoxClase.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				claseSeleccionada();
			}
		});
		
		comboBoxMetodo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				metodoSeleccionado();
			}
		});
	}
	
	private void claseSeleccionada() {
		String claseElegida = (String)comboBoxClase.getSelectedItem();
		
		comboBoxMetodo.removeAllItems();
		for (int i = 0; i < clases.size(); i++) {
			Clase clase = clases.get(i);
			if (claseElegida.equals(clase.getNombre())) {
				metodos = clase.findMethods();
				mostrarDatoClase(clase);
				
				for (int j = 0; j < metodos.size(); j++) {
					comboBoxMetodo.addItem(metodos.get(j).getNombre());
					if (j == 0) {
						mostrarDatoMetodo(metodos.get(j));						
					}
				}
			}
			
		}
	}

	private void mostrarDatoClase(Clase clase) {
		textLineasComentadasClase.setText(String.valueOf(clase.lineasComentadas()));
	}
	
	private void metodoSeleccionado() {
		String metodoElegida = (String)comboBoxMetodo.getSelectedItem();
		if (metodoElegida != null) {					
			Iterator<Metodo> it = metodos.iterator();
			while (it.hasNext()) {
				Metodo metodo = it.next();
				if (metodoElegida.equals(metodo.getNombre())) {
					mostrarDatoMetodo(metodo);
				}
			}
			
			System.out.println("elegio " + comboBoxMetodo.getSelectedItem());
		}
	}

	private void mostrarDatoMetodo(Metodo metodo) {
		textArea.setText(metodo.toString());
		textLineasComentadas.setText(String.valueOf(metodo.lineasComentadas()));
		textPredicados.setText(String.valueOf(metodo.predicados()));
	}

	public void leerArchivo(ActionEvent e) {
		JFileChooser fileSelector = new JFileChooser();

		if (fileSelector.showOpenDialog((Component) e.getSource()) == JFileChooser.APPROVE_OPTION) {
			String ruta = fileSelector.getSelectedFile().getPath();
			if (ruta.contains(".java")) { // Corroborar
				GestorArchivo gestorArchivo = new GestorArchivo(ruta);
				comboBoxClase.removeAllItems();
				clases = gestorArchivo.findClass();
				
				mostrarDatoArchivo(gestorArchivo);
				
				for (int i = 0; i < clases.size(); i++) {
					Clase clase = clases.get(i);
					comboBoxClase.addItem(clase.getNombre());
					if (i == 0) {
						mostrarDatoClase(clase);						
					}
				}
				
			}
		} else {
			JOptionPane.showMessageDialog(null, "Debe seleccionar un archivo del tipo .java", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void mostrarDatoArchivo(GestorArchivo gestorArchivo) {
		textLineasTotales.setText(String.valueOf(gestorArchivo.getTexto().size()));
		textLineasComentadasTotales.setText(String.valueOf(gestorArchivo.lineasComentadas()));
	}
	
	public static void main(String[] args) {
		Ventana principal = new Ventana();
		principal.setVisible(true);
		principal.setSize(500, 500);
	}
}

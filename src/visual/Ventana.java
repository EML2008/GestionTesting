package visual;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import core.Clase;
import core.GestorArchivo;
import core.Metodo;
import javax.swing.JTextField;

public class Ventana extends JFrame {

	private static final long serialVersionUID = -1838218182953168733L;
	private static JComboBox<String> comboBoxClase;
	private static JComboBox<String> comboBoxMetodo;
	private List<Clase> clases;
	private JTextArea textArea;
	private LinkedList<Metodo> metodos;
	private JTextField textPredicado;
	
	public Ventana() {
		getContentPane().setLayout(null);
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

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(10, 107, 316, 143);
		getContentPane().add(textArea);
		
		textPredicado = new JTextField();
		textPredicado.setBounds(336, 109, 86, 20);
		getContentPane().add(textPredicado);
		textPredicado.setColumns(10);
		btnSeleccionarArchivo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JFileChooser fileSelector = new JFileChooser();

				if (fileSelector.showOpenDialog((Component) e.getSource()) == JFileChooser.APPROVE_OPTION) {
					String ruta = fileSelector.getSelectedFile().getPath();
					if (ruta.contains(".java")) { // Corroborar
						GestorArchivo gestorArchivo = new GestorArchivo(ruta);
						clases = gestorArchivo.findClass();
						Iterator<Clase> it = clases.iterator();
						while (it.hasNext()) {
							comboBoxClase.addItem(it.next().getNombre());
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Debe seleccionar un archivo del tipo .java", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		comboBoxClase.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String claseElegida = (String)comboBoxClase.getSelectedItem();
				Iterator<Clase> it = clases.iterator();
				comboBoxMetodo.removeAllItems();
				while (it.hasNext()) {
					Clase clase = it.next();
					if (claseElegida.equals(clase.getNombre())) {
						metodos = clase.findMethods();
						for (Metodo metodo : metodos) {
							
						}
						Iterator<Metodo> metodo = metodos.iterator();
						while (metodo.hasNext()) {
							Metodo metodo1 = metodo.next();
							comboBoxMetodo.addItem(metodo1.getNombre());
							textArea.setText(metodo1.toString());
						}
					}
				}
				
				System.out.println("elegio " + comboBoxMetodo.getSelectedItem());
			}
		});
		
		comboBoxMetodo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String metodoElegida = (String)comboBoxMetodo.getSelectedItem();
				Iterator<Metodo> it = metodos.iterator();
				while (it.hasNext()) {
					Metodo metodo = it.next();
					if (metodoElegida.equals(metodo.getNombre())) {
						textArea.setText(metodo.toString());
						textPredicado.setText(String.valueOf(metodo.predicados()));
					}
				}
				
				System.out.println("elegio " + comboBoxMetodo.getSelectedItem());
			}
		});
	}

	public static void main(String[] args) {
		Ventana principal = new Ventana();
		principal.setVisible(true);
		principal.setSize(500, 500);
	}
}

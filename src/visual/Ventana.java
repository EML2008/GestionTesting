package visual;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import core.Clase;
import core.GestorArchivo;

public class Ventana extends JFrame {

	private static final long serialVersionUID = -1838218182953168733L;

	public Ventana() {
		getContentPane().setLayout(null);
		JButton btnSeleccionarArchivo = new JButton("Seleccionar Archivo");
		btnSeleccionarArchivo.setBounds(10, 11, 151, 23);
		getContentPane().add(btnSeleccionarArchivo);

		JComboBox<Object> comboBoxClase = new JComboBox();
		comboBoxClase.setBounds(10, 74, 151, 22);
		getContentPane().add(comboBoxClase);

		JComboBox<?> comboBoxMetodo = new JComboBox();
		comboBoxMetodo.setBounds(175, 74, 151, 22);
		getContentPane().add(comboBoxMetodo);

		JLabel lblClase = new JLabel("Clases");
		lblClase.setBounds(10, 49, 48, 14);
		getContentPane().add(lblClase);

		JLabel lblMetodo = new JLabel("Metodos");
		lblMetodo.setBounds(175, 49, 92, 14);
		getContentPane().add(lblMetodo);

		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(10, 118, 458, 299);
		getContentPane().add(textArea);
		btnSeleccionarArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileSelector = new JFileChooser();

				if (fileSelector.showOpenDialog((Component) e.getSource()) == JFileChooser.APPROVE_OPTION) {
					String ruta = fileSelector.getSelectedFile().getPath();
					if (ruta.contains(".java")) { // Corroborar
						GestorArchivo gestorArchivo = new GestorArchivo(ruta);
						Iterator<Clase> it = gestorArchivo.findClass().iterator();
						while (it.hasNext()) {
							comboBoxClase.addItem((Object) it.next().getNombre());
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Debe seleccionar un archivo del tipo .java", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

	}

	public static void main(String[] args) {
		Ventana principal = new Ventana();
		principal.setVisible(true);
		principal.setSize(500, 500);
	}
}

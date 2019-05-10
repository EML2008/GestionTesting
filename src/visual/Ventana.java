package visual;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import core.Clase;
import core.GestorArchivo;
import core.Metodo;

public class Ventana extends JFrame {

	private static final long serialVersionUID = -1838218182953168733L;

	public Ventana() {
		getContentPane().setLayout(null);
		JButton btnSeleccionarArchivo = new JButton("Seleccionar Archivo");
		btnSeleccionarArchivo.setBounds(10, 11, 151, 23);
		getContentPane().add(btnSeleccionarArchivo);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(11, 49, 151, 22);
		getContentPane().add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(175, 49, 151, 22);
		getContentPane().add(comboBox_1);
		btnSeleccionarArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileSelector = new JFileChooser();

				if (fileSelector.showOpenDialog((Component) e.getSource()) == JFileChooser.APPROVE_OPTION) {
					String ruta = fileSelector.getSelectedFile().getPath();
					if (ruta.contains(".java")) {
						GestorArchivo gestorArchivo = new GestorArchivo(ruta);
						for (Iterator<Clase> i = gestorArchivo.findClass().iterator(); i.hasNext();) { //Criptico hermano
							Clase clase = i.next();
							System.out.println("clase " + clase + " " + clase.getTexto());
							List<Metodo> metodos = clase.findMethods();
							for (int j = 0; j < metodos.size(); j++) {
								Metodo metodo = metodos.get(j);
								System.out.println("method " + metodo + " " + metodo.getTexto());

								System.out.println("predicados " + metodo.predicados());
							}
						}

					} else {
						JOptionPane.showMessageDialog(null, "Debe seleccionar un archivo del tipo .java", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					}

				} else {
					System.out.println("error");
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

package visual;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import core.GestorArchivo;

public class Ventana extends JFrame {
	
	public Ventana() {
		getContentPane().setLayout(null);
		JButton btnSeleccionarArchivo = new JButton("Seleccionar Archivo");
		btnSeleccionarArchivo.setBounds(10, 11, 151, 23);
		getContentPane().add(btnSeleccionarArchivo);
		btnSeleccionarArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileSelector = new JFileChooser();
				int rtn = fileSelector.showOpenDialog((Component) e.getSource());
				if (rtn == JFileChooser.APPROVE_OPTION) {
					String ruta = fileSelector.getSelectedFile().getPath();
					if (ruta.contains(".java")) {
						GestorArchivo gestorArchivo = new GestorArchivo(ruta);
						System.out.println("clase '" + gestorArchivo.findClass() + "'");
					} else {
						JOptionPane.showMessageDialog(null, "Debe seleccionar un archivo del tipo .java","ERROR", JOptionPane.ERROR_MESSAGE);
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

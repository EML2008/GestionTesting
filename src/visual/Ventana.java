package visual;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.html.HTMLEditorKit;

import core.Clase;
import core.GestorArchivo;
import core.Metodo;

public class Ventana extends JFrame {

	private static final long serialVersionUID = -1838218182953168733L;
	private static JComboBox<String> comboBoxClase;
	private static JComboBox<String> comboBoxMetodo;
	private List<Clase> clases;
	private LinkedList<Metodo> metodos;
	private JTextPane textCodigo;
	private JTextPane textLineasArchivo;
	private JTextPane textLineasComentadasArchivo;
	private JTextPane textLineasClase;
	private JTextPane textLineasComentadasClase;
	private JTextPane textLineasComentadasMetodo;
	private JTextPane textLineasMetodo;
	private JTextPane textPredicadosMetodo;
	
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

		JLabel lblClases = new JLabel("Clases");
		lblClases.setBounds(10, 49, 48, 14);
		getContentPane().add(lblClases);

		JLabel lblMetodos = new JLabel("Metodos");
		lblMetodos.setBounds(175, 49, 92, 14);
		getContentPane().add(lblMetodos);

		textCodigo = new JTextPane();
		textCodigo.setForeground(Color.GREEN);
		textCodigo.setBackground(Color.DARK_GRAY);
		textCodigo.setEditable(false);
		textCodigo.setBounds(10, 107, 499, 322);
		getContentPane().add(textCodigo);
		
		JLabel lblArchivo = new JLabel("Archivo");
		lblArchivo.setBounds(555, 24, 46, 22);
		getContentPane().add(lblArchivo);
		
		JLabel lblLineasArchivo = new JLabel("Lineas");
		lblLineasArchivo.setBounds(535, 42, 107, 22);
		getContentPane().add(lblLineasArchivo);
		
		textLineasArchivo = new JTextPane();
		textLineasArchivo.setBounds(635, 42, 43, 22);
		getContentPane().add(textLineasArchivo);
		
		JLabel lblLineasComentadasArchivo = new JLabel("Comentadas");
		lblLineasComentadasArchivo.setBounds(535, 68, 107, 22);
		getContentPane().add(lblLineasComentadasArchivo);
		
		textLineasComentadasArchivo = new JTextPane();
		textLineasComentadasArchivo.setBounds(635, 68, 43, 22);
		getContentPane().add(textLineasComentadasArchivo);
		
		JLabel lblClase = new JLabel("Clase");
		lblClase.setBounds(555, 130, 46, 22);
		getContentPane().add(lblClase);
		
		JLabel lblLineasClase = new JLabel("Lineas");
		lblLineasClase.setBounds(535, 151, 107, 22);
		getContentPane().add(lblLineasClase);
		
		textLineasClase = new JTextPane();
		textLineasClase.setBounds(635, 151, 43, 22);
		getContentPane().add(textLineasClase);
		
		JLabel lblLineasComentadasClase = new JLabel("Comentadas");
		lblLineasComentadasClase.setBounds(535, 177, 107, 22);
		getContentPane().add(lblLineasComentadasClase);
		
		textLineasComentadasClase = new JTextPane();
		textLineasComentadasClase.setBounds(635, 177, 43, 22);
		getContentPane().add(textLineasComentadasClase);
		
		JLabel lblMetodo = new JLabel("Metodo");
		lblMetodo.setBounds(555, 263, 46, 22);
		getContentPane().add(lblMetodo);
		
		JLabel lblLineasComentadasMetodo = new JLabel("Comentadas");
		lblLineasComentadasMetodo.setBounds(535, 312, 107, 22);
		getContentPane().add(lblLineasComentadasMetodo);
		
		textLineasComentadasMetodo = new JTextPane();
		textLineasComentadasMetodo.setBounds(635, 312, 43, 22);
		getContentPane().add(textLineasComentadasMetodo);
		
		JLabel lblCc = new JLabel("C.C.");
		lblCc.setBounds(535, 338, 107, 22);
		getContentPane().add(lblCc);
		
		textPredicadosMetodo = new JTextPane();
		textPredicadosMetodo.setBounds(635, 338, 43, 22);
		getContentPane().add(textPredicadosMetodo);
		
		JLabel lblLineasMetodo = new JLabel("Lineas");
		lblLineasMetodo.setBounds(535, 286, 90, 23);
		getContentPane().add(lblLineasMetodo);
		
		textLineasMetodo = new JTextPane();
		textLineasMetodo.setBounds(635, 286, 43, 22);
		getContentPane().add(textLineasMetodo);
		
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
		Clase clase;
		for (int i = 0; i < clases.size(); i++) {
			clase = clases.get(i);
			if (claseElegida.equals(clase.getNombre())) {
				metodos = clase.findMethods();
				mostrarDatoClase(clase);
				
				if (metodos.size() == 0) {
					mostrarDatoMetodo(null);
				}
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
		textLineasClase.setText(String.valueOf(clase.getTexto().size()));
	}
	
	private void metodoSeleccionado() {
		String metodoElegida = (String)comboBoxMetodo.getSelectedItem();
		if (metodoElegida != null) {					
			Metodo metodo;
			for (int i = 0; i < metodos.size(); i++) {
				metodo = metodos.get(i);
				if (metodoElegida.equals(metodo.getNombre())) {
					mostrarDatoMetodo(metodo);
					break;
				}
			}			
		}
	}

	private void mostrarDatoMetodo(Metodo metodo) {
		if (metodo == null) {
			textCodigo.setText(null);
			textLineasComentadasMetodo.setText(null);
			textPredicadosMetodo.setText(null);
			textLineasMetodo.setText(null);
			return;
		}
		textCodigo.setEditorKit(new HTMLEditorKit());
		String codigo = metodo.toString();
		
		codigo = codigo.replace("if(", "<font color=\"red\">if(</font>");

		codigo = codigo.replace("if (", "<font color=\"red\">if (</font>");

		codigo = codigo.replace("\n", "<br>");
		codigo = codigo.replace("\t", "&nbsp;&nbsp;");
		textCodigo.setText("<html><font color=\"#58FF33\">" + codigo + "</font></html>");	
		
		textLineasComentadasMetodo.setText(String.valueOf(metodo.lineasComentadas()));
		textPredicadosMetodo.setText(String.valueOf(metodo.predicados()));
		textLineasMetodo.setText(String.valueOf(metodo.getTexto().size()));
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
		textLineasArchivo.setText(String.valueOf(gestorArchivo.getTexto().size()));
		textLineasComentadasArchivo.setText(String.valueOf(gestorArchivo.lineasComentadas()));
	}
	
	private void appendToPane(JTextPane tp, String msg, Color c)
    {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        int len = tp.getText().length();
        tp.setCaretPosition(len);
        tp.setCharacterAttributes(aset, false);
        tp.select(1, 5);
        tp.replaceSelection(msg);
    }
	
	public static void main(String[] args) {
		Ventana principal = new Ventana();
		principal.setVisible(true);
		principal.setSize(700, 500);
	}
}

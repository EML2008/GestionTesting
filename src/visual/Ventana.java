package visual;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.html.HTMLEditorKit;

import core.Clase;
import core.GestorArchivo;
import core.Metodo;


public class Ventana extends JFrame {

	private static final long serialVersionUID = -1838218182953168733L;
	private static JComboBox<String> comboBoxClase;
	private static JComboBox<String> comboBoxMetodo;
	private List<Clase> clases;
	private ArrayList<Metodo> metodos;
	private JTextPane textCodigo;
	private JTextPane textRuta;
	private JTextPane textLineasArchivo;
	private JTextPane textLineasComentadasArchivo;
	private JTextPane textLineasClase;
	private JTextPane textLineasComentadasClase;
	private JTextPane textLineasComentadasMetodo;
	private JTextPane textLineasMetodo;
	private JTextPane textPredicadosMetodo;
	private JTextPane longitudTextPane;
	private JTextPane volumenTextPane;
	private JTextPane operandosTextPane;
	private JTextPane operadoresTextPane;
	private Metodo metodoElegido;

	public Ventana() {
		setTitle("Herramienta de Gesti\u00F3n de Testing");
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnSeleccionarArchivo = new JButton("Seleccionar Archivo");
		btnSeleccionarArchivo.setBounds(10, 11, 151, 23);
		getContentPane().add(btnSeleccionarArchivo);

		comboBoxClase = new JComboBox<String>();
		comboBoxClase.setBounds(10, 74, 165, 22);
		getContentPane().add(comboBoxClase);

		comboBoxMetodo = new JComboBox<String>();
		comboBoxMetodo.setBounds(185, 74, 166, 22);
		getContentPane().add(comboBoxMetodo);

		JLabel lblClases = new JLabel("Clases");
		lblClases.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblClases.setBounds(10, 49, 48, 14);
		getContentPane().add(lblClases);

		JLabel lblMetodos = new JLabel("Metodos");
		lblMetodos.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMetodos.setBounds(175, 49, 92, 14);
		getContentPane().add(lblMetodos);

		textCodigo = new JTextPane();
		textCodigo.setBackground(Color.DARK_GRAY);
		textCodigo.setEditable(false);
		textCodigo.setBounds(10, 107, 499, 322);
		JScrollPane jsp = new JScrollPane(textCodigo);
		jsp.setBounds(10, 107, 568, 362);
		jsp.setVerticalScrollBarPolicy(
                javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		getContentPane().add(jsp);

		JLabel lblArchivo = new JLabel("Archivo");
		lblArchivo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblArchivo.setBounds(597, 11, 72, 22);
		getContentPane().add(lblArchivo);

		JLabel lblLineasArchivo = new JLabel("Lineas");
		lblLineasArchivo.setBounds(597, 39, 90, 22);
		getContentPane().add(lblLineasArchivo);

		textLineasArchivo = new JTextPane();
		textLineasArchivo.setEditable(false);
		textLineasArchivo.setBounds(677, 39, 50, 20);
		getContentPane().add(textLineasArchivo);

		JLabel lblLineasComentadasArchivo = new JLabel("Comentadas");
		lblLineasComentadasArchivo.setBounds(597, 70, 107, 22);
		getContentPane().add(lblLineasComentadasArchivo);

		textLineasComentadasArchivo = new JTextPane();
		textLineasComentadasArchivo.setEditable(false);
		textLineasComentadasArchivo.setBounds(677, 70, 50, 20);
		getContentPane().add(textLineasComentadasArchivo);

		JLabel lblClase = new JLabel("Clase");
		lblClase.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblClase.setBounds(597, 107, 46, 22);
		getContentPane().add(lblClase);

		JLabel lblLineasClase = new JLabel("Lineas");
		lblLineasClase.setBounds(597, 130, 84, 22);
		getContentPane().add(lblLineasClase);

		textLineasClase = new JTextPane();
		textLineasClase.setEditable(false);
		textLineasClase.setBounds(677, 130, 50, 20);
		getContentPane().add(textLineasClase);

		JLabel lblLineasComentadasClase = new JLabel("Comentadas");
		lblLineasComentadasClase.setBounds(597, 163, 90, 22);
		getContentPane().add(lblLineasComentadasClase);

		textLineasComentadasClase = new JTextPane();
		textLineasComentadasClase.setEditable(false);
		textLineasComentadasClase.setBounds(677, 163, 50, 20);

		getContentPane().add(textLineasComentadasClase);

		JLabel lblMetodo = new JLabel("Metodo");
		lblMetodo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMetodo.setBounds(597, 215, 72, 22);
		getContentPane().add(lblMetodo);

		JLabel lblLineasComentadasMetodo = new JLabel("Comentadas");
		lblLineasComentadasMetodo.setBounds(597, 276, 90, 22);
		getContentPane().add(lblLineasComentadasMetodo);

		textLineasComentadasMetodo = new JTextPane();
		textLineasComentadasMetodo.setEditable(false);
		textLineasComentadasMetodo.setBounds(677, 276, 50, 20);
		getContentPane().add(textLineasComentadasMetodo);

		JLabel lblCc = new JLabel("C.C.");
		lblCc.setBounds(597, 309, 72, 22);
		getContentPane().add(lblCc);

		textPredicadosMetodo = new JTextPane();
		textPredicadosMetodo.setEditable(false);
		textPredicadosMetodo.setBounds(677, 312, 50, 20);
		getContentPane().add(textPredicadosMetodo);

		JLabel lblLineasMetodo = new JLabel("Lineas");
		lblLineasMetodo.setBounds(597, 243, 72, 23);
		getContentPane().add(lblLineasMetodo);

		textLineasMetodo = new JTextPane();
		textLineasMetodo.setEditable(false);
		textLineasMetodo.setBounds(677, 243, 50, 20);
		getContentPane().add(textLineasMetodo);

		longitudTextPane = new JTextPane();
		longitudTextPane.setEditable(false);
		longitudTextPane.setBounds(677, 345, 50, 20);
		getContentPane().add(longitudTextPane);

		volumenTextPane = new JTextPane();
		volumenTextPane.setEditable(false);
		volumenTextPane.setBounds(677, 376, 50, 20);
		getContentPane().add(volumenTextPane);

		operandosTextPane = new JTextPane();
		operandosTextPane.setEditable(false);
		operandosTextPane.setBounds(677, 407, 50, 20);
		getContentPane().add(operandosTextPane);

		operadoresTextPane = new JTextPane();
		operadoresTextPane.setEditable(false);
		operadoresTextPane.setBounds(677, 438, 50, 20);
		getContentPane().add(operadoresTextPane);
		
		textRuta = new JTextPane();
		textRuta.setEditable(false);
		textRuta.setBounds(171, 11, 338, 23);
		getContentPane().add(textRuta);

		JLabel lblLongitud = new JLabel("Longitud");
		lblLongitud.setBounds(597, 342, 72, 23);
		getContentPane().add(lblLongitud);

		JLabel lblVolumen = new JLabel("Volumen");
		lblVolumen.setBounds(597, 376, 72, 23);
		getContentPane().add(lblVolumen);

		JLabel lblOperandos = new JLabel("Operandos");
		lblOperandos.setBounds(597, 407, 72, 23);
		getContentPane().add(lblOperandos);

		JLabel lblOperadores = new JLabel("Operadores");
		lblOperadores.setBounds(597, 438, 72, 23);
		getContentPane().add(lblOperadores);
		
		JButton btnOperandos = new JButton("Operandos");
		btnOperandos.setBounds(355, 74, 113, 23);
		getContentPane().add(btnOperandos);
		
		JButton btnOperadores = new JButton("Operadores");
		btnOperadores.setBounds(471, 74, 107, 23);
		getContentPane().add(btnOperadores);

		btnSeleccionarArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				leerArchivo(e);
			}
		});
		
		btnOperandos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarOperandos();
			}
		});

		btnOperadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarOperandores();
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
		String claseElegida = (String) comboBoxClase.getSelectedItem();

		if (claseElegida == null) {
			System.err.println("no hay clase seleccionada");
			return;
		}
		comboBoxMetodo.removeAllItems();
		Clase clase;
		for (int i = 0; i < clases.size(); i++) {
			clase = clases.get(i);
			if (claseElegida.equals(clase.getNombre())) {
				metodos = clase.findMethods();
				mostrarDatoClase(clase);

				if (metodos.size() == 0) {
					mostrarDatoMetodoMcCabe(null);
				}
				for (int j = 0; j < metodos.size(); j++) {
					comboBoxMetodo.addItem(metodos.get(j).getNombre());
					if (j == 0) {
						//mostrarDatoMetodoMcCabe(metodos.get(j));
						//mostrarDatoMetodoHelstead(metodos.get(j));
					}
				}
			}

		}
	}

	private void mostrarDatoClase(Clase clase) {
		textLineasComentadasClase.setText(String.valueOf(clase
				.lineasComentadas()));
		textLineasClase.setText(String.valueOf(clase.getTexto().size()));
	}

	private void metodoSeleccionado() {
		String metodoElegida = (String) comboBoxMetodo.getSelectedItem();
		if (metodoElegida != null) {
			Metodo metodo;
			for (int i = 0; i < metodos.size(); i++) {
				metodo = metodos.get(i);
				if (metodoElegida.equals(metodo.getNombre())) {
					this.metodoElegido = metodo;
					mostrarDatoMetodoMcCabe(metodo);
					mostrarDatoMetodoHelstead(metodo);
					break;
				}
			}
		}
	}

	private void mostrarDatoMetodoHelstead(Metodo metodo) {
		if (metodo == null) {
			this.longitudTextPane.setText(null);
			this.volumenTextPane.setText(null);
			this.operandosTextPane.setText(null);
			this.operadoresTextPane.setText(null);
		} else {
			this.operandosTextPane.setText(String.valueOf(metodo.contarOperandos()));
			this.operadoresTextPane.setText(String.valueOf(metodo.contarOperadores()));
			this.longitudTextPane.setText(String.format("%.2f", metodo.getLongitud()));
			this.volumenTextPane.setText(String.format("%.2f", metodo.getVolumen()));
		}
	}

	private void mostrarDatoMetodoMcCabe(Metodo metodo) {
		if (metodo == null) {
			textCodigo.setText(null);
			textLineasComentadasMetodo.setText(null);
			textPredicadosMetodo.setText(null);
			textLineasMetodo.setText(null);
			return;
		}
		textCodigo.setEditorKit(new HTMLEditorKit());
		textCodigo.setText(metodo.toHtml());

		textLineasComentadasMetodo.setText(String.valueOf(metodo
				.lineasComentadas()));
		textPredicadosMetodo.setText(String.valueOf(metodo.predicados()));
		textLineasMetodo.setText(String.valueOf(metodo.getTexto().size()));
	}

	public void leerArchivo(ActionEvent e) {
		JFileChooser fileSelector = new JFileChooser();

		if (fileSelector.showOpenDialog((Component) e.getSource()) == JFileChooser.APPROVE_OPTION) {
			String ruta = fileSelector.getSelectedFile().getPath();
			if (ruta.endsWith(".java")) {
				GestorArchivo gestorArchivo = new GestorArchivo(ruta);
				comboBoxClase.removeAllItems();
				clases = gestorArchivo.findClass();
				textRuta.setText(ruta);
				mostrarDatoArchivo(gestorArchivo);

				for (int i = 0; i < clases.size(); i++) {
					Clase clase = clases.get(i);
					comboBoxClase.addItem(clase.getNombre());
					if (i == 0) {
						comboBoxClase.setSelectedItem(clase);
						mostrarDatoClase(clase);
					}
				}

			} else {
				JOptionPane.showMessageDialog(null,
						"Debe seleccionar un archivo del tipo .java", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void mostrarDatoArchivo(GestorArchivo gestorArchivo) {
		textLineasArchivo.setText(String.valueOf(gestorArchivo.getTexto()
				.size()));
		textLineasComentadasArchivo.setText(String.valueOf(gestorArchivo
				.lineasComentadas()));
	}
	
	private void cargarOperandos() {
		Operando operando = new Operando("Operandos", this.metodoElegido.getOperandos());
		operando.setVisible(true);
	}

	private void cargarOperandores() {
		Operando operando = new Operando("Operandores", this.metodoElegido.getOperadores());
		operando.setVisible(true);
	}
	
	public static void main(String[] args) {
		Ventana principal = new Ventana();
		principal.setVisible(true);
		principal.setSize(800, 550);
	}
}

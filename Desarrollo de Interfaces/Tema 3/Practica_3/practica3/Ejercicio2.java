//package practica3;

//import java.awt.*;
//import java.awt.event.*;
//import javax.swing.*;
//import edu.cmu.sphinx.api.*;
//
//public class Ejercicio2 extends JFrame {
//
//	private JButton enviar;
//	private JTextField tfWrite;
//	private LiveSpeechRecognizer reconocimiento;
//
//	public Ejercicio2() {
//
//		//Configuro el JFrame del Ejercicio2
//		setTitle("Ejercicio 2");
//		setSize(600, 300);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setLocationRelativeTo(null);
//		setLayout(new FlowLayout());
//
//		//Creo los componentes
//		enviar = new JButton("Enviar");
//		tfWrite = new JTextField(20);
//
//		//Añado los componentes
//		add(new JLabel("Campo de texto: "));
//		add(tfWrite);
//		add(enviar);
//
//		configurarReconocimientoDeVoz();
//
//		enviar.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				enviarFormulario();
//
//			}
//		});
//
//		new Thread(this::iniciarReconocimientoDeVoz).start();
//
//		setVisible(true);
//
//	}
//
//	private void configurarReconocimientoDeVoz() {
//		try {
//			Configuration configuration = new Configuration();
//
//			configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/es-es/es-es");
//			configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/es-es/cmudict-es-es.dict");
//			configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/es-es/es-es.lm.bin");
//
//			reconocimiento = new LiveSpeechRecognizer(configuration);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			JOptionPane.showMessageDialog(this, "Error al configurar el reconocimiento de voz", "Error", JOptionPane.ERROR_MESSAGE);
//		}
//
//	}
//
//	private void iniciarReconocimientoDeVoz() {
//		try {
//			reconocimiento.startRecognition(true);
//			while (true) {
//				SpeechResult resultado = reconocimiento.getResult();
//				if(resultado != null) {
//					String comando = resultado.getHypothesis().toLowerCase();
//					procesarComando(comando);
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			JOptionPane.showMessageDialog(this, "Error al iniciar el reconocimiento de voz", "Error", JOptionPane.ERROR_MESSAGE);
//
//		}
//	}
//
//
//	private void procesarComando(String comando) {
//		if (comando.startsWith("escribir")) {
//			String texto = comando.replaceFirst("escribir", "").trim();
//			tfWrite.setText(texto);
//		} else if(comando.equals("enviar")) {
//			enviarFormulario();
//		} else {
//			System.out.println("Comando no reconocido" + comando);
//		}
//	}
//
//	private void enviarFormulario() {
//		String texto = tfWrite.getText();
//		JOptionPane.showMessageDialog(this, "Formulario enviado correctamente" + texto + "Enviado", texto, JOptionPane.INFORMATION_MESSAGE);
//		tfWrite.setText("");
//	}
//
//	public static void main(String[] args) {
//		new Ejercicio2();
//	}
//
//}
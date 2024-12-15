package Views;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class AUTOPLACE extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel LImagen ;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AUTOPLACE frame = new AUTOPLACE();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AUTOPLACE() {
		
		// Configuración para quitar los bordes y la barra de título
        setUndecorated(true);   // Elimina los bordes y la barra de título
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		setBackground(Color.DARK_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		LImagen = new JLabel("");
		LImagen.setHorizontalAlignment(SwingConstants.CENTER);
		LImagen.setHorizontalTextPosition(SwingConstants.CENTER);
		LImagen.setBorder(null);
		LImagen.setIcon(new ImageIcon(AUTOPLACE.class.getResource("/Recursos/AUTOPLACE.gif")));
		LImagen.setBounds(6, 0, 538, 366);
		contentPane.add(LImagen);
		
		// Crear un temporizador de 10 segundos (10000 milisegundos)
        Timer timer = new Timer(10000, e -> {
            System.out.println("Tiempo agotado, cerrando la ventana.");
            Login login = new Login();
            login.setVisible(true);
            dispose();  // Cierra el JFrame
        });
        
        timer.setRepeats(false);  // No repetir, solo ejecutar una vez
        timer.start();
		
        System.out.println("Cargando");
	}
	

}

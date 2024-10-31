package Views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Controllers.ControladorLogin;
import Controllers.ControllerLogin;
import Models.Respuesta;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class Login extends JFrame {

	//Variables de flujo
	public ControllerLogin controladorLogin;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField TF_Usuario;
	private JPasswordField PFPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	
	public void incicializarComponentes() {
		
		controladorLogin = new ControllerLogin();
	}
	
	public Login() {
		
		incicializarComponentes();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 457, 321);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(254, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 245, 245));
		panel.setBounds(23, 6, 412, 267);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton Btn_Entrar = new JButton("Ingresar");
		Btn_Entrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				iniciarSesion();
			}
		});
		Btn_Entrar.setBounds(68, 199, 117, 29);
		panel.add(Btn_Entrar);
		
		TF_Usuario = new JTextField();
		TF_Usuario.setBounds(58, 99, 309, 26);
		panel.add(TF_Usuario);
		TF_Usuario.setColumns(10);
		
		JLabel JL_Usuario = new JLabel("Usuario");
		JL_Usuario.setBounds(61, 76, 61, 16);
		panel.add(JL_Usuario);
		
		JLabel JL_Password = new JLabel("Password");
		JL_Password.setBounds(58, 137, 61, 16);
		panel.add(JL_Password);
		
		JButton Btn_Cerrar = new JButton("Cerrar");
		Btn_Cerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cerrar();
			}
		});
		Btn_Cerrar.setBounds(210, 199, 117, 29);
		panel.add(Btn_Cerrar);
		
		JLabel JL_Usuario_1 = new JLabel("Ferreteria");
		JL_Usuario_1.setFont(new Font("Arial", Font.PLAIN, 25));
		JL_Usuario_1.setHorizontalAlignment(SwingConstants.CENTER);
		JL_Usuario_1.setBounds(6, 6, 400, 53);
		panel.add(JL_Usuario_1);
		
		PFPassword = new JPasswordField();
		PFPassword.setBounds(58, 161, 309, 26);
		panel.add(PFPassword);
	}
	
	public void iniciarSesion() {
		
		try {
			char[] password = PFPassword.getPassword();
			//Respuesta respuesta = controladorLogin.validarUsuario(TF_Usuario.getText(), TF_Password.getText());
			Respuesta respuesta = controladorLogin.AccesoUsuario(TF_Usuario.getText(), new String(password));
			JOptionPane.showMessageDialog(this,respuesta.getMensaje());
			
			if (!respuesta.getValor()) {
				return;
			}
			
			MenuPrincipal_Form menu = new MenuPrincipal_Form();
			menu.setVisible(true);
			cerrar();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void cerrar() {
		this.dispose();
	}
}

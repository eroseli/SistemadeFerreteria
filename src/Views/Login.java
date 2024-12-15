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
import DAO.ModelsDAO.Usuario;
import Models.Respuesta;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.ImageIcon;

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
		setTitle("Refaccionaria Auto Place");
		
		incicializarComponentes();
		
		setLocationRelativeTo(null);
        // Evitar que se pueda redimensionar
        setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 457, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(254, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaptionBorder);
		panel.setBounds(23, 6, 412, 340);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton Btn_Entrar = new JButton("Ingresar");
		Btn_Entrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				iniciarSesion();
			}
		});
		Btn_Entrar.setBounds(64, 305, 117, 29);
		panel.add(Btn_Entrar);
		
		TF_Usuario = new JTextField();
		TF_Usuario.setBounds(54, 205, 309, 26);
		panel.add(TF_Usuario);
		TF_Usuario.setColumns(10);
		
		JLabel JL_Usuario = new JLabel("Usuario");
		JL_Usuario.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		JL_Usuario.setBounds(57, 182, 303, 16);
		panel.add(JL_Usuario);
		
		JLabel JL_Password = new JLabel("Password");
		JL_Password.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		JL_Password.setBounds(54, 243, 306, 16);
		panel.add(JL_Password);
		
		JButton Btn_Cerrar = new JButton("Cerrar");
		Btn_Cerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cerrar();
			}
		});
		Btn_Cerrar.setBounds(206, 305, 117, 29);
		panel.add(Btn_Cerrar);
		
		PFPassword = new JPasswordField();
		PFPassword.setBounds(54, 267, 309, 26);
		panel.add(PFPassword);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/Recursos/avatar.png")));
		lblNewLabel.setBounds(75, 34, 259, 124);
		panel.add(lblNewLabel);
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
			
			Usuario usuario = (Usuario) respuesta.getRespuesta();
			
			MenuPrincipal_Form menu = new MenuPrincipal_Form(usuario.getId_Usuario());
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

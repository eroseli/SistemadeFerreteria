package Views.Forms;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import Controllers.ControllerUsuario;
import DAO.ModelsDAO.Usuario;
import HerramientasConexion.Herramientas;
import Models.Respuesta;
import Models.UsuarioView;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class FormUsuarios extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField TFId;
	private JTextField TFUsuario;
	private JTextField TFNombre;
	private JTextField TFAPaterno;
	private JTextField TFAMaterno;
	private JTextField TFCorreoElectronico;
	private JTextField TFDireccion;
	private JTextField TFPuesto;
	private JTextField TFTelefono;
	private JButton B_Grabar; 
	private JButton BEliminar ;
	
	private Respuesta respuesta;
	private int tipoOperacion;
	private Usuario usuario;
	private final JPasswordField PFPassword = new JPasswordField();
	private JPasswordField PFValidarPassword;
	
	public static void main(String[] args) {
		try {
			FormUsuarios dialog = new FormUsuarios(Herramientas.tipoOperacion.actualizar,null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FormUsuarios(int tipoOperacion, Usuario usuario) {
		setResizable(false);
		setMinimumSize(new Dimension(450, 540));
		setMaximumSize(new Dimension(450, 600));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel LId = new JLabel("Identificador");
		LId.setHorizontalTextPosition(SwingConstants.CENTER);
		LId.setHorizontalAlignment(SwingConstants.RIGHT);
		LId.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		LId.setBounds(28, 62, 107, 24);
		contentPanel.add(LId);
		
		TFId = new JTextField();
		TFId.setColumns(10);
		TFId.setBounds(145, 62, 260, 24);
		contentPanel.add(TFId);
		
		JLabel LUsuario = new JLabel("Usuario");
		LUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		LUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		LUsuario.setBounds(28, 97, 107, 24);
		contentPanel.add(LUsuario);
		
		TFUsuario = new JTextField();
		TFUsuario.setColumns(10);
		TFUsuario.setBounds(145, 97, 260, 24);
		contentPanel.add(TFUsuario);
		
		JLabel LPassword = new JLabel("Contraseña");
		LPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		LPassword.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		LPassword.setBounds(28, 130, 107, 24);
		contentPanel.add(LPassword);
		
		JLabel Lnombre = new JLabel("Nombre");
		Lnombre.setHorizontalAlignment(SwingConstants.RIGHT);
		Lnombre.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		Lnombre.setBounds(28, 198, 107, 24);
		contentPanel.add(Lnombre);
		
		TFNombre = new JTextField();
		TFNombre.setColumns(10);
		TFNombre.setBounds(145, 198, 260, 24);
		contentPanel.add(TFNombre);
		
		JLabel LPaterno = new JLabel("Apellido Paterno");
		LPaterno.setHorizontalAlignment(SwingConstants.RIGHT);
		LPaterno.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		LPaterno.setBounds(28, 231, 107, 24);
		contentPanel.add(LPaterno);
		
		TFAPaterno = new JTextField();
		TFAPaterno.setColumns(10);
		TFAPaterno.setBounds(145, 231, 260, 24);
		contentPanel.add(TFAPaterno);
		
		JLabel LMaterno = new JLabel("Apellido Materno");
		LMaterno.setHorizontalAlignment(SwingConstants.RIGHT);
		LMaterno.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		LMaterno.setBounds(28, 266, 107, 24);
		contentPanel.add(LMaterno);
		
		TFAMaterno = new JTextField();
		TFAMaterno.setColumns(10);
		TFAMaterno.setBounds(145, 266, 260, 24);
		contentPanel.add(TFAMaterno);
		
		JLabel LCorreo = new JLabel("Correo Electrónico");
		LCorreo.setHorizontalAlignment(SwingConstants.RIGHT);
		LCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		LCorreo.setBounds(28, 301, 107, 24);
		contentPanel.add(LCorreo);
		
		TFCorreoElectronico = new JTextField();
		TFCorreoElectronico.setColumns(10);
		TFCorreoElectronico.setBounds(145, 301, 260, 24);
		contentPanel.add(TFCorreoElectronico);
		
		JLabel Ldireccion = new JLabel("Dirección");
		Ldireccion.setHorizontalAlignment(SwingConstants.RIGHT);
		Ldireccion.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		Ldireccion.setBounds(28, 334, 107, 24);
		contentPanel.add(Ldireccion);
		
		TFDireccion = new JTextField();
		TFDireccion.setColumns(10);
		TFDireccion.setBounds(145, 334, 260, 24);
		contentPanel.add(TFDireccion);
		
		JLabel LPuesto = new JLabel("Puesto");
		LPuesto.setHorizontalAlignment(SwingConstants.RIGHT);
		LPuesto.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		LPuesto.setBounds(28, 369, 107, 24);
		contentPanel.add(LPuesto);
		
		TFPuesto = new JTextField();
		TFPuesto.setColumns(10);
		TFPuesto.setBounds(145, 369, 260, 24);
		contentPanel.add(TFPuesto);
		
		JLabel L_Formulario = new JLabel("Formulario Usuarios");
		L_Formulario.setHorizontalAlignment(SwingConstants.CENTER);
		L_Formulario.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		L_Formulario.setBounds(0, 8, 434, 43);
		contentPanel.add(L_Formulario);
		
		JLabel LTelefono = new JLabel("Teléfono");
		LTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
		LTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		LTelefono.setBounds(28, 404, 107, 24);
		contentPanel.add(LTelefono);
		
		TFTelefono = new JTextField();
		TFTelefono.setColumns(10);
		TFTelefono.setBounds(145, 404, 260, 24);
		contentPanel.add(TFTelefono);
		PFPassword.setBounds(145, 132, 260, 24);
		contentPanel.add(PFPassword);
		
		JLabel LPasswordValida = new JLabel("Validar Contraseña");
		LPasswordValida.setHorizontalAlignment(SwingConstants.RIGHT);
		LPasswordValida.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		LPasswordValida.setBounds(28, 165, 107, 24);
		contentPanel.add(LPasswordValida);
		
		PFValidarPassword = new JPasswordField();
		PFValidarPassword.setBounds(145, 167, 260, 24);
		contentPanel.add(PFValidarPassword);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				B_Grabar = new JButton("Grabar");
				B_Grabar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
				B_Grabar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						grabarRegistro();
					}
				});
				
				BEliminar = new JButton("Eliminar");
				BEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						eliminarUsuario();
					}
				});
				BEliminar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
				buttonPane.add(BEliminar);
				B_Grabar.setActionCommand("OK");
				buttonPane.add(B_Grabar);
				getRootPane().setDefaultButton(B_Grabar);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setFont(new Font("Segoe UI", Font.PLAIN, 11));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		this.usuario = usuario;
		this.tipoOperacion = tipoOperacion;
		inicializarPantalla();
	}
	
	public void inicializarPantalla() {
		
		switch (tipoOperacion) {
			case Herramientas.tipoOperacion.insertar:
				B_Grabar.setText("Agregar");
				BEliminar.setVisible(false);
				break;
			case Herramientas.tipoOperacion.actualizar:
				B_Grabar.setText("Actualizar");
				break;
			case Herramientas.tipoOperacion.eliminar:
				B_Grabar.setVisible(false);
				//TFId.setEditable(false);
				TFUsuario.setEditable(false);
				PFPassword.setEditable(false);
				PFValidarPassword.setEditable(false);
				TFNombre.setEditable(false);
				TFAPaterno.setEditable(false);
				TFAMaterno.setEditable(false);
				TFCorreoElectronico.setEditable(false);
				TFDireccion.setEditable(false);
				TFPuesto.setEditable(false);
				TFTelefono.setEditable(false);
				break;
		}
		
	}
	
	public void eliminarUsuario() {
		ControllerUsuario controllerUsuario = new ControllerUsuario();
		UsuarioView usuarioView = new UsuarioView();
		respuesta = new Respuesta("",true,null);
		
		if ( (JOptionPane.showConfirmDialog(this, "¿Deseas Eliminar al Usuario "+TFUsuario.getText()+" ?"))  >= 1 )
			return;
		
		usuarioView.setId_Usuario(TFId.getText());
		
		respuesta = controllerUsuario.proceso(Herramientas.tipoOperacion.eliminar, usuarioView);
		JOptionPane.showMessageDialog(this, respuesta.getMensaje());
	}
	
	public void grabarRegistro() {
		
		ControllerUsuario controllerUsuario = new ControllerUsuario();
		UsuarioView usuarioView = new UsuarioView();
		respuesta = new Respuesta("",true,null);
		
		usuarioView.setId_Usuario(TFId.getText());
		usuarioView.setUsuario(TFUsuario.getText());
		usuarioView.setPassword(new String( PFPassword.getPassword()));
		usuarioView.setValidarPassword(new String (PFValidarPassword.getPassword()));
		usuarioView.setNombre(TFNombre.getText());
		usuarioView.setApaterno(TFAPaterno.getText());
		usuarioView.setAmaterno(TFAMaterno.getText());
		usuarioView.setCorreo(TFCorreoElectronico.getText());
		usuarioView.setDireccion(TFDireccion.getText());
		usuarioView.setPuesto(TFPuesto.getText());
		usuarioView.setTelefono(TFTelefono.getText());
		
		respuesta = controllerUsuario.proceso(tipoOperacion, usuarioView);
		JOptionPane.showMessageDialog(this, respuesta.getMensaje());
	}
}

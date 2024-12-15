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
import javax.swing.UIManager;
import javax.swing.JTextField;

import Controllers.ControllerPuestos;
import Controllers.ControllerUsuario;
import DAO.ModelsDAO.MarcaDAO;
import DAO.ModelsDAO.PuestoDAO;
import DAO.ModelsDAO.Usuario;
import HerramientasConexion.Herramientas;
import Models.Respuesta;
import Models.UsuarioView;
import Utileria.ComboItem;
import Utileria.ComponentesDesing;
import Views.JP_Usuarios;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JSeparator;

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
	private JTextField TFTelefono;
	private JButton B_Grabar; 
	private JButton BEliminar ;
	private JButton BCancelar ;
	private JComboBox CBPuesto;
	
	private Respuesta respuesta;
	private int tipoOperacion;
	private Usuario usuario;
	private final JPasswordField PFPassword = new JPasswordField();
	private JPasswordField PFValidarPassword;
	private JP_Usuarios jp_Usuarios;
	private ArrayList<PuestoDAO> puestos = new ArrayList<PuestoDAO>();
	private JSeparator separator;
	
	public static void main(String[] args) {
		try {
			
			Usuario usuarioPrueba = new Usuario();
			usuarioPrueba.setId_Usuario(0);
			usuarioPrueba.setUsuario("SaCC18");
			usuarioPrueba.setPassword("1234qwer");
			usuarioPrueba.setNombre("Eros");
			usuarioPrueba.setApaterno("Roque");
			usuarioPrueba.setAmaterno("Santiago");
			usuarioPrueba.setCorreo("eroseliroque@gmail.com");
			usuarioPrueba.setDireccion("1 privada de Jose Mariano Esperanza");
			usuarioPrueba.setPuesto("Administrador");
			usuarioPrueba.setTelefono("9514134591");
			
			FormUsuarios dialog = new FormUsuarios(null,Herramientas.tipoOperacion.insertar,usuarioPrueba);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FormUsuarios(JP_Usuarios jp_Usuarios,int tipoOperacion, Usuario usuario) {
		setResizable(false);
		setMinimumSize(new Dimension(450, 540));
		setMaximumSize(new Dimension(450, 600));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel LId = new JLabel("Identificador");
		LId.setHorizontalTextPosition(SwingConstants.CENTER);
		LId.setHorizontalAlignment(SwingConstants.RIGHT);
		LId.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		LId.setBounds(28, 72, 107, 24);
		contentPanel.add(LId);
		
		TFId = new JTextField();
		TFId.setColumns(10);
		TFId.setBounds(145, 72, 260, 24);
		contentPanel.add(TFId);
		
		JLabel LUsuario = new JLabel("Usuario");
		LUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		LUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		LUsuario.setBounds(28, 107, 107, 24);
		contentPanel.add(LUsuario);
		
		TFUsuario = new JTextField();
		TFUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Herramientas.tamanioCadena(e, TFUsuario, Herramientas.TamCampos.nombres);
			}
		});
		TFUsuario.setColumns(10);
		TFUsuario.setBounds(145, 107, 260, 24);
		contentPanel.add(TFUsuario);
		
		JLabel LPassword = new JLabel("Contraseña");
		LPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		LPassword.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		LPassword.setBounds(28, 140, 107, 24);
		contentPanel.add(LPassword);
		
		JLabel Lnombre = new JLabel("Nombre");
		Lnombre.setHorizontalAlignment(SwingConstants.RIGHT);
		Lnombre.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		Lnombre.setBounds(28, 208, 107, 24);
		contentPanel.add(Lnombre);
		
		TFNombre = new JTextField();
		TFNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Herramientas.tamanioCadena(e, TFNombre, Herramientas.TamCampos.nombres);
			}
		});
		TFNombre.setColumns(10);
		TFNombre.setBounds(145, 208, 260, 24);
		contentPanel.add(TFNombre);
		
		JLabel LPaterno = new JLabel("Apellido Paterno");
		LPaterno.setHorizontalAlignment(SwingConstants.RIGHT);
		LPaterno.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		LPaterno.setBounds(28, 241, 107, 24);
		contentPanel.add(LPaterno);
		
		TFAPaterno = new JTextField();
		TFAPaterno.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Herramientas.tamanioCadena(e, TFAPaterno, Herramientas.TamCampos.nombres);
			}
		});
		TFAPaterno.setColumns(10);
		TFAPaterno.setBounds(145, 241, 260, 24);
		contentPanel.add(TFAPaterno);
		
		JLabel LMaterno = new JLabel("Apellido Materno");
		LMaterno.setHorizontalAlignment(SwingConstants.RIGHT);
		LMaterno.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		LMaterno.setBounds(28, 276, 107, 24);
		contentPanel.add(LMaterno);
		
		TFAMaterno = new JTextField();
		TFAMaterno.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Herramientas.tamanioCadena(e, TFAMaterno, Herramientas.TamCampos.nombres);
			}
		});
		TFAMaterno.setColumns(10);
		TFAMaterno.setBounds(145, 276, 260, 24);
		contentPanel.add(TFAMaterno);
		
		JLabel LCorreo = new JLabel("Correo Electrónico");
		LCorreo.setHorizontalAlignment(SwingConstants.RIGHT);
		LCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		LCorreo.setBounds(28, 311, 107, 24);
		contentPanel.add(LCorreo);
		
		TFCorreoElectronico = new JTextField();
		TFCorreoElectronico.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Herramientas.tamanioCadena(e, TFCorreoElectronico, Herramientas.TamCampos.nombres);
			}
		});
		TFCorreoElectronico.setColumns(10);
		TFCorreoElectronico.setBounds(145, 311, 260, 24);
		contentPanel.add(TFCorreoElectronico);
		
		JLabel Ldireccion = new JLabel("Dirección");
		Ldireccion.setHorizontalAlignment(SwingConstants.RIGHT);
		Ldireccion.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		Ldireccion.setBounds(28, 344, 107, 24);
		contentPanel.add(Ldireccion);
		
		TFDireccion = new JTextField();
		TFDireccion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Herramientas.tamanioCadena(e, TFDireccion, Herramientas.TamCampos.descripcionCorta);
			}
		});
		TFDireccion.setColumns(10);
		TFDireccion.setBounds(145, 344, 260, 24);
		contentPanel.add(TFDireccion);
		
		JLabel LPuesto = new JLabel("Puesto");
		LPuesto.setHorizontalAlignment(SwingConstants.RIGHT);
		LPuesto.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		LPuesto.setBounds(28, 379, 107, 24);
		contentPanel.add(LPuesto);
		
		JLabel L_Formulario = new JLabel("Gestión de Usuario");
		L_Formulario.setHorizontalAlignment(SwingConstants.CENTER);
		L_Formulario.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		L_Formulario.setBounds(6, 6, 434, 43);
		contentPanel.add(L_Formulario);
		
		JLabel LTelefono = new JLabel("Teléfono");
		LTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
		LTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		LTelefono.setBounds(28, 414, 107, 24);
		contentPanel.add(LTelefono);
		
		TFTelefono = new JTextField();
		TFTelefono.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				Herramientas.validarTelefono(arg0, TFTelefono);
			}
		});
		TFTelefono.setColumns(10);
		TFTelefono.setBounds(145, 414, 260, 24);
		contentPanel.add(TFTelefono);
		PFPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Herramientas.tamanioCadena(e, PFPassword, Herramientas.TamCampos.nombres);
			}
		});
		PFPassword.setBounds(145, 142, 260, 24);
		contentPanel.add(PFPassword);
		
		JLabel LPasswordValida = new JLabel("Validar Contraseña");
		LPasswordValida.setHorizontalAlignment(SwingConstants.RIGHT);
		LPasswordValida.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		LPasswordValida.setBounds(28, 175, 107, 24);
		contentPanel.add(LPasswordValida);
		
		PFValidarPassword = new JPasswordField();
		PFValidarPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Herramientas.tamanioCadena(e, PFValidarPassword, Herramientas.TamCampos.nombres);
			}
		});
		PFValidarPassword.setBounds(145, 177, 260, 24);
		contentPanel.add(PFValidarPassword);
		
		CBPuesto = new JComboBox();
		CBPuesto.setBounds(145, 380, 260, 27);
		contentPanel.add(CBPuesto);
		
		separator = new JSeparator();
		separator.setBounds(28, 55, 377, 6);
		contentPanel.add(separator);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 255, 255));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				B_Grabar = new JButton("Grabar");
				B_Grabar.setMnemonic(KeyEvent.VK_ENTER);
				B_Grabar.setToolTipText("Grabar Registro");
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
			}
			{
				BCancelar = new JButton("Cancel");
				BCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				BCancelar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
				BCancelar.setActionCommand("Cancel");
				buttonPane.add(BCancelar);
			}
		}
		
		this.usuario = usuario;
		this.tipoOperacion = tipoOperacion;
		this.jp_Usuarios = jp_Usuarios;
		rellenarCampos();
		inicializarPantalla();
		configurarPantalla();
		ComponentesDesing.JButtonDesing(B_Grabar,Herramientas.tipoButton.grabar);
		ComponentesDesing.JButtonDesing(BEliminar,Herramientas.tipoButton.eliminar);
		ComponentesDesing.JButtonDesing(BCancelar,Herramientas.tipoButton.cancelar);
	}
	
	public void rellenarCampos() {
		
		ControllerPuestos controllerPuestos = new ControllerPuestos();
		respuesta = new Respuesta("",true,null);
		
		respuesta = controllerPuestos.proceso(Herramientas.tipoOperacion.seleccionar, null);
		
		if (!respuesta.getValor()) {
			JOptionPane.showMessageDialog(this, "Error : "+respuesta.getMensaje(),"Error",JOptionPane.ERROR_MESSAGE);
		}
		
		puestos = (ArrayList<PuestoDAO>) respuesta.getRespuesta();
		
		for (PuestoDAO p : puestos) {
			ComboItem c = new ComboItem( String.valueOf( p.getId_puesto()), p.getNombre());
			CBPuesto.addItem( c);
		}
		
	}
	
	public void configurarPantalla() {
		
		if (usuario != null && tipoOperacion != Herramientas.tipoOperacion.insertar) {
			TFId.setText(usuario.getId_Usuario()+"");
			TFUsuario.setText(usuario.getUsuario());
			PFPassword.setText(usuario.getPassword());
			PFValidarPassword.setText(usuario.getPassword());
			TFNombre.setText(usuario.getNombre());
			TFAPaterno.setText(usuario.getApaterno());
			TFAMaterno.setText(usuario.getAmaterno());
			TFCorreoElectronico.setText(usuario.getCorreo());
			TFDireccion.setText(usuario.getDireccion());
			TFTelefono.setText(usuario.getTelefono());
		
			for (int i = 0; i < CBPuesto.getItemCount(); i++) {
			    ComboItem item = (ComboItem) CBPuesto.getItemAt(i);
			    if (item.getValue().equals(usuario.getPuesto())) {
			    	CBPuesto.setSelectedIndex(i);  // Seleccionar el índice correspondiente
			        break;
			    }
			    System.out.println(item.getValue());
			}
			
		}
	}
	
	public void inicializarPantalla() {
		
		ComponentesDesing.textFieldDeshabilitar(TFId);
		
		switch (tipoOperacion) {
			case Herramientas.tipoOperacion.insertar:
				B_Grabar.setText("Agregar");
				BEliminar.setVisible(false);
				break;
			case Herramientas.tipoOperacion.actualizar:
				B_Grabar.setText("Actualizar");
				break;
			case Herramientas.tipoOperacion.eliminar:
				configuracionCompartida();
				break;
			case Herramientas.tipoOperacion.seleccionar:
				configuracionCompartida();
				BEliminar.setVisible(false);
				BCancelar.setText("Cerrar");
				break;
		}
		
	}
	
	public void configuracionCompartida() {
		B_Grabar.setVisible(false);
		ComponentesDesing.textFieldDeshabilitar(TFUsuario);
		ComponentesDesing.textFieldDeshabilitar(TFNombre);
		ComponentesDesing.textFieldDeshabilitar(TFAPaterno);
		ComponentesDesing.textFieldDeshabilitar(TFAMaterno);
		ComponentesDesing.textFieldDeshabilitar(TFCorreoElectronico);
		ComponentesDesing.textFieldDeshabilitar(TFDireccion);
		CBPuesto.setEnabled(false);		
		ComponentesDesing.textFieldDeshabilitar(TFTelefono);
		ComponentesDesing.JPasswordFieldDeshabilitar(PFPassword);
		ComponentesDesing.JPasswordFieldDeshabilitar(PFValidarPassword);
	}
	
	public void eliminarUsuario() {
		ControllerUsuario controllerUsuario = new ControllerUsuario();
		UsuarioView usuarioView = new UsuarioView();
		respuesta = new Respuesta("",true,null);
		
		if ( (JOptionPane.showConfirmDialog(this, "¿Deseas Eliminar al Usuario "+TFUsuario.getText()+" ?"))  >= 1 )
			return;
		
		usuarioView.setId_Usuario(TFId.getText());
		
		respuesta = controllerUsuario.proceso(Herramientas.tipoOperacion.eliminar, usuarioView.getId_Usuario());
		JOptionPane.showMessageDialog(this, respuesta.getMensaje());
		
		if (respuesta.getValor()) {
			jp_Usuarios.iniciarPantalla();
			dispose();
		}
		
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
		usuarioView.setTelefono(TFTelefono.getText());
		
		ComboItem CIPuesto =  (ComboItem) CBPuesto.getSelectedItem(); 		
		usuarioView.setPuesto(CIPuesto.getValue());
		
		respuesta = controllerUsuario.proceso(tipoOperacion, usuarioView);
		
		if (!respuesta.getValor()) {
			JOptionPane.showMessageDialog(this,respuesta.getMensaje(),"Advertencia", JOptionPane.WARNING_MESSAGE);
		}		
		
		JOptionPane.showMessageDialog(this, respuesta.getMensaje(),"Información",JOptionPane.INFORMATION_MESSAGE);
		bloquearPantalla();
		
		
	}
	
	
	public void bloquearPantalla() {		
		jp_Usuarios.iniciarPantalla();
		this.dispose();
	}
}

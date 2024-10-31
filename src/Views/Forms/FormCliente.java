package Views.Forms;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

import Controllers.ControllerCliente;
import Controllers.ControllerUsuario;
import DAO.ModelsDAO.Cliente;
import HerramientasConexion.Herramientas;
import Models.ClienteView;
import Models.Respuesta;
import Models.UsuarioView;
import Utileria.ComponentesDesing;

import javax.swing.JSpinner;
import java.awt.Color;
import java.awt.Cursor;

public class FormCliente extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField TFId;
	private JTextField TFNombre;
	private JTextField TFTelefono;
	private JTextField TFCorreo;
	private JDateChooser DCFechaNacimiento;
	private JSpinner SCompras;
	private JTextField TFApaterno;
	private JTextField TFAmaterno;
	private JButton BGrabar;
	private JButton BEliminar;
	private JButton BCancelar;
	//Variables
	private Respuesta respuesta;
	private ControllerCliente controllerCliente;
	private int tipoOperacion;
	private Cliente cliente;
	
	public static void main(String[] args) {
		try {
			
			Cliente prueba = new Cliente();
			prueba.setIdentificador("SaCC18");
			prueba.setNombre("Damian");
			prueba.setApaterno("Arellano");
			prueba.setAmaterno("Diaz");
			prueba.setFechaNac(Date.valueOf(LocalDate.now()));
			prueba.setTelefono("9876543211");
			prueba.setCorreo("dema@gmail.com");
			prueba.setCompras(1);
			
			FormCliente dialog = new FormCliente(Herramientas.tipoOperacion.insertar, prueba);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public FormCliente(int tipoOperacion, Cliente cliente) {
		setResizable(false);
		setMaximumSize(new Dimension(450, 540));
		setMinimumSize(new Dimension(450, 440));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel LId = new JLabel("Identificador");
			LId.setHorizontalTextPosition(SwingConstants.CENTER);
			LId.setHorizontalAlignment(SwingConstants.RIGHT);
			LId.setFont(new Font("Segoe UI", Font.PLAIN, 11));
			LId.setBounds(29, 74, 107, 24);
			contentPanel.add(LId);
		}
		{
			TFId = new JTextField();
			TFId.setColumns(10);
			TFId.setBounds(146, 74, 260, 24);
			contentPanel.add(TFId);
		}
		{
			JLabel LNombre = new JLabel("Nombre");
			LNombre.setHorizontalAlignment(SwingConstants.RIGHT);
			LNombre.setFont(new Font("Segoe UI", Font.PLAIN, 11));
			LNombre.setBounds(29, 109, 107, 24);
			contentPanel.add(LNombre);
		}
		{
			TFNombre = new JTextField();
			TFNombre.setColumns(10);
			TFNombre.setBounds(146, 109, 260, 24);
			contentPanel.add(TFNombre);
		}
		{
			JLabel LApaterno = new JLabel("Apellido Paterno");
			LApaterno.setHorizontalAlignment(SwingConstants.RIGHT);
			LApaterno.setFont(new Font("Segoe UI", Font.PLAIN, 11));
			LApaterno.setBounds(29, 142, 107, 24);
			contentPanel.add(LApaterno);
		}
		{
			JLabel LAmaterno = new JLabel("Apellido Materno");
			LAmaterno.setHorizontalAlignment(SwingConstants.RIGHT);
			LAmaterno.setFont(new Font("Segoe UI", Font.PLAIN, 11));
			LAmaterno.setBounds(29, 177, 107, 24);
			contentPanel.add(LAmaterno);
		}
		{
			JLabel LFecha = new JLabel("Fecha de Nac.");
			LFecha.setHorizontalAlignment(SwingConstants.RIGHT);
			LFecha.setFont(new Font("Segoe UI", Font.PLAIN, 11));
			LFecha.setBounds(29, 210, 107, 24);
			contentPanel.add(LFecha);
		}
		{
			JLabel LTelefono = new JLabel("Teléfono");
			LTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
			LTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 11));
			LTelefono.setBounds(29, 243, 107, 24);
			contentPanel.add(LTelefono);
		}
		{
			TFTelefono = new JTextField();
			TFTelefono.setColumns(10);
			TFTelefono.setBounds(146, 243, 260, 24);
			contentPanel.add(TFTelefono);
		}
		{
			JLabel LCorreo = new JLabel("Correo Electrónico");
			LCorreo.setHorizontalAlignment(SwingConstants.RIGHT);
			LCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 11));
			LCorreo.setBounds(29, 278, 107, 24);
			contentPanel.add(LCorreo);
		}
		{
			TFCorreo = new JTextField();
			TFCorreo.setColumns(10);
			TFCorreo.setBounds(146, 278, 260, 24);
			contentPanel.add(TFCorreo);
		}
		{
			JLabel lblNumCompras = new JLabel("Num. Compras");
			lblNumCompras.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNumCompras.setFont(new Font("Segoe UI", Font.PLAIN, 11));
			lblNumCompras.setBounds(29, 311, 107, 24);
			contentPanel.add(lblNumCompras);
		}
		{
			JLabel L_Formulario = new JLabel("Formulario Clientes");
			L_Formulario.setHorizontalAlignment(SwingConstants.CENTER);
			L_Formulario.setFont(new Font("Segoe UI", Font.PLAIN, 20));
			L_Formulario.setBounds(0, 11, 434, 43);
			contentPanel.add(L_Formulario);
		}
		
		DCFechaNacimiento = new JDateChooser();
		DCFechaNacimiento.setName("DCFechaNac");
		DCFechaNacimiento.setBounds(146, 210, 260, 24);
		contentPanel.add(DCFechaNacimiento);
		
		SCompras = new JSpinner();
		SCompras.setBounds(146, 313, 260, 24);
		contentPanel.add(SCompras);
		
		TFApaterno = new JTextField();
		TFApaterno.setColumns(10);
		TFApaterno.setBounds(146, 144, 260, 24);
		contentPanel.add(TFApaterno);
		
		TFAmaterno = new JTextField();
		TFAmaterno.setColumns(10);
		TFAmaterno.setBounds(146, 177, 260, 24);
		contentPanel.add(TFAmaterno);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				BGrabar = new JButton("Grabar");
				BGrabar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						guardarRegistro();
					}
				});
				
				BEliminar = new JButton("Eliminar");
				BEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						eliminarRegistro();
					}
				});
				BEliminar.setForeground(new Color(255, 0, 0));
				BEliminar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
				buttonPane.add(BEliminar);
				BGrabar.setActionCommand("OK");
				buttonPane.add(BGrabar);
				getRootPane().setDefaultButton(BGrabar);
			}
			{
				BCancelar = new JButton("Cancel");
				BCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				BCancelar.setActionCommand("Cancel");
				buttonPane.add(BCancelar);
			}
		}
		
		this.tipoOperacion = tipoOperacion;
		this.cliente = cliente;
		inicializarPantalla();
		configuracionPantalla();
	}
	
	public void inicializarPantalla() {
		controllerCliente = new ControllerCliente();
		
		if(cliente != null && tipoOperacion!= Herramientas.tipoOperacion.insertar) {
			TFId.setText(cliente.getIdentificador());
			TFNombre.setText(cliente.getNombre());
			TFApaterno.setText(cliente.getApaterno());
			TFAmaterno.setText(cliente.getAmaterno());
			DCFechaNacimiento.setDate(new java.util.Date(cliente.getFechaNac().getTime()));
			TFTelefono.setText(cliente.getTelefono());
			TFCorreo.setText(cliente.getCorreo());
			SCompras.setValue( (Object) cliente.getCompras());
		}
		
	}
	
	public void configuracionPantalla() {
		
		ComponentesDesing.textFieldDeshabilitar(TFId);
		    
		if (tipoOperacion == Herramientas.tipoOperacion.insertar) {
			BGrabar.setText("Agregar");
			BEliminar.setVisible(false);
			
		}else if(tipoOperacion == Herramientas.tipoOperacion.actualizar) {
			BGrabar.setText("Actualizar");
		}else if(tipoOperacion == Herramientas.tipoOperacion.eliminar) {
			BGrabar.setVisible(false);
			ComponentesDesing.textFieldDeshabilitar(TFNombre);
			ComponentesDesing.textFieldDeshabilitar(TFApaterno);
			ComponentesDesing.textFieldDeshabilitar(TFAmaterno);
			ComponentesDesing.textFieldDeshabilitar(TFTelefono);
			ComponentesDesing.textFieldDeshabilitar(TFCorreo);
			ComponentesDesing.JSnipperDesHabilitar(SCompras);
			ComponentesDesing.JDatachoser(DCFechaNacimiento);
		}
	}
	
	public void eliminarRegistro() {
		ControllerCliente controllerCliente = new ControllerCliente();
		ClienteView clienteView = new ClienteView();
		respuesta = new Respuesta("",true,null);
		
		if ( (JOptionPane.showConfirmDialog(this, "¿Deseas Eliminar al Usuario "+TFNombre.getText()+" ?"))  >= 1 )
			return;
		clienteView.setId_Cliente(TFId.getText());
		
		Cursor cursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
        setCursor(cursor);		
        respuesta = controllerCliente.proceso(Herramientas.tipoOperacion.eliminar, clienteView);
		cursor = Cursor.getDefaultCursor();
		setCursor(cursor);
		
		JOptionPane.showMessageDialog(this, respuesta.getMensaje());
	}
	
	public void guardarRegistro(){
		
		respuesta = new Respuesta("",true,null);
		ClienteView clienteView = new ClienteView();
		
		clienteView.setId_Cliente(TFId.getText());
		clienteView.setNombre(TFNombre.getText());
		clienteView.setApaterno(TFApaterno.getText());
		clienteView.setAmaterno(TFAmaterno.getText());
		clienteView.setFechaNac( Herramientas.convertirFecha(DCFechaNacimiento));
		clienteView.setTelefono(TFTelefono.getText());
		clienteView.setCorreo(TFCorreo.getText());
		clienteView.setCompras(""+SCompras.getValue());
		
		Cursor cursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
        setCursor(cursor);		
        respuesta =controllerCliente.proceso(tipoOperacion, clienteView);
		cursor = Cursor.getDefaultCursor();
		setCursor(cursor);
		
		JOptionPane.showMessageDialog(this, respuesta.getMensaje());
	}
}

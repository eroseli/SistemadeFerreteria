package Views.Forms;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import java.awt.Dimension;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;

import Controllers.ControllerProducto;
import Controllers.ControllerProveedor;
import DAO.ModelsDAO.Proveedor;
import HerramientasConexion.Herramientas;
import Models.ProveedorView;
import Models.Respuesta;
import Utileria.ComponentesDesing;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.Cursor;

public class FormProveedor extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField TFId;
	private JTextField TFNombre;
	private JTextField TFApaterno;
	private JTextField TFAmaterno;
	private JTextField TFTelefono;
	private JTextField TFCorreo;
	private JTextField TFEmpresa;
	private JTextField TFDireccion;
	private JButton BGrabar;
	private JButton BCancelar;
	private JButton BEliminar;
	private JDateChooser DCFechaRegistro;
	
	private Proveedor proveedor;
	private int tipoOperacion;
	
	private ControllerProveedor controllerProveedor;
	private Respuesta respuesta;
	
	public static void main(String[] args) {
		try {
			
			Proveedor proveedorPrueba = new Proveedor();
			
			proveedorPrueba.setId_Proveedor(0);
			proveedorPrueba.setNombre("Damian");
			proveedorPrueba.setApaterno("Diaz");
			proveedorPrueba.setAmaterno("Sanchez");
			proveedorPrueba.setNum_Telefono("3219089021");
			proveedorPrueba.setCorreo("damiandiaz123@gmail.com");
			proveedorPrueba.setEmpresa("TheMaxima");
			proveedorPrueba.setDireccion("10 de Diciembre");
			proveedorPrueba.setFechaRegistro(Date.valueOf(LocalDate.now()));
			
			FormProveedor dialog = new FormProveedor(proveedorPrueba, Herramientas.tipoOperacion.eliminar);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public FormProveedor(Proveedor proveedor, int tipoOperacion) {
		setResizable(false);
		setMinimumSize(new Dimension(450, 460));
		setMaximumSize(new Dimension(450, 500));
		setBounds(100, 100, 450, 365);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel LId = new JLabel("Identificador");
		LId.setHorizontalTextPosition(SwingConstants.CENTER);
		LId.setHorizontalAlignment(SwingConstants.RIGHT);
		LId.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		LId.setBounds(26, 65, 107, 24);
		contentPanel.add(LId);
		
		TFId = new JTextField();
		TFId.setColumns(10);
		TFId.setBounds(143, 65, 260, 24);
		contentPanel.add(TFId);
		
		JLabel LNombre = new JLabel("Nombre");
		LNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		LNombre.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		LNombre.setBounds(26, 100, 107, 24);
		contentPanel.add(LNombre);
		
		TFNombre = new JTextField();
		TFNombre.setColumns(10);
		TFNombre.setBounds(143, 100, 260, 24);
		contentPanel.add(TFNombre);
		
		JLabel LApaterno = new JLabel("Apellido Paterno");
		LApaterno.setHorizontalAlignment(SwingConstants.RIGHT);
		LApaterno.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		LApaterno.setBounds(26, 133, 107, 24);
		contentPanel.add(LApaterno);
		
		TFApaterno = new JTextField();
		TFApaterno.setColumns(10);
		TFApaterno.setBounds(143, 135, 260, 24);
		contentPanel.add(TFApaterno);
		
		JLabel LAmaterno = new JLabel("Apellido Materno");
		LAmaterno.setHorizontalAlignment(SwingConstants.RIGHT);
		LAmaterno.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		LAmaterno.setBounds(26, 168, 107, 24);
		contentPanel.add(LAmaterno);
		
		TFAmaterno = new JTextField();
		TFAmaterno.setColumns(10);
		TFAmaterno.setBounds(143, 168, 260, 24);
		contentPanel.add(TFAmaterno);
		
		JLabel LTelefono = new JLabel("Teléfono");
		LTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
		LTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		LTelefono.setBounds(26, 203, 107, 24);
		contentPanel.add(LTelefono);
		
		TFTelefono = new JTextField();
		TFTelefono.setColumns(10);
		TFTelefono.setBounds(143, 203, 260, 24);
		contentPanel.add(TFTelefono);
		
		JLabel LCorreo = new JLabel("Correo Electrónico");
		LCorreo.setHorizontalAlignment(SwingConstants.RIGHT);
		LCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		LCorreo.setBounds(26, 238, 107, 24);
		contentPanel.add(LCorreo);
		
		TFCorreo = new JTextField();
		TFCorreo.setColumns(10);
		TFCorreo.setBounds(143, 238, 260, 24);
		contentPanel.add(TFCorreo);
		
		JLabel LDireccion = new JLabel("Dirección");
		LDireccion.setHorizontalAlignment(SwingConstants.RIGHT);
		LDireccion.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		LDireccion.setBounds(26, 311, 107, 24);
		contentPanel.add(LDireccion);
		
		JLabel L_Formulario = new JLabel("Formulario Proveedores");
		L_Formulario.setHorizontalAlignment(SwingConstants.CENTER);
		L_Formulario.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		L_Formulario.setBounds(0, 11, 434, 43);
		contentPanel.add(L_Formulario);
		
		JLabel Empresa = new JLabel("Empresa");
		Empresa.setHorizontalAlignment(SwingConstants.RIGHT);
		Empresa.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		Empresa.setBounds(26, 273, 107, 24);
		contentPanel.add(Empresa);
		
		TFEmpresa = new JTextField();
		TFEmpresa.setColumns(10);
		TFEmpresa.setBounds(143, 273, 260, 24);
		contentPanel.add(TFEmpresa);
		
		TFDireccion = new JTextField();
		TFDireccion.setColumns(10);
		TFDireccion.setBounds(143, 311, 260, 24);
		contentPanel.add(TFDireccion);
		
		DCFechaRegistro = new JDateChooser();
		DCFechaRegistro.setName("DCFechaNac");
		DCFechaRegistro.setBounds(143, 346, 260, 24);
		contentPanel.add(DCFechaRegistro);
		
		JLabel LFechaRegistro = new JLabel("Fecha de Registro");
		LFechaRegistro.setHorizontalAlignment(SwingConstants.RIGHT);
		LFechaRegistro.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		LFechaRegistro.setBounds(26, 346, 107, 24);
		contentPanel.add(LFechaRegistro);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 255, 255));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				BGrabar = new JButton("Grabar");
				BGrabar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						grabarRegistro();
					}
				});
				
				BEliminar = new JButton("Eliminar");
				BEliminar.setBorderPainted(false);
				BEliminar.setAlignmentX(Component.CENTER_ALIGNMENT);
				BEliminar.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						BEliminar.setForeground(Color.DARK_GRAY);
						Color rgbColor = new Color(238, 50, 76 );
						BEliminar.setBackground(rgbColor);
						BEliminar.setForeground(Color.white);
					}
					@Override
					public void mouseExited(MouseEvent e) {
						ComponentesDesing.JButtonDesing(BEliminar, 3);
					}
				});
				BEliminar.setActionCommand("OK");
				buttonPane.add(BEliminar);
				BGrabar.setActionCommand("OK");
				buttonPane.add(BGrabar);
				getRootPane().setDefaultButton(BGrabar);
			}
			{
				BCancelar = new JButton("Cancel");
				BCancelar.setActionCommand("Cancel");
				buttonPane.add(BCancelar);
			}
		}	
		
		this.tipoOperacion = tipoOperacion;
		this.proveedor = proveedor;
		

		//Contruir variables
		ConfigurarPantalla();
		InicializarPantalla();
	}
	
	public void InicializarPantalla() {
		controllerProveedor = new ControllerProveedor();
		if(proveedor!= null && tipoOperacion != Herramientas.tipoOperacion.insertar) {
			TFId.setText(proveedor.getId_Proveedor()+"");
			TFNombre.setText(proveedor.getNombre());
			TFApaterno.setText(proveedor.getApaterno());
			TFAmaterno.setText(proveedor.getAmaterno());
			TFTelefono.setText(proveedor.getNum_Telefono());
			TFCorreo.setText(proveedor.getCorreo());
			TFEmpresa.setText(proveedor.getEmpresa());
			TFDireccion.setText(proveedor.getDireccion());
			DCFechaRegistro.setDate(new java.util.Date(proveedor.getFechaRegistro().getTime()));
		}
	}
	
	public void grabarRegistro() {
	
		ProveedorView proveedorView = new ProveedorView();
		respuesta = new Respuesta("",true,null);
		
		proveedorView.setId_Proveedor(TFId.getText());
		proveedorView.setNombre(TFNombre.getText());
		proveedorView.setApaterno(TFApaterno.getText());
		proveedorView.setAmaterno(TFAmaterno.getText());
		proveedorView.setNum_Telefono(TFTelefono.getText());
		proveedorView.setCorreo(TFCorreo.getText());
		proveedorView.setEmpresa(TFEmpresa.getText());
		proveedorView.setDireccion(TFDireccion.getText());
		proveedorView.setFechaRegistro(Herramientas.convertirFecha(DCFechaRegistro));
		
		Cursor cursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
        setCursor(cursor);		
        respuesta =controllerProveedor.proceso(tipoOperacion, proveedorView);
		cursor = Cursor.getDefaultCursor();
		setCursor(cursor);
		
		JOptionPane.showMessageDialog(this, respuesta.getMensaje());
		
	}
	
	
	public void ConfigurarPantalla() {
		
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
			ComponentesDesing.textFieldDeshabilitar(TFEmpresa);		
			ComponentesDesing.textFieldDeshabilitar(TFDireccion);
			ComponentesDesing.JDatachoser(DCFechaRegistro);
		}
		ComponentesDesing.JButtonDesing(BCancelar, Herramientas.tipoButton.cancelar);
		ComponentesDesing.JButtonDesing(BGrabar, Herramientas.tipoButton.grabar);
		ComponentesDesing.JButtonDesing(BEliminar, Herramientas.tipoButton.eliminar);
	}
}

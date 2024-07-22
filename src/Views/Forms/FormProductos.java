package Views.Forms;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Cursor;

import com.toedter.calendar.JDateChooser;

import Controllers.ControllerProducto;
import DAO.ModelsDAO.Producto;
import HerramientasConexion.Herramientas;
import Models.ProductoView;
import Models.Respuesta;
import Utileria.ComponentesDesing;

import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.Format;
import java.time.LocalDate;
import java.awt.Color;

public class FormProductos extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField TFId;
	private JTextField TFCodigo;
	private JTextField TFCantidad;
	private JTextField TFDescripcion;
	private JTextField TFPPublico;
	private JTextField TFPAdquisicion;
	private JTextField TFPMayoreo;
	private JTextField TFCategoria;
	private JTextField TFNombre;
	private JTextField TFMarca;
	private JDateChooser DCFechaCaducidad;
	private JRadioButton RBFecha;
	private JSpinner SExistencia;
	private JButton B_Eliminar; 
	private JButton B_Grabar;
	//Locales
	private int tipoOperacion =0;
	private Producto producto = null;
	private ControllerProducto controllerProducto;
	private Respuesta respuesta;
	
	public static void main(String[] args) {
		try {
			
			Producto producto = new Producto();
			
			producto.setId_producto(1);
			producto.setCodigo("123qwe");
			producto.setNombre("Miguel");
			producto.setDescripcion("Nueva Descripción");
			producto.setCantidad("Cantidad de prueba");
			producto.setFecha_caducidad(Date.valueOf(LocalDate.now()));
			producto.setP_publico(100f);
			producto.setP_Mayoreo(80f);
			producto.setP_Adquisicion(60f);
			producto.setExistencia(10);
			producto.setCategoria("Nueva Categoría");
			producto.setMarca("Marca Nueva");
			
			FormProductos dialog = new FormProductos(Herramientas.tipoOperacion.insertar,producto);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FormProductos(int tipoOperacion, Producto producto) {
		setBounds(100, 100, 450, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel L_Formulario = new JLabel("Formulario Productos");
			L_Formulario.setFont(new Font("Segoe UI", Font.PLAIN, 20));
			L_Formulario.setHorizontalAlignment(SwingConstants.CENTER);
			L_Formulario.setBounds(10, 0, 434, 43);
			contentPanel.add(L_Formulario);
		}
		
		TFId = new JTextField();
		TFId.setBounds(127, 64, 260, 24);
		contentPanel.add(TFId);
		TFId.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Id");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(10, 64, 107, 24);
		contentPanel.add(lblNewLabel);
		{
			JLabel lblCdigo = new JLabel("Código");
			lblCdigo.setFont(new Font("Segoe UI", Font.PLAIN, 11));
			lblCdigo.setHorizontalAlignment(SwingConstants.RIGHT);
			lblCdigo.setBounds(10, 132, 107, 24);
			contentPanel.add(lblCdigo);
		}
		{
			TFCodigo = new JTextField();
			TFCodigo.setColumns(10);
			TFCodigo.setBounds(127, 132, 260, 24);
			contentPanel.add(TFCodigo);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Cantidad(Decrp.)");
			lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_1.setBounds(10, 200, 107, 24);
			contentPanel.add(lblNewLabel_1);
		}
		{
			TFCantidad = new JTextField();
			TFCantidad.setColumns(10);
			TFCantidad.setBounds(127, 200, 260, 24);
			contentPanel.add(TFCantidad);
		}
		{
			JLabel lblDescripcin = new JLabel("Descripción");
			lblDescripcin.setFont(new Font("Segoe UI", Font.PLAIN, 11));
			lblDescripcin.setHorizontalAlignment(SwingConstants.RIGHT);
			lblDescripcin.setBounds(10, 167, 107, 24);
			contentPanel.add(lblDescripcin);
		}
		{
			TFDescripcion = new JTextField();
			TFDescripcion.setColumns(10);
			TFDescripcion.setBounds(127, 167, 260, 24);
			contentPanel.add(TFDescripcion);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Precio Público");
			lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_1.setBounds(10, 266, 107, 24);
			contentPanel.add(lblNewLabel_1);
		}
		{
			TFPPublico = new JTextField();
			TFPPublico.setColumns(10);
			TFPPublico.setBounds(127, 266, 260, 24);
			contentPanel.add(TFPPublico);
		}
		{
			JLabel lblFechaCaducidad = new JLabel("Fecha Caducidad");
			lblFechaCaducidad.setFont(new Font("Segoe UI", Font.PLAIN, 11));
			lblFechaCaducidad.setHorizontalAlignment(SwingConstants.RIGHT);
			lblFechaCaducidad.setBounds(10, 233, 107, 24);
			contentPanel.add(lblFechaCaducidad);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Precio Adquisición");
			lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_1.setBounds(10, 334, 107, 24);
			contentPanel.add(lblNewLabel_1);
		}
		{
			TFPAdquisicion = new JTextField();
			TFPAdquisicion.setColumns(10);
			TFPAdquisicion.setBounds(127, 334, 260, 24);
			contentPanel.add(TFPAdquisicion);
		}
		{
			JLabel lblPrecioMayoreo = new JLabel("Precio Mayoreo");
			lblPrecioMayoreo.setFont(new Font("Segoe UI", Font.PLAIN, 11));
			lblPrecioMayoreo.setHorizontalAlignment(SwingConstants.RIGHT);
			lblPrecioMayoreo.setBounds(10, 301, 107, 24);
			contentPanel.add(lblPrecioMayoreo);
		}
		{
			TFPMayoreo = new JTextField();
			TFPMayoreo.setColumns(10);
			TFPMayoreo.setBounds(127, 301, 260, 24);
			contentPanel.add(TFPMayoreo);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Categoria");
			lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_1.setBounds(10, 402, 107, 24);
			contentPanel.add(lblNewLabel_1);
		}
		{
			TFCategoria = new JTextField();
			TFCategoria.setColumns(10);
			TFCategoria.setBounds(127, 402, 260, 24);
			contentPanel.add(TFCategoria);
		}
		{
			JLabel lblExistencia = new JLabel("Existencia");
			lblExistencia.setFont(new Font("Segoe UI", Font.PLAIN, 11));
			lblExistencia.setHorizontalAlignment(SwingConstants.RIGHT);
			lblExistencia.setBounds(10, 369, 107, 24);
			contentPanel.add(lblExistencia);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Nombre");
			lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_1.setBounds(10, 99, 107, 24);
			contentPanel.add(lblNewLabel_1);
		}
		{
			TFNombre = new JTextField();
			TFNombre.setColumns(10);
			TFNombre.setBounds(127, 99, 260, 24);
			contentPanel.add(TFNombre);
		}
		{
			JLabel lblMarca = new JLabel("Marca");
			lblMarca.setFont(new Font("Segoe UI", Font.PLAIN, 11));
			lblMarca.setHorizontalAlignment(SwingConstants.RIGHT);
			lblMarca.setBounds(10, 437, 107, 24);
			contentPanel.add(lblMarca);
		}
		{
			TFMarca = new JTextField();
			TFMarca.setColumns(10);
			TFMarca.setBounds(127, 437, 260, 24);
			contentPanel.add(TFMarca);
		}
		
		DCFechaCaducidad = new JDateChooser();
		DCFechaCaducidad.setBounds(127, 231, 260, 24);
		contentPanel.add(DCFechaCaducidad);
		
		SExistencia = new JSpinner();
		SExistencia.setBounds(127, 367, 260, 24);
		contentPanel.add(SExistencia);
		
		RBFecha = new JRadioButton("Perecedero");
		RBFecha.setBackground(new Color(255, 255, 255));
		RBFecha.setHorizontalAlignment(SwingConstants.RIGHT);
		RBFecha.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		RBFecha.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CambioEstadoFecha(e);
			}
		});
		RBFecha.setBounds(297, 34, 90, 23);
		contentPanel.add(RBFecha);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 255, 255));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				B_Grabar = new JButton("Grabar");
				B_Grabar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						grabarRegistro();
					}
				});
				{
					B_Eliminar = new JButton("Eliminar");
					B_Eliminar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
						}
					});
					B_Eliminar.setForeground(new Color(255, 0, 0));
					B_Eliminar.setActionCommand("OK");
					buttonPane.add(B_Eliminar);
				}
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
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		//locales
		this.tipoOperacion = tipoOperacion;
		this.producto = producto;
		controllerProducto = new ControllerProducto();
		configuracionPantalla();
		inicializarPantalla();
	}
	
	public void inicializarPantalla() {
		
		if(producto != null && tipoOperacion!=Herramientas.tipoOperacion.insertar) {
			TFId.setText(producto.getId_producto()+"");
			TFNombre.setText(producto.getNombre());
			TFCodigo.setText(producto.getCodigo());
			TFDescripcion.setText(producto.getDescripcion());
			TFCantidad.setText(producto.getCantidad());
			DCFechaCaducidad.setDate(new java.util.Date(producto.getFecha_caducidad().getTime()));
			TFPPublico.setText(producto.getP_publico()+"");
			TFPMayoreo.setText(producto.getP_Mayoreo()+"");
			TFPAdquisicion.setText(producto.getP_Adquisicion()+"");
			SExistencia.setValue((Object) producto.getExistencia());
			TFCategoria.setText(producto.getCategoria());
			TFMarca.setText(producto.getMarca());
		}
	}
	
	public void configuracionPantalla() {
		
		ComponentesDesing.textFieldDeshabilitar(TFId);
		
		if (tipoOperacion == Herramientas.tipoOperacion.insertar) {
			B_Grabar.setText("Agregar");
			B_Eliminar.setVisible(false);
		}else if(tipoOperacion == Herramientas.tipoOperacion.actualizar) {
			B_Grabar.setText("Actualizar");
		}else if(tipoOperacion == Herramientas.tipoOperacion.eliminar) {
			B_Grabar.setVisible(false);
			ComponentesDesing.textFieldDeshabilitar(TFNombre);
			ComponentesDesing.textFieldDeshabilitar(TFCodigo);
			ComponentesDesing.textFieldDeshabilitar(TFDescripcion);
			ComponentesDesing.textFieldDeshabilitar(TFCantidad);
			ComponentesDesing.JDatachoser(DCFechaCaducidad);
			ComponentesDesing.textFieldDeshabilitar(TFPPublico);
			ComponentesDesing.textFieldDeshabilitar(TFPMayoreo);
			ComponentesDesing.textFieldDeshabilitar(TFPAdquisicion);
			ComponentesDesing.JSnipperDesHabilitar(SExistencia);
			ComponentesDesing.textFieldDeshabilitar(TFCategoria);
			ComponentesDesing.textFieldDeshabilitar(TFMarca);
			ComponentesDesing.JRadioButtonDeshabilitar(RBFecha);
		}
		
	}
	
	public void CambioEstadoFecha(MouseEvent e) {
		
		if(RBFecha.isSelected()) { 
			ComponentesDesing.JDatachoser(DCFechaCaducidad);
			DCFechaCaducidad.setDate(null);}
		else
			DCFechaCaducidad.setEnabled(true);
	}
	
	public void grabarRegistro() {
		
		ProductoView productoView = new ProductoView();		
		respuesta = new Respuesta("",true,null);
		
		productoView.setId_producto(TFId.getText());
		productoView.setCodigo(TFCodigo.getText());
		productoView.setNombre(TFNombre.getText());
		productoView.setDescripcion(TFDescripcion.getText());
		productoView.setCantidad(TFCantidad.getText());
		
		if(!RBFecha.isSelected())
			productoView.setFecha_caducidad( Herramientas.convertirFecha(DCFechaCaducidad));
		else
			productoView.setFecha_caducidad(null);
		
		productoView.setP_publico(TFPPublico.getText());
		productoView.setP_Mayoreo(TFPMayoreo.getText());
		productoView.setP_Adquisicion(TFPAdquisicion.getText());
		productoView.setExistencia(""+SExistencia.getValue());
		productoView.setCategoria(TFCategoria.getText());
		productoView.setMarca(TFMarca.getText());
		
		Cursor cursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
        setCursor(cursor);		
		respuesta = controllerProducto.proceso(tipoOperacion, productoView);
		cursor = Cursor.getDefaultCursor();
		setCursor(cursor);
		
		JOptionPane.showMessageDialog(this, respuesta.getMensaje());
		
	}
}

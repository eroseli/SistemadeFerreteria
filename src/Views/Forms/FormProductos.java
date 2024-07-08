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

import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class FormProductos extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField TFId;
	private JTextField JTCodigo;
	private JTextField JTCantidad;
	private JTextField JTDescripcion;
	private JTextField JTPPublico;
	private JTextField JTPAdquisicion;
	private JTextField JTPMayoreo;
	private JTextField JTCategoria;
	private JTextField JTNombre;
	private JTextField JTMarca;
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
			FormProductos dialog = new FormProductos(Herramientas.tipoOperacion.actualizar,null);
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
			JTCodigo = new JTextField();
			JTCodigo.setColumns(10);
			JTCodigo.setBounds(127, 132, 260, 24);
			contentPanel.add(JTCodigo);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Cantidad(Decrp.)");
			lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_1.setBounds(10, 200, 107, 24);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JTCantidad = new JTextField();
			JTCantidad.setColumns(10);
			JTCantidad.setBounds(127, 200, 260, 24);
			contentPanel.add(JTCantidad);
		}
		{
			JLabel lblDescripcin = new JLabel("Descripción");
			lblDescripcin.setFont(new Font("Segoe UI", Font.PLAIN, 11));
			lblDescripcin.setHorizontalAlignment(SwingConstants.RIGHT);
			lblDescripcin.setBounds(10, 167, 107, 24);
			contentPanel.add(lblDescripcin);
		}
		{
			JTDescripcion = new JTextField();
			JTDescripcion.setColumns(10);
			JTDescripcion.setBounds(127, 167, 260, 24);
			contentPanel.add(JTDescripcion);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Precio Público");
			lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_1.setBounds(10, 266, 107, 24);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JTPPublico = new JTextField();
			JTPPublico.setColumns(10);
			JTPPublico.setBounds(127, 266, 260, 24);
			contentPanel.add(JTPPublico);
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
			JTPAdquisicion = new JTextField();
			JTPAdquisicion.setColumns(10);
			JTPAdquisicion.setBounds(127, 334, 260, 24);
			contentPanel.add(JTPAdquisicion);
		}
		{
			JLabel lblPrecioMayoreo = new JLabel("Precio Mayoreo");
			lblPrecioMayoreo.setFont(new Font("Segoe UI", Font.PLAIN, 11));
			lblPrecioMayoreo.setHorizontalAlignment(SwingConstants.RIGHT);
			lblPrecioMayoreo.setBounds(10, 301, 107, 24);
			contentPanel.add(lblPrecioMayoreo);
		}
		{
			JTPMayoreo = new JTextField();
			JTPMayoreo.setColumns(10);
			JTPMayoreo.setBounds(127, 301, 260, 24);
			contentPanel.add(JTPMayoreo);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Categoria");
			lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_1.setBounds(10, 402, 107, 24);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JTCategoria = new JTextField();
			JTCategoria.setColumns(10);
			JTCategoria.setBounds(127, 402, 260, 24);
			contentPanel.add(JTCategoria);
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
			JTNombre = new JTextField();
			JTNombre.setColumns(10);
			JTNombre.setBounds(127, 99, 260, 24);
			contentPanel.add(JTNombre);
		}
		{
			JLabel lblMarca = new JLabel("Marca");
			lblMarca.setFont(new Font("Segoe UI", Font.PLAIN, 11));
			lblMarca.setHorizontalAlignment(SwingConstants.RIGHT);
			lblMarca.setBounds(10, 437, 107, 24);
			contentPanel.add(lblMarca);
		}
		{
			JTMarca = new JTextField();
			JTMarca.setColumns(10);
			JTMarca.setBounds(127, 437, 260, 24);
			contentPanel.add(JTMarca);
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
	}
	
	public void configuracionPantalla() {
		
		if (tipoOperacion == Herramientas.tipoOperacion.insertar) {
			B_Grabar.setText("Agregar");
			B_Eliminar.setVisible(false);
		}else if(tipoOperacion == Herramientas.tipoOperacion.actualizar) {
			B_Grabar.setText("Actualizar");
		}else if(tipoOperacion == Herramientas.tipoOperacion.eliminar) {
			B_Grabar.setVisible(false);
		}
		
	}
	
	public void CambioEstadoFecha(MouseEvent e) {
		
		if(RBFecha.isSelected()) { 
			DCFechaCaducidad.setEnabled(false);
			DCFechaCaducidad.setDate(null);}
		else
			DCFechaCaducidad.setEnabled(true);
	}
	
	public void grabarRegistro() {
		
		ProductoView productoView = new ProductoView();		
		respuesta = new Respuesta("",true,null);
		
		productoView.setId_producto(TFId.getText());
		productoView.setCodigo(JTCodigo.getText());
		productoView.setNombre(JTNombre.getText());
		productoView.setDescripcion(JTDescripcion.getText());
		productoView.setCantidad(JTCantidad.getText());
		
		if(!RBFecha.isSelected())
			productoView.setFecha_caducidad( Herramientas.convertirFecha(DCFechaCaducidad));
		else
			productoView.setFecha_caducidad(null);
		
		productoView.setP_publico(JTPPublico.getText());
		productoView.setP_Mayoreo(JTPMayoreo.getText());
		productoView.setP_Adquisicion(JTPAdquisicion.getText());
		productoView.setExistencia(""+SExistencia.getValue());
		productoView.setCategoria(JTCategoria.getText());
		productoView.setMarca(JTMarca.getText());
		
		Cursor cursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
        setCursor(cursor);		
		respuesta = controllerProducto.proceso(tipoOperacion, productoView);
		cursor = Cursor.getDefaultCursor();
		setCursor(cursor);
		
		JOptionPane.showMessageDialog(this, respuesta.getMensaje());
		
	}
}

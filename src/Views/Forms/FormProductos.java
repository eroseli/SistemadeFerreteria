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
import java.awt.ComponentOrientation;
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

public class FormProductos extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField TFId;
	private JTextField JTCodigo;
	private JTextField JTCantidad;
	private JTextField JTDescripcion;
	private JTextField textField_4;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField JTNombre;
	private JTextField textField_11;
	private JDateChooser DCFechaCaducidad;
	private JRadioButton RBFecha;
	//Locales
	private int tipoOperacion =0;
	private Producto producto = null;
	private ControllerProducto controllerProducto;
	private Respuesta respuesta;
	
	public static void main(String[] args) {
		try {
			FormProductos dialog = new FormProductos(Herramientas.tipoOperacion.insertar,null);
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
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(10, 64, 107, 24);
		contentPanel.add(lblNewLabel);
		{
			JLabel lblCdigo = new JLabel("Código");
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
			JLabel lblNewLabel_1 = new JLabel("Cantidad(Descripción)");
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
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_1.setBounds(10, 266, 107, 24);
			contentPanel.add(lblNewLabel_1);
		}
		{
			textField_4 = new JTextField();
			textField_4.setColumns(10);
			textField_4.setBounds(127, 266, 260, 24);
			contentPanel.add(textField_4);
		}
		{
			JLabel lblFechaCaducidad = new JLabel("Fecha Caducidad");
			lblFechaCaducidad.setHorizontalAlignment(SwingConstants.RIGHT);
			lblFechaCaducidad.setBounds(10, 233, 107, 24);
			contentPanel.add(lblFechaCaducidad);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Precio Adquisición");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_1.setBounds(10, 334, 107, 24);
			contentPanel.add(lblNewLabel_1);
		}
		{
			textField_6 = new JTextField();
			textField_6.setColumns(10);
			textField_6.setBounds(127, 334, 260, 24);
			contentPanel.add(textField_6);
		}
		{
			JLabel lblPrecioMayoreo = new JLabel("Precio Mayoreo");
			lblPrecioMayoreo.setHorizontalAlignment(SwingConstants.RIGHT);
			lblPrecioMayoreo.setBounds(10, 301, 107, 24);
			contentPanel.add(lblPrecioMayoreo);
		}
		{
			textField_7 = new JTextField();
			textField_7.setColumns(10);
			textField_7.setBounds(127, 301, 260, 24);
			contentPanel.add(textField_7);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Categoria");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_1.setBounds(10, 402, 107, 24);
			contentPanel.add(lblNewLabel_1);
		}
		{
			textField_8 = new JTextField();
			textField_8.setColumns(10);
			textField_8.setBounds(127, 402, 260, 24);
			contentPanel.add(textField_8);
		}
		{
			JLabel lblExistencia = new JLabel("Existencia");
			lblExistencia.setHorizontalAlignment(SwingConstants.RIGHT);
			lblExistencia.setBounds(10, 369, 107, 24);
			contentPanel.add(lblExistencia);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Nombre");
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
			lblMarca.setHorizontalAlignment(SwingConstants.RIGHT);
			lblMarca.setBounds(10, 437, 107, 24);
			contentPanel.add(lblMarca);
		}
		{
			textField_11 = new JTextField();
			textField_11.setColumns(10);
			textField_11.setBounds(127, 437, 260, 24);
			contentPanel.add(textField_11);
		}
		
		DCFechaCaducidad = new JDateChooser();
		DCFechaCaducidad.setBounds(127, 231, 260, 24);
		contentPanel.add(DCFechaCaducidad);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(127, 367, 260, 24);
		contentPanel.add(spinner);
		
		RBFecha = new JRadioButton("Perecedero");
		RBFecha.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CambioEstadoFecha(e);
			}
		});
		RBFecha.setBounds(306, 34, 81, 23);
		contentPanel.add(RBFecha);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		//locales
		this.tipoOperacion = tipoOperacion;
		this.producto = producto;
		controllerProducto = new ControllerProducto();
	}
	
	public void CambioEstadoFecha(MouseEvent e) {
		
		if(RBFecha.isSelected()) 
			DCFechaCaducidad.setEnabled(false);
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
		
		if(RBFecha.isSelected())
			productoView.setFecha_caducidad( Herramientas.convertirFecha(DCFechaCaducidad));
		else
			productoView.setFecha_caducidad(null);
		
		productoView.setId_producto(TFId.getText());
		productoView.setId_producto(TFId.getText());
		productoView.setId_producto(TFId.getText());
		productoView.setId_producto(TFId.getText());
		productoView.setId_producto(TFId.getText());
		productoView.setId_producto(TFId.getText());
		
		respuesta = controllerProducto.proceso(Herramientas.tipoOperacion.insertar, productoView);
	
		JOptionPane.showMessageDialog(this, respuesta.getMensaje());
		
	}
}

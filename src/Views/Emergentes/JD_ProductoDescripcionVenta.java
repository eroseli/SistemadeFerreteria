package Views.Emergentes;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.transform.TransformerFactoryConfigurationError;

import Controllers.ControllerVenta;
import DAO.ModelsDAO.ProductosVentaDAO;
import Models.Respuesta;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

public class JD_ProductoDescripcionVenta extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField TFCodigo;
	private JTextField TFNombre;
	private JTextField TFCantidad;
	private JTextField TFPrecio;
	private JTextField TFDescuentoM;
	private JTextField DescuentoEsp;
	private JTextField TFFechaRegistro;
	private JTextArea TADescripcion;
	
	private int idHistorial;
	private Respuesta respuesta = null;
	
	public JD_ProductoDescripcionVenta(int idHistorial) {
		setBounds(100, 100, 450, 350);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(255, 255, 255));
			panel.setBounds(0, 0, 450, 283);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("Código");
				lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNewLabel.setBounds(66, 37, 125, 16);
				panel.add(lblNewLabel);
			}
			
			JLabel lblNombre = new JLabel("Nombre");
			lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNombre.setBounds(66, 65, 125, 16);
			panel.add(lblNombre);
			
			JLabel lblDescripcin = new JLabel("Descripción");
			lblDescripcin.setHorizontalAlignment(SwingConstants.RIGHT);
			lblDescripcin.setBounds(66, 93, 125, 16);
			panel.add(lblDescripcin);
			
			JLabel lblCantidad = new JLabel("Cantidad");
			lblCantidad.setHorizontalAlignment(SwingConstants.RIGHT);
			lblCantidad.setBounds(66, 143, 125, 16);
			panel.add(lblCantidad);
			
			JLabel lblPrecio = new JLabel("Precio");
			lblPrecio.setHorizontalAlignment(SwingConstants.RIGHT);
			lblPrecio.setBounds(66, 171, 125, 16);
			panel.add(lblPrecio);
			
			JLabel lblDescuentoM = new JLabel("Descuento M.");
			lblDescuentoM.setHorizontalAlignment(SwingConstants.RIGHT);
			lblDescuentoM.setBounds(66, 197, 125, 16);
			panel.add(lblDescuentoM);
			
			JLabel lblDescuentoEsp = new JLabel("Descuento Esp.");
			lblDescuentoEsp.setHorizontalAlignment(SwingConstants.RIGHT);
			lblDescuentoEsp.setBounds(66, 225, 125, 16);
			panel.add(lblDescuentoEsp);
			
			JLabel lblFechaDeRegistro = new JLabel("Fecha de Registro");
			lblFechaDeRegistro.setHorizontalAlignment(SwingConstants.RIGHT);
			lblFechaDeRegistro.setBounds(66, 256, 125, 16);
			panel.add(lblFechaDeRegistro);
			
			TFCodigo = new JTextField();
			TFCodigo.setBounds(208, 32, 165, 26);
			panel.add(TFCodigo);
			TFCodigo.setColumns(10);
			
			TFNombre = new JTextField();
			TFNombre.setColumns(10);
			TFNombre.setBounds(208, 60, 165, 26);
			panel.add(TFNombre);
			
			TFCantidad = new JTextField();
			TFCantidad.setColumns(10);
			TFCantidad.setBounds(208, 138, 165, 26);
			panel.add(TFCantidad);
			
			TFPrecio = new JTextField();
			TFPrecio.setColumns(10);
			TFPrecio.setBounds(208, 166, 165, 26);
			panel.add(TFPrecio);
			
			TFDescuentoM = new JTextField();
			TFDescuentoM.setColumns(10);
			TFDescuentoM.setBounds(208, 192, 165, 26);
			panel.add(TFDescuentoM);
			
			DescuentoEsp = new JTextField();
			DescuentoEsp.setColumns(10);
			DescuentoEsp.setBounds(208, 220, 165, 26);
			panel.add(DescuentoEsp);
			
			TFFechaRegistro = new JTextField();
			TFFechaRegistro.setColumns(10);
			TFFechaRegistro.setBounds(208, 251, 165, 26);
			panel.add(TFFechaRegistro);
			
			TADescripcion = new JTextArea();
			TADescripcion.setBounds(203, 91, 170, 40);
			panel.add(TADescripcion);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
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
		
		this.idHistorial = idHistorial;
		CconfiguracionPantalla();
		obtenerValoresPantalla();
		
	}
	
	public void CconfiguracionPantalla() {
		TFCodigo.setEditable(false);
		TFNombre.setEditable(false);
		TFCantidad.setEditable(false);
		TFPrecio.setEditable(false);
		TFDescuentoM.setEditable(false);
		DescuentoEsp.setEditable(false);
		TFFechaRegistro.setEditable(false);
		
		TADescripcion.setLineWrap(true);
		TADescripcion.setWrapStyleWord(true);
		TADescripcion.setEditable(false);
	}
	
	public void obtenerValoresPantalla() {
		
		ControllerVenta controllerVenta = new ControllerVenta();
		respuesta = new Respuesta("",true,null);
		ProductosVentaDAO productoVentaDAO = null;
		
		respuesta = controllerVenta.obtenerHistorialProducto(idHistorial);
		
		if (respuesta.getRespuesta() == null) {
			return;
		}
		
		productoVentaDAO = (ProductosVentaDAO) respuesta.getRespuesta();
		
		TFCodigo.setText(productoVentaDAO.getCodigo());
		TFNombre.setText(productoVentaDAO.getNombre());
		TADescripcion.setText(productoVentaDAO.getDescripcion());
		TFCantidad.setText("" + productoVentaDAO.getCantidad());
		TFPrecio.setText(productoVentaDAO.getPrecio()+"");
		TFDescuentoM.setText(productoVentaDAO.getDescuentoM());
		DescuentoEsp.setText(productoVentaDAO.getDescuentoEsp());
		TFFechaRegistro.setText(productoVentaDAO.getFechaRegistro()+"");
		
		
	}
}

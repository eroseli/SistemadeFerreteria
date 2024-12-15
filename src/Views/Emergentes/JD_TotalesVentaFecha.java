package Views.Emergentes;

import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.sql.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Controllers.ControllerVenta;
import DAO.ModelsDAO.VentaTotalesDAO;
import HerramientasConexion.Herramientas;
import Models.Respuesta;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.text.SimpleDateFormat;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JD_TotalesVentaFecha extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField TFFechaInicio;
	private JTextField TFFechaFinal;
	private JTextField TFTotalVentas;
	private JTextField TFTotalProductos;
	private JTextField TFMontoVenta;
	private JTextField TFDescuentoAplicado;
	private JTextField TFTicketPromedio;
	private JTextArea TAProductoEstrella;
	
	// variables de constructor
	private Date fechaInicio;
	private Date fechaFinal;
	private Respuesta respuesta = new Respuesta("",false,null);
	
	public static void main(String[] args) {
		try {
			JD_TotalesVentaFecha dialog = new JD_TotalesVentaFecha(null,null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public JD_TotalesVentaFecha(Date fechaInicio, Date fechaFinal) {
		
		// Configuracion pantalla
		setLocationRelativeTo(null);
		
		setBounds(100, 100, 450, 425);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBounds(0, 0, 450, 358);
			contentPanel.add(panel);
			panel.setLayout(null);
			
			JLabel LblFechaInicio = new JLabel("Fecha de Inicio");
			LblFechaInicio.setBounds(63, 24, 148, 16);
			panel.add(LblFechaInicio);
			
			TFFechaInicio = new JTextField();
			TFFechaInicio.setDisabledTextColor(new Color(69, 69, 69));
			TFFechaInicio.setCaretColor(new Color(69, 69, 69));
			TFFechaInicio.setSelectedTextColor(new Color(69, 69, 69));
			TFFechaInicio.setEnabled(false);
			TFFechaInicio.setBounds(218, 19, 180, 26);
			panel.add(TFFechaInicio);
			TFFechaInicio.setColumns(10);
			
			TFFechaFinal = new JTextField();
			TFFechaFinal.setDisabledTextColor(new Color(69, 69, 69));
			TFFechaFinal.setEnabled(false);
			TFFechaFinal.setColumns(10);
			TFFechaFinal.setBounds(218, 52, 180, 26);
			panel.add(TFFechaFinal);
			
			JLabel LblFechaFinal = new JLabel("Fecha Final");
			LblFechaFinal.setBounds(63, 57, 148, 16);
			panel.add(LblFechaFinal);
			
			TFTotalVentas = new JTextField();
			TFTotalVentas.setDisabledTextColor(new Color(69, 69, 69));
			TFTotalVentas.setEnabled(false);
			TFTotalVentas.setColumns(10);
			TFTotalVentas.setBounds(218, 90, 180, 26);
			panel.add(TFTotalVentas);
			
			JLabel LblTotalVentas = new JLabel("Total de Ventas");
			LblTotalVentas.setBounds(63, 95, 148, 16);
			panel.add(LblTotalVentas);
			
			TFTotalProductos = new JTextField();
			TFTotalProductos.setDisabledTextColor(new Color(69, 69, 69));
			TFTotalProductos.setEnabled(false);
			TFTotalProductos.setColumns(10);
			TFTotalProductos.setBounds(218, 134, 180, 26);
			panel.add(TFTotalProductos);
			
			JLabel BlbNumeroTotalProductos = new JLabel("Total de Productos");
			BlbNumeroTotalProductos.setBounds(63, 139, 148, 16);
			panel.add(BlbNumeroTotalProductos);
			
			TFMontoVenta = new JTextField();
			TFMontoVenta.setDisabledTextColor(new Color(69, 69, 69));
			TFMontoVenta.setEnabled(false);
			TFMontoVenta.setColumns(10);
			TFMontoVenta.setBounds(218, 172, 180, 26);
			panel.add(TFMontoVenta);
			
			JLabel LblTotalVentaDinero = new JLabel("Monto Total de Venta");
			LblTotalVentaDinero.setBounds(63, 177, 148, 16);
			panel.add(LblTotalVentaDinero);
			
			TFDescuentoAplicado = new JTextField();
			TFDescuentoAplicado.setDisabledTextColor(new Color(69, 69, 69));
			TFDescuentoAplicado.setEnabled(false);
			TFDescuentoAplicado.setColumns(10);
			TFDescuentoAplicado.setBounds(218, 210, 180, 26);
			panel.add(TFDescuentoAplicado);
			
			JLabel LblDescuentos = new JLabel("Descuentos Aplicados");
			LblDescuentos.setBounds(63, 215, 148, 16);
			panel.add(LblDescuentos);
			
			JLabel LblTicketPromedio = new JLabel("Ticket Promedio");
			LblTicketPromedio.setBounds(63, 309, 148, 16);
			panel.add(LblTicketPromedio);
			
			TFTicketPromedio = new JTextField();
			TFTicketPromedio.setDisabledTextColor(new Color(69, 69, 69));
			TFTicketPromedio.setEnabled(false);
			TFTicketPromedio.setColumns(10);
			TFTicketPromedio.setBounds(218, 304, 180, 26);
			panel.add(TFTicketPromedio);
			
			JLabel LblProductoEstrella = new JLabel("Producto Estrella");
			LblProductoEstrella.setBounds(63, 253, 148, 16);
			panel.add(LblProductoEstrella);
			
			TAProductoEstrella = new JTextArea();
			TAProductoEstrella.setDisabledTextColor(new Color(69, 69, 69));
			TAProductoEstrella.setSelectedTextColor(new Color(69, 69, 69));
			TAProductoEstrella.setEnabled(false);
			TAProductoEstrella.setBounds(218, 248, 180, 44);
			TAProductoEstrella.setLineWrap(true); // Ajustar el texto a la línea
			TAProductoEstrella.setWrapStyleWord(true); // Ajustar por palabra
			//TAProductoEstrella.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Añadir espacio interno

	        // Crear un JScrollPane y añadir el JTextArea
	        //JScrollPane scrollPane = new JScrollPane(TAProductoEstrella);
	        
			panel.add(TAProductoEstrella);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton BCerrar = new JButton("Cerrrar");
				BCerrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				BCerrar.setActionCommand("Cancel");
				buttonPane.add(BCerrar);
			}
		}
		
		//fechas
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
		obtenerValoresVenta();
		cargarDatosEmpresa();
		cargarDatosUsuario();
	}
	
	public void cargarDatosEmpresa() {
		
	}
	
	public void cargarDatosUsuario() {
		
	}
	
	
	public void obtenerValoresVenta() {
		
		ControllerVenta controllerVenta = new ControllerVenta();
		respuesta = controllerVenta.obtenerTotalesDescripcionVentas(fechaInicio, fechaFinal);
		VentaTotalesDAO ventatotalesDao = new VentaTotalesDAO();
		
		if (!respuesta.getValor()) {
			JOptionPane.showMessageDialog(null, respuesta.getMensaje(), "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		ventatotalesDao = (VentaTotalesDAO) respuesta.getRespuesta();

		TFFechaInicio.setText(new SimpleDateFormat("E, dd MMM yyyy").format(ventatotalesDao.getFechaInicio()));
		TFFechaFinal.setText(new SimpleDateFormat("E, dd MMM yyyy").format(ventatotalesDao.getFechaFinal()));
		TFTotalVentas.setText(ventatotalesDao.getTotalVentas()+"");
		TFTotalProductos.setText(ventatotalesDao.getTotalProductos()+"");
		TFMontoVenta.setText( Herramientas.formatoDinero(ventatotalesDao.getMontoTotalVenta()) );
		TFDescuentoAplicado.setText(((int)ventatotalesDao.getDescuentoAplicado())+"");
		TFTicketPromedio.setText(Herramientas.formatoDinero( ventatotalesDao.getTicketPromedio()));
		TAProductoEstrella.setText(ventatotalesDao.getProductoEstrella());
		
	}
}

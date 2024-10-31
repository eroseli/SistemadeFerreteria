package Views.Emergentes;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

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
	private JTextField TFProductoEstrella;

	
	// variables de constructor
	private Date fechaInicio;
	private Date fechaFinal;
	
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
			TFFechaInicio.setBounds(218, 19, 180, 26);
			panel.add(TFFechaInicio);
			TFFechaInicio.setColumns(10);
			
			TFFechaFinal = new JTextField();
			TFFechaFinal.setColumns(10);
			TFFechaFinal.setBounds(218, 52, 180, 26);
			panel.add(TFFechaFinal);
			
			JLabel LblFechaFinal = new JLabel("Fecha Final");
			LblFechaFinal.setBounds(63, 57, 148, 16);
			panel.add(LblFechaFinal);
			
			TFTotalVentas = new JTextField();
			TFTotalVentas.setColumns(10);
			TFTotalVentas.setBounds(218, 90, 180, 26);
			panel.add(TFTotalVentas);
			
			JLabel LblTotalVentas = new JLabel("Total de Ventas");
			LblTotalVentas.setBounds(63, 95, 148, 16);
			panel.add(LblTotalVentas);
			
			TFTotalProductos = new JTextField();
			TFTotalProductos.setColumns(10);
			TFTotalProductos.setBounds(218, 134, 180, 26);
			panel.add(TFTotalProductos);
			
			JLabel BlbNumeroTotalProductos = new JLabel("Total de Productos");
			BlbNumeroTotalProductos.setBounds(63, 139, 148, 16);
			panel.add(BlbNumeroTotalProductos);
			
			TFMontoVenta = new JTextField();
			TFMontoVenta.setColumns(10);
			TFMontoVenta.setBounds(218, 172, 180, 26);
			panel.add(TFMontoVenta);
			
			JLabel LblTotalVentaDinero = new JLabel("Monto Total de Venta");
			LblTotalVentaDinero.setBounds(63, 177, 148, 16);
			panel.add(LblTotalVentaDinero);
			
			TFDescuentoAplicado = new JTextField();
			TFDescuentoAplicado.setColumns(10);
			TFDescuentoAplicado.setBounds(218, 210, 180, 26);
			panel.add(TFDescuentoAplicado);
			
			JLabel LblDescuentos = new JLabel("Descuentos Aplicados");
			LblDescuentos.setBounds(63, 215, 148, 16);
			panel.add(LblDescuentos);
			
			JLabel LblTicketPromedio = new JLabel("Ticket Promedio");
			LblTicketPromedio.setBounds(63, 291, 148, 16);
			panel.add(LblTicketPromedio);
			
			TFTicketPromedio = new JTextField();
			TFTicketPromedio.setColumns(10);
			TFTicketPromedio.setBounds(218, 286, 180, 26);
			panel.add(TFTicketPromedio);
			
			TFProductoEstrella = new JTextField();
			TFProductoEstrella.setColumns(10);
			TFProductoEstrella.setBounds(218, 248, 180, 26);
			panel.add(TFProductoEstrella);
			
			JLabel LblProductoEstrella = new JLabel("Producto Estrella");
			LblProductoEstrella.setBounds(63, 253, 148, 16);
			panel.add(LblProductoEstrella);
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
		
		//fechas
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
		
	}
	
	public void obtenerValoresVenta() {
		
		
		
	}
	
}

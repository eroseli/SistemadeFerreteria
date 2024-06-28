package Views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import HerramientasConexion.Herramientas;
import HerramientasConexion.Herramientas.cadenas;
import Models.ProductoVenta;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JD_DescripcionProducto extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField TFDescripcion;
	private JTextField TF_Nombre;
	private JSpinner S_Cantidad;
	private JRadioButton RB_Mayoreo;
	private ProductoVenta producto;
	private  JF_Venta jf_Venta;
	private int indice;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JD_DescripcionProducto dialog = new JD_DescripcionProducto(null,null,0);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JD_DescripcionProducto(JF_Venta jf_Venta ,ProductoVenta producto, int indice ) {
		setBackground(new Color(246, 251, 251));
		setBounds(100, 100, 420, 449);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			TFDescripcion = new JTextField();
			TFDescripcion.setBounds(62, 124, 300, 26);
			contentPanel.add(TFDescripcion);
			TFDescripcion.setColumns(10);
		}
		
		TF_Nombre = new JTextField();
		TF_Nombre.setColumns(10);
		TF_Nombre.setBounds(62, 73, 300, 26);
		contentPanel.add(TF_Nombre);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(62, 55, 61, 16);
		contentPanel.add(lblNewLabel);
		
		JLabel lblDescripcio = new JLabel("Descripción");
		lblDescripcio.setBounds(62, 107, 137, 16);
		contentPanel.add(lblDescripcio);
		
		RB_Mayoreo = new JRadioButton("Aplicar Mayoreo");
		RB_Mayoreo.setBounds(58, 211, 141, 23);
		contentPanel.add(RB_Mayoreo);
		
        SpinnerNumberModel model = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
		S_Cantidad = new JSpinner(model);
		S_Cantidad.setBounds(62, 175, 300, 26);
		contentPanel.add(S_Cantidad);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 420, 421);
		contentPanel.add(panel);
		
		JLabel lblNewLabel_1 = new JLabel("Descripción del Producto");
		lblNewLabel_1.setForeground(new Color(66, 66, 66));
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		panel.add(lblNewLabel_1);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(62, 157, 137, 16);
		contentPanel.add(lblCantidad);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						actualizarProducto();
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
		
		this.producto = producto;
		this.jf_Venta = jf_Venta;
		this.indice = indice;
		cargarPantalla();
		
	}
	
	public void actualizarProducto() {
		
		this.producto.setCantidadComprar((int) S_Cantidad.getValue());
		this.producto.setDescuentoM( RB_Mayoreo.isSelected()? Herramientas.cadenas.CadenaSi:Herramientas.cadenas.CadenaNo );
		
		jf_Venta.productosVenta.set(indice, producto);
		jf_Venta.llenarTabla();
		this.dispose();
		
	}
	
	public void cargarPantalla() {
		
		TF_Nombre.setText(producto.getNombre());
		TFDescripcion.setText(producto.getDescripcion());
		S_Cantidad.setValue(producto.getCantidadComprar());
		
		if (producto.getDescuentoM().equals(Herramientas.cadenas.CadenaSi)) {
			RB_Mayoreo.setSelected(true);
		}
		
	}
}

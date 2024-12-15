package Views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import HerramientasConexion.Configuracion;
import HerramientasConexion.Herramientas;
import HerramientasConexion.Herramientas.cadenas;
import Models.ProductoVenta;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import javax.swing.border.BevelBorder;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class JD_DescripcionProducto extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField TF_Nombre;
	private JSpinner S_Cantidad;
	private JRadioButton RB_Mayoreo;
	private ProductoVenta producto;
	private  JF_Venta jf_Venta;
	private int indice;
	private JTextField TFExistencia;
	private JTextField TFCodigo;
	private JTextArea TAPresentacion;
	private JTextArea TADescripcion;
		
	private Configuracion configuracion;
	private JTextField TFPrecio;

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
		setBounds(100, 100, 420, 483);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
        SpinnerNumberModel model = new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1);
		
		JPanel panel = new JPanel();
		panel.setAutoscrolls(true);
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 420, 416);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel LblCantidad = new JLabel("Cantidad");
		LblCantidad.setBounds(64, 309, 294, 16);
		panel.add(LblCantidad);
		
		JLabel lblNewLabel_1 = new JLabel("Descripción del Producto");
		lblNewLabel_1.setAutoscrolls(true);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(6, 18, 408, 25);
		lblNewLabel_1.setForeground(new Color(66, 66, 66));
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(64, 120, 294, 16);
		panel.add(lblNewLabel);
		
		TFExistencia = new JTextField();
		TFExistencia.setEditable(false);
		TFExistencia.setBounds(278, 328, 80, 26);
		panel.add(TFExistencia);
		TFExistencia.setColumns(10);
		
		RB_Mayoreo = new JRadioButton("Mayoreo");
		RB_Mayoreo.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				ChagedMayoreo();
			}
		});
		RB_Mayoreo.setBounds(265, 383, 93, 23);
		panel.add(RB_Mayoreo);
		S_Cantidad = new JSpinner(model);
		S_Cantidad.setBounds(64, 328, 195, 26);
		panel.add(S_Cantidad);
		
		JLabel LblPresentacion = new JLabel("Presentación");
		LblPresentacion.setBounds(64, 235, 294, 16);
		panel.add(LblPresentacion);
		
		JLabel lblDescripcio = new JLabel("Descripción");
		lblDescripcio.setBounds(64, 166, 300, 16);
		panel.add(lblDescripcio);
		
		TF_Nombre = new JTextField();
		TF_Nombre.setEditable(false);
		TF_Nombre.setBounds(64, 138, 300, 26);
		panel.add(TF_Nombre);
		TF_Nombre.setColumns(10);
		
		JLabel LBlCodigo = new JLabel("Código");
		LBlCodigo.setBounds(64, 69, 294, 16);
		panel.add(LBlCodigo);
		
		TFCodigo = new JTextField();
		TFCodigo.setEditable(false);
		TFCodigo.setText((String) null);
		TFCodigo.setColumns(10);
		TFCodigo.setBounds(64, 89, 300, 26);
		panel.add(TFCodigo);
		
		TAPresentacion = new JTextArea();
		TAPresentacion.setEditable(false);
		TAPresentacion.setCaretColor(SystemColor.inactiveCaptionText);
		TAPresentacion.setBorder(new LineBorder(SystemColor.window));
		TAPresentacion.setBackground(SystemColor.activeCaption);
		TAPresentacion.setBounds(64, 256, 294, 41);
		panel.add(TAPresentacion);
		
		TADescripcion = new JTextArea();
		TADescripcion.setEditable(false);
		TADescripcion.setCaretColor(SystemColor.activeCaption);
		TADescripcion.setBorder(new LineBorder(SystemColor.window));
		TADescripcion.setBackground(SystemColor.activeCaption);
		TADescripcion.setBounds(64, 188, 294, 41);
		panel.add(TADescripcion);
		
		TFPrecio = new JTextField();
		TFPrecio.setVerifyInputWhenFocusTarget(false);
		TFPrecio.setToolTipText("Precio para el Usuario");
		TFPrecio.setText((String) null);
		TFPrecio.setEditable(false);
		TFPrecio.setColumns(10);
		TFPrecio.setBounds(64, 382, 195, 26);
		panel.add(TFPrecio);
		
		JLabel LblPrecio = new JLabel("Precio");
		LblPrecio.setBounds(64, 366, 294, 16);
		panel.add(LblPrecio);
		
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
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		this.configuracion = Configuracion.getInstancia(Herramientas.config.dirConfig);
		this.producto = producto;
		this.jf_Venta = jf_Venta;
		this.indice = indice;
		cargarPantalla();
		
	}
	
	public void ChagedMayoreo() {
		if(RB_Mayoreo.isSelected())
			TFPrecio.setText(Herramientas.formatoDinero(producto.getP_Mayoreo()));
		else
			TFPrecio.setText(Herramientas.formatoDinero(producto.getP_publico()));
	}
	
	
	public void actualizarProducto() {
		
		if((Integer) S_Cantidad.getValue()> producto.getExistencia())
		{
			JOptionPane.showMessageDialog(this, "El número de productos no puede ser mayor a la Existencia.", "Error", JOptionPane.ERROR_MESSAGE, null);
			return;
		}	
		
		this.producto.setCantidadComprar((int) S_Cantidad.getValue());
		this.producto.setDescuentoM( RB_Mayoreo.isSelected()? Herramientas.cadenas.CadenaSi:Herramientas.cadenas.CadenaNo );
	
		if ( ((Integer)S_Cantidad.getValue())  ==0) {
			jf_Venta.productosVenta.remove(indice);
		}
		else {
			jf_Venta.productosVenta.set(indice, producto);
		}
		
		jf_Venta.llenarTabla();
		jf_Venta.TF_total.setText(Herramientas.formatoDinero( jf_Venta.calcularTotal()));
		this.dispose();
		
	}	
	
	public void validaExistencia() {
		if((Integer) S_Cantidad.getValue()> producto.getExistencia())
		{
			JOptionPane.showMessageDialog(this, "El número de productos no puede ser mayor a la Existencia.", "Error", JOptionPane.ERROR_MESSAGE, null);
		}
	}
	
	public void validaExistenciaColors() {
		if (producto.getExistencia()>=Integer.parseInt(configuracion.getEA())) {
			TFExistencia.setForeground(Herramientas.colors.verde);
		}
		else if(producto.getExistencia()>= Integer.parseInt(configuracion.getEM())){
			TFExistencia.setForeground(Herramientas.colors.amarillo);
		}
		else {
			TFExistencia.setForeground(Herramientas.colors.rojo);
		}
	}
	
	public void ExistenciaMaxima(int maxima) {
        SpinnerNumberModel model = new SpinnerNumberModel(1, 1, maxima, 1);
        S_Cantidad.setModel(model);
	}
	
	public void cargarPantalla() {
		ExistenciaMaxima(producto.getExistencia());
		validaExistenciaColors();
		System.out.println("Cayo con  "+producto.getCantidadComprar());
		System.out.println();
		TFCodigo.setText(producto.getCodigo());
		TF_Nombre.setText(producto.getNombre());
		TADescripcion.setText(producto.getDescripcion());
		TAPresentacion.setText(producto.getCantidad());
		S_Cantidad.setValue((Integer)producto.getCantidadComprar());
		TFExistencia.setText(""+producto.getExistencia());
		TFPrecio.setText( Herramientas.formatoDinero(producto.getP_publico()));
		if (producto.getDescuentoM().equals(Herramientas.cadenas.CadenaSi)) {
			RB_Mayoreo.setSelected(true);
		}
	}
}

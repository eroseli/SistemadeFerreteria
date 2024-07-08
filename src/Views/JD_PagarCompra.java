package Views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controllers.ControllerVenta;
import DAO.ModelsDAO.Cliente;
import DAO.ModelsDAO.REGISTROVENTA;
import DAO.ModelsDAO.REGISTROVENTADET;
import DAO.ModelsDAO.Usuario;
import HerramientasConexion.Herramientas;
import HerramientasConexion.Herramientas.cadenas;
import Models.ProductoVenta;
import Models.Respuesta;
import Models.Venta;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JD_PagarCompra extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField TF_CantidadVenta;
	private JTextField TF_CantidadEfectivo;
	private JTextField TF_Cambio;
	private JTextField TF_CantidadTarjeta;
	
	private float cantidad;
	private JF_Venta jf_venta;
	private Usuario usuario;
	private Cliente cliente;
	private ArrayList<ProductoVenta> productosVenta;
	private float globalPagoEfectivo =0f;
	private float globalPagoTarjeta= 0f;
	private String tipoPago;
	
	public JD_PagarCompra(JF_Venta jf_venta, ArrayList<ProductoVenta> productosVenta, Cliente cliente, Usuario usuario,String tipoPago) {
		setBounds(100, 100, 450, 450);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBounds(6, 6, 438, 377);
			contentPanel.add(panel);
			panel.setLayout(null);
			
			TF_CantidadVenta = new JTextField();
			TF_CantidadVenta.setBounds(84, 75, 258, 26);
			panel.add(TF_CantidadVenta);
			TF_CantidadVenta.setColumns(10);
			
			JLabel L_Total = new JLabel("Total");
			L_Total.setBounds(84, 54, 61, 16);
			panel.add(L_Total);
			{
				JLabel L_Cantidad = new JLabel(" Cantidad Efectivo");
				L_Cantidad.setBounds(84, 115, 126, 16);
				panel.add(L_Cantidad);
			}
			{
				TF_CantidadEfectivo = new JTextField();
				TF_CantidadEfectivo.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent arg0) {
						generarCambio(arg0);
					}
				});
				TF_CantidadEfectivo.setColumns(10);
				TF_CantidadEfectivo.setBounds(84, 143, 258, 26);
				panel.add(TF_CantidadEfectivo);
			}
			{
				JLabel L_Cambio = new JLabel("Cambio");
				L_Cambio.setBounds(84, 254, 61, 16);
				panel.add(L_Cambio);
			}
			{
				TF_Cambio = new JTextField();
				TF_Cambio.setColumns(10);
				TF_Cambio.setBounds(84, 282, 258, 26);
				panel.add(TF_Cambio);
			}
			{
				JLabel L_PantallaPago = new JLabel("Realizar Pago");
				L_PantallaPago.setForeground(new Color(66, 66, 66));
				L_PantallaPago.setHorizontalAlignment(SwingConstants.CENTER);
				L_PantallaPago.setFont(new Font("Lucida Grande", Font.BOLD, 22));
				L_PantallaPago.setBounds(0, 17, 438, 25);
				panel.add(L_PantallaPago);
			}
			{
				TF_CantidadTarjeta = new JTextField();
				TF_CantidadTarjeta.setColumns(10);
				TF_CantidadTarjeta.setBounds(84, 209, 258, 26);
				panel.add(TF_CantidadTarjeta);
			}
			{
				JLabel L_Cantidad = new JLabel("Cantidad Tarjeta");
				L_Cantidad.setBounds(84, 181, 126, 16);
				panel.add(L_Cantidad);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton B_Pagar = new JButton("Pagar");
				B_Pagar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						realizarCompra();
					}
				});
				B_Pagar.setActionCommand("OK");
				buttonPane.add(B_Pagar);
				getRootPane().setDefaultButton(B_Pagar);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		
		//propiedades 
		this.jf_venta = jf_venta;
		this.cliente = cliente;
		this.usuario = usuario;
		this.productosVenta = productosVenta;
		this.tipoPago = tipoPago;
		cargarPantalla();
		
	}
	
	public void generarCambio(KeyEvent arg0){
		System.out.println("caracter : "+arg0.getKeyChar());
		String numero = "";
		
		if (!Character.isDigit(arg0.getKeyChar()) && arg0.getKeyChar() != '.') {
            String nuevoTexto = TF_CantidadEfectivo.getText().substring(0, TF_CantidadEfectivo.getText().length() - 1);
            TF_CantidadEfectivo.setText(nuevoTexto);
		}
		
		if (Float.parseFloat(TF_CantidadEfectivo.getText()) >= Float.parseFloat(TF_CantidadVenta.getText()) ) {
			
		}
		
	}
	
	public void cargarPantalla() {
		TF_CantidadVenta.setText(""+ Herramientas.formatoDinero( jf_venta.calcularTotal()) );	
	}
	
	public int obtenerNumProductos() {
		int totalProductos =0;
		for(ProductoVenta  ProductoVenta: productosVenta) {
			
			totalProductos = totalProductos = ProductoVenta.getCantidadComprar();
			
		}
		
		return totalProductos;
	}
	
	
	
	public void realizarCompra() {
		
		Respuesta respuesta = new Respuesta("",true,null);
		ControllerVenta controllerVenta = new ControllerVenta();
		//Traducir datos
		REGISTROVENTA registroventa = new REGISTROVENTA();
		ArrayList<REGISTROVENTADET> registroventadetList= new ArrayList<REGISTROVENTADET>();
		REGISTROVENTADET registroventadet = new REGISTROVENTADET();
		Venta venta = new Venta();
		
		registroventa.setParam_Id_Usuario( usuario != null ? usuario.getId_Usuario(): 0  );
		registroventa.setParam_Id_Cliente( cliente != null ? cliente.getIdentificador(): "" );
		registroventa.setParam_NumProductos(obtenerNumProductos());
		registroventa.setParam_Cantidad(jf_venta.calcularTotal());
		registroventa.setParam_PagoTarjeta( Herramientas.eliminarFormatoDinero(TF_CantidadTarjeta.getText()));
		registroventa.setParam_PagoEfectivo( Herramientas.eliminarFormatoDinero(TF_CantidadEfectivo.getText()) );
		registroventa.setParam_Descuento(0);
		registroventa.setParam_PagoCliente(Herramientas.eliminarFormatoDinero(TF_CantidadEfectivo.getText()) + Herramientas.eliminarFormatoDinero(TF_CantidadTarjeta.getText()));
		registroventa.setParam_SubTotal(jf_venta.calcularTotal());
		registroventa.setParam_respuesta(0);
		registroventa.setParam_mensaje("");
		
		
		for( ProductoVenta productoVenta: productosVenta) {
			
			registroventadet.setParam_Id_Producto(productoVenta.getId_producto());
			registroventadet.setParam_NumProductos(productoVenta.getCantidadComprar());
			registroventadet.setParam_Id_Venta(0);
			registroventadet.setParam_Precio( productoVenta.getDescuentoM().equals(cadenas.CadenaSi) ? productoVenta.getP_Mayoreo() : productoVenta.getP_publico() );
			registroventadet.setParam_DescuentoM(productoVenta.getDescuentoM());
			registroventadet.setParam_DescuentoEsp(productoVenta.getDescuentoE());
			registroventadet.setParam_Id_Cliente(   cliente != null ? cliente.getIdentificador(): "");
			registroventadet.setParam_respuesta(0);
			registroventadet.setParam_mensaje("");
			
			registroventadetList.add(registroventadet);
			
		}
		
		venta = new Venta(registroventa,registroventadetList);
		
		respuesta = controllerVenta.procesarListaVenta(venta);
		
		JOptionPane.showMessageDialog(this, respuesta.getMensaje());
		
	}
	
}

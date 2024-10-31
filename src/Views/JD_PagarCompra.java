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
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public class JD_PagarCompra extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField TF_CantidadVenta;
	private JTextField TF_CantidadEfectivo;
	private JTextField TF_Cambio;
	private JTextField TF_CantidadTarjeta;
	private JButton B_Pagar;
	
	private float cantidad;
	private JF_Venta jf_venta;
	private Usuario usuario;
	private Cliente cliente;
	private ArrayList<ProductoVenta> productosVenta;
	private float globalPagoEfectivo =0f;
	private float globalPagoTarjeta= 0f;
	private String tipoPago;
	private JLabel LTarjeta;
	private JLabel LEfectivo;
	private JButton BCancelar ;
	
	
	float totalVenta =0f;
	
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
				LEfectivo = new JLabel(" Cantidad Efectivo");
				LEfectivo.setBounds(84, 115, 126, 16);
				panel.add(LEfectivo);
			}
			{
				TF_CantidadEfectivo = new JTextField();
				TF_CantidadEfectivo.addCaretListener(new CaretListener() {
					public void caretUpdate(CaretEvent e) {
						obtenerValore();
					}
				});
				TF_CantidadEfectivo.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent arg0) {
						generarCambio(arg0, Herramientas.tipoPago.Efectivo);
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
				TF_CantidadTarjeta.addCaretListener(new CaretListener() {
					public void caretUpdate(CaretEvent e) {
						obtenerValore();
					}
				});
				TF_CantidadTarjeta.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						generarCambio(e, Herramientas.tipoPago.Tarjeta);
					}
				});
				TF_CantidadTarjeta.setColumns(10);
				TF_CantidadTarjeta.setBounds(84, 209, 258, 26);
				panel.add(TF_CantidadTarjeta);
			}
			{
				LTarjeta = new JLabel("Cantidad Tarjeta");
				LTarjeta.setBounds(84, 181, 126, 16);
				panel.add(LTarjeta);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				B_Pagar = new JButton("Pagar");
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
				BCancelar = new JButton("Cancel");
				BCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cerrarPantalla();
					}
				});
				BCancelar.setActionCommand("Cancel");
				buttonPane.add(BCancelar);
			}
		}
		
		
		//propiedades 
		this.jf_venta = jf_venta;
		this.cliente = cliente;
		this.usuario = usuario;
		this.productosVenta = productosVenta;
		this.tipoPago = tipoPago;

		// obtener el total de la venta
		totalVenta = jf_venta.calcularTotal();
		System.out.println("Total de la venta : "+totalVenta);
		cargarPantalla();
		
	}
	
	public void cerrarPantalla() {		
		if(BCancelar.getText().equals("Cerrar")) {
			jf_venta.limpiarPantalla();
			jf_venta.limpiarCarrito();
		}		
		dispose();		
	}
	
	public void obtenerValore() {
				
		String efectivo = (TF_CantidadEfectivo.getText().equals("") || TF_CantidadEfectivo.getText().isEmpty() )? "0":TF_CantidadEfectivo.getText();
		String tarjeta = (TF_CantidadTarjeta.getText().equals("") || TF_CantidadTarjeta.getText().isEmpty() )? "0":TF_CantidadTarjeta.getText();
		Float cambio = 0f;
		
		try {			
			cambio =  Float.parseFloat(efectivo) + Float.parseFloat(tarjeta) - totalVenta;		
			
			if (cambio>=0 && tipoPago.equals(Herramientas.tipoPago.Efectivo) ) {
				TF_Cambio.setText(Herramientas.formatoDinero( cambio));
				B_Pagar.setEnabled(true);
			}else if( cambio == 0 && (tipoPago.equals(Herramientas.tipoPago.Tarjeta) || tipoPago.equals(Herramientas.tipoPago.Mixto))  ){
				TF_Cambio.setText(Herramientas.formatoDinero(cambio));
				B_Pagar.setEnabled(true);
			}else {
				TF_Cambio.setText("");
				B_Pagar.setEnabled(false);
			}
			
		} catch (NumberFormatException e) {
			System.out.println("Error causado : "+e.getMessage());
		}
		
	}
	
	public void generarCambio(KeyEvent arg0, String origen){
		System.out.println("caracter : "+arg0.getKeyChar());
		String numero = "";				
		float cambio = -1f;
				
		try {			
			
			if (!Character.isDigit(arg0.getKeyChar()) && arg0.getKeyChar() != '.' && arg0.getKeyChar() != KeyEvent.VK_BACK_SPACE) {	            
	            arg0.consume();
	            return;
			}
			
			if( origen.equals(Herramientas.tipoPago.Efectivo) && arg0.getKeyChar()=='.' && TF_CantidadEfectivo.getText().contains(String.valueOf('.'))) {
				arg0.consume();
				return;
			}
			
			if( origen.equals(Herramientas.tipoPago.Tarjeta) && arg0.getKeyChar()=='.' && TF_CantidadTarjeta.getText().contains(String.valueOf('.'))) {
				arg0.consume();
				return;
			}
			
		} catch (Exception e) {
			arg0.consume();
			System.out.println("Error causado >"+e.getMessage());
			return;
		}
		
	}
	
	public void cargarPantalla() {
		TF_CantidadVenta.setText(""+ Herramientas.formatoDinero( totalVenta) );
		TF_CantidadVenta.setEditable(false);
		B_Pagar.setEnabled(false);
		
		System.out.println("Tipo del pago"+tipoPago);
		switch (tipoPago) {
			case Herramientas.tipoPago.Efectivo: 
				TF_CantidadTarjeta.setVisible(false);
				LTarjeta.setVisible(false);			
				break;				
			case Herramientas.tipoPago.Tarjeta: 
				TF_CantidadEfectivo.setVisible(false);
				LEfectivo.setVisible(false);
				break;				
		}
		
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
		registroventa.setParam_Cantidad(totalVenta);
		registroventa.setParam_PagoTarjeta(  
				Herramientas.eliminarFormatoDinero( TF_CantidadTarjeta.getText().isEmpty() || TF_CantidadTarjeta.getText().equals("")?"0.0":TF_CantidadTarjeta.getText() ) );
		registroventa.setParam_PagoEfectivo( 
				Herramientas.eliminarFormatoDinero( TF_CantidadEfectivo.getText().isEmpty() || TF_CantidadEfectivo.getText().equals("")?"0.0":TF_CantidadEfectivo.getText()  ) );
		registroventa.setParam_Descuento(0);
		registroventa.setParam_PagoCliente(
				Herramientas.eliminarFormatoDinero( TF_CantidadTarjeta.getText().isEmpty() || TF_CantidadTarjeta.getText().equals("")?"0.0":TF_CantidadTarjeta.getText() ) +
				Herramientas.eliminarFormatoDinero( TF_CantidadEfectivo.getText().isEmpty() || TF_CantidadEfectivo.getText().equals("")?"0.0":TF_CantidadEfectivo.getText()  ) );
		registroventa.setParam_SubTotal(totalVenta);
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
		
		if (respuesta.getValor())
		{
			B_Pagar.setEnabled(false);
			BCancelar.setText("Cerrar");
		}
		
	}
	
}

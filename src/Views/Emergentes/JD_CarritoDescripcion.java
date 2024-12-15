package Views.Emergentes;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controllers.ControllerVenta;
import DAO.ModelsDAO.CarritoDAO;
import HerramientasConexion.Herramientas;
import Models.ProductoVenta;
import Models.Respuesta;
import Views.JF_Venta;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import Utileria.ComboItem;
import javax.swing.JSeparator;

public class JD_CarritoDescripcion extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField TF_Nombre;
	private JComboBox<ComboItem> CBCarritos;

	private JF_Venta jf_Venta;
	private JButton BGrabar;
	
	public static void main(String[] args) {
		try {
			JD_CarritoDescripcion dialog = new JD_CarritoDescripcion(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JD_CarritoDescripcion(JF_Venta jf_Venta) {
	
		this.setLocationRelativeTo(null);
		
		setBounds(100, 100, 330, 314);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			TF_Nombre = new JTextField();
			TF_Nombre.setBounds(41, 63, 250, 26);
			contentPanel.add(TF_Nombre);
			TF_Nombre.setColumns(10);
		}
		
		JLabel lblNewLabel = new JLabel("Carrito");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Helvetica", Font.PLAIN, 18));
		lblNewLabel.setBounds(0, 6, 330, 28);
		contentPanel.add(lblNewLabel);
		
		JLabel LNombreCarrito = new JLabel("Nombre del Carrito");
		LNombreCarrito.setFont(new Font("Helvetica", Font.PLAIN, 12));
		LNombreCarrito.setBounds(41, 35, 125, 16);
		contentPanel.add(LNombreCarrito);
		
		CBCarritos = new JComboBox();
		CBCarritos.setBounds(40, 201, 251, 27);
		contentPanel.add(CBCarritos);
		
		JLabel lblListaDeCarritos = new JLabel("Lista de Carritos");
		lblListaDeCarritos.setFont(new Font("Helvetica", Font.PLAIN, 12));
		lblListaDeCarritos.setBounds(41, 173, 125, 16);
		contentPanel.add(lblListaDeCarritos);
		BGrabar = new JButton("Grabar");
		BGrabar.setForeground(new Color(251, 252, 255));
		BGrabar.setFont(new Font("Helvetica", Font.PLAIN, 13));
		BGrabar.setBounds(110, 101, 85, 29);
		contentPanel.add(BGrabar);
		BGrabar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				grabarCarrito();
			}
		});
		BGrabar.setActionCommand("OK");
		getRootPane().setDefaultButton(BGrabar);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 240, 330, 40);
			contentPanel.add(buttonPane);
			{
				
				JButton BObtener = new JButton("Obtener Carrito");
				BObtener.setBounds(6, 5, 141, 29);
				BObtener.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						obtenerCarrito();
					}
				});
				buttonPane.setLayout(null);
				BObtener.setActionCommand("OK");
				buttonPane.add(BObtener);
			}
			{
				JButton BCancelar = new JButton("Cancel");
				BCancelar.setBounds(146, 5, 86, 29);
				BCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				
				JButton BEliminar = new JButton("Eliminar");
				BEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						EliminarCarritos();
					}
				});
				BEliminar.setBounds(229, 5, 95, 29);
				BEliminar.setActionCommand("Cancel");
				buttonPane.add(BEliminar);
				BCancelar.setActionCommand("Cancel");
				buttonPane.add(BCancelar);
			}
		}
		
		JSeparator separator = new JSeparator();
		separator.setBounds(40, 145, 250, 12);
		contentPanel.add(separator);
		
		this.jf_Venta = jf_Venta;
		obtenerCarritos();
	}
	
	public void EliminarCarritos() {
		ComboItem comboItem = (ComboItem) CBCarritos.getSelectedItem();
		if (comboItem ==null) {
			JOptionPane.showMessageDialog(this,"Seleccione un Carrito para Eliminar", "Advertencia",JOptionPane.WARNING_MESSAGE);
			return ;
		}
		
		jf_Venta.eliminarCarrito( Integer.parseInt( comboItem.getKey()));		
		
		System.out.println("Carrito a eliminar :"+comboItem.getKey()+"\n");
		System.out.println("Carrito a eliminar :"+jf_Venta.carritoComboItem.getKey()+"\n");
		
		if( jf_Venta.carritoComboItem.getKey().equals(comboItem.getKey()) ) {
			jf_Venta.carritoComboItem = null;
			jf_Venta.LNombreCarrito.setText("");
		}		
		dispose();
	}
	
	
	public void obtenerCarrito(){
		
		ControllerVenta controllerVenta = new ControllerVenta();
		Respuesta respuesta = new Respuesta("",true,null);
		
		ComboItem comboItem = (ComboItem) CBCarritos.getSelectedItem();
		
		if (comboItem == null) {
			JOptionPane.showMessageDialog(this, "Seleccione un Carrito a Cargar.","Advertencia",JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		respuesta = controllerVenta.obtenerCarritoDetalleProductos( Integer.parseInt(comboItem.getKey()));
		
		if (!respuesta.getValor()) {
			JOptionPane.showMessageDialog(this, "Problemas al obtener los Productos de tu carrito","Error",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		//rellenar datos de la pantalla principal de ventas
		jf_Venta.productosVenta = (ArrayList<ProductoVenta>) respuesta.getRespuesta();
		jf_Venta.llenarTabla();		
		jf_Venta.TF_total.setText(Herramientas.formatoDinero( jf_Venta.calcularTotal()));
		jf_Venta.carritoComboItem = comboItem;
		jf_Venta.LNombreCarrito.setText(comboItem.getValue());
		jf_Venta.idCarrito = Integer.parseInt(comboItem.getKey());
		this.dispose();	 
		
	}
	
	public void obtenerCarritos() {		
		ControllerVenta controllerVenta = new ControllerVenta();
		Respuesta respuesta = new Respuesta("",true,null);	
		respuesta = controllerVenta.obtenerCarritos();		
		
		List<CarritoDAO> carritos = (ArrayList<CarritoDAO>) respuesta.getRespuesta();	
		CBCarritos.removeAllItems();
		for(CarritoDAO carrito: carritos) {		
			CBCarritos.addItem(new ComboItem( ""+carrito.getIdCarrito(), carrito.getNombre()));			
		}		
	}
	
	public void grabarCarrito() {
				
		ComboItem combo =  (ComboItem) CBCarritos.getSelectedItem();
		
		if(TF_Nombre.getText().trim().isEmpty())
		{
			JOptionPane.showMessageDialog(this, "Agrega un Nombre para el Carrito","Advertencia",JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		Respuesta respuesta = jf_Venta.guardarCarrito(TF_Nombre.getText().trim().toString());
		
		if (!respuesta.getValor()) {
			JOptionPane.showMessageDialog(this, respuesta.getMensaje());
			return;
		}else {
			bloquearPantalla();
		}	
		
		obtenerCarritos();		
		JOptionPane.showMessageDialog(this, respuesta.getMensaje());
		
	}
	
	public void bloquearPantalla() {
		TF_Nombre.setEditable(false);
		TF_Nombre.setText("");
		BGrabar.setEnabled(false);
	}
	
}

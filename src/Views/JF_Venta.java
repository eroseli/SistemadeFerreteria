package Views;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controllers.ControllerProducto;
import Controllers.ControllerVenta;
import HerramientasConexion.Herramientas;
import Models.Carrito;
import DAO.ModelsDAO.Cliente;
import DAO.ModelsDAO.Producto;
import DAO.ModelsDAO.Usuario;
import Models.ProductoVenta;
import Models.Respuesta;
import Utileria.ComboItem;
import Views.Catalogos.JD_Clientes;
import Views.Catalogos.JD_Productos;
import Views.Emergentes.JD_CarritoDescripcion;

import javax.swing.JTable;

import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.Color;
import java.awt.Font;
import javax.swing.UIManager;

public class JF_Venta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTable TProductos;
	private JPanel panel;
	private JTextField TF_Busqueda;
	private JButton btnNewButton;
	private JButton Btn_Agregar;
	private JButton btnClear;
	private JButton btnNewButton_2;
	private JTextField TF_SubTotal;
	public JTextField TF_total;
	public JTextField TF_Cliente;
	private JTable TablaProductos;
	private JScrollPane scrollPane;
	private JPopupMenu JPM_MenuProductos;
	private JMenuItem MI_Editar,MI_Eliminar;
	JScrollPane scrollPaneHorizontal;
	JComboBox<ComboItem> CB_FormaPago;
	public JLabel LNombreCarrito;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JF_Venta frame = new JF_Venta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ArrayList<ProductoVenta> productosVenta = new ArrayList<ProductoVenta>();
	public List<Producto> productos = new ArrayList<>();
	public List<Carrito> carritos = new ArrayList<>();
	public Map<String, List<Producto>> mapaCarritos; 
	public Cliente cliente = null;
	public Usuario usuario = null;
	private DAO.ProductosDAO productosDAO = new DAO.ProductosDAO();
	String[] columnNames = {"ID", "Codigo", "Nombre","Descripcion","Cantidad del Producto",
			"Fecha Caducidad","Precio","Precio Mayoreo","Existencia", "Cantidad",
			"Descuento","Descuento Especial"};
	private JButton BGuardarCarrito;
	
	public ComboItem carritoComboItem = null;

	public JF_Venta() {
		
		this.mapaCarritos = new HashMap<String, List<Producto>>();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(6, 38, 988, 473);
		contentPane.add(scrollPane);
		
		TablaProductos = new JTable();
		scrollPane.setViewportView(TablaProductos);
		//TablaProductos.setModel(dtm);
		
		TablaProductos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TablaProductos.setDefaultEditor(Object.class, null);
		//TablaProductos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 517, 1000, 155);
		contentPane.add(panel);
		panel.setLayout(null);
		
		TF_Busqueda = new JTextField();
		TF_Busqueda.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				buscarProductoCaracter(e);
			
			}
		});
		TF_Busqueda.setBounds(6, 105, 200, 26);
		panel.add(TF_Busqueda);
		TF_Busqueda.setColumns(10);
		
		btnNewButton = new JButton("-");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eliminarProducto();
			}
		});
		btnNewButton.setBounds(277, 105, 75, 29);
		panel.add(btnNewButton);
		
		Btn_Agregar = new JButton("+");
		Btn_Agregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				agregarProducto();
			}
		});
		Btn_Agregar.setBounds(205, 105, 75, 29);
		panel.add(Btn_Agregar);
		
		btnClear = new JButton("clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiarCarrito();
			}
		});
		btnClear.setBounds(352, 105, 75, 29);
		panel.add(btnClear);
		
		CB_FormaPago = new JComboBox<ComboItem>();
		CB_FormaPago.setBounds(813, 17, 136, 27);
		CB_FormaPago.setName("Formas De Pago");
		panel.add(CB_FormaPago);
		
		inicializarCombos(CB_FormaPago);
		
		btnNewButton_2 = new JButton("Pagar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cargarPantallaPago();
			}
		});
		btnNewButton_2.setBounds(832, 105, 117, 29);
		panel.add(btnNewButton_2);
		
		TF_SubTotal = new JTextField();
		TF_SubTotal.setBounds(653, 17, 130, 26);
		panel.add(TF_SubTotal);
		TF_SubTotal.setColumns(10);
		
		TF_total = new JTextField();
		TF_total.setFont(new Font("Arial", Font.PLAIN, 18));
		TF_total.setForeground(new Color(0, 143, 81));
		TF_total.setBounds(653, 55, 130, 39);
		TF_total.setColumns(10);
		panel.add(TF_total);
		
		JLabel lblNewLabel = new JLabel("Sub Total");
		lblNewLabel.setBounds(560, 22, 61, 16);
		panel.add(lblNewLabel);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(560, 66, 61, 16);
		panel.add(lblTotal);
		
		JLabel lblNewLabel_1 = new JLabel("Cliente");
		lblNewLabel_1.setBounds(18, 22, 61, 16);
		panel.add(lblNewLabel_1);
		
		TF_Cliente = new JTextField();
		TF_Cliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				obtenerCliente();
			}
		});
		TF_Cliente.setBounds(76, 17, 241, 26);
		panel.add(TF_Cliente);
		TF_Cliente.setColumns(10);
		
		JButton BLimpiarCliente = new JButton("Limpiar");
		BLimpiarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCliente();
			}
		});
		BLimpiarCliente.setBounds(318, 16, 91, 29);
		panel.add(BLimpiarCliente);

		Object[][] datos = new Object[1][12];	
		
		//DefaultTableModel dtm = new DefaultTableModel(datos, columnNames);
		DefaultTableModel dtm = new DefaultTableModel(datos, columnNames);
        TablaProductos.setModel(dtm);
        
        
        JPM_MenuProductos = new JPopupMenu();
        MI_Editar = new JMenuItem("Editar");
        MI_Eliminar = new JMenuItem("Eliminar");
        
        JPM_MenuProductos.add(MI_Editar);
        JPM_MenuProductos.add(MI_Eliminar);
        
        TablaProductos.setComponentPopupMenu(JPM_MenuProductos);
        
        BGuardarCarrito = new JButton("Carrito");
        BGuardarCarrito.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		mostrarPantallaCarrito();
        	}
        });
        BGuardarCarrito.setBounds(0, 0, 140, 29);
        contentPane.add(BGuardarCarrito);
        
        LNombreCarrito = new JLabel("");
        LNombreCarrito.setFont(new Font("Arial", Font.PLAIN, 11));
        LNombreCarrito.setBounds(142, 5, 140, 16);
        contentPane.add(LNombreCarrito);
        
        MI_Editar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				MenuProductosEditarProducto();
				
			}
		});
          
	}
	
	public void limpiarCliente() {
		cliente = null;
		TF_Cliente.setText("");
	}
	
	public void obtenerCliente() {
		JD_Clientes jd_cliente = new JD_Clientes(this);		
		jd_cliente.setVisible(true);
	}
	
	public void mostrarPantallaCarrito() {		
		JD_CarritoDescripcion carritoDescripcion = new JD_CarritoDescripcion(this);
		carritoDescripcion.setVisible(true);				
	}
	
	public Respuesta guardarCarrito(String nombre) {
		ControllerVenta controllerVenta = new ControllerVenta();
		Respuesta respuesta = new Respuesta("",true,null);
		
		if (productosVenta.size()==0) {
			//JOptionPane.showMessageDialog(this, "No hay Productos en el Carrito.");
			return new Respuesta("No hay Productos en el Carrito.",false,null) ;
		}		
		respuesta = controllerVenta.guardarCarrito( cliente==null? null:cliente.getIdentificador(), productosVenta,nombre);
		return respuesta;
		//JOptionPane.showMessageDialog(this, respuesta.getMensaje());
	}
	
	public void eliminarCarrito(int idCarrito) {
		ControllerVenta controllerVenta = new ControllerVenta();
		Respuesta respuesta = new Respuesta("",true,null);
		
		respuesta = controllerVenta.eliminarCarrito( idCarrito );
		JOptionPane.showMessageDialog(this,respuesta.getMensaje());
		
	}
	
	public void cargarPantallaPago() {
		
		if(productosVenta.size()==0) {
			JOptionPane.showMessageDialog(this, "No hay Productos en el Carrito");
			return;
		}
		ComboItem combo = (ComboItem) CB_FormaPago.getSelectedItem();
		JD_PagarCompra jd_pagarcompra = new JD_PagarCompra(this,productosVenta,cliente,usuario, combo.getKey());
		jd_pagarcompra.setModal(true);
		jd_pagarcompra.setLocationRelativeTo(null);
		jd_pagarcompra.setVisible(true);
		
	}
	
	public void MenuProductosEditarProducto() {
		
		ProductoVenta producto = new ProductoVenta();
		int indice =-1 ;
		try {
			
			indice = TablaProductos.getSelectedRow();
			String codigo =(String) TablaProductos.getValueAt(indice, 1);			
			 producto = productosVenta.get(indice);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		JD_DescripcionProducto descripcionProducto =  new JD_DescripcionProducto(this, producto, indice);
		descripcionProducto.setVisible(true);
		
	}
	
	public float calcularTotal() {
		
		float total =0f;
		float totaltemp = 0f;
		float precio = 0f;
		
		for (ProductoVenta productoVenta: productosVenta) {
			
			if (productoVenta.getDescuentoM().equals(Herramientas.cadenas.CadenaNo)) {
				precio = productoVenta.getP_publico();
			}else {
				precio = productoVenta.getP_Mayoreo();
			}
			
			totaltemp = precio  * productoVenta.getCantidadComprar();
			total = total + totaltemp ;
		}
		
		return total;
	}
	
	public void eliminarProducto() {
		
		int indice =0;
		int indiceTabla =0;
		String codigo ="";
		
		try {
			
			indiceTabla = TablaProductos.getSelectedRow();
			codigo = (String) TablaProductos.getValueAt(indiceTabla, 1);
			
			if (codigo.isEmpty() || codigo.equals("")) {
				JOptionPane.showMessageDialog(this, "No se ha seleccionado ningun elemento");
				return ;
			}
			
			for (ProductoVenta productoVenta: productosVenta) {
				
				if (codigo.equals(productoVenta.getCodigo())) {
					productosVenta.remove(indiceTabla);
					JOptionPane.showMessageDialog(this, "Producto con Codigo "+productoVenta.getCodigo()+" Eliminado Correctamente.");
					break;
				}
				
				indiceTabla++;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		llenarTabla();
		TF_total.setText(Herramientas.formatoDinero(calcularTotal()));
	}
		
	public void limpiarCarrito() {
		
		productosVenta.clear();		
		DefaultTableModel dtm = new DefaultTableModel(null, columnNames);
        TablaProductos.setModel(dtm);	        
		TF_total.setText(Herramientas.formatoDinero(calcularTotal()));
	}
	
	public void limpiarPantalla() {
		
		productos.clear();
		TF_Cliente.setText("");
		TF_SubTotal.setText("");
		TF_total.setText(Herramientas.formatoDinero(calcularTotal()));
		
	}
	
	public void limpiarPantallla() {
		
		DefaultTableModel dtm = new DefaultTableModel(null, columnNames);
        TablaProductos.setModel(dtm);	        		
        TF_SubTotal.setText("");
        TF_total.setText(Herramientas.formatoDinero(calcularTotal()));
        
	}
	
	public void buscarProductoCaracter(KeyEvent e) {
		
		System.out.print(e.getKeyChar());
		Producto producto_buscado = new Producto();
		Respuesta respuesta = new Respuesta("",false,null);
		
		if (e.getKeyChar() == KeyEvent.VK_ENTER) {
			respuesta = productosDAO.obtenerProductoCodigo(TF_Busqueda.getText());
			producto_buscado = (Producto) respuesta.getRespuesta();			
		}			
		if (respuesta.getValor()) {
			agregarProductoCarrito(producto_buscado, producto_buscado.getCodigo());
			TF_Busqueda.setText("");
			llenarTabla();	
			TF_total.setText( Herramientas.formatoDinero(calcularTotal()));
		}		
	}
	
	public void agregarProducto() {
		
		JD_Productos jd_Productos = new JD_Productos(this);
		jd_Productos.setModal(true);
		jd_Productos.setVisible(true);
		
		for(Producto producto: productos) {
			System.out.println(producto.getNombre());
		}
		llenarTabla();
		
	}
	
	public void llenarTabla() {
		Object[][] datos = new Object[productosVenta.size()][12];
		int indice = 0;
		
		for(ProductoVenta productoVenta: productosVenta) {
			
			datos[indice][0] = productoVenta.getId_producto();
			datos[indice][1] = productoVenta.getCodigo();
			datos[indice][2] = productoVenta.getNombre();
			datos[indice][3] = productoVenta.getDescripcion();
			datos[indice][4] = productoVenta.getCantidad();
			datos[indice][5] = productoVenta.getFecha_caducidad();
			datos[indice][6] = productoVenta.getP_publico();
			datos[indice][7] = productoVenta.getP_Mayoreo();
			datos[indice][8] = productoVenta.getExistencia();
			datos[indice][9] = productoVenta.getCantidadComprar();
			datos[indice][10]= productoVenta.getDescuentoM();
			datos[indice][11] = (productoVenta.getDescuentoE()==1) ? "Si":"No";			
			indice++;
		}				
		
		DefaultTableModel dtm = new DefaultTableModel(datos, columnNames);
        TablaProductos.setModel(dtm);	
		
	}
	
	public void agregarProductoCarrito(Producto producto, String codigo) {
		
		ProductoVenta productoActualizado = new ProductoVenta();
		boolean repetido = false; 
		int indice = 0;
		
		for (ProductoVenta productoA: productosVenta) {
			
			if(productoA.getCodigo().equals(codigo)) {
				
				productoActualizado = modelarProductoVenta(
						producto, 
						productoA.getCantidadComprar()+1, 
						(productoA.getDescuentoM().equals("Si") ? productoA.getP_Mayoreo() : productoA.getP_publico() ), 
						productoA.getDescuentoM(), 
						productoA.getDescuentoE()
						);
				repetido = true;				
			}else {
				indice++;				
			}
			
		}
		
		if(!repetido) {
			productosVenta.add(modelarProductoVenta(producto,1,producto.getP_publico(),"No",0));
		}else {
			productosVenta.set(indice, productoActualizado);
		}
		
	}
	
	public ProductoVenta modelarProductoVenta(Producto producto,int cantidad, 
			float precio, String descuentoM, int descuentoE) {
		
		ProductoVenta productoVenta =new ProductoVenta();
		
		productoVenta.setId_producto(0);
		productoVenta.setCodigo(producto.getCodigo());
		productoVenta.setNombre(producto.getNombre());
		productoVenta.setDescripcion(producto.getDescripcion());
		productoVenta.setCantidad(producto.getCantidad());
		productoVenta.setFecha_caducidad(producto.getFecha_caducidad());
		productoVenta.setP_publico(producto.getP_publico());
		productoVenta.setP_Mayoreo(producto.getP_Mayoreo());
		productoVenta.setP_Adquisicion(producto.getP_Adquisicion());
		productoVenta.setExistencia(producto.getExistencia());
		productoVenta.setCategoria(producto.getCategoria());
		productoVenta.setMarca(producto.getMarca());
		
		productoVenta.setCantidadComprar(cantidad);
		productoVenta.setPrecioCompra(precio);
		productoVenta.setDescuentoM(descuentoM);
		productoVenta.setDescuentoE(descuentoE);
		
		return productoVenta;
		
	}
	
	public void inicializarCombos(JComboBox<ComboItem> e) {
		e.addItem(new ComboItem("E","Efectivo"));
		e.addItem(new ComboItem("T","tarjeta"));
		e.addItem(new ComboItem("M","Mixto"));	
	}
	
	public void inicializarLista(JList<String> J_Carritos) {
		DefaultListModel<String> modelo = new DefaultListModel<String>();
		modelo.addElement("Elemento1");
		modelo.addElement("Elemento2");
		modelo.addElement("Elemento3");
	}
	

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}


	public List<Carrito> getCarritos() {
		return carritos;
	}


	public void setCarritos(List<Carrito> carritos) {
		this.carritos = carritos;
	}


	public Map<String, List<Producto>> getMapaCarritos() {
		return mapaCarritos;
	}


	public void setMapaCarritos(Map<String, List<Producto>> mapaCarritos) {
		this.mapaCarritos = mapaCarritos;
	}
}

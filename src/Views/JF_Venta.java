package Views;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import HerramientasConexion.Herramientas;
import Models.Carrito;
import Models.Cliente;
import Models.Producto;
import Models.ProductoVenta;
import Models.Respuesta;
import Models.Usuario;
import Views.Catalogos.JD_Productos;

import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;

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
	private JTextField TF_Cliente;
	private JList<String> L_Carritos;
	private JTable TablaProductos;
	private JScrollPane scrollPane;
	private JPopupMenu JPM_MenuProductos;
	private JMenuItem MI_Editar,MI_Eliminar;
	private JComboBox<String> CB_Carritos;
	JScrollPane scrollPaneHorizontal;
	JComboBox<String> CB_FormaPago;

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
	
	private JButton BT_CargarCarrito;

	public JF_Venta() {
		
		this.mapaCarritos = new HashMap<String, List<Producto>>();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(16, 44, 962, 467);
		contentPane.add(scrollPane);
		
		TablaProductos = new JTable();
		scrollPane.setViewportView(TablaProductos);
		//TablaProductos.setModel(dtm);
		
		TablaProductos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TablaProductos.setDefaultEditor(Object.class, null);
		//TablaProductos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		panel = new JPanel();
		panel.setBounds(0, 517, 1000, 149);
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
		
		CB_FormaPago = new JComboBox<String>();
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
		TF_total.setBounds(653, 55, 130, 26);
		TF_total.setColumns(10);
		panel.add(TF_total);
		
		JLabel lblNewLabel = new JLabel("Sub Total");
		lblNewLabel.setBounds(560, 22, 61, 16);
		panel.add(lblNewLabel);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(560, 60, 61, 16);
		panel.add(lblTotal);
		
		JLabel lblNewLabel_1 = new JLabel("Cliente");
		lblNewLabel_1.setBounds(18, 22, 61, 16);
		panel.add(lblNewLabel_1);
		
		TF_Cliente = new JTextField();
		TF_Cliente.setBounds(76, 17, 130, 26);
		panel.add(TF_Cliente);
		TF_Cliente.setColumns(10);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(TF_Cliente, popupMenu);
		
		L_Carritos = new JList<String>();
		L_Carritos.setBounds(6, 6, 388, 26);
		contentPane.add(L_Carritos);
		
		inicializarLista(L_Carritos);

		Object[][] datos = new Object[1][12];	
		
		//DefaultTableModel dtm = new DefaultTableModel(datos, columnNames);
		DefaultTableModel dtm = new DefaultTableModel(datos, columnNames);
        TablaProductos.setModel(dtm);	
        
        CB_Carritos = new JComboBox();
        CB_Carritos.setActionCommand("CB_Carritos");
        CB_Carritos.setBounds(406, 5, 165, 27);
        CB_Carritos.addItem("Seleccionar");
        contentPane.add(CB_Carritos);
        
        JButton JB_Carrito = new JButton("Guardar Carrito");
        JB_Carrito.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		agregarCarrito(mapaCarritos);
        	}
        });
        JB_Carrito.setBounds(838, 6, 140, 29);
        contentPane.add(JB_Carrito);
        
        BT_CargarCarrito = new JButton("Cargar");
        BT_CargarCarrito.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		presentarCarritoTabla();
        	}
        });
        BT_CargarCarrito.setBounds(568, 3, 117, 29);
        contentPane.add(BT_CargarCarrito);
        
        
        JPM_MenuProductos = new JPopupMenu();
        MI_Editar = new JMenuItem("Editar");
        MI_Eliminar = new JMenuItem("Eliminar");
        
        JPM_MenuProductos.add(MI_Editar);
        JPM_MenuProductos.add(MI_Eliminar);
        
        TablaProductos.setComponentPopupMenu(JPM_MenuProductos);
        
        MI_Editar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				MenuProductosEditarProducto();
				
			}
		});
          
	}
	
	public void cargarPantallaPago() {
	
		JD_PagarCompra jd_pagarcompra = new JD_PagarCompra(this,productosVenta,cliente,usuario, (String) CB_FormaPago.getSelectedItem());
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
		
	}
	
	public void limpiarCarrito() {
		
		productosVenta.clear();
		
		DefaultTableModel dtm = new DefaultTableModel(null, columnNames);
        TablaProductos.setModel(dtm);	        
		
	}
	
	public void limpiarPantallla() {
		
		DefaultTableModel dtm = new DefaultTableModel(null, columnNames);
        TablaProductos.setModel(dtm);	        		
        TF_SubTotal.setText("");
        TF_total.setText("");
        
	}
	
	public void buscarProductoCaracter(KeyEvent e) {
		
		System.out.print(e.getKeyChar());
		Producto producto_buscado = new Producto();
		Respuesta respuesta = new Respuesta("",false,null);
		
		if (e.getKeyChar() == KeyEvent.VK_ENTER) {
			
			JOptionPane.showConfirmDialog(BT_CargarCarrito, "Precionaste enter");
			respuesta = productosDAO.obtenerProductoCodigo(TF_Busqueda.getText());
			producto_buscado = (Producto) respuesta.getRespuesta();
			
		}	
		
		if (respuesta.getValor()) {
			agregarProductoCarrito(producto_buscado, producto_buscado.getCodigo());
			TF_Busqueda.setText("");
			llenarTabla();			
		}
		
	}
	
	public void presentarCarritoTabla() {
		

		this.getMapaCarritos().put("", null);
		this.mapaCarritos.put("", null);
	
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
	
	public void agregarCarrito(Map<String, List<Producto>> mapaCarritos) {
		
		String name = JOptionPane.showInputDialog("Elige un nombre para tu carrito: ");
		List<Producto> p =  getProductos();
		Carrito c = new Carrito(name, p);
		carritos.add(c);
		mapaCarritos.put(name,p);
		llenarCarritos();
		limpiarPantalla();
		llenarTabla();
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
	
	public void limpiarPantalla() {
		
		productos.clear();
		TF_Cliente.setText("");
		TF_SubTotal.setText("");
		TF_total.setText("");
		
	}
	
	public void llenarCarritos() {
		
		CB_Carritos.removeAllItems();
        CB_Carritos.addItem("Seleccionar"); //Estado por defecto
        for (Map.Entry<String, List<Producto>> entry: this.getMapaCarritos().entrySet()) {       	
        	CB_Carritos.addItem(entry.getKey());        	
        }		
	}
	
	public void inicializarCombos(JComboBox<String> e) {
		e.addItem("Efectivo");
		e.addItem("Tarjeta");
		e.addItem("Mixto");
		
		
	}
	
	public void inicializarLista(JList<String> J_Carritos) {
		DefaultListModel<String> modelo = new DefaultListModel<String>();
		modelo.addElement("Elemento1");
		modelo.addElement("Elemento2");
		modelo.addElement("Elemento3");
		J_Carritos.setModel(modelo);	
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
	
	
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}

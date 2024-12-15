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
import javax.swing.table.JTableHeader;

import Controllers.ControllerProducto;
import Controllers.ControllerVenta;
import HerramientasConexion.Herramientas;
import Models.Carrito;
import DAO.ModelsDAO.Cliente;
import DAO.ModelsDAO.Producto;
import DAO.ModelsDAO.Usuario;
import Models.ProductoVenta;
import Models.Respuesta;
import Models.Sesion;
import Models.Components.CustomHeaderRenderer;
import Models.Components.JTableEdited;
import Utileria.ComboItem;
import Utileria.ComponentesDesing;
import Views.Catalogos.JD_Clientes;
import Views.Catalogos.JD_Productos;
import Views.Emergentes.JD_AyudaVenta;
import Views.Emergentes.JD_CarritoDescripcion;

import javax.swing.JTable;

import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
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
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

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
	public int  idCarrito;
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
	public Sesion sesion = null;
	private DAO.ProductosDAO productosDAO = new DAO.ProductosDAO();
	String[] columnNames = {"ID", "Codigo", "Nombre","Descripcion", "Cantidad","Existencia","Precio P.",
			"Precio M.","T. V. Producto","Des. Mayoreo","Des Porcentual"};
	private JButton BGuardarCarrito;
	
	public ComboItem carritoComboItem = null;
	private JLabel lblNewLabel_2;
	private JButton BAyuda;

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
		scrollPane.setFont(new Font("Arial", Font.PLAIN, 12));
		scrollPane.setEnabled(false);
		scrollPane.setBounds(6, 38, 988, 475);
		contentPane.add(scrollPane);
		
		TablaProductos = new JTable(); // configuracion filas seleccion y fuente
		TablaProductos.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				acciones(e);
			}
		});
		TablaProductos.setRowHeight(40);
		TablaProductos.setSelectionBackground(new Color(59, 131, 220));
		TablaProductos.setFont(new Font("Arial", Font.PLAIN, 15));
		
		TablaProductos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TablaProductos.setDefaultEditor(Object.class, null);
		//TablaProductos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		// tabla personalizada
		JTableHeader header = TablaProductos.getTableHeader();
		header.setDefaultRenderer(new CustomHeaderRenderer(2));
		
		
		TablaProductos.setGridColor(Color.red);
		TablaProductos.setShowGrid(true);
		TablaProductos.setDefaultRenderer(Object.class, new CustomHeaderRenderer(2));
		
		scrollPane.setViewportView(TablaProductos);

		panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
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
		TF_Busqueda.setBounds(76, 105, 200, 26);
		panel.add(TF_Busqueda);
		TF_Busqueda.setColumns(10);
		
		btnNewButton = new JButton("-");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eliminarProducto();
			}
		});
		btnNewButton.setBounds(347, 105, 75, 29);
		panel.add(btnNewButton);
		
		Btn_Agregar = new JButton("+");
		Btn_Agregar.setMnemonic('c');
		Btn_Agregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				agregarProducto();
			}
		});
		Btn_Agregar.setBounds(275, 105, 75, 29);
		panel.add(Btn_Agregar);
		
		btnClear = new JButton("clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiarCarrito();
			}
		});
		btnClear.setBounds(422, 105, 75, 29);
		panel.add(btnClear);
		
		CB_FormaPago = new JComboBox<ComboItem>();
		CB_FormaPago.setBounds(843, 18, 136, 27);
		CB_FormaPago.setName("Formas De Pago");
		panel.add(CB_FormaPago);
		
		inicializarCombos(CB_FormaPago);
		
		btnNewButton_2 = new JButton("Pagar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cargarPantallaPago();
			}
		});
		btnNewButton_2.setBounds(843, 105, 136, 29);
		panel.add(btnNewButton_2);
		
		TF_SubTotal = new JTextField();
		TF_SubTotal.setEditable(false);
		TF_SubTotal.setBounds(701, 18, 130, 26);
		panel.add(TF_SubTotal);
		TF_SubTotal.setColumns(10);
		
		TF_total = new JTextField();
		TF_total.setEditable(false);
		TF_total.setFont(new Font("Arial", Font.PLAIN, 18));
		TF_total.setForeground(new Color(0, 143, 81));
		TF_total.setBounds(701, 56, 130, 39);
		TF_total.setColumns(10);
		panel.add(TF_total);
		
		JLabel lblNewLabel = new JLabel("Sub Total");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(608, 23, 61, 16);
		panel.add(lblNewLabel);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setForeground(Color.WHITE);
		lblTotal.setBounds(608, 67, 61, 16);
		panel.add(lblTotal);
		
		JLabel lblNewLabel_1 = new JLabel("Cliente");
		lblNewLabel_1.setForeground(Color.WHITE);
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
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_2.setIcon(new ImageIcon(JF_Venta.class.getResource("/Recursos/CodigoBarras.png")));
		lblNewLabel_2.setBounds(23, 105, 41, 26);
		panel.add(lblNewLabel_2);

		Object[][] datos = new Object[1][12];	
		
		//DefaultTableModel dtm = new DefaultTableModel(datos, columnNames);
//		DefaultTableModel dtm = new DefaultTableModel(datos, columnNames);
//        TablaProductos.setModel(dtm);
//		Herramientas.PreferredWithTable(TablaProductos);			
//        
		formatoTabla(null);
		
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
        BGuardarCarrito.setBounds(6, 5, 140, 29);
        contentPane.add(BGuardarCarrito);
        
        LNombreCarrito = new JLabel("");
        LNombreCarrito.setFont(new Font("Arial", Font.PLAIN, 11));
        LNombreCarrito.setBounds(147, 10, 140, 16);
        contentPane.add(LNombreCarrito);
        
        BAyuda = new JButton("Ayuda");
        BAyuda.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		ayuda();
        	}
        });
        BAyuda.setBounds(877, 5, 117, 29);
        contentPane.add(BAyuda);
        
        MI_Editar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				MenuProductosEditarProducto();
				
			}
		});
        
        //evento desde cualquier parte de la pantalla
        KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.ALT_DOWN_MASK);
        
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getRootPane().getActionMap();

        // Asociamos la combinación de teclas con la acción
        inputMap.put(keyStroke, "openProductWindow");
        actionMap.put("openProductWindow", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	agregarProducto();  // Llamamos a la función para abrir la ventana de productos
            }
        });
          
        this.sesion = Sesion.obtenerInstancia();
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TF_Busqueda.requestFocusInWindow(); // Coloca el foco en el JTextField
            }
        });        
	}
	
	public void ayuda() {
		JD_AyudaVenta jp_ayudaVenta = new JD_AyudaVenta();
		jp_ayudaVenta.setVisible(true);
	}
	
	public  void acciones(KeyEvent arg0) {
	
		Respuesta respuesta = new Respuesta("",false,null);
		
		if (arg0.isAltDown() && arg0.getKeyCode() == KeyEvent.VK_UP ) {
			System.out.println("Entro");
			respuesta = productoMas();
		}
		else if (arg0.isAltDown() && arg0.getKeyCode() == KeyEvent.VK_DOWN) {
			respuesta = productoMenos();
		}	
		
		if (respuesta.getValor()) {
			llenarTabla();	
			TablaProductos.setRowSelectionInterval( (Integer)respuesta.getRespuesta() ,(Integer) respuesta.getRespuesta());
		}
		
	}
	public Respuesta productoMenos() {
		
		
		try {
			
			if (TablaProductos.getSelectedRow()==-1) 
				return new Respuesta("",false,-1);
			
			int idProducto = (int) TablaProductos.getValueAt(TablaProductos.getSelectedRow(),0 );	
			
			productosVenta.stream()
				.filter(p-> p.getId_producto() == idProducto)
				.findFirst()
				.ifPresent(p -> //p.setCantidad( p.getCantidad() +1 ));
				{
					if (p.getCantidadComprar()>1) {
						p.setCantidadComprar( p.getCantidadComprar() -1 );
					}
				});
			
			return new Respuesta("",true,TablaProductos.getSelectedRow());

		} catch (Exception e) {
			System.out.println("Error al intentar agregar producto + 1");
		}
		
		return new Respuesta("",false,-1);
	}
	
	public Respuesta productoMas() {
				
		try {
			
			if (TablaProductos.getSelectedRow()==-1) 
				return new Respuesta("",false,-1);
			
			int idProducto = (int) TablaProductos.getValueAt(TablaProductos.getSelectedRow(),0 );
			System.out.println("Indice Tomado : "+TablaProductos.getSelectedRow()+ " ID "+idProducto);			
			productosVenta.stream()
				.filter(p-> p.getId_producto() == idProducto)
				.findFirst()
				.ifPresent(p -> //p.setCantidad( p.getCantidad() +1 ));
				{
					if (p.getCantidadComprar()<1000) {
						p.setCantidadComprar( p.getCantidadComprar() + 1 );
					}
				});
			
			return new Respuesta("",true,TablaProductos.getSelectedRow());
			
		} catch (Exception e) {
			System.out.println("Error al intentar agregar producto + 1");
		}
		
		return new Respuesta("",false,-1);
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
			return new Respuesta("No hay Productos en el Carrito.",false,null) ;
		}		
		respuesta = controllerVenta.guardarCarrito( cliente==null? null:cliente.getIdentificador(), productosVenta,nombre);
		return respuesta;
	}
	
	public void eliminarCarrito(int idCarrito) {
		ControllerVenta controllerVenta = new ControllerVenta();
		Respuesta respuesta = new Respuesta("",true,null);
		
		respuesta = controllerVenta.eliminarCarrito( idCarrito );
		JOptionPane.showMessageDialog(this,respuesta.getMensaje());
		
	}
	
	public void cargarPantallaPago() {
		
		if(productosVenta.size()==0) {
			JOptionPane.showMessageDialog(this, "No hay Productos en el Carrito","Advertencia",JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		for (ProductoVenta produc : productosVenta) {
			System.out.println("Producto aqui" + produc.toString());
		}
		
		ComboItem combo = (ComboItem) CB_FormaPago.getSelectedItem();
		JD_PagarCompra jd_pagarcompra = new JD_PagarCompra(this,productosVenta,cliente,sesion.getUsuario(), combo.getKey(), this.idCarrito);
		jd_pagarcompra.setModal(true);
		jd_pagarcompra.setLocationRelativeTo(null);
		jd_pagarcompra.setVisible(true);
		TF_Busqueda.requestFocus();
		
	}
	
	public void MenuProductosEditarProducto() {
		
		ProductoVenta producto = new ProductoVenta();
		int indice =-1 ;
		try {
			
			indice = TablaProductos.getSelectedRow();
			String codigo =(String) TablaProductos.getValueAt(indice, 1);			
			producto = productosVenta.get(indice);
			producto.setCantidadComprar((Integer) TablaProductos.getValueAt(indice, 4) );
			System.out.println("Asignando cantidad de compra : "+ producto.getCantidadComprar());
		} catch (Exception e) {
			System.out.println("Error al asignar parametros de entrada : "+e.getMessage());
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
		formatoTabla(null);
		TF_total.setText(Herramientas.formatoDinero(calcularTotal()));

	}
	
	public void formatoTabla(Object[][] datos) {
		DefaultTableModel dtm = new DefaultTableModel(datos, columnNames);
        TablaProductos.setModel(dtm);	        
		ComponentesDesing.PreferredWithTableProductoVenta(TablaProductos);			
	}
	
	public void limpiarPantalla() {
		
		productos.clear();
		TF_Cliente.setText("");
		TF_SubTotal.setText("");
		TF_total.setText(Herramientas.formatoDinero(calcularTotal()));
		this.idCarrito = 0;
		this.cliente = null;
		this.LNombreCarrito.setText("");
		formatoTabla(null);			
	}
	
	public void buscarProductoCaracter(KeyEvent e) {
		
		System.out.print(e.getKeyChar());
		Producto producto_buscado = new Producto();
		Respuesta respuesta = new Respuesta("",false,null);
		
		try {
			
			if (e.getKeyChar() != KeyEvent.VK_ENTER) {
				return;	
			}
			
			respuesta = productosDAO.obtenerProductoCodigo(TF_Busqueda.getText().trim());
			producto_buscado = (Producto) respuesta.getRespuesta();	
			
			if (respuesta.getValor()) {
				agregarProductoCarrito(producto_buscado, producto_buscado.getCodigo());
				llenarTabla();	
				TF_total.setText( Herramientas.formatoDinero(calcularTotal()));			
			}
			TF_Busqueda.setText("");			
			
		} catch (Exception ex) {
			System.out.println("Error al intentar buscar el producto : "+ex.getMessage());
			TF_Busqueda.setText("");
		}
			
	}
	
	public void agregarProducto() {
		
		JD_Productos jd_Productos = new JD_Productos(this,null);
		jd_Productos.setModal(true);
		jd_Productos.setVisible(true);
		
		for(Producto producto: productos) {
			System.out.println(producto.getNombre());
		}
		llenarTabla();
		
	}
	
	public void llenarTabla() {
		Object[][] datos = new Object[productosVenta.size()][11];
		int indice = 0;
		float MTProducto = 0f;
		
		for(ProductoVenta productoVenta: productosVenta) {
			
			if (productoVenta.getDescuentoE()==1) {
				MTProducto = productoVenta.getP_Mayoreo() *  productoVenta.getCantidadComprar();
			}
			else if (productoVenta.getDescuentoM().equals("Si")) {
				MTProducto = productoVenta.getP_Mayoreo() *  productoVenta.getCantidadComprar();
			}
			else {
				MTProducto = productoVenta.getP_publico() *  productoVenta.getCantidadComprar();
			}
			
			datos[indice][0] = productoVenta.getId_producto();
			datos[indice][1] = productoVenta.getCodigo();
			datos[indice][2] = productoVenta.getNombre();
			datos[indice][3] = productoVenta.getDescripcion();
			datos[indice][4] = productoVenta.getCantidadComprar();
			datos[indice][5] = productoVenta.getExistencia();
			datos[indice][6] = productoVenta.getP_publico();
			datos[indice][7] = productoVenta.getP_Mayoreo();
			datos[indice][8] = MTProducto;
			datos[indice][9]= productoVenta.getDescuentoM();
			datos[indice][10] = (productoVenta.getDescuentoE()==1) ? "Si":"No";			
			indice++;
		}	
		
		String[] columnNames = {"ID", "Codigo", "Nombre","Descripcion", "Cantidad","Existencia","Precio ",
				"Precio M.","T. V. Producto","Des. Mayoreo","Des Porcentual"};			
		formatoTabla(datos);
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
		
		productoVenta.setId_producto(producto.getId_producto());
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

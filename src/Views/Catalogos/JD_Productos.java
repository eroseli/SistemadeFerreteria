package Views.Catalogos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import Controllers.ControllerProducto;
import DAO.ModelsDAO.Producto;
import HerramientasConexion.Herramientas;
import Models.ProductoVenta;
import Models.Respuesta;
import Models.Components.CustomHeaderRenderer;
import Models.Components.JTableEdited;
import Views.JF_Venta;
import Views.JP_EntradaSalidaMercancia;
import Views.Forms.JD_DetalleESMercancia;

import javax.swing.JTable;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public class JD_Productos extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable TablaProductos;


//	public static void main(String[] args) {
//		try {
//			JD_Productos dialog = new JD_Productos();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	private DAO.ProductosDAO productosDAO = new DAO.ProductosDAO();
	private ArrayList<Producto> productos = new ArrayList<Producto>();
	private Respuesta respuesta;
	public String codigo = "";
	public JF_Venta padre;
	public JD_DetalleESMercancia jd_DetalleESMercancia;
	private JTextField TFBuscar;
	
	public JD_Productos(JF_Venta padre, JD_DetalleESMercancia jd_DetalleESMercancia) {
		setTitle("Busqueda de Productos");
		setBounds(100, 100, 803, 599);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
//			JScrollPane scrollPane = new JScrollPane(TablaProductos);
//		    scrollPane.setBounds(100, 250, 350, 350);
//
//		    this.add(scrollPane);
		    
			String[] columnNames = {"Nombre", "Años", "Apto",};
	        Object[][] datos = {
	            {"Juan", 25, false},
	            {"Sonia", 33, true},
	            {"Pedro", 42, false}};
	        DefaultTableModel dtm = new DefaultTableModel(datos, columnNames);
		}
		contentPanel.setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(6, 33, 791, 493);
			contentPanel.add(scrollPane);
			TablaProductos = new JTableEdited();
			TablaProductos.setRowHeight(18);
			TablaProductos.setFont(new Font("Arial", Font.PLAIN, 12));
			scrollPane.setViewportView(TablaProductos);
			TablaProductos.setBackground(new Color(249, 251, 251));
			TablaProductos.setAutoCreateRowSorter(true);
			TablaProductos.isCellEditable(0,0);
			TablaProductos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

			// tabla personalizada
			JTableHeader header = TablaProductos.getTableHeader();
			header.setDefaultRenderer(new CustomHeaderRenderer(3));
			TablaProductos.setDefaultRenderer(Object.class, new CustomHeaderRenderer(3));
			
			JButton BBuscar = new JButton("Buscar");
			BBuscar.setBounds(680, 6, 117, 29);
			contentPanel.add(BBuscar);
			{
				TFBuscar = new JTextField();
				TFBuscar.setBounds(469, 6, 208, 26);
				TFBuscar.addCaretListener(new CaretListener() {
					public void caretUpdate(CaretEvent arg0) {
						obtenerProductos(2);
					}
				});
				contentPanel.add(TFBuscar);
				TFBuscar.setColumns(10);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						seleccionarProducto();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		//Objetos creados 
		obtenerProductos(1); // obtener sdin filtros
		this.padre = padre;
		this.jd_DetalleESMercancia = jd_DetalleESMercancia;
	}
	
	public void Buscar(CaretEvent arg0) {
		
		System.out.println(TFBuscar.getText());
		
		ControllerProducto controllerProducto = new ControllerProducto();
		respuesta = new Respuesta("",true,null);
		
		
		respuesta = controllerProducto.proceso(5, TFBuscar.getText());
		
		if (!respuesta.getValor()) {
			JOptionPane.showMessageDialog(this, respuesta.getMensaje(), "Error",JOptionPane.ERROR_MESSAGE);
			return;
		}	
		
	}

	
	public void modificarTabla() {
		
		TablaProductos.getColumnModel().getColumn(0).setPreferredWidth(140);
		System.out.println("Tamanio de la tabla : "+TablaProductos.getColumnCount());
		for (int i = 0; i<TablaProductos.getColumnCount(); i++) {
			TablaProductos.getColumnModel().getColumn(i).setPreferredWidth(120);
		}
		TablaProductos.revalidate();
		TablaProductos.repaint();
	}
	
	public Boolean seleccionarProducto() {
		
		int existencia = 0 ;
		
		try {
			
			this.codigo = String.valueOf(TablaProductos.getValueAt(TablaProductos.getSelectedRow(), 1));
			existencia =  (int) TablaProductos.getValueAt(TablaProductos.getSelectedRow(), 5);
			System.out.println(codigo);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Producto no Seleccionado");
		}
		
		if(codigo.equals("")) {
			JOptionPane.showMessageDialog(contentPanel, "No ha seleccionado un producto.");
			return false;
		}
		
		if(existencia <=0 && padre != null) {
			JOptionPane.showMessageDialog(this, "El producto "+this.codigo+" no tiene disponibilidad en Inventario.", "Advertencia", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		
		Producto producto = obtenerProducto();
		
		
		if (padre != null) {
			agregarProductoCarrito(producto); //codigo para ventana de venta
			padre.llenarTabla();
			padre.TF_total.setText(Herramientas.formatoDinero(padre.calcularTotal()));	
			validarTipoPantalla();
			this.dispose();
			return true;				
		}
		
		this.jd_DetalleESMercancia.producto = producto;
		this.jd_DetalleESMercancia.presentarProducto();	
		this.dispose();
		return true;
		
	}
	
	
	public void validarTipoPantalla() {
		
		System.out.println(padre.getClass().getName());
		
	}
	
	
	public void agregarProductoCarrito(Producto producto) {
		
		ProductoVenta productoActualizado = new ProductoVenta();
		boolean repetido = false; 
		int indice = 0;
		
		for (ProductoVenta productoA: padre.productosVenta) {
			
			if(productoA.getCodigo().equals(codigo)) {
				
				productoActualizado = modelarProductoVenta(
						producto, 
						productoA.getCantidadComprar()+1, 
						(productoA.getDescuentoM().equals("Si") ? productoA.getP_Mayoreo() : productoA.getP_publico() ), 
						productoA.getDescuentoM(), 
						productoA.getDescuentoE()
						);
				repetido = true;	
				break;
			}
			
			indice++;							
			
		}
		
		if(repetido) {
			padre.productosVenta.set(indice, productoActualizado);
		}else {
			padre.productosVenta.add(modelarProductoVenta(producto,1,producto.getP_publico(),"No",0));
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
	
	public void obtenerProductos(int operacion) {
		
		ControllerProducto controllerProducto = new ControllerProducto();
		respuesta = new Respuesta("",true,null);
		
		switch (operacion) {
		case 1: // Busqueda sin filtro
			
			respuesta = (Respuesta) productosDAO.obtenerProductos();			
			if ( !respuesta.getValor() ) {
				JOptionPane.showMessageDialog(this, "Error "+respuesta.getMensaje());
				return ;
			}			
			break; // Busqueda con filtro

		case 2:
			
			respuesta = controllerProducto.proceso(5, TFBuscar.getText());			
			if (!respuesta.getValor()) {
				JOptionPane.showMessageDialog(this, respuesta.getMensaje(), "Error",JOptionPane.ERROR_MESSAGE);
				return;
			}		
			break;
		}
		
		this.productos = (ArrayList<Producto>) respuesta.getRespuesta();
		pintarTabla();
		
	}
	
	public void pintarTabla() {
		
		String[] columnNames = {"ID", "Codigo", "Nombre", "Descripcion",
								"Cantidad", "Existencia", "P. Público", "P. Mayoreo",
								"P. Adquisición", "Fecha Cad.", "Categoría", "Marca",};	
		
		
		Object[][] datos = new Object[productos != null? productos.size():0][13];
		
		int i=0;
		
		for(String o: columnNames) {
			System.out.println(o);
		}
		
		try {
			
			for(Producto producto: productos)
			{
				datos[i][0] = producto.getId_producto();
				datos[i][1] = producto.getCodigo();
				datos[i][2] = producto.getNombre();
				datos[i][3] = producto.getDescripcion();
				datos[i][4] = producto.getCantidad();				
				datos[i][5] = producto.getExistencia();
				datos[i][6] = producto.getP_publico();
				datos[i][7] = producto.getP_Mayoreo();
				datos[i][8] = producto.getP_Adquisicion();
				datos[i][9] = producto.getFecha_caducidad();
				datos[i][10] = producto.getCategoria();
				datos[i][11] = producto.getMarca();
				datos[i][12] = "";
				i++;
			}
			
		} catch (NullPointerException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}		
		
		DefaultTableModel dtm = new DefaultTableModel(datos,columnNames);
		//dtm.setColumnIdentifiers(columnNames);
		TablaProductos.setModel(dtm);
		modificarTabla();
	}
	
	public Producto obtenerProducto() {
		
		Producto producto = new Producto();
		
		for(Producto productoB: productos) {
			
			if (productoB.getCodigo().equals(codigo)) {
				
				//padre.getProductos().add(producto);
				producto = productoB;
			}
			
		}
		
		return producto;
		
	}
}

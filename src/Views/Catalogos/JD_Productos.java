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
import DAO.ModelsDAO.Producto;
import Models.ProductoVenta;
import Models.Components.JTableEdidata;
import Views.JF_Venta;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	
	public String codigo = "";
	public JF_Venta padre;
	
	public JD_Productos(JF_Venta padre) {
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
	        contentPanel.setLayout(null);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(6, 6, 791, 430);
			contentPanel.add(scrollPane);
			TablaProductos = new JTableEdidata();
			TablaProductos.setFont(new Font("Arial", Font.PLAIN, 12));
			scrollPane.setViewportView(TablaProductos);
			TablaProductos.setBackground(new Color(229, 247, 246));
			TablaProductos.setAutoCreateRowSorter(true);
			TablaProductos.isCellEditable(0,0);
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
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		//Objetos creados 
		obtenerProductos();
		this.padre = padre;
	}
	
	public Boolean seleccionarProducto(){
		
		
		try {
			
			this.codigo = String.valueOf(TablaProductos.getValueAt(TablaProductos.getSelectedRow(), 1));
			System.out.println(codigo);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Producto no Seleccionado");
		}
		
		if(codigo.equals("")) {
			JOptionPane.showMessageDialog(contentPanel, "No ha seleccionado un producto.");
			return false;
		}
		
		
		Producto producto = obtenerProducto();
		agregarProductoCarrito(producto);
		padre.llenarTabla();
		padre.TF_total.setText(""+padre.calcularTotal());	
		this.dispose();
		return true;
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
	
	public void obtenerProductos() {
		
		String[] columnNames = {"ID", "Codigo", "Nombre", "Descripcion",
								"Cantidad", "Fecha Cad.", "P. Público", "P. Mayoreo",
								"P. Adquisición", "Existencia", "Categoría", "Marca",};

		productos = (ArrayList<Producto>) productosDAO.obtenerProductos().getRespuesta();
		Object[][] datos = new Object[productos.size()][12];
		
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
				datos[i][4] = producto.getMarca();
				datos[i][5] = producto.getP_publico();
				datos[i][6] = producto.getP_Mayoreo();
				datos[i][7] = producto.getExistencia();
				datos[i][8] = producto.getCategoria();
				datos[i][9] = "";
				datos[i][10] = "";
				datos[i][11] = "";
				i++;
			}
			
		} catch (NullPointerException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}		
		
		DefaultTableModel dtm = new DefaultTableModel(datos,columnNames);
		//dtm.setColumnIdentifiers(columnNames);
		TablaProductos.setModel(dtm);
		
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

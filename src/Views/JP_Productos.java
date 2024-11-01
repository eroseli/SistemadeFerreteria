package Views;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import DAO.ModelsDAO.Producto;
import HerramientasConexion.Herramientas;
import Models.ProductoBusquedaView;
import Models.Respuesta;
import Models.Components.CustomHeaderRenderer;
import Models.Components.JTableEdited;
import Views.Forms.FormProductos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;

import Controllers.ControllerProducto;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JPopupMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;
import java.sql.Date;
import java.time.LocalDate;

public class JP_Productos extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable JT_Productos;
	private JComboBox CBBuscar;
	JDateChooser DCInicial,DCFinal;
	private JRadioButton RBFecha;
	
	//Variables
	private DAO.ProductosDAO productosDAO = new DAO.ProductosDAO();
	private ArrayList<Producto> productos = new ArrayList<Producto>();
	private Cursor cursor;
	private DefaultTableModel dtm;
	private ControllerProducto controllerProducto = new ControllerProducto();
	
	String[] columnNames = {"ID", "Codigo", "Nombre", "Descripcion",
			"Cantidad", "Fecha Cad.", "P. Público", "P. Mayoreo",
			"P. Adquisición", "Existencia", "Categoría", "Marca","Fecha de Registro",};
	
	// Ejemplo de datos (puedes llenar con datos reales de tu aplicación)
    Object[][] datos = {
            {1, "ABC123", "Producto A", "Descripción del producto A",
                    10, "2024-12-31", 100.0, 80.0, 50.0, 20, "Electrónica", "Marca A",""},
            {2, "XYZ456", "Producto B", "Descripción del producto B",
                    5, "2023-10-15", 120.0, 100.0, 70.0, 15, "Ropa", "Marca B",""},
            {3, "XYZ456", "Producto C", "Descripción del producto B",
                        5, "2023-10-15", 120.0, 100.0, 70.0, 15, "Ropa", "Marca B",""},
            {4, "XYZ456", "Producto D", "Descripción del producto B",
                            5, "2023-10-15", 120.0, 100.0, 70.0, 15, "Ropa", "Marca B",""}
    };
    private JTextField TBuscar;
    private JPopupMenu PMProductos;
	
	public JP_Productos() {
		setBackground(new Color(255, 0, 128));
		setBackground(new Color(255, 255, 255));
		setMinimumSize(new Dimension(892, 666));
		setMaximumSize(new Dimension(892, 666));
		setLayout(null);
	    
		JT_Productos = new JTableEdited();
		JT_Productos.setSelectionBackground(new Color(255, 0, 0));
		JT_Productos.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		//JT_Productos.setBackground(new Color(229, 247, 246));
		//JT_Productos.setBounds(10, 63, 880, 504);
		JT_Productos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JT_Productos.setDefaultEditor(Object.class, null);		
		dtm = new DefaultTableModel(datos, columnNames);
		JT_Productos.setModel(dtm);

		//CustomHeaderRenderer render = new CustomHeaderRenderer(1);
		JTableHeader header = JT_Productos.getTableHeader();
		header.setDefaultRenderer(new CustomHeaderRenderer(1));
		JT_Productos.setDefaultRenderer(Object.class, new CustomHeaderRenderer(1));
		
        JScrollPane scrollPane = new JScrollPane(JT_Productos);//,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBackground(new Color(255, 255, 255));
        scrollPane.setBounds(10, 44, 850, 483);
        
        this.add(scrollPane, BorderLayout.CENTER);
        ajustarTabla(JT_Productos);
        
        PMProductos = new JPopupMenu();        
        PMProductos.setFont(new Font("Arial", Font.PLAIN, 11));
		JMenuItem editarItem = new JMenuItem("Editar");
		editarItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarProducto();
			}
		});
		
		editarItem.setBackground(new Color(255, 255, 255));
		JMenuItem eliminarItem = new JMenuItem("Eliminar");
		eliminarItem.setBackground(new Color(255, 255, 255));
		JMenuItem agregarItem = new JMenuItem("Agregar");
		agregarItem.setBackground(new Color(255, 255, 255));
		PMProductos.add(editarItem);
		PMProductos.add(eliminarItem);
		PMProductos.add(agregarItem);
		PMProductos.setBackground(new Color(255, 255, 255));
		PMProductos.setBounds(0, 0, 101, 16);
        addPopup(JT_Productos, PMProductos);
        
        JButton BBuscar = new JButton("Buscar");
        BBuscar.setToolTipText("Buscar productos con aplicación de filtros");
        BBuscar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		cursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
                setCursor(cursor);		
                recargarPantalla();
        		cursor = Cursor.getDefaultCursor();
        		setCursor(cursor);
        		
        	}
        });
        BBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        BBuscar.setBounds(787, 13, 73, 20);
        add(BBuscar);
        
        DCInicial = new JDateChooser();
        DCInicial.setToolTipText("Fecha de Inicio");
        DCInicial.setMaximumSize(new Dimension(140, 28));
        DCInicial.setMinimumSize(new Dimension(140, 28));
        DCInicial.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        DCInicial.setBounds(365, 13, 120, 20);
        add(DCInicial);
        
        DCFinal = new JDateChooser();
        DCFinal.setToolTipText("Fecha Final");
        DCFinal.setMinimumSize(new Dimension(140, 28));
        DCFinal.setMaximumSize(new Dimension(140, 28));
        DCFinal.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        DCFinal.setBounds(491, 13, 120, 20);
        add(DCFinal);
        
        TBuscar = new JTextField();
        TBuscar.setBounds(621, 13, 156, 20);
        add(TBuscar);
        TBuscar.setColumns(10);
        
        CBBuscar = new JComboBox();
        CBBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        CBBuscar.setModel(new DefaultComboBoxModel(new String[] {"Buscar", "Fecha de Registro", "Stock Positivo", "Por Terminar", "Faltantes", "Precio de Compra", "A - Z", "Z - A"}));
        CBBuscar.setBounds(90, 13, 140, 22);
        add(CBBuscar);
        
        JButton BReporte = new JButton("Reporte");
        BReporte.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        BReporte.setBounds(10, 13, 73, 23);
        add(BReporte);
        
		
//		cursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
//        setCursor(cursor);		
//        iniciarPantalla();
//		cursor = Cursor.getDefaultCursor();
//		setCursor(cursor);
		
		RBFecha = new JRadioButton("Fecha Registro");
		RBFecha.setBackground(new Color(255, 255, 255));
		RBFecha.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		RBFecha.setBounds(258, 13, 101, 23);
		add(RBFecha);
		pintarTabla2();
	}
	
	private void actualizarProducto() {
		
		Producto producto = new Producto();
		
		try {
			
			int seleccion = JT_Productos.getSelectedRow();			
			System.out.println(seleccion);
			producto.setId_producto( (int) JT_Productos.getValueAt(seleccion, 0 ) );
			producto.setCodigo((String) JT_Productos.getValueAt(seleccion, 1) );
			producto.setNombre((String) JT_Productos.getValueAt(seleccion, 2) );
			producto.setDescripcion((String) JT_Productos.getValueAt(seleccion, 3));
			producto.setCantidad((String) JT_Productos.getValueAt(seleccion, 4));
			Date fehcaCaducidad = (Date) JT_Productos.getValueAt(seleccion, 5);
			producto.setFecha_caducidad(fehcaCaducidad);
			
			producto.setP_publico((Float) JT_Productos.getValueAt(seleccion, 6));
			producto.setP_Mayoreo((Float) JT_Productos.getValueAt(seleccion, 7));
			producto.setP_Adquisicion((Float) JT_Productos.getValueAt(seleccion, 8));
			
			producto.setExistencia((int) JT_Productos.getValueAt(seleccion, 9));
			producto.setCategoria((String) JT_Productos.getValueAt(seleccion, 10));
			producto.setMarca((String) JT_Productos.getValueAt(seleccion, 11));
			Date fechaRegistro = (Date) JT_Productos.getValueAt(seleccion,12);
			producto.setFechaRegistro(fechaRegistro);
			
			FormProductos formProductos = new FormProductos( Herramientas.tipoOperacion.actualizar , producto);
			formProductos.setVisible(true);		
			
		}catch (Exception e) {
		}
	}
	
	private void ajustarTabla(JTable tabla)
	{
		TableColumn columna;
	    
	    for (int i=0; i<tabla.getColumnCount(); i++) {
	    	columna=tabla.getColumnModel().getColumn(i);
		    columna.setPreferredWidth(120);
		    columna.setMaxWidth(200);
		    columna.setMinWidth(120);
	    }
	    JT_Productos.setRowHeight(22);
	    
	    
	    
	}	
	
	private void limpiarTablaProductos() {		
		try {
			while (JT_Productos.getRowCount() > 0) {
				dtm.removeRow(0);
	        }
		} catch (Exception  e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void iniciarPantalla() {
		
		Respuesta respuesta = new Respuesta("",true,null);
		ProductoBusquedaView productoBusquedaView = new ProductoBusquedaView(null,null,null,null,null);	
		productos = (ArrayList<Producto>) productosDAO.obtenerProductoFiltro((String) CBBuscar.getSelectedItem(), TBuscar.getText() ).getRespuesta();
		respuesta = controllerProducto.proceso(Herramientas.tipoOperacion.seleccionar, productoBusquedaView);
		
		if(!respuesta.getValor()) {
			JOptionPane.showMessageDialog(this,respuesta.getMensaje());
			return;
		}
		pintarTabla((ArrayList<Producto>) respuesta.getRespuesta());	
	}
	
	public void recargarPantalla() {
		
		ProductoBusquedaView productoBusquedaView = new ProductoBusquedaView();	
		Respuesta respuesta = new Respuesta("",true,null);
		
		try {
			
			productoBusquedaView.setBusqueda( TBuscar.getText() );
			productoBusquedaView.setFiltroBusqueda((String)CBBuscar.getSelectedItem());
			productoBusquedaView.setFechaInicio( DCInicial.getDate()!= null?  Herramientas.convertirFecha(DCInicial):null);
			productoBusquedaView.setFechaFinal(DCFinal.getDate() != null? Herramientas.convertirFecha(DCFinal):null);
			productoBusquedaView.setFecha( RBFecha.isSelected()? "Fecha_caducidad":"FechaRegistro" );
			respuesta = controllerProducto.proceso(Herramientas.tipoOperacion.seleccionar, productoBusquedaView);

			if(!respuesta.getValor()) {
				JOptionPane.showMessageDialog(this, "Problemas al obtener la lista de productos"+respuesta.getMensaje());
				return;
			}
			pintarTabla((ArrayList<Producto>) respuesta.getRespuesta());	
			
		}catch(Exception e) {
			System.out.print(e.getMessage());
			JOptionPane.showMessageDialog(this, "Problemas al obtener la lista de productos"+e.getMessage());
		}
		
	}
	
	public <T> Object[][]  GenerarMatrizObjetos(ArrayList<T> lista) {
		
		Object[][] datos = new Object[lista != null?lista.size():0][13];
		int i=0;
		
		int a=0,b=0;
		for (Object obj: lista) {
			
			Class<?> clazz = obj.getClass();
			Field[] fields = clazz.getDeclaredFields();
			
			System.out.println("Valores de "+clazz.getSimpleName());
			b=0;
			for(Field field: fields) {
				field.setAccessible(true);
				try {
					Object value = field.get(obj);
					System.out.println(field.getName()+" : "+ value);
					System.out.println(a+" "+b);				
					//rellenar arreglo
					datos[a][b] =  value == null? "":value;
				} catch (Exception e) {
					e.printStackTrace();
				}
				b++;
			}
			System.out.println();
			a++;
		}
		
		return datos;
	}
	
	public void pintarTabla2() {
		
		ArrayList<Producto> listproductos = new ArrayList<>();		
		listproductos.add(new Producto(1,"q1w2","Tornillo","Tornillo de Cruz","1",Date.valueOf(LocalDate.now()),10f,10f,10f,10,"","" ));
		listproductos.add(new Producto(1,"q1w3","Tornillo 2","Tornillo Plano","1",Date.valueOf(LocalDate.now()),10f,10f,10f,10,"","" ));
		listproductos.add(new Producto(1,"q1w4","Tornillo 3","Tornillo Estrella","1",Date.valueOf(LocalDate.now()),10f,10f,10f,10,"","" ));
		
		Object[][] datos  = GenerarMatrizObjetos(listproductos);
	
		dtm = new DefaultTableModel(datos,columnNames);
		JT_Productos.setModel(dtm);
		ajustarTabla(JT_Productos);
	}
	
	public void pintarTabla(ArrayList<Producto> productos) {
		
		Object[][] datos = new Object[productos != null?productos.size():0][13];
		int i=0;
		
		try {
		
			for(Producto producto: productos)
			{
				datos[i][0] = producto.getId_producto();
				datos[i][1] = producto.getCodigo();
				datos[i][2] = producto.getNombre();
				datos[i][3] = producto.getDescripcion();
				datos[i][4] = producto.getCantidad();
				datos[i][5] = producto.getFecha_caducidad();
				datos[i][6] = producto.getP_publico();
				datos[i][7] = producto.getP_Mayoreo();
				datos[i][8] = producto.getP_Adquisicion();
				datos[i][9] = producto.getExistencia();
				datos[i][10] = producto.getCategoria();
				datos[i][11] = producto.getMarca();
				datos[i][12] = producto.getFechaRegistro();
				i++;
			}
		
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}		
		dtm = new DefaultTableModel(datos,columnNames);
		JT_Productos.setModel(dtm);
		ajustarTabla(JT_Productos);
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

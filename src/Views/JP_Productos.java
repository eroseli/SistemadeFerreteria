package Views;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import DAO.ModelsDAO.Producto;
import DAO.ModelsDAO.Usuario;
import HerramientasConexion.Herramientas;
import HerramientasConexion.Herramientas.tipoOperacion;
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
import java.sql.Date;
import java.awt.event.KeyEvent;

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
        scrollPane.setBounds(10, 44, 864, 596);
        
        this.add(scrollPane, BorderLayout.CENTER);
        ajustarTabla(JT_Productos);
        
        PMProductos = new JPopupMenu();        
        PMProductos.setFont(new Font("Arial", Font.PLAIN, 11));
		
        JMenuItem seleccionarItem = new JMenuItem("Visualizar");
        seleccionarItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		visualizarProducto();
        	}
        	
        });
        
        
        JMenuItem editarItem = new JMenuItem("Editar");
		editarItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarProducto();
			}
		});	
		editarItem.setBackground(new Color(255, 255, 255));
		
		
		JMenuItem eliminarItem = new JMenuItem("Eliminar");
		eliminarItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eliminarRegistro();
			}
		});
		eliminarItem.setBackground(new Color(255, 255, 255));
		
		JMenuItem agregarItem = new JMenuItem("Agregar");
		agregarItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				agregarProducto();
			}
		});
		agregarItem.setBackground(new Color(255, 255, 255));
		
		
		PMProductos.add(seleccionarItem);
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
        BBuscar.setBounds(801, 13, 73, 20);
        add(BBuscar);
        
        DCInicial = new JDateChooser();
        DCInicial.setToolTipText("Fecha de Inicio");
        DCInicial.setMaximumSize(new Dimension(140, 28));
        DCInicial.setMinimumSize(new Dimension(140, 28));
        DCInicial.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        DCInicial.setBounds(393, 13, 120, 20);
        add(DCInicial);
        
        DCFinal = new JDateChooser();
        DCFinal.setToolTipText("Fecha Final");
        DCFinal.setMinimumSize(new Dimension(140, 28));
        DCFinal.setMaximumSize(new Dimension(140, 28));
        DCFinal.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        DCFinal.setBounds(525, 13, 120, 20);
        add(DCFinal);
        
        TBuscar = new JTextField();
        TBuscar.setBounds(657, 12, 140, 20);
        add(TBuscar);
        TBuscar.setColumns(10);
        
        CBBuscar = new JComboBox();
        CBBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        CBBuscar.setModel(new DefaultComboBoxModel(new String[] {"Buscar", "Fecha de Registro", "Stock Positivo", "Por Terminar", "Faltantes", "Precio de Compra", "A - Z", "Z - A"}));
        CBBuscar.setBounds(151, 11, 140, 22);
        add(CBBuscar);
        
        JButton BAgregar = new JButton("Agregar");
        BAgregar.setSelected(true);
        BAgregar.setMnemonic('N');
        BAgregar.setMnemonic(KeyEvent.VK_N);
        BAgregar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		Agregar();
        	}
        });
        BAgregar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        BAgregar.setBounds(75, 9, 73, 23);
        add(BAgregar);
        
		
		cursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
        setCursor(cursor);		
        iniciarPantalla();
		cursor = Cursor.getDefaultCursor();
		setCursor(cursor);
		
		RBFecha = new JRadioButton("Fecha Registro");
		RBFecha.setBackground(new Color(255, 255, 255));
		RBFecha.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		RBFecha.setBounds(284, 10, 111, 23);
		add(RBFecha);

	}
	
	private void Agregar() {
		
		FormProductos formproductos = new FormProductos(this,tipoOperacion.insertar,null);
		formproductos.setVisible(true);
	}
	
	private void eliminarRegistro() {
		
		Optional<Producto> pro = java.util.Optional.empty();
		Respuesta respuesta = new Respuesta("",true,null);
		
		try {
			
			int indice = JT_Productos.getSelectedRow();
			
			if (indice == -1) {
				JOptionPane.showMessageDialog(this,"No hay fila Seleccionada", "Advertencia",JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			int idProducto = (int) JT_Productos.getValueAt(indice, 0);
			
			pro = productos.stream()
					.filter(p -> p.getId_producto() == idProducto)
					.findFirst();
			
			if (pro.orElse(null)== null) {
				JOptionPane.showMessageDialog(this,"Producto No Encontrado","Error" ,JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			Producto p = pro.get();
			
			int continuar = JOptionPane.showConfirmDialog(this, 
                    "¿Estás seguro que deseas Eliminar el Producto "+ p.getCodigo()+" ?", 
                    "Confirmación", 
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.QUESTION_MESSAGE);

			System.out.println("Continuar"+continuar);
			
			if (continuar == JOptionPane.NO_OPTION || continuar == JOptionPane.CLOSED_OPTION ) {
				return;
			}
			
			respuesta = controllerProducto.proceso(Herramientas.tipoOperacion.eliminar, p.getCodigo());
			
			if (!respuesta.getValor()) {
				JOptionPane.showMessageDialog(this, respuesta.getMensaje(),"Error",JOptionPane.ERROR_MESSAGE);
				return ;
			}
			
			iniciarPantalla();
			JOptionPane.showMessageDialog(this,"Registro "+p.getCodigo()+" Eliminado Correctamente.");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	private void agregarProducto() {
		
		FormProductos formProductos = new FormProductos(this,Herramientas.tipoOperacion.insertar, null);
		formProductos.setVisible(true);
		
	}
	
	private void visualizarProducto() {
		
		try {
			
			int seleccion = JT_Productos.getSelectedRow();
			
			if(seleccion == -1) {
				JOptionPane.showMessageDialog(this,"Seleccione Algún Producto","Advertencia", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			int idProducto = (int) JT_Productos.getValueAt(seleccion, 0);
			
			Producto producto = productos.stream()
					.filter(p-> p.getId_producto() == idProducto)
					.findFirst()
					.orElse(null);
			
			if (producto == null) {
				JOptionPane.showMessageDialog(this,"Producto No Encontrado","Error",JOptionPane.ERROR_MESSAGE);
				return;		
			}
			
			producto.setCategoria((String) JT_Productos.getValueAt(seleccion, 10));
			producto.setMarca((String) JT_Productos.getValueAt(seleccion, 11));

			FormProductos formProductos = new FormProductos(this, Herramientas.tipoOperacion.seleccionar , producto);
			formProductos.setVisible(true);	
			
			
		}
		catch(Exception e) {
			System.out.println("Error al obtener el valor de pantalla"+e.getMessage());
		}
		
		
	}

	
	private void actualizarProducto() {
		
		Producto producto = new Producto();
		
		try {
			
			int seleccion = JT_Productos.getSelectedRow();
			
			if(seleccion == -1)
			{
				JOptionPane.showMessageDialog(this, "Seleccione Algún Producto.","Advertencia",JOptionPane.WARNING_MESSAGE);
				return;
			}
				
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
			
			FormProductos formProductos = new FormProductos(this, Herramientas.tipoOperacion.actualizar , producto);
			formProductos.setVisible(true);		
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void ajustarTabla(JTable tabla)
	{
		TableColumn columna;
	    
	    for (int i=0; i<tabla.getColumnCount(); i++) {
	    	columna=tabla.getColumnModel().getColumn(i);
		    columna.setPreferredWidth(120);
		    columna.setMaxWidth(250);
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
	
	public boolean validarParametrosBusqueda() {
		
		
		
		try {
						
			if (DCInicial.getDate()  == null && DCFinal.getDate() ==null) {
				return true;
			}
			else if (DCInicial.getDate()  != null && DCFinal.getDate() !=null) {
				return true;
			}
			else {
				JOptionPane.showConfirmDialog(this,"Para Buscar por Fechas es necesario Introducir una Fecha de Inicio y Final Válida.","Advertencia",JOptionPane.WARNING_MESSAGE);
				return false;
				
			}
		} catch (Exception e) {
			System.out.println("Error al validar Parametros de La Busqueda : "+e.getMessage());
			e.printStackTrace();
		}
		
		
		return true;
	}
	
	public void recargarPantalla() {
		
		ProductoBusquedaView productoBusquedaView = new ProductoBusquedaView();	
		Respuesta respuesta = new Respuesta("",true,null);
		
		try {
			
			if (!validarParametrosBusqueda()) {
				return;
			}
			
			productoBusquedaView.setBusqueda( TBuscar.getText() );
			productoBusquedaView.setFiltroBusqueda((String)CBBuscar.getSelectedItem());
			productoBusquedaView.setFechaInicio( DCInicial.getDate()!= null?  Herramientas.convertirFecha(DCInicial):null);
			productoBusquedaView.setFechaFinal(DCFinal.getDate() != null? Herramientas.convertirFecha(DCFinal):null);
			productoBusquedaView.setFecha( RBFecha.isSelected()? "FechaRegistro":"Fecha_caducidad" );
			System.out.println("Tipo de fecha a buscar : "+productoBusquedaView.getFecha());
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

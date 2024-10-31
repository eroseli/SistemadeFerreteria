package Views;

import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import java.awt.Component;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import Controllers.ControllerUsuario;
import Controllers.ControllerVenta;
import DAO.ModelsDAO.Producto;
import DAO.ModelsDAO.ProductosVentaDAO;
import DAO.ModelsDAO.Usuario;
import DAO.ModelsDAO.Venta;
import HerramientasConexion.Herramientas;
import Models.ProductoBusquedaView;
import Models.Respuesta;
import Models.Components.CustomHeaderRenderer;
import Views.Emergentes.JD_ProductoDescripcionVenta;
import Views.Emergentes.JD_TotalesVentaFecha;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;

import com.toedter.calendar.JDateChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPopupMenu;

public class JP_Ventas extends JPanel {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel dtm;
	private DefaultTableModel dtmp;
	private JDateChooser DCInicial;
	private JDateChooser DCFinal;
	
	String[] columnNames = {"", "ID","Usuario",  "Cliente", "Num. Productos",
			"Pago Tarjeta", "Pago Efectivo","Descuento", "Fecha","Hora"};
	
	String[] NamesProductosTabla = { "","Código", "Nombre","Descripción","Cantidad","Precio","Monto",
			"Descuento M.","Descuento Esp.","Fecha de Registro"};
    
    private JTable TUsuarios;
    
    
    //variables 
    
    List<Usuario> usuarios = new ArrayList<>();
    ControllerVenta controllerVenta = new ControllerVenta();
    private JTable TProductos;
    
	public JP_Ventas() {
		setMinimumSize(new Dimension(892, 666));
		setMaximumSize(new Dimension(872, 644));
		setLayout(null);
		
		JScrollPane SPUsuarios = new JScrollPane((Component) null); //tabla usuarios
		SPUsuarios.setBackground(Color.WHITE);
		SPUsuarios.setBounds(12, 42, 850, 321);
		add(SPUsuarios);
		
		TUsuarios = new JTable();
		TUsuarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				seleccionarClienteProducto();
			}
		});
		
		dtm = new DefaultTableModel(null, columnNames);
		TUsuarios.setModel(dtm);
		SPUsuarios.setViewportView(TUsuarios);
		
		JTableHeader header = TUsuarios.getTableHeader();
		header.setDefaultRenderer(new CustomHeaderRenderer(2));
		TUsuarios.setDefaultRenderer(Object.class, new CustomHeaderRenderer(2));
		
		JScrollPane SPProductos = new JScrollPane((Component) null); //tabla productos
		SPProductos.setBackground(Color.WHITE);
		SPProductos.setBounds(12, 375, 850, 228);
		add(SPProductos);
		
		TProductos = new JTable();
		dtmp = new DefaultTableModel(null,NamesProductosTabla );
		TProductos.setModel(dtmp);				
		SPProductos.setViewportView(TProductos);
		
		JTableHeader headerProducto =  TProductos.getTableHeader();
		headerProducto.setDefaultRenderer(new  CustomHeaderRenderer(2));
		TProductos.setDefaultRenderer(Object.class, new CustomHeaderRenderer(2));

		JPopupMenu PMProductos = new JPopupMenu();
		addPopup(SPProductos, PMProductos);
		JMenuItem menueditar = new JMenuItem("Editar");
		menueditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				presentarDescripcionProducto();
			}
		});
		PMProductos.add(menueditar);
		TProductos.setComponentPopupMenu(PMProductos);
		
		JButton BBuscar = new JButton("Buscar");
		BBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iniciarPantalla(1);
			}
		});
		BBuscar.setToolTipText("Buscar productos con aplicación de filtros");
		BBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		BBuscar.setBounds(789, 11, 73, 20);
		add(BBuscar);
		
		DCInicial = new JDateChooser();
		DCInicial.setToolTipText("Fecha de Inicio");
		DCInicial.setMinimumSize(new Dimension(140, 28));
		DCInicial.setMaximumSize(new Dimension(140, 28));
		DCInicial.setFont(new Font("Dialog", Font.PLAIN, 11));
		DCInicial.setBounds(481, 10, 120, 20);
		add(DCInicial);
		
		DCFinal = new JDateChooser();
		DCFinal.setToolTipText("Fecha de Inicio");
		DCFinal.setMinimumSize(new Dimension(140, 28));
		DCFinal.setMaximumSize(new Dimension(140, 28));
		DCFinal.setFont(new Font("Dialog", Font.PLAIN, 11));
		DCFinal.setBounds(657, 10, 120, 20);
		add(DCFinal);
		
		JLabel lblNewLabel = new JLabel("Final");
		lblNewLabel.setBounds(606, 12, 46, 16);
		add(lblNewLabel);
		
		JLabel lblInicio = new JLabel("Inicio");
		lblInicio.setBounds(435, 14, 46, 16);
		add(lblInicio);
		
		JButton BtnTotales = new JButton("Totales");
		BtnTotales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				presentarTotalesVentaFecha();
			}
		});
		BtnTotales.setBounds(304, 6, 117, 29);
		add(BtnTotales);

		
		limpiarTablaVentas();
		inicializarFechas();
		iniciarPantalla(1); // sin filtros
		
	}
	public void inicializarFechas() {
		long currentTimeMillis = System.currentTimeMillis();
		Date currentDate = new Date(currentTimeMillis);	        
		DCInicial.setDate(currentDate);
		DCFinal.setDate(currentDate);
		
	}
	
	public void presentarTotalesVentaFecha() {
		
		if (!validaFecha()) {
			return;
		}
		
		Date fechaI = Herramientas.convertirFecha(DCInicial);
		Date fechaF = Herramientas.convertirFecha(DCFinal);			
		
		
		JD_TotalesVentaFecha jd_TotalesVentaFecha = new JD_TotalesVentaFecha(fechaI, fechaF);
		jd_TotalesVentaFecha.setVisible(true);
		
	}
	
	public void presentarDescripcionProducto() {
		
		int indice =0; 
		
		try {
			
			indice = (int) TProductos.getValueAt(TProductos.getSelectedRow(), 0); // obtener el id
			System.out.println(indice);
			JD_ProductoDescripcionVenta jd_ProductoDescripcionVenta = new JD_ProductoDescripcionVenta(indice);
			jd_ProductoDescripcionVenta.setVisible(true);		
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public boolean validaFecha() {
		
		try {			
			Date fechaI = Herramientas.convertirFecha(DCInicial);
			Date fechaF = Herramientas.convertirFecha(DCFinal);
			
			if( (fechaI == null || fechaF == null) ) {
		        JOptionPane.showMessageDialog(null, "Favor de validar las fechas", "Error", JOptionPane.ERROR_MESSAGE);
		        return false;
			}			
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	public void iniciarPantalla(int operacion) {
		
		Respuesta respuesta = new Respuesta("",true,null);
		
		// limpiar tabla de productos
		limpiarTablaProducto();
		
		if (!validaFecha()) {
			return;
		}
		
		Date fechaI = Herramientas.convertirFecha(DCInicial);
		Date fechaF = Herramientas.convertirFecha(DCFinal);			
		respuesta = controllerVenta.obtenerventa(operacion,fechaI, fechaF);
		 
		 usuarios = (ArrayList<Usuario>) respuesta.getRespuesta();
		 		
		if(!respuesta.getValor()) {
			JOptionPane.showMessageDialog(this,respuesta.getMensaje());
			return;
		}
		
		pintarTabla((ArrayList<Venta>) respuesta.getRespuesta());	
		
		
	}
	
	public void seleccionarClienteProducto() {
		
		int indice = 0; 
		int i =0; // indice del arreglo
		Respuesta respuesta = new Respuesta("",true,null);
		ControllerVenta controllerventa = new ControllerVenta();
		ArrayList<ProductosVentaDAO>  productosVentaDAO = new ArrayList<ProductosVentaDAO>();			
		System.out.println("Hola");
		try {
			
			System.out.println("Indice : "+TUsuarios.getValueAt(TUsuarios.getSelectedRow(), 1));
			System.out.println("Valor tomado :"+ TUsuarios.getValueAt(TUsuarios.getSelectedRow(), 1)+ ".");
			indice = Integer.parseInt(TUsuarios.getValueAt(TUsuarios.getSelectedRow(), 1).toString()); // obtener el id
			respuesta  = controllerventa.obtenerProductosVenta(indice);
						
			if (respuesta.getRespuesta() == null) {
				System.out.println("Entro aqui");
				return;
			}
			
			productosVentaDAO = (ArrayList<ProductosVentaDAO>) respuesta.getRespuesta();
			System.out.println(productosVentaDAO.size());
			
			Object[][] datos = new Object[productosVentaDAO != null? productosVentaDAO.size():0][10];
			
			for(ProductosVentaDAO productoventa: productosVentaDAO)
				{
					datos[i][0] = productoventa.getIdHistorial();
					datos[i][1] = productoventa.getCodigo();
					datos[i][2] = productoventa.getNombre();
					datos[i][3] = productoventa.getDescripcion();
					datos[i][4] = productoventa.getCantidad();
					datos[i][5] = productoventa.getPrecio();
					datos[i][6] = productoventa.getMonto();
					datos[i][7] = productoventa.getDescuentoM();
					datos[i][8] = productoventa.getDescuentoEsp();
					datos[i][9] = productoventa.getFechaRegistro();
					i++;
					System.out.println("Entro al envento sS");
				}

			DefaultTableModel dtm = new DefaultTableModel(datos,NamesProductosTabla);
			//dtm.setColumnIdentifiers(columnNames);
			TProductos.setModel(dtm);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error al obtener el valor del campo"+e.getMessage());
		}
		
	}
	
	public void pintarTabla(ArrayList<Venta> ventas) {
		
		Object[][] datos = new Object[ventas != null?ventas.size():0][11];
		int i=0;
		
		try {
		
			for(Venta venta: ventas)
			{
				datos[i][0] = venta.getConsecutivo();
				datos[i][1] = venta.getIdVenta();
				datos[i][2] = venta.getUsuario();
				datos[i][3] = venta.getCliente();
				datos[i][4] = venta.getProductos();
				datos[i][5]= venta.getTarjeta();
				datos[i][6] = venta.getEfectivo();
				datos[i][7] = venta.getDescuento()==1?"SI":"NO";
				datos[i][8] = venta.getFecha();
				datos[i][9] = venta.getHora();
				i++;
			}
		
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}		
		dtm = new DefaultTableModel(datos,columnNames);
		TUsuarios.setModel(dtm);
		ajustarTabla(TUsuarios);
	}
	
	public void ajustarTabla(JTable jTable) {}
	
	private void limpiarTablaVentas() {		
		try {
			dtm = new DefaultTableModel(null,columnNames);
			TUsuarios.setModel(dtm);
		} catch (Exception  e) {
			System.out.println("Error al limpiar tabla Usuarios: "+e.getMessage());
		}
	}
	
	private void limpiarTablaProducto() {
		try {
			dtmp = new DefaultTableModel(null,columnNames);
			TProductos.setModel(dtmp);
		} catch (Exception e) {
			System.out.println("Error al limpiar tabla Productos: "+e.getMessage());
		}
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

package Views;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.omg.CORBA.OBJ_ADAPTER;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;

import Controllers.ControllerMercancia;
import DAO.ModelsDAO.MercanciaDAO;
import DAO.ModelsDAO.MercanciaDetDAO;
import DAO.ModelsDAO.Venta;
import HerramientasConexion.Herramientas;
import HerramientasConexion.Herramientas.tipoOperacion;
import Models.Respuesta;
import Models.Components.CustomHeaderRenderer;
import Models.Components.JTableEdited;
import Services.MercanciaService;
import Utileria.ComponentesDesing;
import Views.Forms.JD_DetalleESMercancia;

import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JP_EntradaSalidaMercancia extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTableEdited TESMercancia;
	private DefaultTableModel dtm;
	private JPopupMenu PM_Mercancia;
	private JDateChooser DCInicial;
	private JDateChooser DCFinal;
	
	String[] columnNames = {"Id_HistorialCompra", "Proveedor", "Total Productos",
			"Total Compra", "Descripción", "Estado Compra", "Operación","Fecha Registro", "Estatus"};
	
	
	//variables
	MercanciaDAO mercanciaDAO = new MercanciaDAO();
	ArrayList<MercanciaDAO> mercancias = new  ArrayList<MercanciaDAO>();
	ArrayList<MercanciaDetDAO> mercanciasDet = new  ArrayList<MercanciaDetDAO>();
	
	
	
	public JP_EntradaSalidaMercancia() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(new Color(254, 255, 255));
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 247, 247));
		panel.setBounds(6, 6, 865, 85);
		add(panel);
		panel.setLayout(null);
		
		JButton BBuscar = new JButton("Buscar");
		BBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buscar();
			}
		});
		BBuscar.setBounds(742, 6, 117, 29);
		panel.add(BBuscar);
		
		DCFinal = new JDateChooser();
		DCFinal.setBounds(585, 9, 150, 26);
		panel.add(DCFinal);
		
		DCInicial = new JDateChooser();
		DCInicial.setBounds(380, 9, 150, 26);
		panel.add(DCInicial);
		
		JLabel lblAl = new JLabel("Al");
		lblAl.setBounds(542, 11, 34, 16);
		panel.add(lblAl);
		
		JLabel lblNewLabel = new JLabel("De");
		lblNewLabel.setBounds(325, 11, 42, 16);
		panel.add(lblNewLabel);
		
		JButton BNuevo = new JButton("Nuevo Registro");
		BNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nuevaCompra();
			}
		});
		BNuevo.setBounds(6, 6, 129, 29);
		panel.add(BNuevo);
		
		JButton BAbrir = new JButton("Abrir");
		BAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirMercancia();
			}
		});
		BAbrir.setBounds(130, 6, 69, 29);
		panel.add(BAbrir);
		
		JScrollPane SP_ESMercancia = new JScrollPane();
		SP_ESMercancia.setBorder(null);
		SP_ESMercancia.setBackground(new Color(254, 255, 255));
		SP_ESMercancia.setBounds(6, 96, 865, 602);
		add(SP_ESMercancia);
		
		TESMercancia = new JTableEdited();
		TESMercancia.setBorder(null);
		TESMercancia.setRowHeight(20);
		TESMercancia.setDefaultEditor(Object.class, null);
		dtm = new DefaultTableModel(null,columnNames);
		SP_ESMercancia.setViewportView(TESMercancia);
		TESMercancia.setDefaultRenderer(Object.class, new CustomHeaderRenderer(2));
		// Disenio tabla 
		JTableHeader header = TESMercancia.getTableHeader();
		header.setDefaultRenderer(new CustomHeaderRenderer(2));
		TESMercancia.setDefaultRenderer(Object.class, new CustomHeaderRenderer(2));
		
		
		TESMercancia.setModel(dtm);
		
				
		PM_Mercancia = new JPopupMenu();
		PM_Mercancia.setBounds(0, 0, 103, 65);
		add(PM_Mercancia);
		
		JMenuItem abrir = new JMenuItem("Abrir Registro");
		JMenuItem agregar = new JMenuItem("Agregar");
		PM_Mercancia.add(abrir);
		PM_Mercancia.add(agregar);	
		TESMercancia.setComponentPopupMenu(PM_Mercancia);
		abrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirMercancia();
            }
        });
		agregar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nuevaCompra();
			}
		});
		
		addPopup(SP_ESMercancia, PM_Mercancia);
		
		inicializarFechas();
		buscar();
	}
	
	private void nuevaCompra() {
		JD_DetalleESMercancia detalleESMercancia = new JD_DetalleESMercancia(this, Herramientas.tipoOperacion.insertar,null );
	 	detalleESMercancia.setVisible(true);	
	}
	
	private void abrirMercancia() {
		
		Optional<MercanciaDAO> mercancia = java.util.Optional.empty();
		
		try {
			
			int indice = TESMercancia.getSelectedRow();
			
			if(indice == -1){
				JOptionPane.showMessageDialog(this,"No hay una fila seleccionada ","Advertencia",JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			int idMercancia =(int) TESMercancia.getValueAt(indice, 0);						
			
			mercancia = mercancias.stream()
					.filter( m -> m.getId_HistorialCompra() == idMercancia)
					.findFirst();
			
			if (mercancia.orElse(null)== null) {
				JOptionPane.showMessageDialog(this,"Usuario No Encontrado","Error" ,JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			this.mercanciaDAO = mercancia.get();
			
			JD_DetalleESMercancia detalleESMercancia = new JD_DetalleESMercancia(this, Herramientas.tipoOperacion.seleccionar,this.mercanciaDAO );
			 detalleESMercancia.setVisible(true);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	
	private void inicializarFechas() {
		long currentTimeMillis = System.currentTimeMillis();
		Date currentDate = new Date(currentTimeMillis);	        
		DCInicial.setDate(currentDate);
		DCFinal.setDate(currentDate);
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
	
	public void buscar() {
		
		Respuesta respuesta = new Respuesta("",true,null);
		MercanciaService mercanciaService = new MercanciaService();
		
		if (!validaFecha()) {
			return;
		}
			
		try {
			Date fechaI = Herramientas.convertirFecha(DCInicial);
			Date fechaF = Herramientas.convertirFecha(DCFinal);			
			respuesta = mercanciaService.obtenerVentaFechas(fechaI, fechaF);
			
			System.out.println("Entro");
			
			if(!respuesta.getValor()) {
				JOptionPane.showMessageDialog(this, respuesta.getMensaje());
				return;
			}
			
			mercancias = (ArrayList<MercanciaDAO>) respuesta.getRespuesta();
			
			pintarTabla();
			ComponentesDesing.PreferredWithTableEntradaSalidaMercancia(TESMercancia);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void pintarTabla() {
		
		Object[][] datos = new Object[mercancias != null?mercancias.size():0][11];
		int i=0;
		
		try {
		
			for(MercanciaDAO merca: mercancias)
			{
				datos[i][0] = merca.getId_HistorialCompra();
				datos[i][1] = merca.getId_Proveedor();
				datos[i][2] = merca.getTotalProductos();
				datos[i][3] = merca.getTotalCompra();
				datos[i][4] = merca.getDescripcion();
				datos[i][5]= merca.getEstadoPago();
				datos[i][6] = merca.getTipoOperacion();
				datos[i][7] = merca.getFechaRegistro();
				datos[i][8] = merca.getEstatus();
				i++;
			}
		
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}		
		dtm = new DefaultTableModel(datos,columnNames);
		TESMercancia.setModel(dtm);
		//ajustarTabla(TESMercancia);
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

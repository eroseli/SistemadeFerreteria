package Views;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.toedter.calendar.JDateChooser;

import Controllers.ControllerMovimientosEfectivo;
import DAO.ModelsDAO.MercanciaDAO;
import DAO.ModelsDAO.MovimientoEfectivo;
import HerramientasConexion.Herramientas;
import Models.Respuesta;
import Models.Components.CustomHeaderRenderer;
import Models.Components.JTableEdited;
import Services.MovimientosEfectivoService;
import Utileria.ComponentesDesing;
import Views.Emergentes.JD_TotalesVentaFecha;
import Views.Forms.FormMovimientoEfectivo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;
import java.awt.event.ActionEvent;

public class JP_EntradaSalidaEfectivo extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTableEdited TMovimientosEfectivo;
	private JDateChooser DCFinal ;
	private JDateChooser DCInicial;
	private DefaultTableModel dtm;
	private Respuesta respuesta = null;
	
	String[] columnNames = {"Folio","Concepto", "Observación","Usuario","Monto","Método Pago","Transacción","Estado","Fecha"};
	
	private ArrayList<MovimientoEfectivo> movimientosefectivo = new ArrayList<MovimientoEfectivo>();
	
	public JP_EntradaSalidaEfectivo() {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 47, 868, 547);
		add(scrollPane);
		
		TMovimientosEfectivo = new JTableEdited();
		TMovimientosEfectivo.setBorder(null);
		TMovimientosEfectivo.setRowHeight(20);
		TMovimientosEfectivo.setDefaultEditor(Object.class, null);
		dtm = new DefaultTableModel(null,columnNames);		
		TMovimientosEfectivo.setDefaultRenderer(Object.class, new CustomHeaderRenderer(2));

		JTableHeader header = TMovimientosEfectivo.getTableHeader();
		header.setDefaultRenderer(new CustomHeaderRenderer(2));
		TMovimientosEfectivo.setDefaultRenderer(Object.class, new CustomHeaderRenderer(2));
		
		
		TMovimientosEfectivo.setModel(dtm);
		
		scrollPane.setViewportView(TMovimientosEfectivo);
		
		JButton BBuscar = new JButton("Buscar");
		BBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Buscar();
			}
		});
		BBuscar.setBounds(757, 6, 117, 29);
		add(BBuscar);
		
		DCInicial= new JDateChooser();
		DCInicial.setBounds(414, 9, 123, 26);
		add(DCInicial);
		
		DCFinal = new JDateChooser();
		DCFinal.setBounds(622, 9, 123, 26);
		add(DCFinal);
		
		JLabel lblNewLabel = new JLabel("Del");
		lblNewLabel.setBounds(341, 11, 61, 16);
		add(lblNewLabel);
		
		JLabel lblAl = new JLabel("al");
		lblAl.setBounds(549, 11, 61, 16);
		add(lblAl);
		
		JButton BAgregar = new JButton("Agregar");
		BAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertar();
			}
		});
		BAgregar.setBounds(6, 6, 117, 29);
		add(BAgregar);
		
		JButton BEliminar = new JButton("Eliminar");
		BEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminar();
			}
		});
		BEliminar.setBounds(120, 6, 89, 29);
		add(BEliminar);
		
		JButton BCaja = new JButton("Caja");
		BCaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				caja();
			}
		});
		BCaja.setBounds(207, 6, 89, 29);
		add(BCaja);
		
		inicializarFechas();
		Buscar();

	}
	
	public void caja() {
		
		JD_Caja caja = new JD_Caja(0);
		caja.setVisible(true);
		
	}
	
	public void inicializarFechas() {
		long currentTimeMillis = System.currentTimeMillis();
		Date currentDate = new Date(currentTimeMillis);	        
		DCInicial.setDate(currentDate);
		DCFinal.setDate(currentDate);
	}
	
	public void insertar() {
		
		FormMovimientoEfectivo formMovimientoEfectivo = new FormMovimientoEfectivo(this);
		formMovimientoEfectivo.setVisible(true);
		
	}
	
	public void eliminar() {
		respuesta = new  Respuesta("",true,null);
		MovimientoEfectivo movimiento_efectivo =null;
		ControllerMovimientosEfectivo controllerMovimientosEfectivo = new ControllerMovimientosEfectivo();
		
		try {
			
			int indice = TMovimientosEfectivo.getSelectedRow();
			
			if (indice == -1) {
				JOptionPane.showMessageDialog(this, "Seleccione un Registro","Advertencia",JOptionPane.WARNING_MESSAGE);
			}
			
			int idHistorial = Integer.parseInt( TMovimientosEfectivo.getValueAt(indice, 0).toString() );
			
			if ( JOptionPane.NO_OPTION == JOptionPane.showConfirmDialog(
	                this,  
	                "¿Desea eliminar el registro con Folio "+idHistorial+" ?",
	                "Confirmar eliminación",
	                JOptionPane.YES_NO_OPTION, 
	                JOptionPane.QUESTION_MESSAGE 
					)) {
				return;
			}
			
			Optional<MovimientoEfectivo> movimiento =movimientosefectivo.stream()
					.filter(m -> m.getIdMovimientoEfectivo() == idHistorial)
					.findFirst();
			
			movimiento_efectivo = movimiento.get();
			
			respuesta = controllerMovimientosEfectivo.proceso(Herramientas.tipoOperacion.eliminar, movimiento_efectivo);
			
			if (!respuesta.getValor()) {
				JOptionPane.showMessageDialog(this, respuesta.getMensaje(),"Error",JOptionPane.ERROR_MESSAGE);
			}
			
			Buscar();
			JOptionPane.showMessageDialog(this, respuesta.getMensaje(),"Mensaje",JOptionPane.INFORMATION_MESSAGE);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void Buscar() {
		
		Respuesta respuesta = new Respuesta("",true,null);
		
		MovimientosEfectivoService movimientosEfectivoService = new MovimientosEfectivoService();
		
		
		try {
			
			Date fechaI = Herramientas.convertirFecha(DCInicial);
			Date fechaF = Herramientas.convertirFecha(DCFinal);			
			respuesta = movimientosEfectivoService.obtenerMovimientosEfectivo(fechaI, fechaF);
			
			
			if (!respuesta.getValor()) {
				JOptionPane.showMessageDialog(this,respuesta.getMensaje(),"Error",JOptionPane.ERROR_MESSAGE);
			}
			movimientosefectivo = (ArrayList<MovimientoEfectivo>) respuesta.getRespuesta();
			
			pintarTabla();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this,"Error : "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}

	}
	
	public void pintarTabla() {
		
		Object[][] datos = new Object[movimientosefectivo != null?movimientosefectivo.size():0][9];
		int i=0;
		
		try {
		
			for(MovimientoEfectivo movimiento: movimientosefectivo)
			{
				datos[i][0] = movimiento.getIdMovimientoEfectivo();
				datos[i][1] = movimiento.getConcepto();
				datos[i][2] = movimiento.getObservaciones();
				datos[i][3] = movimiento.getIdUsuario();
				datos[i][4] = movimiento.getMonto();
				datos[i][5]= movimiento.getMetodoPago();
				datos[i][6] = movimiento.getTipoTransaccion();
				datos[i][7] = movimiento.getEstadoMovimiento();
				datos[i][8] = movimiento.getFechaRegistro();
				i++;
			}
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}		
		dtm = new DefaultTableModel(datos,columnNames);
		TMovimientosEfectivo.setModel(dtm);
		//ComponentesDesing.PreferredWithTableMovimientosEfectivo(TMovimientosEfectivo);
	}
	
}

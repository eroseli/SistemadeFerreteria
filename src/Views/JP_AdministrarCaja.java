package Views;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;

import DAO.ModelsDAO.AperturaCaja;
import DAO.ModelsDAO.Cliente;
import HerramientasConexion.Herramientas;
import Models.Respuesta;
import Models.Components.CustomHeaderRenderer;
import Models.Components.JTableEdited;
import Services.AperturaCajaService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class JP_AdministrarCaja extends JPanel {

	private static final long serialVersionUID = 1L;

	private DefaultTableModel dtm;
	Respuesta respuesta = null;
	ArrayList<AperturaCaja> aperturas = new ArrayList<AperturaCaja>();
	AperturaCaja aperturaCaja = null;
	private JTable TAperturasCaja;
	private JDateChooser DCFinal;
	private JDateChooser DCInicio;	
	
	String[] columnNames = {"Folio","M. Apertura","Obs. Apertua","Fecha Apertura","M. Cierre","Obs. Cierre","Fecha Cierre","Estatus", "Usuario"};
	
	public JP_AdministrarCaja() {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 52, 868, 408);
		add(scrollPane);
		
		TAperturasCaja = new JTableEdited();
		dtm = new DefaultTableModel(null, columnNames);
		TAperturasCaja.setModel(dtm);
		TAperturasCaja.setDefaultEditor(Object.class, null);
//		TAperturasCaja.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TAperturasCaja.setRowHeight(26);

		JTableHeader header = TAperturasCaja.getTableHeader();
		header.setDefaultRenderer(new CustomHeaderRenderer(2));
		TAperturasCaja.setDefaultRenderer(Object.class, new CustomHeaderRenderer(2));
		
		
		scrollPane.setViewportView(TAperturasCaja);
		
		JButton BBuscar = new JButton("Busar");
		BBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Buscar();
			}
		});
		BBuscar.setBounds(757, 11, 117, 29);
		add(BBuscar);
		
		DCFinal = new JDateChooser();
		DCFinal.setBounds(583, 14, 162, 26);
		add(DCFinal);
		
		JLabel lblNewLabel = new JLabel("Al");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(534, 16, 48, 16);
		add(lblNewLabel);
		
		DCInicio = new JDateChooser();
		DCInicio.setBounds(366, 11, 162, 26);
		add(DCInicio);

		inicializarFechas();
		Buscar();
		
	}
	
	public void inicializarFechas() {
		long currentTimeMillis = System.currentTimeMillis();
		Date currentDate = new Date(currentTimeMillis);	        
		DCInicio.setDate(currentDate);
		DCFinal.setDate(currentDate);
	} 
	
	
	public void Buscar() {
		
		AperturaCajaService aperturaCajaService = new AperturaCajaService();
		respuesta = new Respuesta("",true,null);
		
		try {
			
			Date fechaI = Herramientas.convertirFecha(DCInicio);
			Date fechaF = Herramientas.convertirFecha(DCFinal);			
			
			respuesta = aperturaCajaService.seleccionCajasFecha(fechaI,fechaF);

			if (!respuesta.getValor()) {
				JOptionPane.showMessageDialog(this,respuesta.getMensaje(),"Error",JOptionPane.ERROR_MESSAGE);
			}
			
			aperturas = (ArrayList<AperturaCaja>) respuesta.getRespuesta();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this,"Error : "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);		
		}
		pintarTabla();
		
	}
	
	public void pintarTabla() {
		
		respuesta = new Respuesta("",true,null);
		
		
		Object[][] datos = new Object[aperturas != null? aperturas.size():0][10];
		
		int i=0;

		
		try {
			
			for(AperturaCaja aperturaC: aperturas)
			{
				datos[i][0] = aperturaC.getIdApertura();
				datos[i][1] = aperturaC.getMontoApertura();
				datos[i][2] = aperturaC.getObservacionesEntrada();
				datos[i][3] = aperturaC.getFechaApertura();
				datos[i][4] = aperturaC.getMontoCierre();
				datos[i][5] = aperturaC.getObservacionesCierre();
				datos[i][6] = aperturaC.getFechaCierre();
				datos[i][7] = aperturaC.getEstado();
				datos[i][8] = aperturaC.getIdUsuario();
				i++;
			}
			
		} catch (NullPointerException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}		
		
		dtm = new DefaultTableModel(datos,columnNames);
		//dtm.setColumnIdentifiers(columnNames);
		TAperturasCaja.setModel(dtm);
		
	}
	
	
}

package Views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.ModelsDAO.AperturaCaja;
import HerramientasConexion.Herramientas;
import Models.Respuesta;
import Services.AperturaCajaService;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import javax.swing.JEditorPane;
import javax.swing.border.LineBorder;

public class JD_Caja extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField TFMonto;
	private int idUsuario = 0;
	private JLabel LblCajaEstatus;
	private JComboBox CBCaja ;
	private JEditorPane EPObservaciones ;
	private Respuesta respuesta = new Respuesta("",true,null);
	private int idCaja = 0;
	
	public class proceso{
		public static final String ABIERTA = "ABRIR CAJA";
		public static final String CERRADA = "CERRAR CAJA";
		
	}
	
	public static void main(String[] args) {
		try {
			JD_Caja dialog = new JD_Caja(0);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * Create the dialog.
	 */
	public JD_Caja(int idUsuario) {
		setTitle("Apertura Y Cierre de Caja");
		setBounds(100, 100, 399, 284);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(254, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(248, 248, 248));
			panel.setBounds(6, 6, 387, 205);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				LblCajaEstatus = new JLabel("New label");
				LblCajaEstatus.setHorizontalAlignment(SwingConstants.CENTER);
				LblCajaEstatus.setBounds(6, 24, 375, 16);
				panel.add(LblCajaEstatus);
			}
			
			JSeparator separator = new JSeparator();
			separator.setBounds(16, 52, 353, 16);
			panel.add(separator);
			
			JLabel lblNewLabel = new JLabel("Monto");
			lblNewLabel.setBounds(26, 120, 92, 16);
			panel.add(lblNewLabel);
			
			TFMonto = new JTextField();
			TFMonto.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent arg0) {
					validarMonto(arg0);
				}
			});
			TFMonto.setBounds(130, 115, 239, 26);
			panel.add(TFMonto);
			TFMonto.setColumns(10);
			
			CBCaja = new JComboBox();
			CBCaja.setModel(new DefaultComboBoxModel(new String[] {"ABRIR CAJA", "CERRAR CAJA"}));
			CBCaja.setBounds(130, 76, 239, 27);
			panel.add(CBCaja);
			
			JLabel lblCaja = new JLabel("Caja");
			lblCaja.setBounds(26, 80, 103, 16);
			panel.add(lblCaja);
			
			JLabel Lblbservacion = new JLabel("Observaciones");
			Lblbservacion.setBounds(26, 158, 92, 16);
			panel.add(Lblbservacion);
			
			EPObservaciones = new JEditorPane();
			EPObservaciones.setBorder(new LineBorder(new Color(192, 192, 192), 1, true));
			EPObservaciones.setBounds(130, 153, 239, 46);
			panel.add(EPObservaciones);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
					
						Grabar();
						
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
	
		this.idUsuario =idUsuario;
		CBCaja.setEnabled(false);
		validarAccion();
	}
	
	public void validarAccion() {
		
		respuesta = new Respuesta("",true,null);		
		AperturaCajaService aperturaCajaService = new AperturaCajaService();
		respuesta =  aperturaCajaService.verificarCajaAbierta();
		
		if (!respuesta.getValor()) {
			JOptionPane.showMessageDialog(this,"Problemas Al intentar obtener el Estatus de la Caja", "Error", JOptionPane.ERROR_MESSAGE);
		}		

		if ( respuesta.getRespuesta() == null ) {
			CBCaja.setSelectedItem(proceso.ABIERTA);;
			LblCajaEstatus.setText("Caja Cerrada");

		}else {
			CBCaja.setSelectedItem(proceso.CERRADA);
			LblCajaEstatus.setText("Caja Abierta");
			AperturaCaja  aperturaCaja = (AperturaCaja) respuesta.getRespuesta();
			idCaja = aperturaCaja.getIdApertura();
		}
		
	}
	
	public void validarMonto(KeyEvent arg0) {
		
		int indicePunto = TFMonto.getText().indexOf('.'); 
		
		try {
			
			if (!Character.isDigit(arg0.getKeyChar()) && arg0.getKeyChar() != '.' && arg0.getKeyChar() != KeyEvent.VK_BACK_SPACE) {	            
	            arg0.consume();
	            return;
			}
			
			if (arg0.getKeyChar()=='.' && TFMonto.getText().contains(String.valueOf( '.')) ) {
				arg0.consume();
				return;
			}
			
			if (indicePunto != -1) {
				String montoText = TFMonto.getText().substring(indicePunto+1);
				if (montoText.length()== 2) {
					arg0.consume();
					return;
				}
			}
			
			if(TFMonto.getText().length()==12)
			{
				arg0.consume();
				return;
			}
			
			
			
		}catch(Exception e) 
		{
			System.out.println("Error al prcesas la solicitud "+e.getMessage());
		}
	}
	
	public void Grabar() {
		
		respuesta = new Respuesta("",true,null);
		AperturaCajaService aperturacajaService = new AperturaCajaService();
		
		respuesta = validar();
		
		if(!respuesta.getValor()) {
			JOptionPane.showMessageDialog(this,respuesta.getMensaje(), "Advertencia", JOptionPane.WARNING_MESSAGE);
			return;
		}
		AperturaCaja aperturaCaja = modelar();
		
		switch (CBCaja.getSelectedItem().toString()) {
		case proceso.ABIERTA:
			respuesta = aperturacajaService.insertar(aperturaCaja);			
			break;
		case proceso.CERRADA:
			respuesta = aperturacajaService.cerrarCaja(aperturaCaja);
			break;
		}
		
		if (!respuesta.getValor()) {
			JOptionPane.showMessageDialog(this, respuesta.getMensaje(),"Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
	
		JOptionPane.showMessageDialog(this, respuesta.getMensaje(),"Información", JOptionPane.INFORMATION_MESSAGE);
		this.dispose();
	}
	
	public AperturaCaja modelar() {
		
		AperturaCaja aperturacaja = new AperturaCaja();
		
		switch (CBCaja.getSelectedItem().toString()) {
		case proceso.ABIERTA:
			aperturacaja.setMontoApertura( new BigDecimal(TFMonto.getText()) );
			aperturacaja.setMontoCierre( new BigDecimal(0) );
			aperturacaja.setObservacionesEntrada(EPObservaciones.getText().trim());
			aperturacaja.setEstado("ABIERTA");
			aperturacaja.setIdUsuario(idUsuario);			
			break;

		case proceso.CERRADA:
			aperturacaja.setIdApertura(idCaja);
			aperturacaja.setMontoCierre( new BigDecimal(TFMonto.getText()) );
			aperturacaja.setEstado("CERRADA"); 		
			aperturacaja.setObservacionesEntrada(EPObservaciones.getText().trim());
			break;
		}
		
		System.out.println("OK :"+EPObservaciones.getText()+aperturacaja.getObservaciones());
		
		return aperturacaja;
	}
	
	
	public Respuesta validar() {

		respuesta = new Respuesta("",true,null);
		if(!Herramientas.validarFlotante(TFMonto.getText())) {
			return new Respuesta ("Validar el Monto",false,null);
		}
		return respuesta;
	}
}

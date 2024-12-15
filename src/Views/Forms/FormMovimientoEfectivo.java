package Views.Forms;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.border.LineBorder;

import Controllers.ControllerMovimientosEfectivo;
import DAO.ModelsDAO.MovimientoEfectivo;
import HerramientasConexion.Herramientas;
import Models.Respuesta;
import Views.JP_EntradaSalidaEfectivo;

import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FormMovimientoEfectivo extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField TFMonto;
	private JComboBox CBTipotransaccion ;
	private JEditorPane EPConcepto ;
	private JEditorPane  EPObservacion;
	private JComboBox CBMetodoPago;
	private JComboBox CBEstadoMovimiento;
	
	Respuesta respuesta = null;
	private int idUsuario = 0;
	private JP_EntradaSalidaEfectivo jp_entradaSalidaEfectivo;
	
	public static void main(String[] args) {
		try {
			FormMovimientoEfectivo dialog = new FormMovimientoEfectivo(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public FormMovimientoEfectivo( JP_EntradaSalidaEfectivo entradaSalidaEfectivo ) {
		setTitle("Registro de Movimiento de Efectivo");
		setBounds(100, 100, 450, 415);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel LblTipoTransaccion = new JLabel("Tipo de transacción");
			LblTipoTransaccion.setBounds(6, 61, 147, 14);
			LblTipoTransaccion.setHorizontalTextPosition(SwingConstants.CENTER);
			LblTipoTransaccion.setHorizontalAlignment(SwingConstants.RIGHT);
			LblTipoTransaccion.setFont(new Font("Dialog", Font.PLAIN, 11));
			contentPanel.add(LblTipoTransaccion);
		}
		{
			TFMonto = new JTextField();
			TFMonto.setBounds(165, 94, 229, 26);
			TFMonto.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent arg0) {
					validarMonto(arg0);
				}
			});
			TFMonto.setColumns(10);
			contentPanel.add(TFMonto);
		}
		{
			JLabel LblMonto = new JLabel("Monto");
			LblMonto.setBounds(6, 101, 147, 14);
			LblMonto.setHorizontalTextPosition(SwingConstants.CENTER);
			LblMonto.setHorizontalAlignment(SwingConstants.RIGHT);
			LblMonto.setFont(new Font("Dialog", Font.PLAIN, 11));
			contentPanel.add(LblMonto);
		}
		
		CBTipotransaccion = new JComboBox();
		CBTipotransaccion.setBounds(165, 55, 229, 27);
		CBTipotransaccion.setModel(new DefaultComboBoxModel(new String[] {"ENTRADA", "SALIDA"}));
		contentPanel.add(CBTipotransaccion);
		
		JLabel LblConcepto = new JLabel("Concepto");
		LblConcepto.setBounds(6, 175, 147, 14);
		LblConcepto.setHorizontalTextPosition(SwingConstants.CENTER);
		LblConcepto.setHorizontalAlignment(SwingConstants.RIGHT);
		LblConcepto.setFont(new Font("Dialog", Font.PLAIN, 11));
		contentPanel.add(LblConcepto);
		
		EPConcepto = new JEditorPane();
		EPConcepto.setBounds(165, 175, 229, 47);
		EPConcepto.setBorder(new LineBorder(new Color(192, 192, 192), 1, true));
		contentPanel.add(EPConcepto);
		
		EPObservacion = new JEditorPane();
		EPObservacion.setBounds(165, 234, 229, 47);
		EPObservacion.setBorder(new LineBorder(new Color(192, 192, 192), 1, true));
		contentPanel.add(EPObservacion);
		
		JLabel LblObservacion = new JLabel("Observaciones");
		LblObservacion.setBounds(6, 239, 147, 14);
		LblObservacion.setHorizontalTextPosition(SwingConstants.CENTER);
		LblObservacion.setHorizontalAlignment(SwingConstants.RIGHT);
		LblObservacion.setFont(new Font("Dialog", Font.PLAIN, 11));
		contentPanel.add(LblObservacion);
		
		JLabel LblMetodoPago = new JLabel("Método de Pago");
		LblMetodoPago.setBounds(6, 144, 147, 14);
		LblMetodoPago.setHorizontalTextPosition(SwingConstants.CENTER);
		LblMetodoPago.setHorizontalAlignment(SwingConstants.RIGHT);
		LblMetodoPago.setFont(new Font("Dialog", Font.PLAIN, 11));
		contentPanel.add(LblMetodoPago);
		
		JLabel LblEstadomovimiento = new JLabel("Método de Pago");
		LblEstadomovimiento.setBounds(6, 303, 147, 14);
		LblEstadomovimiento.setHorizontalTextPosition(SwingConstants.CENTER);
		LblEstadomovimiento.setHorizontalAlignment(SwingConstants.RIGHT);
		LblEstadomovimiento.setFont(new Font("Dialog", Font.PLAIN, 11));
		contentPanel.add(LblEstadomovimiento);
		
		CBMetodoPago = new JComboBox();
		CBMetodoPago.setBounds(165, 138, 229, 27);
		CBMetodoPago.setModel(new DefaultComboBoxModel(new String[] {"EFECTIVO", "TARJETA", "TRANSFERENCIA", "MIXTO", "OTRO"}));
		contentPanel.add(CBMetodoPago);
		
		CBEstadoMovimiento = new JComboBox();
		CBEstadoMovimiento.setBounds(165, 297, 229, 27);
		CBEstadoMovimiento.setModel(new DefaultComboBoxModel(new String[] {"PENDIENTE", "COMPLETADO"}));
		contentPanel.add(CBEstadoMovimiento);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						grabar();
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
		
		//valores
		this.jp_entradaSalidaEfectivo = entradaSalidaEfectivo;
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
	
	public Respuesta validar() {
		
		respuesta = new Respuesta("",true,null);
		
		if (!Herramientas.validarFlotante(TFMonto.getText())) {
			return new Respuesta("Validar el Monto",false,null);
		}
		
		if (EPConcepto.getText().trim().isEmpty() || EPConcepto.getText().trim() == null || EPConcepto.getText().trim() =="") {
			return new Respuesta("Agrega una descripción para el Movimiento",false,null);
		}
		
		return respuesta;
	}
	
	public void grabar() {
		
		respuesta = new Respuesta("",true,null);
		ControllerMovimientosEfectivo controllerMovimientosEfectivo = new ControllerMovimientosEfectivo();
		MovimientoEfectivo movimientoEfectivo = new MovimientoEfectivo();
		
		respuesta = validar();
		
		if (!respuesta.getValor()) {
			JOptionPane.showMessageDialog(this,respuesta.getMensaje(),"Advertencia",JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		movimientoEfectivo = new MovimientoEfectivo(
				CBTipotransaccion.getSelectedItem().toString(),
				new BigDecimal (TFMonto.getText()),
				EPConcepto.getText(),
				null,
				String.valueOf(idUsuario),
				CBMetodoPago.getSelectedItem().toString(),
				EPObservacion.getText(),
				CBEstadoMovimiento.getSelectedItem().toString(),
				"ACTIVO"
				);
		
		respuesta = controllerMovimientosEfectivo.proceso(Herramientas.tipoOperacion.insertar,movimientoEfectivo );
		
		if(!respuesta.getValor()) {
			JOptionPane.showMessageDialog(this, respuesta.getMensaje(),"Error",JOptionPane.ERROR_MESSAGE);
		}
		
		jp_entradaSalidaEfectivo.Buscar();
		dispose();
		JOptionPane.showMessageDialog(this, respuesta.getMensaje(),"Información",JOptionPane.INFORMATION_MESSAGE);
	}
	
	
}

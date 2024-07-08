package Views.Forms;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import javax.swing.JSpinner;

public class FormCliente extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField TFId;
	private JTextField TFNombre;
	private JPasswordField TFApaterno;
	private JPasswordField TFAmaterno;
	private JTextField TFTelefono;
	private JTextField TFCorreo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FormCliente dialog = new FormCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FormCliente() {
		setResizable(false);
		setMaximumSize(new Dimension(450, 540));
		setMinimumSize(new Dimension(450, 440));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel LId = new JLabel("Identificador");
			LId.setHorizontalTextPosition(SwingConstants.CENTER);
			LId.setHorizontalAlignment(SwingConstants.RIGHT);
			LId.setFont(new Font("Segoe UI", Font.PLAIN, 11));
			LId.setBounds(29, 74, 107, 24);
			contentPanel.add(LId);
		}
		{
			TFId = new JTextField();
			TFId.setColumns(10);
			TFId.setBounds(146, 74, 260, 24);
			contentPanel.add(TFId);
		}
		{
			JLabel LNombre = new JLabel("Nombre");
			LNombre.setHorizontalAlignment(SwingConstants.RIGHT);
			LNombre.setFont(new Font("Segoe UI", Font.PLAIN, 11));
			LNombre.setBounds(29, 109, 107, 24);
			contentPanel.add(LNombre);
		}
		{
			TFNombre = new JTextField();
			TFNombre.setColumns(10);
			TFNombre.setBounds(146, 109, 260, 24);
			contentPanel.add(TFNombre);
		}
		{
			JLabel LApaterno = new JLabel("Apellido Paterno");
			LApaterno.setHorizontalAlignment(SwingConstants.RIGHT);
			LApaterno.setFont(new Font("Segoe UI", Font.PLAIN, 11));
			LApaterno.setBounds(29, 142, 107, 24);
			contentPanel.add(LApaterno);
		}
		{
			TFApaterno = new JPasswordField();
			TFApaterno.setBounds(146, 144, 260, 24);
			contentPanel.add(TFApaterno);
		}
		{
			JLabel LAmaterno = new JLabel("Apellido Materno");
			LAmaterno.setHorizontalAlignment(SwingConstants.RIGHT);
			LAmaterno.setFont(new Font("Segoe UI", Font.PLAIN, 11));
			LAmaterno.setBounds(29, 177, 107, 24);
			contentPanel.add(LAmaterno);
		}
		{
			TFAmaterno = new JPasswordField();
			TFAmaterno.setBounds(146, 179, 260, 24);
			contentPanel.add(TFAmaterno);
		}
		{
			JLabel LFecha = new JLabel("Fecha de Nac.");
			LFecha.setHorizontalAlignment(SwingConstants.RIGHT);
			LFecha.setFont(new Font("Segoe UI", Font.PLAIN, 11));
			LFecha.setBounds(29, 210, 107, 24);
			contentPanel.add(LFecha);
		}
		{
			JLabel LTelefono = new JLabel("Teléfono");
			LTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
			LTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 11));
			LTelefono.setBounds(29, 243, 107, 24);
			contentPanel.add(LTelefono);
		}
		{
			TFTelefono = new JTextField();
			TFTelefono.setColumns(10);
			TFTelefono.setBounds(146, 243, 260, 24);
			contentPanel.add(TFTelefono);
		}
		{
			JLabel LCorreo = new JLabel("Correo Electrónico");
			LCorreo.setHorizontalAlignment(SwingConstants.RIGHT);
			LCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 11));
			LCorreo.setBounds(29, 278, 107, 24);
			contentPanel.add(LCorreo);
		}
		{
			TFCorreo = new JTextField();
			TFCorreo.setColumns(10);
			TFCorreo.setBounds(146, 278, 260, 24);
			contentPanel.add(TFCorreo);
		}
		{
			JLabel lblNumCompras = new JLabel("Num. Compras");
			lblNumCompras.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNumCompras.setFont(new Font("Segoe UI", Font.PLAIN, 11));
			lblNumCompras.setBounds(29, 311, 107, 24);
			contentPanel.add(lblNumCompras);
		}
		{
			JLabel L_Formulario = new JLabel("Formulario Clientes");
			L_Formulario.setHorizontalAlignment(SwingConstants.CENTER);
			L_Formulario.setFont(new Font("Segoe UI", Font.PLAIN, 20));
			L_Formulario.setBounds(0, 11, 434, 43);
			contentPanel.add(L_Formulario);
		}
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setName("DCFechaNac");
		dateChooser.setBounds(146, 210, 260, 24);
		contentPanel.add(dateChooser);
		
		JSpinner SCompras = new JSpinner();
		SCompras.setBounds(146, 313, 260, 24);
		contentPanel.add(SCompras);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						guardarRegistro();
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
	}
	
	public void guardarRegistro(){
		
		
		
	}
}

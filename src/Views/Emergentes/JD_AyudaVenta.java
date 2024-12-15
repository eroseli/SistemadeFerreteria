package Views.Emergentes;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JD_AyudaVenta extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JD_AyudaVenta dialog = new JD_AyudaVenta();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JD_AyudaVenta() {
		setTitle("Ayuda Ventana Ventas");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Buscar Producto : Alt + P");
			lblNewLabel.setBounds(31, 17, 339, 16);
			contentPanel.add(lblNewLabel);
		}
		{
			JSeparator separator = new JSeparator();
			separator.setBounds(31, 45, 402, 12);
			contentPanel.add(separator);
		}
		{
			JLabel lblBuscarCliente = new JLabel("Buscar Cliente : Alt + C");
			lblBuscarCliente.setBounds(31, 66, 339, 16);
			contentPanel.add(lblBuscarCliente);
		}
		{
			JSeparator separator = new JSeparator();
			separator.setBounds(31, 94, 402, 12);
			contentPanel.add(separator);
		}
		{
			JLabel lblAgregarProducto = new JLabel("Agregar Producto  + 1 : Alt + Tecla Arriba\n");
			lblAgregarProducto.setVisible(false);
			lblAgregarProducto.setBounds(31, 118, 402, 28);
			contentPanel.add(lblAgregarProducto);
		}
		{
			JSeparator separator = new JSeparator();
			separator.setBounds(31, 187, 402, 12);
			contentPanel.add(separator);
		}
		{
			JLabel lblProducto = new JLabel("Producto  - 1 : Alt + Tecla Abajo\n");
			lblProducto.setVisible(false);
			lblProducto.setBounds(31, 146, 402, 29);
			contentPanel.add(lblProducto);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}

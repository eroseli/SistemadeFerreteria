package Utileria;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;

import com.toedter.calendar.JDateChooser;

import HerramientasConexion.Herramientas;
import Models.Components.JTableEdited;

public class ComponentesDesing {
	
	
	
	public static void textFieldDeshabilitar(JTextField jTextField) {
		
		jTextField.setEditable(false);
		Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1);
		jTextField.setBackground(Color.white);
		jTextField.setForeground(Color.gray);
		jTextField.setBorder(border);
	
	}
	

	public static void textFieldHabilitar() {
		
	}
	
	public static void JSnipperHabilidato() {
		
	}
	
	public static void JSnipperDesHabilitar(JSpinner jSpinner) {
		jSpinner.setEnabled(false);
		jSpinner.setBackground(UIManager.getColor("TextField.background"));	
	}
	
	public static void JDatachoser(JDateChooser chooser) {
		chooser.setEnabled(false);		
	}
	
	public static void JSpinnerDeshabilitado(JSpinner jSpinner) {
		jSpinner.setEnabled(false);
	}
	
	public static void JSpinnerHabilitado() {
		
	}
	
	public static void JComboBoxDeshabilitar(JComboBox combo) {
		combo.setEnabled(false);
	}
	
	public static void JRadioButtonDeshabilitar(JRadioButton jRadioButton) {
		jRadioButton.setVisible(false);
	}
	
	public static void JRadioButtonHabilitar() {
		
	}
	
	public static void JPasswordFieldDeshabilitar(JPasswordField jPasswordField) {
		jPasswordField.setEditable(false);
		jPasswordField.setForeground(Color.gray);
	}
	
	public static void JButtonDesing(JButton button, int tipoBoton) {
		
		button.setBorderPainted(false);
		Font font = new Font("Segoe UI", Font.LAYOUT_LEFT_TO_RIGHT, 12);
		button.setFont(font);
		Color rgbColor;
		switch (tipoBoton) {
		case Herramientas.tipoButton.grabar:
			button.setForeground(Color.DARK_GRAY);
			rgbColor = new Color(234, 234, 234 );
			button.setBackground(rgbColor);
		case Herramientas.tipoButton.cancelar:
			button.setForeground(Color.DARK_GRAY);
			rgbColor = new Color(234, 234, 234 );
			button.setBackground(rgbColor);	
			break;
		case Herramientas.tipoButton.eliminar:
			button.setForeground(Color.DARK_GRAY);
			rgbColor = new Color(234, 128, 143 );
			button.setBackground(rgbColor);
			
			break;
		}
		
	}
	
	public static void PreferredWithTableProductoVenta( JTable table  ) {
		
		table.getColumnModel().getColumn(0).setMinWidth(0);   // Establece el ancho mínimo a 0
        table.getColumnModel().getColumn(0).setMaxWidth(0);   // Establece el ancho máximo a 0
        table.getColumnModel().getColumn(0).setWidth(0);   
        table.getColumnModel().getColumn(1).setPreferredWidth(120);
		
		table.getColumnModel().getColumn(2).setPreferredWidth(220);		
		table.getColumnModel().getColumn(3).setPreferredWidth(220);
		
	    table.getColumnModel().getColumn(4).setMinWidth(80);
		table.getColumnModel().getColumn(4).setMaxWidth(80);   // Establece el ancho máximo a 0
	    table.getColumnModel().getColumn(4).setWidth(80);   
			       
	}
	
	public static void PreferredWithTableTableUsuarios(JTable table) {
		
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setWidth(0);
		
		table.getColumnModel().getColumn(1).setPreferredWidth(180);
		table.getColumnModel().getColumn(2).setPreferredWidth(180);
		table.getColumnModel().getColumn(3).setPreferredWidth(180);
		table.getColumnModel().getColumn(4).setPreferredWidth(180);
		table.getColumnModel().getColumn(5).setPreferredWidth(180);
		table.getColumnModel().getColumn(6).setPreferredWidth(280);
		table.getColumnModel().getColumn(7).setPreferredWidth(180);
		table.getColumnModel().getColumn(8).setPreferredWidth(180);
		table.getColumnModel().getColumn(9).setPreferredWidth(180);
		
	}
	
	public static void PreferredWithTableESMercancia(JTable table) {
		
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setWidth(0);
		
		table.getColumnModel().getColumn(1).setMinWidth(0);
		table.getColumnModel().getColumn(1).setMaxWidth(0);
		table.getColumnModel().getColumn(1).setWidth(0);

		table.getColumnModel().getColumn(2).setMinWidth(0);
		table.getColumnModel().getColumn(2).setMaxWidth(0);
		table.getColumnModel().getColumn(2).setWidth(0);

		
	}
	
	public static void PreferredWithTableEntradaSalidaMercancia( JTable table) {

		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setWidth(0);
		
	}
	
	public static void PreferredWithTableMovimientosEfectivo( JTable table) {

		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setWidth(0);
		
	}
	
	public static void PreferredWithTableCategoria(JTable table) {
	
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setWidth(0);
		
	}
	
}

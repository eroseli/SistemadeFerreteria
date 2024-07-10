package Utileria;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;

import com.toedter.calendar.JDateChooser;

import HerramientasConexion.Herramientas;

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
	
	public static void JRadioButtonDeshabilitar(JRadioButton jRadioButton) {
		jRadioButton.setVisible(false);
	}
	
	public static void JRadioButtonHabilitar() {
		
	}
	
	public static void JPasswordFieldDeshabilitar(JPasswordField jPasswordField) {
		jPasswordField.setEditable(false);
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
	
}

package Views.Forms;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controllers.ControllerCategoria;
import Controllers.ControllerMarca;
import DAO.ModelsDAO.Categoria;
import DAO.ModelsDAO.MarcaDAO;
import HerramientasConexion.Herramientas;
import Models.Respuesta;

import java.awt.Component;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JD_ProductoDetalle extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField TFNombre;
	private JTextField TFDescripcion;
	private JLabel LblNombre ;
	private JLabel LblTituloPantalla;
	private JComboBox CBtipo ;
	private JLabel LblVehiculo ;
	
	private int tipoOperacion = 0;
	private int configuracion = 0;
	private String nombre = "";
	private FormProductos formProductos;
	
	
	public static void main(String[] args) {
		try {
			JD_ProductoDetalle dialog = new JD_ProductoDetalle(null,1,2, "Cadena");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JD_ProductoDetalle(FormProductos formProductos ,int tipoOperacion, int configuracion, String nombre) {
		setBounds(100, 100, 365, 337);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);
			panel.setAlignmentX(Component.LEFT_ALIGNMENT);
			panel.setBounds(0, 0, 365, 270);
			contentPanel.add(panel);
			{
				TFNombre = new JTextField();
				TFNombre.setBounds(74, 89, 230, 26);
				TFNombre.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						Herramientas.tamanioCadena(e, TFNombre, Herramientas.TamCampos.nombres);
					}
				});
				panel.setLayout(null);
				panel.add(TFNombre);
				TFNombre.setColumns(10);
			}
			{
				TFDescripcion = new JTextField();
				TFDescripcion.setBounds(74, 159, 230, 26);
				TFDescripcion.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						Herramientas.tamanioCadena(e, TFDescripcion, Herramientas.TamCampos.descripcionCorta);
					}
				});
				TFDescripcion.setColumns(10);
				panel.add(TFDescripcion);
			}
			{
				LblNombre = new JLabel("Nombre");
				LblNombre.setBounds(74, 61, 137, 16);
				panel.add(LblNombre);
			}
			{
				JLabel lblDescripcion = new JLabel("Descripción");
				lblDescripcion.setBounds(74, 127, 120, 16);
				panel.add(lblDescripcion);
			}
			{
				LblVehiculo = new JLabel("Vehículo");
				LblVehiculo.setBounds(74, 197, 120, 16);
				panel.add(LblVehiculo);
			}
			{
				CBtipo = new JComboBox();
				CBtipo.setBounds(74, 225, 230, 27);
				CBtipo.setModel(new DefaultComboBoxModel(new String[] {"ambos", "auto", "moto"}));
				panel.add(CBtipo);
			}
			{
				LblTituloPantalla = new JLabel("Nombre");
				LblTituloPantalla.setBounds(0, 6, 365, 31);
				LblTituloPantalla.setForeground(Color.DARK_GRAY);
				LblTituloPantalla.setBackground(Color.DARK_GRAY);
				LblTituloPantalla.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
				LblTituloPantalla.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(LblTituloPantalla);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Grabar();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton BCancelar = new JButton("Cancel");
				BCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				BCancelar.setActionCommand("Cancel");
				buttonPane.add(BCancelar);
			}
		}
		
		this.tipoOperacion = tipoOperacion;
		this.configuracion = configuracion;
		this.formProductos = formProductos;
		this.nombre = nombre;
		ConfiguracionPantalla();
	}
	
	public boolean validarCampos() {
		
		
		if (TFNombre.getText().trim().isEmpty() || TFNombre.getText().trim() =="") {
			JOptionPane.showMessageDialog(this,"Es necesario Introducir un Nombre","Advertencia", JOptionPane.WARNING_MESSAGE  );			
			return false;
		}
		
		if (TFDescripcion.getText().trim().isEmpty() || TFDescripcion.getText().trim() =="") {
			JOptionPane.showMessageDialog(this,"Es necesario Introducir una Descripción","Advertencia", JOptionPane.WARNING_MESSAGE  );			
			return false;
		}
		
		return true;
		
	}
	
	public void Grabar() {
		
		Respuesta respuesta = new Respuesta("",false,null);	
		
		if (!validarCampos()) {
			return;
		}
		
		switch(configuracion) {
		case Herramientas.CombosConfiguracion.categoria:
			
			ControllerCategoria controllerCategoria = new ControllerCategoria();		
			Categoria categoria = new Categoria();
			categoria.setNombre(TFNombre.getText().trim());
			categoria.setDescripcion(TFDescripcion.getText());
			categoria.setTipo_vehiculo( (String) CBtipo.getSelectedItem());
			
			switch (tipoOperacion) {
			case Herramientas.tipoOperacion.insertar: 
				respuesta =  controllerCategoria.proceso(tipoOperacion, categoria);
				break;
				
			case Herramientas.tipoOperacion.actualizar:
				break;
			
			}
			
		break;
		case Herramientas.CombosConfiguracion.marca:
						
			ControllerMarca controllerMarca = new ControllerMarca();
			MarcaDAO marca = new MarcaDAO();
			marca.setNombre(TFNombre.getText());
			marca.setDescripcion(TFDescripcion.getText());
			
			switch(tipoOperacion){
			case Herramientas.tipoOperacion.insertar:
				respuesta = controllerMarca.proceso(tipoOperacion, marca);
				break;
			case Herramientas.tipoOperacion.actualizar:
				break;
			}
			
		break;
		
		}
		
		JOptionPane.showMessageDialog(this, respuesta.getMensaje(),"Información", JOptionPane.INFORMATION_MESSAGE  );
		
		if (!respuesta.getValor()) {		
			return;
		}
		
		if (configuracion == Herramientas.CombosConfiguracion.categoria) {			
			formProductos.inicializarComboCategoria();			
		}
		else {
			formProductos.inicializarComboMarca();
		}
		
		dispose();
		
	}
	
	
	public void ConfiguracionPantalla() {
		
		switch(configuracion) {
		case Herramientas.CombosConfiguracion.categoria:
			LblTituloPantalla.setText("Categoría");
		break;
		case Herramientas.CombosConfiguracion.marca:
			System.out.println("Entro a dar alta marca");
			LblTituloPantalla.setText("Marca");
			LblVehiculo.setVisible(false);
			CBtipo.setVisible(false);
		break;
		
		}
		
		TFNombre.setText(nombre);
		
	}
	

}

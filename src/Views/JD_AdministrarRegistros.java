package Views;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import Controllers.ControllerCategoria;
import Controllers.ControllerMarca;
import DAO.ModelsDAO.Categoria;
import DAO.ModelsDAO.MarcaDAO;
import HerramientasConexion.Herramientas;
import Models.Respuesta;
import Models.Components.CustomHeaderRenderer;
import Utileria.ComponentesDesing;

import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JToolBar;
import java.awt.Choice;
import javax.swing.JList;
import java.awt.event.ItemListener;
import java.rmi.server.Operation;
import java.awt.event.ItemEvent;

public class JD_AdministrarRegistros extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField TFNombre;
	private JTextField TFDescripcion;
	private JComboBox CBtipo;
	private JComboBox CBRegistro;
	private JTable TTabla;
	private JLabel LblTituloPantalla;
	private DefaultTableModel dtm;
	
	public static class operacion{
		
		private static final int categoria =1;
		private static final String categoriaS = "Categoría";
		private static final int marca =2;
		private static final String marcaS ="Marca";
	}
	
	String[] columnNamesCategoria = {"ID", "Nombre","Descripción"};
	String[] columnNamesMarca = {"ID", "Nombre","Descripción"};
	
	Object[][] datos = {};
	List<MarcaDAO> marcas = new ArrayList<MarcaDAO>();
	List<Categoria> categorias = new ArrayList<Categoria>();
	Respuesta respuesta = null;
	
	public JD_AdministrarRegistros() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 538, 588);
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setAlignmentX(0.0f);
		add(panel);
		
		TFNombre = new JTextField();
		TFNombre.setColumns(10);
		TFNombre.setBounds(153, 138, 297, 26);
		panel.add(TFNombre);
		
		TFDescripcion = new JTextField();
		TFDescripcion.setColumns(10);
		TFDescripcion.setBounds(152, 176, 298, 26);
		panel.add(TFDescripcion);
		
		JLabel LblNombre = new JLabel("Nombre");
		LblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		LblNombre.setBounds(20, 143, 120, 16);
		panel.add(LblNombre);
		
		JLabel lblDescripcion = new JLabel("Descripción");
		lblDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescripcion.setBounds(20, 181, 120, 16);
		panel.add(lblDescripcion);
		
		JLabel LblVehiculo = new JLabel("Vehículo");
		LblVehiculo.setHorizontalAlignment(SwingConstants.RIGHT);
		LblVehiculo.setBounds(20, 218, 120, 16);
		panel.add(LblVehiculo);
		
		CBtipo = new JComboBox();
		CBtipo.setModel(new DefaultComboBoxModel(new String[] {"auto", "moto", "ambos"}));
		CBtipo.setBounds(153, 214, 297, 27);
		panel.add(CBtipo);
		
		LblTituloPantalla = new JLabel("Nombre");
		LblTituloPantalla.setHorizontalAlignment(SwingConstants.CENTER);
		LblTituloPantalla.setForeground(Color.DARK_GRAY);
		LblTituloPantalla.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		LblTituloPantalla.setBackground(Color.DARK_GRAY);
		LblTituloPantalla.setBounds(6, 48, 526, 31);
		panel.add(LblTituloPantalla);
		
		JButton BGrabar = new JButton("Grabar");
		BGrabar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				agregarCategoria();
			}
		});
		BGrabar.setBounds(333, 253, 117, 29);
		panel.add(BGrabar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 294, 493, 277);
		panel.add(scrollPane);
		
		TTabla = new JTable();
		TTabla.setRowHeight(25);
		TTabla.setDefaultEditor(Object.class, null);
		JTableHeader header = TTabla.getTableHeader();
		header.setDefaultRenderer(new CustomHeaderRenderer(2));
		TTabla.setDefaultRenderer(Object.class, new CustomHeaderRenderer(2));
		
		scrollPane.setViewportView(TTabla);
		
		JButton BEliminar = new JButton("Eliminar");
		BEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eliminar();
			}
		});
		BEliminar.setBounds(198, 253, 117, 29);
		panel.add(BEliminar);
		
		JLabel lblRegistro = new JLabel("Registro");
		lblRegistro.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRegistro.setBounds(20, 99, 120, 16);
		panel.add(lblRegistro);
		
		CBRegistro = new JComboBox();
		CBRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(CBRegistro.getSelectedItem());
				cargar();
			}
		});
		
		CBRegistro.setModel(new DefaultComboBoxModel(new String[] {"Categoría", "Marca"}));
		CBRegistro.setBounds(153, 99, 297, 27);
		panel.add(CBRegistro);
		
		obtenerCategorias();
		inicializarCategoria();
	
	}
	
	public void eliminar() {
		switch ((String)CBRegistro.getSelectedItem()) {
		case operacion.categoriaS:	
			eliminarCategoria();
			break;
		case operacion.marcaS:
			eliminarMarca();
			break;
		}
	}
	
	public void eliminarMarca() {
		
		ControllerMarca controllerMarca = new ControllerMarca();
		respuesta = new Respuesta("",true,null);
		
		try {
			
			int indice = TTabla.getSelectedRow();
			
			if (indice == -1) {
				JOptionPane.showMessageDialog(this,"Seleccione una Fila para Eliminar");
				return;
			}
			
			int idRegistro = (Integer) TTabla.getValueAt(indice, 0);
			
			respuesta = controllerMarca.proceso(Herramientas.tipoOperacion.eliminar, idRegistro);
			
			if (!respuesta.getValor()) {
				JOptionPane.showMessageDialog(this, respuesta.getMensaje(),"Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			JOptionPane.showMessageDialog(this, respuesta.getMensaje(),"Información",JOptionPane.INFORMATION_MESSAGE);
			
			limpiarPantalla();
			inicializarMarca();
			
		} catch (Exception e) {
			System.out.println("Error al intentar Eliminar : "+e.getMessage());
		}
		
	}
	
	public void eliminarCategoria() {
		
		ControllerCategoria controllerCategoria = new ControllerCategoria();
		respuesta = new Respuesta("",true,null);
		
		try {
			
			int indice = TTabla.getSelectedRow();
			
			if (indice ==-1) {
				JOptionPane.showMessageDialog(this, "Seleccione una Fila para Eliminar");
				return;
			}
			
			int idRegistro = (Integer) TTabla.getValueAt(indice, 0);
			
			respuesta = controllerCategoria.proceso(Herramientas.tipoOperacion.eliminar, idRegistro);
			if (!respuesta.getValor()) {
				JOptionPane.showMessageDialog(this, respuesta.getMensaje(),"Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			JOptionPane.showMessageDialog(this, respuesta.getMensaje(),"Información",JOptionPane.INFORMATION_MESSAGE);
			
			limpiarPantalla();
			inicializarCategoria();
		} catch (Exception e) {
			System.out.println("Error al intentar Eliminar : "+e.getMessage());
		}
		
	}
	
	public void cargar() {
		switch ((String)CBRegistro.getSelectedItem()) {
		case operacion.categoriaS:	
			obtenerCategorias();
			inicializarCategoria();
			break;

		case operacion.marcaS:
			obtenerMarcas();
			inicializarMarca();
			break;
		}	
	}
	
	public void grabar() {
		
		switch ((String)CBRegistro.getSelectedItem()) {
		case operacion.categoriaS:	
			agregarCategoria();
			break;

		case operacion.marcaS:
			break;
		}	
	}
	
	public void cambioRegistro() {
			
		switch ((String)CBRegistro.getSelectedItem()) {
		case operacion.categoriaS:
			LblTituloPantalla.setText(operacion.categoriaS);
			break;

		case operacion.marcaS:
			LblTituloPantalla.setText(operacion.marcaS);
			break;
		}
		
	}
	
	public void obtenerMarcas() {
		
		respuesta = new Respuesta("",true,null);
		ControllerMarca controllerMarca = new ControllerMarca();
		
		respuesta = controllerMarca.proceso(Herramientas.tipoOperacion.seleccionar, null);
		
		if (!respuesta.getValor()) {
			JOptionPane.showMessageDialog(this, respuesta.getMensaje(),"Error",JOptionPane.ERROR_MESSAGE);
		}
		
		marcas = (List<MarcaDAO>) respuesta.getRespuesta();
		// pintar
		pintarTablaMarca();
		
	}
	
	public void obtenerCategorias() {
		
		respuesta = new Respuesta("",true,null);
		ControllerCategoria ControllerCategoria = new  ControllerCategoria();
		
		respuesta = ControllerCategoria.proceso(Herramientas.tipoOperacion.seleccionar, null);
		
		if (!respuesta.getValor()) {
			JOptionPane.showMessageDialog(this,respuesta.getMensaje(),"Advertencia",JOptionPane.ERROR_MESSAGE);
		}
		
		categorias = (List<Categoria>) respuesta.getRespuesta();
		
		pintarTablaCategoria();
		
	}
	
	public Respuesta validar() {
		
		respuesta  = new Respuesta("",true,null);
		
		if(TFNombre.getText().trim().isEmpty() || TFNombre.getText().trim() == "") {
			return new  Respuesta("Introduce un Nombre",false,null);
		}
		
		if(TFDescripcion.getText().trim().isEmpty() || TFDescripcion.getText().trim() == "") {
			return new Respuesta("Introduce una Descripción",false,null);
		}
		
		return respuesta;
		
	}
	
	public void agregarMarca() {
		
		Respuesta respuesta = validar();
		ControllerMarca controllerMarca = new ControllerMarca();
		MarcaDAO marca = new MarcaDAO();
		
		try {
			
			marca.setNombre(TFNombre.getText());
			marca.setDescripcion(TFDescripcion.getText());
			
			respuesta = controllerMarca.proceso(Herramientas.tipoOperacion.insertar, marca);
			
			if (!respuesta.getValor()) {
				JOptionPane.showMessageDialog(this, respuesta.getMensaje(),"Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			JOptionPane.showMessageDialog(this, respuesta.getMensaje(),"Información",JOptionPane.INFORMATION_MESSAGE);
			limpiarPantalla();
			inicializarMarca();
			
		} catch (Exception e) {
			System.out.println("Error al agregar Marca : "+e.getMessage());
		}
	}
	
	public void agregarCategoria() {
		
		Respuesta respuesta = validar();
		ControllerCategoria controllerCategoria = new ControllerCategoria();
		Categoria categoria = new Categoria();
		
		
		try {
			
			categoria.setNombre(TFNombre.getText().trim());
			categoria.setDescripcion(TFDescripcion.getText().trim());
			categoria.setTipo_vehiculo((String) CBtipo.getSelectedItem());
			
			respuesta = controllerCategoria.proceso(Herramientas.tipoOperacion.insertar, categoria);
			
			if (!respuesta.getValor()) {
				JOptionPane.showMessageDialog(this,respuesta.getMensaje(),"Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			JOptionPane.showMessageDialog(this, respuesta.getMensaje(),"Informacion",JOptionPane.INFORMATION_MESSAGE);			
			limpiarPantalla();
			inicializarCategoria();
			
		}catch(Exception e) {
			System.out.println("Error al agregar Categoria : "+e.getMessage());
		}
		
	}
	
	public void limpiarPantalla() {
		TFNombre.setText("");
		TFDescripcion.setText("");
	}
	
	public void inicializarMarca() {
		obtenerMarcas();
		pintarTablaMarca();
		CBtipo.setVisible(false);
	}
	
	public void inicializarCategoria() {
		obtenerCategorias();
		pintarTablaCategoria();
	}
	
	public void pintarTablaMarca() {
		Object[][] datos = new Object[marcas != null?marcas.size():0][4];
		int i =0;
		
		try {			
			for (MarcaDAO marca: marcas) {
				datos[i][0] = marca.getIdMarca();
				datos[i][1] = marca.getNombre();
				datos[i][2] = marca.getDescripcion();
				i++;
			}			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		dtm = new DefaultTableModel(datos,columnNamesMarca);
		TTabla.setModel(dtm);
		ComponentesDesing.PreferredWithTableCategoria(TTabla);	
	}
	
	public void pintarTablaCategoria() {
		
		Object[][] datos = new Object[categorias != null?categorias.size():0][4];
		int i =0;
		
		try {
			
			for (Categoria categoria: categorias) {
				datos[i][0] = categoria.getId_cateogria();
				datos[i][1] = categoria.getNombre();
				datos[i][2] = categoria.getDescripcion();
				i++;
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		dtm = new DefaultTableModel(datos,columnNamesCategoria);
		TTabla.setModel(dtm);
		ComponentesDesing.PreferredWithTableCategoria(TTabla);	
	}
}

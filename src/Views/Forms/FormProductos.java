package Views.Forms;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import java.awt.Cursor;

import com.toedter.calendar.JDateChooser;

import Controllers.ControllerCategoria;
import Controllers.ControllerMarca;
import Controllers.ControllerProducto;
import DAO.ModelsDAO.Categoria;
import DAO.ModelsDAO.MarcaDAO;
import DAO.ModelsDAO.Producto;
import HerramientasConexion.Herramientas;
import Models.ProductoView;
import Models.Respuesta;
import Utileria.ComboItem;
import Utileria.ComponentesDesing;
import Views.JP_Productos;

import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.Format;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JScrollBar;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JCheckBox;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JSeparator;

public class FormProductos extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField TFId;
	private JTextField TFCodigo;
	private JTextField TFCantidad;
	private JTextField TFDescripcion;
	private JTextField TFPPublico;
	private JTextField TFPAdquisicion;
	private JTextField TFPMayoreo;
	private JTextField TFNombre;
	private JDateChooser DCFechaCaducidad;
	private JSpinner SExistencia;
	private JButton B_Eliminar; 
	private JButton B_Grabar;
	private JComboBox CBCategoria;
	private JComboBox CBMarca;
	
	//Locales
	private int tipoOperacion =0;
	private Producto producto = null;
	private ControllerProducto controllerProducto;
	private Respuesta respuesta;
	
	//controlador
	ControllerCategoria controllerCategoria = new ControllerCategoria();
	ArrayList<Categoria> categorias = new ArrayList<Categoria>();
	Categoria categoria = new Categoria();
	MarcaDAO marca = new MarcaDAO();
	
	ControllerMarca controllerMarca = new ControllerMarca();
	ArrayList<MarcaDAO> marcas = new ArrayList<MarcaDAO>();
	MarcaDAO marcaDao= new  MarcaDAO();
	JP_Productos jp_Productos = null;
	
	public static void main(String[] args) {
		try {
			
			Producto producto = new Producto();
			
			producto.setId_producto(1);
			producto.setCodigo("1QWER32");
			producto.setNombre("Martillo");
			producto.setDescripcion("MARTILLO DE ACERO INOXIDABLE");
			producto.setCantidad("MARTILLO DE ACERO INOXIDABLE");
			producto.setFecha_caducidad(Date.valueOf(LocalDate.now()));
			producto.setP_publico(100f);
			producto.setP_Mayoreo(80f);
			producto.setP_Adquisicion(60f);
			producto.setExistencia(10);
			producto.setCategoria("6");
			producto.setMarca("1");
			
			FormProductos dialog = new FormProductos(null,Herramientas.tipoOperacion.actualizar,producto);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FormProductos(JP_Productos jp_Productos, int tipoOperacion, Producto producto) {
		setTitle("Productos");
		setBounds(100, 100, 480, 562);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel L_Formulario = new JLabel("Gestión de Producto");
			L_Formulario.setBounds(10, 0, 434, 34);
			L_Formulario.setFont(new Font("Segoe UI", Font.PLAIN, 20));
			L_Formulario.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(L_Formulario);
		}
		
		TFId = new JTextField();
		TFId.setBounds(127, 64, 300, 24);
		contentPanel.add(TFId);
		TFId.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Id");
		lblNewLabel.setBounds(10, 64, 107, 24);
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPanel.add(lblNewLabel);
		{
			JLabel lblCdigo = new JLabel("Código");
			lblCdigo.setBounds(10, 99, 107, 24);
			lblCdigo.setFont(new Font("Segoe UI", Font.PLAIN, 11));
			lblCdigo.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblCdigo);
		}
		{
			TFCodigo = new JTextField();
			TFCodigo.setBounds(127, 98, 300, 24);
			TFCodigo.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent arg0) {
					Herramientas.tamanioCadena(arg0, TFCodigo, 50);
				}
			});
			TFCodigo.setColumns(10);
			contentPanel.add(TFCodigo);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Cantidad(Decrp.)");
			lblNewLabel_1.setBounds(10, 200, 107, 24);
			lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblNewLabel_1);
		}
		{
			TFCantidad = new JTextField();
			TFCantidad.setBounds(127, 200, 300, 24);
			TFCantidad.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					Herramientas.tamanioCadena(e, TFCantidad, 100);
				}
			});
			TFCantidad.setColumns(10);
			contentPanel.add(TFCantidad);
		}
		{
			JLabel lblDescripcin = new JLabel("Descripción");
			lblDescripcin.setBounds(10, 167, 107, 24);
			lblDescripcin.setFont(new Font("Segoe UI", Font.PLAIN, 11));
			lblDescripcin.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblDescripcin);
		}
		{
			TFDescripcion = new JTextField();
			TFDescripcion.setBounds(127, 167, 300, 24);
			TFDescripcion.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					Herramientas.tamanioCadena(e, TFDescripcion, 100);
				}
			});
			TFDescripcion.setColumns(10);
			contentPanel.add(TFDescripcion);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Precio Público");
			lblNewLabel_1.setBounds(10, 274, 107, 24);
			lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblNewLabel_1);
		}
		{
			TFPPublico = new JTextField();
			TFPPublico.setBounds(127, 274, 300, 24);
			TFPPublico.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent arg0) {
					Herramientas.validarMonto(arg0, TFPPublico);
				}
			});
			TFPPublico.setColumns(10);
			contentPanel.add(TFPPublico);
		}
		{
			JLabel lblFechaCaducidad = new JLabel("Fecha Caducidad");
			lblFechaCaducidad.setBounds(10, 448, 107, 24);
			lblFechaCaducidad.setFont(new Font("Segoe UI", Font.PLAIN, 11));
			lblFechaCaducidad.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblFechaCaducidad);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Precio Adquisición");
			lblNewLabel_1.setBounds(10, 342, 107, 24);
			lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblNewLabel_1);
		}
		{
			TFPAdquisicion = new JTextField();
			TFPAdquisicion.setBounds(127, 342, 300, 24);
			TFPAdquisicion.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent arg0) {
					Herramientas.validarMonto(arg0, TFPAdquisicion);
				}
			});
			TFPAdquisicion.setColumns(10);
			contentPanel.add(TFPAdquisicion);
		}
		{
			JLabel lblPrecioMayoreo = new JLabel("Precio Mayoreo");
			lblPrecioMayoreo.setBounds(10, 309, 107, 24);
			lblPrecioMayoreo.setFont(new Font("Segoe UI", Font.PLAIN, 11));
			lblPrecioMayoreo.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblPrecioMayoreo);
		}
		{
			TFPMayoreo = new JTextField();
			TFPMayoreo.setBounds(127, 309, 300, 24);
			TFPMayoreo.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent arg0) {
					Herramientas.validarMonto(arg0, TFPMayoreo);
				}
			});
			TFPMayoreo.setColumns(10);
			contentPanel.add(TFPMayoreo);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Categoria");
			lblNewLabel_1.setBounds(10, 410, 107, 24);
			lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblExistencia = new JLabel("Existencia");
			lblExistencia.setBounds(10, 377, 107, 24);
			lblExistencia.setFont(new Font("Segoe UI", Font.PLAIN, 11));
			lblExistencia.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblExistencia);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Nombre");
			lblNewLabel_1.setBounds(10, 131, 107, 24);
			lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblNewLabel_1);
		}
		{
			TFNombre = new JTextField();
			TFNombre.setBounds(127, 131, 300, 24);
			TFNombre.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					Herramientas.tamanioCadena(e,TFNombre,50);
				}
			});
			TFNombre.setColumns(10);
			contentPanel.add(TFNombre);
		}
		{
			JLabel lblMarca = new JLabel("Marca");
			lblMarca.setBounds(10, 238, 107, 24);
			lblMarca.setFont(new Font("Segoe UI", Font.PLAIN, 11));
			lblMarca.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblMarca);
		}
		
		DCFechaCaducidad = new JDateChooser();
		DCFechaCaducidad.setBounds(127, 446, 300, 24);
		DCFechaCaducidad.setBackground(new Color(255, 255, 255));
		contentPanel.add(DCFechaCaducidad);
		
		SExistencia = new JSpinner();
		SExistencia.setBounds(127, 375, 300, 24);
		SpinnerNumberModel model = new SpinnerNumberModel(
	            0,        // Valor inicial
	            0,        // Valor mínimo
	            100000,   // Valor máximo
	            1         // Incremento
	        );
		SExistencia.setModel(model);
		contentPanel.add(SExistencia);
		
		CBCategoria = new JComboBox();
		CBCategoria.setBounds(127, 409, 300, 27);
		CBCategoria.setEditable(true);
		AutoCompleteDecorator.decorate(CBCategoria);
		contentPanel.add(CBCategoria);
		
        boolean[] isModifying = {false};
		
		CBMarca = new JComboBox();
		CBMarca.setBounds(127, 236, 300, 27);
		CBMarca.setEditable(true);
		contentPanel.add(CBMarca);
		AutoCompleteDecorator.decorate(CBMarca);	
		{
			JSeparator separator = new JSeparator();
			separator.setBounds(20, 46, 418, 12);
			contentPanel.add(separator);
		}
		JTextField TFCBMarca = (JTextField) CBMarca.getEditor().getEditorComponent();
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 255, 255));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				B_Grabar = new JButton("Grabar");
				B_Grabar.setMnemonic(KeyEvent.VK_ENTER);
				B_Grabar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						grabarRegistro();
					}
				});
				{
					B_Eliminar = new JButton("Eliminar");
					B_Eliminar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
						}
					});
					B_Eliminar.setForeground(new Color(255, 0, 0));
					B_Eliminar.setActionCommand("OK");
					buttonPane.add(B_Eliminar);
				}
				B_Grabar.setActionCommand("OK");
				buttonPane.add(B_Grabar);
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
		
		TFDescripcion.addFocusListener(new FocusAdapter() {
		    public void focusGained(FocusEvent e) {
		        TFDescripcion.selectAll();  // Cuando el campo recibe foco, selecciona todo el texto
		    }
		});
		
		//locales
		this.tipoOperacion = tipoOperacion;
		this.producto = producto;
		controllerProducto = new ControllerProducto();
		configuracionPantalla();
		inicializarPantalla();
		this.jp_Productos =jp_Productos;
		
	}	

	public void inicializarComboCategoria() {
		//cargar combo categorias
				respuesta =  controllerCategoria.proceso(Herramientas.tipoOperacion.seleccionar, null);
				
				if (!respuesta.getValor()) 
				{
					JOptionPane.showMessageDialog(this, "Problemas al obtener las categorías : "+ respuesta.getMensaje(), "Advertencia", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				categorias = (ArrayList<Categoria>) respuesta.getRespuesta();
				
				for (Categoria c : categorias) {
					CBCategoria.addItem( c.getNombre());
				}
	}
	
	public void inicializarComboMarca() {
		
		respuesta = null;
		respuesta = controllerMarca.proceso(Herramientas.tipoOperacion.seleccionar, null);
		
		if (!respuesta.getValor()) 
		{
			JOptionPane.showMessageDialog(this, "Problemas al obtener las Marcas : "+ respuesta.getMensaje(), "Advertencia", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		marcas = (ArrayList<MarcaDAO>) respuesta.getRespuesta();
		
		for (MarcaDAO m : marcas) {
			CBMarca.addItem( m.getNombre());
		}
		
	}
	
	public void inicializarPantalla() {
		
		
		inicializarComboCategoria();
		inicializarComboMarca();
		
		if(producto != null && tipoOperacion!=Herramientas.tipoOperacion.insertar) {
			
//			for (int i = 0; i < CBCategoria.getItemCount(); i++) {
//			    ComboItem item = (ComboItem) CBCategoria.getItemAt(i);
//			    if (item.getKey().equals(producto.getCategoria())) {
//			    	CBCategoria.setSelectedIndex(i);  // Seleccionar el índice correspondiente
//			    	System.out.println("Entro" +producto.getCategoria() );
//			        break;  // Salir del bucle una vez que se encuentra el item
//			    }
//			}
//			
//			
//			
//			for (int i = 0; i < CBMarca.getItemCount(); i++) {
//			    ComboItem item = (ComboItem) CBMarca.getItemAt(i);
//			    if (item.getKey().equals(producto.getMarca())) {
//			    	CBMarca.setSelectedIndex(i);  // Seleccionar el índice correspondiente
//			        break;  // Salir del bucle una vez que se encuentra el item
//			    }
//			}
			try {
				
				CBCategoria.setSelectedItem(producto.getCategoria());
				CBMarca.setSelectedItem(producto.getMarca());
				TFId.setText(producto.getId_producto()+"");
				TFNombre.setText(producto.getNombre());
				TFCodigo.setText(producto.getCodigo());
				TFDescripcion.setText(producto.getDescripcion());
				TFCantidad.setText(producto.getCantidad());
				if ( producto.getFecha_caducidad() != null ) {
					DCFechaCaducidad.setDate(new java.util.Date(producto.getFecha_caducidad().getTime()));					
				}
				TFPPublico.setText(producto.getP_publico()+"");
				TFPMayoreo.setText(producto.getP_Mayoreo()+"");
				TFPAdquisicion.setText(producto.getP_Adquisicion()+"");
				SExistencia.setValue((Object) producto.getExistencia());
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Error al presentar la Información "+e.getMessage());
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
			
			
		}
	}
	
	public void configuracionPantalla() {
		
		ComponentesDesing.textFieldDeshabilitar(TFId);
		
		if (tipoOperacion == Herramientas.tipoOperacion.insertar) {
			B_Grabar.setText("Agregar");
			B_Eliminar.setVisible(false);
		}else if(tipoOperacion == Herramientas.tipoOperacion.actualizar) {
			B_Grabar.setText("Actualizar");
		}else if(tipoOperacion == Herramientas.tipoOperacion.eliminar ) {
			configuracionCompartida();
		}else if(tipoOperacion == Herramientas.tipoOperacion.seleccionar) {
			configuracionCompartida();
			B_Eliminar.setVisible(false);
			TFNombre.setFocusable(true);
		}
		
	}
	
	public void configuracionCompartida() {
		
		B_Grabar.setVisible(false);
		ComponentesDesing.textFieldDeshabilitar(TFNombre);
		ComponentesDesing.textFieldDeshabilitar(TFCodigo);
		ComponentesDesing.textFieldDeshabilitar(TFDescripcion);
		TFDescripcion.setFocusable(true);  // Permite que el campo reciba foco y el cursor se mueva
		ComponentesDesing.textFieldDeshabilitar(TFCantidad);
		ComponentesDesing.JDatachoser(DCFechaCaducidad);
		ComponentesDesing.textFieldDeshabilitar(TFPPublico);
		ComponentesDesing.textFieldDeshabilitar(TFPMayoreo);
		ComponentesDesing.textFieldDeshabilitar(TFPAdquisicion);
		ComponentesDesing.JSnipperDesHabilitar(SExistencia);
		ComponentesDesing.JComboBoxDeshabilitar(CBCategoria);
		ComponentesDesing.JComboBoxDeshabilitar(CBMarca);
		CBCategoria.setEditable(false);
		
	}
	
	public void grabarRegistro() {
		
		ProductoView productoView = new ProductoView();		
		respuesta = new Respuesta("",true,null);
		
		String categoria = Herramientas.ComboAutocompletadoCadena(CBCategoria);
				
		if ( categoria == null || categoria=="" ){
						
			if ((JOptionPane.showConfirmDialog(this, "La Categoría No existe. ¿Deseas Agregarla?","Confirmar",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)	)			
			{
				JD_ProductoDetalle  detalle =  new JD_ProductoDetalle(this,Herramientas.tipoOperacion.insertar,
							Herramientas.CombosConfiguracion.categoria,Herramientas.obtenerCadenaComboAutocompletado(CBCategoria,Herramientas.TamCampos.nombres));
				detalle.setVisible(true);
			}
			return;
		}
				
		String marca = Herramientas.ComboAutocompletadoCadena(CBMarca);
		
		if ( marca == null || marca =="" ){
			
			if ((JOptionPane.showConfirmDialog(this, "La Marca No existe. ¿Deseas Agregarla?","Confirmar",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)) {
				System.out.println("Agregar marca");
				JD_ProductoDetalle  detalle =  new JD_ProductoDetalle(this,Herramientas.tipoOperacion.insertar,
							Herramientas.CombosConfiguracion.marca,Herramientas.obtenerCadenaComboAutocompletado(CBMarca,Herramientas.TamCampos.nombres));
				detalle.setVisible(true);
			}
			return;		
		}
				
		
		try {
			Date fecha = Herramientas.convertirFecha( DCFechaCaducidad );
			if (fecha == null) {
				productoView.setFecha_caducidad(null);
			}else {
				productoView.setFecha_caducidad( Herramientas.convertirFecha(DCFechaCaducidad));	
			}
			
		} catch (Exception e) {
			System.out.println("Error al interpretar la fecha");
		}
		
		productoView.setId_producto(TFId.getText());
		productoView.setCodigo(TFCodigo.getText());
		productoView.setNombre(TFNombre.getText());
		productoView.setDescripcion(TFDescripcion.getText());
		productoView.setCantidad(TFCantidad.getText());
		
		
		productoView.setP_publico(TFPPublico.getText());
		productoView.setP_Mayoreo(TFPMayoreo.getText());
		productoView.setP_Adquisicion(TFPAdquisicion.getText());
		productoView.setExistencia(""+SExistencia.getValue());
		
		this.categoria = categorias.stream()
				.filter(c-> c.getNombre().equals(CBCategoria.getSelectedItem()))
				.findFirst()
				.orElse(null);
		
		this.marca = marcas.stream()
				.filter(m -> m.getNombre().equals(CBMarca.getSelectedItem()))
				.findFirst()
				.orElse(null);
		
		productoView.setCategoria(this.categoria.getId_cateogria()+"");
		productoView.setMarca(this.marca.getIdMarca()+"");
		
		Cursor cursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
        setCursor(cursor);		
		respuesta = controllerProducto.proceso(tipoOperacion, productoView);
		cursor = Cursor.getDefaultCursor();
		setCursor(cursor);
		
		if (!respuesta.getValor()) {
			JOptionPane.showMessageDialog(this,respuesta.getMensaje(),"Advertencia",JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		JOptionPane.showMessageDialog(this, respuesta.getMensaje());
		this.dispose();
		jp_Productos.iniciarPantalla();
		
	}
}

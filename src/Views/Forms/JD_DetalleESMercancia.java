package Views.Forms;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import Controllers.ControllerMercancia;
import Controllers.ControllerProveedor;
import DAO.ModelsDAO.Cliente;
import DAO.ModelsDAO.MercanciaDAO;
import DAO.ModelsDAO.MercanciaDetDAO;
import DAO.ModelsDAO.Producto;
import DAO.ModelsDAO.Proveedor;
import DAO.ModelsDAO.Usuario;
import HerramientasConexion.Herramientas;
import Models.MercanciaDetView;
import Models.Respuesta;
import Models.Components.CustomHeaderRenderer;
import Models.Components.JTableEdited;
import Services.MercanciaService;
import Services.ProveedorService;
import Utileria.ComponentesDesing;
import Views.JP_EntradaSalidaMercancia;
import Views.Catalogos.JD_Productos;
import Views.Catalogos.JD_Proveedores;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDesktopPane;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JSpinner;
import java.awt.Button;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SpinnerModel;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JEditorPane;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.JTextArea;

public class JD_DetalleESMercancia extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable TMercanciaDet;
 	private DefaultTableModel dtm;
	public JTextField TFProveedor;
	private JTextField TFProductoCodigo;
	private JTextField TFPrecioUnitario;
	private JTextField TFPrecioLote;
	private JTextField TFProducto;
	private JSpinner SCantidadProducto ;
	private JSpinner STotalProductos ;
	private JEditorPane EPDescripcion;
	private JComboBox CBEntradaSalida;
	private JButton BBuscarProveedor ;
	private JButton BBuscarProducto ;
	private JButton BEliminar ;
	private JButton BGrabar;
	private JButton BGrabarEntradaSalida;
	
	String[] columnNames = {"Id_HistorialCompraDet", "Id_HistorialCompra", "Id_Producto"
			,"Código","Nombre","Descripción","Cantidad", "PrecioUnitario", "PrecioTotal", "Estatus"};
	
	//variables
	Respuesta respuesta=null;
	MercanciaDAO mercanciaDAO = null;
	int tipoOperacion = 0;
	JP_EntradaSalidaMercancia entradaSalidaMercancia =null;
	ArrayList<MercanciaDetView> mercanciasDet = new ArrayList<MercanciaDetView>();
	MercanciaDetView mercanciaDetView = new MercanciaDetView();
	public Cliente cliente = null;
	public Usuario usuario = null;
	public Producto producto = null;
	public Proveedor proveedor = null;

	private DAO.ProductosDAO productosDAO = new DAO.ProductosDAO();
	private JTextField TFTotalCompra;
	private JButton BCancelar;
	
	

	
	public static void main(String[] args) {
		try {
			JD_DetalleESMercancia dialog = new JD_DetalleESMercancia(null,1,null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public JD_DetalleESMercancia(JP_EntradaSalidaMercancia entradaSalidaMercancia,int tipoOpercacion, MercanciaDAO mercanciaDAO) {
		setBackground(new Color(45, 45, 45));
		getContentPane().setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 830, 755);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(45, 45, 45));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JScrollPane SP_MercanciaDet = new JScrollPane();
			SP_MercanciaDet.setBounds(6, 6, 818, 340);
			contentPanel.add(SP_MercanciaDet);
			{
				TMercanciaDet = new JTableEdited();
				TMercanciaDet.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						seleccionarElementoTabla();
					}
				});
				SP_MercanciaDet.setViewportView(TMercanciaDet);
				TMercanciaDet.setBorder(null);
				TMercanciaDet.setRowHeight(20);
				TMercanciaDet.setDefaultEditor(Object.class, null);
				dtm = new DefaultTableModel(null,columnNames);		
				TMercanciaDet.setModel(dtm);
				
				//tabla perzonalizada
				TMercanciaDet.setBackground(new Color(254, 255, 255));
				TMercanciaDet.setDefaultRenderer(Object.class, new CustomHeaderRenderer(2));
				ComponentesDesing.PreferredWithTableESMercancia(TMercanciaDet);

			}
		}
		
		SpinnerNumberModel modelSpinner = new SpinnerNumberModel(0,0,10000,1);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(254, 255, 255));
		panel.setBorder(UIManager.getBorder("Button.border"));
		panel.setBounds(6, 349, 406, 333);
		contentPanel.add(panel);
		panel.setLayout(null);
		{
			
			
			TFProveedor = new JTextField();
			TFProveedor.setEditable(false);
			TFProveedor.setBounds(40, 32, 233, 26);
			panel.add(TFProveedor);
			TFProveedor.setColumns(10);
			
		}
		{
			JLabel LBlProveedor = new JLabel("Tipo de Operación");
			LBlProveedor.setBounds(40, 68, 309, 16);
			panel.add(LBlProveedor);
		}
		{
			JLabel LBlProveedor = new JLabel("Proveedor");
			LBlProveedor.setBounds(40, 16, 309, 16);
			panel.add(LBlProveedor);
		}
		{
			CBEntradaSalida = new JComboBox();
			CBEntradaSalida.setBounds(40, 86, 309, 27);
			panel.add(CBEntradaSalida);
			CBEntradaSalida.setModel(new DefaultComboBoxModel(new String[] {"Entrada", "Salida"}));
		}
		{
			JLabel LBlTotalProducto = new JLabel("Total de Productos");
			LBlTotalProducto.setBounds(40, 125, 309, 16);
			panel.add(LBlTotalProducto);
		}
		
		JLabel LBlTotalCompra = new JLabel("Total de la Compra");
		LBlTotalCompra.setBounds(40, 181, 309, 16);
		panel.add(LBlTotalCompra);
		
		TFTotalCompra = new JTextField();
		TFTotalCompra.setEditable(false);
		TFTotalCompra.setColumns(10);
		TFTotalCompra.setBounds(40, 200, 309, 26);
		panel.add(TFTotalCompra);
		{
			JLabel LBlDescripcion = new JLabel("Descripción");
			LBlDescripcion.setBounds(40, 236, 309, 16);
			panel.add(LBlDescripcion);
		}
		
		EPDescripcion = new JEditorPane();
		EPDescripcion.setBorder(new LineBorder(new Color(192, 192, 192), 1, true));
		EPDescripcion.setBounds(40, 254, 309, 61);
		panel.add(EPDescripcion);
		{
			BBuscarProveedor = new JButton("Buscar");
			BBuscarProveedor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buscarProveedor();
				}
			});
			BBuscarProveedor.setBounds(274, 32, 75, 29);
			panel.add(BBuscarProveedor);
		}
		{
			STotalProductos = new JSpinner();
			STotalProductos.setBounds(40, 153, 309, 26);
			panel.add(STotalProductos);
		}
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(254, 255, 255));
		panel_1.setBounds(418, 349, 406, 333);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblProducto = new JLabel("Producto");
		lblProducto.setBounds(34, 30, 260, 16);
		panel_1.add(lblProducto);
		
		{
			TFProductoCodigo = new JTextField();
			TFProductoCodigo.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent arg0) {
					buscarProductoCodigo(arg0);
				}
			});
			TFProductoCodigo.setBounds(34, 46, 66, 26);
			TFProductoCodigo.setColumns(10);
			panel_1.add(TFProductoCodigo);
		}
		{
			JLabel lblCantidad = new JLabel("Cantidad");
			lblCantidad.setBounds(34, 84, 260, 16);
			panel_1.add(lblCantidad);
		}
		SCantidadProducto = new JSpinner(modelSpinner);
		SCantidadProducto.setBounds(34, 105, 339, 26);
		panel_1.add(SCantidadProducto);
		
		JLabel LBlPrecio = new JLabel("Precio Unitario");
		LBlPrecio.setBounds(34, 140, 260, 16);
		panel_1.add(LBlPrecio);
		
		TFPrecioUnitario = new JTextField();
		TFPrecioUnitario.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				calcularPrecios(1);
			}
		});
		TFPrecioUnitario.setBounds(34, 160, 339, 26);
		TFPrecioUnitario.setColumns(10);
		panel_1.add(TFPrecioUnitario);
		
		TFPrecioLote = new JTextField();
		TFPrecioLote.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				calcularPrecios(2);
			}
		});
		TFPrecioLote.setBounds(34, 218, 339, 26);
		TFPrecioLote.setColumns(10);
		panel_1.add(TFPrecioLote);
		
		JLabel LblPrecioLote = new JLabel("Precio Por Lote");
		LblPrecioLote.setBounds(34, 198, 260, 16);
		panel_1.add(LblPrecioLote);
		
		TFProducto = new JTextField();
		TFProducto.setEnabled(false);
		TFProducto.setColumns(10);
		TFProducto.setBounds(99, 46, 195, 26);
		panel_1.add(TFProducto);
		{
			BGrabar = new JButton("Grabar");
			BGrabar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					agregarProducto();
				}
			});
			BGrabar.setActionCommand("OK");
			BGrabar.setBounds(211, 261, 75, 29);
			panel_1.add(BGrabar);
		}
		{
			BEliminar = new JButton("Eliminar");
			BEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					eliminarProducto();
				}
			});
			BEliminar.setBackground(new Color(250, 128, 114));
			BEliminar.setActionCommand("OK");
			BEliminar.setBounds(298, 261, 75, 29);
			panel_1.add(BEliminar);
		}
		{
			BBuscarProducto = new JButton("Buscar");
			BBuscarProducto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buscarProducto();
				}
			});
			BBuscarProducto.setBounds(298, 46, 75, 29);
			panel_1.add(BBuscarProducto);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				BGrabarEntradaSalida = new JButton("Confirmar");
				BGrabarEntradaSalida.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						grabarMovimiento();
					}
				});
				BGrabarEntradaSalida.setActionCommand("OK");
				buttonPane.add(BGrabarEntradaSalida);
			}
			{
				BCancelar = new JButton("Cancel");
				BCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				BCancelar.setActionCommand("Cancel");
				buttonPane.add(BCancelar);
			}	
		}
		
		 //Deshabilitar la edición directa del Spinner
        JComponent editor = STotalProductos.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            ((JSpinner.DefaultEditor) editor).getTextField().setEditable(false);
        }

		//variables del constructor
		this.entradaSalidaMercancia = entradaSalidaMercancia;
		this.tipoOperacion = tipoOpercacion;
		this.mercanciaDAO = mercanciaDAO;
		
		buscar();
		validarTipoOperacion();
	}
	
	public void validarTipoOperacion() {
		
		if(tipoOperacion == Herramientas.tipoOperacion.seleccionar) {
			bloquearPantalla();
		}
		
		switch(tipoOperacion) {
		case Herramientas.tipoOperacion.seleccionar:
			cargarPantalla();
			bloquearPantalla();
			break;
		case Herramientas.tipoOperacion.insertar:
			break;
		}
			
	}
	
	public void cargarPantalla() {
		
		ProveedorService proveedorService = new ProveedorService();
		respuesta = new Respuesta("",true,null);
		
		try {
						
			respuesta = proveedorService.seleccionarId(mercanciaDAO.getId_Proveedor());
			
			if (!respuesta.getValor()) {
				JOptionPane.showMessageDialog(this, "Error al obtener al Proveedor : "+respuesta.getMensaje(),"Error",JOptionPane.ERROR_MESSAGE);
				return ;
			}
			
			proveedor = (Proveedor) respuesta.getRespuesta();
			
			TFProveedor.setText(proveedor.getTelefono()+", "+proveedor.getNombre()+" "+proveedor.getApaterno());
			CBEntradaSalida.setSelectedItem(mercanciaDAO.getTipoOperacion());
			STotalProductos.setValue(mercanciaDAO.getTotalProductos());
			TFTotalCompra.setText(mercanciaDAO.getTotalCompra()+"");
			EPDescripcion.setText(mercanciaDAO.getDescripcion());
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error al Cargar la Información : "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void bloquearPantalla() {
		TFProveedor.setEditable(false);
		BBuscarProveedor.setEnabled(false);
		CBEntradaSalida.setEnabled(false);
		STotalProductos.setEnabled(false);
		TFTotalCompra.setEditable(false);
		EPDescripcion.setEditable(false);
		BBuscarProducto.setEnabled(false);
		SCantidadProducto.setEnabled(false);
		TFProducto.setEditable(false);
		TFPrecioUnitario.setEditable(false);
		TFPrecioLote.setEditable(false);
		TFProductoCodigo.setEditable(false);
		
		BGrabar.setVisible(false);
		BEliminar.setVisible(false);
		
		BGrabarEntradaSalida.setVisible(false);
		BCancelar.setText("Cerrar");
		
	}
	
	public void grabarMovimiento() {
		
		respuesta = new Respuesta("",true,null);
		ControllerMercancia  controllerMercancia = new ControllerMercancia();
		Map< String,Object> map = new HashMap<String,Object>();
		
		if (mercanciasDet.size()==0) {
			JOptionPane.showConfirmDialog(this, "No hay Productos por Agregar","Advertencia",JOptionPane.WARNING_MESSAGE);
			return;
		}

		respuesta = validarProveedor();
		System.out.println(respuesta.getMensaje());
		if (!respuesta.getValor()) {
			JOptionPane.showConfirmDialog(this, respuesta.getMensaje(),"Advertencia",JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		mercanciaDAO = new MercanciaDAO();
		
		mercanciaDAO.setId_HistorialCompra(0);
		mercanciaDAO.setId_Proveedor(proveedor.getId_Proveedor());
		mercanciaDAO.setTotalProductos((int) STotalProductos.getValue());
		mercanciaDAO.setTotalCompra( Float.parseFloat(TFTotalCompra.getText()) );
		mercanciaDAO.setDescripcion(EPDescripcion.getText());
		mercanciaDAO.setEstadoPago("PAGADO");
		mercanciaDAO.setTipoOperacion( (String) CBEntradaSalida.getSelectedItem() );
		mercanciaDAO.setEstatus("ACTIVO");
		
		map.put("mercanciaDAO", mercanciaDAO );
		map.put("mercanciasDet", mercanciasDet);
		
		respuesta = controllerMercancia.proceso(Herramientas.tipoOperacion.insertar, map);
		
		if (!respuesta.getValor()) {
			JOptionPane.showMessageDialog(this, respuesta.getMensaje(),"Error",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		JOptionPane.showMessageDialog(this, respuesta.getMensaje(),"Información",JOptionPane.INFORMATION_MESSAGE);
		limpiarProducto();
		bloquearPantalla();
		entradaSalidaMercancia.buscar();
	}
	
	public void calcultarTotales() {
		
		int totalProductos = mercanciasDet.stream()
			.mapToInt(MercanciaDetView::getCantidad)
			.sum();
		
		float totalCompra = mercanciasDet.stream()
			.map(p -> p.getPrecioTotal())
			.reduce(0f, (a, b) -> a + b);
		
		TFTotalCompra.setText(totalCompra+"");
		STotalProductos.setValue(totalProductos);
	}
	
	public void buscarProveedor() {
		
		JD_Proveedores jd_proveedor = new JD_Proveedores(this);		
		jd_proveedor.setVisible(true);
		
	}
	
	public void cargarProveedor() {
		
		TFProveedor.setText(proveedor.getTelefono()+", "+proveedor.getNombre()+" "+proveedor.getApaterno());
	}
	
	public void buscarProducto() {
		
		JD_Productos jd_Productos = new JD_Productos(null,this);
		jd_Productos.setModal(true);
		jd_Productos.setVisible(true);
		
		
	}
	
	public void limpiarProducto() {
		
		TFProductoCodigo.setText("");
		TFProducto.setText("");
		SCantidadProducto.setValue(0);
		System.out.println("Limpiando");
		TFPrecioUnitario.setText("");
		TFPrecioLote.setText("");
		mercanciaDetView = null;
		producto = null;
		
	}
	
	public void seleccionarElementoTabla() {
				
		try {
			
			int fila = TMercanciaDet.getSelectedRow();
			if (fila == -1)
				return;
			
			int idProducto = (int) TMercanciaDet.getValueAt(fila, (Integer) TMercanciaDet.getColumnModel().getColumnIndex("Id_Producto") );			
		    Optional<MercanciaDetView>	mercanciaDet_  = mercanciasDet.stream()
						.filter(merca -> merca.getId_Producto() == idProducto)
						.findFirst();
			 
		   mercanciaDetView = mercanciaDet_.get();
		   CargarProductoPantalla();
		   buscarProducto(mercanciaDetView.getCodigo());
		   
		} catch (Exception e) {
			System.out.println("Error al obtener el elemento seleccionado "+e.getMessage());
		}
		
	}
	
	public void eliminarProducto() {
		
		try {
			
			int fila = TMercanciaDet.getSelectedRow();
			if(fila == -1)
				return;
			
			int idMercancia = (int) TMercanciaDet.getValueAt(fila, (Integer) TMercanciaDet.getColumnModel().getColumnIndex("Id_Producto") );
			
			String codigo = (String) TMercanciaDet.getValueAt(fila, TMercanciaDet.getColumnModel().getColumnIndex("Código"));
			
			int eliminarRegistro = JOptionPane.showConfirmDialog(
					this,
					"¿Estás seguro que Quieres Eliminar el Registro con Código "+codigo+"?",
					"Confirmar Eliminación", 
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE
					);
			
			if (eliminarRegistro == JOptionPane.NO_OPTION) {
				return;
			}
			
			System.out.println("Producto a eliminar "+idMercancia);
			mercanciasDet.removeIf( producto -> producto.getId_Producto() == idMercancia );
			limpiarProducto();
			PintarTabla();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public void CargarProductoPantalla() {
		
		TFProducto.setText(mercanciaDetView.getNombre());
		TFPrecioUnitario.setText(mercanciaDetView.getPrecioUnitario()+"");
		TFPrecioLote.setText(mercanciaDetView.getPrecioTotal()+"");
		SCantidadProducto.setValue(mercanciaDetView.getCantidad());
	
	}
	
	public void agregarProducto() {
		
		try {
			
			respuesta = validarProducto();
			
			if (!respuesta.getValor()) {
				JOptionPane.showMessageDialog(this, respuesta.getMensaje(),"Advertencia",JOptionPane.WARNING_MESSAGE);
			}
			
			mercanciaDetView = new MercanciaDetView(
					0,
					0,
					producto.getId_producto(),
					producto.getNombre(),
					producto.getDescripcion(),
					producto.getCodigo(),
					(Integer) SCantidadProducto.getValue(),
					Float.parseFloat(TFPrecioUnitario.getText()),
					Float.parseFloat(TFPrecioLote.getText()),
					"ACTIVO"
					);
			
			if ( mercanciasDet.stream()
					.filter( merca -> merca.getId_Producto() == mercanciaDetView.getId_Producto()  )
					.count() >0
					) {
				mercanciasDet.replaceAll( obj -> obj.getId_Producto() == mercanciaDetView.getId_Producto() ? mercanciaDetView : obj );				
			}else {
				mercanciasDet.add(mercanciaDetView);
			}
			
			limpiarProducto();
			PintarTabla();
			calcultarTotales();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public Respuesta validarProveedor() {
		
		respuesta = new Respuesta("",true,null);
		
		if (proveedor == null) {
			return new Respuesta("Seleccione un Proveedor",false,null);
		}
		
		if (EPDescripcion.getText().isEmpty() || EPDescripcion.getText() == null) {
			return new Respuesta("Agrega una descripción",false,null);
		}
		
		return respuesta;
	}
	
	public Respuesta validarProducto() {
		
		respuesta = new Respuesta("",true,null);

		if (producto == null) {
			return new Respuesta("Selecciona un Producto",false,null);			
		}

		if ( TFProducto.getText()== null || TFProducto.getText().isEmpty()) {
			return new Respuesta("Selecciona un Producto",false,null);
		}
		
		if (! Herramientas.validarEntero(SCantidadProducto.getValue().toString())) {
			
		}
		
		if( !Herramientas.validarFlotante(TFPrecioUnitario.getText())) {
			return new Respuesta("Introduce un Precio Unitario",false,null);
		}
		
		if (!Herramientas.validarFlotante(TFPrecioLote.getText())) {
			return new Respuesta("Introduce el Preio del Lote",false,null);
		}
		return respuesta;
		
	}
	
	public void calcularPrecios(int tipoOperacion) {
		
		float valor = 0f;
		
		try {
			
			switch (tipoOperacion) {
			case 1:
				
				if (SCantidadProducto.getValue().toString().isEmpty()  || SCantidadProducto.getValue() == null) {
					return;
				}
				
				if (TFPrecioUnitario.getText() == null || TFPrecioUnitario.getText().isEmpty()  ) {
					return;				
				}
				
				valor = Float.parseFloat(TFPrecioUnitario.getText())  * (Integer) SCantidadProducto.getValue() ;
				TFPrecioLote.setText(valor+"");
				
				break;

			case 2:
				
				if (SCantidadProducto.getValue().toString().isEmpty()  || SCantidadProducto.getValue() == null) {
					return;
				}
				
				if (TFPrecioLote.getText() == null || TFPrecioLote.getText().isEmpty()) {
					return;
				}
				
				valor = Float.parseFloat(TFPrecioLote.getText())  / (Integer) SCantidadProducto.getValue() ;
				TFPrecioUnitario.setText(valor+"");
				
				break;
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Favor de Validar si tus valores son Correctos "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public void buscar() {
		
		MercanciaService mercanciasService = new MercanciaService();
		respuesta = new Respuesta("",true,null);
		
		if(mercanciaDAO == null) {
			return;
		}
		
		respuesta = mercanciasService.obtenerDetalleCompra(mercanciaDAO.getId_HistorialCompra());
		
		if (!respuesta.getValor()) {
			JOptionPane.showMessageDialog(this,respuesta.getMensaje(),"Error",JOptionPane.ERROR_MESSAGE);
			return ;
		}	
		
		mercanciasDet = (ArrayList<MercanciaDetView>) respuesta.getRespuesta();
		
		PintarTabla();
			
	}
	
	public void PintarTabla() {
		Object[][] datos = new Object[mercanciasDet != null?mercanciasDet.size():0][11];
		int i=0;
		
		try {
		
			for(MercanciaDetView merca: mercanciasDet)
			{
				System.out.println("Mensaje "+merca.getId_HistorialCompraDet());
				datos[i][0] = merca.getId_HistorialCompraDet();
				datos[i][1] = merca.getId_HistorialCompra();
				datos[i][2] = merca.getId_Producto();
				datos[i][3] = merca.getCodigo();
				datos[i][4] = merca.getNombre();
				datos[i][5] = merca.getDescripcion();
				datos[i][6] = merca.getCantidad();
				datos[i][7] = merca.getPrecioUnitario();
				datos[i][8]= merca.getPrecioTotal();
				datos[i][9] = merca.getEstatus();
				i++;
			}
		
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}		
		dtm = new DefaultTableModel(datos,columnNames);
		TMercanciaDet.setModel(dtm);
		ComponentesDesing.PreferredWithTableESMercancia(TMercanciaDet);
	}
	
	public void buscarProducto(String codigo) {
		respuesta = productosDAO.obtenerProductoCodigo(codigo);
		TFProductoCodigo.setText("");

		if (!respuesta.getValor()) {
			JOptionPane.showMessageDialog(this,"Producto No Encontrado","Advertencia",JOptionPane.WARNING_MESSAGE);
		}
		producto = (Producto) respuesta.getRespuesta();
		presentarProducto();		
	}
	
	public void presentarProducto() {						
		TFProducto.setText(producto.getNombre());
	}
	
	public void buscarProductoCodigo(KeyEvent e) {
		
		producto = new Producto();
		Respuesta respuesta = new Respuesta("",false,null);
		
		if (e.getKeyChar() != KeyEvent.VK_ENTER) {
			return ;
		}
		
		if ( TFProductoCodigo.getText() == null || TFProductoCodigo.getText().isEmpty()) {
			return;
		}
		
		buscarProducto(TFProductoCodigo.getText());
		
	}
}

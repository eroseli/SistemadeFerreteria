package Views;

import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import java.awt.Component;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import Controllers.ControllerProveedor;
import Controllers.ControllerUsuario;
import DAO.ProveedoresDAO;
import DAO.ModelsDAO.Proveedor;
import DAO.ModelsDAO.Usuario;
import HerramientasConexion.Herramientas;
import Models.ProductoBusquedaView;
import Models.Respuesta;
import Models.Components.CustomHeaderRenderer;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

public class JP_Proveedores extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField TFBuscar;
	private DefaultTableModel dtm;

	String[] columnNames = {"Id", "Nombre", "Ap. Paterno", "Ap. Materno",
			"Teléfono", "Correo", "Empresa", "Dirección","Productos","Notas","Fecha de Registro"};
	
	// Ejemplo de datos (puedes llenar con datos reales de tu aplicación)
    Object[][] datos = {
    		{1, "Eraldo", "Matias", "Santaella","2223334455", "santaella@gmail.com","aliexpress","1 cerrada de Uruguay","","","12-12-234"},
    		{2, "Israle", "Dionisio", "Sanchez","0987654323", "israaa@gmal.com","Trupper","Mexico 12#12","","","12-12-2232"}       
    };
    
    private JTable TProveedores;
    
    
    //variables 
    
    List<Proveedor> proveedores = new ArrayList<>();
    ControllerProveedor controllerProveedor = new ControllerProveedor();
    
	public JP_Proveedores() {
		setMinimumSize(new Dimension(892, 666));
		setMaximumSize(new Dimension(872, 644));
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBounds(12, 42, 850, 595);
		add(scrollPane);
		
		TProveedores = new JTable();
		dtm = new DefaultTableModel(datos, columnNames);
		TProveedores.setModel(dtm);
		scrollPane.setViewportView(TProveedores);
		
		JTableHeader header = TProveedores.getTableHeader();
		header.setDefaultRenderer(new CustomHeaderRenderer(2));
		TProveedores.setDefaultRenderer(Object.class, new CustomHeaderRenderer(2));
		
		
		TFBuscar = new JTextField();
		TFBuscar.setColumns(10);
		TFBuscar.setBounds(623, 11, 156, 20);
		add(TFBuscar);
		
		JButton BBuscar = new JButton("Buscar");
		BBuscar.setToolTipText("Buscar productos con aplicación de filtros");
		BBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		BBuscar.setBounds(789, 11, 73, 20);
		add(BBuscar);

		limpiarTablaProductos();
		iniciarPantalla();
		
	}
	
	public void iniciarPantalla() {
		
		Respuesta respuesta = new Respuesta("",true,null);
		
		 respuesta = controllerProveedor.proceso(Herramientas.tipoOperacion.seleccionar, TFBuscar.getText());
		 proveedores = (ArrayList<Proveedor>) respuesta.getRespuesta();
		 		
		if(!respuesta.getValor()) {
			JOptionPane.showMessageDialog(this,respuesta.getMensaje());
			return;
		}
		
		pintarTabla((ArrayList<Usuario>) respuesta.getRespuesta());	
		
		
	}
	
	
	public void pintarTabla(ArrayList<Usuario> usuarios) {
		
		Object[][] datos = new Object[proveedores != null?proveedores.size():0][11];
		int i=0;
		
		try {
		
			for(Proveedor proveedor: proveedores)
			{
				datos[i][0] = proveedor.getId_Proveedor();
				datos[i][1] = proveedor.getNombre();
				datos[i][2] = proveedor.getApaterno();
				datos[i][3] = proveedor.getAmaterno();
				datos[i][4] = proveedor.getTelefono();
				datos[i][5]= proveedor.getCorreo();
				datos[i][6] = proveedor.getEmpresa();
				datos[i][7] = proveedor.getDireccion();
				datos[i][8] = proveedor.getTipoProducto();
				datos[i][9] = proveedor.getNotasAdicionales();
				datos[i][10] = proveedor.getFechaRegistro();
				i++;
			}
		
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}		
		dtm = new DefaultTableModel(datos,columnNames);
		TProveedores.setModel(dtm);
		ajustarTabla(TProveedores);
	}
	
	public void ajustarTabla(JTable jTable) {}
	
	private void limpiarTablaProductos() {		
		try {
			while (TProveedores.getRowCount() > 0) {
				dtm.removeRow(0);
	        }
		} catch (Exception  e) {
			System.out.println(e.getMessage());
		}
	}
	
}

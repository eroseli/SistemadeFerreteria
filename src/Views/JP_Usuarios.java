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

import Controllers.ControllerUsuario;
import DAO.ModelsDAO.Producto;
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

public class JP_Usuarios extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private DefaultTableModel dtm;

	String[] columnNames = {"Id", "Usuario", "Password", "Nombre",
			"Ap. Paterno", "Ap. Materno", "Correo", "Dirección","Puesto", "Teléfono", "Fecha Registro"};
	
	// Ejemplo de datos (puedes llenar con datos reales de tu aplicación)
    Object[][] datos = {
    		{1, "Damian", "Contrasena", "Damian","Ramirez", "Sanchez","1 PRIVADA DE SEPTIEMBRE","Programador","9514134591","fecha"},
    		{1, "Damian", "Contrasena", "Damian","Ramirez", "Sanchez","1 PRIVADA DE SEPTIEMBRE","Programador","9514134591","fecha"}       
    };
    
    private JTable TUsuarios;
    
    
    //variables 
    
    List<Usuario> usuarios = new ArrayList<>();
    ControllerUsuario controllerUsuario = new ControllerUsuario();
    
	public JP_Usuarios() {
		setMinimumSize(new Dimension(892, 666));
		setMaximumSize(new Dimension(872, 644));
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBounds(12, 42, 850, 595);
		add(scrollPane);
		
		TUsuarios = new JTable();
		dtm = new DefaultTableModel(datos, columnNames);
		TUsuarios.setModel(dtm);
		scrollPane.setViewportView(TUsuarios);
		
		JTableHeader header = TUsuarios.getTableHeader();
		header.setDefaultRenderer(new CustomHeaderRenderer(2));
		TUsuarios.setDefaultRenderer(Object.class, new CustomHeaderRenderer(2));
		
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(623, 11, 156, 20);
		add(textField);
		
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
		
		 respuesta = controllerUsuario.proceso(Herramientas.tipoOperacion.seleccionar, "");
		 usuarios = (ArrayList<Usuario>) respuesta.getRespuesta();
		 		
		if(!respuesta.getValor()) {
			JOptionPane.showMessageDialog(this,respuesta.getMensaje());
			return;
		}
		
		pintarTabla((ArrayList<Usuario>) respuesta.getRespuesta());	
		
		
	}
	
	public void pintarTabla(ArrayList<Usuario> usuarios) {
		
		Object[][] datos = new Object[usuarios != null?usuarios.size():0][11];
		int i=0;
		
		try {
		
			for(Usuario usuario: usuarios)
			{
				datos[i][0] = usuario.getId_Usuario();
				datos[i][1] = usuario.getUsuario();
				datos[i][2] = usuario.getPassword();
				datos[i][3] = usuario.getNombre();
				datos[i][4] = usuario.getApaterno();
				datos[i][5]= usuario.getCorreo();
				datos[i][6] = usuario.getAmaterno();
				datos[i][7] = usuario.getDireccion();
				datos[i][8] = usuario.getPuesto();
				datos[i][9] = usuario.getTelefono();
				datos[i][10] = usuario.getFechaRegistro();
				i++;
			}
		
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}		
		dtm = new DefaultTableModel(datos,columnNames);
		TUsuarios.setModel(dtm);
		ajustarTabla(TUsuarios);
	}
	
	public void ajustarTabla(JTable jTable) {}
	
	private void limpiarTablaProductos() {		
		try {
			while (TUsuarios.getRowCount() > 0) {
				dtm.removeRow(0);
	        }
		} catch (Exception  e) {
			System.out.println(e.getMessage());
		}
	}
	
}

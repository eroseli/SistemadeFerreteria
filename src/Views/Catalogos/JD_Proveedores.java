package Views.Catalogos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import Controllers.ControllerCliente;
import Controllers.ControllerProveedor;
import DAO.ModelsDAO.Cliente;
import DAO.ModelsDAO.Proveedor;
import HerramientasConexion.Herramientas;
import Models.Respuesta;
import Models.Components.CustomHeaderRenderer;
import Models.Components.JTableEdited;
import Views.Forms.JD_DetalleESMercancia;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JD_Proveedores extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable TProveedores;
	
	
	private JD_DetalleESMercancia detalleESMercancia = null;
	private Respuesta respuesta = null;
	private JTextField TFBuscar;
	
	private ArrayList<Proveedor> proveedores = new ArrayList<Proveedor>();
	private Proveedor proveedor = null;
	
	public static void main(String[] args) {
		try {
			JD_Proveedores dialog = new JD_Proveedores(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JD_Proveedores(JD_DetalleESMercancia detalleESMercancia) {
		setTitle("Busqueda de Proveedores");
		setBounds(100, 100, 800, 550);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(6, 39, 788, 438);
			contentPanel.add(scrollPane);
			{
				TProveedores = new JTableEdited();
				TProveedores.setRowHeight(18);
				TProveedores.setFont(new Font("Arial", Font.PLAIN, 12));			
				scrollPane.setViewportView(TProveedores);
				TProveedores.setBackground(new Color(249, 251, 251));
				TProveedores.setAutoCreateRowSorter(true);
				TProveedores.isCellEditable(0,0);
				//TProveedores.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

				JTableHeader header = TProveedores.getTableHeader();
				header.setDefaultRenderer(new CustomHeaderRenderer(2));
				TProveedores.setDefaultRenderer(Object.class, new CustomHeaderRenderer(2));
				
				
			}
		}
		{
			JButton BBuscar = new JButton("Buscar");
			BBuscar.setBounds(677, 6, 117, 29);
			contentPanel.add(BBuscar);
		}
		{
			TFBuscar = new JTextField();
			TFBuscar.setBounds(493, 6, 176, 26);
			contentPanel.add(TFBuscar);
			TFBuscar.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						obtenerProductoSeleccionado();
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
		
		//Locales
		buscarProveedor();
		this.detalleESMercancia = detalleESMercancia;
		
	}
	
	public void obtenerProductoSeleccionado() {
		
		
		try {
			
			String value = String.valueOf( TProveedores.getValueAt(TProveedores.getSelectedRow(), 0 ));
			
			
			Proveedor prov = proveedores.stream()
					.filter(p -> p.getId_Proveedor() == value)
					.findFirst()
					.orElse(null);
			
			detalleESMercancia.proveedor = prov;
			this.dispose();
			detalleESMercancia.cargarProveedor();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	
	public void buscarProveedor() {
		
		ControllerProveedor controllerProveedor =  new ControllerProveedor();
		respuesta = new Respuesta("",true,null);
		String nombre = TFBuscar.getText().trim();
		
		if (TFBuscar.getText().trim() =="" || TFBuscar.getText().trim().isEmpty() ) {
			nombre = null;
		}
		
		respuesta = controllerProveedor.proceso(Herramientas.tipoOperacion.seleccionar,nombre);
		
		if (!respuesta.getValor()) {
			JOptionPane.showMessageDialog(this,respuesta.getMensaje(),"Error",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		pintarTablaProveedor( (ArrayList<Proveedor>) respuesta.getRespuesta());
		
	}
	
	public void pintarTablaProveedor(ArrayList<Proveedor> proveedoreslist) {
		
		respuesta = new Respuesta("",true,null);
		String[] columnNames = {"Id","Nombre","A. Paterno","A. Materno","Teléfono","Correo","Empresa","Dirección"};
		
		proveedores = proveedoreslist;
		
		Object[][] datos = new Object[proveedores != null? proveedores.size():0][10];
		
		int i=0;

		
		try {
			
			for(Proveedor proveedor: proveedores)
			{
				datos[i][0] = proveedor.getId_Proveedor();
				datos[i][1] = proveedor.getNombre();
				datos[i][2] = proveedor.getApaterno();
				datos[i][3] = proveedor.getAmaterno();
				datos[i][4] = proveedor.getTelefono();
				datos[i][5] = proveedor.getCorreo();
				datos[i][6] = proveedor.getEmpresa();
				datos[i][7] = proveedor.getDireccion();
				i++;
			}
			
		} catch (NullPointerException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}		
		
		DefaultTableModel dtm = new DefaultTableModel(datos,columnNames);
		//dtm.setColumnIdentifiers(columnNames);
		TProveedores.setModel(dtm);	
	}

}

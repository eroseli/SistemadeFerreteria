package Views.Catalogos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import Controllers.ControllerCliente;
import DAO.ModelsDAO.Cliente;
import DAO.ModelsDAO.Producto;
import HerramientasConexion.Herramientas;
import HerramientasConexion.Herramientas.tipoOperacion;
import Models.Respuesta;
import Models.Components.CustomHeaderRenderer;
import Models.Components.JTableEdited;
import Views.JF_Venta;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public class JD_Clientes extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable TablaClientes;

	
	private ControllerCliente controllerClintes = new ControllerCliente();
	private Respuesta respuesta;
	private List<Cliente> clientes;
	private JF_Venta jf_Venta;
	String identificador = "";
	private JTextField TFBuscar;


	public JD_Clientes(JF_Venta jf_Venta) {
		setTitle("Busqueda de Clientes");
		setBounds(100, 100, 802, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(254, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 34, 791, 493);
		
		TablaClientes = new JTableEdited();
		TablaClientes.setFont(new Font("Arial", Font.PLAIN, 12));
		scrollPane.setViewportView(TablaClientes);
		TablaClientes.setBackground(new Color(254, 255, 255));
		TablaClientes.setAutoCreateRowSorter(true);
		TablaClientes.isCellEditable(0,0);
		
		// tabla personalizada
		JTableHeader header = TablaClientes.getTableHeader();
		header.setDefaultRenderer(new CustomHeaderRenderer(2));
		TablaClientes.setDefaultRenderer(Object.class, new CustomHeaderRenderer(2));
					
		
		contentPanel.setLayout(null);
		
		contentPanel.add(scrollPane);
		
		TFBuscar = new JTextField();
		TFBuscar.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				buscarCliente();
			}
		});
		TFBuscar.setBounds(478, 6, 200, 26);
		contentPanel.add(TFBuscar);
		TFBuscar.setColumns(10);
		
		JButton BBuscar = new JButton("Buscar");
		BBuscar.setBounds(680, 6, 117, 29);
		contentPanel.add(BBuscar);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						seleccionarCliente();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
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
		
		//Locales
		buscarCliente();
		this.jf_Venta = jf_Venta;
		
	}
	
	public void buscarCliente() {
		
		ControllerCliente controllerCliente =  new ControllerCliente();
		respuesta = new Respuesta("",true,null);
		String nombre = TFBuscar.getText().trim();
		
		if (TFBuscar.getText().trim() =="" || TFBuscar.getText().trim().isEmpty() ) {
			nombre = null;
		}
		
		respuesta = controllerCliente.proceso(Herramientas.tipoOperacion.seleccionar,nombre);
		
		if (!respuesta.getValor()) {
			JOptionPane.showMessageDialog(this,respuesta.getMensaje(),"Error",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		pintarTablaClientes( (ArrayList<Cliente>) respuesta.getRespuesta());
		
	}
	
	
	public void seleccionarCliente() {
		
		
		try {
			
			identificador = String.valueOf(TablaClientes.getValueAt(TablaClientes.getSelectedRow(), 1));
			System.out.println(identificador);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Producto no Seleccionado");
		}
		
		Cliente cliente = clientes.stream()
				.filter( c -> c.getIdentificador().equals(identificador))
				.findFirst()
				.orElse(null);	
		
		jf_Venta.cliente = cliente;
		
		if (cliente != null) {
			jf_Venta.TF_Cliente.setText(cliente.getNombre() + " "+ cliente.getApaterno()+" "+cliente.getAmaterno());
		}
		
		this.dispose();
	}
	
	
	public void pintarTablaClientes(ArrayList<Cliente> clientesList) {
		
		respuesta = new Respuesta("",true,null);
		String[] columnNames = {"Id","Identificador","Nombre","A. Paterno","A. Materno","Fechan Nacimiento","Teléfono","Correo", "Compras","Fecha Ingreso"};
		
		clientes = clientesList;
		
		Object[][] datos = new Object[clientes != null? clientes.size():0][10];
		
		int i=0;

		
		try {
			
			for(Cliente cliente: clientes)
			{
				datos[i][0] = "";
				datos[i][1] = cliente.getIdentificador();
				datos[i][2] = cliente.getNombre();
				datos[i][3] = cliente.getApaterno();
				datos[i][4] = cliente.getAmaterno();
				datos[i][5] = cliente.getFechaNac();
				datos[i][6] = cliente.getTelefono();
				datos[i][7] = cliente.getCorreo();
				datos[i][8] = cliente.getCompras();
				datos[i][9] = cliente.getFechaRegistro();
				i++;
			}
			
		} catch (NullPointerException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}		
		
		DefaultTableModel dtm = new DefaultTableModel(datos,columnNames);
		//dtm.setColumnIdentifiers(columnNames);
		TablaClientes.setModel(dtm);	
	}
}

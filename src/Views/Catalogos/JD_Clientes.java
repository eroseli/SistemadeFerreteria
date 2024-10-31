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

import Controllers.ControllerCliente;
import DAO.ModelsDAO.Cliente;
import DAO.ModelsDAO.Producto;
import HerramientasConexion.Herramientas;
import Models.Respuesta;
import Models.Components.JTableEdited;
import Views.JF_Venta;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JD_Clientes extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable TablaClientes;

	
	private ControllerCliente controllerClintes = new ControllerCliente();
	private Respuesta respuesta;
	private List<Cliente> clientes;
	private JF_Venta jf_Venta;
	String identificador = "";


	public JD_Clientes(JF_Venta jf_Venta) {
		setBounds(100, 100, 800, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 791, 430);
		
		TablaClientes = new JTableEdited();
		TablaClientes.setFont(new Font("Arial", Font.PLAIN, 12));
		scrollPane.setViewportView(TablaClientes);
		TablaClientes.setBackground(new Color(229, 247, 246));
		TablaClientes.setAutoCreateRowSorter(true);
		TablaClientes.isCellEditable(0,0);
		
		contentPanel.add(scrollPane);
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
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		//Locales
		obtenerClientes();
		this.jf_Venta = jf_Venta;
		
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
	
	
	public void obtenerClientes() {
		
		respuesta = new Respuesta("",true,null);
		String[] columnNames = {"Id","Identificador","Nombre","A. Paterno","A. Materno","Fechan Nacimiento","Teléfono","Correo", "Compras","Fecha Ingreso"};

	
		respuesta = controllerClintes.proceso(Herramientas.tipoOperacion.seleccionar, null);

		if ( !respuesta.getValor() ) {
			JOptionPane.showMessageDialog(this, "Error: "+respuesta.getMensaje());
			return ;
		}
		
		clientes = (ArrayList<Cliente>) respuesta.getRespuesta();
		
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

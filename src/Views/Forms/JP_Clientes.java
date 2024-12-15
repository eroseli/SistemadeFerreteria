package Views.Forms;

import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import java.awt.Component;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import Controllers.ControllerCliente;
import Controllers.ControllerProveedor;
import Controllers.ControllerUsuario;
import DAO.ProveedoresDAO;
import DAO.ModelsDAO.Cliente;
import DAO.ModelsDAO.Proveedor;
import DAO.ModelsDAO.Usuario;
import HerramientasConexion.Herramientas;
import Models.ProductoBusquedaView;
import Models.Respuesta;
import Models.Components.CustomHeaderRenderer;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.JTable;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import javax.swing.JPopupMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

public class JP_Clientes extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField TFBuscar;
	private DefaultTableModel dtm;

	String[] columnNames = {"No.", "Código","Nombre", "Ap. Paterno", "Ap. Materno",
			"Teléfono", "Correo","Fecha de Nac.","Compras","Fecha de Registro"};
	
	// Ejemplo de datos (puedes llenar con datos reales de tu aplicación)
    Object[][] datos = {  };
    
    private JTable TClientes;
    
    
    //variables 
    Respuesta respuesta = null;
    List<Cliente> clientes = new ArrayList<>();
    ControllerCliente controllerCliente = new ControllerCliente();
    private JPopupMenu PMClientes;
    
	public JP_Clientes() {
		setMinimumSize(new Dimension(892, 666));
		setMaximumSize(new Dimension(872, 644));
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBounds(12, 42, 850, 595);
		add(scrollPane);
		
		TClientes = new JTable();
		TClientes.setRowHeight(25);
		TClientes.setDefaultEditor(Object.class,null);
		dtm = new DefaultTableModel(datos, columnNames);
		TClientes.setModel(dtm);
		scrollPane.setViewportView(TClientes);
		
		JTableHeader header = TClientes.getTableHeader();
		header.setDefaultRenderer(new CustomHeaderRenderer(2));
		TClientes.setDefaultRenderer(Object.class, new CustomHeaderRenderer(2));
		
		
		PMClientes = new JPopupMenu();
		
		JMenuItem visualizar = new JMenuItem("Visualizar");
		JMenuItem agregar = new JMenuItem("Agregar");
		JMenuItem editar = new JMenuItem("Editar");
		JMenuItem eliminar = new JMenuItem("Eliminar");
		
		PMClientes.add(visualizar);
		PMClientes.add(agregar);
		PMClientes.add(editar);
		PMClientes.add(eliminar);		
		addPopup(TClientes, PMClientes);
		
		visualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                visualizar();
            }
        });
		
		agregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregar();
            }
        });
		
		editar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	editar();
            }
        });
		
		eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminar();
            }
        });
		
		
		
		TFBuscar = new JTextField();
		TFBuscar.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				buscar();
			}
		});
		TFBuscar.setColumns(10);
		TFBuscar.setBounds(579, 11, 200, 20);
		add(TFBuscar);
		
		JButton BBuscar = new JButton("Buscar");
		BBuscar.setToolTipText("Buscar productos con aplicación de filtros");
		BBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		BBuscar.setBounds(789, 11, 73, 20);
		add(BBuscar);
		
		JButton BAgregar = new JButton("Agregar");
		BAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				agregar();
			}
		});
		BAgregar.setBounds(6, 8, 117, 29);
		add(BAgregar);
		
		JLabel lblNewLabel = new JLabel("Nombre (Teléfono) :");
		lblNewLabel.setBounds(446, 13, 125, 16);
		add(lblNewLabel);

		limpiarTablaProductos();
		iniciarPantalla();
		buscar();
	}
	
	public void agregar() {
		
		FormCliente formCliente = new FormCliente(this, Herramientas.tipoOperacion.insertar,null);
		formCliente.setVisible(true);
		
	}
	
	public void visualizar() {
		
		Cliente cliente = null;
		
		try {
			
			if (TClientes.getSelectedRow() ==-1) {
				JOptionPane.showMessageDialog(this, "No hay una Fila Seleccionada","Advertencia",JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			String idCliente = (String) TClientes.getValueAt(TClientes.getSelectedRow(), 1);
			
			cliente = clientes.stream()
					.filter(c -> c.getIdentificador() == idCliente )
					.findFirst()
					.orElse(null);
			
			FormCliente formCliente =  new FormCliente(this, Herramientas.tipoOperacion.seleccionar, cliente);
			formCliente.setVisible(true);
			
			
		} catch (Exception e) {
			System.out.println("Error al intentar obtener los valores del Cliente");
		}
	}
	
	public void editar() {
		
		int indice = 0;
		Optional<Cliente> cli = java.util.Optional.empty();
		
		try {
			indice = TClientes.getSelectedRow();
			
			if (indice == -1) {
				JOptionPane.showMessageDialog(this, "No hay una Fila Seleccionada","Advertencia",JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			String idCliente = (String) TClientes.getValueAt(indice, 1);
			cli = clientes.stream()
					.filter(p -> p.getIdentificador() == idCliente)
					.findFirst();
			
		} catch (Exception e) {
			System.out.println("Error, No se puede obtener el valor : "+ e.getMessage());
		}
		
		FormCliente formCliente =  new FormCliente(this, Herramientas.tipoOperacion.actualizar, cli.orElse(null));
		formCliente.setVisible(true);
		
	}
	
	public void eliminar() {
		
		Optional<Cliente> cliente = java.util.Optional.empty();
		ControllerCliente controllerCliente = new ControllerCliente();
		respuesta = new Respuesta("",true,null);
		Cliente c = null;
		
		try {
			
			int indice = TClientes.getSelectedRow();
			
			if (indice == -1) {
				JOptionPane.showMessageDialog(this, "No hay una fila Seleccionada","Advertencia", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			String  idCliente = (String) TClientes.getValueAt(indice, 1);			
			cliente = clientes.stream()
					.filter(cl -> cl.getIdentificador() == idCliente)
					.findFirst();
			
			if (cliente.orElse(null) == null) {
				JOptionPane.showMessageDialog(this, "Cliente No Encontrado","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			c = cliente.get();
			
			int continuar = JOptionPane.showConfirmDialog(this,
					"¿Estás seguro que deseas Eliminar al Cliente "+c.getIdentificador()+"?",
					"Confirmación",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			
			if (continuar == JOptionPane.NO_OPTION || continuar == JOptionPane.CLOSED_OPTION ) {
				return;
			}
			
			respuesta = controllerCliente.proceso(Herramientas.tipoOperacion.eliminar, c.getIdentificador());
			JOptionPane.showMessageDialog(this, respuesta.getMensaje(),"Mensaje",JOptionPane.INFORMATION_MESSAGE);			
			buscar();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public void iniciarPantalla() {
		
		Respuesta respuesta = new Respuesta("",true,null);
		
		 respuesta = controllerCliente.proceso(Herramientas.tipoOperacion.seleccionar, TFBuscar.getText());
		 clientes = (ArrayList<Cliente>) respuesta.getRespuesta();
		 		
		if(!respuesta.getValor()) {
			JOptionPane.showMessageDialog(this,respuesta.getMensaje());
			return;
		}
		
		//pintarTabla((ArrayList<Cliente>) respuesta.getRespuesta());	
		
		
	}
	
	
	public void pintarTabla() {
		
		Object[][] datos = new Object[clientes != null?clientes.size():0][10];
		int i=0;
		
		try {
		
			for(Cliente cliente: clientes)
			{
				datos[i][0] = i+1;
				datos[i][1] = cliente.getIdentificador();
				datos[i][2] = cliente.getNombre();
				datos[i][3] = cliente.getApaterno();
				datos[i][4] = cliente.getAmaterno();
				datos[i][5] = cliente.getTelefono();
				datos[i][6]= cliente.getCorreo();
				datos[i][7] = cliente.getFechaNac();
				datos[i][8] = cliente.getCompras();
				datos[i][9] = cliente.getFechaRegistro();
				i++;
			}
		
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}		
		dtm = new DefaultTableModel(datos,columnNames);
		TClientes.setModel(dtm);
		ajustarTabla(TClientes);
	}
	
	public void buscar() {
		
		ControllerCliente controllerCliente = new ControllerCliente();
		respuesta = new Respuesta("",true,null);
		String nombre = TFBuscar.getText();
		
		if(TFBuscar.getText().trim() == "" || TFBuscar.getText().trim().isEmpty()) {
			nombre  ="";
		}
		
		respuesta = controllerCliente.proceso(Herramientas.tipoOperacion.seleccionar, nombre);
		
		if (!respuesta.getValor()) {
			JOptionPane.showMessageDialog(this, respuesta.getMensaje(),"Error", JOptionPane.ERROR_MESSAGE);
		}
		
		clientes = (ArrayList<Cliente>) respuesta.getRespuesta();
		pintarTabla();
		
	}
	
	public void ajustarTabla(JTable jTable) {}
	
	private void limpiarTablaProductos() {		
		try {
			while (TClientes.getRowCount() > 0) {
				dtm.removeRow(0);
	        }
		} catch (Exception  e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}

package Views;

import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

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
import Models.Components.JTableEdited;
import Views.Forms.FormProveedor;

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

public class JP_Proveedores extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField TFBuscar;
	private DefaultTableModel dtm;
	private JPopupMenu PMProveedor ;

	String[] columnNames = {"Id", "Nombre", "Ap. Paterno", "Ap. Materno",
			"Teléfono", "Correo", "Empresa", "Dirección","Productos","Notas","Fecha de Registro"};
	
	// Ejemplo de datos (puedes llenar con datos reales de tu aplicación)
    Object[][] datos = {      
    };
    
    private JTable TProveedores;
    
    
    //variables 
    Respuesta respuesta= new Respuesta();
    List<Proveedor> proveedores = new ArrayList<>();
    Proveedor proveedor = null;
    ControllerProveedor controllerProveedor = new ControllerProveedor();
    
	public JP_Proveedores() {
		setMinimumSize(new Dimension(892, 666));
		setMaximumSize(new Dimension(872, 644));
		setLayout(null);
		
		TProveedores = new JTableEdited();
		TProveedores.setRowHeight(25);
		TProveedores.setSelectionBackground(new Color(255, 0, 0));
		TProveedores.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		
		TProveedores.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TProveedores.setDefaultEditor(Object.class, null);
		dtm = new DefaultTableModel(datos, columnNames);
		TProveedores.setModel(dtm);
				
		JTableHeader header = TProveedores.getTableHeader();
		header.setDefaultRenderer(new CustomHeaderRenderer(2));
		TProveedores.setDefaultRenderer(Object.class, new CustomHeaderRenderer(2));
		
		JScrollPane scrollPane = new JScrollPane(TProveedores);
		scrollPane.setBackground(new Color(255, 255, 255));
        scrollPane.setBounds(10, 44, 864, 596);        
        this.add(scrollPane, BorderLayout.CENTER);
		
		PMProveedor = new JPopupMenu();
		
		JMenuItem visualizar = new JMenuItem("Visualizar");
		JMenuItem agregar = new JMenuItem("Agregar");
		JMenuItem editar = new JMenuItem("Editar");
		JMenuItem eliminar = new JMenuItem("Eliminar");
		
		PMProveedor.add(visualizar);
		PMProveedor.add(agregar);
		PMProveedor.add(editar);
		PMProveedor.add(eliminar);

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
		
		
		addPopup(TProveedores, PMProveedor);
		
		
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
		
		JLabel lblNewLabel = new JLabel("Nombre (Teléfono) :");
		lblNewLabel.setBounds(452, 13, 125, 16);
		add(lblNewLabel);
		
		JButton BAgregar = new JButton("Agregar");
		BAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				agregar();
			}
		});
		BAgregar.setBounds(12, 8, 117, 29);
		add(BAgregar);

		limpiarTablaProductos();
		buscar();
		
	}
	
	public void visualizar() {
		int indice = 0;		
		try {
			
			indice = TProveedores.getSelectedRow();		
			if (indice ==-1) {
				JOptionPane.showMessageDialog(this, "No hay una Fila Seleccionada","Advertencia",JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			String idProveedor = (String) TProveedores.getValueAt(indice, 0);			
			Proveedor pro = proveedores.stream()
					.filter(p -> p.getId_Proveedor() == idProveedor )
					.findFirst()
					.orElse(null);
			
			FormProveedor formProveedor = new FormProveedor(this, Herramientas.tipoOperacion.seleccionar, pro);
			formProveedor.setVisible(true);
			
		} catch (Exception e) {
			System.out.println("Error, No se puede obtener el valor : "+e.getMessage());
		}
		
	}
	
	public void agregar() {
		
		FormProveedor formProveedor = new FormProveedor(this, Herramientas.tipoOperacion.insertar,null);
		formProveedor.setVisible(true);
		
	}
	public void editar() {
		
		int indice = 0;
		Optional<Proveedor> pro = java.util.Optional.empty();
		
		try {
			
			indice = TProveedores.getSelectedRow();
			
			if (indice ==-1) {
				JOptionPane.showMessageDialog(this, "No hay una Fila Seleccionada","Advertencia",JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			String idProveedor = (String) TProveedores.getValueAt(indice, 0);			
			pro = proveedores.stream()
					.filter(p -> p.getId_Proveedor() == idProveedor )
					.findFirst();
			
		} catch (Exception e) {
			System.out.println("Error, No se puede obtener el valor : "+e.getMessage());
		}
		
		FormProveedor formProveedor = new FormProveedor(this, Herramientas.tipoOperacion.actualizar, pro.orElse(null));
		formProveedor.setVisible(true);
	}
	public void eliminar() {
		
		Optional<Proveedor> proveedor = java.util.Optional.empty();
		ControllerProveedor controllerProveedor = new ControllerProveedor();
		respuesta = new Respuesta("",true,null);
		
		try {
			
			int indice = TProveedores.getSelectedRow();
			
			if (indice == -1) {
				JOptionPane.showMessageDialog(this, "No hay una fila Seleccionada","Advertencia", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			String idProveedor = (String) TProveedores.getValueAt(indice, 0);
			
			proveedor = proveedores.stream()
					.filter(p -> p.getId_Proveedor().equals(idProveedor))
					.findFirst();
			
			if (proveedor.orElse(null) ==null) {
				JOptionPane.showMessageDialog(this, "Proveedor No Encontrado","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			Proveedor p = proveedor.get();
			
			int continuar = JOptionPane.showConfirmDialog(this,
					"¿Estás seguro que deseas Eliminar al Proveedor "+p.getId_Proveedor()+"?",
					"Confirmación",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE);
						
			if (continuar == JOptionPane.NO_OPTION || continuar == JOptionPane.CLOSED_OPTION ) {
				return;
			}
			
			respuesta = controllerProveedor.proceso(Herramientas.tipoOperacion.eliminar, p.getId_Proveedor());
			JOptionPane.showMessageDialog(this, respuesta.getMensaje(),"Mensaje",JOptionPane.INFORMATION_MESSAGE);			
			buscar();
			
		} catch (Exception e) {
			// TODO: handle exception
		}		
		
	}
	
	public void buscar() {
		
		ControllerProveedor controllerProveedor = new ControllerProveedor();
		respuesta = new Respuesta("",true,null);
		String nombre = TFBuscar.getText();
		
		if (TFBuscar.getText().trim() == "" || TFBuscar.getText().trim().isEmpty()) {
			nombre = null;
		}
		
		respuesta = controllerProveedor.proceso(Herramientas.tipoOperacion.seleccionar, nombre);
		
		if (!respuesta.getValor()) {
			JOptionPane.showMessageDialog(this, respuesta.getMensaje(),"Error",JOptionPane.ERROR_MESSAGE);
		}
		
		proveedores = (ArrayList<Proveedor>) respuesta.getRespuesta();
		pintarTabla();
				
	}
	
	public void pintarTabla() {
		
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
	
	public void ajustarTabla(JTable tabla) {
		
		TableColumn columna;
	    
	    for (int i=0; i<tabla.getColumnCount(); i++) {
	    	columna=tabla.getColumnModel().getColumn(i);
		    columna.setPreferredWidth(120);
		    columna.setMaxWidth(250);
		    columna.setMinWidth(120);
	    }
	    TProveedores.setRowHeight(22);
	    
		
	}
	
	private void limpiarTablaProductos() {		
		try {
			while (TProveedores.getRowCount() > 0) {
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

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
import HerramientasConexion.Herramientas.tipoOperacion;
import Models.ProductoBusquedaView;
import Models.Respuesta;
import Models.Components.CustomHeaderRenderer;
import Utileria.ComponentesDesing;
import Views.Forms.FormUsuarios;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
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

public class JP_Usuarios extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField TFBuscar;
	private DefaultTableModel dtm;
	private JPopupMenu PMUsuarios ;

	String[] columnNames = {"Id", "Usuario", "Nombre",
			"Ap. Paterno", "Ap. Materno", "Correo", "Dirección","Puesto", "Teléfono", "Fecha Registro"};
	
	// Ejemplo de datos (puedes llenar con datos reales de tu aplicación)
    Object[][] datos = {
    		{1, "Damian", "Damian","Ramirez", "Sanchez","1 PRIVADA DE SEPTIEMBRE","Programador","9514134591","fecha"},
    		{1, "Damian", "Damian","Ramirez", "Sanchez","1 PRIVADA DE SEPTIEMBRE","Programador","9514134591","fecha"}       
    };
    
    private JTable TUsuarios;
    
    
    //variables 
    
    List<Usuario> usuarios = new ArrayList<>();
    ControllerUsuario controllerUsuario = new ControllerUsuario();
    Respuesta respuesta = null;
    
	public JP_Usuarios() {
		setMinimumSize(new Dimension(892, 666));
		setMaximumSize(new Dimension(872, 644));
		setLayout(null);
		
		TUsuarios = new JTable();
		TUsuarios.setRowHeight(25);
		TUsuarios.setDefaultEditor(Object.class, null);
		TUsuarios.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TUsuarios.setDefaultEditor(Object.class, null);		
		dtm = new DefaultTableModel(datos, columnNames);		
		TUsuarios.setModel(dtm);
		
		TUsuarios.setModel(dtm);
		
		JScrollPane scrollPane = new JScrollPane(TUsuarios );
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBounds(12, 42, 850, 595);
		add(scrollPane);
		scrollPane.setViewportView(TUsuarios);
		
		JTableHeader header = TUsuarios.getTableHeader();
		header.setDefaultRenderer(new CustomHeaderRenderer(2));
		TUsuarios.setDefaultRenderer(Object.class, new CustomHeaderRenderer(2));
		
		
		PMUsuarios = new JPopupMenu();
		
		JMenuItem visualizar = new JMenuItem("Visualizar");
		JMenuItem agregar = new JMenuItem("Agregar");
		JMenuItem editar = new JMenuItem("Editar");
		JMenuItem eliminar = new JMenuItem("Eliminar");
		
		PMUsuarios.add(visualizar);
		PMUsuarios.add(agregar);
		PMUsuarios.add(editar);
		PMUsuarios.add(eliminar);
		addPopup(TUsuarios, PMUsuarios);
		
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
				buscarUsuario();
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
		BAgregar.setBounds(6, 6, 117, 29);
		add(BAgregar);
		
		JLabel lblNewLabel = new JLabel("Nombre (Teléfono):");
		lblNewLabel.setBounds(446, 13, 121, 16);
		add(lblNewLabel);

		limpiarTablaProductos();
		iniciarPantalla();
		
	}
	
	public void visualizar() {
		Usuario usuarioEditar = null;
		
		try {
			
			int indice = TUsuarios.getSelectedRow();
			
			if(indice == -1){
				JOptionPane.showMessageDialog(this,"No hay una fila seleccionada ","Advertencia",JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			int  idUsuario =(int) TUsuarios.getValueAt(indice, 0);						
			
			Usuario usuario = usuarios.stream()
					.filter(u -> u.getId_Usuario() == idUsuario)
					.findFirst()
					.orElse(null);
			
			System.out.println("Usuario encontrado "+usuario.getNombre());
			FormUsuarios formUsuarios = new FormUsuarios(this,Herramientas.tipoOperacion.seleccionar, usuario);
			formUsuarios.setVisible(true);
						
		} catch (Exception e) {
			System.out.println("Error, No se pudo obtener el valor : "+e.getMessage());
		}
		
	}
	
	public void agregar() {		
		FormUsuarios formUsuarios = new FormUsuarios(this,Herramientas.tipoOperacion.insertar, null);
		formUsuarios.setVisible(true);		
	}
	
	public void eliminar() {
		
		Usuario usuarioEditar = null;
		Optional<Usuario> usuario = java.util.Optional.empty();
		ControllerUsuario controllerUsuario = new ControllerUsuario();
		respuesta = new Respuesta("",true,null);
		
		try {
			int indice = TUsuarios.getSelectedRow();
			
			if(indice == -1){
				JOptionPane.showMessageDialog(this,"No hay una fila seleccionada ","Advertencia",JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			int  idUsuario =(int) TUsuarios.getValueAt(indice, 0);						
			
			usuario = usuarios.stream()
					.filter(u -> u.getId_Usuario() == idUsuario)
					.findFirst();
			
			if (usuario.orElse(null)== null) {
				JOptionPane.showMessageDialog(this,"Usuario No Encontrado","Error" ,JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			Usuario u = usuario.get();
			
			int continuar = JOptionPane.showConfirmDialog(this, 
                    "¿Estás seguro que deseas Eliminar al Usuario "+ u.getUsuario()+" ?", 
                    "Confirmación", 
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.QUESTION_MESSAGE);

			if (continuar == JOptionPane.NO_OPTION || continuar == JOptionPane.CLOSED_OPTION ) {
				return;
			}
			System.out.println("Id"+u.getId_Usuario());
			respuesta = controllerUsuario.proceso(Herramientas.tipoOperacion.eliminar, u.getId_Usuario() );
			
			JOptionPane.showMessageDialog(this,respuesta.getMensaje(),"Mensaje", JOptionPane.INFORMATION_MESSAGE);
			
			iniciarPantalla();
			
		} catch (Exception e) {
			System.out.println("Error, No se pudo obtener el valor : "+e.getMessage());
		}
		
	}
		
	public void editar() {
		
		Usuario usuarioEditar = null;
		Optional<Usuario> usuario = java.util.Optional.empty();
		
		try {
			
			int indice = TUsuarios.getSelectedRow();
			
			if(indice == -1){
				JOptionPane.showMessageDialog(this,"No hay una fila seleccionada ","Advertencia",JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			int  idUsuario =(int) TUsuarios.getValueAt(indice, 0);						
			
			usuario = usuarios.stream()
					.filter(u -> u.getId_Usuario() == idUsuario)
					.findFirst();
						
		} catch (Exception e) {
			System.out.println("Error, No se pudo obtener el valor : "+e.getMessage());
		}
		
		FormUsuarios formUsuarios = new FormUsuarios(this,Herramientas.tipoOperacion.actualizar, usuario.orElse(null));
		formUsuarios.setVisible(true);
	}
	
	public void buscarUsuario() {
		
		ControllerUsuario controllerUsuario = new ControllerUsuario();
		respuesta = new Respuesta("",true,null);
		String nombre = TFBuscar.getText();
		
		if (TFBuscar.getText().trim() =="" || TFBuscar.getText().trim().isEmpty() ) {
			nombre = null;
		}
		
		respuesta = controllerUsuario.proceso(Herramientas.tipoOperacion.seleccionar, nombre);
		
		if(!respuesta.getValor()) {
			JOptionPane.showMessageDialog(this, respuesta.getMensaje(),"Error",JOptionPane.ERROR_MESSAGE);
		}
		
		 usuarios = (ArrayList<Usuario>) respuesta.getRespuesta();
		
		pintarTabla();
		
	}
	
	
	public void iniciarPantalla() {	
		buscarUsuario();			
	}
	
	public void pintarTabla() {
		
		Object[][] datos = new Object[usuarios != null?usuarios.size():0][11];
		int i=0;
		
		try {
		
			for(Usuario usuario: usuarios)
			{
				datos[i][0] = usuario.getId_Usuario();
				datos[i][1] = usuario.getUsuario();
				datos[i][2] = usuario.getNombre();
				datos[i][3] = usuario.getApaterno();
				datos[i][4]= usuario.getAmaterno();
				datos[i][5] = usuario.getCorreo();
				datos[i][6] = usuario.getDireccion();
				datos[i][7] = usuario.getPuesto();
				datos[i][8] = usuario.getTelefono();
				datos[i][9] = usuario.getFechaRegistro();
				i++;
			}
		
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}		
		dtm = new DefaultTableModel(datos,columnNames);
		TUsuarios.setModel(dtm);
		ajustarTabla(TUsuarios);
		ComponentesDesing.PreferredWithTableTableUsuarios(TUsuarios);
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

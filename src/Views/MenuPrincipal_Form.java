package Views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controllers.ControllerUsuario;
import DAO.ModelsDAO.Usuario;
import HerramientasConexion.Herramientas;
import Models.Empresa;
import Models.Respuesta;
import Models.Sesion;
import Services.AperturaCajaService;
import Services.EmpresaService;
import Services.UsuarioService;
import Views.Forms.JP_Clientes;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Panel;
import java.awt.SystemColor;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.ComponentOrientation;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class MenuPrincipal_Form extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JPanel JP_Principal;
	private JPopupMenu PMProductos;
	private JLabel LblNombreUsuario;	
	private int idUsuario;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal_Form frame = new MenuPrincipal_Form(1);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public MenuPrincipal_Form(int idUsuario) {
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel.setBounds(6, 6, 356, 666);
		panel.setBackground(new Color(66, 66, 66));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(51, 51, 51));
		panel_1.setBounds(6, 6, 344, 189);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		LblNombreUsuario = new JLabel("Bienvenido Usuario");
		LblNombreUsuario.setBounds(6, 135, 332, 24);
		panel_1.add(LblNombreUsuario);
		
		LblNombreUsuario.setForeground(new Color(254, 255, 255));
		LblNombreUsuario.setFont(new Font("Arial", Font.BOLD, 20));
		LblNombreUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(MenuPrincipal_Form.class.getResource("/Recursos/avatar.png")));
		lblNewLabel.setBounds(74, 6, 196, 128);
		panel_1.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(39, 39, 39));
		panel_2.setBounds(6, 207, 344, 232);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JButton BtnESMercancia = new JButton("Entradas y Salidas Mercancía");
		BtnESMercancia.setBounds(6, 104, 332, 23);
		panel_2.add(BtnESMercancia);
		BtnESMercancia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JP_EntradaSalidaMercancia jp_EntradaSalidaMercancia = new JP_EntradaSalidaMercancia();
				Presentar(JP_Principal,jp_EntradaSalidaMercancia);
			}
		});
		BtnESMercancia.setForeground(Color.WHITE);
		BtnESMercancia.setFont(new Font("Dialog", Font.PLAIN, 12));
		BtnESMercancia.setFocusPainted(false);
		BtnESMercancia.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
		BtnESMercancia.setBackground(new Color(66, 77, 81));
		
		JButton BtnHistoricoVenta = new JButton("Historico de Venta");
		BtnHistoricoVenta.setBounds(6, 65, 332, 23);
		panel_2.add(BtnHistoricoVenta);
		BtnHistoricoVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JP_Ventas jp_ventas = new JP_Ventas();
				Presentar(JP_Principal,jp_ventas);
			}
		});
		BtnHistoricoVenta.setForeground(Color.WHITE);
		BtnHistoricoVenta.setFont(new Font("Dialog", Font.PLAIN, 12));
		BtnHistoricoVenta.setFocusPainted(false);
		BtnHistoricoVenta.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
		BtnHistoricoVenta.setBackground(new Color(66, 77, 81));
		
		JButton btnVenta = new JButton("Venta");
		btnVenta.setBounds(6, 24, 332, 29);
		panel_2.add(btnVenta);
		btnVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!validarAperturaCaja())
					return;
				JF_Venta jf_Venta = new JF_Venta();
				jf_Venta.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				jf_Venta.setVisible(true);
			}
		});
		btnVenta.setForeground(Color.WHITE);
		btnVenta.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnVenta.setFocusPainted(false);
		btnVenta.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
		btnVenta.setBackground(new Color(66, 77, 81));
		
		JButton BESEfectivo = new JButton("Entradas y Salidas de Efectivo");
		BESEfectivo.setBounds(6, 148, 332, 23);
		panel_2.add(BESEfectivo);
		BESEfectivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JP_EntradaSalidaEfectivo jp_entradaSalidaEfectivo = new JP_EntradaSalidaEfectivo();
				Presentar(JP_Principal,jp_entradaSalidaEfectivo);
			}
		});
		BESEfectivo.setForeground(Color.WHITE);
		BESEfectivo.setFont(new Font("Dialog", Font.PLAIN, 12));
		BESEfectivo.setFocusPainted(false);
		BESEfectivo.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
		BESEfectivo.setBackground(new Color(66, 77, 81));
		
		JButton BAdminCaja = new JButton("Administrador Caja");
		BAdminCaja.setBounds(6, 183, 332, 23);
		panel_2.add(BAdminCaja);
		BAdminCaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JP_AdministrarCaja administrarCaja = new JP_AdministrarCaja();
				Presentar(JP_Principal, administrarCaja);
			}
		});
		BAdminCaja.setForeground(Color.WHITE);
		BAdminCaja.setFont(new Font("Dialog", Font.PLAIN, 12));
		BAdminCaja.setFocusPainted(false);
		BAdminCaja.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
		BAdminCaja.setBackground(new Color(66, 77, 81));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(39, 39, 39));
		panel_3.setBounds(6, 451, 344, 209);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JButton BProductos = new JButton("Productos");
		BProductos.setBounds(6, 18, 332, 29);
		panel_3.add(BProductos);
		BProductos.setForeground(new Color(255, 255, 255));
		BProductos.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		BProductos.setBackground(new Color(66, 77, 81));
		BProductos.setFocusPainted(false); // Evita que se pinte el borde al obtener el foco
		BProductos.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
		
		JButton JBUsuarios = new JButton("Usuarios");
		JBUsuarios.setBounds(6, 59, 332, 23);
		panel_3.add(JBUsuarios);
		JBUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JP_Usuarios jp_Usuarios = new JP_Usuarios();
				Presentar(JP_Principal,jp_Usuarios);
			}
		});
		JBUsuarios.setForeground(new Color(255, 255, 255));
		JBUsuarios.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		JBUsuarios.setBackground(new Color(66, 77, 81));
		JBUsuarios.setFocusPainted(false); // Evita que se pinte el borde al obtener el foco
		JBUsuarios.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
		
		JButton btnProveedores = new JButton("Proveedores");
		btnProveedores.setBounds(6, 94, 332, 23);
		panel_3.add(btnProveedores);
		btnProveedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JP_Proveedores jp_Proveedores = new JP_Proveedores();
				Presentar(JP_Principal, jp_Proveedores);
			}
		});
		btnProveedores.setForeground(Color.WHITE);
		btnProveedores.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnProveedores.setFocusPainted(false);
		btnProveedores.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
		btnProveedores.setBackground(new Color(66, 77, 81));
		
		JButton btnClientes = new JButton("Clientes");
		btnClientes.setBounds(6, 129, 332, 23);
		panel_3.add(btnClientes);
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JP_Clientes jp_Clientes = new JP_Clientes();
				Presentar(JP_Principal, jp_Clientes);
			}
		});
		btnClientes.setForeground(Color.WHITE);
		btnClientes.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnClientes.setFocusPainted(false);
		btnClientes.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
		btnClientes.setBackground(new Color(66, 77, 81));
		
		JButton BAdministracion = new JButton("Administracion");
		BAdministracion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JP_Administracion jp_Administracion = new JP_Administracion();
				Presentar(JP_Principal, jp_Administracion);
			}
		});
		BAdministracion.setForeground(Color.WHITE);
		BAdministracion.setFont(new Font("Dialog", Font.PLAIN, 12));
		BAdministracion.setFocusPainted(false);
		BAdministracion.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
		BAdministracion.setBackground(new Color(66, 77, 81));
		BAdministracion.setBounds(6, 164, 332, 23);
		panel_3.add(BAdministracion);
		
		BProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JP_Productos jp_productos = new JP_Productos();
				Presentar(JP_Principal,jp_productos);
				
			}
		});
		
		JP_Principal = new JPanel();
		JP_Principal.setBackground(new Color(66, 66, 66));
		JP_Principal.setBounds(372, 6, 892, 666);
		contentPane.add(JP_Principal);
		this.idUsuario = idUsuario;
		mostrarPanel();
		sesion();
	}
	
	public void sesion() {
		
		UsuarioService usuarioService = new UsuarioService();
		EmpresaService empresaService = new EmpresaService();
		
		Respuesta respuesta =  usuarioService.obtenerUsuarioId(idUsuario);
		Usuario usuario = (Usuario) respuesta.getRespuesta();
		respuesta = empresaService.obtenerEmpresa();
		Empresa empresa = (Empresa) respuesta.getRespuesta();

		Sesion sesion = Sesion.obtenerInstancia();
		sesion.setEmpresa(empresa);
		sesion.setUsuario(usuario);
		
		LblNombreUsuario.setText("Bienvenido "+usuario.getNombre()+" "+usuario.getApaterno());
		
	}
	
	
	public Boolean validarAperturaCaja() {
		
		
		AperturaCajaService aperturacajaservice = new AperturaCajaService();
		Respuesta respuesta = new Respuesta();
		
		respuesta = aperturacajaservice.verificarCajaAbierta();
		
		if(!respuesta.getValor())
		{
	        JOptionPane.showMessageDialog(null,respuesta.getMensaje(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		if (respuesta.getRespuesta() == null) {
			JOptionPane.showMessageDialog(this,"La Caja Aún No ha sido Aperturada.", "Advertencia",JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		return true;
		
	}
	
	public void mostrarPanel() {
		JP_Productos jp_productos = new JP_Productos();
		jp_productos.setLocation(0,0);
		jp_productos.setPreferredSize(new Dimension(880,642));
		
		JP_Principal.removeAll();
		JP_Principal.add(jp_productos);
		
		JP_Principal.validate();
		JP_Principal.repaint();
	}
	
	public void Presentar(JPanel J_Panel_Principal, JPanel panel) {
		
		panel.setLocation(0,0);
		panel.setPreferredSize(new Dimension(880,642));
		
		J_Panel_Principal.removeAll();
		J_Panel_Principal.add(panel);
		J_Panel_Principal.validate();
		J_Panel_Principal.repaint();

		
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

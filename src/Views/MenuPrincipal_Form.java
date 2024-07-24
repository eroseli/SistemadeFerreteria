package Views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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

public class MenuPrincipal_Form extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JPanel JP_Principal;
	private JPopupMenu PMProductos;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal_Form frame = new MenuPrincipal_Form();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuPrincipal_Form() {
		
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
		
		JLabel lblNewLabel = new JLabel("Bienvenido Usuario");
		
		lblNewLabel.setForeground(new Color(254, 255, 255));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(6, 21, 344, 64);
		panel.add(lblNewLabel);
		
		JButton BProductos = new JButton("Productos");
		BProductos.setForeground(new Color(255, 255, 255));
		BProductos.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		BProductos.setBackground(new Color(66, 77, 81));
		BProductos.setBounds(32, 152, 210, 23);
		BProductos.setFocusPainted(false); // Evita que se pinte el borde al obtener el foco
		BProductos.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15)); // Crea un borde vacío

		
		BProductos.setBounds(32, 91, 210, 29);
		panel.add(BProductos);
		
		JButton JBUsuarios = new JButton("Usuarios");
		JBUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JP_Usuarios jp_Usuarios = new JP_Usuarios();
				Presentar(JP_Principal,jp_Usuarios);
			}
		});
		JBUsuarios.setForeground(new Color(255, 255, 255));
		JBUsuarios.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		JBUsuarios.setBackground(new Color(66, 77, 81));
		JBUsuarios.setBounds(32, 152, 210, 23);
		JBUsuarios.setFocusPainted(false); // Evita que se pinte el borde al obtener el foco
		JBUsuarios.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15)); // Crea un borde vacío

		panel.add(JBUsuarios);
		
		JP_Principal = new JPanel();
		JP_Principal.setBackground(new Color(66, 66, 66));
		JP_Principal.setBounds(372, 6, 892, 666);
		contentPane.add(JP_Principal);
		
		BProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JP_Productos jp_productos = new JP_Productos();
				Presentar(JP_Principal,jp_productos);
				
			}
		});

		mostrarPanel();
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

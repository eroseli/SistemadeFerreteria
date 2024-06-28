package Views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Panel;

public class MenuPrincipal_Form extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

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
		
		Button button = new Button("Productos");
		
		button.setBounds(125, 91, 117, 29);
		panel.add(button);
		
		JPanel JP_Principal = new JPanel();
		JP_Principal.setBackground(new Color(66, 66, 66));
		JP_Principal.setBounds(374, 6, 900, 666);
		contentPane.add(JP_Principal);
		
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JP_Productos jp_productos = new JP_Productos();
				Presentar(JP_Principal,jp_productos);
				
				
			}
		});
		
		
		//Contruccion de la pantalla
		JP_Entrada jp_Entrada = new JP_Entrada();
		jp_Entrada.setLocation(0,0);	
		jp_Entrada.setPreferredSize(new Dimension(500,500));
		
		JP_Principal.removeAll();	
		JP_Principal.add(jp_Entrada,BorderLayout.CENTER);	
		JP_Principal.revalidate();	
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
}
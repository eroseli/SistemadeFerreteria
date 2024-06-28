package Views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

public class tablap extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tablap frame = new tablap();
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
	public tablap() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 809, 517);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		//array bidimencional de objetos con los datos de la tabla
		 Object[][] data = {
		 {"Mary", "Campione", "Esquiar", new Integer(5), new Boolean(false)},
		 {"Lhucas", "Huml", "Patinar", new Integer(3), new Boolean(true)},
		 {"Kathya", "Walrath", "Escalar", new Integer(2), new Boolean(false)},
		 {"Marcus", "Andrews", "Correr", new Integer(7), new Boolean(true)},
		 {"Angela", "Lalth", "Nadar", new Integer(4), new Boolean(false)}
		 };
		 //array de String's con los títulos de las columnas
		 String[] columnNames = {"Nombre", "Apellido", "Pasatiempo",
		 "Años de Practica", "Soltero(a)"};
		 //se crea la Tabla
		 final JTable table = new JTable(data, columnNames);
		 table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		 //Creamos un JscrollPane y le agregamos la JTable
		 JScrollPane scrollPane = new JScrollPane(table);
		 //Agregamos el JScrollPane al contenedor
		 getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		table.setVisible(true);
		
		
	}

}

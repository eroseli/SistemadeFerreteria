package Views;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;

public class JP_Productos extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable JT_Productos;


	public void inicializarTablaProductos() {
		
		
		
	}
	
	public JP_Productos() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CRUD Productos");
		lblNewLabel.setBounds(295, 6, 232, 62);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		add(lblNewLabel);
		
		JT_Productos = new JTable();
		JT_Productos.setBounds(6, 80, 868, 504);
		add(JT_Productos);
		
		inicializarTablaProductos();

	}
}

package Views;

import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import java.awt.Component;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import Models.Components.CustomHeaderRenderer;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTable;

public class JP_Usuarios extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private DefaultTableModel dtm;

	String[] columnNames = {"ID", "Codigo", "Nombre", "Descripcion",
			"Cantidad", "Fecha Cad.", "P. Público", "P. Mayoreo",
			"P. Adquisición", "Existencia", "Categoría", "Marca","Fecha de Registro",};
	
	// Ejemplo de datos (puedes llenar con datos reales de tu aplicación)
    Object[][] datos = {
            {1, "ABC123", "Producto A", "Descripción del producto A",
                    10, "2024-12-31", 100.0, 80.0, 50.0, 20, "Electrónica", "Marca A",""},
            {2, "XYZ456", "Producto B", "Descripción del producto B",
                    5, "2023-10-15", 120.0, 100.0, 70.0, 15, "Ropa", "Marca B",""},
            {3, "XYZ456", "Producto C", "Descripción del producto B",
                        5, "2023-10-15", 120.0, 100.0, 70.0, 15, "Ropa", "Marca B",""},
            {4, "XYZ456", "Producto D", "Descripción del producto B",
                            5, "2023-10-15", 120.0, 100.0, 70.0, 15, "Ropa", "Marca B",""}
    };
    private JTable TUsuarios;
    
	public JP_Usuarios() {
		setMinimumSize(new Dimension(892, 666));
		setMaximumSize(new Dimension(872, 644));
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBounds(12, 42, 850, 595);
		add(scrollPane);
		
		TUsuarios = new JTable();
		dtm = new DefaultTableModel(datos, columnNames);
		TUsuarios.setModel(dtm);
		scrollPane.setViewportView(TUsuarios);
		
		JTableHeader header = TUsuarios.getTableHeader();
		header.setDefaultRenderer(new CustomHeaderRenderer(1));
		TUsuarios.setDefaultRenderer(Object.class, new CustomHeaderRenderer(1));
		
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(623, 11, 156, 20);
		add(textField);
		
		JButton BBuscar = new JButton("Buscar");
		BBuscar.setToolTipText("Buscar productos con aplicación de filtros");
		BBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		BBuscar.setBounds(789, 11, 73, 20);
		add(BBuscar);

	}
}

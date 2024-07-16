package Views;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import DAO.ModelsDAO.Producto;
import Models.Components.JTableEdidata;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class JP_Productos extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable JT_Productos;

	
	//Variables
	private DAO.ProductosDAO productosDAO = new DAO.ProductosDAO();
	private ArrayList<Producto> productos = new ArrayList<Producto>();
	private Cursor cursor;
	String[] columnNames = {"ID", "Codigo", "Nombre", "Descripcion",
			"Cantidad", "Fecha Cad.", "P. Público", "P. Mayoreo",
			"P. Adquisición", "Existencia", "Categoría", "Marca",};
	
	// Ejemplo de datos (puedes llenar con datos reales de tu aplicación)
    Object[][] datos = {
            {1, "ABC123", "Producto A", "Descripción del producto A",
                    10, "2024-12-31", 100.0, 80.0, 50.0, 20, "Electrónica", "Marca A"},
            {2, "XYZ456", "Producto B", "Descripción del producto B",
                    5, "2023-10-15", 120.0, 100.0, 70.0, 15, "Ropa", "Marca B"}
    };
    private JTextField textField;
	
	public JP_Productos() {
		setBackground(new Color(255, 255, 255));
		setMinimumSize(new Dimension(892, 666));
		setMaximumSize(new Dimension(892, 666));
		setLayout(null);
	    
		JT_Productos = new JTableEdidata();
		JT_Productos.setBounds(10, 63, 880, 504);
		JT_Productos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JT_Productos.setDefaultEditor(Object.class, null);
		JT_Productos.setAutoCreateRowSorter(true);
		JT_Productos.isCellEditable(0,0);
		
		DefaultTableModel dtm = new DefaultTableModel(datos, columnNames);

		JT_Productos = new JTable(dtm);
		JT_Productos.setFont(new Font("Arial", Font.PLAIN, 12));
		JT_Productos.setBackground(new Color(229, 247, 246));
		JT_Productos.setAutoCreateRowSorter(true);

        JScrollPane scrollPane = new JScrollPane(JT_Productos);
        scrollPane.setBounds(10, 44, 850, 595);
        this.add(scrollPane, BorderLayout.CENTER);
        
        JButton BBuscar = new JButton("Buscar");
        BBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        BBuscar.setBounds(771, 13, 89, 20);
        add(BBuscar);
        
        JDateChooser dateChooser_1 = new JDateChooser();
        dateChooser_1.setMaximumSize(new Dimension(140, 28));
        dateChooser_1.setMinimumSize(new Dimension(140, 28));
        dateChooser_1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        dateChooser_1.setBounds(471, 13, 140, 20);
        add(dateChooser_1);
        
        JDateChooser dateChooser_1_1 = new JDateChooser();
        dateChooser_1_1.setMinimumSize(new Dimension(140, 28));
        dateChooser_1_1.setMaximumSize(new Dimension(140, 28));
        dateChooser_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        dateChooser_1_1.setBounds(321, 13, 140, 20);
        add(dateChooser_1_1);
        
        textField = new JTextField();
        textField.setBounds(621, 13, 140, 20);
        add(textField);
        textField.setColumns(10);
        
        JComboBox CBBuscar = new JComboBox();
        CBBuscar.setModel(new DefaultComboBoxModel(new String[] {"Buscar", "Fecha de Registro", "Stock Positivo", "Stock Negativo", "A - Z", "Z - A"}));
        CBBuscar.setBounds(171, 11, 140, 22);
        add(CBBuscar);
        
        JButton BReporte = new JButton("Reporte");
        BReporte.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        BReporte.setBounds(72, 11, 89, 23);
        add(BReporte);
		
			/*cursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
        setCursor(cursor);		
        InicializarPantalla();
		cursor = Cursor.getDefaultCursor();
		setCursor(cursor);
		*/
	}
	
	public void InicializarPantalla() {
		
		productos = (ArrayList<Producto>) productosDAO.obtenerProductos().getRespuesta();
		Object[][] datos = new Object[productos.size()][12];
		
		int i=0;
		
		for(String o: columnNames) {
		System.out.println(o);
		}
		
		try {
		
		for(Producto producto: productos)
		{
		datos[i][0] = producto.getId_producto();
		datos[i][1] = producto.getCodigo();
		datos[i][2] = producto.getNombre();
		datos[i][3] = producto.getDescripcion();
		datos[i][4] = producto.getCantidad();
		datos[i][5] = producto.getFecha_caducidad();
		datos[i][6] = producto.getP_publico();
		datos[i][7] = producto.getP_Mayoreo();
		datos[i][8] = producto.getP_Adquisicion();
		datos[i][9] = producto.getExistencia();
		datos[i][10] = producto.getCategoria();
		datos[i][11] = producto.getMarca();
		i++;
		}
		
		} catch (NullPointerException e) {
		// TODO: handle exception
		System.out.println(e.getMessage());
		}		
		
		DefaultTableModel dtm = new DefaultTableModel(datos,columnNames);
		JT_Productos.setModel(dtm);
		
	}
}

package Views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class prueb extends JFrame{

	
	 public prueb() {

	        String[] columnNames = {"Nombre", "Años", "Apto",};
	        Object[][] datos = {
	            {"Juan", 25, false},
	            {"Sonia", 33, true},
	            {"Pedro", 42, false}};

	        DefaultTableModel dtm = new DefaultTableModel(datos, columnNames);
	        final JTable table = new JTable(dtm);

	        // Agregar nueva columna
	        String[] columnaNueva1 = {"vago", "diestro", "normal",};
	        dtm.addColumn("Tipo", columnaNueva1);

	        // Agregar nueva fila
	        Object[] newRow = {"Maria", 55, false};
	        dtm.addRow(newRow);

	        // Modificar celda especifica
	        dtm.setValueAt("XXX", 3, 3); // Row/Col

	        table.setPreferredScrollableViewportSize(new Dimension(250, 100));
	        JScrollPane scrollPane = new JScrollPane(table);
	        getContentPane().add(scrollPane, BorderLayout.CENTER);       
	        addWindowListener(new WindowAdapter() {           
	            public void windowClosing(WindowEvent e) {
	                System.exit(0);               
	            }
	        });
	       
	    }

	    public static void main(String[] args) {
	        prueb frame = new prueb();
	        frame.pack();
	        frame.setVisible(true);
	    }
	
}

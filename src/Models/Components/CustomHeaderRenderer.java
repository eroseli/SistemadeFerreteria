package Models.Components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class CustomHeaderRenderer extends DefaultTableCellRenderer {

//    public CustomHeaderRenderer() {
//        setHorizontalAlignment(CENTER); // Alinear el texto al centro del encabezado
//        setOpaque(true); // Hacer que el fondo sea opaco para mostrar el color
//        setForeground(Color.cyan); // Color de texto en el encabezado
//        setBackground(Color.BLUE); // Color de fondo del encabezado
//        setFont(new Font("Arial", Font.BOLD, 14)); // Tipo de letra, negrita, tamaño 14
//    }
//
//    @Override
//    public Component getTableCellRendererComponent(JTable table, Object value,
//            boolean isSelected, boolean hasFocus, int row, int column) {
//        System.err.println("Entro "+row);
//        // Llamar al método de la superclase para configurar la apariencia básica
//        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//       
//        if (row%2==0) {
//			setBackground(new Color(191, 201, 202));
//			System.out.println("Entro"+row+" aqui");
//		}else {
//			setBackground(Color.BLACK);
//		}
//           
//        // Devolver el componente configurado
//        return this;
//    }
		private static final Color HEADER_BACKGROUND = Color.BLUE; // Color de fondo del encabezado
	    private static final Color HEADER_FOREGROUND = Color.WHITE; // Color del texto del encabezado
	    private static final Color ROW_COLOR1 = new Color(240, 240, 240); // Color para filas pares
	    private static final Color ROW_COLOR2 = new Color(220, 220, 220); // Color para filas impares

	    public CustomHeaderRenderer() {
	        setOpaque(true); // Asegurarse de que el renderizador sea opaco
	        // Establecer el color de fondo y texto para el encabezado
	        setBackground(Color.black);
	        setForeground(HEADER_FOREGROUND);
	        setFont(new Font("Arial", Font.BOLD, 12)); // Fuente y estilo del texto del encabezado
	    }

	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value,
	                                                   boolean isSelected, boolean hasFocus, int row, int column) {
	        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

	        // Renderizado específico para el encabezado
	        JTableHeader header = table.getTableHeader();
	        if (this == header.getDefaultRenderer()) {
	            setText(value.toString()); // Establecer el texto del encabezado
	            return this;
	        }

	        // Alternar colores de fondo basados en el índice de la fila para las filas de datos
	        if (row % 2 == 0) {
	            setBackground(ROW_COLOR1); // Color para filas pares
	        } else {
	            setBackground(ROW_COLOR2); // Color para filas impares
	        }

	        return this;
	    }
}
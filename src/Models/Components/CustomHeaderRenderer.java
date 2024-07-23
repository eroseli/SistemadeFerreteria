package Models.Components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

public class CustomHeaderRenderer extends DefaultTableCellRenderer {

		private static final Color HEADER_BACKGROUND = new Color(65, 65, 65); // Color de fondo del encabezado
	    private static final Color HEADER_FOREGROUND = new Color(238, 238, 238); // Color del texto del encabezado
	    private static final Color ROW_COLOR1 = new Color(255, 255, 255); // Color para filas pares
	    private static final Color ROW_COLOR2 = new Color(245, 253, 254); // Color para filas impares
	    private int tipoTabla =0;
	    
	    public CustomHeaderRenderer(int tipoTabla) {
	    	this.tipoTabla = tipoTabla;
	        setOpaque(true); // Asegurarse de que el renderizador sea opaco
	        // Establecer el color de fondo y texto para el encabezado
	        setBackground(HEADER_BACKGROUND);
	        setForeground(HEADER_FOREGROUND);
	        setFont(new Font("Segoe UI", Font.ITALIC, 9)); // Fuente y estilo del texto del encabezado
	    }

	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value,
	                                                   boolean isSelected, boolean hasFocus, int row, int column) {
	        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	        table.setGridColor(Color.white); // Cambia el color de las líneas de la cuadrícula      
	        // Renderizado específico para el encabezado
	        JTableHeader header = table.getTableHeader();
	        
	        if (this == header.getDefaultRenderer()) {
	            setText(value.toString()); // Establecer el texto del encabezado
	            return this;
	        }

	        switch (tipoTabla) {
			case 1: //Tabla de productos
				
				if(column == 9) {
		        	
		        	int existencia = Integer.parseInt(value.toString());
		        	
		        	// Aplicar colores según el valor de la existencia
		            if (existencia > 3) {
		                setBackground(new Color(207, 238, 218)); // Verde suave
		            } else if (existencia >= 1) {
		                setBackground(new Color(255, 255, 204)); // Amarillo suave
		            } else if (existencia == 0) {
		                setBackground(new Color(255, 204, 204)); // Rojo suave
		            } else {
		                // Restaurar el color de fondo por defecto para otras celdas
		                setBackground(new Color(49, 49, 49));
		                setForeground(new Color(249, 232, 232));
		            }
		        }else {

		        	// Alternar colores de fondo basados en el índice de la fila para las filas de datos
				    if (row % 2 == 0) {
				    	setBackground(ROW_COLOR1); // Color para filas pares
				        setForeground(new Color(86, 89, 90));
				    } else {
				        setBackground(ROW_COLOR2); // Color para filas impares
				        setForeground(new Color(86, 89, 90));
				    }

		        }
				
				break;

			default:
				break;
			}
	        
		        

		    
	        if(isSelected) {
	        	setBackground(new Color(34, 110, 243)); // Color para filas pares
	            setForeground(new Color(249, 252, 254));
	        }
	        
	        return cellComponent;
	    }
}
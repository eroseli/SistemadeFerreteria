package Models.Components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.AbstractMap;

import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import HerramientasConexion.Configuracion;
import HerramientasConexion.Herramientas;

public class CustomHeaderRenderer extends DefaultTableCellRenderer {

		private static final Color HEADER_BACKGROUND = new Color(65, 65, 65); // Color de fondo del encabezado
	    private static final Color HEADER_FOREGROUND = new Color(238, 238, 238); // Color del texto del encabezado
	    private static final Color ROW_COLOR1 = new Color(255, 255, 255); // Color para filas pares
	    private static final Color ROW_COLOR2 = new Color(245, 253, 254); // Color para filas impares
	    private int tipoTabla =0;

	    private Configuracion configuracion = null;

	    
	    public CustomHeaderRenderer(int tipoTabla) {
	    	this.tipoTabla = tipoTabla;
	        setOpaque(true); // Asegurarse de que el renderizador sea opaco
	        // Establecer el color de fondo y texto para el encabezado
	        setBackground(HEADER_BACKGROUND);
	        setForeground(HEADER_FOREGROUND);
	        //setBorder(new LineBorder(Color.white,1));
	        setFont(new Font("Segoe UI", Font.ITALIC, 12)); // Fuente y estilo del texto del encabezado
	    
	        String directorioTrabajo = System.getProperty("user.dir");
	        System.out.println("Directorio de trabajo: " + directorioTrabajo);
	        configuracion = Configuracion.getInstancia(Herramientas.config.dirConfig);
	        
	    }
	    
	    public AbstractMap.SimpleEntry<Color, Color> obtenerColor(int columnaValidar, String valor, int column, int row ) {
	    	
	    	if(column == columnaValidar) {	
	    		int existencia = Integer.parseInt(valor);
	    		
	        	// Aplicar colores según el valor de la existencia
	            if (existencia >= Integer.parseInt(configuracion.getEA())) {
	                return new AbstractMap.SimpleEntry<Color, Color> (Herramientas.colorsTable.verde, new Color(86, 89, 90) ); // Amarillo suave
	            } else if (existencia >= Integer.parseInt( configuracion.getEM())) {
	                return new AbstractMap.SimpleEntry<Color, Color> (Herramientas.colorsTable.amarillo, new Color(86, 89, 90) ); // Amarillo suave
	            } else if (existencia >= Integer.parseInt(configuracion.getEB())) {
	                return new AbstractMap.SimpleEntry<Color, Color> (Herramientas.colorsTable.rojo, new Color(86, 89, 90) ); // Amarillo suave
	            } else {
	                // Restaurar el color de fondo por defecto para otras celdas
	                return new AbstractMap.SimpleEntry<Color, Color>(new Color(49, 49, 49), new Color(249, 232, 232));
	            }
	        }else {

	        	// Alternar colores de fondo basados en el índice de la fila para las filas de datos
			    if (row % 2 == 0) {
			        setForeground(new Color(86, 89, 90));
	                return new AbstractMap.SimpleEntry<Color, Color>(ROW_COLOR1, new Color(86, 89, 90));

			    } else {
			        setForeground(new Color(86, 89, 90));
	                return new AbstractMap.SimpleEntry<Color, Color>(ROW_COLOR2, new Color(86, 89, 90));

			    }

	        }
	    	
	    }

	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value,
	                                                   boolean isSelected, boolean hasFocus, int row, int column) {
	        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	        table.setGridColor(Color.white); // Cambia el color de las líneas de la cuadrícula      
	        // Renderizado específico para el encabezado
	        JTableHeader header = table.getTableHeader();
	        AbstractMap.SimpleEntry<Color, Color> colores;
	        String valor = "";
	        
	        if (this == header.getDefaultRenderer()) {
	            setText(value.toString()); // Establecer el texto del encabezado
	            return this;
	        }
	        
//	        if (table.getTableHeader() != null) {
//				setBorder(new LineBorder(Color.white));
//			}

	        switch (tipoTabla) {
			case 1: //Tabla de productos	                	
			
	        	if (value != null) {
					valor = value.toString();
				}
	        
				colores = obtenerColor(9,valor, column, row);					 
		    	setBackground(colores.getKey());
		    	setForeground(colores.getValue());			
				break;

			case 2:
				// Alternar colores de fondo basados en el índice de la fila para las filas de datos
			    if (row % 2 == 0) {
			    	setBackground(ROW_COLOR1); // Color para filas pares
			        setForeground(new Color(86, 89, 90));
			    } else {
			        setBackground(ROW_COLOR2); // Color para filas impares
			        setForeground(new Color(86, 89, 90));
			    }
				
				break;
			case 3:
								
	        	if (value != null) {
					valor = value.toString();
				}
				
		        colores = obtenerColor(5,valor, column, row);					 
		    	setBackground(colores.getKey());
		    	setForeground(colores.getValue());			
				break;
			case 4:
		        colores = obtenerColor(11,value.toString(), column, row);					 
		    	setBackground(colores.getKey());
		    	setForeground(colores.getValue());			
				break;
			}
	        
		        

		    
	        if(isSelected) {
	        	setBackground(new Color(34, 110, 243)); // Color para filas pares
	            setForeground(new Color(249, 252, 254));
	        }
	        
	        return cellComponent;
	    }
}
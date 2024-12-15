package HerramientasConexion;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.TextField;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.sql.SQLData;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.toedter.calendar.JDateChooser;

import Utileria.ComboItem;

public class Herramientas {
	
	private Herramientas() {
        throw new UnsupportedOperationException("Esta es una clase de constantes y no puede ser instanciada");
    }
	
	public static final class TamCampos{
		public static final int nombres = 50;
		public static final int telefono = 10;
		public static final int direccion = 200;
		public static final int descripcionCorta = 100;
		public static final int descripcionLarga = 500;
		public static final int inventario = 6;
	}
	
	public static final class CombosConfiguracion{
		public static final int categoria = 1;
		public static final int marca = 2;
	}
	
	public static final class config{
		public static final String dirConfig = "src/Config.properties";
	}
	
	public static final class colors{

		public static final Color rojo = new Color(184, 0, 6 );
		public static final Color verde = new Color(0, 131, 28 );
		public static final Color amarillo = new Color(183, 149, 11);
		
	}
	
	public static final class colorsTable{
		
		public static final Color rojo =  new Color(250, 219, 216 );
		public static final Color amarillo =  new Color(252, 243, 207 );
		public static final Color verde =  new Color(169, 223, 191);
		
	}
	
	public static final class cadenas {
		
		public static final String CadenaSi = "Si";
		public static final String CadenaNo = "No";
		public static final String CadenaVacia = "";
	}
	
	public static final class tipoPago{
		public static final String Tarjeta = "T";
		public static final String Efectivo = "E";
		public static final String Mixto = "M";
	}
	
	public static final class tipoOperacion{
		
		public static final int insertar = 1;
		public static final int actualizar = 2;
		public static final int eliminar = 3;
		public static final int seleccionar = 4;
		
	}
	
	public static final class tipoButton{
		public static final int grabar = 1;
		public static final int cancelar = 2;
		public static final int eliminar = 3;
	}
	
	public static String formatoDinero(Float cantidad) {
		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("es","MX"));		
		return currencyFormatter.format(cantidad);
	}
	
	public static float quitarFormatoDinero( String cantidad) {
		
		Locale locale = new Locale("es", "MX");
		
		NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);

        try {
            // Analizar la cadena y convertirla a Number
            Number number = numberFormat.parse(cantidad);
            return number.floatValue();  // Convertir el Number a float
        } catch (ParseException e) {
            e.printStackTrace();
            // Manejo de error, retornar 0 o lanzar una excepción personalizada
            return 0.0f;  // Por defecto, retornar 0.0f si hay un error
        }
		
	}
	
	public static float eliminarFormatoDinero(String cantidad) {
        // Remover todos los caracteres que no sean dígitos o un punto decimal
        String cantidadLimpia = cantidad.replaceAll("[^\\d.]", "");
        
        try {
            // Convertir la cadena limpia a float
            return Float.parseFloat(cantidadLimpia);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            // Manejo de error, retornar 0 o lanzar una excepción personalizada
            return 0.0f;  // Por defecto, retornar 0.0f si hay un error
        }
    }
	
	public static boolean validarFlotante(String numero) {
		
		try {
			float flotante = Float.parseFloat(numero);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public static boolean validarEntero(String numero) {
		
		try {
			int entero = Integer.parseInt(numero);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
		

	public static Date convertirFecha(JDateChooser jDateChooser) {
		java.sql.Date sqlDate;
		try {
			java.util.Date utilDate = jDateChooser.getDate();
			sqlDate = new java.sql.Date(utilDate.getTime());
		} catch (Exception e) {
			return null;
		}
		return sqlDate;
	}
	
	public static boolean validarFecha(String fecha) {
		
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(fecha);
		try {
			java.util.Date fechaUtil = formato.parse(fecha);
			java.sql.Date  fechaSql = new java.sql.Date(fechaUtil.getTime());
			return true;
		} catch (Exception e) {
			System.out.println("Mensaje de error"+e.getMessage());
			return false;
		}
				
	}
	
	public static void validarMonto(KeyEvent arg0, JTextField TFMonto) {
		
		int indicePunto = TFMonto.getText().indexOf('.'); 
		
		try {
			
			if (!Character.isDigit(arg0.getKeyChar()) && arg0.getKeyChar() != '.' && arg0.getKeyChar() != KeyEvent.VK_BACK_SPACE) {	            
	            arg0.consume();
	            return;
			}
			
			if (arg0.getKeyChar()=='.' && TFMonto.getText().contains(String.valueOf( '.')) ) {
				arg0.consume();
				return;
			}
			
			if (indicePunto != -1) {
				String montoText = TFMonto.getText().substring(indicePunto+1);
				if (montoText.length()== 2) {
					arg0.consume();
					return;
				}
			}
			
			if(TFMonto.getText().length()==12)
			{
				arg0.consume();
				return;
			}
			
			
			
		}catch(Exception e) 
		{
			System.out.println("Error al prcesas la solicitud "+e.getMessage());
		}
	}
	
	public static String formatoDinero(float dinero) {
		
		String dineroF = "";		
		try {
	        NumberFormat formatoMX = NumberFormat.getCurrencyInstance(new Locale("es", "MX"));
			dineroF= formatoMX.format(dinero);
		} catch (Exception e) {
			System.out.println("Error al intentar convertir el formato en dinero : "+e.getMessage());
		}		
		return dineroF;		
	}
	
	public static String obtenerCadenaComboAutocompletado(JComboBox<ComboItem> combo,int tamanio) {
		JTextField textfield = (JTextField) combo.getEditor().getEditorComponent();
		String cadena =  textfield.getText(); 	
		
		if (cadena.length() >50) {
			cadena = (String) cadena.subSequence(0, tamanio);
		}
		
		return cadena;
		
	}
	
	
	public static String ComboAutocompletadoCadena(JComboBox combo) {
		
		String cadena = null;		
		try {		
			JTextField textfield = (JTextField) combo.getEditor().getEditorComponent();
			cadena = textfield.getText(); 
			
			System.out.println("Cadena"+cadena+"s");		
			if (cadena.isEmpty() || cadena == "" ) {
				return null;
			}
			
			for (int i = 0; i < combo.getItemCount(); i++) {
	            String item = (String) combo.getItemAt(i);  // Obtener el valor en la posición i
	            if (item.equals(cadena)) {
	                return cadena;  // El valor existe en el JComboBox
	            }
	        }
			
		} catch (Exception e) {
			System.out.println("Error al intentar obtener el valor del combo "+e.getMessage());
			return null;
		}
		return null;
	}
	
public static String ComboAutocompletadoTexto(JComboBox<ComboItem> combo) {
		
		String cadena = null;
		
		try {
			
			
			
			JTextField textfield = (JTextField) combo.getEditor().getEditorComponent();
			cadena = textfield.getText(); 
			
			System.out.println("Cadena"+cadena+"s");
			
			if (cadena.isEmpty() || cadena == "" ) {
				return null;
			}
		
		} catch (Exception e) {
			System.out.println("Error al intentar obtener el valor del combo "+e.getMessage());
			return null;
		}
		
		return cadena;
	}

	public static void validarTelefono(KeyEvent e, JTextField tx) {
		
		if (!Character.isDigit(e.getKeyChar()) ) {	            
            e.consume();
            return;
		}
		if(tx.getText().length()>=Herramientas.TamCampos.telefono)
			e.consume();
		
	}
	
	public static void tamanioCadena(KeyEvent e,JTextField tx ,int nc ) {
		
		char caracter = e.getKeyChar();
		
		if (esCaracterEspecial(caracter)) {
            e.consume(); 
            return;
        }
		
		if(tx.getText().length()>=nc)
			e.consume();
		
	}
	
	public static boolean esCaracterEspecial(char c) {
        String specialCharacters = "~`^|'\";/*\\";
        return specialCharacters.indexOf(c) >= 0;
    }	
	
}

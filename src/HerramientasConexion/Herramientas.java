package HerramientasConexion;
import java.sql.Date;
import java.sql.SQLData;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import com.toedter.calendar.JDateChooser;

public class Herramientas {

	
	private Herramientas() {
        throw new UnsupportedOperationException("Esta es una clase de constantes y no puede ser instanciada");
    }
	
	public static final class cadenas {
		
		public static final String CadenaSi = "Si";
		public static final String CadenaNo = "No";

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
	
	public static String formatoDinero(Float cantidad) {
		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();		
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
	
}

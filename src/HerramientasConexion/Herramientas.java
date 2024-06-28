package HerramientasConexion;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

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

}

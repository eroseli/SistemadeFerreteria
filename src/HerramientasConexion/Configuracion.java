package HerramientasConexion;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuracion {
	
	private static Configuracion instancia;
	private Properties properties = new Properties();
	
    public Configuracion(String archivoConfig) {
        try (FileInputStream fis = new FileInputStream(archivoConfig)) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para obtener la instancia de Configuracion (Singleton)
    public static Configuracion getInstancia(String archivoConfig) {
        if (instancia == null) {
            instancia = new Configuracion(archivoConfig);
        }
        return instancia;
    }
    
    public String getEA() {
    	return properties.getProperty("app.EA");
    }
    
    public String getEM() {
    	return properties.getProperty("app.EM");
    }
    public String getEB() {
    	return properties.getProperty("app.EB");
    }
    
//    public static void main(String[] args) {
//    	
//    	String directorioActual = System.getProperty("user.dir");
//        
//        // Imprimir el directorio
//        System.out.println("Directorio de trabajo actual: " + directorioActual);
//    	
//        Configuracion config = new Configuracion("src/Config.properties");
//
//        // Leer propiedades
//        String title = config.getProperty("app.title");
//        String version = config.getProperty("app.version");
//        String author = config.getProperty("app.author");
//        
//        String EA = config.getProperty("app.EA");
//        String EM = config.getProperty("app.EM");
//        String EB = config.getProperty("app.EB");
//        		
//        // Imprimir propiedades
//        System.out.println("Título: " + title);
//        System.out.println("Versión: " + version);
//        System.out.println("Autor: " + author);
//    }
	
}

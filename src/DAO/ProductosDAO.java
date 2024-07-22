package DAO;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import HerramientasConexion.ConexionGlobal;
import Models.ProductoBusquedaView;
import Models.Respuesta;
import DAO.ModelsDAO.Producto;

public class ProductosDAO {
	
	
	PreparedStatement stm = null;
	ResultSet resultados = null;
	Respuesta respuesta;
	Producto producto =null;
	List<Producto> productos = null;;
	
	
	public ProductosDAO() {
		productos = new ArrayList<Producto>();

	}
	
public Respuesta obtenerProductoCodigo(String nombre) {
		
		Respuesta respuesta = new Respuesta("",true,null);
		String query = "select * from productos where Codigo = '"+nombre+"'";
		producto = null;
		
		try {
			ConexionGlobal.establecerConexio();
            stm =  (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);           
			resultados = stm.executeQuery();
			
			while (resultados.next()) {
				producto = new Producto(
						resultados.getInt("Id_producto"),
                		resultados.getString("Codigo"),
                		resultados.getString("Nombre"),
                		resultados.getString("Descripcion"),
						resultados.getString("Cantidad"),
                		resultados.getDate("Fecha_caducidad"),
                		resultados.getFloat("P_publico"),
                		resultados.getFloat("P_Mayoreo"),
                		resultados.getFloat("P_Adquisicion"),
                		resultados.getInt("Existencia"),
                		resultados.getString("Categoria"),
                		resultados.getString("Marca"),
                		resultados.getDate("FechaRegistro")
                		);
				
			}
			
			respuesta.setRespuesta(producto);
			
		} catch (SQLException e) {
			// TODO: handle exception
			return new Respuesta("Error: "+e.getMessage(),false,null);
		}
		finally {
			try {
				ConexionGlobal.cerrarConexion();
			} catch (SQLException e) {
				System.out.println("Error: "+e.getMessage());
			}
		}		
		return  respuesta;
	}

	public Respuesta obtenerProductoBusqueda(ProductoBusquedaView productoBusquedaView) {
		Respuesta respuesta = new Respuesta("",true,null);
		String query = "{call PROCEDIMIENTOPRODUCTOSFILTRO(?,?,?,?,?)}";
		ResultSet rs = null;
		
		try {
			productos.clear();
			
			ConexionGlobal.establecerConexio();
			stm = (CallableStatement) ConexionGlobal.connection.prepareCall(query);
			
			stm.setString(1, productoBusquedaView.getBusqueda());
			stm.setDate(2, productoBusquedaView.getFechaInicio());
			stm.setDate(3, productoBusquedaView.getFechaFinal());
			stm.setString(4, productoBusquedaView.getFiltroBusqueda());
			stm.setString(5, productoBusquedaView.getFecha());
			
			boolean tieneResultados = stm.execute();
            System.out.println(query);
            // Si tiene resultados, procesar el ResultSet
            if (tieneResultados) {
                rs = stm.getResultSet();
                
                ResultSetMetaData metaData = rs.getMetaData();
                int numColumns = metaData.getColumnCount();
                System.out.println(rs.getStatement().toString());
                while (rs.next()) {
                    producto = new Producto(
    						rs.getInt("Id_producto"),
                    		rs.getString("Codigo"),
                    		rs.getString("Nombre"),
                    		rs.getString("Descripcion"),
    						rs.getString("Cantidad"),
                    		rs.getDate("Fecha_caducidad"),
                    		rs.getFloat("P_publico"),
                    		rs.getFloat("P_Mayoreo"),
                    		rs.getFloat("P_Adquisicion"),
                    		rs.getInt("Existencia"),
                    		rs.getString("Categoria"),
                    		rs.getString("Marca"),
                    		rs.getDate("FechaRegistro")
                    		);
                    
                    productos.add(producto);
                    
                    
//                    for (int i = 1; i <= numColumns; i++) {
//                        String columnName = metaData.getColumnName(i);
//                        Object value = rs.getObject(columnName);
//                        System.out.println(columnName + ": " + value);
//                    }
//                    System.out.println("----------------------------------");
                    
                }
                
                rs.close(); // Cerrar el ResultSet después de usarlo
            }
			
            respuesta.setRespuesta(productos);
			
		} catch (SQLException e) {
			respuesta = new Respuesta("Error al intentar correr procedimiento"+e.getMessage(), false, null);
			e.printStackTrace();
		}
		finally {						
			try {
				ConexionGlobal.cerrarConexion();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		
		return respuesta;
	}

	public Respuesta obtenerProductoCoincidencia(String nombre) {
		
		Respuesta respuesta = new Respuesta("",true,null);
		String query = "select * from productos where nombre like '%"+nombre+"%'";
		
		try {
			
			productos.clear();
			ConexionGlobal.establecerConexio();
            stm =  (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);           
			resultados = stm.executeQuery();
			
			while (resultados.next()) {
				producto = new Producto(
						resultados.getInt("Id_producto"),
                		resultados.getString("Codigo"),
                		resultados.getString("Nombre"),
                		resultados.getString("Descripcion"),
						resultados.getString("Cantidad"),
                		resultados.getDate("Fecha_caducidad"),
                		resultados.getFloat("P_publico"),
                		resultados.getFloat("P_Mayoreo"),
                		resultados.getFloat("P_Adquisicion"),
                		resultados.getInt("Existencia"),
                		resultados.getString("Categoria"),
                		resultados.getString("Marca"),
                		resultados.getDate("FechaRegistro")
                		);
				
				productos.add(producto);
			}
			
			respuesta.setRespuesta(productos);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			try {
				ConexionGlobal.cerrarConexion();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return  respuesta;
	}
	
	public Respuesta obtenerProductoFiltro(String filtro, String nombre) {
		
		respuesta = new Respuesta("",true,null);
		productos = new ArrayList<Producto>();
		String query = "select * from productos ";
		String where = "";
		
		switch (filtro) {
			case "Fecha de Registro":
				query = query +  (!nombre.equals("") && !nombre.isEmpty()?" where nombre like '%"+nombre+"%' ":"") + " order by Fecha_caducidad desc";
				break;
			case "Stock Positivo":
				query = query +"where "+(!nombre.equals("") && !nombre.isEmpty()?"nombre like '%"+nombre+"%' and":"")+ " Existencia >0";
				break;
			case "Faltantes":
				query = query +(!nombre.equals("") && !nombre.isEmpty()?" where nombre like '%"+nombre+"%' and":"")+ " Existencia =0";
				break;
			case "A - Z":
				query = query +(!nombre.equals("") && !nombre.isEmpty()?" where nombre like '%"+nombre+"%' ":"")+" order by Nombre asc";
				break;
			case "Z - A":
				query = query +(!nombre.equals("") && !nombre.isEmpty()?" where nombre like '%"+nombre+"%' ":"")+ " order by Nombre desc";
				break;
		}
			
		try {
			
			productos.clear();
			ConexionGlobal.establecerConexio();
            stm =  (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);           
			resultados = stm.executeQuery();
			
			while (resultados.next()) {
				producto = new Producto(
						resultados.getInt("Id_producto"),
                		resultados.getString("Codigo"),
                		resultados.getString("Nombre"),
                		resultados.getString("Descripcion"),
						resultados.getString("Cantidad"),
                		resultados.getDate("Fecha_caducidad"),
                		resultados.getFloat("P_publico"),
                		resultados.getFloat("P_Mayoreo"),
                		resultados.getFloat("P_Adquisicion"),
                		resultados.getInt("Existencia"),
                		resultados.getString("Categoria"),
                		resultados.getString("Marca"),
                		resultados.getDate("FechaRegistro")
                		);
				
				productos.add(producto);
			}
			
			respuesta.setRespuesta(productos);
			ConexionGlobal.cerrarConexion();
			
		} catch (Exception e) {
			respuesta = new Respuesta("Error al obtener los datos", false, null);
		}
		finally {
			
		}
		
		return respuesta;
	}
	

	public Respuesta obtenerProductos()   {
		
		respuesta = new Respuesta("",true,null);
		productos = new ArrayList<Producto>();
		String query = "select * from productos order by Id_producto desc;";
		
		try {
			
			productos.clear();
			ConexionGlobal.establecerConexio();
            stm =  (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);           
			resultados = stm.executeQuery();
			
			while (resultados.next()) {
				producto = new Producto(
						resultados.getInt("Id_producto"),
                		resultados.getString("Codigo"),
                		resultados.getString("Nombre"),
                		resultados.getString("Descripcion"),
						resultados.getString("Cantidad"),
                		resultados.getDate("Fecha_caducidad"),
                		resultados.getFloat("P_publico"),
                		resultados.getFloat("P_Mayoreo"),
                		resultados.getFloat("P_Adquisicion"),
                		resultados.getInt("Existencia"),
                		resultados.getString("Categoria"),
                		resultados.getString("Marca"),
                		resultados.getDate("FechaRegistro")
                		);
				
				productos.add(producto);
			}
			
			respuesta.setRespuesta(productos);
			ConexionGlobal.cerrarConexion();
			
		} catch (Exception e) {
			respuesta = new Respuesta("Error al obtener los datos", false, null);
		}
		finally {
			
		}
		
		return respuesta;
	}
	
	public Respuesta insertarProducto( Producto producto)  {
		respuesta = new Respuesta("Producto Registrado Correctamente.",true,null);
		
		String query = "INSERT INTO productos(Codigo, Nombre, Descripcion, Cantidad, Fecha_caducidad, P_publico, P_Mayoreo, P_Adquisicion,"
				+ "	Existencia, Categoria, Marca,Estatus,FechaRegistro) values(?,?,?,?,?,?,?,?,?,?,?,?,?);";
		
		try {
			
			ConexionGlobal.establecerConexio();	
            stm =  (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);           
			
            stm.setString(1,producto.getCodigo());
            stm.setString(2,producto.getNombre());
            stm.setString(3,producto.getDescripcion());
            stm.setString(4,producto.getCantidad());
            stm.setDate(5,producto.getFecha_caducidad());
            stm.setFloat(6,producto.getP_publico());
            stm.setFloat(7,producto.getP_Mayoreo());
            stm.setFloat(8,producto.getP_Adquisicion());
            stm.setInt(9,producto.getExistencia());
            stm.setString(10,producto.getCategoria());
            stm.setString(11,producto.getMarca());
            stm.setString(12,"ACTIVO");
            stm.setDate(13, Date.valueOf(LocalDate.now()));
            stm.execute();

			
		} catch (Exception e) {
			respuesta = new Respuesta("Error al Registrar Porducto. "+e.getMessage(), false, null);
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		finally {
			try {
				ConexionGlobal.cerrarConexion();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return respuesta;
	}
	
	public Respuesta actualizarProducto(Producto producto) {
		
		respuesta = new Respuesta("Producto "+producto.getCodigo()+" Actualizado Correctamente",true,null);
		
		String query = "update productos set Codigo = ?, Nombre = ?,Descripcion = ?, Cantidad= ?, Fecha_caducidad = ?, P_publico = ? ,"
				+ "    P_Mayoreo = ?, P_Adquisicion = ?, Existencia = ?, Categoria = ?, Marca = ? where Codigo = ? ;";
		
		try {
			
			ConexionGlobal.establecerConexio();
            stm =  (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);           
			
			stm.setString(1,producto.getCodigo() );
			stm.setString(2,producto.getNombre() );
			stm.setString(3,producto.getDescripcion() );
			stm.setString(4,producto.getCantidad() );
			stm.setDate(5,producto.getFecha_caducidad() );
			stm.setFloat(6,producto.getP_publico() );
			stm.setFloat(7,producto.getP_Mayoreo() );
			stm.setFloat(8,producto.getP_Adquisicion() );
			stm.setInt(9,producto.getExistencia() );
			stm.setString(10,producto.getCategoria() );
			stm.setString(11,producto.getMarca() );
			stm.setString(12,producto.getCodigo() );		
			stm.execute();
			
		} catch (SQLException e) {
			respuesta = new Respuesta("Error al Actualizar Producto "+producto.getCodigo(), false, null);
		}
		finally {
			try {
				ConexionGlobal.cerrarConexion();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return respuesta;
		
	}
	
	public Respuesta eliminarProducto(String codigo) {
		
		respuesta = new Respuesta("Producto "+codigo+" Eliminado Correctamente",true,null);
		
		String query = "delete from productos where Codigo = '"+codigo+"';";
		
		try {
			
			ConexionGlobal.establecerConexio();
            stm =  (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);           
			stm.execute();
			
		} catch (Exception e) {
			respuesta = new Respuesta("Error al Eliminar Producto "+codigo, false, null);
		}
		finally {
			try {
				ConexionGlobal.cerrarConexion();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return respuesta;
	}
	
	public static void main(String[]args) {
		
		ProductosDAO dao = new ProductosDAO();
		Respuesta respuesta = new Respuesta("",false,null);
		
		/*
		 * Producto p = new Producto(0,"123213","Llave de precion ", "llave de precion",
		 * "1 llave",null,10f,10f,10f,10,"Categoria","Patito" );
		 * 
		 * respuesta = dao.obtenerProductos();
		 * 
		 * List<Producto> productos = (List<Producto>) respuesta.getRespuesta();
		 * 
		 * for (Producto r : productos) { System.out.println(r.getNombre()
		 * +" "+r.getCodigo()); }
		 * 
		 * //respuesta = dao.insertarProducto(p); LocalDate datetime =
		 * LocalDate.of(2014, Month.JANUARY, 1);
		 * 
		 * Date todayDate = new Date(0); SimpleDateFormat sdf = new
		 * SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); String fechaActual =
		 * sdf.format(todayDate); System.out.println(fechaActual); Producto p2 = new
		 * Producto(404,"123213","Llave de precionc222 ", "llave de precion 2",
		 * "1 llave 2",new Date(0),23f,23f,23f,23,"Categoria ac","Patito ac" );
		 * 
		 * 
		 * System.out.println(datetime);
		 */
		java.util.Date utilDate = new java.util.Date();
	    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
	    
		System.out.println(sqlDate);
		Producto p2 = new Producto(407,"1qase32","Llave nueva ", "llave de precion 2","1 llave 2",sqlDate,23f,23f,23f,23,"Categoria ac","Patito ac" );
		respuesta = dao.actualizarProducto(p2);
		
		JOptionPane.showMessageDialog(null, respuesta.getMensaje());
		
		//respuesta = dao.eliminarProducto("123213");
		//JOptionPane.showMessageDialog(null, respuesta.getMensaje());
		
		respuesta = dao.obtenerProductoCoincidencia("tr");
		
		 List<Producto> productos = (List<Producto>) respuesta.getRespuesta();

		
		 for (Producto r : productos) { 
			 System.out.println(r.getNombre()+" "+r.getCodigo()); 
		 }
		
	}
	
	
	
}

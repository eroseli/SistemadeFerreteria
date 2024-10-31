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
import Models.ProductoVenta;
import Models.Respuesta;
import DAO.ModelsDAO.CarritoDAO;
import DAO.ModelsDAO.Producto;

public class ProductosDAO {
	
	
	PreparedStatement stm = null;
	ResultSet resultados = null;
	Respuesta respuesta;
	Producto producto =null;
	CarritoDAO carrito = null;
	List<Producto> productos = null;;
	List<CarritoDAO> carritos = null;
	ProductoVenta productoVenta = null;
	List<ProductoVenta> productosVenta = null;
	
	public ProductosDAO() {
		productos = new ArrayList<Producto>();
		carritos = new ArrayList<CarritoDAO>();
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
				System.out.println(producto.getNombre());
				productos.add(producto);
			}
			
			respuesta.setRespuesta(productos);
			ConexionGlobal.cerrarConexion();
			
		} catch (SQLException e) {
			respuesta = new Respuesta("Error al obtener los datos "+e.getMessage(), false, null);
		}
		finally {
			
		}
		
		return respuesta;
	}
	
	public Respuesta insertarCarrito(String idCliente, String nombre) {
		respuesta = new Respuesta("Carritos Insertado Correctamente",true,null);
		String query = "INSERT INTO Carritos(IdCliente, Nombre, fechaRegistro, horaRegistro) values(?,?,current_date(), curtime())";
		try {
			
			ConexionGlobal.establecerConexio();	
            stm =  (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);           
			
			stm.setString(1,idCliente);
			stm.setString(2,nombre);
            stm.execute();			
			
		}  catch (Exception e) {
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
	
	public Respuesta obtenerUltimoIdentificador() {
		respuesta = new Respuesta("Id del carrito obtenido Correctamente",true,null);
		String query = " select IdCarrito from Carritos order by IdCarrito desc limit 1";
		try {
			
			ConexionGlobal.establecerConexio();
            stm =  (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);           
			resultados = stm.executeQuery();
			int idCarrito =0;
			
			while (resultados.next()) {
				 idCarrito = resultados.getInt("IdCarrito");
				 System.out.println(idCarrito);
			}
			
			respuesta.setRespuesta(idCarrito);
			ConexionGlobal.cerrarConexion();			
			
		}  catch (Exception e) {
			respuesta = new Respuesta("Error al Obtener el registro. "+e.getMessage(), false, null);
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
	
	public Respuesta obtenerCarritoDetalleProductos(int idCarrito) {
		
		productosVenta = new ArrayList<ProductoVenta>();
		
		respuesta = new Respuesta("Carrito Detalle",true,null);
		String query = " select * from Carritos  C inner join Carritosdet CD on C.IdCarrito = CD.Id_Carrito "
				+ "    inner join productos P on CD.Id_Producto = P.Id_producto  where  C.IdCarrito = "+idCarrito;		

		try {
			ConexionGlobal.establecerConexio();
            stm =  (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);           
			resultados = stm.executeQuery();

			while (resultados.next()) {
				System.out.println("Entro");
				productoVenta = new ProductoVenta();
				
				productoVenta.setId_producto(resultados.getInt("Id_Producto"));
				productoVenta.setCodigo(resultados.getString("Codigo"));
				productoVenta.setNombre(resultados.getString("Nombre"));
				productoVenta.setDescripcion(resultados.getString("Descripcion"));
				productoVenta.setCantidad(resultados.getString("Cantidad"));
				productoVenta.setFecha_caducidad(resultados.getDate("Fecha_caducidad"));
				productoVenta.setP_publico(resultados.getFloat("P_publico"));
				productoVenta.setP_Mayoreo(resultados.getFloat("P_Mayoreo"));
				productoVenta.setP_Adquisicion(resultados.getFloat("P_Adquisicion"));
				productoVenta.setExistencia(resultados.getInt("Existencia"));
				productoVenta.setCategoria(resultados.getString("Categoria"));
				productoVenta.setMarca(resultados.getString("Marca"));
				productoVenta.setCantidadComprar(resultados.getInt("CD.Cantidad"));
				productoVenta.setPrecioCompra(resultados.getFloat("P_publico"));
				productoVenta.setDescuentoM(resultados.getString("descuento"));
				productoVenta.setDescuentoE(resultados.getInt("DescuentoEspecial"));
				
				productosVenta.add(productoVenta);
				System.out.println(productoVenta.getNombre());
			}
			
			respuesta.setRespuesta(productosVenta);
			
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
		
		return respuesta;
		
	}
	
	public Respuesta obtenerCarritos() {
		
		respuesta = new Respuesta("Carrito Detalle Insertado Correctamente",true,null);
		String query = " select * from Carritos ORDER BY FechaRegistro desc";		
		
		try {
			ConexionGlobal.establecerConexio();
            stm =  (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);           
			resultados = stm.executeQuery();
			carritos.clear();
			System.out.println("Entro a la funcion");
			while (resultados.next()) {
				System.out.println("Entro");
				carrito = new CarritoDAO(
						resultados.getInt("IdCarrito"),
                		resultados.getString("Nombre"),
                		resultados.getInt("IdCliente"),
                		resultados.getDate("fechaRegistro"),
                		resultados.getTime("horaRegistro")
                		);
				carritos.add(carrito);
				System.out.println("Carrito "+carrito.getNombre());
			}
			
			respuesta.setRespuesta(carritos);
			
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
		
		return respuesta;
	}
	
	public Respuesta insertarDetalleCarrito(ProductoVenta productoVenta, int idCarrito) {
		respuesta = new Respuesta("Carrito Detalle Insertado Correctamente",true,null);
		String query = "INSERT INTO Carritosdet(Id_Carrito, Id_Producto, Cantidad, Descuento, DescuentoEspecial, fechaRegistro)"
				+ " values(?,?,?,?,?,current_date())";
		try {
			
			ConexionGlobal.establecerConexio();	
            stm =  (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);           
			
			stm.setInt(1,idCarrito);
			stm.setInt(2,productoVenta.getId_producto());
			stm.setInt(3, productoVenta.getCantidadComprar());
			stm.setString(4, String.valueOf(productoVenta.getDescuentoM()));
			stm.setInt(5,productoVenta.getDescuentoE());
            stm.execute();			
			
		}  catch (Exception e) {
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
	
	public Respuesta obtenerNumeroCarritos() {
		respuesta = new Respuesta("Cantidad de Carritos",true,null);
		try {
			String query = "SELECT COUNT(*) carritos FROM Carritos;";
			ConexionGlobal.establecerConexio();	
            stm =  (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);           
			resultados = stm.executeQuery();

            while (resultados.next()) {
            	
				respuesta.setRespuesta(resultados.getInt("carritos"));
				System.out.println("Respuesta = "+respuesta.getRespuesta());
			}
            System.out.println("La funcions");
		} catch (SQLException e) {
			System.out.println("Error "+e.getMessage());
		}
		return respuesta;
	}
	
	public  Respuesta NombreCarritoRepetido(String nombre) {
		respuesta = new Respuesta("Cantidad de Carritos",true,0);
		try {
			String query = " select count(Nombre) nombres from Carritos  where Nombre ='"+nombre+"';";
			ConexionGlobal.establecerConexio();	
            stm =  (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);           
			resultados = stm.executeQuery();
            while (resultados.next()) {            	
				respuesta.setRespuesta(resultados.getInt("nombres"));
				System.out.println("Respuesta = "+respuesta.getRespuesta());
			}
            System.out.println("La funcions");
		} catch (SQLException e) {
			System.out.println("Error "+e.getMessage());
			respuesta = new Respuesta("Problemas al obtener el Número de Carritos con en mismo Nombre",false,0);
		}
		return respuesta;
	}
	
	public Respuesta eliminarCarrito(int idCarrito) {
		
		respuesta = new Respuesta("Carrito Eliminado Correctamente",true,null);
		String query = " delete from Carritos where IdCarrito ="+idCarrito;
		System.out.println("Consulta : "+ query);
		try {
			ConexionGlobal.establecerConexio();	
            stm =  (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);           		
            stm.execute();
		} catch (SQLException e) {
			respuesta = new Respuesta("Problemas al eliminar el registro : "+e.getMessage(),true, null);
		}	
		return respuesta;
		
	}
	
	public Respuesta eliminarCarritoDet(int idCarrito) {
		respuesta = new Respuesta("Carrito Eliminado Correctamente",true,null);
		String query = "delete from Carritosdet where Id_Carrito = "+idCarrito;
		System.out.println(query);
		try {
			ConexionGlobal.establecerConexio();	
            stm =  (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);           		
            stm.execute();            
		} catch (SQLException e) {
			respuesta = new Respuesta("Problemas al eliminar el registro : "+e.getMessage(),true, null);
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

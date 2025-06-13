package productos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

/**
 * Clase para gestionar las operaciones CRUD
 */
public class ProductoDAO {
	//Conexión a la BD
	private Connection conn;
	
	/**
	 *  Constructor que inicializa la conexión utilizando la clase DatabaseConnection
	 */
	public ProductoDAO() {
		DatabaseConnection db = new DatabaseConnection();
        this.conn = db.getConnection();
	}
	
	/**
	 * 1. Método para agregar productos a la BD
	 * @param producto a agregar
	 * @return true si se agregó correctamente, false si no
	 */
	public boolean agregarProducto (ProductoOtaku producto) {
		String sql = "INSERT INTO productos (nombre, categoria, precio, stock) VALUES (?, ?, ?, ?)";
		
		try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			stmt.setString(1, producto.getNombre());
			stmt.setString(2, producto.getCategoria());
			stmt.setDouble(3, producto.getPrecio());
			stmt.setInt(4, producto.getStock());
			
			int filasAfectadas = stmt.executeUpdate(); 
			if (filasAfectadas > 0) {
				try (ResultSet generatedKeys = stmt.getGeneratedKeys()){
					if (generatedKeys.next()) {
	                    producto.setId(generatedKeys.getInt(1));  // Asignamos el ID generado
	                }
	            }
	        }
			return filasAfectadas > 0;

		} catch (SQLException ex) {
			System.out.println("Error al agregar producto: " + producto.getNombre());
			ex.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 2. Métodos para obtener producto mediante su ID
	 * @param ID del producto
	 * @return el producto encontrado, o null si no existe
	 */
	public ProductoOtaku mostrarProductoPorId(int id) {
		String sql = "SELECT * FROM productos WHERE id = ?";
		
		try (PreparedStatement stmt = conn.prepareStatement(sql)){
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new ProductoOtaku(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("categoria"),
                    rs.getDouble("precio"),
                    rs.getInt("stock"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
	}
	
	/**
	 * 3. Método para obtener todos los productos almacenados
	 * @return lista de productos
	 */
	public List<ProductoOtaku> mostrarTodosLosProductos(){
		List<ProductoOtaku> productos = new ArrayList<ProductoOtaku>();
		String sql = "SELECT * FROM productos";
		
		try (PreparedStatement stmt = conn.prepareStatement(sql)){
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				productos.add(new ProductoOtaku(
					rs.getInt("id"),
					rs.getString("nombre"),
					rs.getString("categoria"),
					rs.getDouble("precio"),
					rs.getInt("stock")
				));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return productos;
	}
	
	/**
	 * 4. Método para actualizar los datos de un producto 
	 * @param producto con los datos actualizados
	 * @return true si la actualización fue exitosa, false si falló
	 */
	public boolean actualizarProducto(ProductoOtaku producto) {
		String sql = "UPDATE productos SET nombre = ?, categoria = ?, precio = ?, stock = ? WHERE id = ?";
	
		try (PreparedStatement stmt = conn.prepareStatement(sql)){
			stmt.setString(1, producto.getNombre());
			stmt.setString(2, producto.getCategoria());
			stmt.setDouble(3, producto.getPrecio());
			stmt.setInt(4, producto.getStock());
			stmt.setInt(5, producto.getId());
			
			int filas = stmt.executeUpdate();
	        return filas > 0;
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 5. Método para eliminar producto de la BD
	 * @param ID del producto para eliminar
	 * @return true si se eliminó correctamente, false si falló
	 */
	public boolean eliminarProducto(int id) {
		String sql = "DELETE FROM productos WHERE id = ?";
		
		try (PreparedStatement stmt = conn.prepareStatement(sql)){
			stmt.setInt(1, id);
			
			int filas = stmt.executeUpdate();
	        return filas > 0;
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 6. Método para buscar producto por nombre
	 * @param nombre a buscar
	 * @return lista de productos que coincidan
	 */
	public List<ProductoOtaku> buscarProductosPorNombre(String nombre){
		List<ProductoOtaku> productos = new ArrayList<>();
		
		String sql = "SELECT * FROM productos WHERE nombre LIKE ?";
		
		try (PreparedStatement stmt = conn.prepareStatement(sql)){
			stmt.setString(1, "%" + nombre + "%");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				productos.add(new ProductoOtaku(
						rs.getInt("id"),
						rs.getString("nombre"),
						rs.getString("categoria"),
						rs.getDouble("precio"),
						rs.getInt("stock")
						
					));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return productos;
	} 
}
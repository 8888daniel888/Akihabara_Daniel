package testDAO;

import org.junit.jupiter.api.*;

import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import productos.dao.ProductoDAO;
import productos.dao.ProductoOtaku;

//Clase de prueba unitaria para la clase ProductoDAO
public class testProductoDAO {
	// Clase de prueba unitaria para la clase ProductoDAO
	private static ProductoDAO dao;
	private static ProductoOtaku test;

	// Método que se ejecuta antes de cada prueba para preparar el entorno
	@BeforeEach
	public void setup() {
		dao = new ProductoDAO();
		test = new ProductoOtaku("Figura de Anya Forger", "Figura", 200.20, 10);
	}
	
	// Prueba para verificar que se pueden agregar productos correctamente
	@Test
	public void testAgregarProducto() {
		dao.agregarProducto(test); 
	    ProductoOtaku producto = dao.mostrarProductoPorId(test.getId());
	    assertNotNull(producto, "El producto no debe ser null");
	    assertEquals("Figura de Anya Forger", producto.getNombre());
	}

	//Prueba para verificar la obtención de un producto por su ID
	@Test
	public void testMostrarProductoPorId() {
	    boolean agregado = dao.agregarProducto(test);
	    assertTrue(agregado, "El producto debería agregarse correctamente");

	    assertNotNull(test.getId(), "El ID del producto debería ser asignado");

	    ProductoOtaku producto = dao.mostrarProductoPorId(test.getId());
	    assertNotNull(producto, "El producto no debe ser null");
	    assertEquals("Figura de Anya Forger", producto.getNombre());
    }

	// Prueba para obtener todos los productos de la base de datos
	@Test
	public void testMostrarTodosLosProductos() {
		List<ProductoOtaku> productos = dao.mostrarTodosLosProductos();
        assertNotNull(productos, "La lista no debe ser null");
        assertTrue(productos.size() > 0, "Debe haber al menos un producto en la base de datos");
	}

	// Prueba para actualizar un producto existente
	@Test
	public void testActualizarProducto() {
		dao.agregarProducto(test);
		test.setPrecio(300.50);
        boolean actualizado = dao.actualizarProducto(test);
        assertTrue(actualizado, "El producto debería actualizarse");

        ProductoOtaku actualizadoProducto = dao.mostrarProductoPorId(test.getId());
        assertEquals(300.50, actualizadoProducto.getPrecio(), 0.01);
	}

	// Prueba para eliminar un producto de la base de datos
	@Test
	public void testEliminarProducto() {
		dao.agregarProducto(test);
		
		 boolean eliminado = dao.eliminarProducto(test.getId());
	        assertTrue(eliminado, "El producto debe eliminarse correctamente");

	        ProductoOtaku eliminadoProducto = dao.mostrarProductoPorId(test.getId());
	        assertNull(eliminadoProducto, "El producto ya no debe existir");
	}

	

}

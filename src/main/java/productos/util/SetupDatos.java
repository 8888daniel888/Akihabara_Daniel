package productos.util;

import productos.dao.ProductoDAO;
import productos.dao.ProductoOtaku;

public class SetupDatos {
	public static void main(String[] args) {
		// Crear una instancia del DAO para manejar productos
		ProductoDAO dao = new ProductoDAO();
		
		// Crear objetos ProductoOtaku con sus respectivos datos
		ProductoOtaku p1 = new ProductoOtaku("Figura de Anya Forger", "Figura", 59.95, 9);
		ProductoOtaku p2 = new ProductoOtaku("Manga Chainsaw Man Vol.1", "Manga", 9.99, 20);
		ProductoOtaku p3 = new ProductoOtaku("Póster Studio Ghibli Colección", "Póster", 15.50, 15);
		
		// Agregar los productos creados al DAO
		dao.agregarProducto(p1);
		dao.agregarProducto(p2);
		dao.agregarProducto(p3);
		
		
		
		}
}

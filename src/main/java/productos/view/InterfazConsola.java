package productos.view;

import productos.dao.ProductoOtaku;
import java.util.List;
import java.util.Scanner;

import productos.dao.ProductoDAO;
import productos.dao.ProductoOtaku;

/**
 * Clase encargada de manejar la interacción con el usuario a través de la consola
 */
public class InterfazConsola {
	private Scanner scanner = new Scanner(System.in);

	/**
	 *  1. Menú principal
	 */
	public void mostrarMenu() {
		System.out.println(" ==== MENÚ ====");
		System.out.println("1- Añadir productos");
		System.out.println("2- Mostrar detalle de producto mediante id");
		System.out.println("3- Mostrar todos los productos");
		System.out.println("4- Actualizar producto");
		System.out.println("5- Eliminar producto");
		System.out.println("6- Asistente IA");
		System.out.println("7- Descripción Producto por IA");
		System.out.println("8- Sugerir categoría de Producto por IA");
		System.out.println("9- Salir");
		System.out.print("Elige una opción: ");
	}

	/**
	 *  2. Solicita al usuario los datos para crear un nuevo producto
	 * @return un objeto ProductoOtaku con los datos ingresados
	 */
	public ProductoOtaku datosProducto() {
		System.out.println("\n--- Añadir producto --- ");
		String nombre = Utilidades.leerString("Nombre: ");
		String categoria = Utilidades.leerString("Categoría: ");

		double precio = -1;
		while (precio < 0) {
			System.out.print("Precio: ");
			try {
				precio = Double.parseDouble(scanner.nextLine());
				if (precio <= 0) {
					System.out.println("El precio debe ser un número positivo \n");
				}
			} catch (NumberFormatException e) {
				System.out.println("Error.Introduce un número válido para el precio\n");
			}
		}

		int stock = -1;
		while (stock < 0) {
			System.out.print("Stock: ");
			try {
				stock = Integer.parseInt(scanner.nextLine());
				if (stock <= 0) {
					System.out.println("El stock no puede ser negativo\n");
				}
			} catch (NumberFormatException e) {
				System.out.println("Error.Introduce un número entero para el stock \n");
			}
		}
		// Retorna un nuevo producto con los datos ingresados
		return new ProductoOtaku(nombre, categoria, precio, stock);
	}

	/**
	 * 3. Mostrar lista de productos almacenados en la BD
	 * @param productos lista de productos a mostrar
	 */
	public void mostrarListaProductos(List<ProductoOtaku> productos) {
		System.out.println("\n --- Lista de productos ---");

		if (productos.isEmpty()) {
			System.out.println("No hay productos\n");
		} else {
			String formatoEncabezado = "| %-3s | %-30s | %-15s | %-8s | %-5s |\n";
			String formatoDatos = "| %-3d | %-30s | %-15s | %8.2f€ | %-5d |\n";

			String separador = "+-----+--------------------------------+-----------------+----------+-------+";

			System.out.println(separador);
			System.out.printf(formatoEncabezado, "ID", "Nombre", "Categoría", "Precio", "Stock");
			System.out.println(separador);

			for (ProductoOtaku p : productos) {
				System.out.printf(formatoDatos, p.getId(), p.getNombre(), p.getCategoria(), p.getPrecio(),
						p.getStock());
			}

			System.out.println(separador + "\n");
		}
	}

	/**
	 *  4. Muestra los detalles de un solo producto
	 * @param p producto a mostrar
	 */
	public void mostrarProducto(ProductoOtaku p) {
		if (p != null) {
			System.out.println("\n --- Detalles del Producto ---");
			System.out.printf("ID: %d%nNombre: %s%nCategoría: %s%nPrecio: %.2f€%nStock: %d%n", p.getId(), p.getNombre(),
					p.getCategoria(), p.getPrecio(), p.getStock());
			System.out.println(" ");
		} else {
			System.out.println("Producto no encontrado\n");
		}
	}

	/**
	 * 5. Pide al usuario el ID del producto
	 * @return ID introducido
	 */
	public int idProducto() {
		int id = Utilidades.leerInt("Introduce el ID del producto: ");
		return id;
	}

	/**
	 *  6. Pedir el nombre del producto
	 * @return nombre introducido
	 */
	public String solicitarNombreProducto() {
		String nombre = Utilidades.leerString("Introduce el nombre del producto: ");
		return nombre;
	}

	/**
	 *  Lee la opción del menú seleccionada por el usuario
	 * @return  número de opción, o -1 si es inválido
	 */
	public int leerOpcion() {
		try {
			return Integer.parseInt(scanner.nextLine());
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	/**
	 *  7. Pide al usuario valores para actualizar un producto existente
	 * @param producto producto actual que será actualizado
	 * @return producto con los datos actualizados
	 */
	public ProductoOtaku solicitarActualizacionProducto(ProductoOtaku producto) {
		System.out.println("\n --- Actualizar Producto ---");

		String nombre = Utilidades.leerString("Nombre actual: " + producto.getNombre() + "\n Nombre nuevo : ");
		if (!nombre.isEmpty())
			producto.setNombre(nombre);

		String categoria = Utilidades
				.leerString("Categoría actual: " + producto.getCategoria() + "\n Categoría nueva: ");
		if (!categoria.isEmpty())
			producto.setCategoria(categoria);

		String precioStr = Utilidades.leerString("Precio actual: " + producto.getPrecio() + "\n Precio nuevo : ");
		if (!precioStr.isEmpty())
			producto.setPrecio(Double.parseDouble(precioStr));

		String stockStr = Utilidades.leerString("Stock actual: " + producto.getStock() + "\n Stock nuevo: ");
		if (!stockStr.isEmpty())
			producto.setStock(Integer.parseInt(stockStr));

		return producto;
	}

	/**
	 *  Descripcion que genera la IA
	 * @param descripcion texto generado por IA
	 */
	public void descripcionProductoIA(String descripcion) {
		System.out.println("\n--- Descripción generada por IA ---");
		System.out.println(descripcion + "\n");
	}

	/** Muestra la categoría que sugiere la IA
	 * 
	 * @param categoria categoría sugerida
	 */
	public void mostrarCategoriaSugeridaIA(String categoria) {
		System.out.println("\n--- Categoría sugerida por IA ---");
		System.out.println(categoria + "\n");
	}
}

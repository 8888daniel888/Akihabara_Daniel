package productos.controller;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import productos.dao.ProductoDAO;
import productos.dao.ProductoOtaku;
import productos.view.InterfazConsola;
import service.LlmService;

/**
 * Clase principal que controla el flujo de la aplicación.
 */
public class MainApp {
	private InterfazConsola interfaz;
	private ProductoDAO dao;
	private LlmService ia;
	private Scanner scanner;

    /**
     *  Constructor que inicializa los componentes necesarios
     */
	public MainApp() {
		interfaz = new InterfazConsola();
		dao = new ProductoDAO();
		scanner = new Scanner(System.in);

		// Intenta inicializar el servicio de IA y maneja errores
		try {
			ia = new LlmService();
		} catch (IOException e) {
			System.out.println("Error al inicializar el servicio IA: " + e.getMessage());
			e.printStackTrace();
			ia = null;
		}
	}

	/**
	 * Método principal que muestra el menú y gestiona la interacción con el usuario
	 */ 
	public void menu() {
		int opcion;

		do {
			interfaz.mostrarMenu();
			opcion = interfaz.leerOpcion();

			// Switch para manejar la acción correspondiente según la opción elegida
			switch (opcion) {
				case 1:
					agregarProducto();
					break;
				case 2:
					mostrarProductoPorId();
					break;
				case 3:
					mostrarTodosLosProductos();
					break;
				case 4:
					actualizarProducto();
					break;
				case 5:
					eliminarProducto();
					break;
				case 6:
					usarAsistenteIA();
					break;
				case 7:
					descripcionProductoIA();
					break;
				case 8:
					sugerirCategoriaProductoIA(); 
					break;
				case 9:
					System.out.println("Saliendo");
					break;
				default:
					System.out.println("Número incorrecto\n");
			}
		} while (opcion != 9);
	}

	/**
	 *  Agrega un nuevo producto a la base de datos
	 */
	private void agregarProducto() {
		ProductoOtaku producto = interfaz.datosProducto();
		dao.agregarProducto(producto);
		System.out.println("Producto agregado\n");
	}

	/**
	 * Pide un ID y muestra la información del producto 
	 */
	private void mostrarProductoPorId() {
		int id = interfaz.idProducto();
		ProductoOtaku producto = dao.mostrarProductoPorId(id);
		interfaz.mostrarProducto(producto);
		System.out.println(" ");
	}

	/**
	 *  Muestra todos los productos disponibles
	 */
	private void mostrarTodosLosProductos() {
		List<ProductoOtaku> productos = dao.mostrarTodosLosProductos();
		interfaz.mostrarListaProductos(productos);
	}

	/**
	 *  Permite al usuario actualizar la información de un producto existente
	 */
	private void actualizarProducto() {
		int id = interfaz.idProducto();
		ProductoOtaku producto = dao.mostrarProductoPorId(id);

		if (producto == null) {
			System.out.println("Producto no encontrado\n");
			return;
		}
		ProductoOtaku actualizado = interfaz.solicitarActualizacionProducto(producto);
		boolean exito = dao.actualizarProducto(actualizado);
		if (exito) {
			System.out.println("Producto actualizado correctamente\n");
		} else {
			System.out.println("Error al actualizar el producto\n");
		}
	}

	/**
	 *  Elimina un producto por su ID
	 */
	private void eliminarProducto() {
		int id = interfaz.idProducto();
		boolean exito = dao.eliminarProducto(id);
		if (exito) {
			System.out.println("Producto eliminado correctamente\n");
		} else {
			System.out.println("No se pudo eliminar el producto\n");
		}
	}

	/**
	 *  Elimina un producto por su ID
	 */
	private void usarAsistenteIA() {
		if (ia == null) {
			System.out.println("El servicio IA no está disponible \n");
			return;
		}

		System.out.println("\n--- Asistente IA ---");
		System.out.print("Escribe tu pregunta o consulta para el asistente: ");
		String prompt = scanner.nextLine();

		try {
			String respuesta = ia.consultarIA(prompt);
			System.out.println("\nRespuesta del Asistente IA:\n" + respuesta + "\n");
		} catch (Exception e) {
			System.out.println("Ocurrió un error al contactar con el asistente IA \n");
			e.printStackTrace();
		}
	}
	
	/**
	 *  Genera una descripción automática para un producto utilizando IA
	 */
	private void descripcionProductoIA() {
        if (ia == null) {
            System.out.println("El servicio IA no está disponible \n");
            return;
        }
        int id = interfaz.idProducto();
        ProductoOtaku producto = dao.mostrarProductoPorId(id);
        if (producto == null) {
            System.out.println("Producto no encontrado \n");
            return;
        }

        // Prompt para la IA con información del producto
        String prompt = String.format(
            "Genera una descripción de marketing breve y atractiva para el producto otaku: %s de la categoría %s.",
            producto.getNombre(), producto.getCategoria()
        );

        try {
            String descripcion = ia.consultarIA(prompt);
            interfaz.descripcionProductoIA(descripcion);
        } catch (Exception e) {
            System.out.println("Error al generar la descripción con IA \n");
            e.printStackTrace();
        }
    }

	/**
	 *  Sugiere una categoría para un nuevo producto basándose en su nombre
	 */
    private void sugerirCategoriaProductoIA() {
        if (ia == null) {
            System.out.println("El servicio IA no está disponible \n");
            return;
        }
        String nombreNuevoProducto = interfaz.solicitarNombreProducto();
        
        // Prompt con instrucciones y opciones de categorías
        String prompt = String.format(
            "Para un producto otaku llamado '%s', sugiere una categoría adecuada de esta lista: Figura, Manga, Póster, Llavero, Ropa, Videojuego, Otro.",
            nombreNuevoProducto
        );

        try {
            String categoria = ia.consultarIA(prompt);
            interfaz.mostrarCategoriaSugeridaIA(categoria);
        } catch (Exception e) {
            System.out.println("Error al sugerir categoría con IA \n");
            e.printStackTrace();
        }
    }

    /**
     *  Punto de entrada de la aplicación
     * @param args
     */
	public static void main(String[] args) {
		MainApp app = new MainApp();
		app.menu();
	}
}

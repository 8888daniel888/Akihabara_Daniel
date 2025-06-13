package productos.dao;

/**
 * Clase con los atributos necesarios
 */
public class ProductoOtaku {
	private static int nextID = 1; //Variable para llevar el control del siguiente ID
	private int id;
	private String nombre;
	private String categoria;
	private double precio;
	private int stock;

	/**
	 * Constructor que permite establecer una manualmente una ID
	 * @param id
	 * @param nombre
	 * @param categoria
	 * @param precio
	 * @param stock
	 */
	public ProductoOtaku(int id, String nombre, String categoria, double precio, int stock) {
		this.id = id;
		this.nombre = nombre;
		this.categoria = categoria;
		this.precio = precio;
		this.stock = stock;
	}

	/**
	 * Construtor que asigna automáticamente una ID
	 * @param nombre
	 * @param categoria
	 * @param precio
	 * @param stock
	 */
	public ProductoOtaku(String nombre, String categoria, double precio, int stock) {
		this.id = nextID++;
		this.nombre = nombre;
		this.categoria = categoria;
		this.precio = precio;
		this.stock = stock;
	}

	//Métodos GETTER y SETTER
	
	/**
	 * Método estático para establecer una ID
	 * @return próximo ID disponible para productos nuevos
	 */
	public static int getNextID() {
		return nextID;
	}

	/**
	 * 
	 * @param nextID valor para establecer como próximo ID disponible 
	 */
	public static void setNextID(int nextID) {
		ProductoOtaku.nextID = nextID;
	}

	/**
	 * 
	 * @return ID del producto
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * @param id a establecer para el producto
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @return nombre del producto
	 */
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * 
	 * @return categoría del producto
	 */
	public String getCategoria() {
		return categoria;
	}

	/**
	 * 
	 * @param categoria
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	/**
	 * 
	 * @return precio del producto
	 */
	public double getPrecio() {
		return precio;
	}

	/**
	 * 
	 * @param d  nuevo precio del producto
	 */
	public void setPrecio(double d) {
		this.precio = d;
	}

	/**
	 * 
	 * @return stock disponible
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * 
	 * @param stock nuevo stock del producto
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}

}

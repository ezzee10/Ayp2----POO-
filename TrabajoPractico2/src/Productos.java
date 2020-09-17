
public class Productos implements Component, Comparable<Component> {
	private double precioVenta;
	private double precioCosto;
	private Integer codigo;
	private String descripcion;
	private Clasificacion clasificacion;
	
	public Productos (double precioVenta, double precioCosto, Integer codigo, String descripcion){
		this.setPrecioCosto(precioCosto);
		this.setPrecioVenta(precioVenta);
		this.setCodigo(codigo);
		this.setDescripcion(descripcion);
	}

	public double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(double precioVenta) {
		try{
			NoPuedeSerInferior.method(precioCosto, precioVenta);
			this.precioVenta = precioVenta;
		}catch( NoPuedeSerInferior e){
			System.out.println("El Precio de venta debe ser mayor que el precio de Costo");
		}
	}

	public double getPrecioCosto() {
		return precioCosto;
	}

	public void setPrecioCosto(double precioCosto) {
		try{
			NoPuedeSerInferior.method(precioVenta, precioCosto);
			this.precioCosto = precioCosto;
		}catch( NoPuedeSerInferior e){
			System.out.println("El Precio de venta debe ser mayor que el precio de Costo");
		}
	}
	
	public String toString(){
		return (descripcion +"Precio de venta: "+ precioVenta);
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Clasificacion getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(Clasificacion clasificacion) {
		this.clasificacion = clasificacion;
	}


	@Override
	public int compareTo(Component o) {
		return clasificacion.compareTo(o.clasificacion);
	}
}

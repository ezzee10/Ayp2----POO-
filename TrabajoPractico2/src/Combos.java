import java.util.ArrayList;
import java.util.Iterator;


public class Combos implements Component {
	
	private ArrayList<Component> lista = new ArrayList<Component>(); 
	private double descuento;
	private int codigo;
	private String descripcion;
	private Clasificacion clasificacion = Clasificacion.Combos;
	
	public Combos(ArrayList<Component> listaDeCreacion, int descuento,int codigo, String descripcion){
		lista.addAll(listaDeCreacion);
		this.descuento = descuento/100;
	}
	
	public Combos(int descuento){
		this.descuento = descuento;
	}
	
	public void agregarProductoCombo(Productos producto){
		lista.add(producto);
	}
	
	public void agregarProductoComboLista(ArrayList<Productos> lista){
		lista.addAll(lista);
	}
	
	@Override
	public double getPrecioVenta() {
		Iterator<Component> itr = lista.iterator();
		Component aux;
		double precioVenta = 0;
		while(itr.hasNext()){
			aux = itr.next();
			precioVenta += aux.getPrecioVenta();
		}
		
		return (precioVenta - (precioVenta*descuento));
	}

	public int getCodigo() {
		return codigo;
	}
	
	public String toString(){
		return (descripcion+": "+ lista.toString() +"Decuento de: "+descuento+"%");
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}

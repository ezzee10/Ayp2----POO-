import java.util.HashMap;

public class Mesa {
	
	private double consumiciones = 0;
	private Estado estado = Estado.Disponible;
	private HashMap<Integer, Integer> listaCantidades = new HashMap<Integer, Integer>();
	private HashMap<Integer, Double> listaPrecios = new HashMap<Integer, Double>();
	
	public void cambiarADisponible() throws EsDisponible, NoDebeTenerConsumiciones{
		try{
			EsDisponible.method(estado);
			NoDebeTenerConsumiciones.method(consumiciones);
			estado = Estado.Disponible;
		}catch(EsDisponible e){
			System.out.println("La Mesa ya esta en estado Disponible");
		}catch(NoDebeTenerConsumiciones e){
			System.out.println("No debe tener consumiciones");
		}
	}	
	
	public void cambiarACerrada() throws EstaCerrada, NoDebeTenerConsumiciones{
		try{
			EstaCerrada.method(estado);
			NoDebeTenerConsumiciones.method(consumiciones);
			estado = Estado.Disponible;
		}catch(EstaCerrada e){
			System.out.println("La Mesa ya esta en estado Cerrada");
		}catch(NoDebeTenerConsumiciones e){
			System.out.println("No debe tener consumiciones");
		}
	}
	
	public void cambiarAOcupada() throws NoPuedeSerOcupada, NoDebeTenerConsumiciones{
		try{
			NoPuedeSerOcupada.method(estado);
			NoDebeTenerConsumiciones.method(consumiciones);
			estado = Estado.Disponible;
		}catch(NoPuedeSerOcupada e){
			System.out.println("La Mesa ya esta en estado Ocupada o esta Cerrada");
		}catch(NoDebeTenerConsumiciones e){
			System.out.println("No debe tener consumiciones");
		}
	}
	
	public void agregarConsumision(Component producto, int cantidad) throws DebeSerPositivo{
		try{
			Integer auxiliarCantidad = 0;
			Double auxiliarPrecio = 0.0;
			DebeSerPositivo.method(cantidad);
			if(listaCantidades.get(producto.getCodigo()) != null){
				auxiliarCantidad = listaCantidades.get(producto.getCodigo());
			}
			
			listaCantidades.put(producto.getCodigo(), auxiliarCantidad + cantidad);
			
			if(listaPrecios.get(producto.getCodigo()) != null){
				auxiliarPrecio = listaPrecios.get(producto.getCodigo());
			}
			
			listaPrecios.put(producto.getCodigo(), auxiliarPrecio + (producto.getPrecioVenta()*cantidad));
			
			consumiciones += producto.getPrecioVenta()*cantidad;
			
		}catch(DebeSerPositivo e){
			System.out.println("La cantidad debe ser positiva");
		}
	}
	
	public Ticket pedirCuenta(int numeroMesa, int dia, int mes, int año) throws EsDisponible, NoDebeTenerConsumiciones{
		Ticket ticket = new Ticket(this,numeroMesa,dia,mes,año);
		consumiciones = 0;
		cambiarADisponible();
		return ticket;
	}
	
	public HashMap<Integer, Double> getListaPrecios(){
		return listaPrecios;
	}
	
	public HashMap<Integer, Integer> getListaCantidades(){
		return listaCantidades;
	}
	
	public Double getConsumiciones(){
		return consumiciones;
	}
	

}

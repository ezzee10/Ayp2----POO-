import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.TreeSet;



public class Bar {
	private int dia = 1;
	private int mes = 1;
	private int año = 2017;
	private static Bar bar;
	private TreeSet<Ticket> tickets = new TreeSet<Ticket>();
	private HashSet<Component> carta = new HashSet<Component>();
	private Mesa[] mesas;
	private Bar(int cantidadMesas){
		mesas = new Mesa[cantidadMesas];
		for(int numeroMesa = 0; numeroMesa < mesas.length; numeroMesa++){
			mesas[numeroMesa] = new Mesa();
		}
	}
	
	
	/* 
	 * patron singleton
	 */
	public Bar getInstance(){
		return bar;
	}
	
	/*
	 * para poder usar un parametro en el singleton 
	 */
	public Bar getInstance(int cantidadMesas){
		if(bar == null){
			bar = new Bar(cantidadMesas);
		}
		return bar;
	}
	
	public void cambiarDia(int dia){
		try{
			NumeroDiaInvalido.method(dia, mes);
			this.dia = dia;
		}catch(NumeroDiaInvalido e){
			System.out.println("El dia es invalido");
		}
	}
	public void cambiarMes(int mes){
		try{
			NumeroMesInvalido.method(mes);
			this.mes = mes;
		}catch(NumeroMesInvalido e){
			System.out.println("El mes es invalido");
		}
	}
	public void cambiarAño(int año){
		try{
			NumeroAñoInvalido.method(año);
			this.año = año;
		}catch(NumeroAñoInvalido e){
			System.out.println("El Año es Invalido");
		}
		
	}
	
	public void ocuparMesa(int numeroMesa) throws NoPuedeSerOcupada, NoDebeTenerConsumiciones{
		mesas[numeroMesa-1].cambiarAOcupada();
	}
	
	public void desocuparMesa(int numeroMesa) throws EsDisponible, NoDebeTenerConsumiciones{
		if(mesas[numeroMesa-1].getConsumiciones() == 0){
			mesas[numeroMesa-1].cambiarADisponible();
		}else{
			Ticket aux;
			aux = mesas[numeroMesa-1].pedirCuenta(numeroMesa-1, dia, mes, año);
			aux.ImprimirConsumiciones();
			tickets.add(aux);	
		}
	}
	
	public void cerrarMesa(int numeroMesa) throws EsDisponible, NoDebeTenerConsumiciones{
		mesas[numeroMesa-1].cambiarADisponible();
	}
	
	public void crearCombo(ArrayList<Component> listaDeCreacion, int descuento,int codigo,String descripcion){
		if(carta.containsAll(listaDeCreacion)){
			Combos combo = new Combos(listaDeCreacion, descuento,codigo, descripcion);
		}else{
			System.out.println("El combo no pudo ser creado porque uno o mas productos no se encuentran en la carta");
		}
	}
	
	
	public void agregarConsumicion(int numeroMesa,int codigo,int cantidad) throws DebeSerPositivo{
		Iterator<Component> itr = carta.iterator();
		Component aux = null;
		boolean EncontroProducto = false;
		while(itr.hasNext() && !EncontroProducto){
			aux = itr.next();
			if(aux.getCodigo() == codigo){
				EncontroProducto = true;
			}
		}if(EncontroProducto){
			mesas[numeroMesa-1].agregarConsumision(aux, cantidad);
			System.out.println("Se ah agregado la consumicion del Producto");
		}else{
			System.out.println("Codigo de producto incorrecto");
		}
		
	}
	
	public boolean agregarProducto(double precioVenta, double precioCosto, Integer codigo, String descripcion ){
		Productos producto = new Productos(precioVenta, precioCosto, codigo, descripcion);
		return carta.add(producto);
	}
	
	public boolean eliminarProducto(int codigo){
		Iterator<Component> itr = carta.iterator();
		Component aux;
		boolean fueEliminado = false;
		while(itr.hasNext()){
			aux = itr.next();
			if(aux.getCodigo() == codigo){
				fueEliminado = carta.remove(aux);
			}
		}
		return fueEliminado;
	}
	
	public boolean eliminarProducto(String descripcion){
		Iterator<Component> itr = carta.iterator();
		Component aux;
		boolean fueEliminado = false;
		while(itr.hasNext()){
			aux = itr.next();
			if(aux.getDescripcion() == descripcion){
				fueEliminado = carta.remove(aux);
			}
		}
		return fueEliminado;
	}
	
	public void consultarProducto(int codigo){
		Iterator<Component> itr = carta.iterator();
		Component aux;
		boolean fueEncontrado = false;
		while(itr.hasNext()){
			aux = itr.next();
			if(aux.getCodigo() == codigo){
				fueEncontrado = true;
				System.out.println(aux.toString());
			}
		}
		if(!fueEncontrado){
			System.out.println("No fue encontrado el producto");
		}
	}
	
	public void consultarProducto(String descripcion){
		Iterator<Component> itr = carta.iterator();
		Component aux;
		boolean fueEncontrado = false;
		while(itr.hasNext()){
			aux = itr.next();
			if(aux.getDescripcion() == descripcion){
				fueEncontrado = true;
				System.out.println(aux.toString());
			}
		}
		if(!fueEncontrado){
			System.out.println("No fue encontrado el producto");
		}
	}
	
	public void cambiarPrecioVentaProducto(int codigo, double precioVenta){
		Iterator<Component> itr = carta.iterator();
		Productos aux;
		boolean fueEncontrado = false;
		while(itr.hasNext()){
			aux = (Productos) itr.next();
			if(aux.getCodigo() == codigo){
				fueEncontrado = true;
				aux.setPrecioVenta(precioVenta);
			}
		}
		if(!fueEncontrado){
			System.out.println("No fue encontrado el producto");
		}
	}
	
	public void cambiarPrecioVentaProducto(String descripcion, double precioVenta){
		Iterator<Component> itr = carta.iterator();
		Productos aux;
		boolean fueEncontrado = false;
		while(itr.hasNext()){
			aux = (Productos) itr.next();
			if(aux.getDescripcion() == descripcion){
				fueEncontrado = true;
				aux.setPrecioVenta(precioVenta);
			}
		}
		if(!fueEncontrado){
			System.out.println("No fue encontrado el producto");
		}
	}
	
	public void cambiarPrecioCostoProducto(int codigo, double precioCosto){
		Iterator<Component> itr = carta.iterator();
		Productos aux;
		boolean fueEncontrado = false;
		while(itr.hasNext()){
			aux = (Productos) itr.next();
			if(aux.getCodigo() == codigo){
				fueEncontrado = true;
				aux.setPrecioCosto(precioCosto);
			}
		}
		if(!fueEncontrado){
			System.out.println("No fue encontrado el producto");
		}
	}
	
	public void cambiarPrecioCostoProducto(String descripcion, double precioCosto){
		Iterator<Component> itr = carta.iterator();
		Productos aux;
		boolean fueEncontrado = false;
		while(itr.hasNext()){
			aux = (Productos) itr.next();
			if(aux.getDescripcion() == descripcion){
				fueEncontrado = true;
				aux.setPrecioCosto(precioCosto);
			}
		}
		if(!fueEncontrado){
			System.out.println("No fue encontrado el producto");
		}
	}
	
	public void cambiarDescripcion(int codigo, String descripcionNueva){
		Iterator<Component> itr = carta.iterator();
		Productos aux;
		boolean fueEncontrado = false;
		while(itr.hasNext()){
			aux = (Productos) itr.next();
			if(aux.getCodigo() == codigo){
				fueEncontrado = true;
				aux.setDescripcion(descripcionNueva);
			}
		}
		if(!fueEncontrado){
			System.out.println("No fue encontrado el producto");
		}
	}
	
	public void cambiarDescripcion(String descripcion, String descripcionNueva){
		Iterator<Component> itr = carta.iterator();
		Productos aux;
		boolean fueEncontrado = false;
		while(itr.hasNext()){
			aux = (Productos) itr.next();
			if(aux.getDescripcion() == descripcion){
				fueEncontrado = true;
				aux.setDescripcion(descripcionNueva);
			}
		}
		if(!fueEncontrado){
			System.out.println("No fue encontrado el producto");
		}
	}
	
	/*
	 * creo dos tickets auxiliares usando las fechas dadas y una mesa vacia para
	 * comparar las fechas de los tickets que tengo guardados con las fechas entre las 
	 * que tienen que estar los tickets que tengo que devolver
	 */
	public void listarTicketsEnFechas(int dia, int mes, int año, int dia2, int mes2, int año2){
		Iterator<Ticket> itr = tickets.iterator();
		Mesa mesaAuxiliar = new Mesa();
		Ticket ticketAuxiliarMenor = new Ticket(mesaAuxiliar, 0, dia, mes, año);
		Ticket ticketAuxiliarMayor = new Ticket(mesaAuxiliar, 0, dia2, mes2, año2);
		Ticket aux;
		while(itr.hasNext()){
			aux = itr.next();
			if((aux.compareTo(ticketAuxiliarMenor) >= 0) && (aux.compareTo(ticketAuxiliarMayor) <= 0)){
				aux.imprimirPorPantalla();
			}
		}
	}
	
	
	public void archivoTextoProductos() throws IOException{
		Iterator<Component> itr = carta.iterator();
		PriorityQueue<Component> cola = new PriorityQueue<Component>();
		while(itr.hasNext()){
			cola.add(itr.next());
		}
		FileWriter f0 = new FileWriter("C:\\txt\\archivoEmpresa.txt", true);
		Iterator<Component> itr2 = carta.iterator();
		while (itr2.hasNext()) {
			Component aux = itr2.next();
			f0.write(aux.toString() + "\n");
		}
		f0.close();
	}
	
	
}

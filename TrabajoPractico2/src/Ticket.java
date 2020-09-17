import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Ticket implements Comparable<Ticket> {
	private int numeroTicket;
	private Mesa mesa;
	private int dia;
	private int mes;
	private int año;
	private int numeroMesa;

	public Ticket(Mesa mesa, int numeroMesa, int dia, int mes, int año) {
		try {
			NumeroDiaInvalido.method(dia, mes);
			NumeroMesInvalido.method(mes);
			NumeroAñoInvalido.method(año);
			this.mesa = mesa;
			this.dia = dia;
			this.mes = mes;
			this.año = año;
			this.numeroMesa = numeroMesa;
		} catch (NumeroDiaInvalido e) {
			System.out.println("Numero de dia incorrecto");
		} catch (NumeroMesInvalido e) {
			System.out.println("Numero de mes incorrecto");
		} catch (NumeroAñoInvalido e) {
			System.out.println("Numero de año incorrecto");
		}

	}
	
	public void ImprimirConsumiciones(){
		HashMap<Integer, Integer> listaCantidades = mesa.getListaCantidades();
		HashMap<Integer, Double> listaPrecios = mesa.getListaPrecios();
		Set<Integer> lista = listaCantidades.keySet();
		Iterator<Integer> itr = lista.iterator();
		Integer codigo;
		while(itr.hasNext()){
			codigo = itr.next();
			System.out.println("Codigo Producto: "+""+codigo+"Cantidad: "+""+listaCantidades.get(codigo)+"Precio: "+""+listaPrecios.get(codigo));
		}
	}

	public void imprimirPorPantalla(){
		System.out.println("Numero de Mesa: "+""+numeroMesa+ "\n"+ 
				"Fecha: "+""+dia+"/"+""+mes+"/"+""+año+"\n"+ 
				"Consumisiones: "+"");
	}

	@Override
	public int compareTo(Ticket other) {
		Ticket ticket =  other;
		int resultado = 0;
		if(año < ticket.año){
			resultado = -1;
		}
		if(año > ticket.año){
			resultado = 1;
		}
		if(año == ticket.año){
			if(mes < ticket.mes){
				resultado = -1;
			}
			if(mes > ticket.mes){
				resultado = 1;
			}
			if(mes == ticket.mes){
				if(dia < ticket.dia){
					resultado = -1;
				}
				if(dia > ticket.dia){
					resultado = 1;
				}
			}
		}
		
		return resultado;
	}
	
	public void asignarNumeroTicket(int numeroTicket){
		this.numeroTicket = numeroTicket;
	}
	
}

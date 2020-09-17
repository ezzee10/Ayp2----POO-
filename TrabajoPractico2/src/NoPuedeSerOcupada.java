public class NoPuedeSerOcupada extends Exception {
	public static void method(Estado estado) throws NoPuedeSerOcupada{
		if(estado == Estado.Ocupada || estado == Estado.Cerrada){
			throw new NoPuedeSerOcupada();
		}
	}
}

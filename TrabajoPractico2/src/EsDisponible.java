
public class EsDisponible extends Exception {
	public static void method(Estado estado) throws EsDisponible{
		if(estado == Estado.Disponible){
			throw new EsDisponible();
		}
	}
}

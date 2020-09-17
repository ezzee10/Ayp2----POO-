
public class EstaCerrada extends Exception {
	public static void method(Estado estado) throws EstaCerrada{
		if(estado == Estado.Cerrada){
			throw new EstaCerrada();
		}
	}
}

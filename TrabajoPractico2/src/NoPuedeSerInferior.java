
public class NoPuedeSerInferior extends Exception{
	public static void method(double valor1, double valor2) throws NoPuedeSerInferior{
		if(valor1 > valor2){
			throw new NoPuedeSerInferior();
		}
	}
}

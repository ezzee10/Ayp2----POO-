
public class NumeroDiaInvalido extends Exception{
	public static void method(int dia, int mes) throws NumeroDiaInvalido{
		
		if((mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) && (dia > 31 || dia <= 0)){
			throw new NumeroDiaInvalido();
		}
		if((mes == 4|| mes == 6 || mes == 9 || mes == 11) && (dia > 30 || dia <= 0)){
			throw new NumeroDiaInvalido();
		}
		
		if((mes == 2) && (dia > 28 || dia <= 0)){
			throw new NumeroDiaInvalido();
		}
		
	}
}

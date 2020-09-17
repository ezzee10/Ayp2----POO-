public class NumeroAñoInvalido extends Exception {
	public static void method(int año) throws NumeroAñoInvalido {
		if (año < 2017) {
			throw new NumeroAñoInvalido();
		}
	}
}


public class NumeroA�oInvalido extends Exception {
	public static void method(int a�o) throws NumeroA�oInvalido {
		if (a�o < 2017) {
			throw new NumeroA�oInvalido();
		}
	}
}


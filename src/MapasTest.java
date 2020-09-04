import java.util.Map;

public class MapasTest {
	public static void main(String[] args) {
		try {
			Mapa mapa = new Mapa();
			Map<String, String> dados = null;
//			dados = mapa.get("-8.0314895", "-34.9375628");   // Iputinga
//			dados = mapa.get("-8.0268232", "-34.9499354");   // Nova Morada
			dados = mapa.get("-7.50272635", "-35.3132763");  
			
			System.out.println("Bairro: " + dados.get("bairro"));
			System.out.println("Municipio: " + dados.get("municipio"));			
		} catch (Exception e) {
			System.err.println("Deu merda. trace: " + e);
		}
	}
}

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

public class Mapa {
	private final String USER_AGENT = "Mozilla/5.0";
	public String bairro = "";
	
	private String urlBase = "https://mapas.pe.gov.br";

	protected Map<String, String> get(String lat, String lon) throws Exception {
		Map<String, String> data = new HashMap<String, String>();

		String url = this.urlBase + 
				"/nominatim/reverse?format=json&lat=" + 
				lat + "&lon=" + 
				lon + "&zoom=14&addressdetails=1";
		URL obj = new URL(url);

		HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

		conn.setRequestMethod("GET");
		conn.setRequestProperty("User-Agent", USER_AGENT);

//		int responseCode = conn.getResponseCode();
//		System.out.println("Response Code: " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		JSONObject json = new JSONObject(response.toString());
		JSONObject endereco = (JSONObject) json.get("address");

		try {
			this.bairro = endereco.get("suburb").toString();
		} catch (Exception e) {
			this.bairro = "Centro";
		}
		
		data.put("bairro", this.bairro);
		data.put("municipio", endereco.get("city_district").toString());

		return data;
	}
}

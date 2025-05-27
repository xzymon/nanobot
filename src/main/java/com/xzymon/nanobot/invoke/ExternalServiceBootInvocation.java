package com.xzymon.nanobot.invoke;

import com.xzymon.nanobot.client.NoteClient;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class ExternalServiceBootInvocation {

	private NoteClient client = new NoteClient();

	@PostConstruct
	public void invoke() {
		System.out.println("External service booted");

		try {
			String url = "http://localhost:8080/api/v1/note";

			// Przykład użycia metody POST
			String postBody = """
					{
					  "title": "Korelacja na rynkach",
					  "category": "MARKET_FORECASTS",
					  "content": "USDJPY jest dodatnio skorelowany z indeksami: gdy USDJPY rośnie - rosną indeksy. Inaczej mówiąc: wzrosty USDJPY są sygnalizacją sentymentu risk-on, spadki - sygnalizują sentyment risk-off.",
					  "timeReference": "2025-05-09T12:00:00.000Z",
					  "tags": [
					    "JPY",
					    "USD",
					    "sentyment"
					  ],
					  "source": "https://www.youtube.com/watch?v=7v9BysNUju8",
					  "additionalInfo": "TO KLASYCZNY ZNAK HOSSY! Ktoś skupuje tanie coiny... | Podsumowanie tygodnia na rynkach 09.05.2025",
					  "authorName": "Tomasz Piwoński"
					}
                """;

			String postResponse = client.performPostRequest(url, postBody);
			System.out.println("POST Response: " + postResponse);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			client.close();
		}
	}
}

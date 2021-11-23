package JUnit;

import com.integrador4.controller.ClientController;
import com.integrador4.entity.Client;
import com.integrador4.service.ClientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.Optional;
import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
@SpringBootTest( classes = {ClientController.class})
@AutoConfigureMockMvc
public class ClientControllerTest {

	private static final int CLIENT_ID = 1;
	private static final String BASE_URL = "/market/clients";
	private static final String SPECIFIC_URL = "/{client-id}";

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ClientService clientService;

	@Test
	public void getClient() throws Exception {
		Optional<Client> client = Optional.of(new Client("Gaston", "Sanchez"));

		Mockito.when(clientService.getById(CLIENT_ID)).thenReturn(client);

		mvc.perform(
			MockMvcRequestBuilders.get(BASE_URL + SPECIFIC_URL, CLIENT_ID)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
		).andExpect(MockMvcResultMatchers.status().isOk());

		Mockito.verify(clientService, times(1)).getById(CLIENT_ID);
	}

	@Test
	public void getClientReturnsNotFound() throws Exception {
		Mockito.when(clientService.getById(CLIENT_ID)).thenReturn(Optional.empty());

		mvc.perform(
			MockMvcRequestBuilders.get(BASE_URL + SPECIFIC_URL, CLIENT_ID)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
		).andExpect(MockMvcResultMatchers.status().isNotFound());

		Mockito.verify(clientService, Mockito.never()).getById(CLIENT_ID);
	}

}

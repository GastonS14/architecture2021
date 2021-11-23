package JUnit;

import com.integrador4.entity.Client;
import com.integrador4.repository.ClientRepository;
import com.integrador4.service.ClientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

	public static final int CLIENT_ID = 1;

	@Mock
	private ClientRepository clientRepository;

	@InjectMocks
	private ClientService clientService;

	@Test
	public void givenClientWhenFindByIdIsCalledThenReturnProperlyClient() {
		Optional<Client> client = Optional.of(new Client("Gaston", "Sanchez"));

		Mockito.when(clientRepository.findById(CLIENT_ID)).thenReturn(client);

		Optional<Client> actual = clientService.getById(CLIENT_ID);
		assertEquals(client, actual);
	}
}

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
import java.util.ArrayList;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

	private static final int CLIENT_ID = 1;
	private static final Client DEFAULT_CLIENT = new Client("Gaston", "Sanchez");
	private static final Optional<Client> DEFAULT_OPTIONAL_CLIENT = Optional.of(DEFAULT_CLIENT);

	@Mock
	private ClientRepository clientRepository;

	@InjectMocks
	private ClientService clientService;

	@Test
	public void givenClientWhenFindByIdIsCalledThenReturnProperlyClient() {

		Mockito.when(clientRepository.findById(CLIENT_ID)).thenReturn(DEFAULT_OPTIONAL_CLIENT);

		Optional<Client> actual = clientService.getById(CLIENT_ID);
		assertEquals(DEFAULT_OPTIONAL_CLIENT, actual);
	}

	@Test
	public void givenAClientsWhenFindAllIsCalledThenReturnAllTheClients() {
		ArrayList<Client> clients = new ArrayList<>();
		clients.add(new Client("Juan", "Segundo"));
		clients.add(new Client("Pedro", "Segundo"));
		clients.add(new Client("DelSeñor", "Segundo"));

		Mockito.when(clientRepository.findAll()).thenReturn(clients);

		Iterable<Client> actual = clientService.getAll();
		assertEquals(clients, actual);
	}

	@Test
	public void givenClientWhenDeleteIsCalledThenReturnProperlyDeletedClient() {

		Mockito.when(clientRepository.findById(CLIENT_ID)).thenReturn(DEFAULT_OPTIONAL_CLIENT);
		Mockito.doNothing().when(clientRepository).delete(DEFAULT_CLIENT);

		Optional<Client> actual = clientService.delete(CLIENT_ID);

		assertEquals(DEFAULT_OPTIONAL_CLIENT, actual);
	}
}

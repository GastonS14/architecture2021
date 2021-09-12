package extensions;

import java.io.File;
import java.util.Objects;

public class ResourceLoader {

	/**
	 * @param fileName name of the file, the route should be after resource/
	 *                    example: csv/clientes.csv
	 * @return file path
	 * @throws NullPointerException if String is null
	 */
	public static String getString(String fileName) {
		ClassLoader classLoader = ResourceLoader.class.getClassLoader();
		File file = new File((Objects.requireNonNull(classLoader.getResource(fileName))).getFile());
		return file.getPath();
	}

}

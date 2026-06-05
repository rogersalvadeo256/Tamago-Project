package repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class XmlRepository {

    private static final Map<Class<?>, JAXBContext> CONTEXT_CACHE = new ConcurrentHashMap<>();

    private final Path baseDirectory;

    public XmlRepository(Path baseDirectory) {
        this.baseDirectory = baseDirectory;
    }

    public <T> T read(String fileName, Class<T> type, Supplier<T> defaultSupplier) {
        Path filePath = baseDirectory.resolve(fileName);
        if (!Files.exists(filePath)) {
            return defaultSupplier.get();
        }

        try {
            JAXBContext context = getContext(type);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Object value = unmarshaller.unmarshal(filePath.toFile());
            return type.cast(value);
        } catch (JAXBException ex) {
            return defaultSupplier.get();
        }
    }

    public <T> void write(String fileName, T value, Class<T> type) {
        Path filePath = baseDirectory.resolve(fileName);
        try {
            Files.createDirectories(baseDirectory);
            JAXBContext context = getContext(type);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(value, filePath.toFile());
        } catch (JAXBException | IOException ignored) {
        }
    }

    private JAXBContext getContext(Class<?> type) throws JAXBException {
        JAXBContext cached = CONTEXT_CACHE.get(type);
        if (cached != null) {
            return cached;
        }

        JAXBContext created = JAXBContext.newInstance(type);
        CONTEXT_CACHE.put(type, created);
        return created;
    }
}

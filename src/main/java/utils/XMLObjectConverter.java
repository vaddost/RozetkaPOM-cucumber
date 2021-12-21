package utils;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;


public class  XMLObjectConverter<T> {
    @SuppressWarnings("unchecked")
    public T convertXMLToObject(String filePath, Class<T> clazz) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        return (T) jaxbUnmarshaller.unmarshal(new File(filePath));
    }
}

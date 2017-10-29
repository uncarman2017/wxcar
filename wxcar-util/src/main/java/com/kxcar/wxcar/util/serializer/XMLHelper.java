package com.kxcar.wxcar.util.serializer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.stream.XMLStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Created by jiaweiyu on 2016/11/1.
 */
public class XMLHelper {


    public static <T> T toObject(String xml,Class<T> type) throws Exception
    {
        JAXBContext jaxbContext = JAXBContext.newInstance(type);
        StringReader reader = new StringReader(xml);
        Unmarshaller jaxbMarshaller = jaxbContext.createUnmarshaller();
        T result = (T)jaxbMarshaller.unmarshal(reader);
        reader.close();
        return result;
    }

    public static <T> String toXML(T entity) throws Exception
    {
        JAXBContext jaxbContext = JAXBContext.newInstance(entity.getClass());
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        StringWriter writer = new StringWriter();
        // output pretty printed
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING,"UTF-8");

        jaxbMarshaller.marshal(entity,writer);

        String result = writer.toString();
        writer.close();
        return result;
    }




}
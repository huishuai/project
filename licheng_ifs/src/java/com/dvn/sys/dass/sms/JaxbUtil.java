package com.dvn.sys.dass.sms;

import com.sun.xml.bind.marshaller.CharacterEscapeHandler;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

public class JaxbUtil {

    public static Object getRequest(byte[] requestContent, Class clazz)
            throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(new Class[]{clazz});
        Unmarshaller unmarshaller = context.createUnmarshaller();
        InputStream inputStream = new ByteArrayInputStream(requestContent);
        Object obj = unmarshaller.unmarshal(inputStream);
        try {
            inputStream.close();
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
            throw new IOException();
        }
        return obj;
    }

    /**
     * afadsfasfa
     */
    public static Object getRequest(String requestContent, Class clazz)
            throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(new Class[]{clazz});
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Reader reader = new StringReader(requestContent);
        Object obj = unmarshaller.unmarshal(reader);
        try {
            reader.close();
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
            throw new IOException();
        }
        return obj;
    }

    public static byte[] putResponse(Object responseObject, Class clazz, String charset)
            throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(new Class[]{clazz});
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty("jaxb.encoding", charset);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        marshaller.marshal(responseObject, outputStream);
        byte[] bytes = outputStream.toByteArray();
        try {
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
            throw new IOException();
        }
        return bytes;
    }

    public static byte[] putResponse(Object responseObject, Class clazz, String charset, CharacterEscapeHandler characterEscapeHandler)
            throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(new Class[]{clazz});
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty("jaxb.encoding", charset);
        marshaller.setProperty("com.sun.xml.bind.characterEscapeHandler",
                characterEscapeHandler);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        marshaller.marshal(responseObject, outputStream);
        byte[] bytes = outputStream.toByteArray();
        try {
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
            throw new IOException();
        }
        return bytes;
    }


}

package common.helper.transformers;

import common.helper.exceptions.XMLException;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public final class XMLTransformer {
    private XMLTransformer() {}

    public static Document loadXML(String xmlStr) throws XMLException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            InputSource inputSource = new InputSource(new StringReader(xmlStr));
            return documentBuilder.parse(inputSource);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new XMLException("Exception while loading the xml", e);
        }
    }

    public static String parseXML(Document xmlDocument) throws XMLException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();

        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();

            StringWriter stringWriter = new StringWriter();

            transformer.transform(new DOMSource(xmlDocument), new StreamResult(stringWriter));

            return stringWriter.getBuffer().toString();
        } catch (TransformerException e) {
            throw new XMLException("Exception while parsing the xml", e);
        }
    }
}

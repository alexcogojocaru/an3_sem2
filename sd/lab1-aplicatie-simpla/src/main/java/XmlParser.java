import beans.StudentBean;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlParser {
    private static final int NUME = 0;
    private static final int PRENUME = 1;
    private static final int VARSTA = 2;
    private static final int GRUPA = 3;
    private static final int MEDIE = 4;

    static void addStudent(StudentBean bean, String filePath) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document doc = documentBuilder.parse(filePath);

        Element rootElement = doc.getDocumentElement(); // <studenti>
        Element studentBeanElement = doc.createElement("StudentBean");

        Element nume = doc.createElement("nume");
        nume.appendChild(doc.createTextNode(bean.getNume()));
        studentBeanElement.appendChild(nume);

        Element prenume = doc.createElement("prenume");
        prenume.appendChild(doc.createTextNode(bean.getPrenume()));
        studentBeanElement.appendChild(prenume);

        Element varsta = doc.createElement("varsta");
        varsta.appendChild(doc.createTextNode(Integer.toString(bean.getVarsta())));
        studentBeanElement.appendChild(varsta);

        Element grupa = doc.createElement("grupa");
        grupa.appendChild(doc.createTextNode(bean.getGrupa()));
        studentBeanElement.appendChild(grupa);

        Element medie = doc.createElement("medie");
        medie.appendChild(doc.createTextNode(Double.toString(bean.getMedie())));
        studentBeanElement.appendChild(medie);

        rootElement.appendChild(studentBeanElement);

        DOMSource source = new DOMSource(doc);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        StreamResult result = new StreamResult(filePath);
        transformer.transform(source, result);
    }

    static StudentBean searchStudent(String name, String filePath) throws ParserConfigurationException, IOException, SAXException {
        StudentBean bean = null;

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document doc = documentBuilder.parse(filePath);

        Element rootElement = doc.getDocumentElement();
        NodeList students = rootElement.getElementsByTagName("StudentBean");

        for (int i = 0; i < students.getLength(); i++) {
            List<Node> filteredNodes = new ArrayList<>();
            NodeList childs = students.item(i).getChildNodes();

            for (int j = 0; j < childs.getLength(); j++) {
                if (childs.item(j).getNodeType() == Node.ELEMENT_NODE) {
                    filteredNodes.add(childs.item(j));
                }
            }

            if (name.equals(filteredNodes.get(NUME).getTextContent())) {
                bean = new StudentBean();
                bean.setNume(filteredNodes.get(NUME).getTextContent());
                bean.setPrenume(filteredNodes.get(PRENUME).getTextContent());
                bean.setVarsta(Integer.parseInt(filteredNodes.get(VARSTA).getTextContent()));
                bean.setGrupa(filteredNodes.get(GRUPA).getTextContent());
                bean.setMedie(Double.parseDouble(filteredNodes.get(MEDIE).getTextContent()));

                break;
            }
        }

        return bean;
    }
}

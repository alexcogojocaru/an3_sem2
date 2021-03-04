import beans.StudentBean;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.time.Year;

public class ProcessStudentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nume = request.getParameter("nume");
        String prenume = request.getParameter("prenume");
        int varsta = Integer.parseInt(request.getParameter("varsta"));
        String grupa = request.getParameter("grupa");
        double medie = Double.parseDouble(request.getParameter("medie"));

        int anNastere = Year.now().getValue() - varsta;

        XmlMapper mapper = new XmlMapper();
        StudentBean bean = new StudentBean();

        bean.setNume(nume);
        bean.setPrenume(prenume);
        bean.setVarsta(varsta);
        bean.setGrupa(grupa);
        bean.setMedie(medie);

        mapper.writeValue(new File("/home/alex/Desktop/an3_sem2/sd/lab1-aplicatie-simpla/src/main/resources/student.xml"), bean);

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File("/home/alex/Desktop/an3_sem2/sd/lab1-aplicatie-simpla/src/main/resources/studenti.xml"));

            Element rootElement = doc.getDocumentElement();

            Element studentBean = doc.createElement("StudentBean");

            Element numeElement = doc.createElement("nume");
            numeElement.appendChild(doc.createTextNode(nume));
            studentBean.appendChild(numeElement);

            Element prenumeElement = doc.createElement("prenume");
            prenumeElement.appendChild(doc.createTextNode(prenume));
            studentBean.appendChild(prenumeElement);

            Element varstaElement = doc.createElement("varsta");
            varstaElement.appendChild(doc.createTextNode(Integer.toString(varsta)));
            studentBean.appendChild(varstaElement);

            Element grupaElement = doc.createElement("grupa");
            grupaElement.appendChild(doc.createTextNode(grupa));
            studentBean.appendChild(grupaElement);

            Element medieElement = doc.createElement("medie");
            medieElement.appendChild(doc.createTextNode(Double.toString(medie)));
            studentBean.appendChild(medieElement);

            rootElement.appendChild(studentBean);

            DOMSource source = new DOMSource(doc);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            StreamResult result = new StreamResult("/home/alex/Desktop/an3_sem2/sd/lab1-aplicatie-simpla/src/main/resources/studenti_copy.xml");
            transformer.transform(source, result);
        } catch (ParserConfigurationException | SAXException | TransformerException e) {
            e.printStackTrace();
        }

        request.setAttribute("nume", nume);
        request.setAttribute("prenume", prenume);
        request.setAttribute("varsta", varsta);
        request.setAttribute("grupa", grupa);
        request.setAttribute("medie", medie);
        request.setAttribute("anNastere", anNastere);
        request.getRequestDispatcher("./info-student.jsp").forward(request, response);
    }
}

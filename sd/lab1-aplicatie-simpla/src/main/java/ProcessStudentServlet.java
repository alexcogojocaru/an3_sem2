import beans.StudentBean;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
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
            XmlParser.addStudent(bean, "/home/alex/Desktop/an3_sem2/sd/lab1-aplicatie-simpla/src/main/resources/studenti.xml");
        } catch (ParserConfigurationException | TransformerException | SAXException e) {
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

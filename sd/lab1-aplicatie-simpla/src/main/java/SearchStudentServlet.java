import beans.StudentBean;
import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class SearchStudentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nume = request.getParameter("nume");

        StudentBean bean = null;

        try {
            bean = XmlParser.searchStudent(nume, "/home/alex/Desktop/an3_sem2/sd/lab1-aplicatie-simpla/src/main/resources/studenti.xml");
        } catch (ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }

        if (bean != null) {
            request.setAttribute("nume", bean.getNume());
            request.setAttribute("prenume", bean.getPrenume());
            request.setAttribute("varsta", bean.getVarsta());
            request.setAttribute("grupa", bean.getGrupa());
            request.setAttribute("medie", bean.getMedie());
//        request.setAttribute("anNastere", anNastere);
            request.getRequestDispatcher("./info-student.jsp").forward(request, response);
        }
        else {
            response.getWriter().print("Studentul nu a fost gasit");
        }
    }
}

import ejb.StudentEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class FetchStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bazaDeDataSQLite");
        EntityManager manager = entityManagerFactory.createEntityManager();

        StringBuilder responseText = new StringBuilder();
        responseText.append("<h2>Lista studenti</h2>");
        responseText.append("<table border=1><thead>" +
                "<tr>" +
                "<th>ID</th>" +
                "<th>Nume</th>" +
                "<th>Prenume</th>" +
                "<th>Varsta</th></thead>");
        responseText.append("<tbody>");

        // preluare studenti din baza de date
        TypedQuery<StudentEntity> query = manager.createQuery(
                "select student from StudentEntity student",
                StudentEntity.class
        );

        List<StudentEntity> results = query.getResultList();
        for (StudentEntity stud : results) {
            // se creeaza un rand in tabel pentru fiecare student gasit
            responseText.append(
                    "<tr>" +
                    "<td>" + stud.getId() + "</td>" +
                    "<td>" + stud.getNume() + "</td>" +
                    "<td>" + stud.getPrenume() + "</td>" +
                    "<td>" + stud.getVarsta() + "</td></tr>"
            );
        }

        responseText.append("</tbody></table><br /><br />" +
                "<a href='./'>Inapoi la meniul principal</a>");

        manager.close();
        entityManagerFactory.close();

        resp.setContentType("text/html");
        resp.getWriter().print(responseText.toString());
    }
}

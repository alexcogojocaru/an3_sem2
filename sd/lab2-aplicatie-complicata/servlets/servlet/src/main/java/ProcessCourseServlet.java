import ejb.CourseEntity;
import ejb.StudentEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProcessCourseServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nume = req.getParameter("nume");

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bazaDeDataSQLite-cursuri");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setName(nume);
        courseEntity.setStuds(0);

        // adaugare entitate in baza de date (tranzactie)
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(courseEntity);
        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();

        // trimite date catre client (pagina web)
        resp.setContentType("text/html");
        resp.getWriter().println("Datele au fost adaugate in baza de date."
                + "<br /><br /><a href='./'>Inapoi la meniul principal</a>");

    }
}

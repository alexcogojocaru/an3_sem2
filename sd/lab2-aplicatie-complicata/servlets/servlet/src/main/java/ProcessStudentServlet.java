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

public class ProcessStudentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String nume = req.getParameter("nume");
        String prenume = req.getParameter("prenume");
        int varsta = Integer.parseInt(req.getParameter("varsta"));

        // pregatire EntityManager pentru StudentEntity
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bazaDeDataSQLite");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setNume(nume);
        studentEntity.setPrenume(prenume);
        studentEntity.setVarsta(varsta);

        // adaugare entitate in baza de date (tranzactie)
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(studentEntity);
        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();

        // trimite date catre client (pagina web)
        resp.setContentType("text/html");
        resp.getWriter().println("Datele au fost adaugate in baza de date."
                        + "<br /><br /><a href='./'>Inapoi la meniul principal</a>");
    }
}

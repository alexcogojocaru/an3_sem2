import ejb.StudentEntity;

import javax.persistence.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DeleteStudentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String nume = req.getParameter("nume");
        int id = -1;

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("bazaDeDataSQLite");
        EntityManager manager = factory.createEntityManager();

        EntityTransaction entityTransaction = manager.getTransaction();
        entityTransaction.begin();

        TypedQuery<StudentEntity> query = manager.createQuery(
                "select student from StudentEntity student where student.nume = " +
                        "'" + nume + "'",
                StudentEntity.class
        );

        List<StudentEntity> result = query.getResultList();
        StringBuilder message = new StringBuilder();

        if (result.size() == 0) {
            message.append("Studentul " + nume + " nu a fost gasit");
        }
        else {
            id = result.get(0).getId();
            message.append("Studentul " + nume + " a fost sters din baza de date");


            TypedQuery<StudentEntity> deleteQuery = manager.createQuery(
                    "delete from StudentEntity studentEntity where studentEntity.id = " + id,
                    StudentEntity.class
            );

            deleteQuery.executeUpdate();
        }

        entityTransaction.commit();
        manager.close();
        factory.close();

        message.append("<br />" +
                "<a href='./'>Inapoi la meniul principal</a>");

        resp.setContentType("text/html");
        resp.getWriter().print(message);
    }
}

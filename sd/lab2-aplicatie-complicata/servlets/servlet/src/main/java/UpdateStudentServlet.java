import ejb.StudentEntity;

import javax.persistence.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UpdateStudentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String nume = req.getParameter("nume");
        String newName = req.getParameter("nume-nou");
        String prenume = req.getParameter("prenume");
        int varsta = Integer.parseInt(req.getParameter("varsta"));

        StringBuilder message = new StringBuilder();
        int id = -1;

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("bazaDeDataSQLite");
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        transaction.begin();

        TypedQuery<StudentEntity> query = manager.createQuery(
                "select student from StudentEntity student where student.nume = " +
                        "'" + nume + "'",
                StudentEntity.class
        );

        List<StudentEntity> result = query.getResultList();
        if (result.size() == 0) {
            message.append("Studentul " + nume + " nu a fost gasit");
        }
        else {
            id = result.get(0).getId();

            message.append("Studentul " + nume + " nu a fost actualizat");
            TypedQuery<StudentEntity> updateQuery = manager.createQuery(
                    "update StudentEntity " +
                            "set nume = '" + newName + "'," +
                            "prenume = '" + prenume + "'," +
                            "varsta = '" + varsta + "'" +
                            "where id = " + id,
                    StudentEntity.class
            );

            updateQuery.executeUpdate();
        }

        transaction.commit();
        manager.close();
        factory.close();

        message.append("<br />" +
                "<a href='./'>Inapoi la meniul principal</a>");

        resp.setContentType("text/html");
        resp.getWriter().print(message);
    }
}

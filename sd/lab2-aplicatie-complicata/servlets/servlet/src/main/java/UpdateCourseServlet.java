import ejb.CourseEntity;
import ejb.StudentEntity;

import javax.persistence.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UpdateCourseServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nume = req.getParameter("nume");
        int studs = Integer.parseInt(req.getParameter("studs"));

        StringBuilder message = new StringBuilder();

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("bazaDeDataSQLite-cursuri");
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        transaction.begin();

        TypedQuery<CourseEntity> query = manager.createQuery(
                "select course from CourseEntity course where course.name = " +
                        "'" + nume + "'",
                CourseEntity.class
        );

        List<CourseEntity> result = query.getResultList();
        if (result.size() == 0) {
            message.append("Cursul " + nume + " nu a fost gasit");
        }
        else {
            int id = result.get(0).getId();

            message.append("Cursul " + nume + " a fost actualizat");
            TypedQuery<CourseEntity> updateQuery = manager.createQuery(
                    "update CourseEntity " +
                            "set studs = '" + studs + "'" +
                            "where id = " + id,
                    CourseEntity.class
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

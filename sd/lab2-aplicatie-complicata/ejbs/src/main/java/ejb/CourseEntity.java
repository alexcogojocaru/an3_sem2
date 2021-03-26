package ejb;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CourseEntity {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private int studs;

    public CourseEntity() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStuds() {
        return studs;
    }

    public void setStuds(int studs) {
        this.studs = studs;
    }
}

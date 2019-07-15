package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "hibernate_projects", schema = "public", catalog = "hibernate1")
public class HibernateProjectsEntity {
    private int id;
    private String projectName;

    public HibernateProjectsEntity(){}

    public HibernateProjectsEntity(String projectName){
        this.projectName = projectName;
    }

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "PROJECT_NAME", nullable = false, length = 50)
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Override
    public String toString() {
        return "HibernateProjectsEntity{" +
                "id=" + id +
                ", projectName='" + projectName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HibernateProjectsEntity that = (HibernateProjectsEntity) o;
        return id == that.id &&
                projectName.equals(that.projectName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, projectName);
    }
}

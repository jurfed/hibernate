package entities;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Entity
@Table(name = "hibernate_developers", schema = "public", catalog = "hibernate1")
public class HibernateDevelopersEntity {
    private int id;
    private String firstName;
    private String lastName;
    private Integer experience;
    private HibernateSpecialitiesEntity hibernateSpecialitiesEntity;
    private List<HibernateProjectsEntity> projects;
    private Map<String, HibernateKnowledgeEntity> knowledges;

    public HibernateDevelopersEntity() {

    }

    public HibernateDevelopersEntity(int id, String firstName, String lastName, HibernateSpecialitiesEntity hibernateSpecialitiesEntity, Integer experience) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.experience = experience;
        this.hibernateSpecialitiesEntity = hibernateSpecialitiesEntity;

    }

    public HibernateDevelopersEntity(String firstName, String lastName, HibernateSpecialitiesEntity hibernateSpecialitiesEntity, Integer experience) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.experience = experience;
        this.hibernateSpecialitiesEntity = hibernateSpecialitiesEntity;

    }

    @ManyToOne
    @JoinColumn(name = "specialty", referencedColumnName = "speciality")
    public HibernateSpecialitiesEntity getHibernateSpecialitiesEntity() {
        return hibernateSpecialitiesEntity;
    }

    public void setHibernateSpecialitiesEntity(HibernateSpecialitiesEntity hibernateSpecialitiesEntity) {
        this.hibernateSpecialitiesEntity = hibernateSpecialitiesEntity;
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
    @Column(name = "first_name", nullable = true, length = 50)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name", nullable = true, length = 50)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "experience", nullable = true)
    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    @OneToMany
    @JoinColumn(name = "id", referencedColumnName = "developerid")
    public List<HibernateProjectsEntity> getProjects() {
        return projects;
    }

    public void setProjects(List<HibernateProjectsEntity> projects) {
        this.projects = projects;
    }

    public Map<String, HibernateKnowledgeEntity> getKnowledges() {
        return knowledges;
    }

    public void setKnowledges(Map<String, HibernateKnowledgeEntity> knowledges) {
        this.knowledges = knowledges;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HibernateDevelopersEntity that = (HibernateDevelopersEntity) o;

        if (id != that.id) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (experience != null ? !experience.equals(that.experience) : that.experience != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (experience != null ? experience.hashCode() : 0);
        return result;
    }

    String details;

    @Override
    public String toString() {
    details = "HibernateDevelopersEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", experience=" + experience +
                ", hibernateSpecialitiesEntity=" + hibernateSpecialitiesEntity.getSpeciality() +
                '}';

        Optional<List<HibernateProjectsEntity>> optionalList = Optional.ofNullable(projects);
        optionalList.ifPresent(list -> {
            details+=list.toString();
        });

        Optional<Map<String, HibernateKnowledgeEntity>> optionalList2 = Optional.ofNullable(knowledges);
        optionalList2.ifPresent(map -> {
            details+=map.toString();
        });

        return details;
    }
}

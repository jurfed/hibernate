package entities;

import javax.persistence.*;

@Entity
@Table(name = "hibernate_specialities", schema = "public", catalog = "hibernate1")
public class HibernateSpecialitiesEntity {
    private int id;
    private String speciality;

    public HibernateSpecialitiesEntity() {
    }

    public HibernateSpecialitiesEntity(int id, String speciality) {
        this.id = id;
        this.speciality = speciality;
    }

    public HibernateSpecialitiesEntity(String speciality) {
        this.speciality = speciality;
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
    @Column(name = "speciality", nullable = true, length = 50)
    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HibernateSpecialitiesEntity that = (HibernateSpecialitiesEntity) o;

        if (id != that.id) return false;
        if (speciality != null ? !speciality.equals(that.speciality) : that.speciality != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (speciality != null ? speciality.hashCode() : 0);
        return result;
    }

}

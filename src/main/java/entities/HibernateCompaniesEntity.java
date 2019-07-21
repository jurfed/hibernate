package entities;

import java.util.Objects;

public class HibernateCompaniesEntity {

    private int id;
    private String companyName;

    public HibernateCompaniesEntity(){

    }

    public HibernateCompaniesEntity(String companyName) {
        this.companyName = companyName;
    }

    public HibernateCompaniesEntity(int id, String companyName) {
        this.id = id;
        this.companyName = companyName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HibernateCompaniesEntity that = (HibernateCompaniesEntity) o;
        return id == that.id &&
                Objects.equals(companyName, that.companyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, companyName);
    }


    @Override
    public String toString() {
        return "HibernateCompaniesEntity{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}

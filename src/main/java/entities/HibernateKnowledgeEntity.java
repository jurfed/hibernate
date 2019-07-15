package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "HIBERNATE_KNOWLEDGE", schema = "public", catalog = "hibernate1")
public class HibernateKnowledgeEntity {
    private int id;
    private String knowledgeName;
    private String commentariy;

    public HibernateKnowledgeEntity(){}

    public HibernateKnowledgeEntity(String commentariy){
        this.commentariy = commentariy;
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
    @Column(name = "KNOWLEDGE_NAME", nullable = false, length = 50)
    public String getKnowledgeName() {
        return knowledgeName;
    }

    public void setKnowledgeName(String knowledgeName) {
        this.knowledgeName = knowledgeName;
    }


    @Basic
    @Column(name = "comment", nullable = true, length = 50)
    public String getCommentariy() {
        return commentariy;
    }

    public void setCommentariy(String commentariy) {
        this.commentariy = commentariy;
    }

    @Override
    public String toString() {
        return "HibernateKnowledgeEntity{" +
                "id=" + id +
                ", knowledgeName='" + knowledgeName + '\'' +
                ", commentariy='" + commentariy + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HibernateKnowledgeEntity that = (HibernateKnowledgeEntity) o;
        return id == that.id &&
                commentariy.equals(that.commentariy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, commentariy);
    }
}

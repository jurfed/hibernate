import entities.HibernateDevelopersEntity;
import entities.HibernateKnowledgeEntity;
import entities.HibernateProjectsEntity;
import entities.HibernateSpecialitiesEntity;
import org.hibernate.*;
import org.hibernate.loader.custom.sql.SQLCustomQuery;
import org.hibernate.loader.custom.sql.SQLQueryParser;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.cfg.Configuration;

import javax.persistence.metamodel.EntityType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private static final SessionFactory ourSessionFactory;
    private static Session session;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception {

        //1 save to bd new developers
        Main main = new Main();
        main.addDeveloperToBd("Ivan", "Ivanov", "developer", 4);
        main.addDeveloperToBd("Petiya", "petrov", "analitic", 21);
        main.addDeveloperToBd("Masha", "Sidiriva", "tester", 3);
        main.addDeveloperToBd("Vasa", "Pupkin", "developer", 2);
        main.addDeveloperToBd("John", "Johanson", "marketolog", 10);
        //2 print all data in bd
        main.developersList();

        //3 update developers in bd by any parameter, for example name='Ivan'
        session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from HibernateDevelopersEntity where firstName='Ivan'");
        HibernateDevelopersEntity hibernateDevelopersEntity;

        if (query.iterate().hasNext()) {
            hibernateDevelopersEntity = (HibernateDevelopersEntity) query.iterate().next();
            //developerId = hibernateDevelopersEntity.getId();
            hibernateDevelopersEntity.setFirstName("Ivan Velikiy");
            session.update(hibernateDevelopersEntity);
        }
        tx.commit();
        session.close();
        System.out.println("after modifivation: \n");
        main.developersList();

        //4 find developer by id and then update
        session = getSession();
        tx = session.beginTransaction();
        int developerId = ((HibernateDevelopersEntity) session.createQuery("from HibernateDevelopersEntity").list().get(0)).getId();

        hibernateDevelopersEntity = session.get(HibernateDevelopersEntity.class, developerId);
        hibernateDevelopersEntity.setLastName("Kukushkin");
        session.update(hibernateDevelopersEntity);
        tx.commit();

        session.close();
        System.out.println("after modifivation 2: \n");
        main.developersList();

        //4 delete developer frim bd
        session = getSession();
        tx = session.beginTransaction();
        session.delete(hibernateDevelopersEntity);
        tx.commit();
        session.close();
        System.out.println("after delete: \n");
        main.developersList();

        //5 add projects <list/>
        session = getSession();
        tx = session.beginTransaction();

        HibernateProjectsEntity project1 = new HibernateProjectsEntity("game 1");
        HibernateProjectsEntity project2 = new HibernateProjectsEntity("paymants system");
        HibernateProjectsEntity project3 = new HibernateProjectsEntity("comunalka");
        HibernateProjectsEntity project4 = new HibernateProjectsEntity("elestrichestvo");
        List<HibernateProjectsEntity> vasaProjects = new ArrayList<>();
        vasaProjects.add(project1);
        vasaProjects.add(project2);
        vasaProjects.add(project3);
        vasaProjects.add(project4);

        session.save(project1);
        session.save(project2);
        session.save(project3);
        session.save(project4);

        query = session.createQuery("from HibernateDevelopersEntity");
        HibernateDevelopersEntity vasa = (HibernateDevelopersEntity) query.list().get(0);
        vasa.setProjects(vasaProjects);
        session.update(vasa);

        tx.commit();
        session.close();

        System.out.println("with projects: \n");
        main.developersList();

        //5 add projects <Map/>
        session = getSession();
        tx = session.beginTransaction();

        Map<String, HibernateKnowledgeEntity> knowledges1 = new HashMap<>();

        knowledges1.put("economic", new HibernateKnowledgeEntity("economic comment"));
        knowledges1.put("zoologic", new HibernateKnowledgeEntity("zoologic comment"));

        query = session.createQuery("from HibernateDevelopersEntity");
        HibernateDevelopersEntity dev = (HibernateDevelopersEntity) query.list().get(0);

        dev.setKnowledges(knowledges1);
        session.save(dev);

        tx.commit();
        session.close();

        System.out.println("with projects: \n");
        main.developersList();

        System.out.println();
    }

    /**
     * save new developers to bd
     *
     * @param firstName
     * @param lastName
     * @param specialty
     * @param experience
     */
    void addDeveloperToBd(String firstName, String lastName, String specialty, int experience) {
        HibernateSpecialitiesEntity speciality = addSpeciality(specialty);


        session = getSession();
        Transaction tx = session.beginTransaction();

        HibernateDevelopersEntity hibernateDevelopersEntity = new HibernateDevelopersEntity(firstName, lastName, speciality, experience);
        session.save(hibernateDevelopersEntity);

        tx.commit();
        session.close();
    }

    /**
     * save new speciality to bd
     *
     * @param specialty
     * @return
     */
    HibernateSpecialitiesEntity addSpeciality(String specialty) {
        session = getSession();
        Transaction tx = session.beginTransaction();
        HibernateSpecialitiesEntity specialitiesEntity;

        Query query = session.createQuery("from HibernateSpecialitiesEntity where speciality=:specialty");
        query.setParameter("specialty", specialty);
        if (query.iterate().hasNext()) {
            specialitiesEntity = (HibernateSpecialitiesEntity) query.iterate().next();
        } else {
            specialitiesEntity = new HibernateSpecialitiesEntity(specialty);
            session.save(specialitiesEntity);

        }
        tx.commit();
        session.close();
        return specialitiesEntity;
    }

    void developersList() {
        session = getSession();
        Transaction tx = session.beginTransaction();

        session.createQuery("from HibernateDevelopersEntity").list().forEach(o -> {
            System.out.println(o);
        });


        tx.commit();
        session.close();
    }

}
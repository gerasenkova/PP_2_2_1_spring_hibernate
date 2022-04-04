package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User getUserByModelAndSeries(Car car) {
        String hql="FROM User as u WHERE u.car.model=:model AND u.car.series=:series";
        TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery(hql)
                .setParameter("model", car.getModel()).setParameter("series", car.getSeries());
        User result=null;
        try {
            return result=query.getSingleResult();
        }catch (NoResultException e){
            System.out.println("User with this car is not found");
        }
        return result;
    }
}

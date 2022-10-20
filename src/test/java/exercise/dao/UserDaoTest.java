package exercise.dao;

import exercise.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoFactory.class)
class UserDaoTest {

    @Autowired
    ApplicationContext context;

    @Test
    void addAndGet() {
        UserDao userDao = context.getBean("awsUserDao", UserDao.class);
        userDao.deleteAll();
        assertEquals(0, userDao.getCount());

        String id = "1";
        userDao.add(new User(id, "python", "1234"));
        assertEquals(1, userDao.getCount());
        User user = userDao.findById(id);

        assertEquals("python", user.getName());
        assertEquals("1234", user.getPassword());
    }

    @Test
    void count() {

        User user1 = new User("1", "Han", "1234");
        User user2 = new User("2", "Lee", "1423");
        User user3 = new User("3", "Kim", "1324");

        UserDao userDao = context.getBean("awsUserDao", UserDao.class);
        userDao.deleteAll();
        assertEquals(0, userDao.getCount());

        userDao.add(user1);
        assertEquals(1, userDao.getCount());
        userDao.add(user2);
        assertEquals(2, userDao.getCount());
        userDao.add(user3);
        assertEquals(3, userDao.getCount());
    }
}

package com.isaacsufyan.webservices.daoservices;

import com.isaacsufyan.webservices.beans.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDaoService {
    private static final List<User> USER_LIST = new ArrayList<>();
    private static int count;

    static {
        USER_LIST.add(new User(1, "Sufyan", new Date()));
        USER_LIST.add(new User(2, "Faizan", new Date()));
        USER_LIST.add(new User(3, "Udemy", new Date()));
        count = USER_LIST.size();
    }

    public List<User> findAll() {
        return USER_LIST;
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++count);
        }
        if(user.getBirthDate()==null){
            user.setBirthDate(new Date());
        }
        USER_LIST.add(user);
        return user;
    }

    public User findOne(int id) {
        for (User user : USER_LIST) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public User deleteById(int id) {
        Iterator<User> iterator = USER_LIST.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getId() == id) {
                iterator.remove();
                return user;
            }
        }
        return null;
    }
}

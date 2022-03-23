package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sound.midi.Soundbank;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1=new User("User1", "Lastname1", "user1@mail.ru");
        User user2=new User("User2", "Lastname2", "user2@mail.ru");
        User user3=new User("User3", "Lastname3", "user3@mail.ru");

        Car Audi = new Car("Audi", 8);
        Car BMW = new Car("BMW", 3);
        Car Lada = new Car("Lada", 2201);
        Car Nissan = new Car("Nissan", 2201);

        user1.setCar(Audi);
        user2.setCar(BMW);
        user3.setCar(Lada);

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println(user.getFirstName()+" владеет "+user.getCar().getModel());
            System.out.println();
        }

        System.out.println("Audi принадлежит "+userService.getUserByModelAndSeries(Audi).getFirstName());
        System.out.println("Nissan принадлежит "+userService.getUserByModelAndSeries(Nissan));
        context.close();
    }
}

//package scheduler;
//
//import com.example.jokes.domain.User;
//import com.example.jokes.service.JokeRatingAverageCalculatorService;
//import com.example.jokes.service.UserDbService;
//
//import com.example.jokes.service.mail.EmailService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//public class WeeklyRatingEmailScheduler {
//
//    private final UserDbService userDbService;
//    private final JokeRatingAverageCalculatorService ratingCalculatorService;
//    private final EmailService emailService;
//
//    @Autowired
//    public WeeklyRatingEmailScheduler(UserDbService userService,
//                                      JokeRatingAverageCalculatorService ratingCalculatorService,
//                                      EmailService emailService) {
//        this.userDbService = userService;
//        this.ratingCalculatorService = ratingCalculatorService;
//        this.emailService = emailService;
//    }
//
//    @Scheduled(cron = "0 0 0 * * MON") // Scheduled for every Monday at midnight
//    public void sendWeeklyRatingEmails() {
//        List<User> allUsers = userDbService.getAllUsers();
//
//        for (User user : allUsers) {
//            double averageRating = ratingCalculatorService.calculateAverageRatingForUserJokes(user);
//            String email = user.getEmail();
//            String subject = "Weekly Joke Ratings Summary";
//            String message = "Your average joke rating for the past week is: " + averageRating;
//
//            // Send the email
//            //emailService.sendMail(email, subject, message);
//        }
//    }
//}

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class User1 {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters and setters for username and password

    public void updateProfile(String newUsername, String newPassword) {
        this.username = newUsername;
        this.password = newPassword;
        System.out.println("Profile updated successfully.");
    }
}

class Question {
    private String questionText;
    private List<String> options;
    private int correctOption;

    public Question(String questionText, List<String> options, int correctOption) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
    }

    // Getters for question text, options, and correct option

    public boolean isCorrect(int selectedOption) {
        return selectedOption == correctOption;
    }
}

class Exam {
    private List<Question> questions;
    private int durationInMinutes;

    public Exam(List<Question> questions, int durationInMinutes) {
        this.questions = questions;
        this.durationInMinutes = durationInMinutes;
    }

    public void startExam(User user) {
        System.out.println("Welcome, " + user.getUsername() + "! The exam has started.");

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            int remainingTime = durationInMinutes * 60;

            @Override
            public void run() {
                if (remainingTime > 0) {
                    System.out.println("Time remaining: " + remainingTime + " seconds");
                    remainingTime--;
                } else {
                    System.out.println("Time is up! Auto-submitting your answers.");
                    submitAnswers();
                    timer.cancel();
                }
            }
        };

        timer.scheduleAtFixedRate(task, 1000, 1000);

        // Allow the user to select answers
        selectAnswers();

        // End of exam
        System.out.println("Thank you for taking the exam!");
        timer.cancel();
    }

    private void selectAnswers() {
        Scanner scanner = new Scanner(System.in);

        for (Question question : questions) {
            System.out.println(question.getQuestionText());
            for (int i = 0; i < question.getOptions().size(); i++) {
                System.out.println((i + 1) + ". " + question.getOptions().get(i));
            }

            System.out.print("Enter your choice (1-" + question.getOptions().size() + "): ");
            int selectedOption = scanner.nextInt();

            if (question.isCorrect(selectedOption)) {
                System.out.println("Correct!");
            } else {
                System.out.println("Incorrect. The correct answer is: " + question.getCorrectOption());
            }
        }
    }

    private void submitAnswers() {
        // Logic for submitting answers
        System.out.println("Answers submitted successfully.");
    }
}

public class OnlineExaminationSystem {
    public static void main(String[] args) {
        // Sample questions
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("What is the capital of France?", List.of("Berlin", "Paris", "London"), 2));
        questions.add(new Question("Who wrote 'Romeo and Juliet'?", List.of("William Shakespeare", "Charles Dickens", "Jane Austen"), 1));

        // Sample user
        User1 user = new User1("student123", "password");

        // Sample exam
        Exam exam = new Exam(questions, 5); // 5 minutes duration

        // Sample usage
        exam.startExam(user);
    }
}

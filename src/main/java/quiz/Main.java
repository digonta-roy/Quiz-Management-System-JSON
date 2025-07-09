package quiz;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your username: ");
        String inputUsername = scanner.nextLine();

        System.out.print("Enter your password: ");
        String inputPassword = scanner.nextLine();

        try {
            // Load users.json file
            JSONParser parser = new JSONParser();
            JSONArray users = (JSONArray) parser.parse(new FileReader("src/main/resources/users.json"));

            boolean isAuthenticated = false;

            for (Object obj : users) {
                JSONObject user = (JSONObject) obj;
                String username = (String) user.get("username");
                String password = (String) user.get("password");
                String role = (String) user.get("role");

                if (inputUsername.equals(username) && inputPassword.equals(password)) {
                    isAuthenticated = true;

                    // ==== ADMIN LOGIN ====
                    if (role.equals("admin")) {
                        System.out.println("Welcome admin! Please create new questions in the question bank.");

                        JSONArray quizArray;

                        // Load existing questions from quiz.json
                        try {
                            JSONParser quizParser = new JSONParser();
                            FileReader quizReader = new FileReader("src/main/resources/quiz.json");
                            Object objQuiz = quizParser.parse(quizReader);
                            quizArray = (JSONArray) objQuiz;
                        } catch (Exception e) {
                            quizArray = new JSONArray(); // start fresh if file not found
                        }

                        while (true) {
                            System.out.print(" Input your question: ");
                            String question = scanner.nextLine();

                            System.out.print(" Input option 1: ");
                            String op1 = scanner.nextLine();
                            System.out.print(" Input option 2: ");
                            String op2 = scanner.nextLine();
                            System.out.print(" Input option 3: ");
                            String op3 = scanner.nextLine();
                            System.out.print(" Input option 4: ");
                            String op4 = scanner.nextLine();

                            System.out.print(" What is the answer key (1-4)? ");
                            int answerKey = Integer.parseInt(scanner.nextLine());

                            JSONObject mcq = new JSONObject();
                            mcq.put("question", question);
                            mcq.put("option 1", op1);
                            mcq.put("option 2", op2);
                            mcq.put("option 3", op3);
                            mcq.put("option 4", op4);
                            mcq.put("answerkey", answerKey);

                            quizArray.add(mcq);
                            System.out.println(" Saved successfully!\n");

                            System.out.print(" Do you want to add more questions? (press 's' to start, 'q' to quit): ");
                            String next = scanner.nextLine();
                            if (!next.equalsIgnoreCase("s")) break;
                        }

                        // Save all questions to quiz.json
                        try (FileWriter file = new FileWriter("src/main/resources/quiz.json")) {
                            file.write(quizArray.toJSONString());
                            file.flush();
                            System.out.println("All questions saved to quiz.json!");
                        } catch (Exception e) {
                            System.out.println(" Error writing to quiz.json");
                            e.printStackTrace();
                        }
                    }

                    // ==== STUDENT LOGIN ====
                    else if (role.equals("student")) {
                        System.out.println(" Welcome " + username + "! Let's start your quiz.");
                        System.out.println("Each question carries 1 mark. No negative marking.");

                        while (true) {
                            try {
                                JSONParser quizParser = new JSONParser();
                                JSONArray loadedQuestions = (JSONArray) quizParser.parse(new FileReader("src/main/resources/quiz.json"));

                                // Convert to List and shuffle
                                List<JSONObject> questionList = new ArrayList<>();
                                for (Object questionObj : loadedQuestions) {
                                    questionList.add((JSONObject) questionObj);
                                }
                                Collections.shuffle(questionList); // Shuffle questions

                                int score = 0;
                                int total = Math.min(10, questionList.size());

                                for (int i = 0; i < total; i++) {
                                    JSONObject q = questionList.get(i);

                                    System.out.println("\nQuestion " + (i + 1) + ": " + q.get("question"));
                                    System.out.println("1. " + q.get("option 1"));
                                    System.out.println("2. " + q.get("option 2"));
                                    System.out.println("3. " + q.get("option 3"));
                                    System.out.println("4. " + q.get("option 4"));

                                    System.out.print("Your answer (1-4): ");
                                    int answer = Integer.parseInt(scanner.nextLine());

                                    long correct = (long) q.get("answerkey");
                                    if (answer == correct) {
                                        score++;
                                    }
                                }

                                // Show result
                                System.out.println("\nYou got " + score + " out of " + total);
                                if (score >= 8) {
                                    System.out.println(" Excellent!");
                                } else if (score >= 5) {
                                    System.out.println(" Good.");
                                } else if (score >= 3) {
                                    System.out.println(" Very poor.");
                                } else {
                                    System.out.println(" Sorry, you failed.");
                                }

                            } catch (Exception e) {
                                System.out.println(" Something went wrong with the quiz.");
                                e.printStackTrace();
                            }

                            // Ask if user wants to try again
                            System.out.println("Would you like to start again? Press 's' for start or 'q' for quit: ");
                            String restart = scanner.nextLine();
                            if (!restart.equalsIgnoreCase("s")) {
                                System.out.println(" Thank you for playing.");
                                break;
                            }
                        }
                    }

                    break; // Stop checking after successful login
                }
            }

            // If login fails
            if (!isAuthenticated) {
                System.out.println(" Invalid username or password.");
            }

        } catch (Exception e) {
            System.out.println("Error reading users.json file.");
            e.printStackTrace();
        }
    }
}

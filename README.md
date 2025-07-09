#  Quiz Management System

A simple console-based quiz application with role-based access:  
- **Admin** can add multiple-choice questions (MCQs) to a question bank.  
- **Students** can take a quiz from the question bank.

---

## Features

- User login with roles: Admin or Student.
- Admin can add new MCQs with 4 options and the correct answer.
- Student can take a quiz with 10 random questions.
- Score calculated with no negative marking.
- Performance feedback based on score.
- Data stored in JSON files (`users.json` and `quiz.json`).

---

## Tech Stack

- **Language:** Java  
- **Data Storage:** JSON files (for user credentials and quiz questions)  

---
## Scoring System

| Score Range | Performance     | Feedback        |
|-------------|-----------------|-----------------|
| 8 - 10      | Excellent       |  Well done!   |
| 5 - 7       | Good            |  Keep it up!  |
| 3 - 4       | Very Poor       |  Needs improvement |
| 0 - 2       | Failed          |  Try again!   |


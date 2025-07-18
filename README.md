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

### users.json

```json
[
  {
    "username": "admin",
    "password": "1234",
    "role": "admin"
  },
  {
    "username": "salman",
    "password": "1234",
    "role": "student"
  }
]
```

### quiz.json

```json
[
  {
    "question": "Which is not part of system testing?",
    "option 1": "Regression Testing",
    "option 2": "Sanity Testing",
    "option 3": "Load Testing",
    "option 4": "Unit Testing",
    "answerkey": 4
  }
]
```


---

## 📊 Quiz Flow & Scoring

- The student receives 10 random questions from the question bank.
- Each correct answer awards 1 point.
- No negative marking for incorrect answers.


## Tech Stack

- **Language:** Java  
- **Data Storage:** JSON files (for user credentials and quiz questions)  

---
## Scoring System

| Score Range | Performance     | Feedback        |
|-------------|-----------------|-----------------|
| 8 - 10      | Excellent       |  You have got [marks] out of 10   |
| 5 - 7       | Good            |  You have got [marks] out of 10  |
| 3 - 4       | Very Poor       |  You have got [marks] out of 10 |
| 0 - 2       | Very Sorry         |  You have got [marks] out of 10   |

## Student login and completing a quiz Video
https://github.com/user-attachments/assets/43f1cdb6-5c50-4bb2-a51e-263778c7a21b

## Admin  login and adding questions Video
https://github.com/user-attachments/assets/4cf2de70-7ad9-403d-904a-ef85b2fea5c4



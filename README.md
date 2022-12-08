# QuizApp

## Project Description
This project completed for CS 3321 Intro. to Softw. Eng. is a desktop app called QuizApp. Users can add courses, add questions for those courses, and study those questions in a virtual flashcard format. When adding courses, users can view the courses as they are added in the "Current Courses:" text field. Users can also add questions to specific courses after pressing the "Add Questions" button. Then, once "Start Studying" is pressed and a course is selected, users can navigate the cards by pressing "Next Question" and "Previous Question" buttons. They can reveal the solution of their card by pressing the "Show Answer" button, or revert back to the question by pressing "Show Question". When card contents are mastered, users can remove them from their course by pressing the "Remove Card" button. When users are finished studying, they can press the "Back to Main Menu" button and  their course information and associated cards are saved for the next time they open the program. 

## Project Requirements Met
Project uses shared codebase/repository GitHub for version control and collaborative contributions

Project uses Gradle for configuration management and automatic build

Project includes test code at unit test level (see QuizApp>app>build>reports>tests>test>packages>index.html for test results

Project uses continuous integration tool GitHub Actions and Java CI with Gradle

Project includes documentation including a use0case diagram, interaction models, and design models as shown below.

## Use Case Diagram
![image](https://user-images.githubusercontent.com/90599582/206378437-ad1efd7f-05fd-4eb9-803b-60bd57aee7aa.png)

## Software Class Diagram
![image](https://user-images.githubusercontent.com/90599582/206378708-dca4ef5d-ce55-416d-86ca-ec38c247a38a.png)

## Interaction Diagrams (SSD)
### Adding a Course
![image](https://user-images.githubusercontent.com/90599582/206379338-66960c0a-4606-4133-b410-b92b10d2359d.png)

### Adding a Card
![image](https://user-images.githubusercontent.com/90599582/206379391-c2b3c949-4569-42ac-a75e-1f9fdb9503a7.png)

### Studying Cards
![image](https://user-images.githubusercontent.com/90599582/206379435-e03bea4b-3961-4f5f-acfa-d2992601a04a.png)

### Removing Cards
![image](https://user-images.githubusercontent.com/90599582/206379473-a81dfc8b-f486-4357-afe1-5fa0861513ab.png)

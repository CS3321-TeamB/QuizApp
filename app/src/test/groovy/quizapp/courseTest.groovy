package quizapp

import spock.lang.Specification

class courseTest extends Specification {
    public static final String courseName = "Math"
    public static final flashDeck deck = new flashDeck(courseName)

    def "create course"(){
        given:
        def course1 = new course(courseName)

        expect:
        courseName == course1.courseName
    }

    def "test getDeck"(){
        given:
        def course = new course(courseName)
        course.questions = deck

        expect:
        deck == course.getDeck("Math")
    }

    def "test get Course name"(){
        given:
        def course = new course(courseName)

        expect:
        "Math" == course.getCourseName()
    }
    def "test addCard"(){
        given:
        def course = new course(courseName)
        course.questions = deck
        course.addCard("front","back")

        expect:
        course.questions.cardStack.isEmpty() == false
    }
    def "test removeCard"(){
        given:
        def course = new course(courseName)
        course.questions = deck
        course.addCard("front", "back")
        course.removeCard(courseName,course.questions.cardStack.get(0))

        expect:
        course.questions.cardStack.isEmpty() == true
    }
}

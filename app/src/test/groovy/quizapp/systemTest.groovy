package quizapp

import spock.lang.Specification

class systemTest extends Specification{
    public static final system = new system()

    def "create card"(){
        given:
        def card = new card("front", "back")

        expect:
        card.front == system.createCard("front", "back").front
    }

    def "create a course"(){
        given:
        def course = new course("Health")

        expect:
        course.courseName == system.createCourse("Health").courseName
    }

    def "get course list"(){
    }

    def "add to deck"(){

    }

    def "Delete a card"(){

    }

    def "draw a card"(){

    }

    def "get index"(){

    }

    def "get course"(){

    }

    def "test is empty"(){
        given:
        def system = new system()
        system.createCourse("Health")

        expect:
        system.isEmpty("Health") == true
    }

    def "getCourse"(){
        given:
        def system = new system()
        system.createCourse("Math")
        system.getCourse("Math").addCard("Front1", "Back1")
        system.getCourse("Math").addCard("Front2", "Back2")

        expect:
        system.getCourse("Math").getDeckSize() == 2
    }

}

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
        def card1 = new card("Front1", "Back1")
        def card2 = new card("Front2", "Back2")
        system.createCourse("Math")
        system.createCourse("Science")
        system.getCourse("Math").addCard(card1)
        system.getCourse("Math").addCard(card2)
//
//        expect:
//        system.getCourse("Math").getDeckSize() == 2




    }

}

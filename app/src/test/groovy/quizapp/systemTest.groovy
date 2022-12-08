package quizapp
import spock.lang.Specification

class systemTest extends Specification{

    def "create card"(){

    }

    def "create a crouse"(){

    }


    def "get a course"(){

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

package quizapp;

import spock.lang.Specification

class cardTest extends Specification {
    public static final String front = "this is the front"
    public static final String back = "this is the back"

    def "create a card"(){
        given:
        def card = new card(front, back)
        expect:
        card.getFront() == "this is the front"
        card.getBack() == "this is the back"
        card.getHard() == false
        card.getPass() == false
        card.getPriority() == 0
        card.getStarred() == false
    }

    def "test setters"(){
        given:
        def card = new card(front, back)

        when:
        card.setFront("New front")
        card.setBack("New back")
        card.setPriority(5)
        card.setStarred(true)

        then:
        card.getFront() == "New front"
        card.getBack() == "New back"
        card.getPriority() == 5
        card.getStarred() == true
    }
}

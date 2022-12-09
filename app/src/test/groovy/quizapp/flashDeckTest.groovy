package quizapp

import spock.lang.Specification


class flashDeckTest extends Specification {
    public static final String course = "Math"

    def card1 = new card("card 1 front", "card 1 back")
    def card2 = new card("card 2 front", "card 2 back")
    def card3 = new card("card 3 front", "card 3 back")


    def "create flashDeck"(){
        given:
        def flashDeck = new flashDeck(course)

        expect:
        flashDeck.getSubject() == "Math"
        flashDeck.totalCards == 0
    }

    def "add to flashDeck"(){
        given:
        def flashDeck = new flashDeck(course)
        when:
        flashDeck.addCardToDeck("front1", "back1")
        flashDeck.addCardToDeck("front2", "back2")
        flashDeck.addCardToDeck("front3", "back3")

        then:
        flashDeck.totalCards == 3
        flashDeck.drawCard(0).getFront() == "front1"
    }
    def "remove from flashDeck"(){
        given:
        def flashDeck = new flashDeck(course)
        flashDeck.addCardToDeck("front1", "back1")
        flashDeck.addCardToDeck("front2", "back2")
        flashDeck.addCardToDeck("front3", "back3")

        when:
        flashDeck.removeCard(flashDeck.cardStack.get(0))

        then:
        flashDeck.totalCards == 2
    }

    def "draw from flashDeck"() {
        given:
        def flashDeck = new flashDeck(course)

        when:
        for (int i = 0; i < 99; i++) {
            flashDeck.addCardToDeck("front1", "back1")
        }
        flashDeck.addCardToDeck("front2", "back2")

        then:
        flashDeck.totalCards == 100
        flashDeck.drawCard(99).getFront() == "front2"
    }
}

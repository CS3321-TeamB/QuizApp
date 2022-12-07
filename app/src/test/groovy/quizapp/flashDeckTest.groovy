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
        flashDeck.addCard(card1)
        flashDeck.addCard(card2)
        flashDeck.addCard(card3)

        then:
        flashDeck.totalCards == 3
        flashDeck.drawCard(0) == card1
    }
    def "remove from flashDeck"(){
        given:
        def flashDeck = new flashDeck(course)
        flashDeck.addCard(card1)
        flashDeck.addCard(card2)
        flashDeck.addCard(card3)

        when:
        flashDeck.removeCard(card1)

        then:
        flashDeck.totalCards == 2
        flashDeck.drawCard(0) == card2
    }

    def "draw from flashDeck"() {
        given:
        def flashDeck = new flashDeck(course)
        when:
        for (int i = 0; i < 99; i++) {
            flashDeck.addCard(card1)
        }
        flashDeck.addCard(card2)

        then:
        flashDeck.totalCards == 100
        flashDeck.drawCard(99) == card2
    }
    def "shuffle test"(){
        given:
        def flashDeck = new flashDeck(course)
        def flashDeck2 = new flashDeck(course)
        for(int i =0; i < 99; i++){
            if (i%3 == 0) {
                flashDeck.addCard(card1);
            }
            else if(i%3 == 1){
                flashDeck.addCard(card2);
            }
            else {
                flashDeck.addCard(card3);
            }
        }
        ArrayList list = [1,2,3,4,5]
        ArrayList list2 = Collections.shuffle(list)

        for(int i =0; i < flashDeck.totalCards; i++){
            flashDeck2.addCard(flashDeck.drawCard(i))
        }
        when:

        flashDeck.shuffle()

        then:

        flashDeck.cardStack.equals(flashDeck2.cardStack) != true

    }
}

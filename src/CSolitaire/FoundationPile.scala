package CSolitaire

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
  * Created by DefaultPC on 4/9/2017.
  */
class FoundationPile(var startCard:Card) {
  var cardList:ListBuffer[Card] = new ListBuffer[Card]()
  cardList += startCard
  def addCard(card:Card){
    if(card.value == cardList.last.value + 2 || card.value == cardList.last.value - 11)
      cardList.insert(0, card)
  }

  def removeTopCard(): Unit ={
    cardList -= cardList.head
  }
}

package CSolitaire

import scala.collection.mutable.ListBuffer

/**
  * Created by DefaultPC on 4/9/2017.
  */
class FoundationPile(var startCard:Card) {
  var cardList:ListBuffer[Card] = new ListBuffer[Card]()
  val increment: Int = startCard.value
  cardList += startCard
  def addCard(card:Card){
    if(checkCardValidity(card))
      cardList.insert(0, card)
  }

  def removeTopCard(): Unit ={
    cardList -= cardList.head
  }

  def checkCardValidity(card:Card): Boolean = {
    if(cardList.head.value + increment > 13)
      return card.value == cardList.head.value + increment - 13
    else
      card.value == cardList.head.value + increment
  }
}

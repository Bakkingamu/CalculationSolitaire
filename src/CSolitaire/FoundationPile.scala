package CSolitaire

import scala.collection.mutable.ListBuffer

/**
  * Created by DefaultPC on 4/9/2017.
  */
class FoundationPile(var startCard : Card) {
  var cardList:ListBuffer[Card] = new ListBuffer[Card]()
  val increment: Int = startCard.value
  var isLocked : Boolean = false
  cardList += startCard
  def addCard(card:Card){
    if(checkCardValidity(card))
      cardList.insert(0, card)
  }

  def removeTopCard(): Unit = {
    cardList -= cardList.head
  }

  def checkCardValidity(card:Card): Boolean = {
    if(cardList.head.value == 0)
      false
    else if(cardList.head.value + increment > 13)
      card.value == cardList.head.value + increment - 13
    else
      card.value == cardList.head.value + increment
  }

  def finishFoundation(): Unit = {
    do{
      if(cardList.head.value + increment != 13) {
        if (cardList.head.value + increment > 13)
          cardList.insert(0, new Card(SUIT.HEART, cardList.head.value + increment - 13))
        else
          cardList.insert(0, new Card(SUIT.HEART, cardList.head.value + increment))
      }
    }while(cardList.head.value + increment != 13)
  }

  def lockFoundation(): Unit = {
    cardList.insert(0, new Card(SUIT.HEART, 0))
    isLocked = true
  }
}

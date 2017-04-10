package CSolitaire

import scala.collection.mutable.ListBuffer

/**
  * Created by DefaultPC on 4/9/2017.
  */
class WastePile {
  var cardList:ListBuffer[Card] = new ListBuffer[Card]()
  def addCard(card:Card){
      cardList.insert(0, card)
  }

  def removeTopCard(): Unit ={
    cardList -= cardList.head
  }
  def isEmpty : Boolean = {
    cardList.isEmpty
  }
}

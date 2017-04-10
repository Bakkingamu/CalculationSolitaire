package CSolitaire

/**
  * Created by DefaultPC on 4/9/2017.
  */
class DiscardPile(var card:Card) {
  def setCard(newCard:Card): Unit ={
    card = newCard
  }

  def removeCard(): Unit ={
    card = null
  }
  
  def isEmpty : Boolean = {
  card == null
  }
}

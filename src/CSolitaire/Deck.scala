package CSolitaire
import scala.collection.mutable.ListBuffer
import scala.util.Random.shuffle
/**
  * Created by DefaultPC on 4/9/2017.
  */
class Deck {
  var cardList : ListBuffer[Card] = new ListBuffer[Card]()
  def fillDeck(): Unit ={
    for(i <- 1 to 13){
      if(i != 1)
        cardList += new Card(SUIT.HEART, i)
    }
    for(i <- 1 to 13) {
      if (i != 2)
        cardList += new Card(SUIT.DIAMOND, i)
    }
    for(i <- 1 to 13){
      if(i != 3)
        cardList += new Card(SUIT.CLUB, i)
    }
    for(i <- 1 to 13){
      if(i != 4)
        cardList += new Card(SUIT.SPADE, i)
    }

  }

  def removeTopCard(): Unit ={
    cardList -= cardList.head
  }

  def shuffle(): Unit ={
    cardList = util.Random.shuffle(cardList)
  }
}

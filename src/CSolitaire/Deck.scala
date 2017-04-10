package CSolitaire
import scala.collection.mutable.ListBuffer
/**
  * Created by DefaultPC on 4/9/2017.
  */
class Deck {
  var cardList : ListBuffer[Card] = new ListBuffer[Card]()
  def fillDeck(): Unit ={
    for(i <- 1 to 52){
      if(i < 14){
        cardList += new Card(SUIT.HEART, i % 13)
      }
      else if(i < 27){
        cardList += new Card(SUIT.DIAMOND, i % 13)
      }
      else if(i < 40){
        cardList += new Card(SUIT.CLUB, i % 13)
      }
      else{
        cardList += new Card(SUIT.SPADE, i % 13)
      }
    }
  }

  def removeTopCard(): Unit ={
    cardList -= cardList.head
  }

  def shuffle(): Unit ={
    util.Random.shuffle(cardList)
  }
}

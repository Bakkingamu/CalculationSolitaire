package CSolitaire
import javafx.scene.image.Image
/**
  * Created by Justin on 3/21/2017.
  */
import SUIT._
  class Card(var suit: SUIT, var value: Int){
    var img : Image = getImage(suit, value)

    def getImage(st: SUIT, valu: Int): Image ={
      var filename : String = ""
      val value = valu match {
        case 0 => "back"
        case 1 => "ace"
        case x if 2 to 10 contains x => valu.toString
        case 11 => "jack"
        case 12 => "queen"
        case 13 => "king"
        case whoa  => println("Unexpected case: " + whoa.toString)
      }
      val suit = st match{
        case SUIT.CLUB => "club"
        case SUIT.HEART => "heart"
        case SUIT.DIAMOND => "diamond"
        case SUIT.SPADE => "spade"
      }
      if(value == "back")
        filename = "backr.png"
      else
        filename = value + "_of_" + suit + "s.png"
      new Image("CSolitaire/resources/" + filename)
    }
  }



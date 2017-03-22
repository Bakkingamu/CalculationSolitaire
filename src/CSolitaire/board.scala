package CSolitaire

/**
  * Created by Justin on 3/19/2017.
  */
import CSolitaire._
import javafx.application.Application
import javafx.geometry.Insets
import javafx.scene.Scene
import javafx.scene.image.{Image, ImageView}
import javafx.scene.layout._
import javafx.scene.paint.Color
import javafx.stage._
import javax.smartcardio.Card
class board extends Application {
  override def start(primaryStage: Stage ){
    //slot for each card on the board
    val deck = new ImageView
    val discard = new ImageView
    val found1 = new ImageView
    val found2 = new ImageView
    val found3 = new ImageView
    val found4 = new ImageView
    val waste1 = new ImageView
    val waste2 = new ImageView
    val waste3 = new ImageView
    val waste4 = new ImageView
    //test card
    val c : Card = new Card(SUIT.CLUB, 7)
    //set deck pile to test
    deck.setImage(getImage(c))
    //gui
    primaryStage.setTitle("Calculation Solitaire")
    val root = new GridPane
    root.setPadding(new Insets(10))
    root.setVgap(10)
    root.setHgap(10)
    root.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)))
    //add all piles to grid
    root.add(deck, 1,0)
    root.add(discard, 2, 0)

    root.add(found1, 3, 0)
    root.add(found2, 4, 0)
    root.add(found3, 5, 0)
    root.add(found4, 6, 0)
    root.add(waste1, 3, 1)
    root.add(waste2, 4, 1)
    root.add(waste3, 5, 1)
    root.add(waste4, 6, 1)
    //gui
    val scene= new Scene(root)
    primaryStage.setScene(scene)
    primaryStage.show()
  }
  //loads image from card
  def getImage(card: Card): Image ={
    val value = card.value match {
      case 1 => "ace"
      case x if 2 to 11 contains x => card.value.toString
      case 11 => "jack"
      case 12 => "queen"
      case 13 => "king"
      case whoa  => println("Unexpected case: " + whoa.toString)
    }
    val suit = card.suit match{
      case SUIT.CLUB => "club"
      case SUIT.HEART => "heart"
      case SUIT.DIAMOND => "diamond"
      case SUIT.SPADE => "spade"
    }
    val filename : String = value + "_of_" + suit + "s.png"
    new Image("CSolitaire/resources/" + filename)
  }

}
//main
object run{
  def main(args: Array[String]): Unit ={
    Application.launch(classOf[board], args: _*)
  }
}


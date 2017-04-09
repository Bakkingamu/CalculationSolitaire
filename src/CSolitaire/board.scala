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
    val c : Card = new Card(SUIT.CLUB, 1)
    val c1 : Card = new Card(SUIT.DIAMOND, 2)
    val c2 : Card = new Card(SUIT.HEART, 3)
    val c3 : Card = new Card(SUIT.SPADE, 4)
    //set deck pile to test
    //gui
    primaryStage.setTitle("Calculation Solitaire")
    val img1 : Image = new Image("CSolitaire/resources/backr.png")
    deck.setImage(img1)
    val img2 : Image = new Image("CSolitaire/resources/border.png")
    discard.setImage(img2)
    found1.setImage(c.img)
    found2.setImage(c1.img)
    found3.setImage(c2.img)
    found4.setImage(c3.img)
    waste1.setImage(img2)
    waste2.setImage(img2)
    waste3.setImage(img2)
    waste4.setImage(img2)
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

}
//main
object run{
  def main(args: Array[String]): Unit ={
    Application.launch(classOf[board], args: _*)
  }
}


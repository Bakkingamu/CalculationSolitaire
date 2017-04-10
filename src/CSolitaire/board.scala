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
import javafx.scene.text.Text
import javafx.event.EventHandler
import javafx.scene.input.{ClipboardContent, Dragboard, MouseEvent, TransferMode}

class board extends Application {
  override def start(primaryStage: Stage ) {
    //slot for each card on the board
    val deckpile = new Deck
    deckpile.fillDeck()
    deckpile.shuffle()
    val discardpile = new DiscardPile(deckpile.cardList.head)
    deckpile.removeTopCard()
    primaryStage.setTitle("Calculation Solitaire")
    val back : Image = new Image("CSolitaire/resources/backr.png")
    val border : Image = new Image("CSolitaire/resources/border.png")
    var heldCard : Card = null
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
    val held = new ImageView
    discard.setImage(discardpile.card.img)
    deck.setImage(back)
    found1.setImage(border)
    found2.setImage(border)
    found3.setImage(border)
    found4.setImage(border)
    waste1.setImage(border)
    waste2.setImage(border)
    waste3.setImage(border)
    waste4.setImage(border)
    var clickDiscard : EventHandler[MouseEvent] = new EventHandler[MouseEvent]() {
      def handle(event: MouseEvent): Unit = {
        if (heldCard == null)
          {
            if(!discardpile.isEmpty){
              heldCard = discardpile.card
              held.setImage(heldCard.img)
              discardpile.removeCard()
              discard.setImage(border)
            }
          }
      }
    }
    var clickDeck : EventHandler[MouseEvent] = new EventHandler[MouseEvent]() {
      def handle(event: MouseEvent): Unit = {
        if(discardpile.isEmpty){
          discardpile.setCard(deckpile.cardList.head)
          deckpile.removeTopCard()
          discard.setImage(discardpile.card.img)
        }
      }
    }
    var clickPile : EventHandler[MouseEvent] = new EventHandler[MouseEvent]() {
      def handle(event: MouseEvent): Unit = {
        if (heldCard != null)
          {
            event.getSource.asInstanceOf[ImageView].setImage(heldCard.img)
            heldCard = null
            held.setImage(null)
        }
      }
    }
    deck.setOnMouseClicked(clickDeck)
    discard.setOnMouseClicked(clickDiscard)
    found1.setOnMouseClicked(clickPile)
    found2.setOnMouseClicked(clickPile)
    found3.setOnMouseClicked(clickPile)
    found4.setOnMouseClicked(clickPile)
    waste1.setOnMouseClicked(clickPile)
    waste2.setOnMouseClicked(clickPile)
    waste3.setOnMouseClicked(clickPile)
    waste4.setOnMouseClicked(clickPile)
    val root = new GridPane

    root.setPadding(new Insets(10))
    root.setVgap(10)
    root.setHgap(10)
    root.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)))
    //add all piles to grid
    root.add(deck, 1, 0)
    root.add(discard, 2, 0)
    root.add(found1, 3, 0)
    root.add(found2, 4, 0)
    root.add(found3, 5, 0)
    root.add(found4, 6, 0)
    root.add(waste1, 3, 1)
    root.add(waste2, 4, 1)
    root.add(waste3, 5, 1)
    root.add(waste4, 6, 1)
    root.add(held, 2, 1)
    //gui
    val scene = new Scene(root)
    primaryStage.setScene(scene)
    primaryStage.show()
  }
}
//main
object run{
  def main(args: Array[String]): Unit ={
    Application.launch(classOf[board], args: _*)
  }
}


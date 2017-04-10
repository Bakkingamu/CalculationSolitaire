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
    val found1Pile = new FoundationPile(new Card(SUIT.HEART, 1))
    val found2Pile = new FoundationPile(new Card(SUIT.DIAMOND, 2))
    val found3Pile = new FoundationPile(new Card(SUIT.CLUB, 3))
    val found4Pile = new FoundationPile(new Card(SUIT.SPADE, 4))
    val waste1Pile = new WastePile
    val waste2Pile = new WastePile
    val waste3Pile = new WastePile
    val waste4Pile = new WastePile
    primaryStage.setTitle("Calculation Solitaire")
    val back : Image = new Image("CSolitaire/resources/backr.png")
    val border : Image = new Image("CSolitaire/resources/border.png")
    var heldCard : Card = null
    val deck = new ImageView
    val discard = new ImageView
    val found1View = new ImageView
    val found2View = new ImageView
    val found3View = new ImageView
    val found4View = new ImageView
    val waste1View = new ImageView
    val waste2View = new ImageView
    val waste3View = new ImageView
    val waste4View = new ImageView
    val held = new ImageView
    discard.setImage(discardpile.card.img)
    deck.setImage(back)
    found1View.setImage(found1Pile.cardList.head.img)
    found2View.setImage(found2Pile.cardList.head.img)
    found3View.setImage(found3Pile.cardList.head.img)
    found4View.setImage(found4Pile.cardList.head.img)
    waste1View.setImage(border)
    waste2View.setImage(border)
    waste3View.setImage(border)
    waste4View.setImage(border)
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
        if(discardpile.isEmpty && heldCard == null){
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
    var clickFoundationPileOne : EventHandler[MouseEvent] = new EventHandler[MouseEvent]() {
      def handle(event: MouseEvent): Unit = {
        if (heldCard != null)
        {
          if(found1Pile.checkCardValidity(heldCard)){
            found1Pile.addCard(heldCard)
            found1View.setImage(found1Pile.cardList.head.img)
            heldCard = null
            held.setImage(null)
          }
        }
      }
    }
    var clickFoundationPileTwo : EventHandler[MouseEvent] = new EventHandler[MouseEvent]() {
      def handle(event: MouseEvent): Unit = {
        if (heldCard != null)
        {
          if(found2Pile.checkCardValidity(heldCard)){
            found2Pile.addCard(heldCard)
            found2View.setImage(found2Pile.cardList.head.img)
            heldCard = null
            held.setImage(null)
          }
        }
      }
    }
    var clickFoundationPilethree : EventHandler[MouseEvent] = new EventHandler[MouseEvent]() {
      def handle(event: MouseEvent): Unit = {
        if (heldCard != null)
        {
          if(found3Pile.checkCardValidity(heldCard)){
            found3Pile.addCard(heldCard)
            found3View.setImage(found3Pile.cardList.head.img)
            heldCard = null
            held.setImage(null)
          }
        }
      }
    }
    var clickFoundationPileFour : EventHandler[MouseEvent] = new EventHandler[MouseEvent]() {
      def handle(event: MouseEvent): Unit = {
        if (heldCard != null)
        {
          if(found4Pile.checkCardValidity(heldCard)){
            found4Pile.addCard(heldCard)
            found4View.setImage(found4Pile.cardList.head.img)
            heldCard = null
            held.setImage(null)
          }
        }
      }
    }
    var clickWastePileOne : EventHandler[MouseEvent] = new EventHandler[MouseEvent]() {
      def handle(event: MouseEvent): Unit = {
        if (heldCard == null)
        {
          if(!waste1Pile.isEmpty){
            heldCard = waste1Pile.cardList.head
            held.setImage(heldCard.img)
            waste1Pile.removeTopCard()
            if(waste1Pile.isEmpty)
              waste1View.setImage(border)
            else
              waste1View.setImage(waste1Pile.cardList.head.img)
          }
        }else{
          waste1Pile.addCard(heldCard)
          waste1View.setImage(waste1Pile.cardList.head.img)
          heldCard = null
          held.setImage(null)
        }
      }
    }
    var clickWastePileTwo : EventHandler[MouseEvent] = new EventHandler[MouseEvent]() {
      def handle(event: MouseEvent): Unit = {
        if (heldCard == null)
        {
          if(!waste2Pile.isEmpty){
            heldCard = waste2Pile.cardList.head
            held.setImage(heldCard.img)
            waste2Pile.removeTopCard()
            if(waste2Pile.isEmpty)
              waste2View.setImage(border)
            else
              waste2View.setImage(waste2Pile.cardList.head.img)
          }
        }else{
          waste2Pile.addCard(heldCard)
          waste2View.setImage(waste2Pile.cardList.head.img)
          heldCard = null
          held.setImage(null)
        }
      }
    }
    var clickWastePileThree : EventHandler[MouseEvent] = new EventHandler[MouseEvent]() {
      def handle(event: MouseEvent): Unit = {
        if (heldCard == null)
        {
          if(!waste3Pile.isEmpty){
            heldCard = waste3Pile.cardList.head
            held.setImage(heldCard.img)
            waste3Pile.removeTopCard()
            if(waste3Pile.isEmpty)
              waste3View.setImage(border)
            else
              waste3View.setImage(waste3Pile.cardList.head.img)
          }
        }else{
          waste3Pile.addCard(heldCard)
          waste3View.setImage(waste3Pile.cardList.head.img)
          heldCard = null
          held.setImage(null)
        }
      }
    }
    var clickWastePileFour : EventHandler[MouseEvent] = new EventHandler[MouseEvent]() {
      def handle(event: MouseEvent): Unit = {
        if (heldCard == null)
        {
          if(!waste4Pile.isEmpty){
            heldCard = waste4Pile.cardList.head
            held.setImage(heldCard.img)
            waste4Pile.removeTopCard()
            if(waste4Pile.isEmpty)
              waste4View.setImage(border)
            else
              waste4View.setImage(waste4Pile.cardList.head.img)
          }
        }else{
          waste4Pile.addCard(heldCard)
          waste4View.setImage(waste4Pile.cardList.head.img)
          heldCard = null
          held.setImage(null)
        }
      }
    }
    deck.setOnMouseClicked(clickDeck)
    discard.setOnMouseClicked(clickDiscard)
    found1View.setOnMouseClicked(clickFoundationPileOne)
    found2View.setOnMouseClicked(clickFoundationPileTwo)
    found3View.setOnMouseClicked(clickFoundationPilethree)
    found4View.setOnMouseClicked(clickFoundationPileFour)
    waste1View.setOnMouseClicked(clickWastePileOne)
    waste2View.setOnMouseClicked(clickWastePileTwo)
    waste3View.setOnMouseClicked(clickWastePileThree)
    waste4View.setOnMouseClicked(clickWastePileFour)
    val root = new GridPane

    root.setPadding(new Insets(10))
    root.setVgap(10)
    root.setHgap(10)
    root.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)))
    //add all piles to grid
    root.add(deck, 1, 0)
    root.add(discard, 2, 0)
    root.add(found1View, 3, 0)
    root.add(found2View, 4, 0)
    root.add(found3View, 5, 0)
    root.add(found4View, 6, 0)
    root.add(waste1View, 3, 1)
    root.add(waste2View, 4, 1)
    root.add(waste3View, 5, 1)
    root.add(waste4View, 6, 1)
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


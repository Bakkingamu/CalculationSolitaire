package CSolitaire
/**
  * Created by Justin on 3/19/2017.
  */
import javafx.application.Application
import javafx.geometry.Insets
import javafx.scene.{Cursor, ImageCursor, Scene}
import javafx.scene.image.{Image, ImageView}
import javafx.scene.layout._
import javafx.scene.paint.Color
import javafx.stage._
import javafx.event.EventHandler
import javafx.scene.control.MenuBar
import javafx.scene.control.Menu
import javafx.scene.control.MenuItem
import javafx.event.ActionEvent
import javafx.scene.input.MouseEvent
import javafx.scene.text.Text


class board extends Application {
  override def start(primaryStage: Stage ) {
    //virtual hand to pick up card
    var hand : Card = null
    var holding : Boolean = false
    var source : String = ""
    //undo variables
    var undoPossible: Boolean = false
    var undomove : String = ""
    //create the deck
    val deckpile = new Deck
    deckpile.fillDeck()
    deckpile.shuffle()
    //create the discard
    val discardpile = new DiscardPile(deckpile.cardList.head)
    deckpile.removeTopCard()
    //create foundations
    val found1Pile = new FoundationPile(new Card(SUIT.HEART, 1))
    val found2Pile = new FoundationPile(new Card(SUIT.DIAMOND, 2))
    val found3Pile = new FoundationPile(new Card(SUIT.CLUB, 3))
    val found4Pile = new FoundationPile(new Card(SUIT.SPADE, 4))
    //val sequence1 = new Sequence(1)
    //val sequence2 = new Sequence(2)
    //val sequence3 = new Sequence(3)
    //val sequence4 = new Sequence(4)
    //create wastes
    val waste1Pile = new WastePile
    val waste2Pile = new WastePile
    val waste3Pile = new WastePile
    val waste4Pile = new WastePile
    //GUI
    primaryStage.setTitle("Calculation Solitaire")
    val back : Image = new Image("CSolitaire/resources/backr.png")
    val border : Image = new Image("CSolitaire/resources/border.png")
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
    val bar: MenuBar = new MenuBar()
    val gameMenu : Menu = new Menu("Game")
    val undoMenuItem : MenuItem = new MenuItem("Undo")
    val winGameItem : MenuItem = new MenuItem("Win Game")
    //--------------------HELPER METHODS--------------------
    //interact decides what to do when a pile is clicked. The parameter is a string describing the pile clicked
    def interact(str: String): Unit = {
      if(!holding) //if hand is empty
      {
        if(isFound(str))
        {
          return// cant pick up foundation cards
        }
        else
        {
          if(isWaste(str)){ // if its the waste
            if(!getWastePile(str).isEmpty){ //if its not empty pick up
              hand = getWastePile(str).cardList.head
              primaryStage.getScene.setCursor(new ImageCursor(hand.img,hand.img.getWidth() / 2, hand.img.getHeight() / 2))
              getWastePile(str).removeTopCard()
              holding = true
              source = str
            }
          }
          else
          { //must be discard
            if(!discardpile.isEmpty){ //if its not empty pick up
              hand = discardpile.card
              primaryStage.getScene.setCursor(new ImageCursor(hand.img,hand.img.getWidth() / 2, hand.img.getHeight() / 2))
              discardpile.removeCard()
              holding = true
              source = str
            }
          }
        }
      }
      else
      { //since you're holding a card try to place it down at the clicked pile
        tryPlace(str)
      }
      update()
    }
    //tryPlace is only called when there is a card in hand and another pile is clicked
    def tryPlace(destination : String) : Unit = {
      if(isFound(destination)){ //if player tries to move to foundation
        if(getFoundPile(destination).checkCardValidity(hand)) //check if the card in hand is valid
        {
          getFoundPile(destination).addCard(hand) //place in foundation
          emptyHand()
          primaryStage.getScene.setCursor(Cursor.DEFAULT)
          if(getFoundPile(destination).cardList.head.value == 13){
            getFoundPile(destination).lockFoundation()
          }
          else
            createUndo(destination)
        }
        else
        {
          moveBack(source, hand) //move held card back to its source
          emptyHand()
          primaryStage.getScene.setCursor(Cursor.DEFAULT)
        }
      }
      else //destination not foundation
      {
        if(isWaste(destination)) //if the destination is a waste pile
        {
          if(isWaste(source)) //check if the source was a waste pile
          {
            moveBack(source,hand) //if it is the move is invalid so move the card back
            emptyHand()
            primaryStage.getScene.setCursor(Cursor.DEFAULT)
          }
          else // if the destination is waste and the source was the discard
          {
            getWastePile(destination).addCard(hand)
            createUndo(destination)
            emptyHand()
            primaryStage.getScene.setCursor(Cursor.DEFAULT)
          }
        }
      }
    }
    //moveBack moves the currently held card back to the source pile it came from
    def moveBack(src : String, card: Card): Unit ={
      if(isWaste(src)){
        getWastePile(src).addCard(card)
      }else{
        discardpile.setCard(card)
      }
    }
    //emptyHand clears out the variables that handle the currently held card
    def emptyHand(): Unit ={
      hand = null
      holding = false
      source = ""
    }
    //createUndo creates a new undo string that describes the last move
    def createUndo(str: String): Unit ={
      undomove = source + "-" + str
      undoPossible = true
    }
    //undo undoes the last move based on the undo string created by createUndo
    def undo(): Unit ={
      val moves = undomove.split("-")
      val src = moves(0)
      val dest = moves(1)
      if(isWaste(src)) //if the original place of the card was a waste
      {
        getWastePile(src).addCard(getFoundPile(dest).cardList.head) //the only possible destination for the waste is a found
        getFoundPile(dest).removeTopCard()
      }
      else //the only other source is the discard
      {
        if(isFound(dest)){ //if the card was moved from discard to found
          discardpile.setCard(getFoundPile(dest).cardList.head)
          getFoundPile(dest).removeTopCard()
        }
        else //only other operation is discard to waste
        {
          discardpile.setCard(getWastePile(dest).cardList.head)
          getWastePile(dest).removeTopCard()
        }
      }
      undoPossible = false //undo is now impossible
      undomove = ""
      update()
    }
    //isFound checks if the string refers to a foundation pile
    def isFound( str: String): Boolean = {
      if(str == "found1" || str == "found2" || str == "found3" || str == "found4" )
      {
        true
      }else{
        false
      }
    }
    //isWaste checks if the string refers to a waste pile
    def isWaste(str: String): Boolean={
      if(str == "waste1" || str == "waste2" || str == "waste3" || str == "waste4" )
      {
        true
      }else{
        false
      }

    }
    //getWastePile returns the waste pile described by the string
    def getWastePile(str: String): WastePile = {
      val pile = str match{
        case "waste1" => waste1Pile
        case "waste2" => waste2Pile
        case "waste3" => waste3Pile
        case "waste4" => waste4Pile
      }
      pile
    }
    //getFoundPile returns the foundation pile described by the string
    def getFoundPile(str: String): FoundationPile = {
      val pile = str match{
        case "found1" => found1Pile
        case "found2" => found2Pile
        case "found3" => found3Pile
        case "found4" => found4Pile
      }
      pile
    }
    //update refreshes all the images on the board and is called after every interaction, it also disables or enabled the undo function depending on if the player is able to undo
    def update(): Unit ={
      found1View.setImage(found1Pile.cardList.head.img)
      found2View.setImage(found2Pile.cardList.head.img)
      found3View.setImage(found3Pile.cardList.head.img)
      found4View.setImage(found4Pile.cardList.head.img)
      deck.setImage(back)
      if(!waste1Pile.isEmpty){
        waste1View.setImage(waste1Pile.cardList.head.img)
      }else{
        waste1View.setImage(border)
      }

      if(!waste2Pile.isEmpty){
        waste2View.setImage(waste2Pile.cardList.head.img)
      }else{
        waste2View.setImage(border)
      }

      if(!waste3Pile.isEmpty){
        waste3View.setImage(waste3Pile.cardList.head.img)
      }else{
        waste3View.setImage(border)
      }

      if(!waste4Pile.isEmpty){
        waste4View.setImage(waste4Pile.cardList.head.img)
      }else{
        waste4View.setImage(border)
      }

      if(discardpile.card != null){
        discard.setImage(discardpile.card.img)
      }else{
        discard.setImage(border)
      }

      if(holding){
        held.setImage(hand.img)
      }else{
        held.setImage(border)
      }
      undoMenuItem.setDisable(!undoPossible)

      if(found1Pile.isLocked && found2Pile.isLocked && found3Pile.isLocked && found4Pile.isLocked){
        var winStage : Stage = new Stage()
        winStage.initModality(Modality.NONE)
        winStage.initOwner(primaryStage)
        var winVBox : VBox = new VBox(20)
        winVBox.getChildren().add(new Text("You won the game!"));
        var winScene = new Scene(winVBox, 300, 200);
        winStage.setScene(winScene);
        winStage.show();
      }

    }
    //--------------------HELPER METHODS END--------------------

    //--------------------GUI LISTENERS--------------------
    //clickDeck is a listener that draws a card if possible
    val clickDeck : EventHandler[MouseEvent] = new EventHandler[MouseEvent]() {
      def handle(event: MouseEvent): Unit = {
        if(!holding){ //cant draw card if holding one
          if(discardpile.isEmpty)
          {
            if(deckpile.cardList.nonEmpty){
              discardpile.setCard(deckpile.cardList.head)
              deckpile.removeTopCard()
              undoPossible = false
              if(deckpile.cardList.isEmpty){
                deck.setImage(border)
              }
            }
          }
          update()
        }
      }
    }
    //these listeners call the interact method based on the pile clicked
    val clickDiscard : EventHandler[MouseEvent] = new EventHandler[MouseEvent]() {
      def handle(event: MouseEvent): Unit = {
        interact("discard")
      }
    }
    val clickFoundationPileOne : EventHandler[MouseEvent] = new EventHandler[MouseEvent]() {
      def handle(event: MouseEvent): Unit = {
        interact("found1")
      }
    }
    val clickFoundationPileTwo : EventHandler[MouseEvent] = new EventHandler[MouseEvent]() {
      def handle(event: MouseEvent): Unit = {
        interact("found2")
      }
    }
    val clickFoundationPilethree : EventHandler[MouseEvent] = new EventHandler[MouseEvent]() {
      def handle(event: MouseEvent): Unit = {
        interact("found3")
      }
    }
    val clickFoundationPileFour : EventHandler[MouseEvent] = new EventHandler[MouseEvent]() {
      def handle(event: MouseEvent): Unit = {
        interact("found4")
      }
    }
    val clickWastePileOne : EventHandler[MouseEvent] = new EventHandler[MouseEvent]() {
      def handle(event: MouseEvent): Unit = {
        interact("waste1")
      }
    }
    val clickWastePileTwo : EventHandler[MouseEvent] = new EventHandler[MouseEvent]() {
      def handle(event: MouseEvent): Unit = {
        interact("waste2")

      }
    }
    val clickWastePileThree : EventHandler[MouseEvent] = new EventHandler[MouseEvent]() {
      def handle(event: MouseEvent): Unit = {
        interact("waste3")
      }
    }
    val clickWastePileFour : EventHandler[MouseEvent] = new EventHandler[MouseEvent]() {
      def handle(event: MouseEvent): Unit = {
        interact("waste4")
      }
    }
    //calls undo when clicked
    val undoClick : EventHandler[ActionEvent] = new EventHandler[ActionEvent]() {
      def handle(event: ActionEvent): Unit = {
        if(undoPossible)
          undo()
      }
    }
    val exitClick : EventHandler[ActionEvent] = new EventHandler[ActionEvent]() {
      def handle(event: ActionEvent): Unit = {
        System.exit(0)
      }
    }

    val winClick : EventHandler[ActionEvent] =  new EventHandler[ActionEvent] {
      def handle(event : ActionEvent): Unit = {
        waste1Pile.addCard(new Card(SUIT.SPADE, 13))
        waste2Pile.addCard(new Card(SUIT.CLUB, 13))
        waste3Pile.addCard(new Card(SUIT.DIAMOND, 13))
        waste4Pile.addCard(new Card(SUIT.HEART, 13))
        found1Pile.finishFoundation()
        found2Pile.finishFoundation()
        found3Pile.finishFoundation()
        found4Pile.finishFoundation()
        discardpile.removeCard()
        update()
      }
    }
    //--------------------GUI LISTENERS END--------------------
    //set up GUI
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
    val exitMenuItem : MenuItem = new MenuItem("Exit")
    exitMenuItem.setOnAction(exitClick)
    undoMenuItem.setOnAction(undoClick)
    winGameItem.setOnAction(winClick)
    gameMenu.getItems.add(undoMenuItem)
    gameMenu.getItems.add(exitMenuItem)
    gameMenu.getItems.add(winGameItem)
    bar.getMenus.addAll(gameMenu)
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
    //finish gui
    update()
    val vbox : VBox = new VBox()
    vbox.getChildren.add(bar)
    vbox.getChildren.add(root)
    val scene = new Scene(vbox)
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


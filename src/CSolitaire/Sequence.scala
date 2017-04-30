package CSolitaire

import scala.collection.mutable.ListBuffer

/**
  * Created by DefaultPC on 4/9/2017.
  */
class Sequence(var startValue : Int) {
  var i = 0
  var initialSequenceString: String = ""
  var currentSequenceString: String = ""
  if(startValue == 1) {
    for(i <- 2 to 13){
      i match {
        case 11 => initialSequenceString += "J"
        case 12 => initialSequenceString += "Q"
        case 13 => initialSequenceString += "K"
        case default => initialSequenceString += i.toString()
      }
    }
    currentSequenceString = initialSequenceString
  }

  if(startValue == 2) {
    var i = 4
    do{
      i match {
        case 1 => initialSequenceString += "A"
        case 11 => initialSequenceString += "J"
        case 12 => initialSequenceString += "Q"
        case 13 => initialSequenceString += "K"
        case default => initialSequenceString += i.toString()
      }
      i += 2
      if(i > 13)
        i = i - 13
    }while(i != 13)
    initialSequenceString += "K"
    currentSequenceString = initialSequenceString
  }

  if(startValue == 3) {
    var i = 6
    do {
      i match {
        case 1 => initialSequenceString += "A"
        case 11 => initialSequenceString += "J"
        case 12 => initialSequenceString += "Q"
        case 13 => initialSequenceString += "K"
        case default => initialSequenceString += i.toString()
      }
      i += 3
      if(i > 13)
        i = i - 13
    }while(i != 13)
    initialSequenceString += "K"
    currentSequenceString = initialSequenceString
  }

  if(startValue == 4) {
    var i = 8
    do {
      i match {
        case 1 => initialSequenceString += "A"
        case 11 => initialSequenceString += "J"
        case 12 => initialSequenceString += "Q"
        case 13 => initialSequenceString += "K"
        case default => initialSequenceString += i.toString()
      }
      i += 4
      if(i > 13)
        i = i - 13
    }while(i != 13)
    initialSequenceString += "K"
    currentSequenceString = initialSequenceString
  }

  def removePartOfString(value: Int): Unit = {
    if(startValue == 1){
      if(value != 13)
      currentSequenceString = initialSequenceString.substring(value)
      else
        currentSequenceString = ""
    }
    else if(startValue == 2){
      if(startValue != value) {
        value match{
          case 4 |6 | 8 | 10 | 12 => currentSequenceString = initialSequenceString.substring((value/2))
          case 1 | 3 | 5 | 7 | 9 | 11 => currentSequenceString = initialSequenceString.substring(((value+13)/2))
          case 0 | 13 => currentSequenceString = ""
        }
      }
    }
    else if(startValue == 3){
      if(startValue != value){
        value match {
          case 6 | 9 | 12 => currentSequenceString = initialSequenceString.substring((value/3))
          case 2 | 5 | 8 | 11 => currentSequenceString = initialSequenceString.substring(((value+13)/3))
          case 1 | 4 | 7 | 10 => currentSequenceString = initialSequenceString.substring(((value+26)/3))
          case 0 | 13 => currentSequenceString = ""
        }
      }
    }
    else {
      if(startValue != value) {
        value match {
          case 8 | 12 => currentSequenceString = initialSequenceString.substring(value / 4)
          case 3 | 7 | 11 => currentSequenceString = initialSequenceString.substring((value + 13) / 4)
          case 2 | 6 | 10 => currentSequenceString = initialSequenceString.substring((value + 26) / 4)
          case 1 | 5 | 9 => currentSequenceString = initialSequenceString.substring((value + 39) / 4)
          case 0 | 13 => currentSequenceString = ""
        }
      }
    }
  }


}

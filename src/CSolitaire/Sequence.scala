package CSolitaire

import scala.collection.mutable.ListBuffer

/**
  * Created by DefaultPC on 4/9/2017.
  */
class Sequence(var startValue : Int) {
  var i = 0
  var sequenceOrder : ListBuffer[Int] = new ListBuffer[Int]()
  if(startValue == 1) {
    for(i <- 1 to 13){
      sequenceOrder.insert(i)
    }
  }

  if(startValue == 2) {
    var i = 2
    do{
      sequenceOrder.insert(i)
      i += 2
      if(i > 13)
        i = i - 13
    }while(i != 13)
    sequenceOrder.insert(13)
  }

  if(startValue == 3) {
    var i = 3
    do {
      sequenceOrder.insert(i)
      i += 3
      if (i > 13)
        i = i - 13
    }while(i != 13)
    sequenceOrder.insert(13)
  }

  if(startValue == 4) {
    var i = 4
    do {
      sequenceOrder.insert(i)
      i += 4
      if(i > 13)
        i = i - 13
    }while(i != 13)
    sequenceOrder.insert(13)
  }

}

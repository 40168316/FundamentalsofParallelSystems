package c5
     
import org.jcsp.lang.*
import org.jcsp.groovy.*
import c05.ScaledData     
   
class Scale implements CSProcess {
  def int scaling
  def ChannelOutput outChannel
  def ChannelOutput factor
  def ChannelInput inChannel
  def ChannelInput suspend
  def ChannelInput injector
  
  void run () {
    def SECOND = 1000
    def DOUBLE_INTERVAL = 5 * SECOND
    def SUSPEND  = 0
    def INJECT   = 1
    def TIMER    = 2
    def INPUT    = 3
    
    def timer = new CSTimer()
    def scaleAlt = new ALT ( [ suspend, injector, timer, inChannel ] )
    
    def preCon = new boolean [4]
    preCon[SUSPEND] = true
    preCon[INJECT] = false
    preCon[TIMER] = true
    preCon[INPUT] = true
    def suspended = false
                                                                    
    def timeout = timer.read() + DOUBLE_INTERVAL
    timer.setAlarm ( timeout )
    
    while (true) {
      switch ( scaleAlt.priSelect(preCon) ) {
        case SUSPEND :
          //  deal with suspend input  
		  println "Suspend Input is ${scaling}" 
		  preCon[SUSPEND] = false
		  preCon[INPUT] = true
          break
        case INJECT:
          //  deal with inject input
		  println "Inject Input is ${scaling}"
          break
        case TIMER:
          //  deal with Timer input
          println "Normal Timer: new scaling is ${scaling}"
		  preCon[TIMER] = false
          break
        case INPUT:
          //   deal with Input channel 
		  def inValue = inChannel.read()
		  def result = new ScaledData()
		  result.original = inValue
		  result.scaled = inValue * scaling
		  outChannel.write(result)
		  preCon[INJECT] = true
          break
      } //end-switch
    } //end-while
  } //end-run
}
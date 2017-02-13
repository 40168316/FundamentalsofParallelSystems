package c07

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel

import org.jcsp.lang.*
import org.jcsp.groovy.*


class Client implements CSProcess{  
	
  def ChannelInput receiveChannel
  def ChannelOutput requestChannel
  def clientNumber   
  def selectList = [ ] 
   
  void run () {
    def iterations = selectList.size
	//println "interaction ${iterations}"
    println "Client $clientNumber has $iterations values in $selectList"
	
    for ( i in 0 ..< iterations) {
      def key = selectList[i]
	  println "key ${key}"
      requestChannel.write(key)
	  //println "yo"
      def v = receiveChannel.read()
	  println "v ${v}"
    }
	
    println "Client $clientNumber has finished"
  }
}

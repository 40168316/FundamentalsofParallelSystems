package c2

import org.jcsp.lang.*

class ListToStream implements CSProcess{
	
	def ChannelInput inChannel
	def ChannelOutput outChannel
	
	void run (){
		// Change made to line below
		def inList =[]
		while (inList[0] != -1) {
			// hint: output	list elements as single integers
			inList = inChannel.read()
			println " Three Object is [${inList[0]}, ${inList[1]}, ${inList[2]}]"
			outChannel.write(inList[0])
			outChannel.write(inList[1])
			outChannel.write(inList[2])
		}
		outChannel.write(-1)
	}
}
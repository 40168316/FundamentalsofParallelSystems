package c2

import org.jcsp.lang.*

class CreateSetsOfEight implements CSProcess{
	
	def ChannelInput inChannel

	void run(){
		def outList = []
		// Possibly just define, maybe not
		def v //= inChannel.read()
		while (v != -1){
			for ( i in 0 .. 7 ) {
				// put v into outList and read next input
				// Check order of these
				v = inChannel.read()
				outList[i] = v			
			}
			println " Eight Object is ${outList}"
		}
		println "Finished"
	}
}
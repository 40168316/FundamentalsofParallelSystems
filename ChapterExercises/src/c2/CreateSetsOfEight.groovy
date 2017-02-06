package c2

import org.jcsp.lang.*

class CreateSetsOfEight implements CSProcess{
	
	def ChannelInput inChannel
	def outList1 = []
	void run(){
		def v = inChannel.read()
		while (v != -1){
			def outList = []
			for ( i in 0 .. 7 ) {
				// put v into outList and read next input
				outList[i] = v			
				v = inChannel.read()
			}
			println " Eight Object is ${outList}"
			outList1 << outList
		}
		println "Finished $outList1"
	}
}
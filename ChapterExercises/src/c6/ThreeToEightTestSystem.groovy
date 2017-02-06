package c6

import org.jcsp.groovy.PAR
import org.jcsp.lang.ChannelInput;
import org.jcsp.lang.*
import groovy.util.GroovyTestCase;
import c2.*;

// Class extending the GroovyTestCase
class ThreeToEightTestSystem extends GroovyTestCase {
void testProcess(){
	// Channels used within the exercise
	One2OneChannel connect1 = Channel.createOne2One()
	One2OneChannel connect2 = Channel.createOne2One()
	// Using CreateSetsOfEight from the process list, input the Inputted sets of 8
	def InputtedSetsOfEight = new CreateSetsOfEight (inChannel:connect2.in())
	
	// Process list
	def processList = [
		new GenerateSetsOfThree ( outChannel: connect1.out() ),
        new ListToStream ( inChannel: connect1.in(), outChannel: connect2.out() ),
		InputtedSetsOfEight
	]
	
	new PAR (processList).run()

	def expected = [[1, 2, 3, 4, 5, 6, 7, 8], [9, 10, 11, 12, 13, 14, 15, 16], [17, 18, 19, 20, 21, 22, 23, 24]]
	def actual = InputtedSetsOfEight.outList1

	assertTrue(expected == actual)
	}
}
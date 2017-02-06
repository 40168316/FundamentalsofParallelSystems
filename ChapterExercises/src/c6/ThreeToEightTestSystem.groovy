package c6

import org.jcsp.groovy.PAR
import org.jcsp.lang.One2OneChannel
import groovy.util.GroovyTestCase;
import c2.*;

// Class extending the GroovyTestCase
class ThreeToEightTestSystem extends GroovyTestCase {
void testProcess(){
	// Channels used within the exercise
	One2OneChannel connect1 = Channel.createOne2One()
	One2OneChannel connect2 = Channel.createOne2One()
	// Using CreateSetsOfEight from the process list, input the Inputted sets of 8
	CreateSetsOfEight InputtedSetsOfEight = new CreateSetsOfEight (inChannel:connect2.in())
	
	// Process list
	def processList = [
		new GenerateSetsOfThree (outChannel:connect1.out()),
		new ListToStream(inChannel:connect1.in(), outChannel:connect2.out()),
		InputtedSetsOfEight
	]
	
	def actual = [17, 18, 19, 20, 21, 22, 23, 24]
	def expected = InputtedSetsOfEight
	new PAR (processList).run()
	// If expected equals actual results then true
	println actual
	//assertTrue(expected == actual)
	}
}
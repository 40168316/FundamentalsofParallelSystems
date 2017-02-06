package c2

import org.jcsp.groovy.PAR
import org.jcsp.lang.One2OneChannel

// Class extending the GroovyTestCase
class ThreeToEightTestSystem extends GroovyTestCase {
void testProcess(){
	// Channels used within the exercise
	One2OneChannel connect1 = Channel.createOne2One()
	One2OneChannel connect2 = Channel.createOne2One()
	
	// Process list
	def processList = [
		new GenerateSetsOfThree (outChannel: connect1.out()),
		new ListToStream(inChannel: connect1.in, outChannel: connect2.out()),
		new CreateSetsOfEight ( inChannel: connect2.in() )
		]
	
	CreateSetsOfEight.println 
	//def expected = CreateSetsOfEight
	//def actual = [1, 2, 3, 4, 5, 6, 7, 8]
	//def actual = [9, 10, 11, 12, 13, 14, 15, 16]
	//def actual = [17, 18, 19, 20, 21, 22, 23, 24]
	
	//new PAR (processList).run()
	// If expected equals actual results then true
	//assertTrue(expected == actual)
	}
}

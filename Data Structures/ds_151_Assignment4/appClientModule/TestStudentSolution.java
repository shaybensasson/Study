import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * TODO
 */

/**
 * @author Shay Ben-Sasson (061203410) and Itav Eliyahu (042828855)
 *
 */
public class TestStudentSolution extends TestBase {

	private static final String path = "C:\\Users\\Shay\\workspace\\ds_151_Assignment4\\appClientModule\\";
	
	private static MyInterface DB1_studentSolution;
	private static MyInterface DB2_studentSolution;
	
	private static final int MIN_RANGE = 0;
	private static final int MAX_RANGE = Integer.MAX_VALUE;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		ObjectWithCoordinates[] owcs;
		owcs = readObjectWithCoordinatesFile(path + "DB1.txt");
		DB1_studentSolution = new StudentSolution();
		for (ObjectWithCoordinates owc : owcs)
			DB1_studentSolution.insertDataFromDBFile((String)owc.getData(), owc.getX(), owc.getY());
		
		owcs = readObjectWithCoordinatesFile(path + "DB2.txt");
		DB2_studentSolution = new StudentSolution();
		for (ObjectWithCoordinates owc : owcs)
			DB2_studentSolution.insertDataFromDBFile((String)owc.getData(), owc.getX(), owc.getY());
	}

	/*@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}*/
	
	private final ObjectWithCoordinatesComparator compX = new ObjectWithCoordinatesByXThenYComparator();
	private final ObjectWithCoordinatesComparator compY = new ObjectWithCoordinatesByYThenXComparator();
	
	@Test
	public void testSol1_NoItemsInRange_DB1() {
		//arrange
		String[] expected = new String[0];
		
		//act
		String[] actual = DB1_studentSolution.firstSolution(MAX_RANGE, MAX_RANGE, MAX_RANGE, MAX_RANGE);		
				
		//assert
		assertTrue(stringArrayEquals(expected, actual));
	}
	
	@Test
	public void testSol2_NoItemsInRange_DB1() {
		//arrange
		String[] expected = new String[0];
		
		//act
		String[] actual = DB1_studentSolution.secondSolution(MAX_RANGE, MAX_RANGE, MAX_RANGE, MAX_RANGE);		
				
		//assert
		assertTrue(stringArrayEquals(expected, actual));
	}
	
	@Test
	public void testSol3_NoItemsInRange_DB1() {
		//arrange
		String[] expected = new String[0];
		
		//act
		String[] actual = DB1_studentSolution.thirdSolution(MAX_RANGE, MAX_RANGE, MAX_RANGE, MAX_RANGE);		
				
		//assert
		assertTrue(stringArrayEquals(expected, actual));
	}
	
	@Test
	public void testSol1_NoItemsInRange_DB2() {
		//arrange
		String[] expected = new String[0];
		
		//act
		String[] actual = DB2_studentSolution.firstSolution(MAX_RANGE, MAX_RANGE, MAX_RANGE, MAX_RANGE);		
				
		//assert
		assertTrue(stringArrayEquals(expected, actual));
	}
	
	@Test
	public void testSol2_NoItemsInRange_DB2() {
		//arrange
		String[] expected = new String[0];
		
		//act
		String[] actual = DB2_studentSolution.secondSolution(MAX_RANGE, MAX_RANGE, MAX_RANGE, MAX_RANGE);		
				
		//assert
		assertTrue(stringArrayEquals(expected, actual));
	}
	
	@Test
	public void testSol3_NoItemsInRange_DB2() {
		//arrange
		String[] expected = new String[0];
		
		//act
		String[] actual = DB2_studentSolution.thirdSolution(MAX_RANGE, MAX_RANGE, MAX_RANGE, MAX_RANGE);		
				
		//assert
		assertTrue(stringArrayEquals(expected, actual));
	}
	
	@Test
	public void testSol1_AllRange_DB1() {
		//arrange
		String expected = "[Hefer X=140 Y=48, Amir X=295 Y=50, Eyal X=455 Y=51, Lilach X=546 Y=98, Tali X=246 Y=107, Yardena X=252 Y=172, Daniel X=506 Y=202]";
		
		//act
		String[] res = DB1_studentSolution.firstSolution(MIN_RANGE, MIN_RANGE, MAX_RANGE, MAX_RANGE);		
		
		String actual = Arrays.deepToString(res); 
				
		//assert
		assertEquals(expected, actual);
	}
	
	@Test
	public void testSol2_AllRange_DB1() {
		//arrange
		String expected = "[Hefer X=140 Y=48, Amir X=295 Y=50, Eyal X=455 Y=51, Lilach X=546 Y=98, Tali X=246 Y=107, Yardena X=252 Y=172, Daniel X=506 Y=202]";
		
		//act
		String[] res = DB1_studentSolution.secondSolution(MIN_RANGE, MIN_RANGE, MAX_RANGE, MAX_RANGE);		
		
		String actual = Arrays.deepToString(res); 
				
		//assert
		assertEquals(expected, actual);
	}
	
	@Test
	public void testSol3_AllRange_DB1() {
		//arrange
		String expected = "[Hefer X=140 Y=48, Amir X=295 Y=50, Eyal X=455 Y=51, Lilach X=546 Y=98, Tali X=246 Y=107, Yardena X=252 Y=172, Daniel X=506 Y=202]";
		
		//act
		String[] res = DB1_studentSolution.thirdSolution(MIN_RANGE, MIN_RANGE, MAX_RANGE, MAX_RANGE);		
		
		String actual = Arrays.deepToString(res); 
				
		//assert
		assertEquals(expected, actual);
	}
	
	@Test
	public void testSol1_AllRange_DB2() {
		//arrange
		String expected = "[BarneyStinson X=144 Y=76, TedMosby X=327 Y=102, MarshallEriksen X=500 Y=112, RobinScherbatsky X=243 Y=116, LilyAldrin X=409 Y=120]";
		
		//act
		String[] res = DB2_studentSolution.firstSolution(MIN_RANGE, MIN_RANGE, MAX_RANGE, MAX_RANGE);		
		
		String actual = Arrays.deepToString(res);
				
		//assert
		assertEquals(expected, actual);
	}
	
	@Test
	public void testSol2_AllRange_DB2() {
		//arrange
		String expected = "[BarneyStinson X=144 Y=76, TedMosby X=327 Y=102, MarshallEriksen X=500 Y=112, RobinScherbatsky X=243 Y=116, LilyAldrin X=409 Y=120]";
		
		//act
		String[] res = DB2_studentSolution.secondSolution(MIN_RANGE, MIN_RANGE, MAX_RANGE, MAX_RANGE);		
		
		String actual = Arrays.deepToString(res);
				
		//assert
		assertEquals(expected, actual);
	}
	
	@Test
	public void testSol3_AllRange_DB2() {
		//arrange
		String expected = "[BarneyStinson X=144 Y=76, TedMosby X=327 Y=102, MarshallEriksen X=500 Y=112, RobinScherbatsky X=243 Y=116, LilyAldrin X=409 Y=120]";
		
		//act
		String[] res = DB2_studentSolution.thirdSolution(MIN_RANGE, MIN_RANGE, MAX_RANGE, MAX_RANGE);		
		
		String actual = Arrays.deepToString(res);
				
		//assert
		assertEquals(expected, actual);
	}
	
	@Test
	public void testSol1_SingleItemInRange_DB1() {
		//arrange
		String expected = "[Hefer X=140 Y=48]";
		
		//act
		String[] res = DB1_studentSolution.firstSolution(140, 48, 140, 48);		
		
		String actual = Arrays.deepToString(res); 
				
		//assert
		assertEquals(expected, actual);
	}
	
	@Test
	public void testSol2_SingleItemInRange_DB1() {
		//arrange
		String expected = "[Hefer X=140 Y=48]";
		
		//act
		String[] res = DB1_studentSolution.secondSolution(140, 48, 140, 48);		
		
		String actual = Arrays.deepToString(res); 
				
		//assert
		assertEquals(expected, actual);
	}
	
	@Test
	public void testSol3_SingleItemInRange_DB1() {
		//arrange
		String expected = "[Hefer X=140 Y=48]";
		
		//act
		String[] res = DB1_studentSolution.thirdSolution(140, 48, 140, 48);		
		
		String actual = Arrays.deepToString(res); 
				
		//assert
		assertEquals(expected, actual);
	}
	
	@Test
	public void testSol1_SingleItemInRange_DB2() {
		//arrange
		String expected = "[RobinScherbatsky X=243 Y=116]";
		
		//act
		String[] res = DB2_studentSolution.firstSolution(243, 116, 243, 116);		
		
		String actual = Arrays.deepToString(res); 
				
		//assert
		assertEquals(expected, actual);
	}
	
	@Test
	public void testSol2_SingleItemInRange_DB2() {
		//arrange
		String expected = "[RobinScherbatsky X=243 Y=116]";
		
		//act
		String[] res = DB2_studentSolution.secondSolution(243, 116, 243, 116);		
		
		String actual = Arrays.deepToString(res); 
				
		//assert
		assertEquals(expected, actual);
	}
	
	@Test
	public void testSol3_SingleItemInRange_DB2() {
		//arrange
		String expected = "[RobinScherbatsky X=243 Y=116]";
		
		//act
		String[] res = DB2_studentSolution.thirdSolution(243, 116, 243, 116);		
		
		String actual = Arrays.deepToString(res); 
				
		//assert
		assertEquals(expected, actual);
	}

	/**
	 * 
	 */
	public void testSol1_Temp_OneMil() {
		//arrange
		ObjectWithCoordinates[] owcs;
		owcs = readObjectWithCoordinatesFile(path + "milliondollarhomepage.txt");
		StudentSolution OneMil_studentSolution = new StudentSolution();
		for (ObjectWithCoordinates owc : owcs)
			OneMil_studentSolution.insertDataFromDBFile((String)owc.getData(), owc.getX(), owc.getY());
		
		String expected = "[RobinScherbatsky X=243 Y=116]";
		
		//act
		String[] res = OneMil_studentSolution.firstSolution(267, 398, 581, 324);		
		
		String actual = Arrays.deepToString(res); 
				
		//assert
		assertEquals(expected, actual);
		
	}
	
	
	
}

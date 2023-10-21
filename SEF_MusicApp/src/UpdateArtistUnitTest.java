import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.stream.*;
import org.junit.jupiter.params.provider.*;
import org.junit.jupiter.params.ParameterizedTest;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class UpdateArtistUnitTest {

	/// *********** Test cases for the function Update artist method
	/// *****************

	//// To update occupation by Artist ID where birthdate year is before 2000
	static Stream<Arguments> testDataOfUpdateArtistCase1() {
		ArrayList<String> occupationData1 = new ArrayList<String>();
		occupationData1.add("Lyricist");
		occupationData1.add("Musician");

		ArrayList<String> occupationData2 = new ArrayList<String>();
		occupationData1.add("Lyricist");
		occupationData1.add("Musician");

		ArrayList<String> Genres = new ArrayList<String>();
		ArrayList<String> Awards = new ArrayList<String>();
		return Stream.of(
				//// Data with ID and only occupation to update with test data 1
				Arguments.of("987ABCDE@#", "", "", "", "", occupationData1, Genres, Awards),

				//// Data with ID and only occupation to update with test data 2
				Arguments.of("987ABCDE@#", "", "", "", "", occupationData2, Genres, Awards)

		);
	}

	//// To update Award's title by Artist ID where award given year is before 2000
	static Stream<Arguments> testDataOfUpdateArtistCase3() {
		ArrayList<String> occupation = new ArrayList<String>();
		ArrayList<String> Genres = new ArrayList<String>();
		ArrayList<String> AwardsData1 = new ArrayList<String>();
		AwardsData1.add("1999, New updated music award");
		ArrayList<String> AwardsData2 = new ArrayList<String>();
		AwardsData2.add("1999, Updated award for the artist");
		return Stream.of(
				//// Data with ID and only Award to update with test data 1
				Arguments.of("987ABCDE@#", "", "", "", "", occupation, Genres, AwardsData1),

				//// Data with ID and only Award to update with test data 2
				Arguments.of("987ABCDE@#", "", "", "", "", occupation, Genres, AwardsData2)

		);
	}

	//// To update Award's title by Artist ID where award given year is before 2000
	//// with invalid award details
	static Stream<Arguments> testDataOfUpdateArtistCase5() {
		ArrayList<String> occupation = new ArrayList<String>();
		ArrayList<String> Genres = new ArrayList<String>();
		ArrayList<String> AwardsData1 = new ArrayList<String>();
		AwardsData1.add("2001, Updated award");
		ArrayList<String> AwardsData2 = new ArrayList<String>();
		AwardsData2.add("2001, SIMA best playback singer and music composer of the year 2001");
		return Stream.of(
				//// TEST DATA 1 = Data with ID and only Award to update where title is only 2
				//// words.
				Arguments.of("567NNNRR_%", "", "", "", "", occupation, Genres, AwardsData1),

				//// TEST DATA 2 = Data with ID and only Award to update where title is more
				//// than 10 words.
				Arguments.of("567NNNRR_%", "", "", "", "", occupation, Genres, AwardsData2)

		);
	}

	//// To update bio of the artist
	static Stream<Arguments> testDataOfUpdateArtistCase6() {
		String bio1 = "New bio of the artist to update with more than 10 words";
		String bio2 = "New bio of the  artist";
		ArrayList<String> occupation = new ArrayList<String>();
		ArrayList<String> Genres = new ArrayList<String>();
		ArrayList<String> AwardsData = new ArrayList<String>();
		boolean expectedResult1 = true;
		boolean expectedResult2 = false;
		return Stream.of(
				//// TEST DATA 1 = Data with ID and only Award to update where title is only 2
				//// words.
				Arguments.of("567NNNRR_%", "", "", "", bio1, occupation, Genres, AwardsData, expectedResult1),

				//// TEST DATA 2 = Data with ID and only Award to update where title is more
				//// than 10 words.
				Arguments.of("567NNNRR_%", "", "", "", bio2, occupation, Genres, AwardsData, expectedResult2)

		);
	}

////*************************************************************************************************************************************************************************************************************

	//// ******************************************** TEST METHODS FOR UPDATE
	//// FUNCTION. ***********************************************************

	/// TEST CASE 1
	@ParameterizedTest
	@MethodSource("testDataOfUpdateArtistCase1")
	@DisplayName("Test Update Artist with occupation with birthdate before 2000")
	public void testUpdateArtist_TestCase1(String ArtistID, String Name, String Address, String Birthdate, String Bio,
			ArrayList<String> Occupations, ArrayList<String> Genres, ArrayList<String> Awards) {
		Artist TestData1 = new Artist(ArtistID, Name, Address, Birthdate, Bio, Occupations, Genres, Awards);
		boolean data = TestData1.UpdateArtist();
		Assert.assertTrue(data == false);
	}

	/// TEST CASE 2
	@Test
	@DisplayName("Test Update Artist with occupation with birthdate after 2000 - Test Dat 1")
	public void testUpdateArtist_TestCase2_TestData1() {
		ArrayList<String> occupationData1 = new ArrayList<String>();
		occupationData1.add("Lyricist");
		occupationData1.add("Musician");
		ArrayList<String> Genres = new ArrayList<String>();
		ArrayList<String> Awards = new ArrayList<String>();
		Artist TestData1 = new Artist("567NNNRR_%", "", "", "", "", occupationData1, Genres, Awards);
		boolean data = TestData1.UpdateArtist();
		Assert.assertTrue(data == true);
	}

	//// TEST CASE 2
	@Test
	@DisplayName("Test Update Artist with occupation with birthdate after 2000 - Test Data 2")
	public void testUpdateArtist_TestCase2_TestData2() {
		ArrayList<String> occupationData2 = new ArrayList<String>();
		occupationData2.add("Composer");
		occupationData2.add("Producer");
		ArrayList<String> Genres = new ArrayList<String>();
		ArrayList<String> Awards = new ArrayList<String>();
		Artist TestData1 = new Artist("567NNNRR_%", "", "", "", "", occupationData2, Genres, Awards);
		boolean data = TestData1.UpdateArtist();
		Assert.assertTrue(data == true);
	}

	//// TEST CASE 3
	@ParameterizedTest
	@MethodSource("testDataOfUpdateArtistCase3")
	@DisplayName("Test Update Artist's award with the given year before 2000")
	public void testUpdateArtist_TestCase3(String ArtistID, String Name, String Address, String Birthdate, String Bio,
			ArrayList<String> Occupations, ArrayList<String> Genres, ArrayList<String> Awards) {
		Artist TestData1 = new Artist(ArtistID, Name, Address, Birthdate, Bio, Occupations, Genres, Awards);
		boolean data = TestData1.UpdateArtist();
		Assert.assertTrue(data == false);
	}

	/// TEST CASE 4
	@Test
	@DisplayName("Test Update Award's title which was given after 2000 - Test Data 1")
	public void testUpdateArtist_TestCase4_TestData1() {
		ArrayList<String> occupation = new ArrayList<String>();
		ArrayList<String> Genres = new ArrayList<String>();
		ArrayList<String> Awards = new ArrayList<String>();
		Awards.add("2001,Updated award for the artist");
		Artist TestData1 = new Artist("567NNNRR_%", "", "", "", "", occupation, Genres, Awards);
		boolean data = TestData1.UpdateArtist();
		Assert.assertTrue(data == true);
	}

	/// TEST CASE 4
	@Test
	@DisplayName("Test Update Award's title which was given after 2000 - Test Data 2")
	public void testUpdateArtist_TestCase4_TestData2() {
		ArrayList<String> occupation = new ArrayList<String>();
		ArrayList<String> Genres = new ArrayList<String>();
		ArrayList<String> Awards = new ArrayList<String>();
		Awards.add("2001,Updated award for the second time");
		Artist TestData1 = new Artist("567NNNRR_%", "", "", "", "", occupation, Genres, Awards);
		boolean data = TestData1.UpdateArtist();
		Assert.assertTrue(data == true);
	}

	/// TEST CASE 5
	@ParameterizedTest
	@MethodSource("testDataOfUpdateArtistCase5")
	@DisplayName("Test Update Artist's award with the given year after 2000 with invalid title")
	public void testUpdateArtist_TestCase5(String ArtistID, String Name, String Address, String Birthdate, String Bio,
			ArrayList<String> Occupations, ArrayList<String> Genres, ArrayList<String> Awards) {
		Artist TestData1 = new Artist(ArtistID, Name, Address, Birthdate, Bio, Occupations, Genres, Awards);
		boolean data = TestData1.UpdateArtist();
		Assert.assertTrue(data == false);
	}

	/// TEST CASE 6
	@ParameterizedTest
	@MethodSource("testDataOfUpdateArtistCase6")
	@DisplayName("Test Update Artist's award with the given year after 2000 with invalid title")
	public void testUpdateArtist_TestCase6(String ArtistID, String Name, String Address, String Birthdate, String Bio,
			ArrayList<String> Occupations, ArrayList<String> Genres, ArrayList<String> Awards, boolean expectedResult) {
		Artist TestData1 = new Artist(ArtistID, Name, Address, Birthdate, Bio, Occupations, Genres, Awards);
		boolean data = TestData1.UpdateArtist();
		Assert.assertTrue(data == expectedResult);
	}
}

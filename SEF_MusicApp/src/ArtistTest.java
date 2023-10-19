import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.stream.*;
import org.junit.jupiter.params.provider.*;
import org.junit.jupiter.params.ParameterizedTest;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class ArtistTest 
{
	////Valid input
	  static Stream<Arguments> testDataOfCase1() 
	  {		  
			ArrayList<String> occupation = new ArrayList<String>();
			occupation.add("Singer");
			occupation.add("Developer");
			occupation.add("Driver");
			ArrayList<String> Genres = new ArrayList<String>();
			Genres.add("pop");
			Genres.add("Jazz");
			ArrayList<String> Awards = new ArrayList<String>();
			Awards.add("1999,69th film fare awards");
			Awards.add("2002,25th film fare awards");
	        return Stream.of(
	            Arguments.of("567NNNRR_%", "Case1",  "Melbourne|Victoria|Australia","25-06-1999", "Sample bio for the test data 1 which contains 10 words", occupation,Genres, Awards),  
	            Arguments.of("987ABCDE@#", "Case1",  "Melbourne|Victoria|Australia","25-06-2023", "Sample bio for the test data 2 which contains 10 words", occupation,Genres, Awards)  
	        );
	    }
	  
	  ////Invalid artist id
	  static Stream<Arguments> testDataOfCase2() 
	  {		  
			ArrayList<String> occupation = new ArrayList<String>();
			occupation.add("Singer");
			occupation.add("Developer");
			occupation.add("Driver");
			ArrayList<String> Genres = new ArrayList<String>();
			Genres.add("pop");
			Genres.add("Jazz");
			ArrayList<String> Awards = new ArrayList<String>();
			Awards.add("1999,69th film fare awards");
	        return Stream.of(
	        	////Data where the ID is less than 10 characters
	            Arguments.of("NNNRR", "Case1",  "Melbourne|Victoria|Australia","25-06-1999", "Sample bio for the test data 2 which contains 10 words", occupation,Genres, Awards),  
	            
	            ////Data where the ID has only two digits at the starting of string
	            Arguments.of("12NNNRR@# ", "Case1",  "Melbourne|Victoria|Australia","25-06-2023", "Sample bio for the test data 2 which contains 10 words", occupation,Genres, Awards),  
	            
	            ////Data where the ID has no upper case letters from 4th to 8th character
	            Arguments.of("589nnnrr@# ", "Case1",  "Melbourne|Victoria|Australia","25-06-2023", "Sample bio for the test data 2 which contains 10 words", occupation,Genres, Awards)  
	        );
	    }
	  
	  ////Invalid birth date
	  static Stream<Arguments> testDataOfCase3() 
	  {		  
			ArrayList<String> occupation = new ArrayList<String>();
			occupation.add("Singer");
			occupation.add("Developer");
			occupation.add("Driver");
			ArrayList<String> Genres = new ArrayList<String>();
			Genres.add("pop");
			Genres.add("Jazz");
			ArrayList<String> Awards = new ArrayList<String>();
			Awards.add("1999,69th film fare awards");
	        return Stream.of(
	        	////Data where the date format of birth date is 'yyyy-mm-dd'
	            Arguments.of("987ABCDE@#", "Case1",  "Melbourne|Victoria|Australia","1999-06-25", "Sample bio for the test data 2 which contains 10 words", occupation,Genres, Awards),  
	            
	            ////Data where the the date format of birth date is 'dd-mmm-yyyy'
	            Arguments.of("987ABCDE@#", "Case1",  "Melbourne|Victoria|Australia","25-Jun-2023", "Sample bio for the test data 2 which contains 10 words", occupation,Genres, Awards)
	         );
	    }
	  
	  ////Invalid bio
	  static Stream<Arguments> testDataOfCase4() 
	  {		  
			ArrayList<String> occupation = new ArrayList<String>();
			occupation.add("Singer");
			occupation.add("Developer");
			occupation.add("Driver");
			ArrayList<String> Genres = new ArrayList<String>();
			Genres.add("pop");
			Genres.add("Jazz");
			ArrayList<String> Awards = new ArrayList<String>();
			Awards.add("1999,69th film fare awards");
			String bio = "Sample bio for the test data 2 which contains more than thirty words so that the test case will be passed and the function will validate and the condition fails which will leads or makes the function to return false as the return value of add artist function";
	        return Stream.of(
	        	////Data where the Bio of artist contains less than 10 words
	            Arguments.of("987ABCDE@#", "Case1",  "Melbourne|Victoria|Australia","25-06-1999", "Sample bio", occupation,Genres, Awards),  
	            
	            ////Data where the bio of artist contains more than 30 words
	            Arguments.of("987ABCDE@#", "Case1",  "Melbourne|Victoria|Australia","25-06-2023", bio, occupation,Genres, Awards)  
	        );
	    }
	  
	  	////Invalid Awards
		static Stream<Arguments> testDataOfCase5() 
		{		  
			ArrayList<String> occupation = new ArrayList<String>();
			occupation.add("Singer");
			occupation.add("Developer");
			occupation.add("Driver");
			ArrayList<String> Genres = new ArrayList<String>();
			Genres.add("pop");
			Genres.add("Jazz");
			ArrayList<String> AwardsCase1 = new ArrayList<String>();
			AwardsCase1.add("1999,SiMA awards");
			ArrayList<String> AwardsCase2 = new ArrayList<String>();
			AwardsCase2.add("1999,Best playback singer of the year");
			AwardsCase2.add("2002,best voice of the year");
			AwardsCase2.add("2005,sensational singer of the year");
			AwardsCase2.add("2000,Best music composition of the year");
			ArrayList<String> AwardsCase3 = new ArrayList<String>();
			AwardsCase3.add("SIMA music fare awards");
			
	        return Stream.of(
	        	////Data where the Award title of artist contains less than  words
	            Arguments.of("987ABCDE@#", "Case1",  "Melbourne|Victoria|Australia","25-06-1999", "Sample bio for the test data 1 which contains 10 words ", occupation,Genres, AwardsCase1),  
	            
	            ////Data where the artist doesn't have any awards
	            Arguments.of("987ABCDE@#", "Case1",  "Melbourne|Victoria|Australia","25-06-2023", "Sample bio for the test data 2 which contains 10 words ", occupation,Genres, AwardsCase2),  
	        
	            ////Data where the award of the artist doesn't have a year.
	            Arguments.of("987ABCDE@#", "Case1",  "Melbourne|Victoria|Australia","25-06-2023", "Sample bio for the test data 3 which contains 10 words ", occupation,Genres, AwardsCase3)  
	        
	        		
    		);
	    }
		
		 ////Invalid Active Genres
		  static Stream<Arguments> testDataOfCase6() 
		  {		  
				ArrayList<String> occupation = new ArrayList<String>();
				occupation.add("Singer");
				occupation.add("Developer");
				occupation.add("Driver");
				ArrayList<String> GenresCase1 = new ArrayList<String>();
				GenresCase1.add("pop");
				ArrayList<String> GenresCase2 = new ArrayList<String>();
				GenresCase2.add("pop");	
				GenresCase2.add("rock");
				ArrayList<String> Awards = new ArrayList<String>();
				Awards.add("1999,69th film fare awards");

		        return Stream.of(
		        	////Data where there is only one active genre for the artist
		            Arguments.of("987ABCDE@#", "Case1",  "Melbourne|Victoria|Australia","25-06-1999", "Sample bio for the test data 1 which contains 10 words", occupation,GenresCase1, Awards),  
		            
		            ////Data where the artist was active in both 'pop' and 'rock' genres.
		            Arguments.of("987ABCDE@#", "Case1",  "Melbourne|Victoria|Australia","25-06-2023", "Sample bio for the test data 2 which contains 10 words", occupation,GenresCase2, Awards)  
		        );
		    }
	 
	
	@ParameterizedTest
	@MethodSource("testDataOfCase1")
	@DisplayName("Test Add Artist with valid input")
	void testAddArtist_TestCase1(String ArtistID,  String Name, String Address, String Birthdate,String Bio, ArrayList<String> Occupations, ArrayList<String> Genres, ArrayList<String> Awards) 
	{
		Artist TestData1 = new Artist(ArtistID, Name, Address, Birthdate, Bio, Occupations, Genres, Awards);
		boolean data = TestData1.Update();
	//	boolean data = TestData1.addArtist();
		Assert.assertTrue(data == true);
	}
	
	
	@ParameterizedTest
	@MethodSource("testDataOfCase2")
	@DisplayName("Test Add Artist with invalid Artist ID")
	void testAddArtist_TestCase2(String ArtistID,  String Name, String Address, String Birthdate,String Bio, ArrayList<String> Occupations, ArrayList<String> Genres, ArrayList<String> Awards) 
	{
		Artist TestData1 = new Artist(ArtistID, Name, Address, Birthdate, Bio, Occupations, Genres, Awards);
		boolean data = TestData1.addArtist();
		Assert.assertTrue(data == false);
	}
	
	@ParameterizedTest
	@MethodSource("testDataOfCase3")
	@DisplayName("Test Add Artist with invalid date format of birthdate")
	void testAddArtist_TestCase3(String ArtistID,  String Name, String Address, String Birthdate,String Bio, ArrayList<String> Occupations, ArrayList<String> Genres, ArrayList<String> Awards) 
	{
		Artist TestData1 = new Artist(ArtistID, Name, Address, Birthdate, Bio, Occupations, Genres, Awards);
		boolean data = TestData1.addArtist();
		Assert.assertTrue(data == false);
	}
	
	@ParameterizedTest
	@MethodSource("testDataOfCase4")
	@DisplayName("Test Add Artist with invalid bio")
	void testAddArtist_TestCase4(String ArtistID,  String Name, String Address, String Birthdate,String Bio, ArrayList<String> Occupations, ArrayList<String> Genres, ArrayList<String> Awards) 
	{
		Artist TestData1 = new Artist(ArtistID, Name, Address, Birthdate, Bio, Occupations, Genres, Awards);
		boolean data = TestData1.addArtist();
		Assert.assertTrue(data == false);
	}

	@ParameterizedTest
	@MethodSource("testDataOfCase5")
	@DisplayName("Test Add Artist with invalid awards")
	void testAddArtist_TestCase5(String ArtistID,  String Name, String Address, String Birthdate,String Bio, ArrayList<String> Occupations, ArrayList<String> Genres, ArrayList<String> Awards) 
	{
		Artist TestData1 = new Artist(ArtistID, Name, Address, Birthdate, Bio, Occupations, Genres, Awards);
		boolean data = TestData1.addArtist();
		Assert.assertTrue(data == false);
	}
	
	@ParameterizedTest
	@MethodSource("testDataOfCase6")
	@DisplayName("Test Add Artist with invalid sctive genres")
	void testAddArtist_TestCase6(String ArtistID,  String Name, String Address, String Birthdate,String Bio, ArrayList<String> Occupations, ArrayList<String> Genres, ArrayList<String> Awards) 
	{
		Artist TestData1 = new Artist(ArtistID, Name, Address, Birthdate, Bio, Occupations, Genres, Awards);
		boolean data = TestData1.addArtist();
		Assert.assertTrue(data == false);
	}

}

//JUnit 5 user guide
//@Display for uni test check with it
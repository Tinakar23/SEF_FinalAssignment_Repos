import java.text.*;
import java.util.regex.*;
import java.io.*;
import java.util.*;

/// Artist class.
public class Artist {

	private String ID;
	private String Name;
	private String Address;
	private String Birthdate;
	private String Bio;
	private ArrayList<String> Occupations;
	private ArrayList<String> Genres;
	private ArrayList<String> Awards;

	/// Constructor of the Artist Class.
	public Artist(String id, String name, String address, String birthdate, String bio, ArrayList<String> occupations,
			ArrayList<String> genres, ArrayList<String> awards) 
	{
		ID = id;
		Name = name;
		Address = address;
		Birthdate = birthdate;
		Bio = bio;
		Occupations = occupations;
		Genres = genres;
		Awards = awards;
	}

	/// Function to check all the precondition to add the artist to file.
	private boolean preConditionsToAddArtist() {
		boolean isValidArtistId = false;
		boolean isValidDate = false;
		boolean isValidAddress = false;
		boolean isValidBio = false;
		boolean isValidOccupation = false;
		boolean isValidAwards = false;
		boolean isValidGeners = false;
		isValidArtistId = validArtistId();
		isValidDate = validBirthDate();
		isValidAddress = validAddress(isValidAddress);
		isValidBio = validateBio(isValidBio);
		isValidOccupation = validateOccupation(isValidOccupation);
		isValidAwards = validateAwards(isValidAwards);
		isValidGeners = validateGenres(isValidGeners);

		return isValidArtistId && isValidDate && isValidAddress && isValidBio && isValidOccupation && isValidAwards
				&& isValidGeners;
	}

	/// Function to validate the Artist genres and return true if valid and false if not valid genres.
	private boolean validateGenres(boolean isValidGeners) {
		if (this.Genres.size() >= 2 && this.Genres.size() <= 5
				&& !(this.Genres.contains("rock") && this.Genres.contains("pop"))) {
			isValidGeners = true;
		}
		return isValidGeners;
	}

	/// Function to validate the Artist awards and return true if valid and false if not valid awards.
	private boolean validateAwards(boolean isValidAwards) {
		if (this.Awards.size() <= 3) {
			int invalidDataCount = 0;
			for (String award : this.Awards) {
				String[] awardParts = award.split(",");
				if (awardParts.length == 2) {
					String year = awardParts[0];
					String title = awardParts[1];
					String[] titleWords = title.split("\\s+");
					if ((year.length() != 4 || !(titleWords.length >= 4 && titleWords.length <= 10))) {
						invalidDataCount++;
					}

				}
				isValidAwards = invalidDataCount == 0 && awardParts.length == 2;
			}
		}
		return isValidAwards;
	}

	/// Function to validate the Artist occupation and return true if valid and false if not valid occcupation.
	private boolean validateOccupation(boolean isValidOccupation) {
		if (!this.Occupations.isEmpty() && this.Occupations.size() <= 5) {
			isValidOccupation = true;
		}
		return isValidOccupation;
	}

	/// Function to validate the Artist bio and return true if valid and false if not valid bio.
	private boolean validateBio(boolean isValidBio) {
		String[] bioParts = this.Bio.split("\\s+");
		if (bioParts.length >= 10 && bioParts.length <= 30) {
			isValidBio = true;
		}
		return isValidBio;
	}

	/// Function to validate the Artist address and return true if valid and false if not valid address.
	private boolean validAddress(boolean isValidAddress) {
		String[] addressParts = this.Address.split("\\|");
		if (addressParts.length == 3) {
			isValidAddress = true;
		}
		return isValidAddress;
	}

	/// Function to validate the Artist birthdate and return true if valid and false if not valid birthdate.
	private boolean validBirthDate() {
		boolean isValidDate;
		String datePattern = "^(0[1-9]|[1-2][0-9]|3[0-1])-(0[1-9]|1[0-2])-\\d{4}$$"; //// REGEX to check the date format. 
		Pattern dateRegex = Pattern.compile(datePattern);
		Matcher dateMatcher = dateRegex.matcher(this.Birthdate);
		isValidDate = dateMatcher.matches();
		return isValidDate;
	}

	/// Function to validate the Artist ID and return true if valid and false if not valid id.
	private boolean validArtistId() {
		boolean isValidArtistId;
		String artistIdPatter = "^^[5-9]{3}[A-Z]{5}[!@#$%^&*()_+=<>?/{}\\[\\]|]{2}$"; ///// REGEX to chceck the ID.
		Pattern artistIdRegex = Pattern.compile(artistIdPatter);
		Matcher artistIdMatcher = artistIdRegex.matcher(this.ID);
		isValidArtistId = artistIdMatcher.matches();
		return isValidArtistId;
	}

	//// Function to add the artist details to file after validating the inputs.
	public boolean addArtist() {
		boolean isAddArtistSuccess = false;
		boolean validDetails = this.preConditionsToAddArtist();

		if (validDetails) {
			File fileObject = new File("fileName.txt"); /// to create a file instance.
			try {
				if (!fileObject.exists()) {
					fileObject.createNewFile(); /// to create a new file if not exits
				}

				/// to write the content in to the file.
				FileWriter fileWrite = new FileWriter("fileName.txt", true);
				fileWrite.write("ID: " + this.ID + "\n");
				fileWrite.write("Name: " + this.Name + "\n");
				fileWrite.write("Birthdate: " + this.Birthdate + "\n");
				fileWrite.write("Address: " + this.Address + "\n");
				fileWrite.write("Bio: " + this.Bio + "\n");
				this.writeArrayList(this.Occupations, fileWrite, "Occupation: ");
				this.writeArrayList(this.Genres, fileWrite, "Genres: ");
				this.writeArrayList(this.Awards, fileWrite, "Awards: ");
				fileWrite.write("\n");
				fileWrite.close();	/// to close the file
				isAddArtistSuccess = true;
				
			} catch (IOException e) {
				System.out.println("Error occured");
			}
		}
		return isAddArtistSuccess;   //// return true if the artist was added to the file  and false if not added

	}

	/// Function to write the array list in to the file by the custom format.
	private void writeArrayList(ArrayList<String> list, FileWriter writer, String title) {
		String element = title;
		for (int i = 0; i < list.size(); i++) {
			String seperator = title == "Awards: " ? "|" : ",";
			element += list.get(i) + seperator;
			
		}

		String newElement =  element.substring(0, element.length() - 1);
		try {
			writer.write(newElement + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	/// Function to update the artist details in to the file.
	public boolean UpdateArtist() {

		boolean isUpdated = false;
		try 
		{
			Scanner scanner = new Scanner(new File("fileName.txt")); /// to read the existing file
			int Birthdate = 0;
			boolean isMatchedData = false;

			StringBuilder newContent = new StringBuilder();
			String newAwardString = "Awards: ";
			List<String> awardTitle = new ArrayList<String>();
			while (scanner.hasNextLine())   //// to read each line in loop
			{
				String line = scanner.nextLine();
				
				//// block to update the ID
				if(line.startsWith("ID") ) //// to check whether the lines starts with ID
				{
					String Id = line.substring(line.indexOf(": ") + 2);
					isMatchedData = (Id.equals(this.ID)) ? true : false;
					newContent.append(line).append("\n");					
				}
				
				//// Block to update the name
				if(line.startsWith("Name"))///to check whether the lines starts with Name
				{
					newContent.append(line).append("\n");
				}
				
				/// block to update the bio
				if(line.startsWith("Bio"))///to check whether the lines starts with Bio
				{
					if( this.Bio != ""  && isMatchedData)
					{
						if(this.validateBio(false))
						{
						String newBioLine = "Bio: " + this.Bio;
						newContent.append(newBioLine).append("\n");
						isUpdated = true;
						}
						else
						{
							isUpdated = false;
							break;
						}
					}
					else
					{
						newContent.append(line).append("\n");
					}
				}
				
				//// block to update the birthdate.
				if(line.startsWith("Birthdate"))/// to check whether the lines starts with Birthdate
				{
					String birthDate = line.substring(line.indexOf(": ") + 2);
					Birthdate = this.GetYear(birthDate);
					if(this.Birthdate != "" && isMatchedData)
					{
						if(this.validBirthDate())
						{							
						String newBirthdate = "Birthdate: " + this.Birthdate;
						newContent.append(newBirthdate).append("\n");
						isUpdated = true;
						}
						else
						{
							isUpdated = false;
							break;
						}
					}
					else
					{
						newContent.append(line).append("\n");
					}
				}
				
				/// block to update the address.
				if(line.startsWith("Address"))/// to check whether the lines starts with Address
				{
					if(this.Address != "" && isMatchedData)
					{
						if(this.validAddress(false))
						{							
						String newAddress = "Address: " + this.Address;
						newContent.append(newAddress).append("\n");
						isUpdated = true;
						}
						else
						{
							isUpdated = false;
							break;
						}
					}
					else
					{
						newContent.append(line).append("\n");
					}
				}
				
				/// block to update the genres.
				if(line.startsWith("Genres")) ///to check whether the lines starts with Genres
				{
					if(!this.Genres.isEmpty() && isMatchedData)
					{
						if(this.validateGenres(false))
						{
							String newGenres = "Genres: "+ this.Genres +"\n";
							newContent.append(newGenres).append("\n");
							isUpdated = true;
						}
						else
						{
							isUpdated = false;
							break;
						}
					}
					else
					{
						newContent.append(line).append("\n");
					}
				}
				
				/// block to update the awards
				if ( line.startsWith("Award")) ////  to check whether the lines starts with Award
				{
					if(this.Awards != null && !this.Awards.isEmpty() && isMatchedData)
					{
						if(!this.validateAwards(false))
						{
							isUpdated = false;
							break;
						}
						else
						{
							for (String award : this.Awards) 
							{
								String[] awardParts = award.split(",");
								awardTitle.add(awardParts[1]);
							}

							String[] awardTitleArray = awardTitle.toArray(new String[awardTitle.size()]);
							String[] parts = line.substring(line.indexOf(": ") + 2).split("\\|");
							for (int i = 0; i < parts.length; i++) 
							{
								String part = parts[i];
								String[] subParts = part.split(",");

								if (subParts.length == 2 && Integer.parseInt(subParts[0]) > 2000) 
								{
									subParts[1] = awardTitleArray[i];
									isUpdated = true;
								}
								newAwardString += String.join(",", subParts) + "|";
							}
							newAwardString = newAwardString.substring(0, newAwardString.length() - 1);
							newContent.append(String.join("|", newAwardString)).append("\n");
						}
					}
					else	
					{
						newContent.append(line).append("\n");
					}
				}
				
				/// block to update the occupations
				if (line.startsWith("Occupation")) ///to check whether the lines starts with Occupation
 				{
					if(!this.Occupations.isEmpty() && isMatchedData)
					{
						if(!this.validateOccupation(false))
						{
							isUpdated = false;
							break;
						}
						else
						{
							
							if (Birthdate > 2000) 
							{
								newContent.append("Occupation: "+String.join(",", this.Occupations)+"\n");
								isUpdated = true;
							}							
						}
					}
					else 
					{
						newContent.append(line).append("\n");
					}
				}
				if(line.isEmpty())
				{
					newContent.append("\n");
				}
			}

			/// to check is all the inputs were valid and to update it in the file.
			if(isUpdated)
			{			
				FileWriter fileWriter = new FileWriter("fileName.txt", false); /// to clear the file and update the new content.
				BufferedWriter writer = new BufferedWriter(fileWriter);
				writer.write(newContent.toString());
				writer.close();
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return isUpdated;
	}
	
	
	/// function to get the year from tyhe birthdate of the artist
	private int GetYear(String birthDate)
	{
		Date converted_birthDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		try 
		{
			converted_birthDate = dateFormat.parse(birthDate);
		} 
		catch (ParseException e) 
		{
			// 	Handle parsing error
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(converted_birthDate);
		return calendar.get(Calendar.YEAR);
	}
}




import java.text.*;
import java.util.regex.*;
import java.io.*;
import java.util.*;

public class Artist {

	private String ID;
	private String Name;
	private String Address;
	private String Birthdate;
	private String Bio;
	private ArrayList<String> Occupations;
	private ArrayList<String> Genres;
	private ArrayList<String> Awards;

	public Artist(String id, String name, String address, String birthdate, String bio, ArrayList<String> occupations,
			ArrayList<String> genres, ArrayList<String> awards) {
		ID = id;
		Name = name;
		Address = address;
		Birthdate = birthdate;
		Bio = bio;
		Occupations = occupations;
		Genres = genres;
		Awards = awards;
	}

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

	private boolean validateGenres(boolean isValidGeners) {
		if (this.Genres.size() >= 2 && this.Genres.size() <= 5
				&& !(this.Genres.contains("rock") && this.Genres.contains("pop"))) {
			isValidGeners = true;
		}
		return isValidGeners;
	}

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

	private boolean validateOccupation(boolean isValidOccupation) {
		if (!this.Occupations.isEmpty() && this.Occupations.size() <= 5) {
			isValidOccupation = true;
		}
		return isValidOccupation;
	}

	private boolean validateBio(boolean isValidBio) {
		String[] bioParts = this.Bio.split("\\s+");
		if (bioParts.length >= 10 && bioParts.length <= 30) {
			isValidBio = true;
		}
		return isValidBio;
	}

	private boolean validAddress(boolean isValidAddress) {
		String[] addressParts = this.Address.split("\\|");
		if (addressParts.length == 3) {
			isValidAddress = true;
		}
		return isValidAddress;
	}

	private boolean validBirthDate() {
		boolean isValidDate;
		String datePattern = "^(0[1-9]|[1-2][0-9]|3[0-1])-(0[1-9]|1[0-2])-\\d{4}$$";
		Pattern dateRegex = Pattern.compile(datePattern);
		Matcher dateMatcher = dateRegex.matcher(this.Birthdate);
		isValidDate = dateMatcher.matches();
		return isValidDate;
	}

	private boolean validArtistId() {
		boolean isValidArtistId;
		String artistIdPatter = "^^[5-9]{3}[A-Z]{5}[!@#$%^&*()_+=<>?/{}\\[\\]|]{2}$";
		Pattern artistIdRegex = Pattern.compile(artistIdPatter);
		Matcher artistIdMatcher = artistIdRegex.matcher(this.ID);
		isValidArtistId = artistIdMatcher.matches();
		return isValidArtistId;
	}

	public boolean addArtist() {
		boolean isAddArtistSuccess = false;
		boolean validDetails = this.preConditionsToAddArtist();

		if (validDetails) {
			File fileObject = new File("fileName.txt");
			try {
				if (!fileObject.exists()) {
					fileObject.createNewFile();
				}

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
				fileWrite.close();
				isAddArtistSuccess = true;
			} catch (IOException e) {
				System.out.println("Error occured");
			}
		}
		return isAddArtistSuccess;

	}

	private void writeArrayList(ArrayList<String> list, FileWriter writer, String title) {
		String element = title;
		for (int i = 0; i < list.size(); i++) {
			String seperator = title == "Awards: " ? "|" : ",";
			element += list.get(i) + seperator;
		}
		try {
			writer.write(element + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean updateArtist()
	{
		boolean validDetails = this.preConditionsToAddArtist();
		ArrayList<String> dataFromFile = new ArrayList<String>();
		try(BufferedReader bufferReader = new BufferedReader(new FileReader("fileName.txt"));
		FileWriter bufferWriter = new FileWriter("fileName.txt.tmp"))
		{
			String line;
			bufferWriter.write("ID: " + this.ID +"\n");
			bufferWriter.write("Name: " + this.Name+"\n");
			bufferWriter.write("Birthdate: " + this.Birthdate+"\n");
			bufferWriter.write("Address: " + this.Address+"\n");
			bufferWriter.write("Bio: " + this.Bio+"\n");
			while(( line = bufferReader.readLine())!= null)
			{	
				if(line.startsWith("Occupation: "))
				{
					Date birthdate = new SimpleDateFormat("dd-mm-yyyy").parse(this.Birthdate);
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(birthdate);
					
					int year = calendar.get(Calendar.YEAR);
					if((year >= 2000))
					{
						this.writeArrayList(this.Occupations, bufferWriter, "Occupation: ");
					}					
				}
				else if(line.startsWith("Awards: "))
				{
					for(String award : this.Awards)
					{
						String[] newAwardParts = award.split(",");
						String[]oldAwardParts = award.split(","):
						if(newAwardParts.length == 2)
						{
							int year = Integer.parseInt(newAwardParts[0]);
							if ((year >= 2000))
							{
								newAwardParts[0
							}
						}
					}
				}
				else
				{
					bufferWriter.write(line);
				}
			}
		}
		
		catch(IOException e)
		{
			e.printStackTrace();
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
		
		if(dataFromFile.contains(this.ID) && validDetails)
		{
			
		}
			return true;	
	}

	public boolean Update() {
		Date converted_birthDate = new Date();
		try {
			FileWriter fileWriter = new FileWriter("fileName.txt", true);
			BufferedWriter writer = new BufferedWriter(fileWriter);
			Scanner scanner = new Scanner(new File("fileName.txt"));
			// scanner.nextLine(); // Skip the first line

			StringBuilder newContent = new StringBuilder();
			String newContentString = "Occuption: ";
			List<String> awardTitle = new ArrayList<String>();
			boolean isUpdated = false;
			while (scanner.hasNextLine()) 
			{
				String line = scanner.nextLine();
				if (this.Awards != null && !this.Awards.isEmpty()&& line.startsWith("Award")) 
				{
					if(!this.validateAwards(false))
					{
						isUpdated = false;
						break;
					}
					
					for (String award : this.Awards) 
					{
						String[] awardParts = award.split(",");
						awardTitle.add(awardParts[1]);
					}

					String[] awardTitleArray = awardTitle.toArray(new String[awardTitle.size()]);
					String[] parts = line.substring(line.indexOf(": ") + 2).split("\\|");
					for (int i = 0; i < parts.length; i++) {
						String part = parts[i];
						String[] subParts = part.split(",");

						if (subParts.length == 2 && Integer.parseInt(subParts[0]) > 2000) 
						{
							subParts[1] = awardTitleArray[i];
						}
						newContentString += String.join(",", subParts) + "|";
					}
					newContentString = newContentString.substring(0, newContentString.length() - 1);
					newContent.append(String.join("|", newContentString)).append("\n");
					isUpdated = true;
				}
				
				if (!this.Occupations.isEmpty() && line.startsWith("Occupation")) 
				{
					if(!this.validateOccupation(false))
					{
						isUpdated = false;
						break;
					}
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					try 
					{
						converted_birthDate = dateFormat.parse(this.Birthdate);
					} 
					catch (ParseException e) 
					{
						// Handle parsing error
					}
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(converted_birthDate);
					if (calendar.get(Calendar.YEAR) > 2000) 
					{
						newContent.append("new Occupation\n");
					}
					else 
					{
						newContent.append(line).append("\n");
					}
				} 
				
				else {
					newContent.append(line).append("\n");
				}
			}

			writer.write(newContent.toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
}

//https://stackoverflow.com/questions/12970484/java-for-loop-with-an-arraylist

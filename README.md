# Weather

What the app does:
The app takes in a user's location and outputs live weather conditions at the specified location.
How to use:
1.	Click on “Enter Latitude”, delete pre, existing text, enter a valid latitude coordinate. Repeat for “Enter Longitude”,
2.	Close your device's keyboard popup.
3.	Click “Confirm Coordinates”
4.	Click “Get Weather”

Process:
-	Spent time trying to find various ways of making API calls. Chose Volley for simplicity.
-	Learned how to use card view and how to cleanly display the required information.
-	Studied Openweather API call to generate a valid URL 
-	Studied API response to properly parse the JSON response and display it cleanly. 
o	Got area and country, displayed together separated by a comma.
o	Got temperature values as doubles, converted from Kelvin to Celsius. Displayed as String in listview withing cardview

To implement:
-	Generate error toasts to stop the app from crashing. 
-	Save the last state of the app and recreate it.
-	Separate classes to follow MVC architecture by adding some classes to utilities and focusing on the view in the main activity. 
-	Add preset cities data.
-	Self-initiated: add a second activity to show additional data (atmospheric pressure, wind conditions, rain, etc) of either the current location or one of the preset locations.

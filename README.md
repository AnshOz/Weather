# Weather
What this app does:
The app takes in a user location and outputs live weather conditions at the specified location.
How to use:
1.	Click on “Enter Latitude”, delete pre, existing text, enter a valid latitude coordinate. Repeat for “Enter Longitude”,
2.	Close your device keyboard popup.
3.	Click “Confirm Coordinates”
4.	Click “Get Weather”
Process:
-	Spent time trying to find various ways of making api calls. Chose Volley for simplicity.
-	Learnt how to use card view and how to cleanly display required information.
-	Studied Openweather api call to generate a valid url 
-	Studied api response to properly parse the JSON response and display it cleanly. 
o	Got area and country, displayed together separated by a comma.
o	Got temperature values as doubles, converted from Kelvin to Celsius. Displayed as String in listview withing cardview
To implement:
-	Generate error toasts to stop app from crashing. 
-	Know how to do third part but could not complete due to time constraints will implement after exams.
-	Separate classes to follow MVC architecture by adding some classes to utilities and focus on view in the main activity. 
-	Add bonus cities data.
-	Self-initiated: add a second activity to show additional data (atmospheric pressure, wind conditions, rain, etc) of either current location or one of the preset locations.

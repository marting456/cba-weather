package org.marting.weather;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import org.marting.weather.engine.Prediction;
import org.marting.weather.engine.WeatherEngine;

/**
 * @author Martin Gercsak - mgercsak@yahoo.com.au
 */
public class WeatherPredictor {

	private static final long JAN_1ST_2015 = 1420030800000L;
	private static final long MILLISECONDS_IN_A_YEAR = 31556952000L;
	private static final String WEATHER_STATIONS_FILE = "/GlobalAirportDatabase.txt";
	private static final String[] DEFAULT_DATA =
		{ "SYD", "BUD", "JFK", "SVO", "GIG", "GCJ", "LHR", "SIN", "LAX", "HND" };


	public static void main(String[] args) {
		if (args.length == 0) {
			args = DEFAULT_DATA;
		}
		WeatherPredictor weatherPredictor = new WeatherPredictor();
		WeatherEngine weatherEngine = new WeatherEngine();
		DecimalFormat formatter = new DecimalFormat("#0.0");
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		Map<String, WeatherStation> weatherStations = weatherPredictor.createWeatherStations();
		List<String> locations = Arrays.asList(args);
		for(String location : locations) {
			WeatherStation weatherStation = weatherStations.get(location);
			if (weatherStation != null) {
				Date date = weatherPredictor.generateRandomDate();
				Prediction prediction = weatherEngine.getPrediction(weatherStation, date);
				System.out.println(weatherStation.getCityCode() + "|" +
						weatherStation.getLatitude() + ", " +
						weatherStation.getLongitude() + ", " +
						weatherStation.getElevation() + "|" +
						dateFormatter.format(date) + "|" +
						prediction.getConditions() + "|" +
						formatter.format(prediction.getTemperature()) + "|" +
						formatter.format(prediction.getPressure()) + "|" +
						prediction.getHumidity() + "|");
			} else {
				System.err.println("Location '" + location + "' does not exist.");
			}

		}

	}

	private Date generateRandomDate() {
		long random = ThreadLocalRandom.current().nextLong(0, MILLISECONDS_IN_A_YEAR * 2);
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(JAN_1ST_2015 + random);
		return cal.getTime();

	}

	public Map<String, WeatherStation> createWeatherStations() {

		Map<String, WeatherStation> weatherStations = new HashMap<String, WeatherStation>();
		try {
//			ClassLoader classLoader = getClass().getClassLoader();
//			File file = new File(classLoader.getResource(WEATHER_STATIONS_FILE).getFile());
			InputStream is = getClass().getResourceAsStream(WEATHER_STATIONS_FILE);
			Scanner scanner = new Scanner(is);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String array[] = line.split(":");
				String cityCode = array[1];
				String latitudeStr = array[5] + "." + array[6];
				if (array[8].equals("S")) {
					latitudeStr = "-" + latitudeStr;
				}
				String longitudeStr = array[9] + "." + array[10];
				String elevationStr = array[13];
				elevationStr = elevationStr.replaceAll("^0+-", "-");
				double longitude = Double.parseDouble(longitudeStr);
				double latitude = Double.parseDouble(latitudeStr);
				int elevation = Integer.parseInt(elevationStr);
				WeatherStation weatherStation = new WeatherStation(cityCode, latitude, longitude, elevation);
				weatherStations.put(weatherStation.getCityCode(), weatherStation);
			}
			scanner.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return weatherStations;
	}
}

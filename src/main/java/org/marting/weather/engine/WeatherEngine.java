package org.marting.weather.engine;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.marting.weather.WeatherStation;
import org.marting.weather.engine.Prediction.Conditions;

/**
 * @author Martin Gercsak - mgercsak@yahoo.com.au
 */
public class WeatherEngine {


	private static final double PRESSURE_BASE = 1010.0;
	private static final double LATITUDE_WEIGHT = 1.5;
	// final temperature range
	private static final double C = -50.0;
	private static final double D = 50.0;
	// raw temperature range
	private static final double A = -1  - (90 * LATITUDE_WEIGHT); // baseline - max latitude * weight
	private static final double B = 21  + (0);

	private static final int[] NORTHERN_HEMISPHERE_TEMPERATURE_BASELINE =
		{-1, 1, 6, 11, 16, 19, 21, 21, 17, 12, 5, 1};
	private static final int[] SOUTHERN_HEMISPHERE_TEMPERATURE_BASELINE =
		{21, 21, 17, 12, 5, 1, -1, 1, 6, 11, 16, 19};
	private static final int[] HOUR_OF_DAY_TEMPERATURE_BASELINE = {
		0,
		0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11,
		10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0
	};

	public Prediction getPrediction(WeatherStation weatherStation, Date date) {

		double temperature = predictTemperature(weatherStation, date);
		Conditions conditions = predictConditions(weatherStation, temperature);
		double pressure = predictPressure(weatherStation);
		int humidity = predictHumidity(weatherStation);
		Prediction prediction = new Prediction(conditions, temperature, pressure, humidity, weatherStation, date);
		return prediction;
	}

	private double predictTemperature(WeatherStation weatherStation, Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		double temperature = 0;
		if (weatherStation.getLatitude() > 0) {
			temperature = NORTHERN_HEMISPHERE_TEMPERATURE_BASELINE[calendar.get(Calendar.MONTH)];
		} else {
			temperature = SOUTHERN_HEMISPHERE_TEMPERATURE_BASELINE[calendar.get(Calendar.MONTH)];
		}
		temperature = (temperature) - (Math.abs(weatherStation.getLatitude()) * LATITUDE_WEIGHT); // compensate for latitude
		temperature = normalizeToRange(temperature);
		temperature = temperature + HOUR_OF_DAY_TEMPERATURE_BASELINE[calendar.get(Calendar.HOUR_OF_DAY)];
		double random = ThreadLocalRandom.current().nextDouble(-5, 5);
		temperature = temperature + random;
		return temperature;
	}

	private Conditions predictConditions(WeatherStation weatherStation, double temperature) {
		Conditions conditions = Conditions.Sunny;
		double probability = (90.0 - Math.abs(weatherStation.getLatitude())) * 0.8;
		Random r = new Random();
		int randomInt = r.nextInt(100) + 1;
		if (randomInt > probability) {
			if (temperature <0 ) {
				conditions = Conditions.Snow;
			} else {
				conditions = Conditions.Rain;
			}
		}
		return conditions;
	}

	private double predictPressure(WeatherStation weatherStation) {
		double pressure = PRESSURE_BASE + weatherStation.getElevation() / 100 + ThreadLocalRandom.current().nextDouble(-5, 5);
		return pressure;
	}

	private int predictHumidity(WeatherStation weatherStation) {
		Double humidity = (90.0 - Math.abs(weatherStation.getLatitude())) + ThreadLocalRandom.current().nextDouble(-5, 5);
		return humidity.intValue();
	}

	private double normalizeToRange(double x) {
		// Y = (X-A)/(B-A) * (D-C) + C
		double temperature = (x - A) / (B - A) * (D - C) + C;
		return temperature;
	}
}

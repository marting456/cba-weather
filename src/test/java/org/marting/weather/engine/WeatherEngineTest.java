package org.marting.weather.engine;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;
import org.marting.weather.WeatherPredictor;
import org.marting.weather.WeatherStation;
import org.marting.weather.engine.Prediction;
import org.marting.weather.engine.WeatherEngine;
import org.marting.weather.engine.Prediction.Conditions;

/**
 * @author Martin Gercsak - mgercsak@yahoo.com.au
 */
public class WeatherEngineTest {

	private static Map<String, WeatherStation> weatherStations;

	@BeforeClass
	public static void setup() {
		WeatherPredictor weatherPredictor = new WeatherPredictor();
		weatherStations = weatherPredictor.createWeatherStations();
	}

	@Test
	public void shouldGetPredictionForSYDWinterMidnight() throws ParseException {
		WeatherStation syd = weatherStations.get("SYD");
		WeatherEngine weatherEngine = new WeatherEngine();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		String dateInString = "7-Jul-2013";
		Date date = formatter.parse(dateInString);
		for (int i = 0; i < 1000; i++) {
			Prediction sydPrediction = weatherEngine.getPrediction(syd, date);
			assertThat(sydPrediction.getTemperature(), lessThan(15.0));
			assertThat(sydPrediction.getTemperature(), greaterThan(-5.0));
			assertThat(sydPrediction.getWeatherStation().getCityCode(), is("SYD"));
		}
	}

	@Test
	public void shouldGetPredictionForSYDSummerMidday() throws ParseException {
		WeatherStation syd = weatherStations.get("SYD");
		WeatherEngine weatherEngine = new WeatherEngine();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy:HH");
		String dateInString = "7-Jul-2013:12";
		Date date = formatter.parse(dateInString);
		for (int i = 0; i < 1000; i++) {
			Prediction sydPrediction = weatherEngine.getPrediction(syd, date);
			assertThat(sydPrediction.getTemperature(), lessThan(45.0));
			assertThat(sydPrediction.getTemperature(), greaterThan(5.0));
			assertThat(sydPrediction.getWeatherStation().getCityCode(), is("SYD"));
		}
	}

	@Test
	public void shouldGetPredictionForBUDWinterMidnight() throws ParseException {
		WeatherStation bud = weatherStations.get("BUD");
		WeatherEngine weatherEngine = new WeatherEngine();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		String dateInString = "1-Jan-2013";
		Date date = formatter.parse(dateInString);
		for (int i = 0; i < 1000; i++) {
			Prediction budPrediction = weatherEngine.getPrediction(bud, date);
			assertThat(budPrediction.getTemperature(), lessThan(-0.0));
			assertThat(budPrediction.getTemperature(), greaterThan(-20.0));
			assertThat(budPrediction.getWeatherStation().getCityCode(), is("BUD"));
			assertThat(budPrediction.getConditions(), not(Conditions.Rain));
		}
	}

	@Test
	public void shouldGetPredictionForBUDWinterMidday() throws ParseException {
		WeatherStation bud = weatherStations.get("BUD");
		WeatherEngine weatherEngine = new WeatherEngine();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy:HH");
		String dateInString = "1-Jan-2013:12";
		Date date = formatter.parse(dateInString);
		for (int i = 0; i < 1000; i++) {
			Prediction budPrediction = weatherEngine.getPrediction(bud, date);
			assertThat(budPrediction.getTemperature(), lessThan(10.0));
			assertThat(budPrediction.getTemperature(), greaterThan(-10.0));
			assertThat(budPrediction.getWeatherStation().getCityCode(), is("BUD"));
		}
	}

	@Test
	public void shouldGetPredictionForBUDSummerMidday() throws ParseException {
		WeatherStation bud = weatherStations.get("BUD");
		WeatherEngine weatherEngine = new WeatherEngine();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy:HH");
		String dateInString = "15-Jul-2013:12";
		Date date = formatter.parse(dateInString);
		for (int i = 0; i < 1000; i++) {
			Prediction budPrediction = weatherEngine.getPrediction(bud, date);
			assertThat(budPrediction.getTemperature(), lessThan(40.0));
			assertThat(budPrediction.getTemperature(), greaterThan(10.0));
			assertThat(budPrediction.getWeatherStation().getCityCode(), is("BUD"));
			assertThat(budPrediction.getConditions(), not(Conditions.Snow));
			assertThat(budPrediction.getHumidity(), lessThan(70));
		}
	}
}

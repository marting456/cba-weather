package org.marting.weather.engine;

import java.util.Date;

import org.marting.weather.WeatherStation;

/**
 * @author Martin Gercsak - mgercsak@yahoo.com.au
 */
public class Prediction {

	public enum Conditions { Rain, Snow, Sunny };

	private Conditions conditions;
	private double temperature;
	private double pressure;
	private int humidity;
	private WeatherStation weatherStation;
	private Date date;

	public Prediction(Conditions conditions, double temperature, double pressure, int humidity,
			WeatherStation weatherStation, Date date) {
		super();
		this.conditions = conditions;
		this.temperature = temperature;
		this.pressure = pressure;
		this.humidity = humidity;
		this.weatherStation = weatherStation;
		this.date = date;
	}
	public WeatherStation getWeatherStation() {
		return weatherStation;
	}
	public Date getDate() {
		return date;
	}
	public Conditions getConditions() {
		return conditions;
	}
	public double getTemperature() {
		return temperature;
	}
	public double getPressure() {
		return pressure;
	}
	public int getHumidity() {
		return humidity;
	}
}

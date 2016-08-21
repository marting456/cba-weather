package org.marting.weather;

/**
 * @author Martin Gercsak - mgercsak@yahoo.com.au
 */
public class WeatherStation {

	private String cityCode;
	private double longitude;
	private double latitude;
	private int elevation;
	/**
	 * @param cityCode
	 * @param longitude
	 * @param latitude
	 * @param elevation
	 */
	public WeatherStation(String cityCode, double latitude, double longitude, int elevation) {
		super();
		this.cityCode = cityCode;
		this.longitude = longitude;
		this.latitude = latitude;
		this.elevation = elevation;
	}

	public String getCityCode() {
		return cityCode;
	}
	public double getLongitude() {
		return longitude;
	}
	public double getLatitude() {
		return latitude;
	}

	public int getElevation() {
		return elevation;
	}
}

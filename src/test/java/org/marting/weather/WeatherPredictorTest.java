package org.marting.weather;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Map;

import org.junit.Test;


public class WeatherPredictorTest {

    @Test
    public void shouldReadWeatherStationsFromFile() {
        WeatherPredictor weatherPredictor = new WeatherPredictor();
        Map<String, WeatherStation> weatherStations = weatherPredictor.createWeatherStations();
        assertThat(weatherStations.get("SYD"), notNullValue());
        WeatherStation syd = weatherStations.get("SYD");
        assertThat(syd.getLatitude(), is(-33.56));
        assertThat(syd.getElevation(), is (21));
        WeatherStation bud = weatherStations.get("BUD");
        assertThat(bud.getLatitude(), is(47.26));
        assertThat(bud.getElevation(), is (495));
    }
}

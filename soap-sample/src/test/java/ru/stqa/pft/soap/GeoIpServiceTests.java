package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {

    @Test
    public void testMyIp() {
        String countryNameByISO2 = new GeoIPService().getGeoIPServiceSoap12().getCountryNameByISO2("37.77.110.141");
        assertEquals(countryNameByISO2, "<GeoIP><Country>UNITED STATES</Country></GeoIP>");
    }

    @Test
    public void testAnotherIp() {
        String countryNameByISO2 = new GeoIPService().getGeoIPServiceSoap12().getCountryNameByISO2("xx.73.87.2xx");
        System.out.println(countryNameByISO2);
    }
}

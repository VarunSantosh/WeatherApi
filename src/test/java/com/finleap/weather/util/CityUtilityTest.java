package com.finleap.weather.util;

import com.finleap.weather.utility.CityUtility;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class CityUtilityTest {

    private CityUtility cityUtil;

    @Before
    public void setUp() {
        cityUtil = new CityUtility();
    }

    @After
    public void destroy() {
        cityUtil = null;
    }

    @Test
    public void testGetCityId() {
        Long cityId = cityUtil.getCityId("kosti");
        Assert.assertEquals("371760", String.valueOf(cityId));
    }
}

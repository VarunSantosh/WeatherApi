package com.finleap.weather.util;

import com.finleap.weather.utility.FormatUtility;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class FormatUtilityTest {

    @Test
    public void testGetNextDate() {
        String reqDate = "2019-12-31";
        String nextDate = FormatUtility.getNextDate(reqDate);
        Assert.assertEquals("2020-01-01", nextDate);
    }
}

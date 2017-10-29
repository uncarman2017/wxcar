package com.kxcar.wxcar.business.test;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by jiaweiyu on 2016/12/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/beans.xml"})
public abstract class BaseUnitTest extends TestCase
{

    @Before
    @Override
    public void  setUp() throws Exception{

    }

    @After
    @Override
    public void  tearDown() throws Exception{

    }
}

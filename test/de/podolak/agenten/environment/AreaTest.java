/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.podolak.agenten.environment;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author podolak
 */
public class AreaTest {

    public AreaTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getField method, of class Area.
     */
    @Test
    public void testGetField_int_int() {
        System.out.println("getField");
        int x = 0;
        int y = 0;
        Area area = new Area();
        Field expResult = null;
        Field result = null;

        // <editor-fold defaultstate="collapsed" desc=" Test 1: x too small ">
        // vorhanden: 2D-Array 0..3,0..3; xOffset = 0, yOffset = 0
        // erfragt: (-1,0): x by 1 too small
        area = new Area(null, 4, 4);

        System.out.println(area);

        x = -1;
        y = 0;
        expResult = new Field(x + "," + y, Field.CLEAN, null);
        result = area.getField(x, y);
        
        System.out.println(expResult);
        System.out.println(result);
        System.out.println(area);
        System.out.println("--------------------------------------------");
        
        assertEquals(expResult, result);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc=" Test 2: x too big ">
        // vorhanden: 2D-Array 0..3,0..3; xOffset = 0, yOffset = 0
        // erfragt: (5,0): x by 2 too big
        area = new Area(null, 4, 4);

        System.out.println(area);

        x = 5;
        y = 0;
        expResult = new Field(x + "," + y, Field.CLEAN, null);
        result = area.getField(x, y);

        System.out.println(expResult);
        System.out.println(result);
        System.out.println(area);
        System.out.println("--------------------------------------------");

        assertEquals(expResult, result);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc=" Test 3: y too small ">
        // vorhanden: 2D-Array 0..3,0..3; xOffset = 0, yOffset = 0
        // erfragt: (0,-4): y by 4 too small
        area = new Area(null, 4, 4);

        System.out.println(area);

        x = 0;
        y = -4;
        expResult = new Field(x + "," + y, Field.CLEAN, null);
        result = area.getField(x, y);

        System.out.println(expResult);
        System.out.println(result);
        System.out.println(area);
        System.out.println("--------------------------------------------");

        assertEquals(expResult, result);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc=" Test 4: y too big ">
        // vorhanden: 2D-Array 0..3,0..3; xOffset = 0, yOffset = 0
        // erfragt: (0,4): y by 1 too big
        area = new Area(null, 4, 4);

        System.out.println(area);

        x = 0;
        y = 4;
        expResult = new Field(x + "," + y, Field.CLEAN, null);
        result = area.getField(x, y);

        System.out.println(expResult);
        System.out.println(result);
        System.out.println(area);
        System.out.println("--------------------------------------------");

        assertEquals(expResult, result);
        // </editor-fold>
    }

//    /**
//     * Test of getX method, of class Area.
//     */
//    @Test
//    public void testGetX() {
//        System.out.println("getX");
//        Field field = null;
//        Area instance = new Area();
//        int expResult = 0;
//        int result = instance.getX(field);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getY method, of class Area.
//     */
//    @Test
//    public void testGetY() {
//        System.out.println("getY");
//        Field field = null;
//        Area instance = new Area();
//        int expResult = 0;
//        int result = instance.getY(field);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getField method, of class Area.
//     */
//    @Test
//    public void testGetField_3args() {
//        System.out.println("getField");
//        Field field = null;
//        int xOffset = 0;
//        int yOffset = 0;
//        Area instance = new Area();
//        Field expResult = null;
//        Field result = instance.getField(field, xOffset, yOffset);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

}
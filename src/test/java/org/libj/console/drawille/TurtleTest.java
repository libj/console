/* Copyright (c) 2020 LibJ
 * Copyright (c) 2018 Rafael Grigorian
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * You should have received a copy of The MIT License (MIT) along with this
 * program. If not, see <http://opensource.org/licenses/MIT/>.
 */

package org.libj.console.drawille;

import static org.junit.Assert.*;

import org.junit.Test;

public class TurtleTest {
  @Test
  public void testGettersWork() {
    Turtle turtle = new Turtle(1, 1);
    assertTrue(turtle.getX() == 0);
    assertTrue(turtle.getY() == 0);
    turtle.move(1, 2);
    assertTrue(turtle.getX() == 1);
    assertTrue(turtle.getY() == 2);
  }

  @Test
  public void testAngleWorks() {
    Turtle turtle = new Turtle(1, 1);
    assertTrue(turtle.getAngle() == 0);
    turtle.left(45);
    assertTrue(turtle.getAngle() == -45);
    turtle.right(90);
    assertTrue(turtle.getAngle() == 45);
  }

  @Test
  public void testPenIsDrawing() {
    Turtle turtle = new Turtle(1, 1);
    assertNull(turtle.get(0, 0));
    assertNull(turtle.get(0, 1));
    assertNull(turtle.get(0, 2));
    assertNull(turtle.get(0, 3));
    turtle.move(0, 3);
    assertNull(turtle.get(0, 0));
    assertNull(turtle.get(0, 1));
    assertNull(turtle.get(0, 2));
    assertNull(turtle.get(0, 3));
    turtle.down();
    turtle.move(0, 0);
    assertNotNull(turtle.get(0, 0));
    assertNotNull(turtle.get(0, 1));
    assertNotNull(turtle.get(0, 2));
    assertNotNull(turtle.get(0, 3));
    turtle.up();
    turtle.move(1, 0);
    assertNull(turtle.get(1, 0));
    assertNotNull(turtle.get(0, 0));
  }
}
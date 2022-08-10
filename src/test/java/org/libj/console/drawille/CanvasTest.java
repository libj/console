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

import java.io.ByteArrayOutputStream;

import org.junit.Test;
import org.libj.console.Ansi;

public class CanvasTest {
  private static boolean throwsException(final int x, final int y) {
    final Canvas canvas = new Canvas(1, 1);
    try {
      canvas.set(x, y);
    }
    catch (final DrawilleException e) {
      return true;
    }

    return false;
  }

  @Test
  public void testExceptionIsThrown() {
    assertTrue(throwsException(-1, 0));
    assertTrue(throwsException(0, -1));
    assertTrue(throwsException(2, 0));
    assertTrue(throwsException(1, 4));
    assertFalse(throwsException(0, 0));
    assertFalse(throwsException(1, 2));
  }

  @Test
  public void testDimensionGetterWorks() {
    final Canvas c1 = new Canvas(10, 30);
    final Canvas c2 = new Canvas(0, 0);
    final Canvas c3 = new Canvas(2, 5);
    assertTrue(c1.getWidth() == 10 * 2);
    assertTrue(c1.getHeight() == 30 * 4);
    assertTrue(c2.getWidth() == 0 * 2);
    assertTrue(c2.getHeight() == 0 * 4);
    assertTrue(c3.getWidth() == 2 * 2);
    assertTrue(c3.getHeight() == 5 * 4);
  }

  @Test
  public void testGetterWorks() {
    final Canvas canvas = new Canvas(1, 1);
    assertNull(canvas.get(0, 0));
    assertNull(canvas.get(1, 1));
    canvas.set(0, 0);
    canvas.set(1, 1);
    assertNotNull(canvas.get(0, 0));
    assertNotNull(canvas.get(1, 1));
  }

  @Test
  public void testChangeWorks() {
    final Canvas canvas = new Canvas(1, 1);
    assertNull(canvas.get(0, 0));
    assertNull(canvas.get(1, 1));
    canvas.change(0, 0, Ansi.Color.DEFAULT);
    canvas.change(1, 1, Ansi.Color.DEFAULT);
    assertNotNull(canvas.get(0, 0));
    assertNotNull(canvas.get(1, 1));
    canvas.change(0, 0, null);
    canvas.change(1, 1, null);
    assertNull(canvas.get(0, 0));
    assertNull(canvas.get(1, 1));
  }

  @Test
  public void testSetWorks() {
    final Canvas canvas = new Canvas(1, 1);
    assertNull(canvas.get(0, 0));
    assertNull(canvas.get(1, 1));
    canvas.set(0, 0);
    canvas.set(1, 1);
    assertNotNull(canvas.get(0, 0));
    assertNotNull(canvas.get(1, 1));
  }

  @Test
  public void testUnsetWorks() {
    final Canvas canvas = new Canvas(1, 1);
    assertNull(canvas.get(0, 0));
    assertNull(canvas.get(1, 1));
    canvas.set(0, 0);
    canvas.set(1, 1);
    assertNotNull(canvas.get(0, 0));
    assertNotNull(canvas.get(1, 1));
    canvas.unset(0, 0);
    canvas.unset(1, 1);
    assertNull(canvas.get(0, 0));
    assertNull(canvas.get(1, 1));
  }

  @Test
  public void testRenderOverload() {
    try {
      final ByteArrayOutputStream output = new ByteArrayOutputStream();
      final Canvas canvas = new Canvas(1, 2);
      canvas.set(1, 1);
      canvas.set(1, 2);
      String result = canvas.render(output).toString().replace("\n", "");
//      assertTrue(result.equals("\u2830\u2800"));
    }
    catch (final Exception e) {
      assertTrue(false);
    }

    final Canvas canvas = new Canvas(75, 6);
    for (int x = 0, x$ = canvas.getWidth() * 8; x <= x$; x++) { // [N]
      canvas.set(x / 10, (int)Math.round(10 + Math.cos(x * Math.PI / 180) * 10), Ansi.Color.RED);
      canvas.set(x / 10, (int)Math.round(10 + Math.sin(x * Math.PI / 180) * 10), Ansi.Color.CYAN);
    }
    canvas.render();
  }
}
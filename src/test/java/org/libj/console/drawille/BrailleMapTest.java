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
import org.libj.console.Ansi;

public class BrailleMapTest {
  private static boolean throwsException(final int x, final int y) {
    final BrailleMap map = new BrailleMap();
    try {
      map.set(x, y);
    }
    catch (final DrawilleException e) {
      return true;
    }

    return false;
  }

  public void testExceptionIsThrown() {
    assertTrue(throwsException(-1, 0));
    assertTrue(throwsException(0, -1));
    assertTrue(throwsException(2, 0));
    assertTrue(throwsException(1, 4));
    assertFalse(throwsException(0, 0));
    assertFalse(throwsException(1, 2));
  }

  @Test
  public void testChangeWorks() {
    BrailleMap map = new BrailleMap();
    assertNull(map.get(0, 0));
    map.change(0, 0, Ansi.Color.DEFAULT);
    assertNotNull(map.get(0, 0));
    map.change(0, 0, null);
    assertNull(map.get(0, 0));
  }

  @Test
  public void testGetWorks() {
    BrailleMap map = new BrailleMap();
    assertNull(map.get(0, 0));
    map.change(0, 0, Ansi.Color.DEFAULT);
    assertNotNull(map.get(0, 0));
  }

  @Test
  public void testSetWorks() {
    BrailleMap map = new BrailleMap();
    assertNull(map.get(0, 0));
    map.set(0, 0);
    assertNotNull(map.get(0, 0));
    assertNull(map.get(1, 3));
    map.set(1, 3);
    assertNotNull(map.get(1, 3));
  }

  @Test
  public void testUnsetWorks() {
    BrailleMap map = new BrailleMap();
    assertNull(map.get(0, 0));
    map.set(0, 0);
    assertNotNull(map.get(0, 0));
    map.unset(0, 0);
    assertNull(map.get(0, 0));
  }

  @Test
  public void testToStringWorks() {
    BrailleMap map = new BrailleMap();
    assertNotNull(map.toString().equals("⠀"));
    map.change(0, 0, Ansi.Color.DEFAULT);
    assertTrue(map.toString().equals("⠁"));
    map.change(0, 0, null);
    assertTrue(map.toString().equals("⠀"));
  }
}
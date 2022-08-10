/* Copyright (c) 2020 LibJ
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

package org.libj.console;

import org.junit.Test;
import org.libj.console.Ansi.Color;
import org.libj.console.Ansi.Intensity;

public class AnsiTest {
  private static final String str = "The quick brown fox jumps over the lazy dog";

  private static String test(final String str) {
    return str + " " + Ansi.toHtml(str);
  }

  @Test
  public void testColor() {
    for (final Color color : Color.values()) // [A]
      System.out.println("C[" + color + "] " + test(color.apply(str)));
  }

  @Test
  public void testIntensity() {
    for (final Intensity intensity : Intensity.values()) // [A]
      System.out.println("I[" + intensity + "] " + test(intensity.apply(str)));
  }

  @Test
  public void testColorIntensity() {
    for (final Intensity intensity : Intensity.values()) // [A]
      for (final Color color : Color.values()) // [A]
        System.out.println("I[" + intensity + "] C[" + color + "] " + test(Ansi.apply(str, intensity, color)));
  }
}
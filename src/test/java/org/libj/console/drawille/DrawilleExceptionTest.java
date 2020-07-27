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

public class DrawilleExceptionTest {
  @Test
  public void testExceptionPassParameters() {
    try {
      throw new DrawilleException(-1, -2);
    }
    catch (final DrawilleException e) {
      assertTrue(e.getMessage().equals("Out of range {x:-1,y:-2}"));
    }
  }
}
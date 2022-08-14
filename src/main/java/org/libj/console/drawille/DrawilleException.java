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

/**
 * This class inherits from the RuntimeException class. It is meant to be thrown whenever an out of range value is passed to the
 * Canvas and BrailleMap class. The message is statically defined in this class and the caller only has to pass in the out of bounds
 * (x,y) value pairs.
 */
public class DrawilleException extends RuntimeException {
  /**
   * This constructor takes in an (x,y) value pair and displays those pairs to the user. These values are defined to be out of range
   * by the caller, therefore the caller will be alerted.
   *
   * @param x The passed horizontal coordinate
   * @param y The passed vertical coordinate
   */
  public DrawilleException(int x, int y) {
    super(String.format("Out of range {x:%d,y:%d}", x, y));
  }
}
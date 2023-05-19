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

import java.util.Arrays;

import org.libj.console.Ansi;

/**
 * This class stores a 4 by 2 pixel matrix that is eventually translated into a braille character. This method abstracts away all
 * the calculations that is needed to transform a matrix into a braille character. This class is meant to be used as a sub-matrix.
 */
public class BrailleMap {
  /** Braille characters unicode offset */
  protected static final int UNICODE_OFFSET = 10240;

  /** Transformation matrix for braille */
  protected static final int[] TRANSFORM_MATRIX = {1, 8, 2, 16, 4, 32, 64, 128};

  /** Flattened pixel map matrix (4 by 2) */
  protected Ansi.Color[] map;

  /**
   * This constructor initializes the pixel map matrix and resets the matrix by initializing the values with false.
   */
  public BrailleMap() {
    this.map = new Ansi.Color[8];
    this.reset();
  }

  /**
   * This method takes in a horizontal and vertical component and checks to see if it is in range of the pixel matrix. Since braille
   * can be expressed by a 4 by 2 dot matrix, these bounds are taken to be the upper bound respectively while negative numbers are
   * taken as the lower bound.
   *
   * @param x Horizontal coordinate
   * @param y Vertical coordinate
   */
  protected void checkRange(final int x, final int y) {
    if (x < 0 || y < 0 || x > 1 || y > 3)
      throw new DrawilleException(x, y);
  }

  /**
   * This method takes in a horizontal and vertical coordinates alongside a matrix entry value. It then sets said value into the
   * pixel matrix based on the passed coordinates
   *
   * @param x Horizontal coordinate
   * @param y Vertical coordinate
   * @param color The color to set matrix entry to
   */
  public void change(final int x, final int y, final Ansi.Color color) {
    checkRange(x, y);
    map[y * 2 + x] = color;
  }

  /**
   * This method takes in a horizontal and vertical coordinates and it returns the value that is saved in the pixel matrix based on
   * the passed coordinates.
   *
   * @param x Horizontal coordinate
   * @param y Vertical coordinate
   * @return Boolean Saved state based on coordinates
   */
  public Ansi.Color get(final int x, final int y) {
    checkRange(x, y);
    return map[y * 2 + x];
  }

  /**
   * This method takes in a horizontal and vertical coordinates, it then activates the value into the pixel matrix based on the
   * passed coordinates.
   *
   * @param x Horizontal coordinate
   * @param y Vertical coordinate
   */
  public void set(final int x, final int y) {
    set(x, y, Ansi.Color.DEFAULT);
  }

  public void set(final int x, final int y, final Ansi.Color color) {
    change(x, y, color);
  }

  /**
   * This method takes in a horizontal and vertical coordinates, it then deactivates the value into the pixel matrix based on the
   * passed coordinates.
   *
   * @param x Horizontal coordinate
   * @param y Vertical coordinate
   */
  public void unset(final int x, final int y) {
    change(x, y, null);
  }

  /**
   * This method traverses through the pixel map matrix and deactivates all the pixels in the matrix by setting all the values to
   * false.
   */
  public void reset() {
    Arrays.fill(map, null);
  }

  /**
   * This method traverses through the pixel map matrix and transforms the matrix into a braille character. The resulting character
   * is returned in string value.
   *
   * @return String Pixel matrix as braille character
   */
  @Override
  public String toString() {
    int decimal = BrailleMap.UNICODE_OFFSET;
    Ansi.Color color = null;
    for (int i = 0; i < 8; ++i) { // [N]
      if (map[i] != null) {
        color = map[i];
        decimal += BrailleMap.TRANSFORM_MATRIX[i];
      }
    }

    final String str = Character.toString((char)decimal);
    return color == null ? str : color.apply(str);
  }
}
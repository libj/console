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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UncheckedIOException;

import org.libj.console.Ansi;

/**
 * This class is used to hold all the BrailleMap objects and uses them as
 * sub-matrices. It is an abstraction of a pixel screen. Methods to interact
 * with those pixels can be found in this class.
 */
@SuppressWarnings("javadoc")
public class Canvas {
  /**
   * @var Integer width Width of the canvas
   * @var Integer height Height of the canvas
   * @var BrailleMap [] screen Flattened screen matrix
   */
  protected final int width;
  protected final int pixelWidth;
  protected final int height;
  protected final int pixelHeight;
  protected final int area;
  protected final BrailleMap[] screen;

  /**
   * This constructor takes in a width and height and initializes a flattened
   * matrix of BrailleMap objects. These objects serve as sub-matrices and
   * extend the 'pixel' definition that can be displayed on a screen.
   *
   * @param Integer width The desired width of the canvas
   * @param Integer height The desired height of the canvas
   */
  public Canvas(final int width, final int height) {
    this.width = width;
    this.pixelWidth = width * 2;
    this.height = height;
    this.pixelHeight = height * 4;
    this.area = width * height;
    this.screen = new BrailleMap[area];
    for (int i = 0; i < area; ++i)
      screen[i] = new BrailleMap();
  }

  /**
   * This method takes in a horizontal and vertical component and checks to see
   * if it is in range of the screen matrix. Since braille can be expressed by a
   * 3 by 2 dot matrix, these bounds are taken to be the upper bound
   * respectively while negative numbers are taken as the lower bound. These
   * values are taken into effect by the getWidth and getHeight methods.
   *
   * @param Integer x Horizontal coordinate
   * @param Integer y Vertical coordinate
   * @return void
   */
  protected void checkRange(final int x, final int y) {
    if (x >= getWidth() || y >= getHeight() || x < 0 || y < 0)
      throw new DrawilleException(x, y);
  }

  /**
   * This method returns the screen width in the true pixel definition. The user
   * supplied width is multiplied by 2 because a braille dot matrix has 2
   * columns.
   *
   * @return Integer True pixel width
   */
  public int getWidth() {
    return pixelWidth;
  }

  /**
   * This method returns the screen height in the true pixel definition. The
   * user supplied height is multiplied by 4 because a braille dot matrix has 4
   * rows.
   *
   * @return Integer True pixel width
   */
  public int getHeight() {
    return pixelHeight;
  }

  /**
   * This method takes in a horizontal and vertical coordinate and returns the
   * value of the activation of said pixel. If true, the pixel is turned on,
   * otherwise it is off.
   *
   * @param Integer x Horizontal coordinate of pixel
   * @param Integer y Vertical coordinate of pixel
   * @return Boolean The activation value of the pixel
   */
  public Ansi.Color get(final int x, final int y) {
    checkRange(x, y);
    final BrailleMap map = screen[((y / 4) * width) + (x / 2)];
    return map.get(x % 2, y % 4);
  }

  /**
   * This method takes in a horizontal and vertical coordinate as well as an
   * activation state. It then applies that activation to said pixel that lives
   * in the passed coordinates.
   *
   * @param Integer x Horizontal coordinate of pixel
   * @param Integer y Vertical coordinate of pixel
   * @param Boolean value Activation to set on pixel
   * @return void
   */
  public void change(final int x, final int y, final Ansi.Color color) {
    checkRange(x, y);
    final BrailleMap map = screen[((y / 4) * width) + (x / 2)];
    map.change(x % 2, y % 4, color);
  }

  /**
   * This method takes in a horizontal and vertical coordinate, it then
   * activates said pixel by setting it's value to true.
   *
   * @param Integer x Horizontal coordinate of pixel
   * @param Integer y Vertical coordinate of pixel
   * @return void
   */
  public void set(final int x, final int y) {
    set(x, y, Ansi.Color.DEFAULT);
  }

  public void set(final int x, final int y, final Ansi.Color color) {
    change(x, y, color);
  }

  /**
   * This method takes in a horizontal and vertical coordinate, it then
   * deactivates said pixel by setting it's value to false.
   *
   * @param Integer x Horizontal coordinate of pixel
   * @param Integer y Vertical coordinate of pixel
   * @return void
   */
  public void unset(final int x, final int y) {
    change(x, y, null);
  }

  /**
   * This method traverses through all the BrailleMap objects that is stored to
   * make up the screen, it then resets all the values in those sub-matrices.
   *
   * @return void
   */
  public void clear() {
    for (int i = 0; i < area; ++i) {
      screen[i].reset();
    }
  }

  /**
   * This method traverses through all the BrailleMap objects and renders out
   * the sub-matrices by asking for the object's string value with the getString
   * method. It then prints them all out to the screen by using the overloaded
   * corresponding render method.
   *
   * @return void
   */
  public void render() {
    try {
      render(System.out);
    }
    catch (final IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  /**
   * This method traverses through all the BrailleMap objects and renders out
   * the sub-matrices by asking for the object's string value with the getString
   * method. It then writes said output to the specified ByteArrayOutputStream.
   * This stream is then returned back to caller for method chaining.
   *
   * @param ByteArrayOutputStream stream Stream to write to
   * @return ByteArrayOutputStream Same stream that was passed in
   * @throws IOException ByteArrayOutputStream throws exception
   */
  public OutputStream render(final OutputStream out) throws IOException {
    for (int i = 0; i < area; ++i) {
      out.write(screen[i].toString().getBytes());
      if (i % width == width - 1)
        out.write('\n');
    }

    return out;
  }

  @Override
  public String toString() {
    try {
      final ByteArrayOutputStream writer = new ByteArrayOutputStream();
      for (int i = 0; i < area; ++i) {
        writer.write(screen[i].toString().getBytes());
        if (i % width == width - 1)
          writer.write('\n');
      }

      return new String(writer.toByteArray());
    }
    catch (final IOException e) {
      throw new UncheckedIOException(e);
    }
  }
}
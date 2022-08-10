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
 * This class inherits from the Canvas class and it tries to implement turtle graphics. The methods in this class can be abstracted
 * with an idea of a pen and paper. One can move the pen in three axis and based on the z axis, when the pen moves it either draws
 * on the paper or not.
 */
public class Turtle extends Canvas {
  /** Horizontal coordinate */
  protected double x = 0;

  /** Vertical coordinate */
  protected double y = 0;

  /** Angle */
  protected double a = 0;

  /** Is the pen down? */
  protected Boolean isDrawing = false;

  /**
   * This constructor simply calls the super constructor and passes the desired dimensions.
   *
   * @param width Desired width of canvas
   * @param height Desired height of canvas
   */
  public Turtle(final int width, final int height) {
    super(width, height);
  }

  /**
   * This method simply returns the horizontal component of the position of the pen.
   *
   * @return double Horizontal position of pen
   */
  public double getX() {
    return x;
  }

  /**
   * This method simply returns the vertical component of the position of the pen.
   *
   * @return double Vertical position of pen
   */
  public double getY() {
    return y;
  }

  /**
   * This method simply returns the angle component of the position of the pen.
   *
   * @return double Angle of pen
   */
  public double getAngle() {
    return a;
  }

  /**
   * This method simply sets the state of the pen to be in the drawing state.
   */
  public void down() {
    isDrawing = true;
  }

  /**
   * This method simply sets the state of the pen to be in the non-drawing state.
   */
  public void up() {
    isDrawing = false;
  }

  /**
   * This method takes in the angle to add to the right and adds it to the pen angle.
   *
   * @param angle Angle to move right
   */
  public void right(final double angle) {
    a += angle;
  }

  /**
   * This method takes in the angle to add to the left and subtracts it from pen angle.
   *
   * @param angle Angle to move right
   */
  public void left(final double angle) {
    a -= angle;
  }

  /**
   * This method takes in the length and expects us to move backwards with the pen based on the current pen angle. The forward
   * method is used to implement this method by simply passing in the same scalar value of length but in the opposite direction.
   *
   * @param length Length to move back
   */
  public void backward(final double length) {
    forward(length * -1);
  }

  /**
   * This method takes in the length and expects us to move forwards with the pen based on the current pen angle.
   *
   * @param length Length to move forward
   */
  public void forward(double length) {
    final double theta = a / 180.0 * Math.PI;
    move(x + length * Math.cos(theta), y + length * Math.sin(theta));
  }

  /**
   * This method takes in a new horizontal and vertical coordinate and based on the current coordinates, it draws a line to connect
   * them. If the state of isDrawing is false, then we do not draw and instead just move the pen to those coordinates.
   *
   * @param x Horizontal coordinate
   * @param y Vertical coordinate
   */
  public void move(final double x, final double y) {
    if (isDrawing) {
      int x1 = (int)Math.round(this.x);
      int y1 = (int)Math.round(this.y);
      int x2 = (int)Math.round(x);
      int y2 = (int)Math.round(y);
      int deltaX = Math.max(x1, x2) - Math.min(x1, x2);
      int deltaY = Math.max(y1, y2) - Math.min(y1, y2);
      int directionX = x1 <= x2 ? 1 : -1;
      int directionY = y1 <= y2 ? 1 : -1;
      int a = Math.max(deltaX, deltaY);
      for (int i = 0; i <= a; ++i) { // [N]
        int x0 = x1;
        int y0 = y1;
        if (deltaY > 0)
          y0 += ((double)i * deltaY) / a * directionY;

        if (deltaX > 0)
          x0 += ((double)i * deltaX) / a * directionX;

        set(x0, y0);
      }
    }

    this.x = x;
    this.y = y;
  }
}
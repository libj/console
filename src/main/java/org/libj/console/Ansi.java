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

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;

/**
 * Class for utilities related to ANSI standards.
 */
public final class Ansi {
  private static Color[] colors = new Color[10];
  private static Intensity[][] intensities = new Intensity[5][7];

  /**
   * Enum of ANSI color codes.
   */
  public enum Color implements Function<String,String> {
    BLACK(0, "black"),
    BLUE(4, "blue"),
    CYAN(6, "cyan"),
    DEFAULT(9, null),
    GREEN(2, "lightgreen"),
    MAGENTA(5, "magenta"),
    RED(1, "red"),
    WHITE(7, "white"),
    YELLOW(3, "yellow");

    private final String lowerCase;
    private final String code;
    private final String css;

    Color(final int code, final String css) {
      this.lowerCase = name().toLowerCase();
      this.code = String.valueOf(code);
      this.css = css;
      colors[code] = this;
    }

    public String toCSS() {
      return css != null ? "color:" + css : null;
    }

    String toSequence() {
      return String.valueOf(code);
    }

    /**
     * Applies this {@code Color} to the specified string.
     */
    @Override
    public String apply(final String t) {
      return Ansi.apply0(t, Intensity.DEFAULT, this);
    }

    private static Comparator<Object> comparator = new Comparator<Object>() {
      @Override
      public int compare(final Object o1, final Object o2) {
        final String n1 = o1 instanceof Color ? ((Color)o1).lowerCase : (String)o1;
        final String n2 = o2 instanceof Color ? ((Color)o2).lowerCase : (String)o2;
        return n1.compareTo(n2);
      }
    };

    public static Color fromString(final String str) {
      final Color[] values = values();
      final int index = Arrays.binarySearch(values, str.toLowerCase(), comparator);
      return index < 0 ? null : values[index];
    }

    public static Color fromCode(final int code) {
      return colors[code];
    }
  }

  /**
   * Enum of ANSI intensity codes.
   */
  public enum Intensity implements Function<String,String> {
    BOLD(1, 3, "font-weight:bolder"),
    DEFAULT(0, 3, null),
    FAINT(2, 3, "font-weight:light"),
    INTENSE(0, 9, "font-weight:bold"),
    ITALIC(3, 3, "font-weight:italic"),
    UNDERLINE(4, 3, "text-decoration:underline");

    private final String lowerCase;
    private final String strength;
    private final String group;
    private final String css;

    Intensity(final int strength, final int group, final String css) {
      this.lowerCase = name().toLowerCase();
      this.strength = String.valueOf(strength);
      this.group = String.valueOf(group);
      this.css = css;
      intensities[strength][group - 3] = this;
    }

    public String toCSS() {
      return css;
    }

    String toSequence() {
      return strength + ";" + group;
    }

    /**
     * Applies this {@link Intensity} to the specified string.
     */
    @Override
    public String apply(final String t) {
      return Ansi.apply0(t, this, Color.DEFAULT);
    }

    private static Comparator<Object> comparator = new Comparator<Object>() {
      @Override
      public int compare(final Object o1, final Object o2) {
        final String n1 = o1 instanceof Intensity ? ((Intensity)o1).lowerCase : (String)o1;
        final String n2 = o2 instanceof Intensity ? ((Intensity)o2).lowerCase : (String)o2;
        return n1.compareTo(n2);
      }
    };

    public static Intensity fromString(final String str) {
      final Intensity[] values = values();
      final int index = Arrays.binarySearch(values, str.toLowerCase(), comparator);
      return index < 0 ? null : values[index];
    }

    public static Intensity fromCode(final int strength, final int group) {
      return intensities[strength][group - 3];
    }
  }

  private static final String ENCODE_START = "\033[";
  private static final String ENCODE_END = "m";
  private static final String RESET = "0;39";

  /**
   * Applies the specified {@code intensity} to the provided {@code str}.
   *
   * @param str The string to which the specified {@code intensity} is to be
   *          applied.
   * @param intensity The {@link Intensity}.
   * @return A new string with the specified {@code intensity} applied to the
   *         provided {@code str}.
   */
  public static String apply(final String str, final Intensity intensity) {
    return intensity == null || intensity == Intensity.DEFAULT ? str : apply0(str, intensity, Color.DEFAULT);
  }

  /**
   * Applies the specified {@code color} to the provided {@code str}.
   *
   * @param str The string to which the specified {@code color} is to be
   *          applied.
   * @param color The {@link Color}.
   * @return A new string with the specified {@code color} applied to the
   *         provided {@code str}.
   */
  public static String apply(final String str, final Color color) {
    return color == null || color == Color.DEFAULT ? str : apply0(str, Intensity.DEFAULT, color);
  }

  /**
   * Applies the specified {@code intensity} and {@code color} to the provided
   * {@code str}. If {@code intensity} or {@code color} is null, the default is
   * applied instead.
   *
   * @param str The string to which the specified {@code intensity} and
   *          {@code color} are to be applied.
   * @param intensity The {@link Intensity}.
   * @param color The {@link Color}.
   * @return A new string with the specified {@code intensity} and {@code color}
   *         applied to the provided {@code str}.
   */
  public static String apply(final String str, Intensity intensity, Color color) {
    if (intensity == null) {
      if (color == null)
        return str;

      intensity = Intensity.DEFAULT;
    }
    else if (color == null) {
      color = Color.DEFAULT;
    }

    return apply0(str, intensity, color);
  }

  /**
   * Accepts a string that may contain ANSI escapes for colors and intensities,
   * and returns an equivalent string with the same colors and intensities as
   * HTML+CSS tags.
   *
   * @param str The string whose ANSI-escaped colors and/or intensities are to
   *          be transformed into HTML+CSS equivalents.
   * @return The transformed string.
   */
  public static String toHtml(final String str) {
    final StringBuilder builder = new StringBuilder();
    final char[] chars = str.toCharArray();
    char ch0, ch1 = Character.MAX_VALUE;
    int strength = Integer.MIN_VALUE;
    int group = Integer.MIN_VALUE;
    Intensity intensity = null;
    Color color = null;
    boolean hasEndTag = true;
    for (int i = 0; i < chars.length; ++i, ch1 = ch0) {
      ch0 = chars[i];
      if (color != null && intensity != null && ch0 == 'm') {
        if (color == Color.DEFAULT && intensity == Intensity.DEFAULT) {
          builder.append("</span>");
          hasEndTag = true;
        }
        else {
          if (!hasEndTag)
            builder.append("</span>");

          hasEndTag = false;
          builder.append("<span style=\"");
          if (intensity != Intensity.DEFAULT)
            builder.append(intensity.toCSS()).append(';');

          if (color != Color.DEFAULT)
            builder.append(color.toCSS()).append(';');

          builder.append("\">");
        }

        strength = Integer.MIN_VALUE;
        group = Integer.MIN_VALUE;
        intensity = null;
        color = null;
      }
      else if (group == Integer.MAX_VALUE) {
        group = ch0 - '0';
      }
      else if (group != Integer.MIN_VALUE) {
        color = Color.fromCode(ch0 - '0');
        intensity = Intensity.fromCode(strength, group);
      }
      else if (strength == Integer.MAX_VALUE) {
        strength = ch0 - '0';
      }
      else if (strength != Integer.MIN_VALUE && group == Integer.MIN_VALUE && ch0 == ';') {
        group = Integer.MAX_VALUE;
      }
      else if (ch0 == '[' && ch1 == '\033') {
        strength = Integer.MAX_VALUE;
        builder.setLength(builder.length() - 1);
      }
      else {
        builder.append(ch0);
      }
    }

    return builder.toString();
  }

  private static String apply0(final String str, Intensity intensity, Color color) {
    final StringBuilder builder = new StringBuilder();
    builder.append(ENCODE_START);
    builder.append(intensity.toSequence()).append(color.toSequence());
    builder.append(ENCODE_END);
    builder.append(str);
    builder.append(ENCODE_START);
    builder.append(RESET);
    builder.append(ENCODE_END);
    return builder.toString();
  }

  private Ansi() {
  }
}
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

import static org.libj.lang.Assertions.*;
import static org.libj.lang.Strings.Align.*;

import org.libj.lang.Strings;
import org.libj.lang.Strings.Align;

public final class Tables {
  /**
   * Returns a string with a table layout of the specified array of data organized into columns with the provided {@code headings}.
   * <p>
   * This is the equivalent of calling:
   *
   * <pre>
   * {@code printTable(false, true, data, headings)}
   * </pre>
   *
   * @param data The array of data.
   * @param headings The headings of the columns.
   * @return A string with a table layout of the specified data array organized into columns with the provided {@code headings}.
   * @throws IllegalArgumentException If {@code data} or {@code headings} is null.
   */
  public static String printTable(final Object[] data, final String ... headings) {
    return printTable(false, CENTER, LEFT, 1, false, data, headings);
  }

  /**
   * Returns a string with a table layout of the specified array of data organized into columns with the provided {@code headings}.
   * <p>
   * This is the equivalent of calling:
   *
   * <pre>
   * {@code printTable(false, true, data, headings)}
   * </pre>
   *
   * @param data The array of data.
   * @param cells The number of consecutive column elements per cell (except for the first and single heading column element).
   * @param firstColumnOneCell Whether the first column is to have 1 cell.
   * @param headings The headings of the columns.
   * @return A string with a table layout of the specified data array organized into columns with the provided {@code headings}.
   * @throws IllegalArgumentException If {@code data} or {@code headings} is null.
   */
  public static String printTable(final Object[] data, final int cells, final boolean firstColumnOneCell, final String ... headings) {
    return printTable(false, CENTER, LEFT, cells, firstColumnOneCell, data, headings);
  }

  /**
   * Returns a string with a table layout of the specified array of data organized into columns with the provided {@code headings}.
   * <p>
   * This is the equivalent of calling:
   *
   * <pre>
   * {@code printTable(false, true, data, headings)}
   * </pre>
   *
   * @param data The array of data.
   * @param headings The headings of the columns.
   * @return A string with a table layout of the specified data array organized into columns with the provided {@code headings}.
   * @throws IllegalArgumentException If {@code data} or {@code headings} is null.
   */
  public static String printTable(final String[] data, final String ... headings) {
    return printTable(false, CENTER, LEFT, 1, false, data, headings);
  }

  /**
   * Returns a string with a table layout of the specified array of data organized into columns with the provided {@code headings}.
   * <p>
   * This is the equivalent of calling:
   *
   * <pre>
   * {@code printTable(false, true, data, headings)}
   * </pre>
   *
   * @param data The array of data.
   * @param cells The number of consecutive column elements per cell (except for the first and single heading column element).
   * @param firstColumnOneCell Whether the first column is to have 1 cell.
   * @param headings The headings of the columns.
   * @return A string with a table layout of the specified data array organized into columns with the provided {@code headings}.
   * @throws IllegalArgumentException If {@code data} or {@code headings} is null.
   */
  public static String printTable(final String[] data, final int cells, final boolean firstColumnOneCell, final String ... headings) {
    return printTable(false, CENTER, LEFT, cells, firstColumnOneCell, data, headings);
  }

  /**
   * Returns a string with a table layout of the specified array of data organized into columns with the provided {@code headings}.
   *
   * @param borders Whether to draw borders.
   * @param alignHeading {@link Align} for the heading.
   * @param align Text alignment to be used for data cells.
   * @param data The array of data.
   * @param headings The headings of the columns.
   * @return A string with a table layout of the specified data array organized into columns with the provided {@code headings}.
   * @throws IllegalArgumentException If {@code data} or {@code headings} is null.
   */
  public static String printTable(final boolean borders, final Align alignHeading, final Align align, final Object[] data, final String ... headings) {
    return printTable(borders, alignHeading, align, 1, false, data, headings);
  }

  /**
   * Returns a string with a table layout of the specified array of data organized into columns with the provided {@code headings}.
   *
   * @param borders Whether to draw borders.
   * @param alignHeading {@link Align} for the heading.
   * @param align Text alignment to be used for data cells.
   * @param cells The number of consecutive column elements per cell (except for the first and single heading column element).
   * @param firstColumnOneCell Whether the first column is to have 1 cell.
   * @param data The array of data.
   * @param headings The headings of the columns.
   * @return A string with a table layout of the specified data array organized into columns with the provided {@code headings}.
   * @throws IllegalArgumentException If {@code data} or {@code headings} is null.
   */
  public static String printTable(final boolean borders, final Align alignHeading, final Align align, final int cells, final boolean firstColumnOneCell, final Object[] data, final String ... headings) {
    if (assertNotNull(data).getClass().getComponentType() == String.class)
      return printTable(borders, alignHeading, align, cells, firstColumnOneCell, (String[])data, headings);

    final String[] strings = new String[data.length];
    for (int i = 0; i < data.length; ++i)
      if (data[i] != null)
        strings[i] = String.valueOf(data[i]);

    return printTable(borders, alignHeading, align, cells, firstColumnOneCell, strings, headings);
  }

  /**
   * Returns a string with a table layout of the specified array of data organized into columns with the provided {@code headings}.
   *
   * @param borders Whether to draw borders.
   * @param alignHeading {@link Align} for the heading.
   * @param align Text alignment to be used for data cells.
   * @param data The array of data.
   * @param headings The headings of the columns.
   * @return A string with a table layout of the specified data array organized into columns with the provided {@code headings}.
   * @throws IllegalArgumentException If {@code data} or {@code headings} is null.
   */
  public static String printTable(final boolean borders, final Align alignHeading, final Align align, final String[] data, final String ... headings) {
    return printTable(borders, alignHeading, align, 1, false, data, headings);
  }

  /**
   * Returns a string with a table layout of the specified array of data organized into columns with the provided {@code headings}.
   *
   * @param borders Whether to draw borders.
   * @param alignHeading {@link Align} for the heading.
   * @param align Text alignment to be used for data cells.
   * @param cells The number of consecutive column elements per cell (except for the first and single heading column element).
   * @param firstColumnOneCell Whether the first column is to have 1 cell.
   * @param data The array of data.
   * @param headings The headings of the columns.
   * @return A string with a table layout of the specified data array organized into columns with the provided {@code headings}.
   * @throws IllegalArgumentException If {@code data} or {@code headings} is null.
   */
  public static String printTable(final boolean borders, final Align alignHeading, final Align align, final int cells, final boolean firstColumnOneCell, final String[] data, final String ... headings) {
    final int rows = assertNotNull(data).length / assertNotNull(headings).length;
    final int remainder = data.length % headings.length == 0 ? 0 : 1;

    final String[][] columns = new String[headings.length][];
    for (int i = 0; i < headings.length; ++i) {
      final String[] column = columns[i] = new String[1 + (rows + remainder) * cells];
      column[0] = headings[i];
      for (int j = 1; j < column.length; j += cells) {
        for (int k = 0; k < cells; ++k) {
          final int l = i * headings.length * cells + (j - 1) + k;
          if (l < data.length)
            column[j + k] = data[l];
        }
      }
    }

    return printTable(borders, alignHeading, align, cells, firstColumnOneCell, columns);
  }

  /**
   * Returns a string with a table layout of the provided array of columns of new-line-delimited rows, without borders, aligned to
   * the left.
   * <p>
   * This is the equivalent of calling:
   *
   * <pre>
   * {@code printTable(false, true, columns)}
   * </pre>
   *
   * @param columns The 2 dimensional array of columns to print.
   * @return A string with a column layout of the provided 2 dimensional array.
   * @throws IllegalArgumentException If {@code columns} is null.
   */
  public static String printTable(final String ... columns) {
    return printTable(false, CENTER, LEFT, 1, false, columns);
  }

  /**
   * Returns a string with a table layout of the provided array of columns of new-line-delimited rows, without borders, aligned to
   * the left.
   * <p>
   * This is the equivalent of calling:
   *
   * <pre>
   * {@code printTable(false, true, columns)}
   * </pre>
   *
   * @param cells The number of consecutive column elements per cell (except for the first and single heading column element).
   * @param firstColumnOneCell Whether the first column is to have 1 cell.
   * @param columns The 2 dimensional array of columns to print.
   * @return A string with a column layout of the provided 2 dimensional array.
   * @throws IllegalArgumentException If {@code columns} is null.
   */
  public static String printTable(final int cells, final boolean firstColumnOneCell, final String ... columns) {
    return printTable(false, CENTER, LEFT, cells, firstColumnOneCell, columns);
  }

  /**
   * Returns a string with a table layout of the provided array of columns of new-line-delimited rows, without borders, aligned to
   * the left.
   * <p>
   * This is the equivalent of calling:
   *
   * <pre>
   * {@code printTable(false, true, columns)}
   * </pre>
   *
   * @param borders Whether to draw borders.
   * @param alignHeading {@link Align} for the heading.
   * @param align Text alignment to be used for data cells.
   * @param columns The 2 dimensional array of columns to print.
   * @return A string with a column layout of the provided 2 dimensional array.
   * @throws IllegalArgumentException If {@code columns} is null.
   */
  public static String printTable(final boolean borders, final Align alignHeading, final Align align, final String ... columns) {
    return printTable(borders, alignHeading, align, 1, false, columns);
  }

  /**
   * Returns a string with a table layout of the provided array of columns of new-line-delimited rows, without borders, aligned to
   * the left.
   * <p>
   * This is the equivalent of calling:
   *
   * <pre>
   * {@code printTable(false, true, columns)}
   * </pre>
   *
   * @param borders Whether to draw borders.
   * @param alignHeading {@link Align} for the heading.
   * @param align Text alignment to be used for data cells.
   * @param cells The number of consecutive column elements per cell (except for the first and single heading column element).
   * @param firstColumnOneCell Whether the first column is to have 1 cell.
   * @param columns The 2 dimensional array of columns to print.
   * @return A string with a column layout of the provided 2 dimensional array.
   * @throws IllegalArgumentException If {@code columns} is null.
   */
  public static String printTable(final boolean borders, final Align alignHeading, final Align align, final int cells, final boolean firstColumnOneCell, final String ... columns) {
    // Split input strings into columns and rows
    final String[][] strings = new String[assertNotNull(columns).length][];
    for (int i = 0; i < columns.length; ++i)
      strings[i] = columns[i] == null ? null : columns[i].split("\n");

    return printTable(borders, alignHeading, align, cells, firstColumnOneCell, (Object[][])strings);
  }

  /**
   * Returns a string with a table layout of the provided 2 dimensional array of columns, without borders, aligned to the left.
   * <p>
   * This is the equivalent of calling:
   *
   * <pre>
   * {@code printTable(false, true, columns)}
   * </pre>
   *
   * @param columns The 2 dimensional array of columns to print.
   * @return A string with a column layout of the provided 2 dimensional array.
   * @throws IllegalArgumentException If {@code columns} is null.
   */
  public static String printTable(final String[] ... columns) {
    return printTable(false, CENTER, LEFT, 1, false, columns);
  }

  /**
   * Returns a string with a table layout of the provided 2 dimensional array of columns, without borders, aligned to the left.
   * <p>
   * This is the equivalent of calling:
   *
   * <pre>
   * {@code printTable(false, true, columns)}
   * </pre>
   *
   * @param columns The 2 dimensional array of columns to print.
   * @param cells The number of consecutive column elements per cell (except for the first and single heading column element).
   * @param firstColumnOneCell Whether the first column is to have 1 cell.
   * @return A string with a column layout of the provided 2 dimensional array.
   * @throws IllegalArgumentException If {@code columns} is null.
   */
  public static String printTable(final int cells, final boolean firstColumnOneCell, final String[] ... columns) {
    return printTable(false, CENTER, LEFT, cells, firstColumnOneCell, columns);
  }

  /**
   * Returns a string with a table layout of the provided 2 dimensional array of columns, without borders, aligned to the left.
   * <p>
   * This is the equivalent of calling:
   *
   * <pre>
   * {@code printTable(false, true, columns)}
   * </pre>
   *
   * @param columns The 2 dimensional array of columns to print.
   * @return A string with a column layout of the provided 2 dimensional array.
   * @throws IllegalArgumentException If {@code columns} is null.
   */
  public static String printTable(final Object[] ... columns) {
    return printTable(false, CENTER, LEFT, 1, false, columns);
  }

  /**
   * Returns a string with a table layout of the provided 2 dimensional array of columns, without borders, aligned to the left.
   * <p>
   * This is the equivalent of calling:
   *
   * <pre>
   * {@code printTable(false, true, columns)}
   * </pre>
   *
   * @param cells The number of consecutive column elements per cell (except for the first and single heading column element).
   * @param firstColumnOneCell Whether the first column is to have 1 cell.
   * @param columns The 2 dimensional array of columns to print.
   * @return A string with a column layout of the provided 2 dimensional array.
   * @throws IllegalArgumentException If {@code columns} is null.
   */
  public static String printTable(final int cells, final boolean firstColumnOneCell, final Object[] ... columns) {
    return printTable(false, CENTER, LEFT, cells, firstColumnOneCell, columns);
  }

  /**
   * Returns a string with a table layout of the provided 2 dimensional array of columns.
   *
   * @param borders Whether to draw borders.
   * @param alignHeading {@link Align} for the heading.
   * @param align Text alignment to be used for data cells.
   * @param columns The 2 dimensional array of columns to print.
   * @return A string with a column layout of the provided 2 dimensional array.
   * @throws IllegalArgumentException If {@code columns} is null.
   */
  public static String printTable(final boolean borders, final Align alignHeading, final Align align, final Object[] ... columns) {
    return printTable(borders, alignHeading, align, 1, false, columns);
  }

  /**
   * Returns a string with a table layout of the provided 2 dimensional array of columns.
   *
   * @param borders Whether to draw borders.
   * @param alignHeading {@link Align} for the heading.
   * @param align Text alignment to be used for data cells.
   * @param cells The number of consecutive column elements per cell (except for the first and single heading column element).
   * @param firstColumnOneCell Whether the first column is to have 1 cell.
   * @param columns The 2 dimensional array of columns to print.
   * @return A string with a column layout of the provided 2 dimensional array.
   * @throws IllegalArgumentException If {@code columns} is null.
   */
  public static String printTable(final boolean borders, final Align alignHeading, final Align align, final int cells, final boolean firstColumnOneCell, final Object[] ... columns) {
    if (assertNotNull(columns).getClass().getComponentType() == String[].class)
      return printTable(borders, alignHeading, align, (String[][])columns);

    final String[][] strings = new String[columns.length][];
    for (int i = 0; i < strings.length; ++i) {
      final Object[] column = columns[i];
      if (column != null) {
        final String[] string = strings[i] = new String[column.length];
        for (int j = 0; j < column.length; ++j)
          if (column[j] != null)
            string[j] = String.valueOf(column[j]);
      }
    }

    return printTable(borders, alignHeading, align, cells, firstColumnOneCell, strings);
  }

  /**
   * Returns a string with a table layout of the provided 2 dimensional array of columns.
   *
   * @param borders Whether to draw borders.
   * @param alignHeading {@link Align} for the heading.
   * @param align Text alignment to be used for data cells.
   * @param columns The 2 dimensional array of columns to print.
   * @return A string with a column layout of the provided 2 dimensional array.
   * @throws IllegalArgumentException If {@code columns} is null.
   */
  public static String printTable(final boolean borders, final Align alignHeading, final Align align, final String[] ... columns) {
    return printTable(borders, alignHeading, align, 1, false, columns);
  }

  private static int maxLengthPrintable(final String[] strings) {
    int len = 0;
    for (int i = 0; i < strings.length; ++i)
      len = Math.max(len, Strings.lengthPrintable(strings[i]));

    return len;
  }

  /**
   * Returns a string with a table layout of the provided 2 dimensional array of columns.
   *
   * @param borders Whether to draw borders.
   * @param alignHeading {@link Align} for the heading.
   * @param align Text alignment to be used for data cells.
   * @param cells The number of consecutive column elements per cell (except for the first and single heading column element).
   * @param firstColumnOneCell Whether the first column is to have 1 cell.
   * @param columns The 2 dimensional array of columns to print.
   * @return A string with a column layout of the provided 2 dimensional array.
   * @throws IllegalArgumentException If {@code columns} is null.
   */
  public static String printTable(final boolean borders, final Align alignHeading, final Align align, final int cells, boolean firstColumnOneCell, final String[] ... columns) {
    assertNotNull(columns);
    // Moot if cells == 1 and firstColumnOneCell == true
    if (cells == 1)
      firstColumnOneCell = false;

    // Count the total number of rows
    int numRows = 0;
    for (int c = 0; c < columns.length; ++c)
      if (columns[c] != null)
        numRows = Math.max(numRows, firstColumnOneCell ? columns[c].length : columns[c].length / cells);

    ++numRows; // heading row
    final int[] heights = new int[numRows];

    final String cellPadding = borders ? " │ " : " ";
    final int cellPaddingLength = cellPadding.length();
    int maxRows = 0;
    // Calculate the cell widths for each column
    final int[] widths = new int[columns.length * cells - (firstColumnOneCell ? 1 : 0)];
    for (int c = 0, h = 0; c < columns.length; ++c, h = 0) {
      final int w = c * cells - (c > 0 && firstColumnOneCell ? 1 : 0);
      final String[] rows = columns[c];
      if (rows != null && rows.length > 0) {
        maxRows = Math.max(maxRows, rows.length);
        // First row is the heading, which has only 1 cell
        String[] multiline = rows[0] == null ? Strings.EMPTY_ARRAY : rows[0].split("\n");
        heights[h] = Math.max(heights[h++], multiline.length);
        final int headingWidth = rows[0] == null ? 0 : maxLengthPrintable(multiline);
        for (int i = 0; i < cells; ++i) {
          final int width = widths[w + i];
          int cellWidth = headingWidth;
          if (c == 0 && firstColumnOneCell)
            ++i;
          else
            cellWidth = (int)Math.ceil((cellWidth - (cellPaddingLength * (cells - 1d))) / cells);

          widths[w + i] = Math.max(width, cellWidth);
        }

        // Following rows have `cells` number of cells
        final int inc = c == 0 && firstColumnOneCell ? 1 : cells;
        for (int r = 1; r < rows.length; r += inc, ++h) {
          for (int i = 0; i < inc; ++i) {
            final int width = widths[w + i];
            final int j = i + r;
            if (j < rows.length && rows[j] != null) {
              multiline = rows[j].split("\n");
              heights[h] = Math.max(heights[h], multiline.length);
              widths[w + i] = Math.max(width, maxLengthPrintable(multiline));
            }
          }
        }
      }
    }

    // Print the rows
    final StringBuilder builder = new StringBuilder();

    // Print the top border
    if (borders) {
      builder.append('╔');
      for (int c = 0; c < columns.length; ++c) {
        final int w = c * cells - (c > 0 && firstColumnOneCell ? 1 : 0);
        if (c > 0)
          builder.append('╦');

        // Calculate the full column width across the # of `cells`
        int fullWidth = -cellPaddingLength;
        for (int i = 0; i < cells; ++i) {
          final int width = widths[w + i];
          fullWidth += width + cellPaddingLength;
          if (c == 0 && firstColumnOneCell)
            break;
        }

        builder.append(Strings.repeat('═', fullWidth + 2));
      }

      builder.append('╗');
    }

    // Print the heading row
    String[] rows;
    for (int h = 0; h < heights[0]; ++h) {
      if (borders)
        builder.append("\n║ ");

      for (int c = 0; c < columns.length; ++c) {
        final int w = c * cells - (c > 0 && firstColumnOneCell ? 1 : 0);
        rows = columns[c];
        String row = rows == null || rows[0] == null ? "" : rows[0];
        if (heights[0] != 1) {
          final String[] multiline = row.split("\n");
          final int m = h - (heights[0] - multiline.length);
          row = -1 < m && m < multiline.length ? multiline[m] : "";
        }

        // Calculate the full column width across the # of `cells`
        int fullWidth = -cellPaddingLength;
        for (int i = 0; i < cells; ++i) {
          final int width = widths[w + i];
          fullWidth += width + cellPaddingLength;
          if (c == 0 && firstColumnOneCell)
            break;
        }

        builder.append(Strings.pad(row, alignHeading, fullWidth, ' ', true));
        if (borders)
          builder.append(" ║");

        builder.append(' ');
      }
    }

    // Print the middle border
    if (borders) {
      builder.append("\n╠");
      for (int c = 0; c < columns.length; ++c) {
        final int w = c * cells - (c > 0 && firstColumnOneCell ? 1 : 0);
        if (c > 0)
          builder.append('╬');

        builder.append('═');
        for (int i = 0; i < cells; ++i) {
          if (i > 0)
            builder.append("═╤═");

          final int width = widths[w + i];
          builder.append(Strings.repeat("═", width));
          if (c == 0 && firstColumnOneCell)
            break;
        }

        builder.append('═');
      }

      builder.append("╣");
    }

    // Print the data rows
    for (int r = 1; r < maxRows; r += cells) {
      builder.append('\n');
      if (borders)
        builder.append("║ ");

      for (int c = 0; c < columns.length; ++c) {
        final int w = c * cells - (c > 0 && firstColumnOneCell ? 1 : 0);
        final int i = r > 1 && c == 0 && firstColumnOneCell ? (r + 1) / 2 : r;
        rows = columns[c];
        for (int j = 0; j < cells; ++j) {
          if (j > 0)
            builder.append(cellPadding);

          final String cell = rows == null || i + j >= rows.length || rows[i + j] == null ? "" : rows[i + j];
          final int width = widths[w + j];
          builder.append(Strings.padAll(cell, align, width, ' ', false));
          if (c == 0 && firstColumnOneCell)
            break;
        }

        if (borders)
          builder.append(" ║");

        builder.append(' ');
      }
    }

    // Print the bottom border
    if (borders) {
      builder.append("\n╚");
      for (int c = 0; c < columns.length; ++c) {
        final int w = c * cells - (c > 0 && firstColumnOneCell ? 1 : 0);
        if (c > 0)
          builder.append('╩');

        builder.append('═');
        for (int i = 0; i < cells; ++i) {
          if (i > 0)
            builder.append("═╧═");

          final int width = widths[w + i];
          builder.append(Strings.repeat("═", width));
          if (c == 0 && firstColumnOneCell)
            break;
        }

        builder.append('═');
      }

      builder.append('╝');
    }

    return builder.length() == 0 ? "null" : builder.toString();
  }

  private Tables() {
  }
}
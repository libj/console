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

import org.libj.lang.Strings;
import org.libj.lang.Strings.Align;

public final class Tables {
  /**
   * Returns a string with a table layout of the specified array of data
   * organized into columns with the provided {@code headings}.
   * <p>
   * This is the equivalent of calling:
   *
   * <pre>
   * {@code printTable(false, true, data, headings)}
   * </pre>
   *
   * @param data The array of data.
   * @param headings The headings of the columns.
   * @return A string with a table layout of the specified data array organized
   *         into columns with the provided {@code headings}.
   * @throws NullPointerException If {@code data} or {@code headings} is null.
   */
  public static String printTable(final Object[] data, final String ... headings) {
    return printTable(false, Align.LEFT, 1, false, data, headings);
  }

  /**
   * Returns a string with a table layout of the specified array of data
   * organized into columns with the provided {@code headings}.
   * <p>
   * This is the equivalent of calling:
   *
   * <pre>
   * {@code printTable(false, true, data, headings)}
   * </pre>
   *
   * @param data The array of data.
   * @param cells The number of consecutive column elements per cell (except for
   *          the first and single heading column element).
   * @param firstColumnOneCell Whether the first column is to have 1 cell.
   * @param headings The headings of the columns.
   * @return A string with a table layout of the specified data array organized
   *         into columns with the provided {@code headings}.
   * @throws NullPointerException If {@code data} or {@code headings} is null.
   */
  public static String printTable(final Object[] data, final int cells, final boolean firstColumnOneCell, final String ... headings) {
    return printTable(false, Align.LEFT, cells, firstColumnOneCell, data, headings);
  }

  /**
   * Returns a string with a table layout of the specified array of data
   * organized into columns with the provided {@code headings}.
   * <p>
   * This is the equivalent of calling:
   *
   * <pre>
   * {@code printTable(false, true, data, headings)}
   * </pre>
   *
   * @param data The array of data.
   * @param headings The headings of the columns.
   * @return A string with a table layout of the specified data array organized
   *         into columns with the provided {@code headings}.
   * @throws NullPointerException If {@code data} or {@code headings} is null.
   */
  public static String printTable(final String[] data, final String ... headings) {
    return printTable(false, Align.LEFT, 1, false, data, headings);
  }

  /**
   * Returns a string with a table layout of the specified array of data
   * organized into columns with the provided {@code headings}.
   * <p>
   * This is the equivalent of calling:
   *
   * <pre>
   * {@code printTable(false, true, data, headings)}
   * </pre>
   *
   * @param data The array of data.
   * @param cells The number of consecutive column elements per cell (except for
   *          the first and single heading column element).
   * @param firstColumnOneCell Whether the first column is to have 1 cell.
   * @param headings The headings of the columns.
   * @return A string with a table layout of the specified data array organized
   *         into columns with the provided {@code headings}.
   * @throws NullPointerException If {@code data} or {@code headings} is null.
   */
  public static String printTable(final String[] data, final int cells, final boolean firstColumnOneCell, final String ... headings) {
    return printTable(false, Align.LEFT, cells, firstColumnOneCell, data, headings);
  }

  /**
   * Returns a string with a table layout of the specified array of data
   * organized into columns with the provided {@code headings}.
   *
   * @param borders Whether to draw borders.
   * @param align Text alignment to be used for data cells.
   * @param data The array of data.
   * @param headings The headings of the columns.
   * @return A string with a table layout of the specified data array organized
   *         into columns with the provided {@code headings}.
   * @throws NullPointerException If {@code data} or {@code headings} is null.
   */
  public static String printTable(final boolean borders, final Align align, final Object[] data, final String ... headings) {
    return printTable(borders, align, 1, false, data, headings);
  }

  /**
   * Returns a string with a table layout of the specified array of data
   * organized into columns with the provided {@code headings}.
   *
   * @param borders Whether to draw borders.
   * @param align Text alignment to be used for data cells.
   * @param cells The number of consecutive column elements per cell (except for
   *          the first and single heading column element).
   * @param firstColumnOneCell Whether the first column is to have 1 cell.
   * @param data The array of data.
   * @param headings The headings of the columns.
   * @return A string with a table layout of the specified data array organized
   *         into columns with the provided {@code headings}.
   * @throws NullPointerException If {@code data} or {@code headings} is null.
   */
  public static String printTable(final boolean borders, final Align align, final int cells, final boolean firstColumnOneCell, final Object[] data, final String ... headings) {
    if (data.getClass().getComponentType() == String.class)
      return printTable(borders, align, cells, firstColumnOneCell, (String[])data, headings);

    final String[] strings = new String[data.length];
    for (int i = 0; i < data.length; ++i)
      if (data[i] != null)
        strings[i] = String.valueOf(data[i]);

    return printTable(borders, align, cells, firstColumnOneCell, strings, headings);
  }

  /**
   * Returns a string with a table layout of the specified array of data
   * organized into columns with the provided {@code headings}.
   *
   * @param borders Whether to draw borders.
   * @param align Text alignment to be used for data cells.
   * @param data The array of data.
   * @param headings The headings of the columns.
   * @return A string with a table layout of the specified data array organized
   *         into columns with the provided {@code headings}.
   * @throws NullPointerException If {@code data} or {@code headings} is null.
   */
  public static String printTable(final boolean borders, final Align align, final String[] data, final String ... headings) {
    return printTable(borders, align, 1, false, data, headings);
  }

  /**
   * Returns a string with a table layout of the specified array of data
   * organized into columns with the provided {@code headings}.
   *
   * @param borders Whether to draw borders.
   * @param align Text alignment to be used for data cells.
   * @param cells The number of consecutive column elements per cell (except for
   *          the first and single heading column element).
   * @param firstColumnOneCell Whether the first column is to have 1 cell.
   * @param data The array of data.
   * @param headings The headings of the columns.
   * @return A string with a table layout of the specified data array organized
   *         into columns with the provided {@code headings}.
   * @throws NullPointerException If {@code data} or {@code headings} is null.
   */
  public static String printTable(final boolean borders, final Align align, final int cells, final boolean firstColumnOneCell, final String[] data, final String ... headings) {
    final int rows = data.length / headings.length;
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

    return printTable(borders, align, cells, firstColumnOneCell, columns);
  }

  /**
   * Returns a string with a table layout of the provided array of columns of
   * new-line-delimited rows, without borders, aligned to the left.
   * <p>
   * This is the equivalent of calling:
   *
   * <pre>
   * {@code printTable(false, true, columns)}
   * </pre>
   *
   * @param columns The 2 dimensional array of columns to print.
   * @return A string with a column layout of the provided 2 dimensional array.
   * @throws NullPointerException If {@code columns} is null.
   */
  public static String printTable(final String ... columns) {
    return printTable(false, Align.LEFT, 1, false, columns);
  }

  /**
   * Returns a string with a table layout of the provided array of columns of
   * new-line-delimited rows, without borders, aligned to the left.
   * <p>
   * This is the equivalent of calling:
   *
   * <pre>
   * {@code printTable(false, true, columns)}
   * </pre>
   *
   * @param cells The number of consecutive column elements per cell (except for
   *          the first and single heading column element).
   * @param firstColumnOneCell Whether the first column is to have 1 cell.
   * @param columns The 2 dimensional array of columns to print.
   * @return A string with a column layout of the provided 2 dimensional array.
   * @throws NullPointerException If {@code columns} is null.
   */
  public static String printTable(final int cells, final boolean firstColumnOneCell, final String ... columns) {
    return printTable(false, Align.LEFT, cells, firstColumnOneCell, columns);
  }

  /**
   * Returns a string with a table layout of the provided array of columns of
   * new-line-delimited rows, without borders, aligned to the left.
   * <p>
   * This is the equivalent of calling:
   *
   * <pre>
   * {@code printTable(false, true, columns)}
   * </pre>
   *
   * @param borders Whether to draw borders.
   * @param align Text alignment to be used for data cells.
   * @param columns The 2 dimensional array of columns to print.
   * @return A string with a column layout of the provided 2 dimensional array.
   * @throws NullPointerException If {@code columns} is null.
   */
  public static String printTable(final boolean borders, final Align align, final String ... columns) {
    return printTable(borders, align, 1, false, columns);
  }

  /**
   * Returns a string with a table layout of the provided array of columns of
   * new-line-delimited rows, without borders, aligned to the left.
   * <p>
   * This is the equivalent of calling:
   *
   * <pre>
   * {@code printTable(false, true, columns)}
   * </pre>
   *
   * @param borders Whether to draw borders.
   * @param align Text alignment to be used for data cells.
   * @param cells The number of consecutive column elements per cell (except for
   *          the first and single heading column element).
   * @param firstColumnOneCell Whether the first column is to have 1 cell.
   * @param columns The 2 dimensional array of columns to print.
   * @return A string with a column layout of the provided 2 dimensional array.
   * @throws NullPointerException If {@code columns} is null.
   */
  public static String printTable(final boolean borders, final Align align, final int cells, final boolean firstColumnOneCell, final String ... columns) {
    // Split input strings into columns and rows
    final String[][] strings = new String[columns.length][];
    for (int i = 0; i < columns.length; ++i)
      strings[i] = columns[i] == null ? null : columns[i].split("\n");

    return printTable(borders, Align.LEFT, cells, firstColumnOneCell, (Object[][])strings);
  }

  /**
   * Returns a string with a table layout of the provided 2 dimensional array of
   * columns, without borders, aligned to the left.
   * <p>
   * This is the equivalent of calling:
   *
   * <pre>
   * {@code printTable(false, true, columns)}
   * </pre>
   *
   * @param columns The 2 dimensional array of columns to print.
   * @return A string with a column layout of the provided 2 dimensional array.
   * @throws NullPointerException If {@code columns} is null.
   */
  public static String printTable(final String[] ... columns) {
    return printTable(false, Align.LEFT, 1, false, columns);
  }

  /**
   * Returns a string with a table layout of the provided 2 dimensional array of
   * columns, without borders, aligned to the left.
   * <p>
   * This is the equivalent of calling:
   *
   * <pre>
   * {@code printTable(false, true, columns)}
   * </pre>
   *
   * @param columns The 2 dimensional array of columns to print.
   * @param cells The number of consecutive column elements per cell (except for
   *          the first and single heading column element).
   * @param firstColumnOneCell Whether the first column is to have 1 cell.
   * @return A string with a column layout of the provided 2 dimensional array.
   * @throws NullPointerException If {@code columns} is null.
   */
  public static String printTable(final int cells, final boolean firstColumnOneCell, final String[] ... columns) {
    return printTable(false, Align.LEFT, cells, firstColumnOneCell, columns);
  }

  /**
   * Returns a string with a table layout of the provided 2 dimensional array of
   * columns, without borders, aligned to the left.
   * <p>
   * This is the equivalent of calling:
   *
   * <pre>
   * {@code printTable(false, true, columns)}
   * </pre>
   *
   * @param columns The 2 dimensional array of columns to print.
   * @return A string with a column layout of the provided 2 dimensional array.
   * @throws NullPointerException If {@code columns} is null.
   */
  public static String printTable(final Object[] ... columns) {
    return printTable(false, Align.LEFT, 1, false, columns);
  }

  /**
   * Returns a string with a table layout of the provided 2 dimensional array of
   * columns, without borders, aligned to the left.
   * <p>
   * This is the equivalent of calling:
   *
   * <pre>
   * {@code printTable(false, true, columns)}
   * </pre>
   *
   * @param cells The number of consecutive column elements per cell (except for
   *          the first and single heading column element).
   * @param firstColumnOneCell Whether the first column is to have 1 cell.
   * @param columns The 2 dimensional array of columns to print.
   * @return A string with a column layout of the provided 2 dimensional array.
   * @throws NullPointerException If {@code columns} is null.
   */
  public static String printTable(final int cells, final boolean firstColumnOneCell, final Object[] ... columns) {
    return printTable(false, Align.LEFT, cells, firstColumnOneCell, columns);
  }

  /**
   * Returns a string with a table layout of the provided 2 dimensional array of
   * columns.
   *
   * @param borders Whether to draw borders.
   * @param align Text alignment to be used for data cells.
   * @param columns The 2 dimensional array of columns to print.
   * @return A string with a column layout of the provided 2 dimensional array.
   * @throws NullPointerException If {@code columns} is null.
   */
  public static String printTable(final boolean borders, final Align align, final Object[] ... columns) {
    return printTable(borders, align, 1, false, columns);
  }

  /**
   * Returns a string with a table layout of the provided 2 dimensional array of
   * columns.
   *
   * @param borders Whether to draw borders.
   * @param align Text alignment to be used for data cells.
   * @param cells The number of consecutive column elements per cell (except for
   *          the first and single heading column element).
   * @param firstColumnOneCell Whether the first column is to have 1 cell.
   * @param columns The 2 dimensional array of columns to print.
   * @return A string with a column layout of the provided 2 dimensional array.
   * @throws NullPointerException If {@code columns} is null.
   */
  public static String printTable(final boolean borders, final Align align, final int cells, final boolean firstColumnOneCell, final Object[] ... columns) {
    if (columns.getClass().getComponentType() == String[].class)
      return printTable(borders, align, (String[][])columns);

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

    return printTable(borders, align, cells, firstColumnOneCell, strings);
  }

  /**
   * Returns a string with a table layout of the provided 2 dimensional array of
   * columns.
   *
   * @param borders Whether to draw borders.
   * @param align Text alignment to be used for data cells.
   * @param columns The 2 dimensional array of columns to print.
   * @return A string with a column layout of the provided 2 dimensional array.
   * @throws NullPointerException If {@code columns} is null.
   */
  public static String printTable(final boolean borders, final Align align, final String[] ... columns) {
    return printTable(borders, align, 1, false, columns);
  }

  /**
   * Returns a string with a table layout of the provided 2 dimensional array of
   * columns.
   *
   * @param borders Whether to draw borders.
   * @param align Text alignment to be used for data cells.
   * @param cells The number of consecutive column elements per cell (except for
   *          the first and single heading column element).
   * @param firstColumnOneCell Whether the first column is to have 1 cell.
   * @param columns The 2 dimensional array of columns to print.
   * @return A string with a column layout of the provided 2 dimensional array.
   * @throws NullPointerException If {@code columns} is null.
   */
  public static String printTable(final boolean borders, final Align align, final int cells, boolean firstColumnOneCell, final String[] ... columns) {
    // Moot if cells == 1 and firstColumnOneCell == true
    if (cells == 1)
      firstColumnOneCell = false;

    final String cellPadding = borders ? " │ " : " ";
    final int cellPaddingLength = cellPadding.length();
    int maxRows = 0;
    // Calculate the cell widths for each column
    final int[] widths = new int[columns.length * cells - (firstColumnOneCell ? 1 : 0)];
    for (int i = 0; i < columns.length; ++i) {
      final int w = i * cells - (i > 0 && firstColumnOneCell ? 1 : 0);
      final String[] rows = columns[i];
      if (rows != null && rows.length > 0) {
        maxRows = Math.max(maxRows, rows.length);
        // First row is the heading, which has only 1 cell
        final int headingWidth = rows[0] == null ? 0 : Strings.lengthPrintable(rows[0]);
        for (int j = 0; j < cells; ++j) {
          final int width = widths[w + j];
          int cellWidth = headingWidth;
          if (i == 0 && firstColumnOneCell)
            ++j;
          else
            cellWidth = (int)Math.ceil((cellWidth - (cellPaddingLength * (cells - 1d))) / cells);

          widths[w + j] = Math.max(width, cellWidth);
        }

        // Following rows have `cells` number of cells
        final int inc = i == 0 && firstColumnOneCell ? 1 : cells;
        for (int k = 1; k < rows.length; k += inc) {
          for (int j = 0; j < inc; ++j) {
            final int width = widths[w + j];
            final int r = j + k;
            widths[w + j] = Math.max(width, r >= rows.length || rows[r] == null ? 0 : Strings.lengthPrintable(rows[r]));
          }
        }
      }
    }

    // Print the rows
    final StringBuilder builder = new StringBuilder();

    // Print the top border
    if (borders) {
      builder.append('╔');
      for (int i = 0; i < columns.length; ++i) {
        final int w = i * cells - (i > 0 && firstColumnOneCell ? 1 : 0);
        if (i > 0)
          builder.append('╦');

        // Calculate the full column width across the # of `cells`
        int fullWidth = -cellPaddingLength;
        for (int j = 0; j < cells; ++j) {
          final int width = widths[w + j];
          fullWidth += width + cellPaddingLength;
          if (i == 0 && firstColumnOneCell)
            break;
        }

        builder.append(Strings.repeat("═", fullWidth + 2));
      }

      builder.append("╗\n║ ");
    }

    // Print the heading row
    String[] rows;
    for (int i = 0; i < columns.length; ++i) {
      final int w = i * cells - (i > 0 && firstColumnOneCell ? 1 : 0);
      rows = columns[i];
      final String row = rows == null || rows[0] == null ? "" : rows[0];
      // Calculate the full column width across the # of `cells`
      int fullWidth = -cellPaddingLength;
      for (int j = 0; j < cells; ++j) {
        final int width = widths[w + j];
        fullWidth += width + cellPaddingLength;
        if (i == 0 && firstColumnOneCell)
          break;
      }

      builder.append(Strings.pad(row, Align.CENTER, fullWidth, ' ', false));
      if (borders)
        builder.append(" ║");

      builder.append(' ');
    }

    // Print the middle border
    if (borders) {
      builder.append("\n╠");
      for (int i = 0; i < columns.length; ++i) {
        final int w = i * cells - (i > 0 && firstColumnOneCell ? 1 : 0);
        if (i > 0)
          builder.append('╬');

        builder.append('═');
        for (int j = 0; j < cells; ++j) {
          if (j > 0)
            builder.append("═╤═");

          final int width = widths[w + j];
          builder.append(Strings.repeat("═", width));
          if (i == 0 && firstColumnOneCell)
            break;
        }

        builder.append('═');
      }

      builder.append("╣");
    }

    // Print the data rows
    for (int k = 1; k < maxRows; k += cells) {
      builder.append('\n');
      if (borders)
        builder.append("║ ");

      for (int i = 0; i < columns.length; ++i) {
        final int w = i * cells - (i > 0 && firstColumnOneCell ? 1 : 0);
        final int r = k > 1 && i == 0 && firstColumnOneCell ? (k + 1) / 2 : k;
        rows = columns[i];
        for (int j = 0; j < cells; ++j) {
          if (j > 0)
            builder.append(cellPadding);

          final String cell = rows == null || r + j >= rows.length || rows[r + j] == null ? "" : rows[r + j];
          final int width = widths[w + j];
          builder.append(Strings.pad(cell, align, width, ' ', false));
          if (i == 0 && firstColumnOneCell)
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
      for (int i = 0; i < columns.length; ++i) {
        final int w = i * cells - (i > 0 && firstColumnOneCell ? 1 : 0);
        if (i > 0)
          builder.append('╩');

        builder.append('═');
        for (int j = 0; j < cells; ++j) {
          if (j > 0)
            builder.append("═╧═");

          final int width = widths[w + j];
          builder.append(Strings.repeat("═", width));
          if (i == 0 && firstColumnOneCell)
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
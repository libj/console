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

import static org.libj.lang.Strings.Align.*;

import java.util.ArrayList;

import org.junit.Test;
import org.libj.lang.Strings;

public class TablesTest {
  @Test
  public void testPrintTable() {
    final double nullThreshold = 0.1; // Set this to 0 for debugging
    final String[] rows1x = new String[8];
    final String[][] columns1x = new String[8][];
    final String[][] columns2x = new String[8][];
    final ArrayList<Object> data = new ArrayList<>();
    columns1x[0] = new String[] {"Latitude", "Miles", "Feet", "Meters"};
    rows1x[0] = columns1x[0][0] + "\n" + columns1x[0][1] + "\n" + columns1x[0][2] + "\n" + columns1x[0][3];
    for (int r = 0; r < 3; ++r) {
      for (int i = 1, j = -1; i < columns1x.length; ++i, j = -1) {
        if ((r + 1) * Math.random() < nullThreshold)
          continue;

        final String[] column = columns1x[i] = new String[(1 + r) + (int)((nullThreshold == 0 ? 1 : Math.random()) * (3 - r))];
        final int lat = i - 1;
        final int meters = (int)(1000 * (111.133d - 0.559d * Math.cos(2 * lat)));
        data.add(rows1x[i] = column[++j] = Math.random() < nullThreshold ? "" : Strings.truncate(String.valueOf(lat), 18, false));

        if (++j < column.length) {
          data.add(column[j] = (r + 1) * Math.random() < nullThreshold ? "" : Strings.truncate(String.valueOf(meters * 0.00062137119224), 12, false));
          rows1x[i] += "\n" + column[j];
        }

        if (++j < column.length) {
          data.add(column[j] = (r + 1) * Math.random() < nullThreshold ? "" : Strings.truncate(String.valueOf(meters * 3.2808), 12, false));
          rows1x[i] += "\n" + column[j];
        }

        if (++j < column.length) {
          data.add(column[j] = (r + 1) * Math.random() < nullThreshold ? "" : Strings.truncate(String.valueOf(meters), 12, false));
          rows1x[i] += "\n" + column[j];
        }
      }

      for (int i = 0; i < columns1x.length; ++i) {
        final String[] column = columns1x[i];
        if (column != null) {
          final String[] column2x = columns2x[i] = new String[1 + (column.length - 1) * 2];
          column2x[0] = column[0];
          System.arraycopy(column, 1, column2x, 1, column.length - 1);
          System.arraycopy(column, 1, column2x, column.length, column.length - 1);
        }
      }

      final Object[] array1x = data.toArray();
      final Object[] array2x = new Object[array1x.length * 2];
      for (int i = 0; i < array1x.length; ++i)
        array2x[i * 2] = array2x[i * 2 + 1] = array1x[i];

      final String[] rows2x = new String[rows1x.length * 2];
      for (int i = 0; i < rows1x.length; ++i)
        rows2x[i * 2] = rows2x[i * 2 + 1] = rows1x[i];

      final Object[][] arrays = new Object[][] {array1x, array2x};
      final String[][][] columns = new String[][][] {columns1x, columns2x};
      final String[][] rows = new String[][] {rows1x, rows2x};
      for (int i = 0; i < arrays.length; ++i) {
        final Object[] array = arrays[i];
        final String[][] column = columns[i];
        final String[] row = rows[i];
        final int cells = i + 1;
        System.out.println(Tables.printTable(array, column[0]));
        System.out.println();
        System.out.println(Tables.printTable(false, LEFT, cells, false, array, column[0]));
        System.out.println();
        System.out.println(Tables.printTable(true, RIGHT, cells, false, array, column[0]));
        System.out.println();
        System.out.println(Tables.printTable(true, LEFT, cells, false, array, column[0]));
        System.out.println();

        System.out.println(Tables.printTable(column));
        System.out.println();
        System.out.println(Tables.printTable(false, LEFT, cells, false, column));
        System.out.println();
        System.out.println(Tables.printTable(true, RIGHT, cells, false, column));
        System.out.println();
        System.out.println(Tables.printTable(true, LEFT, cells, false, column));

        System.out.println(Tables.printTable(row));
        System.out.println();
        System.out.println(Tables.printTable(false, LEFT, cells, false, row));
        System.out.println();
        System.out.println(Tables.printTable(true, RIGHT, cells, false, row));
        System.out.println();
        System.out.println(Tables.printTable(true, LEFT, cells, false, row));
      }
    }
  }

  @Test
  public void testPrintTable1() {
    final String[][] columns = new String[4][4];
    columns[0] = new String[] {"", "a", "a", "b", "b", "c", "c"};
    columns[1] = new String[] {"One", "324", "32", "43982", "4398", "380", "38"};
    columns[2] = new String[] {"Two", "1894", "189", "15", "1", "290", "29"};
    columns[3] = new String[] {"Three", "204", "20", "31", "3", "321", "32"};
    System.out.println(Tables.printTable(true, LEFT, 2, false, columns));
  }

  @Test
  public void testPrintTable2() {
    final String[][] columns = new String[4][4];
    columns[0] = new String[] {"", "a", "b", "c"};
    columns[1] = new String[] {"One", "324", "32", "43982", "4398", "380", "38"};
    columns[2] = new String[] {"Two", "1894", "189", "15", "1", "290", "29"};
    columns[3] = new String[] {"Three", "204", "20", "31", "3", "321", "32"};
    System.out.println(Tables.printTable(true, LEFT, 2, true, columns));
  }
}
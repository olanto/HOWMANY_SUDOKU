/** ********
 * Copyright © 2020 Olanto Foundation Geneva
 *
 * This file is part of mySUDOKU.
 *
 * mySUDOKU is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of
 * the License, or (at your option) any later version.
 *
 * mySUDOKU is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with mySUDOKU.  If not, see <http://www.gnu.org/licenses/>.
 *
 *********
 */
package org.olanto.sudoku.util;

import java.io.*;

/**
 * position of a "symbol in a grid store into bits ... (2 longs) to boost
 * compare, test ...
 */
public class LocalBitSet {

    private final static int ADDRESS_BITS_PER_UNIT = 6;
    private final static int BITS_PER_UNIT = 1 << ADDRESS_BITS_PER_UNIT;
    private final static int BIT_INDEX_MASK = BITS_PER_UNIT - 1;

    /* Used to shift left or right for a partial word mask */
    private static final long WORD_MASK = 0xffffffffffffffffL;

    long A, B;  // this should be called unit[]

    private static long bit(int bitIndex) {
        return 1L << (bitIndex & BIT_INDEX_MASK);
    }

    private static int unitIndex(int bitIndex) {
        return bitIndex >> ADDRESS_BITS_PER_UNIT;
    }

    public LocalBitSet() {
    }

    public final void set(int bitIndex) {
        int unitIndex = unitIndex(bitIndex);
        if (unitIndex == 0) {
            A |= bit(bitIndex);
        } else {
            B |= bit(bitIndex);
        }
    }

    public final void clear() {
        A = 0;
        B = 0;
    }

    public final boolean isEmpty() {
        return (A == 0 && B == 0);
    }

    public final void and(LocalBitSet set) {
        A &= set.A;
        B &= set.B;
    }

    public final void or(LocalBitSet set) {
        A |= set.A;
        B |= set.B;

    }

    public void andNot(LocalBitSet set) {
        A &= ~set.A;
        B &= ~set.B;
    }

    public static final boolean ok(LocalBitSet x, LocalBitSet y) {
        return ((x.A & y.A) == 0 && (x.B & y.B) == 0);
    }

}

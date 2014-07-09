package com.iheart.interleaver;

import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
* Provides an Iterable across a Collection of Iterators of elements, interleaving elements from each input.
*/
public class InterleavingIterable<T> implements Iterable<T> {

    private final Iterable<? extends Iterator<T>> columns;
    private Iterator<T> row;
    private Iterator<? extends Iterator<T>> columnIterator;
    private final List<T> rowBuffer;

    public InterleavingIterable(final Collection<? extends Iterator<T>> input) {
        this.columns = input;
        this.row = Iterators.emptyIterator();
        this.columnIterator = columns.iterator();
        this.rowBuffer = new ArrayList<T>(Iterables.size(input));
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            @Override
            public boolean hasNext() {
                if (!row.hasNext()) {
                    row = getNextSlice();
                }

                return row.hasNext();
            }

            @Override
            public T next() {
                return row.next();
            }

            @Override
            public void remove() {
                row.remove();
            }

            /**
             * @return an iterator of the head elements of each input.
             */
            private Iterator<T> getNextSlice() {
                //Re-use existing list to avoid object allocation.
                rowBuffer.clear();

                //Visit the head of each input column and consume it if not empty.
                while (columnIterator.hasNext()) {
                    Iterator<T> column = columnIterator.next();

                    //Some input iterables may be emptied before others.
                    if (column.hasNext()) {
                        //Populate buffer of head elements for return
                        rowBuffer.add(column.next());
                    }
                }

                // Set to the next row.
                columnIterator = columns.iterator();

                // Return the current row.
                return rowBuffer.iterator();
            }
        };
    }
}

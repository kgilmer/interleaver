package com.iheart.interleaver;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Iterator;

/**
 * Provides an output Iterable that interleaves from input Iterables.
 * @deprecated Use InterleavedIterator instead.
 * Example: [[a1, a2, a3] [b1] [] [d1, d2]] -> [a1, b1, d1, a2, d2, a3]
 * 
 */
@Deprecated
public class Interleaver {

    private Interleaver() {
    }

    /**
     * Interleave the supplied iterables.  
     *  
     * @param input
     * @return
     */
    public static <T> Iterable<T> fromIterables(Iterable<? extends Iterable<T>> input) {

        return new InterleavingIterable<T>(
            Lists.newArrayList(
                Iterables.transform(input, 
                    new Function<Iterable<T>, Iterator<T>>() {

                        @Override
                        public Iterator<T> apply(@Nullable Iterable<T> input) {
                            return input.iterator();
                        }
        })));
    }

    /**
     * Interleave the supplied iterators. 
     *  
     * @param input
     * @return
     */
    public static <T> Iterable<T> fromIterators(Collection<? extends Iterator<T>> input) {
        return new InterleavingIterable<T>(input);
    }
}

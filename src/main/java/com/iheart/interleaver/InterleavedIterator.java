package com.iheart.interleaver;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.Iterator;

/**
 * Provides an output Iterable that interleaves from input Iterables.
 * 
 * Example: [[a1, a2, a3] [b1] [] [d1, d2]] -> [a1, b1, d1, a2, d2, a3]
 * 
 */
public class InterleavedIterator {

    private InterleavedIterator() {
    }

    /**
     * Interleave the supplied iterables as an interator.
     *  
     * @param input
     * @return
     */
    public static <T> Iterator<T> fromIterables(Iterable<? extends Iterable<T>> input) {

        InterleavingIterable<T> ii = new InterleavingIterable<T>(
                Lists.newArrayList(
                        Iterables.transform(input,
                                new Function<Iterable<T>, Iterator<T>>() {

                                    @Override
                                    public Iterator<T> apply(Iterable<T> input) {
                                        return input.iterator();
                                    }
                                })));

        return ii.iterator();

    }

    /**
     * Interleave the supplied iterators. 
     *  
     * @param input
     * @return
     */
    public static <T> Iterator<T> fromIterators(Collection<? extends Iterator<T>> input) {
        return (new InterleavingIterable<T>(input)).iterator();
    }

}

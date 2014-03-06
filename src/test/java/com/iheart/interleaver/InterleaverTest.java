package com.iheart.interleaver;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.junit.Test;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

@SuppressWarnings("unchecked")
public class InterleaverTest {

    @Test
    public void testEmptySubIterator4() {

        List<String> l1 = Arrays.asList("a1", "a2", "a3");
        List<String> l2 = Arrays.asList();
        List<String> l3 = Arrays.asList("c1");

        List<String> expectedOrder = Arrays.asList("a1", "c1", "a2", "a3");

        Iterator<String> expectedElement = expectedOrder.iterator();

        int correctIterationCount = l1.size() + l2.size() + l3.size();
        int iterationCount = 0;

        for (String e : Interleaver.fromIterables(Arrays.asList(l1, l2, l3))) {
            assertTrue("Correct element is returned.", expectedElement.next().equals(e));
            iterationCount++;
        }

        assertTrue("Correct number of elements iterated over", iterationCount == correctIterationCount);
    }

    @Test
    public void testEmptySubIterator() {
        List<String> l1 = Arrays.asList("a1", "a2", "a3");
        List<String> l2 = new ArrayList<String>();

        List<String> expectedOrder = Arrays.asList("a1", "a2", "a3");

        Iterator<String> expectedElement = expectedOrder.iterator();

        int correctIterationCount = l1.size() + l2.size();
        int iterationCount = 0;

        for (String e : Interleaver.fromIterables(Arrays.asList(l1, l2))) {
            assertTrue("Correct element is returned.", expectedElement.next().equals(e));
            iterationCount++;
        }

        assertTrue("Correct number of elements iterated over", iterationCount == correctIterationCount);
    }

    @Test
    public void testEmptySubIterator2() {

        List<String> l1 = Arrays.asList("a1", "a2", "a3");
        List<String> l2 = Arrays.asList();
        List<String> l3 = Arrays.asList("c1", "c2", "c3");

        List<String> expectedOrder = Arrays.asList("a1", "c1", "a2", "c2", "a3", "c3");

        Iterator<String> expectedElement = expectedOrder.iterator();

        int correctIterationCount = l1.size() + l2.size() + l3.size();
        int iterationCount = 0;

        for (String e : Interleaver.fromIterables(Arrays.asList(l1, l2, l3))) {
            assertTrue("Correct element is returned.", expectedElement.next().equals(e));
            iterationCount++;
        }

        assertTrue("Correct number of elements iterated over", iterationCount == correctIterationCount);
    }

    @Test
    public void testEmptySubIterator3() {
        List<String> l1 = new ArrayList<String>();
        List<String> l2 = Arrays.asList("a1", "a2", "a3");

        List<String> expectedOrder = Arrays.asList("a1", "a2", "a3");

        Iterator<String> expectedElement = expectedOrder.iterator();

        int correctIterationCount = l1.size() + l2.size();
        int iterationCount = 0;

        for (String e : Interleaver.fromIterables(Arrays.asList(l1, l2))) {
            assertTrue("Correct element is returned.", expectedElement.next().equals(e));
            iterationCount++;
        }

        assertTrue("Correct number of elements iterated over", iterationCount == correctIterationCount);
    }

    @Test
    public void testIterleaveFullMatrix() {

        List<String> l1 = Arrays.asList("a1", "a2", "a3");
        List<String> l2 = Arrays.asList("b1", "b2", "b3");
        List<String> l3 = Arrays.asList("c1", "c2", "c3");

        List<String> expectedOrder = Arrays.asList("a1", "b1", "c1", "a2", "b2", "c2", "a3", "b3", "c3");

        Iterator<String> expectedElement = expectedOrder.iterator();

        int correctIterationCount = l1.size() + l2.size() + l3.size();
        int iterationCount = 0;

        for (String e : Interleaver.fromIterables(Arrays.asList(l1, l2, l3))) {
            assertTrue("Correct element is returned.", expectedElement.next().equals(e));
            iterationCount++;
        }

        assertTrue("Correct number of elements iterated over", iterationCount == correctIterationCount);
    }

    @Test
    public void testIterleaveSparseMatrix() {

        List<String> l1 = Arrays.asList("a1", "a2", "a3");
        List<String> l2 = Arrays.asList("b1");
        List<String> l3 = Arrays.asList("c1", "c2", "c3", "c4");

        List<String> expectedOrder = Arrays.asList("a1", "b1", "c1", "a2", "c2", "a3", "c3", "c4");

        Iterator<String> expectedElement = expectedOrder.iterator();

        int correctIterationCount = l1.size() + l2.size() + l3.size();
        int iterationCount = 0;

        for (String e : Interleaver.fromIterables(Arrays.asList(l1, l2, l3))) {
            assertTrue("Correct element is returned.", expectedElement.next().equals(e));
            iterationCount++;
        }

        assertTrue("Correct number of elements iterated over", iterationCount == correctIterationCount);
    }

    @Test
    public void testIterleaveSingleIterable() {

        List<String> l1 = Arrays.asList("a1", "a2", "a3");

        List<String> expectedOrder = Arrays.asList("a1", "a2", "a3");

        Iterator<String> expectedElement = expectedOrder.iterator();

        int correctIterationCount = l1.size();
        int iterationCount = 0;

        for (String e : Interleaver.fromIterables(Arrays.asList(l1))) {
            assertTrue("Correct element is returned.", expectedElement.next().equals(e));
            iterationCount++;
        }

        assertTrue("Correct number of elements iterated over", iterationCount == correctIterationCount);
    }

    @Test
    public void testIterleaveEmptyIterable() {

        List<String> l1 = Collections.emptyList();

        assertFalse("Iterleaving empty list returns no elements.", Interleaver.fromIterables(Arrays.asList(l1)).iterator().hasNext());

    }

    @Test
    public void testIterleaveList() {
        List<Iterator<String>> lis = new ArrayList<Iterator<String>>();

        lis.add(Arrays.asList("a1", "a2", "a3").iterator());
        lis.add(Arrays.asList("b1", "b2", "b3").iterator());
        lis.add(Arrays.asList("c1", "c2", "c3").iterator());

        List<String> expectedOrder = Arrays.asList("a1", "b1", "c1", "a2", "b2", "c2", "a3", "b3", "c3");

        Iterator<String> expectedElement = expectedOrder.iterator();

        int correctIterationCount = 9;
        int iterationCount = 0;

        for (String e : Interleaver.fromIterators(lis)) {
            assertTrue("Correct element is returned.", expectedElement.next().equals(e));
            iterationCount++;
        }

        assertTrue("Correct number of elements iterated over", iterationCount == correctIterationCount);
    }

    @Test
    public void testRandomInputs() {

        for (int il = 0; il < 20; ++il) {
            Random rng = new Random();
            int inputListSize = rng.nextInt(20) + 1;

            List<List<String>> inputLists = new ArrayList<List<String>>(inputListSize);
            for (int i = 0; i < inputListSize; ++i) {
                inputLists.add(new ArrayList<String>());
            }

            int elements = rng.nextInt(inputListSize * 20);
            int listIndex = 0;
            for (int i = 0; i < elements; ++i) {
                if (listIndex >= inputLists.size()) {
                    listIndex = 0;
                }

                if (rng.nextBoolean()) {
                    String v = "element-" + i;
                    inputLists.get(listIndex).add(v);
                }

                listIndex++;
            }

            Set<String> interleavedInputs = Sets.newHashSet(Interleaver.fromIterables(inputLists));
            Set<String> flattenedInputs = Sets.newHashSet(Iterables.concat(inputLists));

            assertTrue("Interleave contains same items as original", interleavedInputs.equals(flattenedInputs));
        }
    }

}

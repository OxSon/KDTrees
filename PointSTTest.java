package kdTree;

import edu.princeton.cs.algs4.Point2D;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class PointSTTest {
    private PointST<Integer> st;
    private static Random rand = new Random();

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        st = new PointST<Integer>();
    }

    @org.junit.jupiter.api.Test
    void isEmptyAll() {
        isEmptyFalse();

        st = new PointST<>();
        isEmptyTrue();

        st = new PointST<>();
        isEmptyTrueThenFalse();
    }

    void isEmptyTrue() {
        assertTrue(st.isEmpty());
    }

    void isEmptyFalse() {
        st.put(new Point2D(0, 0), 0);
        assertFalse(st.isEmpty());
    }

    void isEmptyTrueThenFalse() {
        assertTrue(st.isEmpty());

        st.put(new Point2D(0, 0), 0);
        assertFalse(st.isEmpty());
    }

    @org.junit.jupiter.api.Test
    void sizeAll() {
        sizeEmpty();

        st = new PointST<>();
        sizeSmall();

        st = new PointST<>();
        sizeLarge();
    }

    void sizeEmpty() {
        assertEquals(0, st.size());
    }

    void sizeSmall() {
        for (int i = 0; i < 5; i++) {
            st.put(new Point2D(Math.random(), Math.random()), rand.nextInt());
        }

        assertEquals(5, st.size());
    }

    void sizeLarge() {
        int size = rand.nextInt(9000) + 9000;

        for(int i = 0 ; i < size; i++) {
            st.put(new Point2D(Math.random(), Math.random()), rand.nextInt());
        }

        assertEquals(size, st.size());
    }

    @org.junit.jupiter.api.Test
    void put() {
        putWasEmpty();

        st = new PointST<>();
        putNotEmpty();
    }

    void putWasEmpty() {
        fail("Unimplemented");
    }

    void putNotEmpty() {
        fail("Unimplemented");
    }

    @org.junit.jupiter.api.Test
    void get() {
        getEmpty();

        st = new PointST<>();
        getNotEmpty();
    }

    void getEmpty() {
        fail("Unimplemented");
    }

    void getNotEmpty() {
        fail("Unimplemented");
    }

    @org.junit.jupiter.api.Test
    void contains() {
        containsEmpty();

        fail("Unimplemented");
        containsTrue();

        fail("Unimplemented");
        containsFalse();
    }

    void containsEmpty() {
        fail("Unimplemented");
    }

    void containsTrue() {
        fail("Unimplemented");
    }

    void containsFalse() {
        fail("Unimplemented");
    }

    @org.junit.jupiter.api.Test
    void points() {
        pointsEmpty();

        st = new PointST<>();
        pointsSmall();

        st = new PointST<>();
        pointsLarge();
    }

    void pointsEmpty() {
        fail("Unimplemented");
    }

    void pointsSmall() {
        fail("Unimplemented");
    }

    void pointsLarge() {
        fail("Unimplemented");
    }

    @org.junit.jupiter.api.Test
    void range() {
        fail("Unimplemented");
    }

    @org.junit.jupiter.api.Test
    void nearest() {
        fail("Unimplemented");
    }
}
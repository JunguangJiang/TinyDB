package db.file;

import db.file.heap.HeapPageId;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClockTest {

    @Test
    public void addPageId() {
        Clock clock = new Clock();
        PageId pageIds[] = new HeapPageId[10];
        for (int i=0; i<10; i++) {
            pageIds[i] = new HeapPageId(1,i);
        }
        clock.addPageId(pageIds[0]);
        clock.addPageId(pageIds[1]);
        assert clock.evict() == pageIds[0];
        clock.addPageId(pageIds[8]);
        clock.addPageId(pageIds[0]);
        clock.addPageId(pageIds[3]);
        clock.addPageId(pageIds[1]);

        assert clock.evict() == pageIds[8];

    }

    @Test
    public void evict() {
    }
}
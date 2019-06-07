package db.file;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Clock evicting algorithm
 */
class Clock {
    private LinkedList<PageId> queue;
    private HashMap<PageId, Boolean> recentlyUsed;

    Clock() {
        this.queue = new LinkedList<>();
        this.recentlyUsed = new HashMap<>();
    }

    /**
     * add a new pageId to the clock
     * @param pageId
     */
    void addPageId(PageId pageId){
        if (!recentlyUsed.containsKey(pageId)) {
            queue.addLast(pageId);
            recentlyUsed.put(pageId, false);
        } else {
            this.recentlyUsed.put(pageId, true);
        }
    }

    /**
     * evict a least recently used pageId from clock
     * @return
     */
    PageId evict() {
        while (queue.size() > 0){
            PageId pageId = queue.removeFirst();
            Boolean used = recentlyUsed.remove(pageId);
            assert used != null;
            if (used) {
                queue.addLast(pageId);
                recentlyUsed.put(pageId,false);
            } else {
                return pageId;
            }
        }
        return null;
    }
}


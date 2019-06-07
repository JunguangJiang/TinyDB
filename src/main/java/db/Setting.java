package db;

/**
 * Basic setting for the database
 */
public class Setting {
    public final static boolean isBTree = true;

    public final static long MAX_MEMORY_BYTES_FOR_FILTER_BUFFER = 4 * 1024 * 1024; // TupleBuffer in BufferedFilter use at most 4MB memory
    public final static long MAX_MEMORY_BYTES_FOR_UPDATE = 4 * 1024 * 1024;
    public final static long MAX_MEMORY_BYTES_FOR_DELETE = 4 * 1024 * 1024;
    /*
    * Hash Join will use MAX_MEMORY_BYTES_FOR_JOIN_HASH_MAP+MAX_MEMORY_BYTES_FOR_JOIN_BUFFER Bytes memory
    * Nested-Loop Join will use MAX_MEMORY_BYTES_FOR_JOIN_BUFFER Bytes memory
    * */
    public final static long MAX_MEMORY_BYTES_FOR_JOIN_HASH_MAP = 4 * 1024 * 1024; // HashMaps in Hash-Join use at most 4MB memory
    public final static long MAX_MEMORY_BYTES_FOR_JOIN_BUFFER = 4 * 1024 * 1024; //TupleBuffer in Join use at most 4MB memory

    public final static long BUFFER_POOL_PAGE_NUMBER = 1024 * 16; // Buffer Pool max bytes is 4KB * 1024 * 16 = 64MB
}

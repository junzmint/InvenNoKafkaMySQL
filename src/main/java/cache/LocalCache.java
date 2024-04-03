package cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class LocalCache {
    public static final int NEW_SKU = -1;
    private final LoadingCache<String, Integer> cache;

    public LocalCache() {
        CacheLoader<String, Integer> loader;
        loader = new CacheLoader<String, Integer>() {
            @Override
            public Integer load(String key) {
                return 1;
            }
        };

        this.cache = CacheBuilder.newBuilder().maximumSize(1).build(loader);
    }

    public int update(String key, Integer value) {
        this.cache.put(key, value);
        return 1;
    }

    public int get(String key) {
        try {
            return this.cache.getIfPresent(key);
        } catch (Exception e) {
            return NEW_SKU;
        }
    }
}

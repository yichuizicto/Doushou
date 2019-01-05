package com.yichuizi.tiktik.media;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;

import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.FileDataSourceFactory;
import com.google.android.exoplayer2.upstream.cache.CacheDataSinkFactory;
import com.google.android.exoplayer2.upstream.cache.CacheDataSource;
import com.google.android.exoplayer2.upstream.cache.CacheDataSourceFactory;
import com.google.android.exoplayer2.upstream.cache.CacheUtil;
import com.google.android.exoplayer2.upstream.cache.LeastRecentlyUsedCacheEvictor;
import com.google.android.exoplayer2.upstream.cache.SimpleCache;

import java.io.File;
import java.io.IOException;

/**
 * 作者：一锤子打扫卫生的 on 2019/1/3 14:49
 * 描述：
 */
public class PlayerCacheUtil implements CacheDataSource.EventListener {
    private final static int PRELOAD_SIZE = 200 * 1024;
    private static PlayerCacheUtil mPlayerCacheUtil;
    private DataSource.Factory dataSourceFactory;
    private SimpleCache simpleCache;

    public static PlayerCacheUtil getDefault(Context context) {
        if (mPlayerCacheUtil == null) {
            synchronized (PlayerCacheUtil.class) {
                if (mPlayerCacheUtil == null) {
                    mPlayerCacheUtil = new PlayerCacheUtil(context);
                }
            }
        }
        return mPlayerCacheUtil;
    }

    public PlayerCacheUtil(Context context) {
        File cacheFile = new File(context.getExternalCacheDir(), "videos");
        simpleCache = new SimpleCache(cacheFile, new LeastRecentlyUsedCacheEvictor(256 * 1024 * 1024));
        dataSourceFactory = new CacheDataSourceFactory(simpleCache, new DefaultDataSourceFactory(context, "yichuizi", new DefaultBandwidthMeter()),
                new FileDataSourceFactory(), new CacheDataSinkFactory(simpleCache, Long.MAX_VALUE), 0, null);

    }


    public DataSource.Factory getDataSourceFactory() {
        return dataSourceFactory;
    }


    public void preload(String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        Uri uri = Uri.parse(url);
        if (simpleCache.isCached(uri.toString(), 0, PRELOAD_SIZE)) {
            return;
        }

        DataSpec dataSpec = new DataSpec(uri, 0, PRELOAD_SIZE, null);
        CacheUtil.CachingCounters counters = new CacheUtil.CachingCounters();
        try {
            CacheUtil.cache(dataSpec, simpleCache, dataSourceFactory.createDataSource(), counters, null);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCachedBytesRead(long cacheSizeBytes, long cachedBytesRead) {
    }

    @Override
    public void onCacheIgnored(int reason) {

    }

}

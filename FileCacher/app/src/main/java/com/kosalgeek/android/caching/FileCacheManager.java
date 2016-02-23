package com.kosalgeek.android.caching;

import android.content.Context;

import java.io.File;

public class FileCacheManager {
    private final String LOG = this.getClass().getName();

    private Context context;

    /**
     * create an instance of FilCacheManager
     * @param context context
     */
    public FileCacheManager(Context context){
        this.context = context;
    }

    /**
     * delete the whole cache files from the cache folder
     */
    public void deleteAllCaches(){
        File filePath = context.getFilesDir();
        if (filePath.isDirectory())
        {
            String[] files = filePath.list();
            for(String file : files){
                context.deleteFile(file);
            }
        }
    }

    /**
     * get a list of cache file names
     * @return array of String of file names
     */
    public String[] getCacheFileNames(){
        File filePath = context.getFilesDir();

        String[] files = null;

        if (filePath.isDirectory())
        {
            files = filePath.list();
        }
        return files;
    }


    /**
     * Check whether the cache file exists or not.
     * @return 'true' if cache exists or 'false' if cache does not exists.
     */
    public boolean hasCacheFiles(){
        File filePath = context.getFilesDir();

        if (filePath.isDirectory())
        {
            if(filePath.list().length > 0){
                return true;
            }
        }
        return false;
    }

    /**
     * get cache path
     *
     * @return String of cache path
     */
    public String getPath(){
        return context.getFilesDir().getPath();
    }

}

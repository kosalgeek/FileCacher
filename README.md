# FileCacher
The FileCacher is a simple library for Android to cache your object (any object type, e.g. ``int``, ``String``, ``ArrayList<YourClass>``, ``HashMap<int, ArrayList<YourClass>>``, etc) to store in a file in the internal storage. It is a context based cache and the file creation mode is MODE_PRIVATE. That is, you can read cache within an app that you write. It won't exponse your cache to other apps to used.

You can however read and write the same file in different Activities. Because of this, you have to be careful about the file name. The same file name that have different object type can cause your app not responsed (ANR). To void ANR, make sure you catch ``ClassCastException`` during the reading cache, e.g ``readCache()`` method. See the full code of ``readCache()`` below.

# SETUP
1. Download FileCacher.jar
2. Copy it and paste into your Android project at *App* > *libs* > right click on the jar file and choose *Add as Library*

# USAGE
You can simply cache an object and read it back in 3 simple steps.

### Step 1: Create ``FileCacher`` object by passing a context and a unique file name
Because you can cache any object type, it is ideally important to specify a type parameter when creating an instance. In the example below, it can take a ``String`` object. However, it is not limit to String, you can store any other object type, e.g. ``ArrayList<YouClass>`` or ``HashMap<int, ArrayList<YourClass>>``.
```java
FileCacher<String> stringCacher = new FileCacher<>(MainActivity.this, "sometext.txt");
```

### Step 2: Call ``writeCache()`` method
That's it. You are ready to ``writeCache()`` in the second line. It should ask you catch an ``IOException``.
```java
try {
  stringCacher.writeCache("Just a sample text while you can cache more up to your internal storage.");
} catch (IOException e) {
  e.printStackTrace();
}
```

### Step 3: Call ``readCache()`` method
After you write, now it's time to read it. However, it is neccesary to check wether you have already written the cache or not. To check it, you can use ``hasCache()`` method. The ``readCache()`` ask you catch an ``IOException``. Because you may accidently use the same file name in different Activities and write different object type, you should additionally catch ``ClassCastException``. 
```java
if(stringCacher.hasCache()){
  try {
  stringCacher.readCache();
  } catch (IOException e) {
    e.printStackTrace();
  } catch (ClassCastException e){
    e.printStackTrace();
  }
}
```

### Additional Method
#### Method ``clearCache()`` to clear cache
```java
try {
  stringCacher.clearCache();
  } catch (IOException e) {
  e.printStackTrace();
}
```

#### Method ``hasCache()`` returns ``true`` if has cache or ``false`` if not.
```java 
if(stringCacher.hasCache()){
  //read cache
}
```

#### Method ``getFilePath()`` returns a String of a file path.
```java
Log.d("MainActivity", stringCacher.getFilePath());
```

#### Method ``getSize()`` returns long integer of a file size. If returns -1, it means the file does not exist.
```java
Log.d("MainActivity", "" + stringCacher.getSize());
```
# FileCacheManager
This is an additional file to manage all caches. It can delete all caches, retrieve the cache file names, and check if there is a cache. 

*Below is a snippet code to retrieve cache file names.*
```java
FileCacheManager manager = new FileCacheManager(MainActivity.this);
manager.getPath();

if(manager.hasCacheFiles()){
  String[] files = manager.getCacheFileNames();
  for(String f : files){
    Log.d("MainActivity", f);
  }
}
```

#### Method ``deleteAllCaches()`` You can use this method to delete all caches within your app. It won't affect other app caches. However, once you delete it, it's gone.
```java
manager.deleteAllCaches();
```




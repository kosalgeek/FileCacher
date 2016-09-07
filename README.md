# FileCacher
The FileCacher is a simple library for Android to cache your object (any object type, e.g. ``int``, ``String``, ``ArrayList<YourClass>``, ``HashMap<int, ArrayList<YourClass>>``, etc) to store in a file in the internal storage. It is a context based cache and the file creation mode is MODE_PRIVATE. That is, you can read cache within an app that you write. It won't exponse your cache to other apps to used.

You can however read and write the same file in different Activities. Because of this, you have to be careful about the file name. The same file name that have different object type can cause your app not responsed (ANR). To void ANR, make sure you catch ``ClassCastException`` during the reading cache, e.g ``readCache()`` method. See the full code of ``readCache()`` below.

### You can watch tutorial video of FileCacher at https://www.youtube.com/watch?v=CSBOBbw5rXc

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

### Additional Methods
#### Method ``appendOrWrite()`` to append an object if file exists, or create a new file and write an object to it.
```java
try {
  stringCacher.appendOrWrite("add more text.");
} catch (IOException e) {
  e.printStackTrace();
}
```

#### Method ``getAllCaches()`` return a ``List`` of all the caches.
```java
try {
  List<String> list = stringCacher.getAllCaches();
  for(String text : list){
    //do something with text
  }
} catch (IOException e) {
  e.printStackTrace();
}
```

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

# Follow Me
 * Get more free source code at https://github.com/kosalgeek
 * Watch my video tutorials at my YouTube channel https://youtube.com/user/oumsaokosal
 * Like my Facebook Page at https://facebook.com/kosalgeek
 * Follow me on Twitter https://twitter.com/okosal
 * Get more tutorials at http://www.kosalgeek.com and http://www.top12review.com
 
# Donation
#### If you think this library have saved your life, please support me by donating a few bucks, just for my coffee :)
[![Donate](https://img.shields.io/badge/Donate-PayPal-green.svg)](
https://www.paypal.com/cgi-bin/webscr?cmd=_donations&business=oumsaokosal01%40gmail%2ecom&lc=US&currency_code=USD&bn=PP%2dDonationsBF%3abtn_donateCC_LG%2egif%3aNonHosted)

<input type="image" src="https://www.paypalobjects.com/en_US/i/btn/btn_donateCC_LG.gif" border="0" name="submit" alt="PayPal - The safer, easier way to pay online!">
<img alt="" border="0" src="https://www.paypalobjects.com/en_US/i/scr/pixel.gif" width="1" height="1">
</form>

# LICENSE

(The MIT License)
Copyright (c) 2016 KosalGeek. (kosalgeek at gmail dot com)

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the 'Software'), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED 'AS IS', WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.



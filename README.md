
*Note that version chronicle-map-2.1.10 is the last official working release under the apache license.

*We can help you get Chronicle up and running in your organisation, we suggest you invite us in for
consultancy, charged on an ad-hoc basis, we can discuss the best options tailored to your individual
requirements. - [Contact Us](http://chronicle.software/support/)*

*Or you may already be using Chronicle and just want some help - [find out more..](http://chronicle.software/support/)*

# Chronicle Map

A low latency replicated Key Value Store across your network, with eventual consistency, persistence and performance.
![Chronicle Map](http://chronicle.software/wp-content/uploads/2014/07/ChronicleMap_200px.png)

#### Maven Artifact Download
```xml
<dependency>                                   
  <groupId>net.openhft</groupId>
  <artifactId>chronicle-map</artifactId>
  <version><!--replace with the latest version--></version>
</dependency>
```
Click here to get the [Latest Version Number](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22net.openhft%22%20AND%20a%3A%22chronicle-map%22) 

#### Contents
* [Overview](https://github.com/OpenHFT/Chronicle-Map#overview)
* [Should I use Chronicle Queue or Chronicle Map](https://github.com/OpenHFT/Chronicle-Map#should-i-use-chroniclequeue-or-chroniclemap)
* [What is the difference between SharedHashMap and Chronicle Map](https://github.com/OpenHFT/Chronicle-Map#what-is-the-difference-between-sharedhashmap-and-chroniclemap)
* [JavaDoc](http://openhft.github.io/Chronicle-Map/apidocs)
* [Getting Started Guide](https://github.com/OpenHFT/Chronicle-Map#getting-started)
 *  [Simple Construction](https://github.com/OpenHFT/Chronicle-Map#simple-construction)
 *   [Maven Download](https://github.com/OpenHFT/Chronicle-Map#maven-artifact-download-1)
 *   [Snapshot Download](https://github.com/OpenHFT/Chronicle-Map#maven-snapshot-download)
 *   [Key Value Object Types](https://github.com/OpenHFT/Chronicle-Map#key-value-object-types)
 *   [Off Heap and How to improve performance](https://github.com/OpenHFT/Chronicle-Map#off-heap-storage-and-how-using-a-proxy-object-can-improve-performance)
 *   [Sharing Data Between Two or More Maps](https://github.com/OpenHFT/Chronicle-Map#sharing-data-between-two-or-more-maps)
 *   [Entries](https://github.com/OpenHFT/Chronicle-Map#entries)
 *   [Size of Space Reserved on Disk](https://github.com/OpenHFT/Chronicle-Map#size-of-space-reserved-on-disk)
 *   [Chronicle Map Interface](https://github.com/OpenHFT/Chronicle-Map#chronicle-map-interface)
* [Serialization](https://github.com/OpenHFT/Chronicle-Map#serialization)
  *   [Simple Types](https://github.com/OpenHFT/Chronicle-Map#simple-types)
  *   [Complex Types](https://github.com/OpenHFT/Chronicle-Map#complex-types)
  *   [Import/Export entries as JSON](https://github.com/OpenHFT/Chronicle-Map#importexport-entries-as-json)
* [Close](https://github.com/OpenHFT/Chronicle-Map#close)
* [TCP / UDP Replication](https://github.com/OpenHFT/Chronicle-Map#tcp--udp-replication)
 * [TCP / UDP Background.](https://github.com/OpenHFT/Chronicle-Map#tcp--udp-background)
 *   [How to setup UDP Replication](https://github.com/OpenHFT/Chronicle-Map#how-to-setup-udp-replication)
 *  [TCP/IP Throttling](https://github.com/OpenHFT/Chronicle-Map#tcpip--throttling)
 *  [Replication How it works](https://github.com/OpenHFT/Chronicle-Map#replication-how-it-works)
 *  [Multiple Processes on the same server with Replication](https://github.com/OpenHFT/Chronicle-Map#multiple-processes-on-the-same-server-with-replication)
 *  [Identifier for Replication](https://github.com/OpenHFT/Chronicle-Map#identifier-for-replication)
 *  [Bootstrapping](https://github.com/OpenHFT/Chronicle-Map#bootstrapping)
 *    [Identifier](https://github.com/OpenHFT/Chronicle-Map#identifier)
 * [Port](https://github.com/OpenHFT/Chronicle-Map#port)
 * [Heart Beat Interval](https://github.com/OpenHFT/Chronicle-Map#heart-beat-interval)
* [Multi Chronicle Maps - Network Distributed](https://github.com/OpenHFT/Chronicle-Map#multiple-chronicle-maps---network-distributed)
* [Stateless Client](https://github.com/OpenHFT/Chronicle-Map#stateless-client)
* [How to speed up the Chronicle Map Stateless Client](https://github.com/OpenHFT/Chronicle-Map#how-to-speed-up-the-chronicle-map-stateless-client)
* [Questions/Answers](https://github.com/OpenHFT/Chronicle-Map#questions-and-answers)

#### Miscellaneous

 * [Known Issues](https://github.com/OpenHFT/Chronicle-Map#known-issues)
 * [Stackoverflow](http://stackoverflow.com/tags/chronicle/info)
 * [Development Tasks - JIRA] (https://higherfrequencytrading.atlassian.net/browse/HCOLL)
 * [Use Case Which include Chronicle Map] (http://chronicle.software/products/chronicle-engine/)
 
#### Examples

 * [Hello World - A map which stores data off heap](https://github.com/OpenHFT/Chronicle-Map/blob/master/README.md#example--simple-hello-world)
 * [Sharing the map between two ( or more ) processes on the same computer](https://github.com/OpenHFT/Chronicle-Map/blob/master/README.md#example--sharing-the-map-on-two--or-more--processes-on-the-same-machine)
 * [Replicating data between process on different servers with TCP/IP Replication](https://github.com/OpenHFT/Chronicle-Map/blob/master/README.md#example--replicating-data-between-process-on-different-servers-via-tcp)
 * [Replicating data between process on different servers with UDP] (https://github.com/OpenHFT/Chronicle-Map/blob/master/README.md#example--replicating-data-between-process-on-different-servers-using-udp)
 *  [Creating a Chronicle Set and adding data to it](https://github.com/OpenHFT/Chronicle-Map/blob/master/README.md#example--creating-a-chronicle-set-and-adding-data-to-it)

#### Performance Topics

* [Chronicle Map with Large Data ](https://github.com/OpenHFT/Chronicle-Map#chronicle-map-with-large-data)
* [Lock Contention] (https://github.com/OpenHFT/Chronicle-Map/blob/master/README.md#lock-contention)
* [Better to use small keys](https://github.com/OpenHFT/Chronicle-Map#better-to-use-small-keys)
* [ConcurrentHashMap v ChronicleMap](https://github.com/OpenHFT/Chronicle-Map#concurrenthashmap-v-chroniclemap)

### Overview
Chronicle Map implements the interface `java.util.concurrent.ConcurrentMap`, however unlike the standard
jdk implementations, ChronicleMap is both persistent and able to share your entries accross processes:

![](http://chronicle.software/wp-content/uploads/2014/07/Chronicle-Map-diagram_04.jpg)

## When to use HashMap, ConcurrentHashMap and ChronicleMap
#### When to use HashMap
If you compare `HashMap`, `ConcurrentHashMap` and `ChronicleMap`, most of the maps in your system
are likely to be HashMap.  This is because `HashMap` is lightweight.  Synchronized HashMap works
well for lightly contended use cases.  By contention we mean, how many threads on average are trying
to use a Map.  One reason you can't have many contended resources, is that you only have so many
CPUs and they can only be accessing a limited resources at once (ideally no more than one or two
per thread at a time).

####  When to use ConcurrentHashMap
`ConcurrentHashMap` scales very well when highly contended.  It uses more memory but if you only
have a few of them, this doesn't matter.  They have higher throughput than the other two solutions,
but it does create the highest amount of garbage.  If garbage pressure is an issue for you (for example in 
a low latency evironment), you should consider `ChronicleMap`.

####  When to use ChronicleMap
If you have;
* lots of small key-values
* you want to minimise garbage produced, and medium lived objects.
* you need to share data between JVMs
* you need persistence

#### Should I use ChronicleQueue or ChronicleMap
ChronicleQueue is 'lossless', designed to send every update. If your network can't do this something has
to give. You could compress the data but at some point you have to work within the limits of your
hardware or get more hardware. ChronicleMap on the other hand sends the latest value only.
This will naturally drop updates and is a more natural choice for low bandwidth connections.

#### What is the difference between [SharedHashMap](https://github.com/OpenHFT/HugeCollections) and ChronicleMap
SharedHashMap was the old name of what is now ChronicleMap. Since the last release of SharedHashMap
 we have added a lot of new features to ChronicleMap, most of these are listed in this readme.

## Getting Started

#### Tutorial 1 - Creating an instance of Chronicle Map
[![ScreenShot](http://chronicle.software/wp-content/uploads/2014/09/Screen-Shot-2014-10-14-at-17.49.36.png)](http://chronicle.software/chronicle-map-video-tutorial-1/)

### Simple Construction

To download the JAR which contains Chronicle Map, we recommend you use maven, which will download it
from [Maven Central](http://search.maven.org), once you have installed maven, all you have to do is
add the following to your projects `pom.xml`:

![Maven](http://chronicle.software/wp-content/uploads/2014/09/maven.gif)

```xml
<dependency>
  <groupId>net.openhft</groupId>
  <artifactId>chronicle-map</artifactId>
  <version><!--replace with the latest version--></version>
</dependency>
```
To get the latest version number
[Click Here](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22net.openhft%22%20AND%20a%3A%22chronicle-map%22) 

when you add ( the above dependency ) to your pom maven will usually attempt to download the release artifacts from: 
```
http://repo1.maven.org/maven2/net/openhft/chronicle-map
```

#### Maven Snapshot Download
If you want to try out the latest pre-release code, you can download the snapshot artifact manually from: 
```xml
https://oss.sonatype.org/content/repositories/snapshots/net/openhft/chronicle-map/
```
a better way is to add the following to your setting.xml, to allow maven to download snapshots :

```xml
<repository>
    <id>Snapshot Repository</id>
    <name>Snapshot Repository</name>
    <url>https://oss.sonatype.org/content/repositories/snapshots</url>
    <snapshots>
        <enabled>true</enabled>
    </snapshots>
</repository>
```
and define the snapshot version in your pom.xml, for example:
```xml
<dependency>
  <groupId>net.openhft</groupId>
  <artifactId>chronicle-map</artifactId>
  <version><!--replace with the latest version--></version>
</dependency>
```

#### Key Value Object Types

Unlike HashMap which will support any heap object, ChronicleMap only works with objects that it 
can store off heap, so the objects have to be  :  (one of the following )

- AutoBoxed primitives - for good performance.
- Strings - for good performance.
- implements Serializable  
- implements Externalizable ( with a public default constructor ) 
- implements our custom interface BytesMarshallable ( with a public default constructor ) - use 
this for best performance.

or value objects that are created through, a directClass interface, for example : 
``` java
      ChronicleMap<String, BondVOInterface> chm = ChronicleMapBuilder
               .of(String.class, BondVOInterface.class)
               .create();

```

Object graphs can also be included as long as the outer object supports Serializable, Externalizable or BytesMarshallable.

#### ChronicleMap Construction

Creating an instance of ChronicleMap is a little more complex than just calling a constructor.
To create an instance you have to use the ChronicleMapBuilder.

``` java
import net.openhft.chronicle.map.*
.....

try {

    String tmp = System.getProperty("java.io.tmpdir");
    String pathname = tmp + "/shm-test/myfile.dat";

    File file = new File(pathname);

    ChronicleMapBuilder<Integer, CharSequence> builder =
        ChronicleMapBuilder.of(Integer.class, CharSequence.class);
    ConcurrentMap<Integer, CharSequence> map = builder.createPersistedTo(file);
 
} catch (IOException e) {
    e.printStackTrace();
}
```

ChronicleMap stores its data off the java heap, If you wish to share this off-heap memory between
processes on the same server, you must provide a "file", this file must be the same "file" for all
the instances of Chronicle Map on the same server. The name and location of the "file" is entirely
up to you.  For the best performance on many unix systems we recommend using
[tmpfs](http://en.wikipedia.org/wiki/Tmpfs).

### Sharing Data Between Two or More Maps
Since this file is memory mapped, if you were to create another instance of the ChronicleMap,
pointing to the same file, both ChronicleMaps use this file as a common memory store, into which they
both read and write. The good thing about this, is that the two ( or more instances of the ChronicleMap )
don't have to be running in the same Java process. Ideally and for best performance, the two processes
should be running on the same server. Since the file is memory mapped, ( in most cases ) the read
and writes to the file are hitting the disk cache. This allows the ChronicleMap to exchange data
between processes by just using memory and in around 40 nanoseconds. 

``` java 
ConcurrentMap<Integer, CharSequence> map1, map2;

// this could be on one process
map1 = ChronicleMapBuilder.of(Integer.class, CharSequence.class).createPersistedTo(file);

// this could be on the other process
map2 = ChronicleMapBuilder.of(Integer.class, CharSequence.class).createPersistedTo(file);
```
Note: In order to share data between map1 and map2, the file has to point to the same file location
on your server.

### Entries

One of the differences with ChronicleMap against ConcurrentHashMap, is that it can't be resized.
Unlike the ConcurrentHashMap, ChronicleMap is not limited to the available on heap memory.
Resizing is a very expensive operation for HashMaps as it can stall your application, this is why
we don't do it. When you are building a ChronicleMap you can set the maximum number of entries that
you are ever likely to support but it's fine to overestimate this number. This is because ChronicleMap is not
limited to your available memory therefore, at worst, you will end up having a large file on disk.

You set the maximum number of entries using the builder:

``` java
ConcurrentMap<Integer, CharSequence> map =
    ChronicleMapBuilder.of(Integer.class, CharSequence.class)
    constantValueSizeBySample("a long sample string"),
    .entries(1000) // set the max number of entries here
    .create();
```
In this example above we have set 1000 entries.


We have optimised ChronicleMap so that you can have situations where you either don't use;

- All the entries for which you have allowed.  This works best on Unix where the disk space and memory
used reflect the number of actual entries, not the number for which you have allowed.

- All the space you allow for each entry (see below).  This helps if you have entries which are multiple cache
lines (128 bytes +). Only the lines you touch sit in your CPU cache and if you have multiple pages
(8+ Kbytes) only the pages you touch use memory or disk.  The CPU cache usage matters as it can be
10000x smaller than main memory.

#### Entry size

The size of each entry depends on the type of the Keys and Values, as some types are larger than others. For example, if an entry uses Integers for both the keys and values, both the key and value each take exactly 4 bytes.
There is also some other overhead which is internal to chronicle, such as its internal multi-map, which has an overhead of
* 6 bytes per entry for segment size of < 64k,
* 9 bytes per entry for segment size of < 16m but > 64k
* 12 bytes per entry for segment size of > 16m entries per segment.

Also, if you create a replicated map, there is an additional 10 bytes per entry.

We suggest you don't configure size for constant-sized keys or values, instead you can use the
builder methods .constantKeySizeBySample(sampleKey) and
.constantValueSizeBySample(sampleValue). For common types like Integer we suggest you don't use
these methods, for example ChronicleMap understands that Integer is 4 bytes long, Long is 8, etc.


### Size of space reserved on disk

In Linux, if you looked at the size of the 'file', it will report the used entry size so if you
have just added one entry, it will report the size of this entry. Windows will report
the reserved size, as it reserves the disk space eagerly ( in fact windows also reserves the memory
eagerly as well ) in other words, number-of-entries x entry-size. 

so on Linux, if you type
``` 
# It shows you the extents. 
ls -l <file>

# It shows you how much is actually used.
du <file>
```

To illustrate this with an example - On Ubuntu we can create a 100 TB ChronicleMap.  Both `top` and
`ls -l` say the process virtual size / file size is 100 TB, however the resident memory via `du`
says the size is 71 MB after adding 10000 entries. You can see the size actually used with du.

### How Operating Systems differ

As a pure Java library, the same ChronicleMap Java byte code can be run on Windows, Linux and Mac OSX,
however these operating systems work with memory mapped files differently. These differences effect how
ChronicleMap is able to map memory to a file and hence this can impact the total number of entries that you are
able to configure.

- Windows allocates memory and disk eagerly. Windows will fail if more than 4 GB is allocated in a single memory
mapping, ( calculated as 4GB = 2^20 * 4 KB pages). Windows doesn't fail when a memory mapped region is mapped, rather it will fail when it is used up. This limitation doesn't apply to newer or server based versions of Windows. Eager memory allocation means you can't map more than free memory, but it should reduce jitter when you use it. In the future we may support multiple mappings to avoid this limitation, but there is no immediate plan to do so.
- Linux allocates memory and disk lazily. Linux systems see a performance degradation at around 200% of main memory.
- Mac OSX allocates memory lazily and disk eagerly.

ChronicleMap allocates head room which is a waste on Windows (Linux's sparse allocation means the head room has little
impact).

##### For production
- on Windows we recommend you use map sizes of less than 4 GB each, and less than 50% main memory in total.
- on Linux we recommend you use small to large maps of less than double main memory. e.g. if you have a 128 GB server, we recommend you have less than 256 GB of maps on the server.
- on Mac OSX, we have no specific recommendations.

### ChronicleMap Interface
The ChronicleMap interface adds a few methods above and beyond the standard ConcurrentMap.
The ChronicleMapBuilder can also be used to return the ChronicleMap, see the example below :

``` java
ChronicleMap<Integer, CharSequence> map =
    ChronicleMapBuilder.of(Integer.class, CharSequence.class).create();
```
One way to achieve good performance is to focus on unnecessary object creation, as this reduces
the amount of work that has to be carried out by the Garbage Collector. As such ChronicleMap
supports the following methods :

 - [`V getUsing(K key, V value);`](http://openhft.github.io/Chronicle-Map/apidocs/net/openhft/chronicle/map/ChronicleMap.html#getUsing-K-V-)
 - [`V acquireUsing(K key, V value);`](http://openhft.github.io/Chronicle-Map/apidocs/net/openhft/chronicle/map/ChronicleMap.html#acquireUsing-K-V-)
 - [`ReadContext<K, V> getUsingLocked(@NotNull K key, @NotNull V usingValue);`]  (https://github.com/OpenHFT/Chronicle-Map#off-heap-storage-and-how-using-a-proxy-object-can-improve-performance)
 - [`WriteContext<K, V> acquireUsingLocked(@NotNull K key, @NotNull V usingValue);`]    (https://github.com/OpenHFT/Chronicle-Map#acquireusinglocked)

These methods let you provide the object to which the data will be written so that the value used is mutable. For example

``` java
CharSequence using = new StringBuilder();
CharSequence myResult = map.getUsing("key", using);
// at this point "using" and "myResult" will both point to the same object
```

The `map.getUsing()` method is similar to `map.get()`, but because Chronicle Map stores its data off
heap, if you were to call get("key"), a new object would be created each time. map.getUsing() works
by reusing the heap memory which was used by the original Object "using". This technique provides
you with better control over your object creation.

Exactly like `map.getUsing()`, `map.acquireUsing()` will give you back a reference to an value 
based on
a key, but unlike `map.getUsing()` if there is no entry in the map for this key the entry 
will be added and the value return will we the same value which you provided. ( The section below
 covers both `map.getUsing()` and `map.acquireUsing()` in more detail )

#### Off heap storage and how using a Proxy Object can improve performance

ChronicleMap stores its data off heap. There are some distinct advantages in using off heap data storage

 * When the off heap data is backed by a memory mapped file, the entire map and its contents are automatically persisted. So if you have to restart your system, you won’t lose the content of your map.
 * Off heap data structures are not visited by the garbage collector.  With on heap maps like HashMap the garbage collector has to scan your entire object graph ( in fact just the live objects ) to remove garbage.  If you are able to keep your on heap footprint low, the garbage collector has a lot less work to do, this in turn will improve performance.
 * ChronicleMap and its data is serialised so it can be stored in off 
heap (shared) memory. When this memory is shared between processes on the same server, 
ChronicleMap is able to distribute entries between processes with extremely low overhead. 
This is because both processes are sharing the same off heap memory space. Additionaly, since your objects are already stored off heap ( as a series of bytes ), replicating entries over the network adds relatively little overhead.

For more information on the benefit's of off heap memory, see our article on - [On
heap vs off
heap memory
usage]
(http://vanillajava.blogspot.co.uk/2014/12/on-heap-vs-off-heap-memory-usage.html)

One of the downsides of an off heap map is that whenever you wish to get a value ( on heap ) from an entry
which is off heap, for example calling :

 ``` java
Value v = get(key)
```

that entry has to be deserialised onto the Java heap so that you can use its value just like any other Java object. So if you were to call get(key) ten times, 

 ``` java
for(int i=1;i<=10;i++) {
  Value v = get(key)
}
``` 
this would create 10 separate instances of the value. This is because each time get() is called, the map has to
first create a Object to store the result in and then deserialise the value stored in the off 
heap entry. Assuming you want to get the value back on heap so that you can use it like a normal Java 
value, deserialisation is unavoidable.  Deserialisation has to occur every time, 
( since the value may have been changed by another thread ), but we don’t have to create the 
object each time. This is why we introduced the method `getUsing(key,using)`. By reducing the number of objects
you create, the amount of work that the garbage collector has to carry out is reduced, 
this in turn may improve overall performance. So back to `getUsing(key,using)`, if you wish to reuse
and existing object ( in this case the ‘using’ value ), you can instead call :
 
 ``` java
Value using = new Value();

for(int i=1;i<=10;i++) {
  Value bond = map.getUsing(key,using); // this won’t create a new value each time.
  assert using == bond; // this will always be the same instance and the Vaule type can not be immutable
}
``` 
We can get a further performance improvement if we don't deserialize the whole object, 
but deserialize only the bytes that in which we are actually interested, 

Lets assume that we had the following interface :

``` java
public interface LongValue {
    long getValue();
    void setValue(long value);
}
``` 
It is possible to use chronicle as an off heap proxy that can go directly to the memory location off heap and just get back the long that you require. This is especially useful if the value object has a lot of fields in it like the BondVOInterface
``` java
public interface BondVOInterface {
 ... 

    long getIssueDate();
    void setIssueDate(long issueDate);  /* time in millis */

    long getMaturityDate();
    void setMaturityDate(long maturityDate);  /* time in millis */

    double getCoupon();
    void setCoupon(double coupon);

    String getSymbol();
    void setSymbol(@MaxSize(20) String symbol);
 ... 
}
``` 
If for example you want to just do the following

``` java
BondVOInterface  bond = map.get(key);
bond.getCoupon()

```
lets say that it is only the `coupon` field that we are interested in, then its better not to have to
deserialise the whole object that implements the `BondVOInterface`. The `ChronicleMapBuilder` will look a the types of keys
 and values that you use, If the value type is a simple accessor/mutator interface that is exposing a non nested pojo, which uses simple types
 like `CharSequence` and primitives with corresponding get..() and
 set..() methods, Chronicle is able to generate off heap poxies so the whole object is not
 deserialized each
  time it is accessed, The off heap proxies are able to read
and write into
the off heap data structures directly, this reduced serialisation can give you a big performance boost.
See the example below which demonstartes how you can work directly with off heap entries.

``` java
ChronicleMap<CharSequence, BondVOInterface> map = ChronicleMapBuilder
        .of(String.class, BondVOInterface.class)
        .keySize(10)
        .create();
```
by default, builder assume that we are going to work directly with the off heap
entries (`DataValueClasses.directClassFor(BondVOInterface.class)`).

The value class, in our case `BondVOInterface.class` is an `interface` rather than a `class`,  as before, we can
use the `getUsing(key,using)` method, but this time we have to create the ‘using’ instance slightly
differently. We call either the `map.newValueInstance()` or `map.newKeyInstance()` method.

``` java
BondVOInterface using = map.newValueInstance();
``` 

the call to `getUsing(key,value)` is the same as we had in the earlier example

``` java
BondVOInterface using = map.newValueInstance();
BondVOInterface  bond = map.getUsing(key,using);
assert using == bond; // this will always be the same instance
```

`getUsing(key,using)` won’t create any on heap objects, and it won’t deserialize the ‘bond’ entry from off heap to on
heap, all it does is sets the bond as a proxy to the off heap memory, this proxy object was created by
`map.newValueInstance()`, it allows us access to the fields of our
entry, directly into the off heap storage.

As above, ideally you would reuse the `using` variable.

 ``` java
BondVOInterface using = map.newValueInstance();

for(int i=1;i<=10;i++) {
  BondVOInterface bond = map.getUsing(key,using); // this won’t create a new value each time.
  double coupon = bond.getCoupon()
  assert using == bond; // this will always be the same instance
}
```

so when you call :

``` java
bond.getCoupon()
```

Note: It is only the coupon that gets deserialized.

Just like any other ConcurrentMap, ChronicleMap uses segment locking, if you wish to obtain a read lock
when calling getUsing(key,using) you can do this :

``` java
BondVOInterface  bond = map.getUsing(key,value);
try (ReadContext<?, BondVOInterface> context = map.getUsingLocked(key,bond)) {
   long issueDate =  bond.getIssueDate();
   String symbol = bond.getSymbol();

	// add your logic here ( the lock will ensure this bond can not be changed by another thread )

} // the lock is released here because close() is called

```

To ensure that 'issueDate' and 'symbol' can be read atomically, these values
must be read while the segment read lock is in place.

When you call map.getUsingLocked(key,using) we return a ReadContext, the ReadContext extends
AutoCloseable so will automatically unlock the segment when the try block is exited.

If you wish not to use a try block you must manually release the segment lock by calling
``` java
context.close() //  releases the lock
```

####  acquireUsing()
Just like getUsing(), acquireUsing() will also recycle the value you pass it. The following
code snippet is a pattern that you will often come across. `acquireUsing(key,value)` offers this 
functionality from a single method call, reducing the hash look-ups and making your code run slightly faster.

 ``` java
V acquireUsing(key,value) {
    Lock l = ...; // the segment lock of the map
    l.lock();
    try {
           V v = map.getUsing(key,value)
           if (v == null) {
               map.put(key,value); // create a new entry
               return value;
           } else
               return v; // return the value of the existing entry
    } finally {
        l.unlock();
    }
}
```



####  acquireUsingLocked()

If you are only accessing ChronicleMap from a single thread. If you are not doing replication
and don't care about atomic reads. Then its simpler ( and faster ) to use `acquireUsing(key,
value)` otherwise werecommend you use `acquireUsingLocked(key,value)` as this gives you atomicity.

The acquireUsingLocked(key,value) method holds a segment write lock, as it will update or put a
new entry into the map, this is unlike getUsingLocked
(key,using) which only holds a segment read lock. Below is an example of how to use
acquireUsingLocked(..,..).

``` java
BondVOInterface bond = ... // create your instance
try (WriteContext<?, BondVOInterface> context = map.acquireUsingLocked("one", bond)) {
 assert bond ==  context.value();

 long issueDate =  bond.getIssueDate();
 String symbol = bond.getSymbol();

 if (condition)  // based on your own business logic
    context.removeEntry();
}
```


If after some business logic, in our example after reading the 'issueDate' and
'symbol', you wish to remove the entry, its more efficient to use the 'context' directly to remove the entry. The
'context' is already aware of the entries location in memory. So it will be quicker to call
`context.removeEntry()` rather than `map.remove(key)`.

## Serialization

![Serialization](http://chronicle.software/wp-content/uploads/2014/09/Serialization_01.jpg)


ChronicleMap stores your data into off heap memory, so when you give it a Key or Value, it will
serialise these objects into bytes.

### Simple Types
If you are using simple auto boxed objects based on the primitive types, ChronicleMap will
automatically handle the serialisation for you.  

### Complex Types
For anything other than the standard object, the Objects either have to :
* implement "java.io.Serializable" ( which we don't recommend as this can be slow )
* we also support "java.io.Externalizable", we recommend this over Serializable as its usually faster.
* or for the best performance implement net.openhft.lang.io.serialization.BytesMarshallable,
an example of how to do this can be found at "IntValue$$Native"
* alternatively, you could write a "Custom Marshaller", the custom marshaller can be implemented
for a single type or a number of types.

### Import/Export entries as JSON
![Import/Export](http://chronicle.software/wp-content/uploads/2014/09/Export-import_04.jpg)

ChronicleMap supports importing and exporting all the entries into a JSON encoded file.

``` java
void getAll(File toFile) throws IOException;
void putAll(File fromFile) throws IOException;
```

Only the entries of your map are exported, not the configuration of your map. So care
must be taken to populate the data in to a map of the correct Key/Value type and with enough
available entries. When importing data :

* entries that are in the map but not in the JSON file will remain untouched.
* entries that are in the map and in the JSON file will be updated.
* entries that are not in the map but are in the JSON file will be added.

In other words importing data into a ChronicleMap works like `map.putAll(<JSON entries>)`.

When importing data, if you are also writing to the map at the same time, the last update will win.
In other words a write lock is not held for the entire import process.
Importing and exporting the map, is ideal if you wish to:
* Bulk load data from one ChronicleMap into another.
* migrate data between versions of ChronicleMap.


## Close
Unlike ConcurrentHashMap, ChronicleMap stores its data off heap, often in a memory mapped file.
Its recommended that you call close() once you have finished working with a ChronicleMap.

``` java
map.close()
```

This is especially important when working with ChronicleMap replication, as failure to call close may prevent
you from restarting a replicated map on the same port. In the event that your application crashes it may not
be possible to call close(). Your operating system will usually close dangling ports automatically,
so although it is recommended that you close() when you have finished with the map,
its not something that you must do, it's just something that we recommend you should do.

###### WARNING

If you call close() too early before you have finished working with the map, this can cause
your JVM to crash. Close MUST BE the last thing that you do with the map.


# TCP / UDP Replication

ChronicleMap supports both TCP and UDP replication

![TCP/IP Replication](http://chronicle.software/wp-content/uploads/2014/07/Chronicle-Map-TCP-Replication_simple_02.jpg)

### TCP / UDP Background.
TCP/IP is a reliable protocol, what this means is that unless you have a network failure or hardware
outage the data is guaranteed to arrive. TCP/IP provides point to point connectivity. So in effect
( over simplified ), if the message was sent to 100 hosts, the message would have to be sent
100 times. With UDP, the message is only sent once. This is ideal if you have a large number of
hosts and you wish to broadcast the same data to each of them.   However, one of the big drawbacks
with UDP is that it's not a reliable protocol. This means, if the UDP message is Broadcast onto
the network, the hosts are not guaranteed to receive it, so they can miss data. Some solutions
attempt to build resilience into UDP, but arguably, this is in effect reinventing TCP/IP.

### How to setup UDP Replication
In reality on a good quality wired LAN, when using UDP, you will rarely miss messages. Nevertheless this is
a risk that we suggest you don't take. We suggest that whenever you use UDP replication you use it
in conjunction with a throttled TCP replication, therefore if a host misses a message over UDP, they
will later pick it up via TCP/IP. 

###  TCP/IP  Throttling
We are careful not to swamp your network with too much TCP/IP traffic, We do this by providing
a throttled version of TCP replication. This works because ChronicleMap only broadcasts the latest
update of each entry. 

### Replication How it works

ChronicleMap provides multi master hash map replication. What this means, is that each remote
map, mirrors its changes over to another remote map, neither map is considered
the master store of data. Each map uses timestamps to reconcile changes.
We refer to in instance of a remote map as a node.
A node can be connected to up to 128 other nodes.
The data that is stored locally in each node becomes eventually consistent. So changes made to one
node, for example by calling put(), will be replicated over to the other node. To achieve a high
level of performance and throughput, the call to put() won’t block, 
With ConcurrentHashMap, It is typical to check the return code of some methods to obtain the old
value, for example remove(). Due to the loose coupling and lock free nature of this multi master
implementation,  this return value is only the old value on the nodes local data store. In other
words the nodes are only concurrent locally. Its worth realising that another node performing
exactly the same operation may return a different value. However reconciliation will ensure the maps
themselves become eventually consistent.

### Reconciliation 
If two ( or more nodes ) receive a change to their maps for the same key but different values, say
by a user of the maps, calling the put(key,value), then, initially each node will update its local
store and each local store will hold a different value. The aim of multi master replication is
to provide eventual consistency across the nodes. So, with multi master whenever a node is changed
it will notify the other nodes of its change. We will refer to this notification as an event.
The event will hold a timestamp indicating the time the change occurred, it will also hold the state
transition, in this case it was a put with a key and value.
Eventual consistency is achieved by looking at the timestamp from the remote node, if for a given
key, the remote nodes timestamp is newer than the local nodes timestamp, then the event from
the remote node will be applied to the local node, otherwise the event will be ignored. Since
none of the nodes is a primary, each node holds information about the other nodes. For this node its
own identifier is referred to as its 'localIdentifier', the identifiers of other nodes are the
'remoteIdentifiers'. On an update or insert of a key/value, this node pushes the information of
the change to the remote nodes. The nodes use non-blocking java NIO I/O and all replication is done
on a single thread. However there is an edge case. If two nodes update their map at the same time
with different values, we have to deterministically resolve which update wins. This is because eventual
consistency mandates that both nodes should end up locally holding the same data. Although it is rare that two remote
nodes receive an update to their maps at exactly the same time for the same key, we have
to handle this edge case.  We can not therefore rely on timestamps alone to reconcile
the updates. Typically the update with the newest timestamp should win, but in this example both
timestamps are the same, and the decision made to one node should be identical to the decision made
to the other. This dilemma is resolved by using a node identifier, the node identifier is a unique
'byte' value that is assigned to each node. When the time stamps are the same the remote node with the
smaller identifier will be preferred.

### Multiple Processes on the same server with Replication

On a server if you have a number of Java processes and then within each Java process you create an instance of a ChronicleMap which binds to the same underline 'file', they exchange data via shared memory rather than TCP or UDP replication. So if a ChronicleMap which is not performing TCP Replication is updated, this update can be picked up by another ChronicleMap. This other ChronicleMap could be a TCP replicated ChronicleMap. In such an example the TCP replicated ChronicleMap would then push the update to the remote nodes.

Likewise, if the TCP replicated ChronicleMap was to received an update from a remote node, then this update would be immediately available to all the ChronicleMaps on the server.

### Identifier for Replication
If all you are doing is replicating your ChronicleMaps on the same server you don't have to set up
TCP and UDP replication. You also don't have to set the identifiers - as explained earlier this identifier is only for the resolution of conflicts amongst remote servers.

If however you wish to replicate data between 2 or more servers, then ALL of the ChronicleMaps
including those not actively participating in TCP or UDP replication must have the identifier set.
The identifier must be unique to each server. Each ChronicleMap on the same server must have
the same identifier. The reason that all ChronicleMaps must have the identifier set, is because
the memory is laid out slightly differently when using replication, so even if a Map is not actively
performing TCP or UDP replication itself, if it wishes to replicate with one that is, it must have
its memory laid out the same way to be compatible. 

If the identifiers are not set up uniquely then the updates will be ignored, as for example
a ChronicleMap set up with the identifiers equals '1', will ignore all events which contain
the remote identifier of '1', in other words Chronicle Map replication is set up to ignore updates
which have originated from itself. This is to avoid the circularity of events.

When setting up the identifier you can use values from 1 to 127. ( see the section above for more
information on identifiers and how they are used in replication. )

The identifier is setup on the builder as follows.

```java
TcpTransportAndNetworkConfig tcpConfig = ...
map = ChronicleMapBuilder
    .of(Integer.class, CharSequence.class)
    .replication(identifier, tcpConfig)
    .create();
```


### Bootstrapping 
When a node is connected over the network to an active grid of nodes. It must first receive any data
that it does not have from the other nodes. Eventually, all the nodes in the grid have to hold a
copy of exactly the same data. We refer to this initial data load phase as bootstrapping.
Bootstrapping by its very nature is point to point, so it is only performed over TCP replication.
For architectures that wish to use UDP replication it is advised you use TCP Replication as well. A
grid which only uses UDP replication will miss out on the bootstrapping, possibly leaving the nodes
in an inconsistent state. To avoid this, if you would rather reduce the amount of TCP traffic on
your network, we suggest you consider using a throttle TCP replication along with UDP replication.
Bootstrapping is not used when the nodes are on the same server, so for this case, TCP replication
is not required.

### Identifier
Each map is allocated a unique identifier

Server 1 has:
```
.replication((byte) 1, tcpConfigServer1)
```

Server 2 has:
```
.replication((byte) 2, tcpConfigServer2)
```

Server 3 has:
```
.replication((byte) 3, tcpConfigServer3)
```

If you fail to allocate a unique identifier replication will not work correctly.

### Port
Each map must be allocated a unique port, the port has to be unique per server, if the maps are
running on different hosts they could be allocated the same port, but in our example we allocated
them different ports, we allocated map1 port 8076 and map2 port 8077. Currently we don't support
data forwarding, so it important to connect every remote map, to every other remote map, in other
words you can't have a hub configuration where all the data passes through a single map which every
other map is connected to. So currently, if you had 4 servers each with a Chronicle Map, you would
require 6 connections.

In our case we are only using 2 maps, this is how we connected map1 to map 2.
```
TcpTransportAndNetworkConfig.of(8076, new InetSocketAddress("localhost", 8077))
                    .heartBeatInterval(1, SECONDS);
```
you could have put this instruction on map2 instead, like this 
```
TcpTransportAndNetworkConfig.of(8077, new InetSocketAddress("localhost", 8076))
                    .heartBeatInterval(1, SECONDS);
```
even though data flows from map1 to map2 and map2 to map1 it doesn't matter which way you connected
this, in other words its a bidirectional connection. 

### Configuring Three Way TCP/IP Replication

![TCP/IP Replication 3Way](http://chronicle.software/wp-content/uploads/2014/09/Screen-Shot-2014-10-27-at-18.19.05.png)

Below is example how to set up tcpConfig for 3 host

```java
String hostServer1 = "localhost"; // change this to your host
int serverPort1 = 8076;           // change this to your port
InetSocketAddress inetSocketAddress1 = new InetSocketAddress(hostServer1, serverPort1);

String hostServer2 = "localhost"; // change this to your host
int  serverPort2= 8077;           // change this to your port
InetSocketAddress inetSocketAddress2 = new InetSocketAddress(hostServer2, serverPort2);

String hostServer3 = "localhost"; // change this to your host
int serverPort3 = 8078;           // change this to your port
InetSocketAddress inetSocketAddress3 = new InetSocketAddress(hostServer3, serverPort3);

// this is to go on server 1
TcpTransportAndNetworkConfig tcpConfigServer1 =
        TcpTransportAndNetworkConfig.of(serverPort1);

// this is to go on server 2
TcpTransportAndNetworkConfig tcpConfigServer2 = TcpTransportAndNetworkConfig
        .of(serverPort2, inetSocketAddress1);

// this is to go on server 3
TcpTransportAndNetworkConfig tcpConfigServer3 = TcpTransportAndNetworkConfig
        .of(serverPort3, inetSocketAddress1, inetSocketAddress2);
```     

### Heart Beat Interval
We set a heartBeatInterval, in our example to 1 second
``` java
 heartBeatInterval(1, SECONDS)
```
A heartbeat will only be send if no data is transmitted, if the maps are constantly exchanging data
no heartbeat message is sent. If a map does not receive either data of a heartbeat the connection
is dropped and re-established.

# Multiple Chronicle Maps - Network Distributed

![Chronicle Maps Network Distributed](http://chronicle.software/wp-content/uploads/2014/07/Chronicle-Map_channels_diagram_02.jpg)

ChronicleMap TCP Replication lets you distribute a single ChronicleMap, to a number of servers
across your network. Replication is point to point and the data transfer is bidirectional, so in the
example of just two servers, they only have to be connected via a single TCP socket connection and
the data is transferred both ways. This is great, but what if you wanted to replicate more than
just one ChronicleMap, what if you were going to replicate two ChronicleMaps across your network,
unfortunately with just TCP replication you would have to have two tcp socket connections, which is
not ideal. This is why we created the `ReplicationHub`. The `ReplicationHub` lets you replicate numerous
ChronicleMaps via a single point to point socket connection.

The `ReplicationHub` encompasses TCP replication, where each map has to be given a
unique identifier, but when using the `ReplicationHub` we use a channel to identify the map,
rather than the identifier.  The identifier is used to identify the host/server which broadcasts the
update. Put simply:

* Each host must be given a unique identifier.
* Each map must be given a unique channel.

``` java
byte identifier= 2;
ReplicationHub replicationHub = ReplicationHub.builder()
                    .tcpTransportAndNetwork(tcpConfig)
                    .createWithId(identifier);
```

In this example above the `ReplicationHub` is given the identifier of 2.

With channels you are able to attach additional maps to a `ReplicationChannel` once its up and
running.

When creating the `ReplicationChannel` you should attach your tcp or udp configuration :
``` java
byte identifier = 1;
ReplicationHub replicationHub = ReplicationHub.builder()
                    .tcpTransportAndNetwork(tcpConfig)
                    .createWithId(identifier);
```

Attaching a `ReplicationChannel` to the map :

``` java
short channel = (short) 2;
ChronicleMap<Integer, CharSequence> map = ChronicleMapBuilder.of(Integer.class, CharSequence.class)
  .entries(1000)
  .instance().replicatedViaChannel(replicationHub.createChannel(channel))
  .create();
```

The Chronicle channel is use to identify which map is to be replicated to which other map on
the remote node. In the example above this is assigned to '(short) 1', so for example if you have
two maps, lets call them map1 and map2, you could assign them with chronicle
channels 1 and 2 respectively. Map1 would have the chronicle channel of 1 on both servers. You
should not confuse the Chronicle Channels with the identifiers, the identifiers are unique per
replicating node ( in this case which host, the reason we say replicating node rather than host as it is
possible to have more than one replicating node per host if each of them had a different TCP/IP port ), where as the chronicle channels are used to identify which map you are referring. No additional socket
 connection is made per chronicle channel that
you use, so we allow up to 32767 chronicle channels.

If you inadvertently got the chronicle channels around the wrong way, then chronicle would attempt
to replicate the wrong maps data. The chronicle channels don't have to be in order but they must be
unique for each map you have.

### Channels and ReplicationChannel - Example

``` java

import net.openhft.chronicle.hash.replication.ReplicationChannel;
import net.openhft.chronicle.hash.replication.ReplicationHub;
import net.openhft.chronicle.hash.replication.TcpTransportAndNetworkConfig;
import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

...

ChronicleMap<CharSequence, CharSequence> favoriteColourServer1, favoriteColourServer2;
ChronicleMap<CharSequence, CharSequence> favoriteComputerServer1, favoriteComputerServer2;


// server 1 with  identifier = 1
{
    ChronicleMapBuilder<CharSequence, CharSequence> builder =
            ChronicleMapBuilder.of(CharSequence.class, CharSequence.class).entries(1000);

    byte identifier = (byte) 1;

    TcpTransportAndNetworkConfig tcpConfig = TcpTransportAndNetworkConfig
            .of(8086, new InetSocketAddress("localhost", 8087))
            .heartBeatInterval(1, TimeUnit.SECONDS);

    ReplicationHub hubOnServer1 = ReplicationHub.builder()
            .tcpTransportAndNetwork(tcpConfig)
            .createWithId(identifier);

    // this demotes favoriteColour
    short channel1 = (short) 1;

    ReplicationChannel channel = hubOnServer1.createChannel(channel1);
    favoriteColourServer1 = builder.instance()
            .replicatedViaChannel(channel).create();

    favoriteColourServer1.put("peter", "green");

    // this demotes favoriteComputer
    short channel2 = (short) 2;

    favoriteComputerServer1 = builder.instance()
            .replicatedViaChannel(hubOnServer1.createChannel(channel2)).create();

    favoriteComputerServer1.put("peter", "dell");
}

// server 2 with  identifier = 2
{
    ChronicleMapBuilder<CharSequence, CharSequence> builder =
            ChronicleMapBuilder.of(CharSequence.class, CharSequence.class).entries(1000);

    byte identifier = (byte) 2;

    TcpTransportAndNetworkConfig tcpConfig = TcpTransportAndNetworkConfig
            .of(8087).heartBeatInterval(1, TimeUnit.SECONDS);

    ReplicationHub hubOnServer2 = ReplicationHub.builder()
            .tcpTransportAndNetwork(tcpConfig)
            .createWithId(identifier);

    // this demotes favoriteColour
    short channel1 = (short) 1;

    favoriteColourServer2 = builder.instance()
            .replicatedViaChannel(hubOnServer2.createChannel(channel1)).create();

    favoriteColourServer2.put("rob", "blue");

    // this demotes favoriteComputer
    short channel2 = (short) 2;

    favoriteComputerServer2 = builder.instance()
            .replicatedViaChannel(hubOnServer2.createChannel(channel2)).create();

    favoriteComputerServer2.put("rob", "mac");
    favoriteComputerServer2.put("daniel", "mac");
}

// allow time for the recompilation to resolve
for (int t = 0; t < 2500; t++) {
    if (favoriteComputerServer2.equals(favoriteComputerServer1) &&
            favoriteColourServer2.equals(favoriteColourServer1))
        break;
    Thread.sleep(1);
}

assertEquals(favoriteComputerServer1, favoriteComputerServer2);
assertEquals(3, favoriteComputerServer2.size());

assertEquals(favoriteColourServer1, favoriteColourServer2);
assertEquals(2, favoriteColourServer1.size());

favoriteColourServer1.close();
favoriteComputerServer2.close();
favoriteColourServer2.close();
favoriteColourServer1.close();

``` 

# Stateless Client

![](http://chronicle.software/wp-content/uploads/2014/07/Chronicle-Map-remote-stateless-map_04_vB.jpg)

A stateless client is an instance of a `ChronicleMap` or a `ChronicleSet` that does not hold any 
data
 locally, all the Map or Set operations are delegated via a Remote Procedure Calls ( RPC ) to 
 another `ChronicleMap` or  `ChronicleSet`  which we will refer to as the server. The server
 holds all your data, the server can not it’s self be a stateless client. Your stateless client must
 be connected to the server via TCP/IP.

 ![ChronicleMap](http://chronicle.software/wp-content/uploads/2014/09/State-Transition_1-thread_02.jpg)

 The stateless client delegates all your method calls to
 the remote server. The stateless client operations will block, in other words the stateless
 client waits for the server to send a response before continuing to the next operation. The stateless
 client could be  consider to be a ClientProxy to `ChronicleMap` or  `ChronicleSet`  running
 on another host.
 
 Below is an example of how to configure a stateless client.

``` java
final ChronicleMap<Integer, CharSequence> serverMap;
final ChronicleMap<Integer, CharSequence> statelessMap;

// server
{

    ChronicleMapBuilder.of(Integer.class, CharSequence.class)
            .replication((byte) 2, TcpTransportAndNetworkConfig.of(8076))
            .create();            
                       
    serverMap.put(10, "EXAMPLE-10");
}

// stateless client
{
    statelessMap = ChronicleMapBuilder.of(Integer.class, CharSequence.class)
            .statelessClient(new InetSocketAddress("localhost", 8076))
            .create();

    Assert.assertEquals("EXAMPLE-10", statelessMap.get(10));
    Assert.assertEquals(1, statelessMap.size());
}

serverMap.close();
statelessMap.close();
```

When used with a stateless client, each statefull server has to be configured with TCP 
replication, when you set up TCP replication you must define a port for the replication to 
run on, the port you choose is up to you, but you should pick a free port that is not currently 
being used by another application. In this example we choose the port 8076

``` java
// sets the server to run on localhost:8076
.replication((byte) 2, TcpTransportAndNetworkConfig.of(8076))
``` 
  
On the "stateless client" we connect to the server via TCP/IP on localhost:8076 : 

``` java
.statelessClient(new InetSocketAddress("localhost", 8076))
```

but in your example you should choose the host of the statefull server and the port you allocated
 it. 

``` java
.statelessClient(new InetSocketAddress(<host of state-full server>, <port of state-full server>))
```

the ".statelessClient(..)" returns an instance of `StatelessClientConfig`, which has only a few
of its own configurations, such as the `create()` method, which can be used to create a new
stateless client.
For this example we ran both 
the client and the server on the same host ( hence the “localhost" setting ), 
but in a real scanario the stateless client will typically be on a different server than its
statefull host. If you are aiming to create a stateless client and server on the same host, it's
better not to do this, as the stateless client connects to the server via TCP/IP. It would be better to
share the maps via memory as this will give you better performance ( read more about
this at [Sharing Data Between Two or More Maps](https://github
.com/OpenHFT/Chronicle-Map#sharing-data-between-two-or-more-maps).

click [here](https://github.com/OpenHFT/Chronicle-Map#sharing-data-between-two-or-more-maps ) 

### How to speed up the Chronicle Map Stateless Client 

When calling the stateless client, you will get better throughput if you invoke your requests from a
 number of threads, this is because by default when you make a method call to a `ChronicleMap`
 stateless client, your method call is wrapped into an event which is sent over TCP and processed
  by the server. Your stateless client will block until an acknowledgement has been received from the server that
the event was processed.

![Chronicle Map](http://chronicle.software/wp-content/uploads/2014/09/State-Transition_2-thread_03.jpg)

When you are calling methods that return a value like get() this
blocking adds no additional overhead, because you have to wait for the return value anyway, 
In some cases you could get better performance if you don't have to wait for the acknowledgement, This maybe the case when you are calling the `put()` method, but the problem with this method is it returns the old value even though you may not use it.

For in memory data-structures like a HashMap this isn’t a big problem. But in a distributed environment things are a bit more complicated. Blocking for an old value that you don’t require adds additional network overhead and additional processing overhead of serialising the old value on the server and deserialising it on the client

So if you don’t require the old value and don’t wish to block until your `put()` has been received by the server, then you may wish to consider using the following configuration :

``` java
.putReturnsNull(true)
```
  
and also for the `remove()` method

``` java
.removeReturnsNull(true)
```

``` java
statelessClientMap = ChronicleMapBuilder.of(Integer.class, CharSequence.class)
     .putReturnsNull(true)
     .removeReturnsNull(true)
     .statelessClient(new InetSocketAddress("localhost", 8076))
     .create();
```

For the very best performance you should also set these properties on the server as well

``` java
ChronicleMapBuilder.of(Integer.class, CharSequence.class)
    .replication((byte) 2, TcpTransportAndNetworkConfig.of(8076))
    .putReturnsNull(true)
    .removeReturnsNull(true)
    .create();            
```

##### Performance

The throughput and latency performance for different configurations.

Tested using a test called BGChronicleTest.

On one machine (i7 3.5 GHz) we have two persisted replicas run as

-Dreplicas=2 eg.BGChronicleTest server

On another machine (Dual Xeon 8 core 2.6 GHz) connected via a pair of Solarflare SFN5121T 10 Gig-E with onload enabled.

-Dreplicas=2 -Dclients={see below} -DmaxRate=30000 -DreadRatio=2 eg.BGChronicleTest client

2 clients
Throughput test
messages per seconds: 58,864

Latency test at 30,000 msg/sec
50% / 90% / 99% // 99.9% / 99.99% / worst latency was 33 / 80 / 111 // 120 / 148 / 3,921 us

4 clients
Throughput test
messages per seconds: 94,006

Latency test at 30,000 msg/sec
50% / 90% / 99% // 99.9% / 99.99% / worst latency was 32 / 94 / 106 // 131 / 177 / 2,153 us

8 clients
Throughput test
messages per seconds: 162,961

Latency test at 30,000 msg/sec
50% / 90% / 99% // 99.9% / 99.99% / worst latency was 35 / 94 / 117 // 140 / 167 / 1,685 us

16 clients
Throughput test
messages per seconds: 267,097

Latency test at 30,000 msg/sec
50% / 90% / 99% // 99.9% / 99.99% / worst latency was 38 / 97 / 122 // 149 / 174 / 2,771 us

24 clients
Throughput test
messages per seconds: 253,052

Latency test at 30,000 msg/sec
50% / 90% / 99% // 99.9% / 99.99% / worst latency was 40 / 99 / 121 // 151 / 243 / 3,669 us

##### Close

its always important to close `ChronicleMap`'s and `ChronicleSet` 's when you have finished with them

``` java
serverMap.close();
statelessMap.close();
``` 

#  Known Issues

##### Memory issue on Windows

ChronicleMap lets you assign a map larger than your available memory, If you were to create more
entries than the available memory, ChronicleMap will page the segments that are accessed least to
disk, and load the recently used segments into available memory. This feature lets you work with
extremely large maps, it works brilliantly on Linux but unfortunately, this paging feature is not
supported on Windows, if you use more memory than is physically available on windows you will
experience the following error :

```java
Java frames: (J=compiled Java code, j=interpreted, Vv=VM code)
j sun.misc.Unsafe.compareAndSwapLong(Ljava/lang/Object;JJJ)Z+0
j net.openhft.lang.io.NativeBytes.compareAndSwapLong(JJJ)Z+13
j net.openhft.lang.io.AbstractBytes.tryLockNanos8a(JJ)Z+12
j net.openhft.lang.io.AbstractBytes.tryLockNanosLong(JJ)Z+41
j net.openhft.collections.AbstractVanillaSharedHashMap$Segment.lock()V+12
```

##### When Chronicle Map is Full

`IllegalStateException` is thrown.

ChronicleMap doesn't resize automatically.  It is assumed you will make the virtual size of the map
larger than you need and it will handle this reasonably efficiently. With the default settings you
will run out of space between 1 and 2 million entries.

You should set the .entries(..) and .averageValueSize(..) to those you require.

##### Don't forget to set the EntrySize

If you put() and entry that is much larger than the max entry size set via averageValueSize(),
the code will error. To see how to set the entry size the example below sets the entry size to 10, 
you should pick a size that is the size in bytes of your entries : 

```java
ChronicleMap<Integer, String> map =
             ChronicleMapBuilder.of(Integer.class, String.class)
                     .averageValueSize(10).create();
 
```

This example will throw an java.lang.IllegalArgumentException because the averageValueSize is too small.

```java
@Test
public void test() throws IOException, InterruptedException {
    ChronicleMap<Integer, String> map =
            ChronicleMapBuilder.of(Integer.class, String.class)
                    .averageValueSize(10).create();

    String value =   new String(new char[2000]);
    map.put(1, value);

    Assert.assertEquals(value, map.get(1));
}

```

If the entry size is dramatically too small ( like in the example below ), 
you will get a *malloc_error_break* :

```java
@Test
public void test() throws IOException, InterruptedException {
    ChronicleMap<Integer, String> map =
            ChronicleMapBuilder.of(Integer.class, String.class)
                    .averageValueSize(10).create();

    String value =   new String(new char[20000000]);
    map.put(1, value);

    Assert.assertEquals(value, map.get(1));
}
```

# Example : Simple Hello World

This simple chronicle map, works just like ConcurrentHashMap but stores its data off-heap. If you
want to use Chronicle Map to share data between java process you should look at the next exampl 

``` java 
Map<Integer, CharSequence> map = ChronicleMapBuilder.of(Integer.class,
        CharSequence.class).create();

map.put(1, "hello world");
System.out.println(map.get(1));

``` 

# Example : Sharing the map on two ( or more ) processes on the same machine

Lets assume that we had two server, lets call them server1 and server2, if we wished to share a map
between them, this is how we could set it up

``` java 

// --- RUN ON ONE JAVA PROCESS ( BUT ON THE SAME SERVER )
{
    File file = new File("a-new-file-on-your-sever");	
    Map<Integer, CharSequence> map1 = ChronicleMapBuilder.of(Integer.class, CharSequence.class)
            .createPersistedTo(file); // this has to be the same file as used by map 2
    map1.put(1, "hello world");
}

// --- RUN ON THE OTHER JAVA PROCESS ( BUT ON THE SAME SERVER )
{
    File file = new File("a-new-file-on-your-sever");  // this has to be the same file as used by map 1
    Map<Integer, CharSequence> map1 = ChronicleMapBuilder.of(Integer.class, CharSequence.class)
            .createPersistedTo(file);

    System.out.println(map1.get(1));
}
```

# Example : Replicating data between process on different servers via TCP/IP

Lets assume that we had two server, lets call them server1 and server2, if we wished to share a map
between them, this is how we could set it up

``` java 
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class YourClass {

    @Test
    public void test() throws IOException, InterruptedException {

        Map map1;
        Map map2;

//  ----------  SERVER1 1 ----------
        {

            // we connect the maps via a TCP/IP socket connection on port 8077

            TcpTransportAndNetworkConfig tcpConfig = TcpTransportAndNetworkConfig
                    .of(8076, new InetSocketAddress("localhost", 8077))
                    .heartBeatInterval(1L, TimeUnit.SECONDS);
            ChronicleMapBuilder<Integer, CharSequence> map1Builder =
                    ChronicleMapBuilder.of(Integer.class, CharSequence.class)
                            .entries(20000L)
                            .replication((byte) 1, tcpConfig);

            map1 = map1Builder.create();
        }
//  ----------  SERVER2 on the same server as ----------

        {
            TcpTransportAndNetworkConfig tcpConfig =
                    TcpTransportAndNetworkConfig.of(8077)
                    .heartBeatInterval(1L, TimeUnit.SECONDS);
            ChronicleMapBuilder<Integer, CharSequence> map2Builder =
                    ChronicleMapBuilder.of(Integer.class, CharSequence.class)
                            .entries(20000L)
                            .replication((byte) 2, tcpConfig);
            map2 = map2Builder.create();

            // we will stores some data into one map here
            map2.put(5, "EXAMPLE");
        }

//  ----------  CHECK ----------

// we are now going to check that the two maps contain the same data

// allow time for the recompilation to resolve
        int t = 0;
        for (; t < 5000; t++) {
            if (map1.equals(map2))
                break;
            Thread.sleep(1);
        }

        Assert.assertEquals(map1, map2);
        Assert.assertTrue(!map1.isEmpty());
    }

}
```

# Example : Replicating data between process on different servers using UDP

This example is the same as the one above, but it uses a slow throttled TCP/IP connection to fill in
updates that may have been missed when sent over UDP. Usually on a good network, for example a wired
LAN, UDP won’t miss updates. But UDP does not support guaranteed delivery, we recommend also running
a TCP connection along side to ensure the data becomes eventually consistent.  Note : It is possible
to use Chronicle without the TCP replication and just use UDP (  that’s if you like living dangerously ! )

``` java
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class YourClass {

    @Test
    public void test() throws IOException, InterruptedException {

        Map map1;
        Map map2;

        int udpPort = 1234;

//  ----------  SERVER1 1 ----------
        {

            // we connect the maps via a TCP socket connection on port 8077

            TcpTransportAndNetworkConfig tcpConfig = TcpTransportAndNetworkConfig
                    .of(8076, new InetSocketAddress("localhost", 8077))
                    .heartBeatInterval(1L, TimeUnit.SECONDS)

                            // a maximum of 1024 bits per millisecond
                    .throttlingConfig(ThrottlingConfig.throttle(1024, TimeUnit.MILLISECONDS));

            UdpTransportConfig udpConfig = UdpTransportConfig
                    .of(Inet4Address.getByName("255.255.255.255"), udpPort);

            ChronicleMapBuilder<Integer, CharSequence> map1Builder =
                    ChronicleMapBuilder.of(Integer.class, CharSequence.class)
                            .entries(20000L)
                            .replication(SingleChronicleHashReplication.builder()
                                    .tcpTransportAndNetwork(tcpConfig)
                                    .udpTransport(udpConfig)
                                    .createWithId((byte) 1));

            map1 = map1Builder.create();
        }
//  ----------  SERVER2 2 on the same server as ----------

        {
            TcpTransportAndNetworkConfig tcpConfig =
                    TcpTransportAndNetworkConfig.of(8077)
                    .heartBeatInterval(1L, TimeUnit.SECONDS)
                    .throttlingConfig(ThrottlingConfig.throttle(1024, TimeUnit.MILLISECONDS));

            UdpTransportConfig udpConfig = UdpTransportConfig
                    .of(Inet4Address.getByName("255.255.255.255"), udpPort);

            ChronicleMapBuilder<Integer, CharSequence> map2Builder =
                    ChronicleMapBuilder.of(Integer.class, CharSequence.class)
                            .entries(20000L)
                            .replication(SingleChronicleHashReplication.builder()
                                    .tcpTransportAndNetwork(tcpConfig)
                                    .udpTransport(udpConfig)
                                    .createWithId((byte) 2));

            map2 = map2Builder.create();

            // we will stores some data into one map here
            map2.put(5, "EXAMPLE");
        }

//  ----------  CHECK ----------

// we are now going to check that the two maps contain the same data

// allow time for the recompilation to resolve
        int t = 0;
        for (; t < 5000; t++) {
            if (map1.equals(map2))
                break;
            Thread.sleep(1);
        }

        Assert.assertEquals(map1, map2);
        Assert.assertTrue(!map1.isEmpty());
    }
}
```

# Example : Creating a Chronicle Set and adding data to it

This project also provides the Chronicle Set, `ChronicleSet` is built on Chronicle Map, so the builder
configuration are almost identical to `ChronicleMap` ( see above ), this example shows how to create
a simple off heap set
``` java 
        Set<Integer> set = ChronicleSetBuilder.of(Integer.class).create();
        
        set.add(1);
        set.remove(1)
```
and just like map it support shared memory and TCP replication.

# Customizing Chronicle Map behaviour and using listeners

You could customize Chronicle Map behaviour on several levels:

 - [`ChronicleMapBuilder.entryOperations()`](http://openhft.github.io/Chronicle-Map/apidocs/net/openhft/chronicle/map/ChronicleMapBuilder.html#entryOperations)
   define the "inner" listening level, all operations with entries, either during ordinary map
   method calls, remote calls, replication or modifications during iteration over the map, operate
   via this configured SPI.

 - [`ChronicleMapBuilder.mapMethods()`](http://openhft.github.io/Chronicle-Map/apidocs/net/openhft/chronicle/map/ChronicleMapBuilder.html#mapMethods)
   is the higher-level of listening for local calls of Map methods. Methods in `MapMethods`
   interface correspond to `Map` interface methods with the same names, and define their
   implementations for `ChronicleMap`.

 - [`ChronicleMapBuilder.remoteOperations()`](http://openhft.github.io/Chronicle-Map/apidocs/net/openhft/chronicle/map/ChronicleMapBuilder.html#remoteOperations)
   is for listening and customizing behaviour of remote calls, and replication events.

All executions around `ChronicleMap` go through the three tiers (or the two bottom):

 1. Query tier: `MapQueryContext` interface
 2. Entry tier: `MapEntry` and `MapAbsentEntry` interfaces
 3. Data tier: `Data` interface

`MapMethods` and `MapRemoteOperations` methods accept *query context*, i. e. these SPI is above
the Query tier. `MapEntryOperations` methods accept `MapEntry` or `MapAbsentEntry`, i. e. this SPI
is between Query and Entry tiers.

Combined, listening/customization SPI interfaces and `ChronicleMap.queryContext()` API are powerful
enough to

 - Log all operations of some kind on ChronicleMap (e. g. all remove, insert or update operations)
 - Log some specific operations on ChronicleMap (e. g. log only acquireUsing() calls, which
   has created a new entry)
 - Forbid performing operations of some kind on the ChronicleMap instance
 - Backup all changes to ChronicleMap to some durable storage, e. g. SQL database
 - Perform multi-ChronicleMap operations correctly in concurrent environment,
   by acquiring locks on all ChronicleMaps before updating them.
 - <s>Perform multi-key operations on a single ChronicleMap correctly in concurrent environment,
   by acquiring locks on all keys before updating the entries</s> SOON
 - Define own replication/reconciliation logic for distributed ChronicleMaps

## Examples

### Simple logging

Just log all modification operations on ChronicleMap

```
class SimpleLoggingMapEntryOperations<K, V> implements MapEntryOperations<K, V, Void> {

    private static final SimpleLoggingMapEntryOperations INSTANCE =
            new SimpleLoggingMapEntryOperations();

    public static <K, V> MapEntryOperations<K, V, Void> simpleLoggingMapEntryOperations() {
        return SimpleLoggingMapEntryOperations.INSTANCE;
    }

    private SimpleLoggingMapEntryOperations() {}

    @Override
    public Void remove(@NotNull MapEntry<K, V> entry) {
        System.out.println("remove " + entry.key() + ": " + entry.value());
        entry.doRemove();
        return null;
    }

    @Override
    public Void replaceValue(@NotNull MapEntry<K, V> entry, Data<V, ?> newValue) {
        System.out.println("replace " + entry.key() + ": " + entry.value() + " -> " + newValue);
        entry.doReplaceValue(newValue);
        return null;
    }

    @Override
    public Void insert(@NotNull MapAbsentEntry<K, V> absentEntry, Data<V, ?> value) {
        System.out.println("insert " + absentEntry.absentKey() + " -> " + value);
        absentEntry.doInsert(value);
        return null;
    }

    @Override
    public Data<V, ?> defaultValue(@NotNull MapAbsentEntry<K, V> absentEntry) {
        Data<V, ?> defaultValue = absentEntry.defaultValue();
        System.out.println("default " + absentEntry.absentKey() + " -> " + defaultValue);
        return defaultValue;
    }
}
```

Usage:

```
ChronicleMap<Integer, IntValue> map = ChronicleMapBuilder
        .of(Integer.class, IntValue.class)
        .entries(100)
        .entryOperations(simpleLoggingMapEntryOperations())
        .create();

// do anything with the map
```

### BiMap

Possible bidirectional map (i. e. a map that preserves the uniqueness of its values as well
as that of its keys) implementation over Chronicle Maps.

```
enum DualLockSuccess {SUCCESS, FAIL}
```

```
class BiMapMethods<K, V> implements MapMethods<K, V, DualLockSuccess> {
    @Override
    public void remove(MapQueryContext<K, V, DualLockSuccess> q, ReturnValue<V> returnValue) {
        while (true) {
            q.updateLock().lock();
            try {
                MapEntry<K, V> entry = q.entry();
                if (entry != null) {
                    returnValue.returnValue(entry.value());
                    if (q.remove(entry) == SUCCESS)
                        return;
                }
            } finally {
                q.readLock().unlock();
            }
        }
    }

    @Override
    public void put(MapQueryContext<K, V, DualLockSuccess> q, Data<V, ?> value,
                    ReturnValue<V> returnValue) {
        while (true) {
            q.updateLock().lock();
            try {
                MapEntry<K, V> entry = q.entry();
                if (entry != null) {
                    throw new IllegalStateException();
                } else {
                    if (q.insert(q.absentEntry(), value) == SUCCESS)
                        return;
                }
            } finally {
                q.readLock().unlock();
            }
        }
    }

    @Override
    public void putIfAbsent(MapQueryContext<K, V, DualLockSuccess> q, Data<V, ?> value,
                            ReturnValue<V> returnValue) {
        while (true) {
            try {
                if (q.readLock().tryLock()) {
                    MapEntry<?, V> entry = q.entry();
                    if (entry != null) {
                        returnValue.returnValue(entry.value());
                        return;
                    }
                    // Key is absent
                    q.readLock().unlock();
                }
                q.updateLock().lock();
                MapEntry<?, V> entry = q.entry();
                if (entry != null) {
                    returnValue.returnValue(entry.value());
                    return;
                }
                // Key is absent
                if (q.insert(q.absentEntry(), value) == SUCCESS)
                    return;
            } finally {
                q.readLock().unlock();
            }
        }
    }

    @Override
    public boolean remove(MapQueryContext<K, V, DualLockSuccess> q, Data<V, ?> value) {
        while (true) {
            q.updateLock().lock();
            MapEntry<K, V> entry = q.entry();
            try {
                if (entry != null && bytesEquivalent(entry.value(), value)) {
                    if (q.remove(entry) == SUCCESS) {
                        return true;
                    } else {
                        //noinspection UnnecessaryContinue
                        continue;
                    }
                } else {
                    return false;
                }
            } finally {
                q.readLock().unlock();
            }
        }
    }

    @Override
    public void acquireUsing(MapQueryContext<K, V, DualLockSuccess> q,
                             ReturnValue<V> returnValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void replace(MapQueryContext<K, V, DualLockSuccess> q, Data<V, ?> value,
                        ReturnValue<V> returnValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean replace(MapQueryContext<K, V, DualLockSuccess> q, Data<V, ?> oldValue,
                           Data<V, ?> newValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void compute(MapQueryContext<K, V, DualLockSuccess> q,
                        BiFunction<? super K, ? super V, ? extends V> remappingFunction,
                        ReturnValue<V> returnValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void merge(MapQueryContext<K, V, DualLockSuccess> q, Data<V, ?> value,
                      BiFunction<? super V, ? super V, ? extends V> remappingFunction,
                      ReturnValue<V> returnValue) {
        throw new UnsupportedOperationException();
    }
}
```

```
class BiMapEntryOperations<K, V> implements MapEntryOperations<K, V, DualLockSuccess> {
    ChronicleMap<V, K> reverse;

    public void setReverse(ChronicleMap<V, K> reverse) {
        this.reverse = reverse;
    }

    @Override
    public DualLockSuccess remove(@NotNull MapEntry<K, V> entry) {
        try (ExternalMapQueryContext<V, K, ?> rq = reverse.queryContext(entry.value())) {
            if (!rq.updateLock().tryLock()) {
                if (entry.context() instanceof MapQueryContext)
                    return FAIL;
                throw new IllegalStateException("Concurrent modifications to reverse map " +
                        "during remove during iteration");
            }
            MapEntry<V, K> reverseEntry = rq.entry();
            if (reverseEntry != null) {
                entry.doRemove();
                reverseEntry.doRemove();
                return SUCCESS;
            } else {
                throw new IllegalStateException(entry.key() + " maps to " + entry.value() +
                        ", but in the reverse map this value is absent");
            }
        }
    }

    @Override
    public DualLockSuccess replaceValue(@NotNull MapEntry<K, V> entry, Data<V, ?> newValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DualLockSuccess insert(@NotNull MapAbsentEntry<K, V> absentEntry,
                                  Data<V, ?> value) {
        try (ExternalMapQueryContext<V, K, ?> rq = reverse.queryContext(value)) {
            if (!rq.updateLock().tryLock())
                return FAIL;
            MapAbsentEntry<V, K> reverseAbsentEntry = rq.absentEntry();
            if (reverseAbsentEntry != null) {
                absentEntry.doInsert(value);
                reverseAbsentEntry.doInsert(absentEntry.absentKey());
                return SUCCESS;
            } else {
                Data<K, ?> reverseKey = rq.entry().value();
                if (reverseKey.equals(absentEntry.absentKey())) {
                    // recover
                    absentEntry.doInsert(value);
                    return SUCCESS;
                }
                throw new IllegalArgumentException("Try to associate " +
                        absentEntry.absentKey() + " with " + value + ", but in the reverse " +
                        "map this value already maps to " + reverseKey);
            }
        }
    }
}
```

Usage:
```
BiMapEntryOperations<Integer, CharSequence> biMapOps1 = new BiMapEntryOperations<>();
ChronicleMap<Integer, CharSequence> map1 = ChronicleMapBuilder
        .of(Integer.class, CharSequence.class)
        .entries(100)
        .actualSegments(1)
        .averageValueSize(10)
        .entryOperations(biMapOps1)
        .mapMethods(new BiMapMethods<>())
        .create();

BiMapEntryOperations<CharSequence, Integer> biMapOps2 = new BiMapEntryOperations<>();
ChronicleMap<CharSequence, Integer> map2 = ChronicleMapBuilder
        .of(CharSequence.class, Integer.class)
        .entries(100)
        .actualSegments(1)
        .averageKeySize(10)
        .entryOperations(biMapOps2)
        .mapMethods(new BiMapMethods<>())
        .create();

biMapOps1.setReverse(map2);
biMapOps2.setReverse(map1);

map1.put(1, "1");
System.out.println(map2.get("1"));
```

### CRDT values for replicated Chronicle Maps -- Grow-only set

`Set` values won't replace each other on replication, but will converge to a single, common set,
the union of all elements added to all sets on all replicated nodes.

```
class GrowOnlySetValuedMapEntryOperations<K, E>
        implements MapEntryOperations<K, Set<E>, Void> {

    private static final GrowOnlySetValuedMapEntryOperations INSTANCE =
            new GrowOnlySetValuedMapEntryOperations();

    public static <K, E>
    MapEntryOperations<K, Set<E>, Void> growOnlySetValuedMapEntryOperations() {
        return GrowOnlySetValuedMapEntryOperations.INSTANCE;
    }

    private GrowOnlySetValuedMapEntryOperations() {}

    @Override
    public Void remove(@NotNull MapEntry<K, Set<E>> entry) {
        throw new UnsupportedOperationException("Map with grow-only set values " +
                "doesn't support map value removals");
    }
}
```

```
class GrowOnlySetValuedMapRemoteOperations<K, E>
        implements MapRemoteOperations<K, Set<E>, Void> {

    private static final GrowOnlySetValuedMapRemoteOperations INSTANCE =
            new GrowOnlySetValuedMapRemoteOperations();

    public static <K, E>
    MapRemoteOperations<K, Set<E>, Void> growOnlySetValuedMapRemoteOperations() {
        return GrowOnlySetValuedMapRemoteOperations.INSTANCE;
    }

    private GrowOnlySetValuedMapRemoteOperations() {}

    @Override
    public void put(MapRemoteQueryContext<K, Set<E>, Void> q, Data<Set<E>, ?> newValue) {
        MapReplicableEntry<K, Set<E>> entry = q.entry();
        if (entry != null) {
            Set<E> merged = new HashSet<>(entry.value().get());
            merged.addAll(newValue.get());
            q.replaceValue(entry, q.wrapValueAsValue(merged));
        } else {
            q.insert(q.absentEntry(), newValue);
            q.entry().updateOrigin(q.remoteIdentifier(), q.remoteTimestamp());
        }
    }

    @Override
    public void remove(MapRemoteQueryContext<K, Set<E>, Void> q) {
        throw new UnsupportedOperationException();
    }
}
```

Usage:
```
ChronicleMap<Integer, Set<Integer>> map1 = ChronicleMapBuilder
        .of(Integer.class, (Class<Set<Integer>>) (Class) Set.class)
        .entries(100)
        .averageValueSize(1000)
        .entryOperations(growOnlySetValuedMapEntryOperations())
        .remoteOperations(growOnlySetValuedMapRemoteOperations())
        .replication((byte) 1, /* ... replicated nodes */)
        .instance()
        .name("map1")
        .create();
```


# Performance Topics

There are general principles we can give direction on - for specific advise we believe consulting
 to be the most productive solution.

We want the Map to be reasonably general purpose, so in broad terms we can say
- the key and values have to be self contained, ideally trees, rather than graphs.
- ideally values are similar lengths, however we support varying lengths.
- ideally you want to use primitives and use object recycling for performance, though this is not a requirement.
- ideally you have some idea as to the maximum number of entries, though it is not too important if
the maximum entries is above what you need.
- if for example you are working with, market depth, this  can be supported via an array of nested 
types.
- we support code generation of efficient custom serializes - See the examples where you provide 
an interface as the data type, the map will generate the implementation.

### Improving the performance of Chronicle Maps Serialization

Are you finding that when using Chronicle Map the serialisation is slow, but you have a large object graph,
if so -
here are some steps that you can take to improve performance ( you may find that just one of these steps gives you the performance you require )

* consider replacing java.io.Serializable with java.io.Externalizable ( java.io.Externalizable can be much
faster, but requires you do the the serialisation your self)
* consider using net.openhft.lang.io.serialization.BytesMarshallable, working with this is like using java
.io.Externalizable but in addition is supports some compressed types, which can give you slightly better performance.
* try where possible to de-normalize your object graph, if you are able to limit your graph down to just a
few types you can then look at using our off heap interface proxy objects and have a map instance per type
of object
 ( much like in a database where you would have a table per type ), you could then set up foreign key
 relationships between the maps, by using for example integers or longs. This works especially well as
 integers and longs have a good hash distribution.

### Tuning Chronicle Map with Large Data

Generally speaking `ChronicleMap` is slower then ConcurrentHashMap for a small number of entries, but
for a large number of entries ConcurrentHashMap doesn't scale as well as Chronicle Map, especially
when you start running low on heap. ConcurrentHashMap quickly becomes unusable whereas Chronicle Map
can still work when it is 20 times the size of a ConcurrentHashMap with an Out of Memory Error.
  
For example with a heap of 3/4 of say 32 GB main memory, you might get say 100 million entries but
when using most of the heap you might see 20-40 second gc pauses with `ChronicleMap` you could have
1000 million entries and see < 100 ms pauses (depending on your disk subsystem and how fast you
write your data)

Chronicle Map makes heavy use of the OS to perform the memory management and writing to disk. How it
behaves is very dependant on how you tune the kernel and what hardware you are using. You may get
bad behaviour when the kernel forces a very large amount of data to disk after letting a lot of
uncommited data build up. In the worst case scenario the OS will stop the process for tens of
seconds at a time ( even up to 40 seconds) rather than let the program continue. However, to get
into that state you have to be loading a lot of data which exceeds main memory with very little rest
(e.g. cpu processing). There are good use cases for bulk data loads, but you have to be careful how
this is done if you also want good worst case latency characteristics. (the throughput should be
much the same)

When you create a ChronicleMap, it has many segments. By default it has a minimum of 128, but one
for every 32 K entries. e.g. for 500M entries you can expect ~16K segments (being the next power of
2). With so many segments, the chances of a perfect hash distribution is low and so the Chronicle
Map allows for double what you asked for but is designed to do this with almost no extra main memory
(only extra virtual memory). This means when you ask for 500M * 256 bytes entries you actually get 1
BN possible entries (assuming a perfect hash distribution between segments) There is a small
overhead per entry of 16 - 24 bytes adding another 20 GB.

So while the virtual memory is 270 GB, it is expected that for 500 M entries you will be trying to
use no more than 20 GB (overhead/hash tables) + ~120 GB (entries)

When `ChronicleMap` has exhausted all the memory on your server, its not going to be so fast, for a
random access pattern you are entirely dependant on how fast your underlying disk is. If your home
directory is an HDD and its performance is around 125 IOPS (I/Os per second). Each lookup takes two
memory accesses so you might get around 65 lookups per second. For 100-200K operations you can
expect around 1600 seconds or 25-50 minutes. If you use an SSD, it can get around 230 K IOPS, or
about 115 K `ChronicleMap` lookups per second.

### Lock contention

If you see the following warning :

``` java 
WARNING:net.openhft.lang.io.AbstractBytes tryLockNanosLong0
WARNING: Thread-2, to obtain a lock took 0.129 seconds
``` 
 
It's likely you have lock contention, this can be due to : 

- a low number of segments and
- the machine was heavily over utilised, possibly with the working data set larger than main memory.
- you have a large number of threads, greater than the number of cores you have, doing nothing but hit one map.

It’s not possible to fully disable locking,  locking is done a a segment basis.
So, If you set a large number of actual segments, this will reduce your lock contention. 

See the example below to see how to set the number of segments :

``` java 
ChronicleMap<Long, String> map = ChronicleMapBuilder.of(Long.class, String.class)
   .entries(100)
   .actualSegments(100)    // set your number of segments here
   .create();
```  
 
Reducing lock contention will make this warning message go away, but this message maybe more of a symptom 
of a general problem with what the system is doing, so you may experience a delay anyway.

### Better to use small keys

If you put() a small number of large entries into ChronicleMap, you are unlikely to see any
performance gains over a standard map, So we recommend you use a standard ConcurrentHashMap, unless
you need ChronicleMaps other features.

Chronicle Map gives better performance for smaller keys and values due to the low overhead per
entry. It can use 1/5th the memory of ConcurrentHashMap. When you have larger entries, the overhead
per entry doesn't matter so much and the relative waste per entry starts to matter. For Example,
ChronicleMap assumes every entry is the same size and if you have 10kB-20kB entries the 10K entries
can be using 20 kB of virtual memory or at least 12 KB of actual memory (since virtual memory turns
into physical memory in multiples of a page)

As the `ChronicleMap` gets larger the most important factor is the use of CPU cache rather than main
memory, performance is constrained by the number of cache lines you have to touch to update/read an
entry. For large entries this is much the same as ConcurrentHashMap.  In this case, `ChronicleMap` is
not worse than ConcurrentHashMap but not much better.

For large key/values it is not total memory use but other factors which matter such as;
- how compact each entry is. Less memory used makes better use of the L3 cache and memory bus which
  is often a bottleneck in highly concurrent applications. 
- reduce the impact on GCs. The time to perform  GC and its impact is linear. Moving the bulk of
  your data off heap can dramatically improve throughput not to mention worst case latency.
- Large data structures take a long time to reload and having a persisted store significantly
  reduces restart times.
- data can be shared between processes. This gives you more design options to share between JVMS and
  support short lived tasks without having to use TCP.
- data can be replicated across machines.

### ConcurrentHashMap v ChronicleMap
ConcurrentHashMap ( CHM ) outperforms `ChronicleMap` ( CM ) on throughput.  If you don't need
the extra features ChronicleMap gives you, it is not worth the extra complexity it brings.
i.e. don't use it just because you think it is cool. The test can be found in
[ChronicleMapTest](https://github.com/OpenHFT/Chronicle-Map/blob/master/src/test/java/net/openhft/chronicle/map/ChronicleMapTest.java)
under testAcquirePerf() and testCHMAcquirePerf()

ChronicleMap outperforms ConcurrentHashMap on memory consumption, and worst case latencies.
It can be used to reduce or eliminate GCs.

#### Performance Test for many small key-values
The following performance test consists of string keys of the form "u:0123456789" and an int
counter.  The update increments the counter once in each thread, creating an new entry if required.

| Number of entries | Chronicle* Throughput  |  Chronicle RSS  | HashMap* Throughput | HashMap Worst GC pause | HashMap RSS |
|------------------:|---------------:|---------:|---------------:|-------------------:|--------:|
|        10 million |      30 Mupd/s |     ½ GB |     155 Mupd/s |           2.5 secs |    9 GB |
|        50 million |      31 Mupd/s |    3⅓ GB |     120 Mupd/s |           6.9 secs |   28 GB |
|       250 million |      30 Mupd/s |    14 GB |     114 Mupd/s |          17.3 secs |   76 GB |
|      1000 million |      24 Mupd/s |    57 GB |           OOME |            43 secs |      NA |
|      2500 million |      23 Mupd/s |   126 GB |   Did not test |                 NA |      NA |

_*HashMap refers to ConcurrentHashMap, Chronicle refers to Chronicle Map_


Key :
RSS - Resident memory size.  How much main memory was used.
Mupd/s - Million write operations per second. i.e. put(key, value);


Notes:
* `ChronicleMap` was tested with a 32 MB heap, CHM was test with a 100 GB heap.
* The `ChronicleMap` test had a small minor GC on startup of 0.5 ms, but not during the test.
  This is being investigated.
* `ChronicleMap` was tested "writing" to a tmpfs file system.

#### How does it perform when persisted?

Chronicle Map also supports persistence. In this regard there is no similar class in the JDK.

| Number of entries | Chronicle Throughput  |  Chronicle RSS |
|------------------:|---------------:|---------:|
|        10 million |      28 Mupd/s |     ½ GB |
|        50 million |      28 Mupd/s |     9 GB |
|       250 million |      26 Mupd/s |    24 GB |
|      1000 million |     1.3 Mupd/s |    85 GB |

Notes:
* Persistence was performed at a PCI-SSD which supports up to 230K IOPS and 900 MB/s write speed.
  This test didn't test the card to it's limit until the last test.
* The kernel tuning parameters for write back are important here.
  This explains the suddern drop off and this is being investigated.

The sysctl parameters used were approximately 10x the defaults to allow as many operations
to be performed in memory as possible.

    vm.dirty_background_ratio = 50
    vm.dirty_expire_centisecs = 30000
    vm.dirty_ratio = 90
    vm.dirty_writeback_centisecs = 5000

# Questions and Answers

#### Question
I'm searching for a Map implementation that is backed by either direct off-heap or a memory 
mapped file. I want to use it as write-once, read-n-times kind of cache.
#### Answer
The latest version of ChronicleMap has been optimised for this use case. Ie for heavy read to write ratios. 
Note: heavy writers will see about the same performance.

---

#### Question
The backing of to off-heap is only needed to prevent OOM-situations. Otherwise the process  can use a big part of the available memory for the map.

#### Answer
Chronicle Map can be larger than main memory. In fact you might want to reduce the heap size to give chronicle map more memory in extreme cases. Ie because the map can't utilise on heap memory much.

---

#### Question
I stumbled over chronicle-map and it looks like it could do most of those things. Correct me if i'm wrong but
``` java
// would create the off-heap version 
ChronicleMapBuilder.of(A.class, B.class).create();
```
#### Answer
Yes.

---

#### Question
``` java
// the memory-mapped file version ?
 ChronicleMapBuilder.of(A.class, B.class).file(mapFile).create();
```
#### Answer
Yes.

---

#### Question
But both versions write the complete map at every single moment to off-heap, right ?

#### Answer
Every key/value is off heap. There is no cache of objects on heap.

---

#### Question
Is there something in chronicle map which can have part of the map-content still in on-heap memory.

#### Answer
There are data structures which are on heap to manahe the off heap data. This is typically less than 100 kB.

---

#### Question
Either serialized or deserialized. Performance wise do you think that would even matter ?
#### Answer
It matter a lot but you can get the performance close to on heap objects without the long GC pause times. Ie only the bit on the heap makes any difference.

---

#### Question
Last but not least... do you think chronicle is suitable for this simple purpose ?

#### Answer
Actually this is all it does really. We try to make it fast by keeping it simple. 
Eg it is not a cache and doesn't have expiries etc. There is no background thread managing it.
In fact it is so simple that many methods are completely in lined. 
Ie if you look at a crash dump the chronicle methods have been completely in lined and don't appear in the stack trace any more. (The still appear in java stack traces)

---

#### Question
Or is there a lot of overhead for other functionality which i do not intend to use (yet) !?

#### Answer
You can share the data between processes on the same machine but this doesn't add overhead.
You can add replication between machines. But this uses extra classes which are not used in the simple case ie it is as if they were not there, only an option.

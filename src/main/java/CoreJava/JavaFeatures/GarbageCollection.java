package CoreJava.JavaFeatures;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class GarbageCollection {

    public static void main(String[] args) {
        // Strong Reference (Default)
        // Not collected
        Object strongRef = new Object();

        // Weak Reference
        //collected by the garbage collector if there are
        // no strong references to it. (Caching)
        WeakReference<Object> weakRef = new WeakReference<>(new Object());

        // Soft Reference (used in image caching libraries )
        //collected by the garbage collector if memory pressure increases.
        SoftReference<Object> softRef = new SoftReference<>(new Object());

        // Phantom Reference (Memory Management Frameworks)
        // weakest type of reference and
        // are mainly used to track when an object is being
        // finalized by the garbage collector.
        ReferenceQueue<Object> phantomQueue = new ReferenceQueue<>();
        PhantomReference<Object> phantomRef = new PhantomReference<>(new Object(), phantomQueue);


//     Let's simulate some memory pressure
        System.gc();

//     Print status of references
        System.out.println("Strong reference: " + strongRef); //java.lang.Object@5acf9800
        System.out.println("Weak reference: " + weakRef.get()); //null
        System.out.println("Soft reference: " + softRef.get()); //java.lang.Object@4617c264
        System.out.println("Phantom reference: " + phantomRef.get()); //null
    }

}

package games.mgd.archery.util;

import java.util.ArrayList;
import java.util.List;

public class Pool<T>
{
    public interface PoolObjectFactory<T> 
    {
        public T createObject();
    }

    private final List<T> freeObjects;
    private final int maxSize;

    public Pool(PoolObjectFactory<T> factory)
    {
        this.maxSize = 100;
        this.freeObjects = new ArrayList<T>(100);
    }

// --Commented out by Inspection START (25/08/2014 03:28):
//    public T newObject()
//    {
//        T object = null;
//
//        if (freeObjects.size() == 0)
//            object = factory.createObject();
//        else
//            object = freeObjects.remove(freeObjects.size() - 1);
//
//        return object;
//    }
// --Commented out by Inspection STOP (25/08/2014 03:28)

    public void free(T object)
    {
        if (freeObjects.size() < maxSize)
            freeObjects.add(object);
    }
}

package design.pattern.creational;

/**
 * {@link Singleton} is used when there should be only one instance of the that class
 * */
public interface Singleton {}

class EagerSingleton implements Singleton {
    private static final EagerSingleton INSTANCE = new EagerSingleton();

    private EagerSingleton() {/* Initialized only once */}

    public static EagerSingleton getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        EagerSingleton eagerSingleton = EagerSingleton.getInstance();

        System.out.println(eagerSingleton);
    }
}

class LazySingleton implements Singleton {
    private static volatile LazySingleton INSTANCE;

    private LazySingleton() {/* Initialized only once */}

    public static LazySingleton getInstance() {
        if (INSTANCE != null) {
            synchronized (LazySingleton.class) {
                if (INSTANCE != null) {
                    INSTANCE = new LazySingleton();
                }
            }
        }
        return INSTANCE;
    }


    public static void main(String[] args) {
        LazySingleton lazySingleton = LazySingleton.getInstance();

        System.out.println(lazySingleton);
    }
}

/**
 *
 * {@link InstanceHolder} will be only initialized when it is first referred
 * not during the {@link LazyHolderSingleton} initializes
 * */
class LazyHolderSingleton implements Singleton {

    private LazyHolderSingleton() {/* Initialized only once */}

    private static final class InstanceHolder {
        private static final LazyHolderSingleton INSTANCE = new LazyHolderSingleton();
    }

    public static LazyHolderSingleton getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public static void main(String[] args) {
        LazyHolderSingleton lazyHolderSingleton = LazyHolderSingleton.getInstance();

        System.out.println(lazyHolderSingleton);
    }
}

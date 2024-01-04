package design.pattern.behavioural;

/**
 * {@link Iterator} is used to iterate sequentially over aggregate objects without
 *              exposing the internal collection used
 * */
public interface Iterator<T> {
    boolean hasNext();
    T next();
}

enum Color {
    RED, GREEN, BLUE, WHITE, BLACK;

    private static class ColorIterator implements Iterator<Color> {
        private Integer position = 0;
        @Override
        public boolean hasNext() {
            return Color.values().length > position;
        }

        @Override
        public Color next() {
            return Color.values()[position ++];
        }
    }

    public static Iterator<Color> getIterator() {
        return new ColorIterator();
    }

    public static void main(String[] args) {
        Iterator<Color> colorIterator = Color.getIterator();

        while (colorIterator.hasNext()) {
            System.out.println(colorIterator.next());
        }
    }
}

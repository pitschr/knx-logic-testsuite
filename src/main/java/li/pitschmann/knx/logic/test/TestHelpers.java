package li.pitschmann.knx.logic.test;

import li.pitschmann.knx.core.annotations.Nullable;
import li.pitschmann.knx.core.utils.Preconditions;

import java.lang.reflect.Array;

public final class TestHelpers {

    private TestHelpers() {
        throw new AssertionError("Do not touch me!");
    }

    /**
     * Creates a new instance from {@code clazz}. The class must have a public null-arg constructor!
     *
     * @param clazz the class that contains null-arg constructor; may not be null
     * @param <T>   the type of new instance
     * @return a new instance of class from type {@code T}
     */
    public static <T> T newInstance(final Class<T> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (final ReflectiveOperationException t) {
            throw new AssertionError("Could not create a new instance by null-arg constructor from: " + clazz);
        }
    }

    /**
     * Converts the given {@link Object} which is an array to an {@code Object[]} array.
     * <p>
     * This method is helpful to convert e.g. {@code boolean[]} to an {@code Object[]} instance
     *
     * @param objectAsArray object as an array; can be be null
     * @return a new array, if {@code null} then new array of {@code Object[0]} is returned
     */
    public static Object[] asArray(final @Nullable Object objectAsArray) {
        if (objectAsArray == null) {
            return new Object[0];
        }
        Preconditions.checkArgument(objectAsArray.getClass().isArray(),
                "The inputAsArray should be a (primitive) array:" + objectAsArray);

        final var arrayLength = Array.getLength(objectAsArray);
        final var newArray = new Object[arrayLength];
        for (var i = 0; i < arrayLength; i++) {
            newArray[i] = Array.get(objectAsArray, i);
        }
        return newArray;
    }
}

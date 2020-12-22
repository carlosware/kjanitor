package com.carlosware.kjanitor.factories;

public interface Factory<T> {
    /**
     * Builds the requested object type
     * @return an object of the requested type
     * @throws FactoryException in case the construction failed.
     */
    T build() throws FactoryException;
}

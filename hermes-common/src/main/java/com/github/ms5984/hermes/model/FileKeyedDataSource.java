package com.github.ms5984.hermes.model;

import org.jetbrains.annotations.Nullable;

import java.io.File;

public abstract class FileKeyedDataSource implements KeyedDataSource {
    protected final File file;

    protected FileKeyedDataSource(@Nullable File file) {
        this.file = file;
    }
}

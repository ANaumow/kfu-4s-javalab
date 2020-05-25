package ru.naumow.components.resolvers;

public interface StorageFilenameResolver {

    String localUrl(String filename);

    String publicUrl(String filename);

    String localPath();

    String publicPath();

}

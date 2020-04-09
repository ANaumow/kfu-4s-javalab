package ru.naumow.components.resolvers;

public interface StorageFilenameResolver {

    String localUrl(String filename);

    String sharedUrl(String filename);

    String asPostResource(String filename);

    String localPath();

    String sharedPath();

}

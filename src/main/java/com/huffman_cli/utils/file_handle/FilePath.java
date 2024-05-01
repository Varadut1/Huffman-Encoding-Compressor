package com.huffman_cli.utils.file_handle;

public class FilePath {
    public String extension;
    public String fileName;
    public String path;

    public FilePath(String path) {
        this.path = path;
        this.extension = path.substring(path.lastIndexOf(".") + 1);
        this.fileName = path.substring(path.lastIndexOf("/") + 1, path.lastIndexOf("."));
    }

    @Override
    public String toString() {
        return "Path [extension=" + extension + ", fileName=" + fileName + ", path=" + path + "]";
    }
}

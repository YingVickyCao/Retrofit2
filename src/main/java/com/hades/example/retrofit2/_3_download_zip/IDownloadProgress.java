package com.hades.example.retrofit2._3_download_zip;

public interface IDownloadProgress {
    void update(long bytesRead, long length, boolean done);
}

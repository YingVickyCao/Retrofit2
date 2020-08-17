package com.example.hades.retrofit2._5_download_zip;

public interface IDownloadProgress {
    void update(long bytesRead, long length, boolean done);
}

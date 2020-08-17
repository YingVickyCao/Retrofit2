# Retrofit2

```java
@GET
Call<ResponseBody> downloadFile(@Url String fileUrl);

// Way 2 : @Streaming for large file
@Streaming
@GET
Call<ResponseBody> downloadFile_Streaming(@Url String fileUrl);
```

Time 1: (MS) of Way 1 download 72.3kb
Time 2: (MS) of Way 2 download 72.3kb

| No.     | Time 1 | Time 2 |
| ------- | ------ | ------ |
| 1       | 45     | 112    |
| 2       | 5      | 102    |
| 3       | 11     | 84     |
| 4       | 5      | 130    |
| 5       | 5      | 110    |
| 6       | 16     | 94     |
| 7       | 16     | 130    |
| 8       | 7      | 59     |
| 9       | 4      | 95     |
| 10      | 9      | 126    |
| 11      | 6      | 109    |
| 12      | 3      | 182    |
| 13      | 3      | 50     |
| 14      | 3      | 87     |
| 15      | 4      | 106    |
| 16      | 3      | 47     |
| 17      | 5      | 105    |
| Sum     | 150    | 1728   |
| Average | 8      | 105    |

Time 1: (MS) of Way 1 download 1.4 MB
Time 2: (MS) of Way 2 download 1.4 MB

| No.     | Time | Time2   |
| ------- | ---- | ------- |
| 1       | 80   | 20596   |
| 2       | 79   | 14820   |
| 3       | 86   | 10930   |
| 4       | 103  | 21278   |
| 5       | 87   | 12973   |
| 6       | 97   | 12232   |
| 5       | 64   | 11866   |
| 6       | 72   | 5084    |
| 7       | 82   | 6114    |
| 8       | 70   | 5493    |
| 9       | 59   | 6867    |
| 10      | 55   | 8842    |
| 11      | 55   | 22555   |
| 12      | 59   | 12792   |
| 13      | 56   | 9273    |
| 14      | 80   | 20596   |
| 15      | 79   | 14820   |
| Sum     | 1263 | 217131  |
| Average | 84.2 | 14475.4 |

73.2MB

package com.dpm.program.cloudstorage;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.users.FullAccount;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class DropboxStorage {
    private static final String ACCESS_TOKEN = "sl.BJI2kYRjTORS6EVOjOCzxlwGPy8kKfhASsDflmIxLUlj1cd2gayW21Cc9n6XoBnXZyoAt7cEwetRgubgqLA5e5eqbFmIewFK_CATJAjRZZGrA2ks-K4ex29i5e3pTzkuFIhCcd4";
    private static DbxClientV2 client;


    public DropboxStorage()
    {
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/duncanschoenmakers@hotmail.com").build();
        client = new DbxClientV2(config, ACCESS_TOKEN);
    }

    public String uploadFile(File file)
    {
        FullAccount account = null;
        try {
            account = client.users().getCurrentAccount();
        } catch (DbxException e) {
            e.printStackTrace();
        }
        System.out.println(account.getName().getDisplayName());
        try (InputStream in = new FileInputStream(file)) {
            FileMetadata metadata = client.files().uploadBuilder("/" + file.getName().toLowerCase())
                    .uploadAndFinish(in);
            System.out.println(metadata);
            return metadata.getPathLower();
        } catch (IOException | DbxException e) {
            e.printStackTrace();
        }
        return null;
    }
}

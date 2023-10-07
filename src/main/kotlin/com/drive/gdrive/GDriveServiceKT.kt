package com.drive.gdrive

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import com.google.api.client.json.JsonFactory;

import org.springframework.stereotype.Component;

/* class to demonstrate use of Drive files list API */
@Component
class GoogleDriveServiceKT {

    companion object {
        /**
         * Application name.
         */
        private const val APPLICATION_NAME = "gdrive";

        /**
         * Global instance of the JSON factory.
         */
        private val JSON_FACTORY: JsonFactory = GsonFactory.getDefaultInstance();

        /**
         * Directory to store authorization tokens for this application.
         */
        private const val TOKENS_DIRECTORY_PATH = "tokens";

        /**
         * Global instance of the scopes required by this quickstart.
         * If modifying these scopes, delete your previously saved tokens/ folder.
         */
        private val SCOPES = Collections.singletonList(DriveScopes.DRIVE_FILE);

        private const val CREDENTIALS_FILE_PATH = "/credentials.json";

        /**
         * Creates an authorized Credential object.
         *
         * @param HTTP_TRANSPORT The network HTTP Transport.
         * @return An authorized Credential object.
         * @throws IOException If the credentials.json file cannot be found.
         */
        private fun getCredentials(HTTP_TRANSPORT: NetHttpTransport): Credential? {

            try {
                // Load client secrets.
                val input: InputStream? = GoogleDriveServiceKT::class.java.getResourceAsStream(CREDENTIALS_FILE_PATH);

                input ?: throw FileNotFoundException("Resource not found: $CREDENTIALS_FILE_PATH");

                val clientSecrets =
                    GoogleClientSecrets.load(JSON_FACTORY, InputStreamReader(input));

                // Build flow and trigger user authorization request.
                val flow = GoogleAuthorizationCodeFlow.Builder(
                    HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES
                )
                    .setDataStoreFactory(FileDataStoreFactory(java.io.File(TOKENS_DIRECTORY_PATH)))
                    .setAccessType("offline")
                    .build();
                val receiver = LocalServerReceiver.Builder().setPort(8888).build();
                val credential = AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
                //returns an authorized Credential object.
                return credential;
            } catch (e: Exception) {
                println(e)
            }
            return null;

        }
    }

    fun getInstance(): Drive? {

        try {
            // Build a new authorized API client service.
            val HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            val service = Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
            return service;
        } catch (e: GeneralSecurityException) {
            println(e.message)
        } catch (e: GeneralSecurityException) {
            println(e.message)
        }

        return null;

    }

//Code needs to be implemented for the uploding a file to drive
//uploading functions are as follows as
//Using this code snippet you can do all drive functionality
//getfiles()
//uploadFile()


}
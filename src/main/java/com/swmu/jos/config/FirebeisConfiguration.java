package com.swmu.jos.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.io.FileInputStream;
import java.io.IOException;

import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class FirebeisConfiguration {

    @Bean
    @DependsOn(value = {"firebaseApp"})
    public FirebaseMessaging firebaseMessaging(FirebaseApp firebaseApp) {
        return FirebaseMessaging.getInstance(firebaseApp);
    }

    @Bean
    public FirebaseApp firebaseApp() throws IOException {
        final FileInputStream serviceAccount = new FileInputStream(
            "src/main/resources/service-account.json");

        final FirebaseOptions options = FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .setDatabaseUrl("https://joss-d2476-default-rtdb.europe-west1.firebasedatabase.app/")
            .build();

        return FirebaseApp.initializeApp(options);
    }

    /**
     * @example databaseReference.child(" messages ")
     */
    @Bean
    @DependsOn(value = {"firebaseApp"})
    public DatabaseReference databaseReference() {
        return FirebaseDatabase
            .getInstance()
            .getReference("restricted_access/secret_document");
    }
}

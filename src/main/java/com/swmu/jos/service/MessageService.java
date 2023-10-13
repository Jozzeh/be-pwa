package com.swmu.jos.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.swmu.jos.model.Message;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final List<Message> messages = new ArrayList<>();

    public Collection<Message> getAll() throws IOException {
        FileInputStream serviceAccount = new FileInputStream("src/main/resources/service-account.json");

        // Initialize the app with a service account, granting admin privileges
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                // The database URL depends on the location of the database
                .setDatabaseUrl("https://joss-d2476-default-rtdb.europe-west1.firebasedatabase.app/")
                .build();
        FirebaseApp.initializeApp(options);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        final DatabaseReference dinosaursRef = database.getReference("messages");
        Query query = dinosaursRef.limitToLast(123456789);
        query.orderByKey().
        dinosaursRef.orderByKey().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                Message message =  dataSnapshot.getValue(Message.class);
                messages.add(message);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        System.out.println(messages.get(0));
        return messages.stream().toList();
    }

    public void save(final Message message) throws IOException {
        FileInputStream serviceAccount = new FileInputStream("src/main/resources/service-account.json");

// Initialize the app with a service account, granting admin privileges
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                // The database URL depends on the location of the database
                .setDatabaseUrl("https://joss-d2476-default-rtdb.europe-west1.firebasedatabase.app/")
                .build();
        FirebaseApp.initializeApp(options);

// As an admin, the app has access to read and write all data, regardless of Security Rules
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference("restricted_access/secret_document");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Object document = dataSnapshot.getValue();
                System.out.println(document);
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
        DatabaseReference messages = ref.child("messages");
        Map<String, Message> messageMap = new HashMap<>();
        messageMap.put(UUID.randomUUID().toString(), message);
        messages.setValueAsync(messageMap);
    }
}

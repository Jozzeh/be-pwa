package com.swmu.jos.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.cloud.FirestoreClient;
import com.swmu.jos.model.Message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final FirebaseApp app;

    private final List<Message> messages = new ArrayList<>();

    public MessageService(final FirebaseApp app) {
        this.app = app;
    }

    public Collection<Message> getAll() throws IOException, ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();

        // asynchronously retrieve all users
        ApiFuture<QuerySnapshot> query = db.collection("users").get();
// ...
// query.get() blocks on response
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            System.out.println("User: " + document.getId());
            System.out.println("First: " + document.getString("first"));
            if (document.contains("middle")) {
                System.out.println("Middle: " + document.getString("middle"));
            }
            System.out.println("Last: " + document.getString("last"));
            System.out.println("Born: " + document.getLong("born"));
        }
        return messages.stream().toList();
    }

    public void save(final Message message) throws IOException, ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();


        final var docRef = db.collection("messages").document("message");
// Add document data  with id "alovelace" using a hashmap
        Map<String, Object> data = new HashMap<>();
        data.put(UUID.randomUUID().toString(), message);
//asynchronously write data
        ApiFuture<WriteResult> result = docRef.set(data);
        result.get();
// ...
// result.get() blocks on response
        System.out.println("Update time : " + result.get().getUpdateTime());
    }
}

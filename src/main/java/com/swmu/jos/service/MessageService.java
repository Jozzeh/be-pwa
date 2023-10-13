package com.swmu.jos.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.swmu.jos.model.Message;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    public Collection<Message> getAll()
        throws IOException, ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> query = db.collection("messages").get();
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> docs = querySnapshot.getDocuments();
        return docs
            .stream()
            .map(documentSnapshot -> documentSnapshot.toObject(Message.class))
            .toList();
    }

    private Message convertToMessage(final QueryDocumentSnapshot document) {
        return new Message(UUID.randomUUID(), Long.parseLong(document.getString("timestamp")),
            document.getString("message"), document.getString("username"), null);
    }

    public void save(final Message message)
        throws IOException, ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        final var docRef = db.collection("messages").document(UUID.randomUUID().toString());
        Map<String, Object> data = new HashMap<>();
        data.put(UUID.randomUUID().toString(), message);
        ApiFuture<WriteResult> result = docRef.set(data);
        result.get();
        System.out.println("Update time : " + result.get().getUpdateTime());
    }
}

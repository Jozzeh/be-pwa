package com.swmu.jos.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
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
        final var credentials = """
            {
              "type": "service_account",
              "project_id": "joss-d2476",
              "private_key_id": "b5726907ea7464168c6192530825abadbb7dcf6d",
              "private_key": "-----BEGIN PRIVATE KEY-----\\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDwuH4r5PR+/ssZ\\nTIQA+afKNj6E61kQ7ETC62Hwbrw6UwGJRVIdMLn1ghcKY9A0A9X5ktqUtnHv9sxY\\n3RrL9eYM1QvjEAABpVOv+qvuUVTGnwwM+2w/9HPuKALhDEih/C+1Jn/0nMJo9Lyf\\n4WBVmYTPVy4LipwY8tXnMQMi/RbUYZ7e83wjN2l+8AxT9HrVa0qwZU+BCzL+dk5h\\nIEB7ZF0nnyrfZBb/XLr+kQsBXwKXaJOLVmOwjiy0v8evG+DF1gGCoDL6dv4+j0Y4\\nyLnJCrQBKI9xfMgXXz9WerA4wq+8eF+n1zsItfQTQ5B5AN0SG1ShRBwtjiA9T/xO\\nZ1NeNHxjAgMBAAECggEADb16KWc+mkEdtqpd0b07JplFPrEJ7gooCQUGghxpm6qn\\nqJfPjnr8u/Bame04mFkS5zv93gWYwbaEoE3k/N9iuJ8whOihsQ/1LVMeagWabfvs\\nfcRo8v2Xegoun+N9NQ6Znbo+c4GL8N6HWPKBKftC6WxzsS6OpzeCBI0RPZE7GSqc\\n+RjU3WEMuzdwmQgr6VyinHFjZPPkk3VHCFJxfqzGP9Wa+DJCEswL2A5EiuwkcugT\\nxNUBxl0idfmBepv5kuOXg8ehQuBp2odfdcWQxiuv21+6N71nArwqJfA+rqV+G3oN\\nEoS+Him/YwT9Qp+kBm21IKXd95qr4cqb3zeyBplwAQKBgQD7KLHgpkdnt+Mj6Akp\\nshp4W8qZ5ekup5JfzQp38K+0RiUp6v/RzSsaake1+c6TdB55syV2c2eMN3GCcgrS\\nOuZyqwBv2pp2Fs5sO8XVnZgzh5Q+bzFDL8Bnv8DY8gQUPvqjkMj5i3GSh10GHsbp\\nKc402324GJP7N194962mzdoH4wKBgQD1XEq5uMFTt2+FZ9CzJAt1S5IuDgrPD0NZ\\n1EIBLxEsuedqqJd33/tpHObM2G8wymXk118enAxmrqK6jgxkBtwDR14odH8HE/XS\\nFMGfQq73/6FKzd9sXlCoJJPbu2ZbeoYg12yY4v9ZAhMyBCf6f/Fzwiz5Kyk1QGdd\\nqComcHjhgQKBgQCngNCA/uSIa7905v+Va070DmPkl80YbHHWqqzcqQzFsjJ+7leG\\n2MpyZ4dD+kKnXL4mdC/7+6OHhEKTuPM2mkmvGBTGhIiXUUaCMKsUeHJVi4p2yTmV\\nkXdEukInbeECj64hACLJKxKnoteLiWMnhdYcsnX6HYoz9Q4bYzPRF2Dd+QKBgQDc\\n9zIA0kehakjP/u+v9V0wVE+glFrdoORJ6ONMh6PTANVNc+l9NsUHI9UjVCPG7/AV\\nyHRMb8KnUs0Qb07vYlIPzd0ZV9jyeeJfQPwivn4usBQE9yUoRkQjOVXDhKCo784N\\nk5Skl4HZGgpJd/69FS5QzuWLvHgPEiJE8WaMWawzgQKBgEfSdV8RPt3ZdkE6uIIQ\\ngDAbcoJpK5MHGLmMiM9hx7H2joOU2hMAFuL1Sefae0p8hdQr5vP58uK/8+JdFrua\\ndEUSm83bite4MH4LHxW0PGTYG5U1+w1Yo1Xmn/mrs3cK5fJ9mLoueAdTFbOtywkF\\nNgj08GjEgdIuwlj+oluuV888\\n-----END PRIVATE KEY-----\\n",
              "client_email": "firebase-adminsdk-xo35p@joss-d2476.iam.gserviceaccount.com",
              "client_id": "108153919985317789344",
              "auth_uri": "https://accounts.google.com/o/oauth2/auth",
              "token_uri": "https://oauth2.googleapis.com/token",
              "auth_provider_x509_cert_url": "https://www.googleapis.com/oauth2/v1/certs",
              "client_x509_cert_url": "https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-xo35p%40joss-d2476.iam.gserviceaccount.com",
              "universe_domain": "googleapis.com"
            }
                        
            """;
        final InputStream serviceAccount = new ByteArrayInputStream(credentials.getBytes());

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
        return FirebaseDatabase.getInstance().getReference("restricted_access/secret_document");
    }
}

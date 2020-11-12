package com.mh16629.onedayonepage.firebase;

import androidx.annotation.NonNull;

import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class FirebaseAccessStorage {

    private static final String TAG = "FirebaseAccessStorage";

    static FirebaseAccessStorage instance;
    private FirebaseFirestore mFireStore;
    private FirebaseAuth mAuth;

    private FirebaseAccessStorage() {
        mAuth = FirebaseAuth.getInstance();
    }

    public static FirebaseAccessStorage getInstance() {
        if (instance == null) {
            instance = new FirebaseAccessStorage();
        }
        return instance;
    }

    public void createNewBookTest() {
        //Access a Cloud Firestore instance
        mFireStore = FirebaseFirestore.getInstance();

        //Create a new book with a name
        Map<String, Object> book = new HashMap<>();
        book.put("user_id", mAuth.getUid());
        book.put("title", "테스트용 책 타이틀"+mAuth.getCurrentUser().getDisplayName());
        book.put("author", "테스트용 저자"+mAuth.getCurrentUser().getDisplayName());
        book.put("book_id", "테스트용 책 아이디"+mAuth.getCurrentUser().getDisplayName());
        book.put("category_id", "테스트용 카테고리 아이디"+mAuth.getCurrentUser().getDisplayName());

        //Add a new document with a generated ID
        mFireStore.collection("BookList")
                .add(book)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "Error adding document", e);
                    }
                });
    }

    public void getBookDataTest() {
        mFireStore.collection("BookList")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }


}

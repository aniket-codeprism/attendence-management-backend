package com.major.project.attendancemanagementbackend.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.ListUsersPage;
import com.google.firebase.auth.UserRecord;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FirebaseService {
    public UserRecord signUpUser(String email) throws FirebaseAuthException {
        return FirebaseAuth.getInstance().createUser(new UserRecord.CreateRequest().setEmail(email).setPassword("aniket"));
    }
    public String resetPassword(String email) throws FirebaseAuthException {
        return FirebaseAuth.getInstance().generatePasswordResetLink(email);
    }
    public Object clearFirebase() throws FirebaseAuthException {
        ListUsersPage page = FirebaseAuth.getInstance().listUsers(null);
        List<String> s=new ArrayList<>();
        for(var user:page.getValues()){
            if(!user.getUid().equals("MjRP3sphJghK5eaYfrzwn18vNYR2")||!user.getUid().equals("p9qui8BybQhyoK6hwHodJRPiIk83")||!user.getUid().equals("HdplYHtkhMQPtjO0PkXpPtaHfy43"))
                s.add(user.getUid());
        }

        return  FirebaseAuth.getInstance().deleteUsers(s);

    }
}

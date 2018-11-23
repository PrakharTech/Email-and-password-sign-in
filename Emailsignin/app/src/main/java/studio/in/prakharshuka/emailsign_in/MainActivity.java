package studio.in.prakharshuka.emailsign_in;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText email,pass;
    Button signin,register;
    FirebaseAuth mAuth;
    String password,mail;
    String TAG="Email Sign in";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth=FirebaseAuth.getInstance();

        email=findViewById(R.id.email);
        pass=findViewById(R.id.password);
        signin=findViewById(R.id.signin);
        register=findViewById(R.id.registerr);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RegisterActivity.class));
            }
        });


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mail = email.getText().toString();
                password = pass.getText().toString();

                mAuth.signInWithEmailAndPassword(mail, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {

                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(MainActivity.this, "Done!", Toast.LENGTH_SHORT).show();

                                } else {
                                    String errorMessage = task.getException().getMessage();
                                    Toast.makeText(MainActivity.this, "Failed!", Toast.LENGTH_SHORT).show();

                                }

                            }
                        });


            }

        });

        }
    }

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

public class RegisterActivity extends AppCompatActivity {

    EditText pass,conpass,email;
    Button register;
    String password,mail,confirm;
    FirebaseAuth mAuth;
    String TAG="Register";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        pass=findViewById(R.id.password);
        conpass=findViewById(R.id.conPassword);
        email=findViewById(R.id.email);
        register=findViewById(R.id.registerr);
        mAuth=FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password=pass.getText().toString();
                mail=email.getText().toString();
                confirm=conpass.getText().toString();

                if (confirm.equals(password))
                {
                    mAuth.createUserWithEmailAndPassword(mail, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()) {

                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Toast.makeText(RegisterActivity.this, "Done!", Toast.LENGTH_SHORT).show();

                                    } else {
                                        String errorMessage = task.getException().getMessage();
                                        Toast.makeText(RegisterActivity.this, "Failed!", Toast.LENGTH_SHORT).show();

                                    }

                                }
                            });


                }
                else
                {
                    conpass.setError("Please enter the same password again");
                }

            }
        });
    }


}

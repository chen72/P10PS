package sg.edu.rp.webservices.p10ps;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";

    // Declare variable
    private TextView tvMessageText;
    private EditText etTitle, etdate,etDays;
    private Button btnTodo;
    private CheckBox checkBox;

    // TODO: Task 1 - Declare Firebase variables
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference todoPOJOReference;
    Boolean isCheck;
    int days;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle =findViewById(R.id.etTitle);
        etdate =findViewById(R.id.etDate);
        etDays =findViewById(R.id.etDays);
        btnTodo = findViewById(R.id.buttonAdd);
        checkBox = findViewById(R.id.checkBox);
        tvMessageText = findViewById(R.id.textViewMessageText);

        firebaseDatabase = FirebaseDatabase.getInstance();
        todoPOJOReference = firebaseDatabase.getReference("todoPOJO");


        // TODO: Task 3 - Add a value event listener to the "message" node
        todoPOJOReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // TODO: Task 4 - Get the latest value from the dataSnapshot and display on the UI using the EditText message
                // This method will get fired everytime the "message" value updates in the realtime database. We're getting our data back as a DataSnapshot
                Todo todo = dataSnapshot.getValue(Todo.class);
                if (todo != null) {
                    tvMessageText.setText("Title: "+todo.getTitle()+"\nDate: "+todo.getDate()+"\nNumOfDays: "+todo.getNumOfDays()+"\nCompleted?"+todo.isCompleted());

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // This method will be invoked if there is any error
                Log.e(TAG, "Database error occurred", databaseError.toException());

            }
        });



        // TODO: Task 5 - Update UI elements, and then include OnClickListener for Send Message button
        btnTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                days = Integer.parseInt(etDays.getText().toString());
                if (checkBox.isChecked()==true){
                    isCheck = true;
                }else{
                    isCheck = false;
                }

                // Note: We're not directly updating the text view, but calling setValue() to update the data in the database instead
                Todo todo = new Todo(etTitle.getText().toString(), etdate.getText().toString(),days,isCheck);
                todoPOJOReference.setValue(todo);
            }
        });




    }
}

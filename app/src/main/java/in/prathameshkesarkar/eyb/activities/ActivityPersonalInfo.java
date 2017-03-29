package in.prathameshkesarkar.eyb.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;

import in.prathameshkesarkar.eyb.R;

/**
 * Created by prathameshkesarkar on 22/03/17.
 */

public class ActivityPersonalInfo extends AppCompatActivity {


    TextInputLayout firstName,lastName;
    TextInputEditText firstNameEditText,lastNameEditText;


    /**
     * TODO:USE REALM for Adding data to database and Retrofit for network request
     * **/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        firstName = (TextInputLayout) findViewById(R.id.first_name_TIL);
        lastName = (TextInputLayout) findViewById(R.id.last_name_TIL);

        firstName.setHintAnimationEnabled(true);
        lastName.setHintAnimationEnabled(true);


        firstNameEditText = (TextInputEditText) findViewById(R.id.name_edit_text);

        lastNameEditText = (TextInputEditText) findViewById(R.id.last_name_edit_text);

        firstNameEditText.setText("Prathamesh");
        lastNameEditText.setText("Kesarkar");
        firstName.setHint("First Name");
        lastName.setHint("Last Name");
    }
}

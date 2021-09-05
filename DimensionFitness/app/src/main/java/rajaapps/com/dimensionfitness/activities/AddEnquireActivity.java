package rajaapps.com.dimensionfitness.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.example.velm.testlib.model.Enquires;

import rajaapps.com.dimensionfitness.R;

/**
 * Created by Velmurugan on 7/17/2017.
 */

public class AddEnquireActivity extends AppCompatActivity {

    String[] trainers = {"Sivakumar", "Manikandan", "Karthi"};

    String[] source = {"News Paper", "Tv adv", "Facebook", "Friends"};

    String[] address = {"Velachery","Perumbakkam","Guindy","K.k.Nagar","Tharamani"};


    MaterialBetterSpinner trainerSpinner,sourceSpinner,addressSpinner;
    private TextInputLayout inputLayoutName, inputLayoutEmail, inputLayoutPhone;
    private EditText inputName, inputEmail, inputPhone;
    Button btnEnqSave;


    Context context;

    DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("enquires");
        setContentView(R.layout.add_enquire_activity);
        context = this;

        inputLayoutName = (TextInputLayout) findViewById(R.id.input_layout_name);
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        inputLayoutPhone = (TextInputLayout) findViewById(R.id.input_layout_phone);

        inputName = (EditText) findViewById(R.id.input_name);
        inputEmail = (EditText) findViewById(R.id.input_email);
        inputPhone = (EditText) findViewById(R.id.input_phone);

        btnEnqSave = (Button)findViewById(R.id.enq_save);

        final Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        ArrayAdapter<String> trainerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, trainers);
        trainerSpinner = (MaterialBetterSpinner)
                findViewById(R.id.input_trainer);
        trainerSpinner.setAdapter(trainerAdapter);

        ArrayAdapter<String> sourceAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, source);
        sourceSpinner = (MaterialBetterSpinner)
                findViewById(R.id.input_source);
        sourceSpinner.setAdapter(sourceAdapter);

        ArrayAdapter<String> addressAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, address);
        addressSpinner = (MaterialBetterSpinner)
                findViewById(R.id.input_address);
        addressSpinner.setAdapter(addressAdapter);

        inputName.addTextChangedListener(new MyTextWatcher(inputName));
        inputEmail.addTextChangedListener(new MyTextWatcher(inputEmail));

        btnEnqSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                submitForm();
            }
        });




    }

    private void submitForm() {
        if (!validateName()) {
            return;
        }

        if (!validateEmail()) {
            return;
        }

        String id = databaseReference.push().getKey();

        String name = inputName.getText().toString();
        String email = inputEmail.getText().toString();

        String address = addressSpinner.getText().toString();

        String trainer = trainerSpinner.getText().toString();

        String source = sourceSpinner.getText().toString();

        String phone = inputPhone.getText().toString();

        String comments = "";

        String followUpDate = "";

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss a");
        String date = df3.format(c.getTime());

        Enquires enquires = new Enquires(name,email,address,phone,source,comments,followUpDate,trainer,date);
        databaseReference.child(id).setValue(enquires);
        Toast.makeText(getApplicationContext(), "Thank You!", Toast.LENGTH_SHORT).show();
    }

    private boolean validateName() {
        if (inputName.getText().toString().trim().isEmpty()) {
            inputLayoutName.setError(getString(R.string.err_msg_name));
            requestFocus(inputName);
            return false;
        } else {
            inputLayoutName.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateEmail() {
        String email = inputEmail.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutEmail.setError(getString(R.string.err_msg_email));
            requestFocus(inputEmail);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(EditText view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_name:
                    validateName();
                    break;
                case R.id.input_email:
                    validateEmail();
                    break;

            }
        }
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            //NavUtils.navigateUpTo(this, new Intent(this, MainActivity.class));
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

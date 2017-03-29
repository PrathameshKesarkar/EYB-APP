package in.prathameshkesarkar.eyb.activities;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import in.prathameshkesarkar.eyb.R;

/**
 * Created by prathameshkesarkar on 22/03/17.
 */

public class QuestionActivity extends AppCompatActivity {


    TextView questionTop, questionBottom;

    Typeface typeface;
    FloatingActionButton changeDoneFab;
    EditText answerEditText;


    RelativeLayout questionSection, answerSection;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        questionTop = (TextView) findViewById(R.id.question_top);
        questionBottom = (TextView) findViewById(R.id.question_bottom);
        changeDoneFab = (FloatingActionButton) findViewById(R.id.save_change_fab);
        answerEditText = (EditText) findViewById(R.id.answer_edit_text);

        questionSection = (RelativeLayout) findViewById(R.id.question_section);
        answerSection = (RelativeLayout) findViewById(R.id.answer_section);

        typeface = Typeface.createFromAsset(getAssets(), "fonts/francoisone.ttf");
        questionBottom.setTypeface(typeface);


        String topQuestion = getIntent().getStringExtra("QuestionTop");
        String bottomQuestion = getIntent().getStringExtra("QuestionBottom");
        String answerHint = getIntent().getStringExtra("AnswerHint");

        int colorPrimary = getIntent().getIntExtra("QuestionColor", -1);
        int colorPrimaryDark = getIntent().getIntExtra("AnswerColor", -1);

        if (colorPrimary != -1) {
            questionSection.setBackgroundColor(ContextCompat.getColor(this, colorPrimary));
        }
        if (colorPrimaryDark != -1) {
            answerSection.setBackgroundColor(ContextCompat.getColor(this, colorPrimaryDark));
            if (Build.VERSION.SDK_INT >= 21)
                getWindow().setStatusBarColor(ContextCompat.getColor(this, colorPrimaryDark));
        }

        questionTop.setText(topQuestion);
        questionBottom.setText(bottomQuestion);
        answerEditText.setHint(answerHint);


        changeDoneFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        answerEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().isEmpty())
                    changeDoneFab.show();
                else
                    changeDoneFab.hide();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}

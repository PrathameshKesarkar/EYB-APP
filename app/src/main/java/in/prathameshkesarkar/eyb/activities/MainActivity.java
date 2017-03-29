package in.prathameshkesarkar.eyb.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import in.prathameshkesarkar.eyb.R;

/**
 * Created by prathameshkesarkar on 21/03/17.
 */

public class MainActivity extends AppCompatActivity {


    FrameLayout shortBioButton,workButton,educationButton,emailButton,phoneButton,githubButton,linkedButton;
    LinearLayout profileButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_profile);

        shortBioButton = (FrameLayout) findViewById(R.id.bio_button);
        workButton = (FrameLayout) findViewById(R.id.work_button);
        educationButton = (FrameLayout) findViewById(R.id.education_button);
        emailButton = (FrameLayout) findViewById(R.id.email_button);
        phoneButton = (FrameLayout) findViewById(R.id.phone_button);
        githubButton = (FrameLayout) findViewById(R.id.github_button);
        linkedButton = (FrameLayout) findViewById(R.id.linked_button);
        profileButton = (LinearLayout) findViewById(R.id.user_info_ll);

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ActivityPersonalInfo.class);
                startActivity(intent);
            }
        });


        shortBioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,QuestionActivity.class);
                intent.putExtra("QuestionTop","Tell Us");
                intent.putExtra("QuestionBottom","a bit about yourself");
                intent.putExtra("AnswerHint","eg I am a content marketer with through knowlede in b2c,blogging,social media and content writing");
                intent.putExtra("QuestionColor",R.color.bio_color_primary);
                intent.putExtra("AnswerColor",R.color.bio_color_dark);

                startActivity(intent);
            }
        });

        workButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,QuestionActivity.class);
                intent.putExtra("QuestionTop","Let's add a new Internship!");
                intent.putExtra("QuestionBottom","a bit about your past Internship");
                intent.putExtra("AnswerHint","eg I was working on drafting the news article for the xyz new's paper company.Also used to cover their blogpost at the tine of my internship");
                intent.putExtra("QuestionColor",R.color.work_color_primary);
                intent.putExtra("AnswerColor",R.color.work_color_dark);

                startActivity(intent);
            }
        });

        educationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,QuestionActivity.class);
                intent.putExtra("QuestionTop","Tell Us,");
                intent.putExtra("QuestionBottom","where did you study?");
                intent.putExtra("AnswerHint","I studied at \n school,university");
                intent.putExtra("QuestionColor",R.color.education_color_primary);
                intent.putExtra("AnswerColor",R.color.education_color_dark);

                startActivity(intent);
            }
        });
        phoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,QuestionActivity.class);
                intent.putExtra("QuestionTop","What' your");
                intent.putExtra("QuestionBottom","mobile number ?");
                intent.putExtra("AnswerHint","+91 98765 98765");
                intent.putExtra("QuestionColor",R.color.mobile_color_primark);
                intent.putExtra("AnswerColor",R.color.mobile_color_dark);

                startActivity(intent);
            }
        });
        githubButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,QuestionActivity.class);
                intent.putExtra("QuestionTop","Show off your");
                intent.putExtra("QuestionBottom","next level coding skills");
                intent.putExtra("AnswerHint","Your Github Link");
                intent.putExtra("QuestionColor",R.color.github_color_primary);
                intent.putExtra("AnswerColor",R.color.github_color_dark);

                startActivity(intent);

            }
        });

        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,QuestionActivity.class);
                intent.putExtra("QuestionTop"," ");
                intent.putExtra("QuestionBottom","What's your email?");
                intent.putExtra("AnswerHint","My email is - me@mail.com");
                intent.putExtra("QuestionColor",R.color.email_color_primary);
                intent.putExtra("AnswerColor",R.color.email_color_dark);

                startActivity(intent);
            }
        });

        linkedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,QuestionActivity.class);
                intent.putExtra("QuestionTop","Show off your");
                intent.putExtra("QuestionBottom","endrosments & connection");
                intent.putExtra("AnswerHint","Your LinkedIn Url");
                intent.putExtra("QuestionColor",R.color.linked_color_primary);
                intent.putExtra("AnswerColor",R.color.linked_color_dark);

                startActivity(intent);
            }
        });
    }
}

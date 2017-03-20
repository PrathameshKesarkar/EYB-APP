package in.prathameshkesarkar.eyb.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import java.util.ArrayList;

import in.prathameshkesarkar.eyb.R;
import in.prathameshkesarkar.eyb.adapter.StudentAdapter;
import in.prathameshkesarkar.eyb.model.StudentGridItem;

/**
 * Created by prathameshkesarkar on 08/02/17.
 */

public class StudentModuleActivity extends AppCompatActivity {

    GridView studentGridView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_module);
        studentGridView = (GridView) findViewById(R.id.gridview);

        StudentGridItem profileItem = new StudentGridItem("Profile", R.drawable.ic_account_box_white_36dp);
        StudentGridItem assignmentItem = new StudentGridItem("Assignment", R.drawable.ic_assignment_white_36dp);
        StudentGridItem eventItem = new StudentGridItem("Event", R.drawable.ic_event_white_36dp);
        StudentGridItem galleryItem = new StudentGridItem("Gallery", R.drawable.ic_photo_library_white_36dp);
        ArrayList<StudentGridItem> list = new ArrayList<>();
        list.add(profileItem);
        list.add(assignmentItem);
        list.add(eventItem);
        list.add(galleryItem);

        StudentAdapter adapter = new StudentAdapter(this,list);
        studentGridView.setAdapter(adapter);
    }
}

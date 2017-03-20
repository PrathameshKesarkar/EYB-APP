package in.prathameshkesarkar.eyb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import in.prathameshkesarkar.eyb.R;
import in.prathameshkesarkar.eyb.model.StudentGridItem;

/**
 * Created by prathameshkesarkar on 08/02/17.
 */

public class StudentAdapter extends BaseAdapter {
    private Context mContext;
    private List<StudentGridItem> gridItems;

    public StudentAdapter(Context mContext, List<StudentGridItem> gridItems) {
        this.mContext = mContext;
        this.gridItems = gridItems;
    }

    @Override
    public int getCount() {
        return gridItems.size();
    }

    @Override
    public StudentGridItem getItem(int position) {
        return gridItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        StudentGridItem studentItem = getItem(position);
        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.single_student_item, null, false);
        }
        ImageView studentItemimageView = (ImageView) convertView.findViewById(R.id.student_image_item);
        TextView studentItemTextView = (TextView) convertView.findViewById(R.id.student_text_item);

        studentItemimageView.setImageResource(studentItem.getItemId());
        studentItemTextView.setText(studentItem.getItemName());
        return convertView;
    }
}

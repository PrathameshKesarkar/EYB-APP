package in.prathameshkesarkar.eyb.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import in.prathameshkesarkar.eyb.R;

/**
 * Created by prathameshkesarkar on 04/04/17.
 */

public class ProfileGridView extends FrameLayout {
    public ProfileGridView(@NonNull Context context) {
        super(context);
        initView(context,null);
    }

    public ProfileGridView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context,attrs);

    }

    public ProfileGridView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context,attrs);
    }

    @TargetApi(21)
    public ProfileGridView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context,attrs);
    }

    public void initView(Context context, AttributeSet attrs) {
        TextView gridTextView;
        View view = inflate(context, R.layout.square_grid, null);
        gridTextView = (TextView) view.findViewById(R.id.grid_description);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ProfileGridView);
        Drawable drawable = array.getDrawable(R.styleable.ProfileGridView_drawble);
        String description = array.getString(R.styleable.ProfileGridView_grid_description);

        gridTextView.setText(description);
        gridTextView.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
        addView(view);
        array.recycle();
    }
}

package infinity1087.android.com.examplehr.ViewPager;

import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import infinity1087.android.com.examplehr.R;

public class ViewPager extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNav);
    }
}

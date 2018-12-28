package infinity1087.android.com.examplehr;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import infinity1087.android.com.examplehr.ProductDetalModel.PriceDetails;
import infinity1087.android.com.examplehr.adapter.RecyclerItems;
import infinity1087.android.com.examplehr.ProductDetalModel.ResponseDetail;

public class detailLayout extends AppCompatActivity implements AdapterView.OnItemSelectedListener,NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView mRecyclerView;
    private RecyclerItems mAdapter;
    private List<ResponseDetail> mData;
    private List<PriceDetails>  mprice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_navigation_trial);
        Intent i = getIntent();
        mData  = (List<ResponseDetail>) i.getSerializableExtra("yyy");
        mprice = (List<PriceDetails>) i.getSerializableExtra("zzz");
        Log.d("ttt", String.valueOf(mData));
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar_detail);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_detail);

        // Spinner click listener
       // spinner.setOnItemSelectedListener(this);

        List<String> categories = new ArrayList<String>();

//        categories.add("50g");
//        categories.add("250g");
//        categories.add("500g");
//        categories.add("1Kg");

        @SuppressLint("ResourceType") ArrayAdapter<String> dataA = new ArrayAdapter<String>(this,R.id.spinner);
        mRecyclerView = findViewById(R.id.recycler_view_detail);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(detailLayout.this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setNestedScrollingEnabled(false);
        setUpRecyclerView(mData,mprice);

        navigationView.setNavigationItemSelectedListener(this);

    }

    private void setUpRecyclerView(List<ResponseDetail> datumList, List<PriceDetails> priceDetails) {

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);

        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new RecyclerItems(datumList,priceDetails);
        mRecyclerView.setAdapter(mAdapter);

    }


    //TODO:StrikeThru
    private void strikeThroughText(TextView price){

        price.setPaintFlags(price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String item = String.valueOf(parent.getItemIdAtPosition(position));
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void btnAddtoCart(View view) {

        Intent i = new Intent(detailLayout.this,MyCart.class);
        startActivity(i);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }
}

package com.werq.patient.Activities;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.werq.patient.Adapters.DoctorTeamAdapter;
import com.werq.patient.Adapters.StackImagesAdapter;
import com.werq.patient.Interfaces.RecyclerViewClickListerner;
import com.werq.patient.R;
import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.RecyclerViewHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchByNameActivity extends AppCompatActivity implements RecyclerViewClickListerner {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rvDoctorTeam)
    RecyclerView rvDoctorTeam;
    @BindView(R.id.search_view)
    MaterialSearchView searchView;
    @BindView(R.id.toolbar_container)
    FrameLayout toolbarContainer;
    private StackImagesAdapter stackImageView;
    Context mContext;
    private DoctorTeamAdapter doctorTeamAdapter;
    private SearchView.OnQueryTextListener queryTextListener;
    RecyclerViewClickListerner recyclerViewClickListerner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_name);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        setToolBar();

        inilizevariables();
        searchView.setVoiceSearch(false);
        searchView.setEllipsize(true);
        setDoctorList();


        //searchView = (SearchView) searchItem.getActionView();
    }

    private void setDoctorList() {
        RecyclerViewHelper.setAdapterToRecylerView(mContext, rvDoctorTeam, doctorTeamAdapter);
        RecyclerViewHelper.setAdapterToRecylerViewwithanimation(mContext, rvDoctorTeam);
    }

    private void setToolBar() {
        Helper.setToolbarwithBack(getSupportActionBar(), "Doctor Name");

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchview, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        if (searchItem != null) {
            searchView.setMenuItem(searchItem);
        }
 /*       if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

            queryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String newText) {
                    Log.i("onQueryTextChange", newText);

                    return true;
                }
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Log.i("onQueryTextSubmit", query);

                    return true;
                }
            };
            searchView.setOnQueryTextListener(queryTextListener);
        }*/
        return true;
    }


    /*  @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.searchview, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);

        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

            queryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String newText) {
                    Log.i("onQueryTextChange", newText);

                    return true;
                }
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Log.i("onQueryTextSubmit", query);

                    return true;
                }
            };
            searchView.setOnQueryTextListener(queryTextListener);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }*/

    private void inilizevariables() {
        //context
        mContext = this;

        //listner
        recyclerViewClickListerner = this;

        //adapters
        doctorTeamAdapter = new DoctorTeamAdapter(mContext, true, recyclerViewClickListerner);
    }


    private ArrayList<Integer> setImageResources() {
        ArrayList<Integer> imageLists = new ArrayList<>();
        imageLists.add(R.drawable.imageone);
        imageLists.add(R.drawable.imagetwo);
        imageLists.add(R.drawable.imagetwo);
        imageLists.add(R.drawable.imageone);
        return imageLists;
    }


    @Override
    public void onclick(int position) {

    }
}

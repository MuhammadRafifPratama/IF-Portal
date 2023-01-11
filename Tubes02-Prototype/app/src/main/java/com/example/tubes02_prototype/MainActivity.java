package com.example.tubes02_prototype;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.tubes02_prototype.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IMainActivity{
    ActivityMainBinding binding;
    DrawerLayout drawer;
    NavigationView navigationView;
    PengumumanFragment pengumumanFragment;
    FragmentManager fragmentManager;
    MainPresenter presenter;
    BottomNavigationView bottomNavigationView;
    PengumumanBaruFormFragment fragmentPengumumanBaruForm;
    PengumumanDetailFragment pengumumanDetailFragment;
    PertemuanFragment pertemuanFragment;
    PengumumanAdapter pengumumanAdapter;
    PertemuanAdapter pertemuanAdapter;

    Fragment frsFragment;
    FRSAdapter frsAdapter;
    String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);

        this.role = getIntent().getStringExtra("role");
        Log.d("role", "role: "+this.role);

        String email = getIntent().getStringExtra("email");
        String password = getIntent().getStringExtra("password");
        String token = getIntent().getStringExtra("token");
        Log.d("token", token);
        LoginUser user = new LoginUser(email, password, role,token);

        VolleyMain volleyMain = new VolleyMain(this, this);
        this.presenter = new MainPresenter(this, user, volleyMain);

        this.fragmentManager = this.getSupportFragmentManager();
        this.pengumumanAdapter = new PengumumanAdapter(this.presenter, this.fragmentManager);
        this.pengumumanFragment = PengumumanFragment.newInstance(this.presenter, fragmentManager);
        this.pengumumanDetailFragment = PengumumanDetailFragment.newInstance(this.presenter, fragmentManager);

        this.pertemuanAdapter = new PertemuanAdapter(this.presenter, this.fragmentManager);
        this.pertemuanFragment = PertemuanFragment.newInstance(this.presenter, fragmentManager, pertemuanAdapter);


        this.frsAdapter = new FRSAdapter(this.presenter, this.fragmentManager);
        this.frsFragment = FRSFragment.newInstance(this.presenter, fragmentManager, frsAdapter);

        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        ft.add(binding.fragmentsContainer.getId(), this.pengumumanFragment).commit();

        this.drawer = binding.drawerLayout;
        this.bottomNavigationView = binding.bottomToolbar;
        this.bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.btn_home:
                        fragment = pengumumanFragment;
                        break;

                    //tambahin navigation buat Appointment dan FRS
                    case R.id.btn_appoint:
                        fragment = pertemuanFragment;
                        break;

                    case R.id.btn_frs:
                        fragment = frsFragment;
                        break;
                }

                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragments_container,fragment).commit();

                item.setChecked(true);
                return true;
            }
        });
    }



    @Override
    public void updatePengumumanList(List<Pengumuman> pengumumans, String cursor) {
        Log.d("debug", String.valueOf(pengumumans.size()));
        pengumumanFragment.setList(pengumumans, cursor);
    }

    @Override
    public void updateTagList(List<Tags> tags){
        pengumumanFragment.setTagList(tags);
    }

    @Override
    public void setDetailPengumuman(PengumumanDetail pengumuman) {
        pengumumanDetailFragment.setDetail(pengumuman);
    }

    @Override
    public void responNewPengumuman(PengumumanDetail pengumumanDetail) {

    }

//    @Override
//    public void updateTagListForm(List<Tags> tags) {
//        fragmentPengumumanBaruForm.setList(tags);
//    }

    @Override
    public void getPertemuan() throws JSONException {
        VolleyPertemuan task = new VolleyPertemuan(this.presenter, this);
        task.execute();
    }

    @Override
    public void updatePertemuan(ArrayList<Pertemuan> pertemuans) {
        pertemuanFragment.updateListPertemuan(pertemuans);
    }


}
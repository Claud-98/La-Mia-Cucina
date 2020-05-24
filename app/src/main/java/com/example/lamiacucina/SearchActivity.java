package com.example.lamiacucina;


import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.example.lamiacucina.databinding.ActivitySearchBinding;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;


public class SearchActivity extends AppCompatActivity {
    private String TAG = "SearchActivity";
    private ActivitySearchBinding binding;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
    /*
        androidx.appcompat.widget.Toolbar toolbar;
        toolbar = binding.searchActivityToolbar;

        setSupportActionBar(toolbar);
*/
        setContentView(view);

        NavController navController = Navigation.findNavController(this, R.id.navHostfragment);


        androidx.appcompat.widget.Toolbar toolbar;
        toolbar = binding.searchActivityToolbar;
        setSupportActionBar(toolbar);


    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {

        getMenuInflater().inflate(R.menu.menu_search,menu);
        return true;

    }


}

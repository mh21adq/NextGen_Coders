package com.example.next;

import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.next.databinding.ActivityNavigationBinding;

/**
 * NavigationActivity sets up the navigation framework for the application
 * using a BottomNavigationView and the Android Jetpack Navigation component.
 */
public class NavigationActivity extends AppCompatActivity {

    /**
     * Binding instance for accessing the layout views.
     */
    private ActivityNavigationBinding binding;

    /**
     * Initializes the activity, sets up the navigation components and links
     * BottomNavigationView with NavController.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle contains the data it most
     *                           recently supplied in onSaveInstanceState(Bundle). Note: Otherwise it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate the layout for this activity
        binding = ActivityNavigationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize the BottomNavigationView
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Define AppBarConfiguration with IDs of top level destinations in the nav graph
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home,R.id.navigation_examples,R.id.navigation_compiler,
                R.id.navigation_chat_bot,R.id.navigation_user)
                .build();
        // Find the NavController for the NavHostFragment
        NavController navController = Navigation.findNavController
                (this, R.id.nav_host_fragment_activity_navigation);
        // Set up BottomNavigationView to work with NavController
        NavigationUI.setupWithNavController(binding.navView, navController);
    }
}

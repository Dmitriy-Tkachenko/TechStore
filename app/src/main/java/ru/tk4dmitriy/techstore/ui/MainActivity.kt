package ru.tk4dmitriy.techstore.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.tk4dmitriy.features.products.api.FeatureProductsApi
import ru.tk4dmitriy.features.products.ui.ProductsFragment
import ru.tk4dmitriy.techstore.App
import ru.tk4dmitriy.techstore.R
import ru.tk4dmitriy.techstore.databinding.ActivityMainBinding
import javax.inject.Inject
import javax.inject.Provider

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var featureProductsApi: Provider<FeatureProductsApi>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        (application as App).appComponent.inject(this)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, ProductsFragment(),featureProductsApi.get().getTagFragment())
                .commit()
        }
    }
}
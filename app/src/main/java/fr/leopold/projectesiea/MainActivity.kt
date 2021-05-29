package fr.leopold.projectesiea

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

import fr.leopold.projectesiea.fragments.AddPlantFragment
import fr.leopold.projectesiea.fragments.BoutiquePageFragment
import fr.leopold.projectesiea.fragments.CollectionFragment
import fr.leopold.projectesiea.fragments.HomeFragment
import fr.leopold.projectesiea.presentation.PokemonListFragment

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment(HomeFragment(this),R.string.home_page_title)

        //importer la bottomnavigationView
        val navigationView = findViewById<BottomNavigationView>(R.id.navigation_view)
        navigationView.setOnNavigationItemSelectedListener{
            when(it.itemId)
            {
                R.id.home_page -> {
                    loadFragment(HomeFragment(this),R.string.home_page_title)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.collection_page -> {
                    loadFragment(CollectionFragment(this),R.string.collection_page_title)
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.add_plant_page -> {
                    loadFragment(AddPlantFragment(this),R.string.add_pant_page_title)
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.add_boutique_page ->{
                    loadFragment(PokemonListFragment(this), R.string.bottom_add_boutique_item)
                    return@setOnNavigationItemSelectedListener true
                }




                else ->false
            }

        }


        loadFragment(HomeFragment(this),R.string.home_page_title)



    }

    private fun loadFragment(fragment: Fragment, string: Int) {
        // charger notre PlantRepository

        val repo = PlantRepository()
        // actualiser le titre de la page
        findViewById<TextView>(R.id.page_title).text = resources.getString(string)
        //mettre à jour la liste des plantes
        repo.updateData {

            //injecter le fragment dans notre boite(fragement_container)
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }


}
}



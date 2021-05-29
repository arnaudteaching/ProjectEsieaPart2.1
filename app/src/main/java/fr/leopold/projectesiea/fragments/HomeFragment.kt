package fr.leopold.projectesiea.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fr.leopold.projectesiea.MainActivity
import fr.leopold.projectesiea.PlantRepository.Singleton.plantList
import fr.leopold.projectesiea.PlanteModel
import fr.leopold.projectesiea.R
import fr.leopold.projectesiea.adapter.PlantAdapter
import fr.leopold.projectesiea.adapter.PlantItemDecoration

class HomeFragment(
    private val context:MainActivity)
    : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view =  inflater?.inflate(R.layout.fragment_home, container , false)
        // recuperer le recyclerview
        val horizontalRecyclerView = view.findViewById<RecyclerView>(R.id.horizontal_recycler_view)
        horizontalRecyclerView.adapter = PlantAdapter(context,plantList.filter{ !it.liked },R.layout.item_horizontal_plant)

        val verticalRecyclerView = view.findViewById<RecyclerView>(R.id.vertical_recycler_view)
         verticalRecyclerView.adapter = PlantAdapter(context,plantList,R.layout.item_vertical_plante)
        verticalRecyclerView.addItemDecoration(PlantItemDecoration())

        return view
    }

}
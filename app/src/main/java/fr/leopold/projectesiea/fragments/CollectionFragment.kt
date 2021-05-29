package fr.leopold.projectesiea.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.leopold.projectesiea.MainActivity
import fr.leopold.projectesiea.PlantRepository.Singleton.plantList
import fr.leopold.projectesiea.R
import fr.leopold.projectesiea.adapter.PlantAdapter
import fr.leopold.projectesiea.adapter.PlantItemDecoration

class CollectionFragment(private val context:MainActivity)
    :Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater?.inflate(R.layout.fragment_collection, container, false)

        //recupérer ma recyclreview
        val collectionRecyclerView = view.findViewById<RecyclerView>(R.id.collection_recycler_list)
        collectionRecyclerView.adapter = PlantAdapter(context, plantList.filter{it.liked} , R.layout.item_vertical_plante)
        collectionRecyclerView.layoutManager = LinearLayoutManager(context)
        collectionRecyclerView.addItemDecoration(PlantItemDecoration())



        return view
    }

}
package fr.leopold.projectesiea.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.leopold.projectesiea.*

class PlantAdapter(val context: MainActivity, private val plantList: List<PlanteModel>, private val layoutId: Int) : RecyclerView.Adapter<PlantAdapter.ViewHolder>() {
    // boite pour ranger tt les composants à controler
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        //Image de la plante
        val planteImage = view.findViewById<ImageView>(R.id.image_item)
        val plantName:TextView? = view.findViewById<TextView>(R.id.name_item)
        val plantDescription:TextView? = view.findViewById<TextView>(R.id.description_item)
        val starIcon = view.findViewById<ImageView>(R.id.star_icon)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(layoutId, parent, false)
        return ViewHolder(view)
    }
//Mettre à jour chaque plante
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //récupérer les informations de la plante
        val currentPlant = plantList[position]

        //recupérer le repositery

        val repo = PlantRepository()

        //utiliser glide pour récupérer l'image à partir de son lien -> composant
        Glide.with(context).load(Uri.parse(currentPlant.imageUrl)).into(holder.planteImage)

        //Mettre à jour le nom de la plante
        holder.plantName?.text = currentPlant.name

        //Mettre à jour la description de la plante
        holder.plantDescription?.text = currentPlant.description

        //Verifier si la plante a été liked ou non
        if(currentPlant.liked) {

            holder.starIcon.setImageResource(R.drawable.ic_star)
        }
    else {
            holder.starIcon.setImageResource(R.drawable.ic_unstar)
    }
           //Rajouter une interaction sur cette etoile
        holder.starIcon.setOnClickListener{
            //inverse si le bouton is liked ou non
            currentPlant.liked = !currentPlant.liked

            //mettre à jour l'objet plante

            repo.updatePlant(currentPlant)
        }
        //interaction lors d'un clic sur une plante
        holder.itemView.setOnClickListener{
            PlantPopup(this,currentPlant).show()
        }
        }


//renvoie cb d'item on veut renvoyer
    override fun getItemCount(): Int = plantList.size
}
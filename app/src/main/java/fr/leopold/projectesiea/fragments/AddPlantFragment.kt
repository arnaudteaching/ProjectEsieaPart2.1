package fr.leopold.projectesiea.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import fr.leopold.projectesiea.MainActivity
import fr.leopold.projectesiea.PlantRepository
import fr.leopold.projectesiea.PlantRepository.Singleton.downloadUri
import fr.leopold.projectesiea.PlanteModel
import fr.leopold.projectesiea.R
import java.util.*

class AddPlantFragment(private val context : MainActivity) : Fragment() {
    private var file:Uri? = null
    private var uploadImage:ImageView? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
       val view =inflater?.inflate(R.layout.fragment_add_plant,container,false)

        //Récupérer uploadImage pour lui associer son composant
        uploadImage = view.findViewById(R.id.preview_image)
        //Récupérer le bouton de l'image
        val pickupImageButton = view.findViewById<Button>(R.id.upload_button)

        //lordqu'on clique dessus ça ouvre les images du telphone
        pickupImageButton.setOnClickListener{ pickupImage() }

        //Récupérer le bouton confirmer
        val confirmButton = view.findViewById<Button>(R.id.confirm_button)
        confirmButton.setOnClickListener{ sendForm(view) }
        return view
    }

    private fun sendForm(view:View) {

        val repo = PlantRepository()
        repo.uploadImage(file!!) {

            val plantName = view.findViewById<EditText>(R.id.name_input).text.toString()
            val plantDescription =
                view.findViewById<EditText>(R.id.description_input).text.toString()
            val grow = view.findViewById<Spinner>(R.id.grow_spinner).selectedItem.toString()
            val water = view.findViewById<Spinner>(R.id.water_spinner).selectedItem.toString()
            val downloadImageUrl = downloadUri

            //créer un nouvel object plantModel

            val plant = PlanteModel(
                UUID.randomUUID().toString(),
                plantName,
                plantDescription,
                downloadImageUrl.toString(),
                grow,
                water
            )
            //envoyer en bbd
            repo.insertPlant(plant)
        }

    }

    private fun pickupImage() {
        val intent = Intent()
        intent.type = "image/"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "select Picture"), 47)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 47 && resultCode == Activity.RESULT_OK){
            //Vérifier si les données sont nulles
            if(data == null || data.data == null) return

            //recuperer l'image
            val file = data.data

            //mettre à jour l'aperçu de l'image
            uploadImage?.setImageURI(file)

           
        }
    }


}
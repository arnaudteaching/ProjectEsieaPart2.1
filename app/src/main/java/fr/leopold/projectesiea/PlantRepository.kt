package fr.leopold.projectesiea

import android.net.Uri
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import fr.leopold.projectesiea.PlantRepository.Singleton.databaseRef
import fr.leopold.projectesiea.PlantRepository.Singleton.downloadUri
import fr.leopold.projectesiea.PlantRepository.Singleton.plantList
import fr.leopold.projectesiea.PlantRepository.Singleton.storageReference
import java.util.*

class PlantRepository {
    object Singleton {
        //donner le lien pour acceder au bucket
        private val BUCKET_URL :String = "gs://plantecollection-dc226.appspot.com"
        //se connecter à notre espace de stockage
        val storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(BUCKET_URL)
        // se connecter à la référence plante

        val databaseRef = FirebaseDatabase.getInstance().getReference("plants")

        //créer une liste qui va contenir nos plantes

        val plantList = arrayListOf<PlanteModel>()

        //contenir le lien de l'image courante
        var downloadUri :Uri? =null
    }
    fun updateData(callback:() -> Unit){
        //absorber les données depuis la databaseRef -> liste de plante

        databaseRef.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
               //retirer les anciennes
                plantList.clear()
                //Recolter la liste
                for(ds in snapshot.children){
                    //contruire un objet plante
                    val plant = ds.getValue(PlanteModel :: class.java)

                    //Verifier que la plante n'est pas nul
                    if(plant != null ){
                        plantList.add(plant)
                    }
                }
                //Actioner le callback
                callback()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }
    //créer une fonction pour envoyer des fichers sur le storage
    fun uploadImage(file: Uri, callback: () -> Unit){
        //Vérifier si file n'est pas null
        if(file!= null){
            val fileName = UUID.randomUUID().toString() + ".jpg"
            val ref = storageReference.child(fileName)
            val uploadTask = ref.putFile(file)

            // démarer la tache d'envoi
            uploadTask.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
                //Vérifier si il y a au un problème lors de l'envoi du fichier
                if(!task.isSuccessful) {
                    task.exception?.let { throw it }

                }
                return@Continuation ref.downloadUrl
            })
                .addOnCompleteListener{ task ->
                //Vérifier si tout a bien fonctionner
                if(task.isSuccessful){
                    //récupérer l'image
                    downloadUri= task.result
                    callback()
                }
            }
        }

    }
    fun updatePlant(plant: PlanteModel) = databaseRef.child(plant.id).setValue(plant)

// inserer une nv plante en bdd
    fun insertPlant(plant: PlanteModel) = databaseRef.child(plant.id).setValue(plant)

    //suprimer une plante de la base
    fun deletePlant(plant: PlanteModel) = databaseRef.child(plant.id).removeValue()
}
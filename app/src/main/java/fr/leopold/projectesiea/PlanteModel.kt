package fr.leopold.projectesiea
/*modéle tiré du tuto sur yt de graven, j'ai gardé la liste plant dans "Firebase" pour plus de simplicité */

class PlanteModel (
    val id : String = "pokemon0",
    val name:String = "Pikachu",
    val description:String = "Petite Description",
    val imageUrl:String = "http://graven.yt/plante.jpg",
    val type:String = "electric",
    val entretien: String = "Moyenne",
    var liked :Boolean = false


        ) {

}

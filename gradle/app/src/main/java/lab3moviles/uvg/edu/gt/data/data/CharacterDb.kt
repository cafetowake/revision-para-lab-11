package lab3moviles.uvg.edu.gt.data.data

import com.uvg.rickandmorty.R
import lab3moviles.uvg.edu.gt.data.dc.Person

class CharacterDb {
    private val people: List<Person> = listOf(
        Person(1, "Rick Sanchez", "Alive", "Human", "Male", R.drawable.rick_sanchez.toString()),
        Person(2, "Morty Smith", "Alive", "Human", "Male", R.drawable.morty_smith.toString()),
        Person(3, "Summer Smith", "Alive", "Human", "Female", R.drawable.summer_smith.toString()),
        Person(4, "Beth Smith", "Alive", "Human", "Female", R.drawable.beth_smith.toString()),
        Person(5, "Jerry Smith", "Alive", "Human", "Male", R.drawable.jerry_smith.toString()),
        Person(6, "Abadango Cluster Princess", "Alive", "Alien", "Female", R.drawable.abadango_cluster_princess.toString()),
        Person(7, "Abradolf Lincler", "unknown", "Human", "Male", R.drawable.abradolf_lincler.toString()),
        Person(8, "Adjudicator Rick", "Dead", "Human", "Male", R.drawable.adjudicator_rick.toString()),
        Person(9, "Agency Director", "Dead", "Human", "Male", R.drawable.agency_director.toString()),
        Person(10, "Alan Rails", "Dead", "Human", "Male", R.drawable.alan_rails.toString()),
        Person(11, "Albert Einstein", "Dead", "Human", "Male", R.drawable.albert_einstein.toString()),
        Person(12, "Alexander", "Dead", "Human", "Male", R.drawable.alexander.toString()),
        Person(13, "Alien Googah", "unknown", "Alien", "unknown", R.drawable.alien_googah.toString()),
        Person(14, "Alien Morty", "unknown", "Alien", "Male", R.drawable.alien_morty.toString()),
        Person(15, "Alien Rick", "unknown", "Alien", "Male", R.drawable.alien_rick.toString()),
        Person(16, "Amish Cyborg", "Dead", "Alien", "Male", R.drawable.amish_cyborg.toString()),
        Person(17, "Annie", "Alive", "Human", "Female", R.drawable.annie.toString()),
        Person(18, "Antenna Morty", "Alive", "Human", "Male", R.drawable.antenna_morty.toString()),
        Person(19, "Antenna Rick", "unknown", "Human", "Male", R.drawable.antenna_rick.toString()),
        Person(20, "Ants in my Eyes Johnson", "unknown", "Human", "Male", R.drawable.ants_in_my_eyes_johnson.toString())
    )

    fun getAllCharacters(): List<Person> {
        return people
    }

    fun getCharacterById(id: Int): Person {
        return people.first { it.id == id }
    }
}

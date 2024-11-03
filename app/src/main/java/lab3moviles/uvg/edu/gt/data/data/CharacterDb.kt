package lab3moviles.uvg.edu.gt.data.data

import lab3moviles.uvg.edu.gt.data.dc.Person

class CharacterDb {
    private val characters: List<Person> = listOf(
        Person(1, "Rick Sanchez", "Alive", "Human", "Male", "https://rickandmortyapi.com/api/character/avatar/1.jpeg"),
        Person(2, "Morty Smith", "Alive", "Human", "Male", "https://rickandmortyapi.com/api/character/avatar/2.jpeg"),
        Person(3, "Summer Smith", "Alive", "Human", "Female", "https://rickandmortyapi.com/api/character/avatar/3.jpeg"),
        Person(4, "Beth Smith", "Alive", "Human", "Female", "https://rickandmortyapi.com/api/character/avatar/4.jpeg"),
        Person(5, "Jerry Smith", "Alive", "Human", "Male", "https://rickandmortyapi.com/api/character/avatar/5.jpeg"),
        Person(6, "Abadango Cluster Princess", "Alive", "Alien", "Female", "https://rickandmortyapi.com/api/character/avatar/6.jpeg"),
        Person(7, "Abradolf Lincler", "unknown", "Human", "Male", "https://rickandmortyapi.com/api/character/avatar/7.jpeg"),
        Person(8, "Adjudicator Rick", "Dead", "Human", "Male", "https://rickandmortyapi.com/api/character/avatar/8.jpeg"),
        Person(9, "Agency Director", "Dead", "Human", "Male", "https://rickandmortyapi.com/api/character/avatar/9.jpeg"),
        Person(10, "Alan Rails", "Dead", "Human", "Male", "https://rickandmortyapi.com/api/character/avatar/10.jpeg"),
        Person(11, "Albert Einstein", "Dead", "Human", "Male", "https://rickandmortyapi.com/api/character/avatar/11.jpeg"),
        Person(12, "Alexander", "Dead", "Human", "Male", "https://rickandmortyapi.com/api/character/avatar/12.jpeg"),
        Person(13, "Alien Googah", "unknown", "Alien", "unknown", "https://rickandmortyapi.com/api/character/avatar/13.jpeg"),
        Person(14, "Alien Morty", "unknown", "Alien", "Male", "https://rickandmortyapi.com/api/character/avatar/14.jpeg"),
        Person(15, "Alien Rick", "unknown", "Alien", "Male", "https://rickandmortyapi.com/api/character/avatar/15.jpeg"),
        Person(16, "Amish Cyborg", "Dead", "Alien", "Male", "https://rickandmortyapi.com/api/character/avatar/16.jpeg"),
        Person(17, "Annie", "Alive", "Human", "Female", "https://rickandmortyapi.com/api/character/avatar/17.jpeg"),
        Person(18, "Antenna Morty", "Alive", "Human", "Male", "https://rickandmortyapi.com/api/character/avatar/18.jpeg"),
        Person(19, "Antenna Rick", "unknown", "Human", "Male", "https://rickandmortyapi.com/api/character/avatar/19.jpeg"),
        Person(20, "Ants in my Eyes Johnson", "unknown", "Human", "Male", "https://rickandmortyapi.com/api/character/avatar/20.jpeg")
    )

    fun getAllCharacters(): List<Person> {
        return characters
    }

    fun getCharacterById (id: Int): Person {
        return characters.first { it.id == id }
    }
}
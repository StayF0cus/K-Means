import scala.io.Source

class MatriceDonnees(private var matrice: Array[Donnees]){


    override def toString() : String = { // toString de MatriceDonnee
        var str = ""
        for (donnees <- this.matrice){
          str += donnees.toString + "\n"
        }
        return str
    }

    def getMatrice(i: Int): Donnees = { // recuperer l'element i dans la matrice
        return this.matrice(i)
    }

    def getMatrice(): Array[Donnees] = {
      return this.matrice
    }

    def getLongueur(): Int = {
        return this.matrice.length
    }
  }

package pt.ulusofona.cm.kotlin.challenge.models

import pt.ulusofona.cm.kotlin.challenge.interfaces.Movimentavel
import java.util.*

abstract class Veiculo(open val identificador: String) : Movimentavel {
    lateinit var posicao: Posicao
    lateinit var dataDeAquisicao : Date

    abstract fun requerCarta() : Boolean
}
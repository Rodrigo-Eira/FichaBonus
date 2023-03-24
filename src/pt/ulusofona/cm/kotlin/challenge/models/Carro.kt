package pt.ulusofona.cm.kotlin.challenge.models

import pt.ulusofona.cm.kotlin.challenge.exceptions.VeiculoDesligadoException
import pt.ulusofona.cm.kotlin.challenge.interfaces.Ligavel

class Carro(override val identificador : String, var motor: Motor) : Veiculo(identificador), Ligavel {
    override fun requerCarta(): Boolean {
        return true
    }

    override fun moverPara(x: Int, y: Int) {
        if(motor.estaLigado()){
            posicao.alterarPosicaoPara(x, y)
        }
        throw VeiculoDesligadoException()

    }

    override fun ligar() {
        motor.ligar()
    }

    override fun desligar() {
        motor.desligar()
    }

    override fun estaLigado(): Boolean {
        return motor.estaLigado()
    }

    override fun toString(): String {
        return "Carro | $identificador | $dataDeAquisicao | $posicao"
    }
}
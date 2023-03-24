package pt.ulusofona.cm.kotlin.challenge.models

import pt.ulusofona.cm.kotlin.challenge.exceptions.VeiculoDesligadoException

class Carro(override val indentificador : String, var motor: Motor) : Veiculo(indentificador) {
    override fun requerCarta(): Boolean {
        return true
    }

    override fun moverPara(x: Int, y: Int) {
        if(motor.estaLigado()){
            posicao.alterarPosicaoPara(x, y)
        }
        throw VeiculoDesligadoException()

    }

    override fun toString(): String {
        return "Carro | $indentificador | $dataDeAquisicao | $posicao"
    }
}
package pt.ulusofona.cm.kotlin.challenge.models

import pt.ulusofona.cm.kotlin.challenge.exceptions.MenorDeIdadeException
import pt.ulusofona.cm.kotlin.challenge.exceptions.PessoaSemCartaException
import pt.ulusofona.cm.kotlin.challenge.exceptions.VeiculoNaoEncontradoException
import java.time.Instant
import java.util.*
import kotlin.time.Duration.Companion.minutes

class Pessoa(var nome : String, var dataDeNascimento : Date){
    lateinit var veiculos : ArrayList<Veiculo>
    var carta: Carta? = null
    var posicao: Posicao = Posicao(0,0)

    override fun toString(): String {
        return "Pessoa | $nome | $dataDeNascimento | $posicao"
    }

    fun comprarVeiculo(veiculo: Veiculo){
        veiculo.dataDeAquisicao = Date.from(Instant.now())
        veiculos.add(veiculo)
    }

    fun pesquisarVeiculo(indentificador: String) : Veiculo{
        veiculos.forEach {
            if(it.indentificador == indentificador){
                return it
            }
        }
        throw VeiculoNaoEncontradoException()
    }

    fun venderVeiculo(veiculo: Veiculo, comprador:Pessoa){
        val veiculoPesquisado = pesquisarVeiculo(veiculo.indentificador)
        veiculos.remove(veiculoPesquisado)
        comprador.comprarVeiculo(veiculoPesquisado)
    }

    fun moverVeiculoPara(indentificador: String, x:Int, y:Int){
        val veiculo = pesquisarVeiculo(indentificador)
        if(!temCarta() && veiculo.requerCarta()){
            throw PessoaSemCartaException(nome)
        }
        veiculo.moverPara(x, y)
    }

    fun tirarCarta(){
        if(maiorDeIdade()){
            this.carta = Carta()
        }
        throw MenorDeIdadeException()
    }

    fun temCarta() : Boolean{
        return this.carta != null
    }

    fun maiorDeIdade(): Boolean {
        val now = Date()
        val idadeEmMilis = now.time - dataDeNascimento.time
        val idadeEmAnos = idadeEmMilis / 1000L / 60L / 60L / 24L / 365L
        return idadeEmAnos >= 18L
    }
}
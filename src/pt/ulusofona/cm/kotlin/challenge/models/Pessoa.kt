package pt.ulusofona.cm.kotlin.challenge.models

import pt.ulusofona.cm.kotlin.challenge.exceptions.MenorDeIdadeException
import pt.ulusofona.cm.kotlin.challenge.exceptions.PessoaSemCartaException
import pt.ulusofona.cm.kotlin.challenge.exceptions.VeiculoNaoEncontradoException
import pt.ulusofona.cm.kotlin.challenge.interfaces.Movimentavel
import java.time.Instant
import java.util.*

class Pessoa(var nome : String, var dataDeNascimento : Date) : Movimentavel{
    lateinit var veiculos : ArrayList<Veiculo>
    var carta: Carta? = null
    var posicao: Posicao = Posicao(0,0)

    override fun moverPara(x: Int, y: Int) {
        posicao.alterarPosicaoPara(x, y)
    }

    override fun toString(): String {
        return "Pessoa | $nome | $dataDeNascimento | $posicao"
    }

    fun comprarVeiculo(veiculo: Veiculo){
        veiculo.dataDeAquisicao = Date.from(Instant.now())
        veiculos.add(veiculo)
    }

    fun pesquisarVeiculo(indentificador: String) : Veiculo{
        for(veiculo in veiculos){
            if(veiculo.identificador == indentificador){
                return veiculo
            }
        }
        throw VeiculoNaoEncontradoException()
    }

    fun venderVeiculo(veiculo: Veiculo, comprador:Pessoa){
        val veiculoPesquisado = pesquisarVeiculo(veiculo.identificador)
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
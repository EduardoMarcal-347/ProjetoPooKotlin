// [Template no Kotlin Playground](https://pl.kotl.in/WcteahpyN)

enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

class Usuario(var nome: String?) {

    var conteudosFinalizados = mutableListOf<ConteudoEducacional>();
    var pontuacao: Int;
    init {
         pontuacao = 0;
    }

}

data class ConteudoEducacional(var nome: String, val duracao: Int = 60)

data class Formacao(val nome: String, var conteudos: List<ConteudoEducacional>) {

    val inscritos = mutableListOf<Usuario>()
    lateinit var nivel: Nivel;
    
    fun matricular(usuario: Usuario) {
        inscritos.add(usuario).also { println("O usuario ${usuario.nome} foi matriculado") };
    }

    fun cancelarMatricula(usuario: Usuario) {
        inscritos.remove(usuario).also { println("O usuario ${usuario.nome} teve a matricula cancelada") };
    }

    fun finalizarConteudo(usuario: Usuario, conteudoEducacional: ConteudoEducacional){
        if(!usuario.conteudosFinalizados.contains(conteudoEducacional)) {
            usuario.pontuacao += 50;
            usuario.conteudosFinalizados.add(conteudoEducacional);
        } else println("${conteudoEducacional.nome} já foi finalizado por ${usuario.nome}")
    }

    fun exibirMatriculados() {
        println("Matriculados no curso ${this.nome}")
        for (usuario in inscritos) {
            println(usuario.nome)
        }
    }

    fun exibirConteudos() {
        println("Conteudos do curso ${this.nome}")
        for (conteudo in conteudos){
            println("${conteudo.nome} duração: ${conteudo.duracao} minutos")
        }
    }

    fun exibirRankingUsuarios (){
        inscritos.sortBy { usuario -> usuario.pontuacao};
        for (index in inscritos.indices) {
            println("#$index - ${inscritos.get(index).nome} - Pontuação: ${inscritos.get(index).pontuacao}")
        }
    }

}

fun main() {

    
    TODO("Simule alguns cenários de teste. Para isso, crie alguns objetos usando as classes em questão.")
}

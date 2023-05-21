// [Template no Kotlin Playground](https://pl.kotl.in/WcteahpyN)

enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

class Usuario(var nome: String?) {

    var conteudosFinalizados = mutableListOf<ConteudoEducacional>();
    var pontuacao: Int;
    init {
         pontuacao = 0;
    }

}

data class ConteudoEducacional(var nome: String, val duracao: Int = 60){
    lateinit var nivel: Nivel
}

data class Formacao(val nome: String, var conteudos: List<ConteudoEducacional>) {

    val inscritos = mutableListOf<Usuario>()
    
    fun matricular(usuario: Usuario) {
        inscritos.add(usuario).also { println("O usuario ${usuario.nome} foi matriculado") };
    }

    fun cancelarMatricula(usuario: Usuario) {
        inscritos.remove(usuario).also { println("O usuario ${usuario.nome} teve a matricula cancelada") };
    }

    fun finalizarConteudo(usuario: Usuario, conteudoEducacional: ConteudoEducacional){
        if(!usuario.conteudosFinalizados.contains(conteudoEducacional)) {
            usuario.pontuacao += when(conteudoEducacional.nivel){
                Nivel.BASICO -> 25;
                Nivel.INTERMEDIARIO -> 50;
                Nivel.DIFICIL -> 75;
                else -> 0;
            }
            usuario.conteudosFinalizados.add(conteudoEducacional);
        } else println("${conteudoEducacional.nome} já foi finalizado por ${usuario.nome}")
    }

    fun exibirMatriculados() {
        println("---Matriculados no curso ${this.nome}---")
        for (usuario in inscritos) {
            println(usuario.nome)
        }
    }

    fun exibirConteudos() {
        println("---Conteudos do curso ${this.nome}---")
        for (conteudo in conteudos){
            println("${conteudo.nome} duração: ${conteudo.duracao} minutos")
        }
    }

    fun exibirRankingUsuarios (){
        println("---Ranking Pontuação Usuários---")
        inscritos.sortedByDescending { it.pontuacao };
        for (index in inscritos.indices) {
            println("#${index+1} - ${inscritos.get(index).nome} - Pontuação: ${inscritos.get(index).pontuacao}")
        }
    }

}

fun main() {
    val usuario1 = Usuario("Eduardo");
    val usuario2 = Usuario("Mateus");
    val usuario3 = Usuario("Larissa")
    val conteudo1 = ConteudoEducacional("java")
    val conteudo2 = ConteudoEducacional("Kotlin")
    val listaConteudos = mutableListOf<ConteudoEducacional>();
    listaConteudos.add(conteudo2);
    listaConteudos.add(conteudo1);
    val formacao = Formacao("Dev BackEnd", listaConteudos);
    conteudo1.nivel = Nivel.DIFICIL;
    conteudo2.nivel = Nivel.INTERMEDIARIO;

    formacao.matricular(usuario1);
    formacao.matricular(usuario2);
    formacao.matricular(usuario3);
    formacao.exibirMatriculados();

    formacao.finalizarConteudo(usuario1, formacao.conteudos.get(0));
    formacao.finalizarConteudo(usuario1, formacao.conteudos.get(1));
    formacao.finalizarConteudo(usuario2, formacao.conteudos.get(1));
    formacao.exibirRankingUsuarios();

    formacao.cancelarMatricula(usuario3);
    formacao.exibirMatriculados()

    formacao.exibirConteudos();
}

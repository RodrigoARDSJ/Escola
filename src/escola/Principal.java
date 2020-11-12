package escola;
import java.util.List;
import java.util.Scanner;
import escola.bd.AlunoBd;

public class Principal {
	public static void main(String[] args) {
		try (Scanner scan = new Scanner(System.in)) {
			int opcao = 0;
			do {
				System.out.println("|---------------------------------|");
				System.out.println("|          Escolas DOS            |");
				System.out.println("|     Digite a opção desejada:    |");
				System.out.println("| 1 - Cadastrar aluno             |");
				System.out.println("| 2 - Consultar turma             |");
				System.out.println("| 0 - Sair do Sistema             |");
				System.out.println("|---------------------------------|");

				System.out.print("> ");
				opcao = scan.nextInt();
				scan.nextLine();

				switch (opcao) {
				case 1 -> cadastrarAluno(scan);
				case 2 -> consultarTurma();
				}

				System.out.println("\n\n");

			} while (opcao != 0);

			System.out.println("Programa fechado com sucesso");
		}

	}

	private static void consultarTurma() {
		System.out.println("Consultar Turma");
		List<Alunos> alunos = new AlunoBd().consularTodos(); 
		System.out.println("Listando os alunos");
		alunos.forEach(System.out::println);
		System.out.println("|------ Fim da Consulta -------|");

	}

	private static void cadastrarAluno(Scanner scan) {
		System.out.println("|------ Cadastro do aluno -------|");
		System.out.println("Digite o Rm do Aluno:");
		int rm = scan.nextInt();
		scan.nextLine();
		System.out.println("Digite o nome do aluno:");
		String nome = scan.nextLine();
		System.out.println("Digite a nota 1 do aluno:");
		double nota1 = scan.nextDouble();
		scan.nextLine();
		System.out.println("Digite a nota 2 do aluno:");
		double nota2 = scan.nextDouble();
		scan.nextLine();
		
		Alunos aluno = new Alunos(rm, nome);
		aluno.setAtivo(true);
		aluno.setNota1(nota1);
		aluno.setNota2(nota2);	
		System.out.println(aluno);
		
		new AlunoBd().salva(aluno);
		System.out.println("|------ Fim do Cadastro -------|");

		
	}
}

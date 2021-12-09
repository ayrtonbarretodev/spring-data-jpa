package br.com.ayrton.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.ayrton.spring.data.orm.Unidade;
import br.com.ayrton.spring.data.repository.UnidadeTrabalhoRepository;

@Service
public class UnidadeTrabalhoService {
	private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;
	private Boolean system = true;

	public UnidadeTrabalhoService(UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
		this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
	}

	public void inicial(Scanner scanner) {
		while (system) {
			System.out.println("Qual ação deseja executar: ");
			System.out.println("0 - Sair");
			System.out.println("1 - Salvar");
			System.out.println("2 - Atualizar");
			System.out.println("3 - Visualizar");
			System.out.println("4 - Deletar");

			int acaoUsuario = scanner.nextInt();

			switch (acaoUsuario) {
			case 1:
				salvar(scanner);
				break;
			case 2:
				atualizar(scanner);
				break;
			case 3:
				visualizar();
				break;
			case 4:
				deletar(scanner);
				break;
			default:
				system = false;
				break;
			}
		}

	}

	private void deletar(Scanner scanner) {
		System.out.println("Digite o id da unidade a qual deseja deletar: ");
		int id = scanner.nextInt();
		unidadeTrabalhoRepository.deleteById(id);
		System.out.println("Deletado ...");

	}

	private void visualizar() {
		System.out.println("Lista das Unidades");

		Iterable<Unidade> unidades = unidadeTrabalhoRepository.findAll();
		unidades.forEach(unidade -> System.out.println(unidade));

	}

	private void atualizar(Scanner scanner) {
		System.out.println("Digite o id da unidade na qual deseja atualizar: ");
		int id = scanner.nextInt();

		System.out.println("Digite o novo nome da unidade");
		String nome = scanner.next();
		
		System.out.println("Digite o novo endereço da unidade");
		String endereco = scanner.next();

		Unidade unidade = new Unidade(id,nome,endereco);
		unidadeTrabalhoRepository.save(unidade);

	}

	private void salvar(Scanner scanner) {
		System.out.println("Digite o nome da unidade");
		String nome = scanner.next();

		System.out.println("Digite o endereço");
		String endereco = scanner.next();

		Unidade unidade = new Unidade(nome, endereco);
		unidadeTrabalhoRepository.save(unidade);
		System.out.println("Unidade Salva");

	}

}

package br.com.ayrton.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.ayrton.spring.data.orm.Cargo;
import br.com.ayrton.spring.data.repository.CargoRepository;

@Service
public class CargoService {
	private final CargoRepository cargoRepository;
	private Boolean system = true;

	public CargoService(CargoRepository cargoRepository) {
		this.cargoRepository = cargoRepository;
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

	private void salvar(Scanner scanner) {
		System.out.println("Descrição do cargo");
		String descricao = scanner.next();
		Cargo cargo = new Cargo(descricao);
		cargoRepository.save(cargo);
		System.out.println("Cargo Salvo");
	}

	private void atualizar(Scanner scanner) {
		System.out.println("Digite o id da descrição na qual deseja atualizar: ");
		int id = scanner.nextInt();

		System.out.println("Digite o novo nome da descrição do cargo");
		String descricao = scanner.next();

		Cargo cargo = new Cargo(id, descricao);
		cargoRepository.save(cargo);
	}

	private void visualizar() {
		System.out.println("Lista dos Cargos");
		
		Iterable<Cargo> cargos = cargoRepository.findAll();
		cargos.forEach(cargo -> System.out.println(cargo));

	}

	private void deletar(Scanner scanner) {
		System.out.println("Digite o id do cargo no qual deseja deletar: ");
		int id = scanner.nextInt();
		cargoRepository.deleteById(id);
		System.out.println("Deletado ...");
	}

}

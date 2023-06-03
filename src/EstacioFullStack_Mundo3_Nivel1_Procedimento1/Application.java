/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package EstacioFullStack_Mundo3_Nivel1_Procedimento1;

import EstacioFullStack_Mundo3_Nivel1_Procedimento1.model.PessoaFisica;
import EstacioFullStack_Mundo3_Nivel1_Procedimento1.model.PessoaJuridica;
import EstacioFullStack_Mundo3_Nivel1_Procedimento1.model.gerenciadores.PessoaFisicaRepo;
import EstacioFullStack_Mundo3_Nivel1_Procedimento1.model.gerenciadores.PessoaJuridicaRepo;

import java.io.IOException;

public class Application {

    public static void main(String[] args) {

        PessoaFisicaRepo pessoaFisicaRepo1 = new PessoaFisicaRepo();

        PessoaFisica pessoaFisica1 = new PessoaFisica(1, "John", "11111111111", 30);
        PessoaFisica pessoaFisica2 = new PessoaFisica(2, "Mary", "22222222222", 25);
        pessoaFisicaRepo1.inserir(pessoaFisica1);
        pessoaFisicaRepo1.inserir(pessoaFisica2);

        try {
            pessoaFisicaRepo1.persistir("pessoas_fisicas.dat");

            PessoaFisicaRepo pessoaFisicaRepo2 = new PessoaFisicaRepo();
            pessoaFisicaRepo2.recuperar("pessoas_fisicas.dat");

            for (PessoaFisica pessoaFisica : pessoaFisicaRepo2.obterTodos()) {
                pessoaFisica.exibir();
                System.out.println();
            }

        } catch (IOException | ClassNotFoundException erro) {
            System.out.println("Erro ao persistir ou recuperar os dados: " + erro.getMessage());
        }

        PessoaJuridicaRepo pessoaJuridicaRepo1 = new PessoaJuridicaRepo();

        PessoaJuridica pessoaJuridica1 = new PessoaJuridica(1, "X Corp", "1231231230");
        PessoaJuridica pessoaJuridica2 = new PessoaJuridica(2, "Acme LTDA", "4564564560");
        pessoaJuridicaRepo1.inserir(pessoaJuridica1);
        pessoaJuridicaRepo1.inserir(pessoaJuridica2);

        try {
            pessoaJuridicaRepo1.persistir("pessoas_juridicas.dat");

            PessoaJuridicaRepo pessoaJuridicaRepo2 = new PessoaJuridicaRepo();
            pessoaJuridicaRepo2.recuperar("pessoas_juridicas.dat");

            for (PessoaJuridica pessoaJuridica : pessoaJuridicaRepo2.obterTodos()) {
                pessoaJuridica.exibir();
                System.out.println();
            }
        } catch (IOException | ClassNotFoundException erro) {
            System.out.println("Erro ao persistir ou recuperar os dados: " + erro.getMessage());
        }
    }
}

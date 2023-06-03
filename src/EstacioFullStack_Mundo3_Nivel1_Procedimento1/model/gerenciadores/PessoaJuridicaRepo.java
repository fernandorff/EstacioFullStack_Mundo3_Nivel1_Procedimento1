package EstacioFullStack_Mundo3_Nivel1_Procedimento1.model.gerenciadores;

import EstacioFullStack_Mundo3_Nivel1_Procedimento1.model.PessoaJuridica;

import java.io.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class PessoaJuridicaRepo {
    private ArrayList<PessoaJuridica> pessoasJuridicas;

    public PessoaJuridicaRepo() {
        pessoasJuridicas = new ArrayList<>();
    }

    public void inserir(PessoaJuridica pessoaJuridica){
        pessoasJuridicas.add(pessoaJuridica);
    }

    public void alterar(PessoaJuridica pessoaJuridica, String novoNome, String novoCpf){
        pessoaJuridica.setNome(novoNome);
        pessoaJuridica.setCnpj(novoCpf);
    }

    public void excluir(int pessoaId){
        pessoasJuridicas.remove(obter(pessoaId));
    }

    public PessoaJuridica obter(int pessoaId) throws NoSuchElementException {
        for (PessoaJuridica pessoaJuridica: pessoasJuridicas) {
            if (pessoaJuridica.getId() == pessoaId) {
                return pessoaJuridica;
            }
        }
        throw new NoSuchElementException("Pessoa Jurídica com ID " + pessoaId + "não encontrada.");
    }

    public ArrayList<PessoaJuridica> obterTodos(){
        return pessoasJuridicas;
    }

    public void persistir(String nomeArquivo) throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(nomeArquivo));
        outputStream.writeObject(pessoasJuridicas);
        outputStream.close();
        System.out.println("Dados da pessoa jurídica armazenados.");
        System.out.println();
    }

    public void recuperar(String nomeArquivo) throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(nomeArquivo));
        pessoasJuridicas = (ArrayList<PessoaJuridica>) inputStream.readObject();
        inputStream.close();
        System.out.println("Dados da pessoa jurídica recuperados.");
        System.out.println();
    }
}
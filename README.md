![image](https://github.com/fernandorff/EstacioFullStack_Mundo3_Nivel1_Procedimento1/assets/101672271/0af36516-6d72-49f7-9e09-03b185642c8e)

## Centro Universitário Estácio do Ceará - Campus Centro

### Curso: Desenvolvimento Full Stack

#### Disciplina: Nível 1: Iniciando o Caminho Pelo Java

#### Número da Turma: 

#### Semestre Letivo: 3

#### Integrantes: Fernando Rocha Fonteles Filho

#### Repositorio Git: https://github.com/fernandorff/EstacioFullStack_Mundo3_Nivel1_Procedimento1

##

### Título da Prática: 1º Procedimento | Criação das Entidades e Sistema de Persistência

#### Objetivos da Prática: 

- Utilizar herança e polimorfismo na  definição de entidades.
- Utilizar persistência de objetos em arquivos binários.
- Implementar uma interface cadastral em modo texto.
- Utilizar o controle de exceções da plataforma Java.
- No final do projeto, o aluno terá implementado um sistema cadastral em Java,
utilizando os recursos da programação orientada a objetos e a persistência em
arquivos binários.

#### Códigos solicitados neste roteiro de aula:

- Classe Pessoa (Entidade)

```
package EstacioFullStack_Mundo3_Nivel1_Procedimento1.model;

import java.io.Serializable;

public class Pessoa implements Serializable {
    private int id;
    private String nome;

    public Pessoa() {}

    public Pessoa(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void exibir() {
        System.out.println("ID: " + id);
        System.out.println("Nome: " + nome);
    }
}
```

- Classe PessoaFisica (Entidade)

```
package EstacioFullStack_Mundo3_Nivel1_Procedimento1.model;

import java.io.Serializable;

public class PessoaFisica extends Pessoa implements Serializable {
    private String cpf;
    private int idade;

    public PessoaFisica() {}

    public PessoaFisica(int id, String nome, String cpf, int idade) {
        super(id, nome);
        this.cpf = cpf;
        this.idade = idade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    @Override
    public void exibir() {
        super.exibir();
        System.out.println("CPF: " + cpf);
        System.out.println("Idade: " + idade);
    }
}
```

- Classe PessoaJuridica (Entidade)

```
package EstacioFullStack_Mundo3_Nivel1_Procedimento1.model;

import java.io.Serializable;

public class PessoaJuridica extends Pessoa implements Serializable {
    private String cnpj;

    public PessoaJuridica() {}

    public PessoaJuridica(int id, String nome, String cnpj) {
        super(id, nome);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj){
        this.cnpj = cnpj;
    }

    @Override
    public void exibir() {
        super.exibir();
        System.out.println("CNPJ: " + cnpj);
    }
}
```

- Classe PessoaFisicaRepo (Gerenciador)

```
package EstacioFullStack_Mundo3_Nivel1_Procedimento1.model.gerenciadores;

import EstacioFullStack_Mundo3_Nivel1_Procedimento1.model.PessoaFisica;

import java.io.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class PessoaFisicaRepo {
     private ArrayList<PessoaFisica> pessoasFisicas;

     public PessoaFisicaRepo() {
         pessoasFisicas = new ArrayList<>();
     }

     public void inserir(PessoaFisica pessoaFisica){
        pessoasFisicas.add(pessoaFisica);
     }

     public void alterar(PessoaFisica pessoaFisica, String novoNome, String novoCpf, int novaIdade){
        pessoaFisica.setNome(novoNome);
        pessoaFisica.setCpf(novoCpf);
        pessoaFisica.setIdade(novaIdade);
     }

     public void excluir(int pessoaId){
         pessoasFisicas.remove(obter(pessoaId));
     }

     public PessoaFisica obter(int pessoaId) throws NoSuchElementException {
         for (PessoaFisica pessoaFisica: pessoasFisicas) {
             if (pessoaFisica.getId() == pessoaId) {
                 return pessoaFisica;
             }
         }
         throw new NoSuchElementException("Pessoa Física com ID " + pessoaId + "não encontrada.");
     }

     public ArrayList<PessoaFisica> obterTodos(){
         return pessoasFisicas;
    }

    public void persistir(String nomeArquivo) throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(nomeArquivo));
        outputStream.writeObject(pessoasFisicas);
        outputStream.close();
        System.out.println("Dados da pessoa física armazenados.");
        System.out.println();
    }

    public void recuperar(String nomeArquivo) throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(nomeArquivo));
        pessoasFisicas = (ArrayList<PessoaFisica>) inputStream.readObject();
        inputStream.close();
        System.out.println("Dados da pessoa física recuperados.");
        System.out.println();
    }
}
```

- Classe PessoaJuridicaRepo (Gerenciador)

```
package EstacioFullStack_Mundo3_Nivel1_Procedimento1.model.gerenciadores;

import EstacioFullStack_Mundo3_Nivel1_Procedimento1.model.PessoaFisica;

import java.io.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class PessoaFisicaRepo {
    private ArrayList<PessoaFisica> pessoasFisicas;

    public PessoaFisicaRepo() {
        pessoasFisicas = new ArrayList<>();
    }

    public void inserir(PessoaFisica pessoaFisica) {
        pessoasFisicas.add(pessoaFisica);
    }

    public void alterar(PessoaFisica pessoaFisica, String novoNome, String novoCpf, int novaIdade) {
        pessoaFisica.setNome(novoNome);
        pessoaFisica.setCpf(novoCpf);
        pessoaFisica.setIdade(novaIdade);
    }

    public void excluir(int pessoaId) {
        pessoasFisicas.remove(obter(pessoaId));
    }

    public PessoaFisica obter(int pessoaId) throws NoSuchElementException {
        for (PessoaFisica pessoaFisica : pessoasFisicas) {
            if (pessoaFisica.getId() == pessoaId) {
                return pessoaFisica;
            }
        }
        throw new NoSuchElementException("Pessoa Física com ID " + pessoaId + "não encontrada.");
    }

    public ArrayList<PessoaFisica> obterTodos() {
        return pessoasFisicas;
    }

    public void persistir(String nomeArquivo) throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(nomeArquivo));
        outputStream.writeObject(pessoasFisicas);
        outputStream.close();
        System.out.println("Dados da pessoa física armazenados.");
        System.out.println();
    }

    public void recuperar(String nomeArquivo) throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(nomeArquivo));
        pessoasFisicas = (ArrayList<PessoaFisica>) inputStream.readObject();
        inputStream.close();
        System.out.println("Dados da pessoa física recuperados.");
        System.out.println();
    }
}
```

- Classe Application (Aplicação)

```
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
```

- Resultado da execução do código.

```
"C:\Program Files\Java\jdk-20\bin\java.exe" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2023.1.2\lib\idea_rt.jar=50645:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2023.1.2\bin" -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath "C:\Users\roxni\OneDrive\Área de Trabalho\JavaProjects\EstacioFullStack_Mundo3_Nivel1_Procedimento1\out\production\JavaApplication1" EstacioFullStack_Mundo3_Nivel1_Procedimento1.Application
Dados da pessoa física armazenados.

Dados da pessoa física recuperados.

ID: 1
Nome: John
CPF: 11111111111
Idade: 30

ID: 2
Nome: Mary
CPF: 22222222222
Idade: 25

Dados da pessoa jurídica armazenados.

Dados da pessoa jurídica recuperados.

ID: 1
Nome: X Corp
CNPJ: 1231231230

ID: 2
Nome: Acme LTDA
CNPJ: 4564564560


Process finished with exit code 0
```

##

### Análise e Conclusão

#### Quais as vantagens e desvantagens do uso de herança?
A herança tem como principal objetivo reutilizar funcionalidades de outras elementos da aplicação.

package netcusoft.com.aula.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.UUID;


@Entity
@Table(name = "produtos")
public class ProductModel {

    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = false, length = 100)
    @Size(max = 100,message = "Nome do produto muito longo!")
    private String nome;


    private Double preco;
    @Column(unique = true)
    private UUID uuid=UUID.randomUUID();

    @Column(unique = false,updatable=true,nullable = false)
    @Size(max = 100,message = "Descricao do produto bastante longa")
    private String descricao;


    public Long getId(){
        return id;
    }

    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}

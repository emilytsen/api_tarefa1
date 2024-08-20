package application.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Entity
@Table(name = "Tarefa")
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String descricao;
    @Column(nullable = false)
    private Boolean concluido;

    //GET E SET DA COLUNA ID
    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return this.id;
    }

    //GET E SET DA COLUNA DESCRICAO
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getDescricao() {
        return this.descricao;
    }

    //GET E SET DA COLUNA CONCLUIDO
    public void setConcluido(Boolean concluido) {
        this.concluido = concluido;
    }
    public Boolean getConcluido() {
        return this.concluido;
    }
}
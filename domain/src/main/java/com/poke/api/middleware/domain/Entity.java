/**
 * Classe base abstrata que representa uma entidade geral com atributos e métodos comuns.
 * Fornece gerenciamento de datas de criação e atualização, além de status de ativação.
 */
package com.poke.api.middleware.domain;

/**
 * A classe {@code Entity} é uma classe base abstrata para entidades. Ela contém campos e métodos
 * para gerenciar a data de criação, data da última atualização, um identificador único e o status ativo.
 * A classe obriga a validação ao exigir que subclasses implementem o método {@link #validate()}.
 */
public abstract class Entity {
    /**
     * O identificador único da entidade.
     */
    private final int id;

    /**
     * Construtor que inicializa a entidade com um ID específico, status ativo, data de criação e data de atualização.
     *
     * @param id        o identificador único da entidade
     */
    protected Entity(
            int id
    ) {
        this.id = id;
    }

    /**
     * Método abstrato que obriga as subclasses a implementar a lógica de validação.
     * As subclasses devem sobrescrever este método para validar suas restrições específicas de domínio.
     *
     * @throws DomainValidationException se a validação falhar
     */
    protected abstract void validate() throws
            DomainValidationException;
    /**
     * Obtém o identificador único da entidade.
     *
     * @return o identificador único
     */
    public int getId() {
        return id;
    }
}

package com.openclassrooms.myrepo.model;

import java.util.Date;
import java.util.Objects;

/**
 * Représente une tâche. Une tâche contient un texte (description)
 * et une date limite (dueTime) qui indique jusqu'à quand elle doit être faite.
 */
public class Task {

    private String description;

    // Modif 1 : Ajout d'une date limite pour la tâche.
    // Cette date indique jusqu'à quand la tâche doit être réalisée.
    private Date dueTime;

    /**
     * Crée une nouvelle tâche avec une description et une date limite.
     *
     * @param description Le texte qui décrit la tâche.
     * @param dueTime La date limite à laquelle la tâche doit être terminée.
     */
    public Task(String description, Date dueTime) {
        this.description = description;
        this.dueTime = dueTime;
    }

    // Modif 2 : Getter pour récupérer la date limite.

    /**
     * Retourne la date limite de la tâche.
     *
     * @return La date limite.
     */
    public Date getDueTime() {
        return dueTime;
    }

    // Modif 3 : Setter pour modifier la date limite.

    /**
     * Change la date limite de la tâche.
     *
     * @param dueTime La nouvelle date limite.
     */
    public void setDueTime(Date dueTime) {
        this.dueTime = dueTime;
    }

    /**
     * Retourne la description de la tâche.
     *
     * @return Le texte de la tâche.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Change la description de la tâche.
     *
     * @param description Le nouveau texte de la tâche.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        return Objects.equals(description, task.description)
                && Objects.equals(dueTime, task.dueTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, dueTime);
    }
}

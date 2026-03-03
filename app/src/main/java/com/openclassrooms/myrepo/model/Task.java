package com.openclassrooms.myrepo.model;

import java.util.Date;
import java.util.Objects;

/**
 * Une classe représentant une tâche avec une description et une date limite.
 */
public class Task {
    private String description;
    // Modif 1 : ajout de l'attribut de classe pour la fonctionnalité date d'échéance
    private Date dueTime;

    /**
     * Constructeur pour créer une nouvelle tâche avec une description.
     * Par défaut, la date limite est à +10 jours à partir de la date de création.
     *
     * @param description La description de la tâche.
     */
    public Task(String description, Date dueTime) {
        this.description = description;
        this.dueTime = dueTime;

    }
    // Modif 2 : Ajout du Getter pour la date d'échéance

    /**
     * Obtient la date d'échéance de la tâche.
     *
     * @return La date d'échéance de la tâche.
     */

    public Date getDueTime() {
        return dueTime;
    }
    // Modif 3 : Ajout du Setter pour le temps limite

    /**
     * Modifie le temps limite de la tâche.
     *
     * @param dueTime La nouvelle date d'échéance de la tâche.
     */

    public void setDueTime(Date dueTime) {
        this.dueTime = dueTime;
    }

    /**
     * Obtient la description de la tâche.
     *
     * @return La description de la tâche.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Modifie la description de la tâche.
     *
     * @param description La nouvelle description de la tâche.
     */
    public void setDescription(String description) {
        this.description = description;
    }


    @Override

    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        return Objects.equals(description, task.description) && Objects.equals(dueTime, task.dueTime);

    }

    @Override

    public int hashCode() {

        return Objects.hash(description, dueTime);

    }

}
package com.openclassrooms.myrepo.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.openclassrooms.myrepo.R;
import com.openclassrooms.myrepo.model.Task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Adaptateur qui sert à afficher une liste de tâches dans un RecyclerView.
 * Il transforme chaque tâche en un élément visuel que l'utilisateur peut voir.
 */
public class TaskRecyclerViewAdapter extends ListAdapter<Task, TaskRecyclerViewAdapter.ViewHolder> {

    /**
     * Crée un nouvel adaptateur pour la liste de tâches.
     */
    public TaskRecyclerViewAdapter() {
        super(new ItemCallback());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    /**
     * Représente une ligne de la liste.
     * Chaque ViewHolder affiche une seule tâche.
     */
    static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView factTextView;
        private final TextView dueTimeTextView;
        private final LinearProgressIndicator progressIndicator;

        /**
         * Prépare les éléments visuels (textes, barre de progression) pour une tâche.
         *
         * @param itemView La vue qui représente une ligne de la liste.
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            factTextView = itemView.findViewById(R.id.task_description);
            dueTimeTextView = itemView.findViewById(R.id.task_duetime);
            progressIndicator = itemView.findViewById(R.id.progress_horizontal);
        }

        /**
         * Remplit l'affichage avec les informations d'une tâche.
         *
         * @param task La tâche à afficher dans cette ligne.
         */
        public void bind(Task task) {

            factTextView.setText(task.getDescription());

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            String formattedDueTime = dateFormat.format(task.getDueTime());

            dueTimeTextView.setText("Date limite : " + formattedDueTime);

            int progress = calculateProgress(task.getDueTime());
            progressIndicator.setProgress(progress);
        }

        /**
         * Calcule combien de temps il reste avant la date limite d'une tâche,
         * et transforme ce temps en pourcentage pour la barre de progression.
         *
         * @param dueTime La date limite de la tâche.
         * @return Un pourcentage entre 0 et 100 qui indique l'avancement.
         */
        private int calculateProgress(Date dueTime) {

            Calendar calendarToday = Calendar.getInstance();
            calendarToday.set(Calendar.HOUR_OF_DAY, 0);
            calendarToday.set(Calendar.MINUTE, 0);
            calendarToday.set(Calendar.SECOND, 0);
            calendarToday.set(Calendar.MILLISECOND, 0);

            Calendar calendarDueTime = Calendar.getInstance();
            calendarDueTime.setTime(dueTime);
            calendarDueTime.set(Calendar.HOUR_OF_DAY, 0);
            calendarDueTime.set(Calendar.MINUTE, 0);
            calendarDueTime.set(Calendar.SECOND, 0);
            calendarDueTime.set(Calendar.MILLISECOND, 0);

            int daysDifference = (int) ((calendarDueTime.getTimeInMillis()
                    - calendarToday.getTimeInMillis()) / (24 * 60 * 60 * 1000));

            return 100 - (daysDifference * 10);
        }
    }

    /**
     * Permet à l'adaptateur de savoir si deux tâches sont identiques
     * et si leur contenu a changé. Cela aide à mettre à jour la liste proprement.
     */
    private static class ItemCallback extends DiffUtil.ItemCallback<Task> {
        @Override
        public boolean areItemsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
            return oldItem.getDescription().equals(newItem.getDescription());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
            return oldItem.equals(newItem);
        }
    }
}

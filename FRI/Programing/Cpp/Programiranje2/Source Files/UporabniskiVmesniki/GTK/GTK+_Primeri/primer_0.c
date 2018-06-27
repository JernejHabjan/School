#include <gtk/gtk.h>

/* Podobi doda gumb z doloceno oznako */
GtkWidget *AddButton(GtkWidget *theWindow, const gchar *buttonText) {
    GtkWidget *button;
    button = gtk_button_new_with_label(buttonText);
    gtk_container_add(GTK_CONTAINER(theWindow), button);
    gtk_widget_show(button);
    return button;
}

/* Odzivne funkcije */
void ButtonClicked(GtkButton *button, gpointer data) {
    g_print("Hello world!\n");
}

void StopTheApp(GObject *object, gpointer data) {
    gtk_main_quit();
}

gint main(gint argc, gchar *argv[]) {
    GtkWidget *window;
    GtkWidget *button;

    gtk_init(&argc, &argv);
    /* Zgradi okno z ustreznimi dimenzijami */
    window = gtk_window_new(GTK_WINDOW_TOPLEVEL);
    gtk_window_set_default_size(GTK_WINDOW(window), 160, 100);

    /* Nastavi sirino roba na 5 pikslov */
    gtk_container_set_border_width(GTK_CONTAINER(window), 5);

    /* Uporabi funkcijo AddButton za gradnjo novega gumba */
    button = AddButton(window, "Pritisni za pozdrav!");

    /* Instaliraj signal za zaprtje aplikacije */
    g_signal_connect(G_OBJECT(window),
                     "destroy",
                     G_CALLBACK(StopTheApp),
                     NULL);

    /* Povezi signal gumba z odzivno funkcijo ButtonClicked */
    g_signal_connect(G_OBJECT(button),
                     "pressed",
                     G_CALLBACK(ButtonClicked),
                     NULL);
    /* Prikazi okno */
    gtk_widget_show(window);

    /*  GTK+ zanka dogodkov */
    gtk_main();
    return 0;
}


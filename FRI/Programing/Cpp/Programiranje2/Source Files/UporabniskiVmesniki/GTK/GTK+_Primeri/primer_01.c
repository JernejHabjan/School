#include <gtk/gtk.h>

/* Zaprtje aplikacija */
void CloseTheApp(GObject *object, gpointer data) {
    gtk_main_quit();
}

/* Odzivna funkcija za delo z dogodki */
gboolean EventHandler(GtkWidget *widget, GdkEvent *event, gpointer data) {
    /* Ugotovi vrsto dogodka */

    switch (event->type) {
        case GDK_EXPOSE:
            g_print("The window contents were redrawn\n");
            break;
        case GDK_BUTTON_PRESS:
            g_print("Button pressed\n");
            //return TRUE;
            break;
        default:
            g_print("\n");
            break;
    }
    return FALSE;
    //return TRUE;
}

/* Odzivna funkcija za signal */
void ButtonClicked(GtkButton *button, gpointer data) {
    g_print("Hello world!\n");
}

gint main(gint argc, gchar *argv[]) {
    GtkWidget *window;
    GtkWidget *button;

    gtk_init(&argc, &argv);

    window = gtk_window_new(GTK_WINDOW_TOPLEVEL);
    gtk_window_set_default_size(GTK_WINDOW(window), 160, 100);
    gtk_container_set_border_width(GTK_CONTAINER(window), 5);
    button = gtk_button_new_with_label("Pritisni za pozdrav!");
    gtk_container_add(GTK_CONTAINER(window), button);

    g_signal_connect(G_OBJECT(button), "event",
                     G_CALLBACK(EventHandler), NULL);
    g_signal_connect(G_OBJECT(button),
                     "pressed",
                     G_CALLBACK(ButtonClicked), NULL);
    g_signal_connect(G_OBJECT(window), "destroy",
                     G_CALLBACK(CloseTheApp), NULL);
    gtk_widget_show_all(window);

    gtk_main();
    return 0;
}


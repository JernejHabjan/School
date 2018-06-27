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
        case GDK_ENTER_NOTIFY:
            g_print("The mouse entered the window\n");
            break;
        case GDK_LEAVE_NOTIFY:
            g_print("The mouse left the window\n");
            break;
        case GDK_DELETE:
            g_print("The user killed the window\n");
            break;
        default:
            g_print("\n");
            break;
    }
    //return TRUE;
    return FALSE;
}

gint main(gint argc, gchar *argv[]) {
    GtkWidget *window;

    gtk_init(&argc, &argv);
    window = gtk_window_new(GTK_WINDOW_TOPLEVEL);
    g_signal_connect(G_OBJECT(window), "event",
                     G_CALLBACK(EventHandler), NULL);
    g_signal_connect(G_OBJECT(window), "destroy",
                     G_CALLBACK(CloseTheApp), NULL);
    gtk_widget_show(window);

    gtk_main();
    return 0;
}


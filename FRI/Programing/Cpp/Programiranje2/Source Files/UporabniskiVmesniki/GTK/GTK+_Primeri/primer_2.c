#include <gtk/gtk.h>

/* Zaprtje aplikacija */
void StopTheApp(GtkWidget *window, gpointer data) {
    gtk_main_quit();
}

GtkWidget *MakeWindow() {
    GtkWidget *window;
    window = gtk_window_new(GTK_WINDOW_TOPLEVEL);
    gtk_window_set_default_size(GTK_WINDOW(window), 320, 80);
    gtk_container_set_border_width(GTK_CONTAINER(window), 10);
    gtk_window_set_title(GTK_WINDOW(window), "Uporabniski vmesnik");
    g_signal_connect(G_OBJECT(window),
                     "destroy",
                     G_CALLBACK(StopTheApp),
                     NULL);
    return window;
}

GtkWidget *MakeEntryBox() {
    GtkWidget *box;
    GtkWidget *widget;
    box = gtk_box_new(GTK_ORIENTATION_HORIZONTAL, 2);
    widget = gtk_label_new("Vpisite vase  ime:");
    gtk_label_set_justify(GTK_LABEL(widget), GTK_JUSTIFY_LEFT);
    gtk_label_set_xalign(GTK_LABEL(widget), 0.0);
    gtk_box_pack_start(GTK_BOX(box), widget, FALSE, TRUE, 0);
    widget = gtk_entry_new();
    gtk_box_pack_start(GTK_BOX(box), widget, FALSE, TRUE, 0);
    gtk_box_set_homogeneous(GTK_BOX(box), TRUE);
    return box;
}

GtkWidget *MakeButtons() {
    GtkWidget *box;
    GtkWidget *button;
    box = gtk_box_new(GTK_ORIENTATION_HORIZONTAL, 2);
    button = gtk_button_new_with_label("OK");
    gtk_box_pack_start(GTK_BOX(box), button, FALSE, TRUE, 0);
    button = gtk_button_new_with_label("Cancel");
    gtk_box_pack_start(GTK_BOX(box), button, FALSE, TRUE, 0);
    gtk_box_set_homogeneous(GTK_BOX(box), TRUE);

    return box;
}

gint main(gint argc, gchar *argv[]) {
    GtkWidget *window;
    GtkWidget *mainbox;
    GtkWidget *entrybox;
    GtkWidget *buttonbox;

    gtk_init(&argc, &argv);

    window = MakeWindow();
    entrybox = MakeEntryBox();
    buttonbox = MakeButtons();

    mainbox = gtk_box_new(GTK_ORIENTATION_VERTICAL, 10);
    gtk_box_pack_start(GTK_BOX(mainbox), entrybox, FALSE, FALSE, 0);
    gtk_box_pack_start(GTK_BOX(mainbox), buttonbox, FALSE, FALSE, 0);
    gtk_box_set_homogeneous(GTK_BOX(mainbox), TRUE);

    gtk_container_add(GTK_CONTAINER(window), mainbox);
    gtk_widget_show_all(window);

    gtk_main();
    return 0;
}


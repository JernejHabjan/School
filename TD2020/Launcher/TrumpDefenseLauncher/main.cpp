#include "widget.h"
#include <QApplication>

int main(int argc, char *argv[]){

    //http://doc.qt.io/qt-4.8/qt-opengl-hellogl-example.html

    QApplication a(argc, argv);
    Widget w;
    w.show();

    return a.exec();
}

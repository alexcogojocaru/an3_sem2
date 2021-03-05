#include <QtWidgets/QApplication>
#include "lab2.h"

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    
    MyWidget3 m;
    m.show();

    return a.exec();
}

#include <QApplication>
#include "lab1.h"

int main(int argc, char *argv[])
{
	QApplication app(argc, argv);

	MyWidget1 m;
	m.show();

	return app.exec();
}

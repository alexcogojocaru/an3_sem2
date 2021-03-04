#pragma once
#include <QWidget>
#include <QPainter>
#include <cmath>
#include <iostream>

#define M_PI 3.141592

class MyWidget1 : public QWidget
{
	int xc, yc; // centrul ferestrei
	float x[4], y[4]; // coordonatele varfurilor unui patrat
	int N; //nr de patrate
		
public:
	MyWidget1(QWidget *parent = nullptr) : QWidget(parent)
	{
		xc = width() / 2;
		yc = height() / 2;
		N = 10;
		init_obiect(100, 100);
	}

	void desen(QPainter &painter, QPen &pen)
	{
		//deseneaza un patrat cu varfurile (x[0] y[0]), (x[1] y[1]), etc.
		painter.setPen(pen);
		for (int i = 0; i < 3; i++)
			painter.drawLine(x[i], y[i], x[i + 1], y[i + 1]);
		painter.drawLine(x[3], y[3], x[0], y[0]);
	}

	void init_obiect(int raza, int latura)
	{
		//functie ce calculeaza coordonatele varfurilor in pozitia initiala
		x[0] = xc + raza - latura / 2;
        y[0] = yc + latura / 2;

        x[1] = x[0] + latura;
        y[1] = y[0];

        x[2] = x[1];
        y[2] = y[1] - latura;

        x[3] = x[0];
        y[3] = y[2];
	}

	void paintEvent(QPaintEvent *e)
	{
		QPainter painter(this);
		QPen pen(Qt::blue);

		float du = 2 * M_PI / N; //unghiul dintre doua patrate succesive
		float c = cos(du);
		float s = sin(du);

        // desen(painter, pen);

		for (int i = 0; i < N; i++)
		{
			/*
			- se deseneaza patratul curent
			- se calculeaza noi valori pentru x[], y[]
			prin aplicarea unor transformari de translatie / rotatie, dupa caz
			*/

            desen(painter, pen);

            for (int j = 0; j < 4; j++)
            {
                rotate(x[j], y[j], xc, yc, c, s);
            }
		}
	}

private:
    void rotate(float& x, float& y, float xf, float yf, float cos, float sin)
    {
        float xr = x * cos - y * sin + xf - xf * cos + yf * sin;
        float yr = x * sin + y * cos + yf - xf * sin - yf * cos;

        x = xr;
        y = yr;
    }
};
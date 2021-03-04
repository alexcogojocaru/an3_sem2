#pragma once
#include <QWidget>
#include <QPainter>
#include <cmath>
#include <iostream>

// #define M_PI 3.141592

class MyWidget : public QWidget
{
	int xc, yc; // centrul ferestrei
	float x[4], y[4]; // coordonatele varfurilor unui patrat
	float xcp, ycp;
	int raza, latura;
	int N; //nr de patrate
		
public:
	MyWidget(QWidget *parent = nullptr) : QWidget(parent)
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
		this->raza = raza;
		this->latura = latura;

		xcp = xc + raza;
		ycp = yc;
	}

	void init_patrat(int latura)
	{
		x[0] = xcp - latura / 2;
		y[0] = ycp + latura / 2;

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

		float du = 2 * M_PI / N;
		float c = cos(du);
		float s = sin(du);
		int L2 = latura / 2;

		desen(painter, pen);
		init_patrat(latura);
		rotate(xcp, ycp, xc, yc, c, s);

		for (int i = 0; i < N; i++)
		{
			desen(painter, pen);
            init_patrat(latura);
			rotate(xcp, ycp, xc, yc, c, s);
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
#pragma once
#include <QWidget>
#include <QPainter>
#include <cmath>
#include <algorithm>

class MyWidget3 : public QWidget
{

	float XFm, XFM, YFm, YFM;
	int XPm, XPM, YPm, YPM;
	int tip_tran; //tip_tran == 0 -> scalare neuniforma, tip_tran != 0 -> scalare uniforma
	float sx, sy, tx, ty;

	typedef float(*MyFuncPtrType)(float);

public:
	MyWidget3(QWidget* parent = nullptr) : QWidget(parent)
	{
	}

	void init_grafic()
	{
		XFm = YFm = XFM = YFM = 0;
		XPm = YPm = XPM = YPM = 0;
		tip_tran = 0;
		sx = sy = tx = ty = 0;
	}

	void calctran()
	{
		if ((XFM > XFm) && (YFM > YFm))
		{
			//se determina sx, sy, tx, ty
			
			sx = (XPM - XPm) / (XFM - XFm);
			sy = (YPM - YPm) / (YFM - YFm);
		
			tx = XPm - XFm * sx;
			ty = YPm - YFm * sy;
		}
		else
		{
			sx = sy = tx = ty = 0;
		}
	}

	int XDisp(float xf)
	{
		//transformarea fereastra-poarta pt coordonata x
		
		return xf * sx + tx;
	}

	int YDisp(float yf)
	{
		//transformarea fereastra-poarta pt coordonata y
		
		return yf * sy + ty;
	}

	void cadru_poarta(QPainter& painter, QPen& pen)
	{
		painter.setPen(pen);
		painter.drawLine(XPm, YPm, XPm, YPM);
		painter.drawLine(XPm, YPM, XPM, YPM);
		painter.drawLine(XPM, YPM, XPM, YPm);
		painter.drawLine(XPM, YPm, XPm, YPm);
	}

	static float f1(float x)
	{
		return sin(10 * x);
	}

	static float f2(float x)
	{
		return tan(x);
	}

	static float f3(float x)
	{
		return x * sin(10 * x);
	}

	static float f4(float x)
	{
		return (x * x - 2) * (x + 3);
	}

	void grafic(QPainter& painter, QPen& pen, float xmin, float xmax, float pas, MyFuncPtrType f)
	{
		painter.setPen(pen);

		XFm = xmin;
		XFM = xmax;

		//se determina YFm si YFM ca fiind valoarea minima, respectiv maxima a functiei f in intervalul XFm, XFM
		std::vector<float> points;
		for (float x = XFm; x <= XFM; x++)
		{
			points.push_back(f(x));
		}

		std::vector<float>::iterator val_min = std::min_element(points.begin(), points.end());
		std::vector<float>::iterator val_max = std::max_element(points.begin(), points.end());

		YFm = points.at(std::distance(points.begin(), val_min));
		YFM = points.at(std::distance(points.begin(), val_max));

		cadru_poarta(painter, pen);
		calctran();

		//trasarea axei x
		if (YFm < 0 && YFM > 0)
		{
			painter.drawLine(XPm, rotireTranslatie(YDisp(0)), XPM, rotireTranslatie(YDisp(0)));
		}

		//trasarea axei y
		if (XFm < 0 && XFM > 0)
		{
			painter.drawLine(XDisp(0), YPm, XDisp(0), YPM);
		}

		//trasare grafic
		for (float x = xmin; x < xmax - pas; x += pas)
		{
			float y_disp = YDisp(f(x));
			float x_disp = XDisp(x);

			// pentru a desena doar in interiorul portii
			//if (((y_disp > YPm) && (y_disp < YPM)) && ((x_disp > XPm) && (x_disp < XPM)))
			{
				painter.drawLine(XDisp(x - pas), rotireTranslatie(YDisp(f(x - pas))), XDisp(x), rotireTranslatie(YDisp(f(x))));
			}
		}
	}

	void text(QPainter& painter, QString str)
	{
		painter.drawText(XPm, YPM + 12, str);
	}

	void text(QPainter& painter, QString str, int offX, int offY)
	{
		painter.drawText(XPm + offX, YPM + 12 + offY, str);
	}

	void paintEvent(QPaintEvent* e)
	{
		QPainter painter(this);
		QPen pen(Qt::blue);

		int xmaxe, ymaxe, stg = 50, drt = 50;
		float pas = 0.1;

		init_grafic();

		xmaxe = width();
		ymaxe = height();

		XPm = stg;
		XPM = xmaxe / 2 - drt / 2;
		YPm = stg;
		YPM = ymaxe / 2 - drt / 2;

		grafic(painter, pen, -5, 10, pas, f1);
		text(painter, "sin(10 * x)");

		//functia f2 ... 

		int lengthHorizontal = XPM - XPm + stg;
		int lengthVertical = YPM - YPm + stg;

		XPm += lengthHorizontal;
		XPM += lengthHorizontal;

		grafic(painter, pen, -5, 5, pas, f4);
		text(painter, "(x * x - 2) * (x + 3)");

		//functia f3 ... 

		YPm += lengthVertical;
		YPM += lengthVertical;

		/*grafic(painter, pen, -5, 5, pas, f2);
		text(painter, "tan(x)");*/

		//functia f4 ... 

		XPm -= lengthHorizontal;
		XPM -= lengthHorizontal;

		grafic(painter, pen, -5, 5, pas, f3);
		text(painter, "x * sin(10 * x)");
	}

private:
	float rotireTranslatie(float val)
	{
		return -val + YPm + YPM;
	}
};
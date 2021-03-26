#include <iostream>
#include <fstream>
#include <string>

#include <stdio.h>

#include <GL/glew.h>
#include <GL/freeglut.h>

#include <glm/mat4x4.hpp>
#include <glm/gtx/transform.hpp>
#include <glm/gtc/type_ptr.hpp>
#include "spheremesh.h"

constexpr float SPEED = 0.4f;
constexpr float JUMP_SPEED = 0.0005f;

float translateVerticalAxis = 0.75f;
float offset = -JUMP_SPEED;

struct buffers {
	GLuint vao;
	GLuint vbo;
	GLuint ebo;
};

//varfurile triunghiului
float points[] = {
	  0.5f,  0.5f, 0.0f,
	  0.5f, -0.5f, 0.0f,
	 -0.5f, -0.5f, 0.0f,
	 -0.5f,  0.5f, 0.0f
};

UINT indices[] = {
	0, 1, 3,
	1, 2, 3
};

GLuint shader_programme;

buffers sphereBuffers, planeBuffers;

SphereMesh sphere;

std::string textFileRead(char *fn) 
{
	std::ifstream ifile(fn);
	std::string filetext;
	while (ifile.good()) {
		std::string line;
		std::getline(ifile, line);
		filetext.append(line + "\n");
	}
	return filetext;
}

void bindSphere(buffers& buf)
{
	glGenBuffers(1, &buf.vbo);
	glGenVertexArrays(1, &buf.vao);
	glGenBuffers(1, &buf.ebo);

	glBindVertexArray(buf.vao);

	glBindBuffer(GL_ARRAY_BUFFER, buf.vbo);
	glBufferData(GL_ARRAY_BUFFER, sphere.vertices.size() * sizeof(glm::vec3), sphere.vertices.data(), GL_STATIC_DRAW);

	glVertexAttribPointer(0, 3, GL_FLOAT, GL_FALSE, 0, NULL);

	glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, buf.ebo);
	glBufferData(GL_ELEMENT_ARRAY_BUFFER, sphere.triangles.size() * sizeof(glm::ivec3), sphere.triangles.data(), GL_STATIC_DRAW);
	glEnableVertexAttribArray(0);
}

void bindPlane(buffers& buf)
{
	glGenBuffers(1, &buf.vao);
	glGenBuffers(1, &buf.vbo);
	glGenBuffers(1, &buf.ebo);

	glBindVertexArray(buf.vao);

	glBindBuffer(GL_ARRAY_BUFFER, buf.vbo);
	glBufferData(GL_ARRAY_BUFFER, sizeof(points), points, GL_STATIC_DRAW);

	glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, buf.ebo);
	glBufferData(GL_ELEMENT_ARRAY_BUFFER, sizeof(indices), indices, GL_STATIC_DRAW);

	glVertexAttribPointer(0, 3, GL_FLOAT, GL_FALSE, 3 * sizeof(float), (void*)0);
	glEnableVertexAttribArray(0);
}

void drawSphere(bool isJumping = true)
{
	glEnableVertexAttribArray(sphereBuffers.vao);

	glm::mat4 rotationMatrix = glm::mat4(1.0f);
	glm::mat4 translateMatrix = glm::mat4(1.0f);
	glm::mat4 scaleMatrix = glm::mat4(1.0f);
	glm::mat4 projectionMatrix = glm::mat4(1.0f);

	translateMatrix = glm::translate(translateMatrix, glm::vec3(0.0f, (isJumping) ? translateVerticalAxis : 0.0f, -1.0f));
	
	if (isJumping)
	{
		translateVerticalAxis += offset;
		if (translateVerticalAxis <= -0.0f)
		{
			offset = JUMP_SPEED;
		}
		else if (translateVerticalAxis >= 0.75f)
		{
			offset = -JUMP_SPEED;
		}
	}

	scaleMatrix = glm::scale(glm::vec3(0.2f, 0.2f, 0.2f));
	rotationMatrix = glm::rotate(glm::radians(static_cast<float>(glutGet(GLUT_ELAPSED_TIME) * SPEED)), glm::vec3(0.0f, 0.2f, 0.2f));

	GLuint matrixID = glGetUniformLocation(shader_programme, "modelMatrix");
	glUniformMatrix4fv(matrixID, 1, GL_FALSE, glm::value_ptr(translateMatrix * rotationMatrix * scaleMatrix));
	glUniformMatrix4fv(glGetUniformLocation(shader_programme, "projectionMatrix"), 1, GL_FALSE, glm::value_ptr(projectionMatrix));

	glBindVertexArray(sphereBuffers.vao);
	glDrawElements(GL_TRIANGLES, (GLsizei)sphere.triangles.size() * sizeof(glm::ivec3), GL_UNSIGNED_INT, 0);
}

void drawPlane()
{
	glEnableVertexAttribArray(planeBuffers.vao);

	glm::mat4 model = glm::mat4(1.0f);
	glm::mat4 projectionMatrix = glm::mat4(1.0f);
	glm::mat4 scaleMatrix = glm::mat4(1.0f);
	glm::mat4 translateMatrix = glm::mat4(1.0f);
	glm::mat4 rotationMatrix = glm::mat4(1.0f);

	//scaleMatrix = glm::scale(glm::vec3(0.2f, 0.2f, 0.2f));
	translateMatrix = glm::translate(translateMatrix, glm::vec3(0.0f, 0.0f, -1.0f));
	rotationMatrix = glm::rotate(glm::radians(66.0f), glm::vec3(1.0f, 0.0f, 0.0f));
	projectionMatrix = glm::perspective(glm::radians(-75.0f), 800.0f / 600.0f, 0.1f, 100.0f);

	glUniformMatrix4fv(glGetUniformLocation(shader_programme, "modelMatrix"), 1, GL_FALSE, glm::value_ptr(translateMatrix * rotationMatrix * scaleMatrix));
	glUniformMatrix4fv(glGetUniformLocation(shader_programme, "projectionMatrix"), 1, GL_FALSE, glm::value_ptr(projectionMatrix));

	glBindVertexArray(planeBuffers.vao);
	glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_INT, 0);
}

void display()
{
	glEnable(GL_DEPTH_TEST);
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	glUseProgram(shader_programme);

	bindSphere(sphereBuffers);
	drawSphere();
	bindPlane(planeBuffers);
	drawPlane();

	glutPostRedisplay();

	glFlush();
}

void init()
{
	// get version info
	const GLubyte* renderer = glGetString(GL_RENDERER); // get renderer string
	const GLubyte* version = glGetString(GL_VERSION); // version as a string
	printf("Renderer: %s\n", renderer);
	printf("OpenGL version supported %s\n", version);

	glClearColor(1, 1, 1, 0);

	glewInit();

	bindSphere(sphereBuffers);
	bindPlane(planeBuffers);

	std::string vstext = textFileRead("vertex.vert");
	std::string fstext = textFileRead("fragment.frag");
	const char* vertex_shader = vstext.c_str();
	const char* fragment_shader = fstext.c_str();

	GLuint vs = glCreateShader(GL_VERTEX_SHADER);
	glShaderSource(vs, 1, &vertex_shader, NULL);
	glCompileShader(vs);
	GLuint fs = glCreateShader(GL_FRAGMENT_SHADER);
	glShaderSource(fs, 1, &fragment_shader, NULL);
	glCompileShader(fs);

	shader_programme = glCreateProgram();
	glAttachShader(shader_programme, fs);
	glAttachShader(shader_programme, vs);
	glLinkProgram(shader_programme);
}

int main(int argc, char** argv)
{

	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_RGB | GLUT_SINGLE);
	glutInitWindowPosition(200, 200);
	glutInitWindowSize(512, 512);
	glutCreateWindow("SPG");

	init();

	glutDisplayFunc(display);
	glutMainLoop();

	return 0;
}

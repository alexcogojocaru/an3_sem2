// opengl.cpp : Defines the entry point for the application.
//

#include <iostream>

#include <glad/glad.h>
#include <glfw/glfw3.h>

#include <glm/glm.hpp>
#include <glm/gtc/matrix_transform.hpp>
#include <glm/gtc/type_ptr.hpp>


using namespace std;

int main()
{
	GLFWwindow* window = glfwCreateWindow(600, 600, "spg", nullptr, nullptr);

	return 0;
}

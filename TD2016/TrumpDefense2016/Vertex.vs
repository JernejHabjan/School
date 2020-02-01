
#version 330 core
layout (location = 0) in vec3 position;
layout(location = 1) in vec2 vertexUV;
layout (location = 2) in vec3 normal;

out vec3 Normal;
out vec3 FragPos;
out vec2 UV;


uniform mat4 P;
uniform mat4 V;
uniform mat4 M;

uniform mat4 PVM;
uniform mat4 M_Transp_Inverse;


void main()
{
    gl_Position = PVM * vec4(position, 1.0f);
	FragPos = vec3(M * vec4(position, 1.0));

    Normal = mat3(M_Transp_Inverse) * normal;  

	UV = vertexUV;
} 
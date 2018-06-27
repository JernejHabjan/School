
#version 330 core

struct Material {
    vec3 ambient;
    vec3 diffuse;
    vec3 specular;    
    float shininess;
}; 

struct PointLight {
    vec3 position;
    vec3 ambient;
    vec3 diffuse;
    vec3 specular;

	float constant;
    float linear;
    float quadratic;
};


struct SpotLight {
    vec3 direction;
    vec3 position;
    vec3 ambient;
    vec3 diffuse;
    vec3 specular;

	float constant;
    float linear;
    float quadratic;

	float cutOff;
	float outerCutOff;
};

struct DirLight {
    vec3 direction;
    vec3 ambient;
    vec3 diffuse;
    vec3 specular;
};

uniform sampler2D diffuseMap;
uniform sampler2D specularMap;


in vec3 FragPos;  
in vec3 Normal;  
in vec2 UV;

out vec4 color;
  
uniform vec3 viewPos;
uniform Material material;


uniform DirLight dirLight;
#define NR_POINT_LIGHTS 8 
uniform PointLight pointLights[NR_POINT_LIGHTS];
#define NR_SPOT_LIGHTS 2
uniform SpotLight spotLights[NR_SPOT_LIGHTS];




vec3 CalcPointLight(PointLight light, vec3 MaterialDiffuseColor, vec3 MaterialSpecularColor, Material material, vec3 FragPos, vec3 Normal){
	// Ambient
  	vec3 ambient = light.ambient * MaterialDiffuseColor;

	// Diffuse 
	vec3 norm = normalize(Normal);
	vec3 lightDir = normalize(light.position - FragPos);
	float diff = max(dot(norm, lightDir), 0.0);
	vec3 diffuse = light.diffuse * (diff * MaterialDiffuseColor);

	// Specular
	vec3 viewDir = normalize(viewPos - FragPos);
	vec3 reflectDir = reflect(-lightDir, norm);  
	float spec = pow(max(dot(viewDir, reflectDir), 0.0), material.shininess);
	vec3 specular = light.specular * (spec * MaterialSpecularColor);  
        
	//new stuff below
	float distance = length(light.position - FragPos);
	float attenuation = 1.0f / (light.constant + light.linear * distance + light.quadratic * (distance * distance)); 
	ambient *= attenuation;
	diffuse *= attenuation;
	specular *= attenuation;
	return (ambient + diffuse + specular);

}

vec3 CalcDirLight(DirLight light, vec3 normal, vec3 FragPos, vec3 MaterialDiffuseColor, vec3 MaterialSpecularColor)
{
    vec3 lightDir = normalize(-light.direction);

    // Diffuse shading
	vec3 norm = normalize(normal);
    float diff = max(dot(norm, lightDir), 0.0);


    // Specular shading
	vec3 viewDir = normalize(viewPos - FragPos);
    vec3 reflectDir = reflect(-lightDir, normal);
    float spec = pow(max(dot(viewDir, reflectDir), 0.0), material.shininess);
    // Combine results
    vec3 ambient = light.ambient * MaterialDiffuseColor;
    vec3 diffuse = light.diffuse * diff * MaterialDiffuseColor;
    vec3 specular = light.specular * spec * MaterialSpecularColor;
    return (ambient + diffuse + specular);
}



vec3 CalcSpotLight(SpotLight light, vec3 MaterialDiffuseColor, vec3 MaterialSpecularColor, Material material, vec3 FragPos, vec3 Normal){
    vec3 lightDir = normalize(light.position - FragPos);
    // Diffuse shading
    float diff = max(dot(Normal, lightDir), 0.0);
    // Specular shading
    vec3 reflectDir = reflect(-lightDir, Normal);
	vec3 viewDir = normalize(viewPos - FragPos);

    float spec = pow(max(dot(viewDir, reflectDir), 0.0), material.shininess);
    // Attenuation
    float distance = length(light.position - FragPos);
    float attenuation = 1.0f / (light.constant + light.linear * distance + light.quadratic * (distance * distance));    
    // Spotlight intensity
    float theta = dot(lightDir, normalize(-light.direction)); 
    float epsilon = light.cutOff - light.outerCutOff;
    float intensity = clamp((theta - light.outerCutOff) / epsilon, 0.0, 1.0);
    // Combine results
    vec3 ambient = light.ambient * MaterialDiffuseColor;
    vec3 diffuse = light.diffuse * diff * MaterialDiffuseColor;
    vec3 specular = light.specular * spec * MaterialSpecularColor;
    ambient *= attenuation * intensity;
    diffuse *= attenuation * intensity;
    specular *= attenuation * intensity;

    return (ambient + diffuse + specular);

}


void main(){
	

	vec4 MaterialDiffuseColor4f = texture(diffuseMap, UV );
	// vec3 MaterialDiffuseColor = vec3(MaterialDiffuseColor4f);
	vec3 MaterialDiffuseColor = vec3(UV.x, UV.y, 1.0);
	
	//if (MaterialDiffuseColor4f.a < 0.5)
    //   discard; 

	
	vec3 MaterialSpecularColor = vec3(texture(specularMap, UV ));

	vec3 result = vec3(0.0, 0.0, 0.0);

	//DIR LIGHT
	result += CalcDirLight(dirLight, Normal, FragPos, MaterialDiffuseColor, MaterialSpecularColor);

	//POINT LIGHTS
	for(int i = 0; i < NR_POINT_LIGHTS; i++){
		result += CalcPointLight(pointLights[i], MaterialDiffuseColor, MaterialSpecularColor, material, FragPos, Normal);    
	}


	//SPOT LIGHTS
	for(int i = 0; i < NR_SPOT_LIGHTS; i++){
		result += CalcSpotLight(spotLights[i], MaterialDiffuseColor, MaterialSpecularColor, material, FragPos, Normal);    
	}
	  
	color = vec4(result, 1.0f);

} 

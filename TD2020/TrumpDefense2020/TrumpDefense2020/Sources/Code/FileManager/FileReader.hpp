#pragma once
//include libs
#include <string>
#include <SDL\SDL.h>

//include files
#include "..\Renderer\GL_API.hpp"

class File;
class ResourceManager;


class FileReader {
private:
	File *file;
	ResourceManager *resourceManager;

	
public:
	FileReader(ResourceManager *resourcemanager);
	~FileReader();

	
	bool readINI(const std::string path);
	bool readOBJ(const std::string path);

	bool fileRead(std::string path, ResourceManager * resourceManager);











};
/*
#include <assimp/Importer.hpp>      // C++ importer interface
#include <assimp/scene.h>           // Output data structure
#include <assimp/postprocess.h>     // Post processing fla

class CAssimpModel
{
public:
	bool LoadModelFromFile(char* sFilePath);
	bool LoadModelFromFileBumpMap(char* sFilePath, char* sColorMap, char* sNormalMap);

	static void FinalizeVBO();
	static void BindModelsVAO();

	void RenderModel();
	void RenderModelBumpMap(CShaderProgram* spProgram);

	CAssimpModel();
private:
	bool bLoaded;
	static CVertexBufferObject vboModelData;
	static UINT uiVAO;
	static std::vector<CTexture> tTextures;
	std::vector<int> iMeshStartIndices;
	std::vector<int> iMeshSizes;
	std::vector<int> iMaterialIndices;
	int iNumMaterials;
};


bool CAssimpModel::LoadModelFromFile(char* sFilePath)
{
	if (vboModelData.GetBufferID() == 0) //check if vbo has been created
	{
		vboModelData.CreateVBO();
		tTextures.reserve(50);
	}
	Assimp::Importer importer;
	const aiScene* scene = importer.ReadFile(sFilePath, //path
		aiProcess_CalcTangentSpace | //we want to calculate tangent space - for bump maps
		aiProcess_Triangulate |  //triangles
		aiProcess_JoinIdenticalVertices | //different verticies with same coordinates are the same
		aiProcess_SortByPType); //how sort data internaly

	if (!scene)
	{
		MessageBox(appMain.hWnd, "Couldn't load model ", "Error Importing Asset", MB_ICONERROR);
		return false;
	}

	//CONVERT assimp data to data usable in vertex buffer object and for rendering:
	const int iVertexTotalSize = sizeof(aiVector3D) * 2 + sizeof(aiVector2D);

	int iTotalVertices = 0;

	FOR(i, scene->mNumMeshes)
	{
		aiMesh* mesh = scene->mMeshes[i];
		int iMeshFaces = mesh->mNumFaces;
		iMaterialIndices.push_back(mesh->mMaterialIndex);
		int iSizeBefore = vboModelData.GetCurrentSize();
		iMeshStartIndices.push_back(iSizeBefore / iVertexTotalSize);
		FOR(j, iMeshFaces)
		{
			const aiFace& face = mesh->mFaces[j];
			FOR(k, 3)
			{
				aiVector3D pos = mesh->mVertices[face.mIndices[k]];
				aiVector3D uv = mesh->mTextureCoords[0][face.mIndices[k]];
				aiVector3D normal = mesh->HasNormals() ? mesh->mNormals[face.mIndices[k]] : aiVector3D(1.0f, 1.0f, 1.0f);
				vboModelData.AddData(&pos, sizeof(aiVector3D));
				vboModelData.AddData(&uv, sizeof(aiVector2D));
				vboModelData.AddData(&normal, sizeof(aiVector3D));
			}
		}
		int iMeshVertices = mesh->mNumVertices;
		iTotalVertices += iMeshVertices;
		iMeshSizes.push_back((vboModelData.GetCurrentSize() - iSizeBefore) / iVertexTotalSize);
	}
	iNumMaterials = scene->mNumMaterials;


	//// LOADING MATERIALS:
	std::vector<int> materialRemap(iNumMaterials);

	FOR(i, iNumMaterials)
	{
		const aiMaterial* material = scene->mMaterials[i];
		int a = 5;
		int texIndex = 0;
		aiString path;  // filename

		if (material->GetTexture(aiTextureType_DIFFUSE, texIndex, &path) == AI_SUCCESS)
		{
			std::string sDir = GetDirectoryPath(sFilePath);
			std::string sTextureName = path.data;
			std::string sFullPath = sDir + sTextureName;
			int iTexFound = -1;
			FOR(j, ESZ(tTextures))if (sFullPath == tTextures[j].GetPath())
			{
				iTexFound = j;
				break;
			}
			if (iTexFound != -1)materialRemap[i] = iTexFound;
			else
			{
				CTexture tNew;
				tNew.LoadTexture2D(sFullPath, true);
				materialRemap[i] = ESZ(tTextures);
				tTextures.push_back(tNew);
			}
		}
	}

	FOR(i, ESZ(iMeshSizes))
	{
		int iOldIndex = iMaterialIndices[i];
		iMaterialIndices[i] = materialRemap[iOldIndex];
	}

	return bLoaded = true;
}




void CAssimpModel::FinalizeVBO() //uploads the collected data to GPU... when we load models and we arent going to load anymore
{
	glGenVertexArrays(1, &uiVAO);
	glBindVertexArray(uiVAO);
	vboModelData.BindVBO();
	vboModelData.UploadDataToGPU(GL_STATIC_DRAW);
	// Vertex positions
	glEnableVertexAttribArray(0);
	glVertexAttribPointer(0, 3, GL_FLOAT, GL_FALSE, 2 * sizeof(aiVector3D) + sizeof(aiVector2D), 0);
	// Texture coordinates
	glEnableVertexAttribArray(1);
	glVertexAttribPointer(1, 2, GL_FLOAT, GL_FALSE, 2 * sizeof(aiVector3D) + sizeof(aiVector2D), (void*)sizeof(aiVector3D));
	// Normal vectors
	glEnableVertexAttribArray(2);
	glVertexAttribPointer(2, 3, GL_FLOAT, GL_FALSE, 2 * sizeof(aiVector3D) + sizeof(aiVector2D), (void*)(sizeof(aiVector3D) + sizeof(aiVector2D)));
}

inline CAssimpModel::CAssimpModel()
{
}



//Rendering things now is just a matter of making few OpenGL calls.First, a check if model was properly loaded is done.Then, we go through every mesh of model, apply its associated texture, and then render mesh with appropriate starting index and number of vertices :


void CAssimpModel::RenderModel()
{
	if (!bLoaded)return;
	int iNumMeshes = ESZ(iMeshSizes);
	FOR(i, iNumMeshes)
	{
		int iMatIndex = iMaterialIndices[i];
		tTextures[iMatIndex].BindTexture();
		glDrawArrays(GL_TRIANGLES, iMeshStartIndices[i], iMeshSizes[i]);
	}
}*/
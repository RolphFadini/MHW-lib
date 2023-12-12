<div align="center">
  
  # Desenvolvimento de Serviços Web e Testes com Java
  ## *Assessment*
</div>
  
  #### Aluno: Rolph da Luz Fadini
  #### Curso: Análise e Desenvolvimento de Sistemas
  #### Professor(a): Leonardo Silva da Gloria
  
  ##
  
### O arquivo .jar do projeto pode ser baixado clicando em: [mhwlib.jar](https://github.com/RolphFadini/MHW-lib/blob/master/out/artifacts/mhwlib_jar/mhwlib.jar)
  > Todos os dados utilizados foram retirados da API externa: [Monster Hunter World API](https://docs.mhw-db.com).

##

# Get All Monsters
#### - Esse endpoint retorna todos os monstros
#### Possíveis parâmetros: 
> size (determina a quantidade de monstros retornados),<br>
> page (determina em que página da lista de todos os monstros estamos)

### HTTP Request:

## GET
```
http://localhost:8080/monsters
```
```
http://localhost:8080/monsters?size=20
```
```
http://localhost:8080/monsters?size=20&page=2
```

##

# Get By ID
- #### Esse endpoint retorna um monstro que contém um id especificado.

### HTTP Request:

## GET
```
http://localhost:8080/monsters/1
```

##

# Create New Monster
- #### Esse endpoint cria um novo monstro na biblioteca de monstros.

### HTTP Request:

## POST
```
http://localhost:8080/monsters
```

### Corpo do JSON:  (Exemplo)
```
{
  "name": "Rolph",
    "species": "Humano",
    "weaknesses": [
      {
        "element": "fire",
        "stars": 1,
        "condition": null
      },
      {
        "element": "thunder",
        "stars": 1,
        "condition": null
      }
    ]
  }
```

##

# Delete Monster
- #### Esse endpoint deleta um monstro pelo seu id.

### HTTP Request:

## DELETE
```
http://localhost:8080/monsters/30
```

##

# Update Monster By ID
- #### Esse endpoint atualiza um monstro com novos dados pelo seu id.

### HTTP Request:

## PUT
```
http://localhost:8080/monsters/50
```

### Corpo do JSON:  (Exemplo)
```
{
  "name": "Rolph",
    "species": "Humano",
    "weaknesses": [
      {
        "element": "fire",
        "stars": 1,
        "condition": null
      },
      {
        "element": "thunder",
        "stars": 1,
        "condition": null
      }
    ]
  }
```

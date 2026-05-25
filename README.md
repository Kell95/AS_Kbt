# AS_Kbt
Kelly Julieth Arango Henao

1036657098

# Laboratorio 3 – REST + HATEOAS + Docker + Kubernetes

Curso de Arquitectura de Software 

Aplicación RESTful construida con Spring Boot para gestión de vuelos, contenedorizada con Docker y desplegada en Kubernetes con ArgoCD.

## Stack

- Java 17 + Spring Boot 2.7.17
- MySQL 5.7
- Docker + Minikube + kubectl
- ArgoCD (GitOps)
- Swagger / HAL Explorer

## Endpoints

| Método | URL | Descripción |
|--------|-----|-------------|
| POST | /flight/save | Crear vuelo |
| GET | /flight/listAll | Listar todos |
| GET | /flight/list/{id} | Buscar por ID |
| GET | /flight/topFlights | Vuelos rating |
| PUT | /flight | Actualizar vuelo |
| DELETE | /flight/{id} | Eliminar vuelo |

## Correr localmente

```bash
# 1. Compilar
./mvnw clean package 

# 2. Construir imagen 
docker build -t kbt-app:latest .

# 3. Desplegar en Kubernetes
kubectl apply -f mysql-config.yaml
kubectl apply -f mysql-deployment.yaml
kubectl apply -f kbt-app.yaml

```

## Verificar estado

```bash
kubectl get pods
kubectl get deployments

```

## Swagger UI
http://localhost:8089/swagger-ui/index.html

## HAL Explorer
http://localhost:8089/explorer/index.html

## ArgoCD

```bash
kubectl port-forward svc/argocd-server -n argocd 9090:443
# Abrir https://localhost:9090
```



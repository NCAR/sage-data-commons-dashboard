# sage-data-commons-dashboard

A web application for viewing the status of HPC resources in a browser-based dashboard.

---

## 🚀 Deployment Overview

This application is deployed in a **Kubernetes environment** on the **CIRRUS cloud**.  
Deployment is managed using a **Helm chart**, which resides in a separate repository:

[NCAR/sage-data-commons-dashboard-k8s-dev](https://github.com/NCAR/sage-data-commons-dashboard-k8s-dev)

---

## Deployment Steps

When code changes are pushed to this repository, **GitHub Actions** automatically build and publish a Docker image to the repository’s container registry:

[GitHub Packages – sage-data-commons-dashboard](https://github.com/NCAR/sage-data-commons-dashboard/pkgs/container/sage-data-commons-dashboard)

### 1. Identify the New Artifact Tag
After a successful build, locate the latest image tag — for example:

0.0.1-SNAPSHOT-20251007-141304

### 2. Update the Helm Chart
Copy the desired image tag and update the `values.yaml` file in the Helm chart repository:

yaml
container:
  tag: 0.0.1-SNAPSHOT-20251007-141304

## Trigger ArgoCD Deployment

Once the `values.yaml` file is updated and committed:

- **ArgoCD** detects the change automatically.  
- It then deploys the new image to the **CIRRUS Kubernetes environment**.

> **Note:**  
> The Helm chart repository is **private**.  
> Authentication is handled via an **SSH Deploy Key pair** configured in the ArgoCD application and the repo.

---

## Accessing the Application

After deployment completes, the latest version of the dashboard is available at:

🔗 [https://dashboard.data-commons.k8s.ucar.edu/](https://dashboard.data-commons.k8s.ucar.edu/)

---

## Technologies

- **Java Spring Boot** – Web application framework
- **Github Actions** - CI/CD
- **Docker / GitHub Packages** – Containerization & artifact hosting  
- **Helm / Kubernetes** – Deployment configuration and orchestration  
- **ArgoCD** – Continuous delivery automation  
- **CIRRUS Cloud** – Kubernetes hosting environment

# Data Commons Dashboard Local Run Guide

## **Background**

The **Data Commons Dashboard** application is a Spring Boot web application developed using **Maven**, **Java**, and **Thymeleaf**. Its primary function is to visualize the output of HPC commands through web pages. The app shows:
- Job status and variable values.
- HPC server availability/status (up/down).

### **How It Works**
- Behind the scenes, **HPC qstat command** outputs data in JSON or text format. This data is stored as files within a file system.
- File names and locations are specified via an external configuration file.
- The content is refreshed frequently (in seconds) and is parsed by the app to display data in a browser.

---

## **Options for Local Deployment**

You can run the **Data Commons Dashboard** using the following methods:

1. **Within IntelliJ IDEA**:
    - Use a Run configuration (simplest and supports hot-swapping).
    - Use IntelliJ's Debugger.
2. **Using Docker**:
    - Run Docker from the command line or create an IntelliJ Docker run configuration.
3. **Using Docker Compose** (**Recommended**):
    - Use `docker-compose` for streamlined deployment.
    - Optionally, set up remote debugging using Docker Compose.

---

## **Setup**

### **Prerequisites**
Before setting up the project, ensure you have these tools installed:

- **Java Development Kit (JDK 17 or higher)**:
    - Verify installation: `java -version`.
    - Download if necessary: [Oracle JDK](https://www.oracle.com/java/) or [OpenJDK](https://openjdk.org/).

- **Maven** (for dependency management):
    - Typically included with IntelliJ for Maven projects.

- **Git** (for cloning the project):
    - Verify installation: `git --version`.
    - Download: [Git Website](https://git-scm.com/).

- **IntelliJ IDEA**:
    - Download: [JetBrains IntelliJ IDEA](https://www.jetbrains.com/idea/download/?section=mac).

- **Docker** (for containerized deployment):
    - Installation: [Docker for Mac Setup](https://docs.docker.com/desktop/setup/install/mac-install/).

### **Clone the Project**
Run the following command to clone the GitHub repository locally:

```bash
git clone https://github.com/NCAR/sage-data-commons-dashboard.git
```

### Ensure you have the following configuration files ready:
1. `application.properties`:
   Located at `/path/to/dashboard/conf/application.properties`.

2. `hpc.yml`:
   Located at `/path/to/dashboard/conf/hpc.yml`.

3. Test data files:
   Located at `/path/to/dashboard/data` (e.g., JSON files such as `casper_qstat_jobs.json`).

See src/main/resources/application.properties for the default settings.

---

### **Run the Application Locally**

#### Step 1: Open Project in IntelliJ IDEA
1. Launch IntelliJ IDEA.
2. Navigate to **File > Open...** and select the root folder of the cloned repository (`sage-data-commons-dashboard`).
3. IntelliJ will detect the Maven project and import dependencies from `pom.xml`.

#### Step 2: Configure IntelliJ SDK
1. Go to **File > Project Structure > Project**.
2. Set the Build and Run SDK to **JDK temurin 17** (or higher).

#### Step 3: Build the Project
1. Open **View > Tool Windows > Maven**.
2. Expand the **Lifecycle** section.
3. Run the `install` task to compile, test, and package the project.

#### Step 4: Run the Application
1. Locate the main entry point: `DashboardApplication.java` in the `src/main/java` directory.
2. Right-click on `DashboardApplication` and select **Run 'DashboardApplication.main()'**.
3. IntelliJ will display logs indicating the application has started successfully. Look for a message like:
   ```
   Started DashboardApplication in X.XXX seconds
   ```

4. Open a web browser and access the app at:
   ```
   http://localhost:8080
   ```

---

## **Using Docker**

Follow the steps below to deploy the application with Docker.

### **Prerequisites**
Install and verify Docker:
```bash
docker --version
```

#### External Configuration Files:
Ensure the following files are accessible:
- `application.properties` (path: `/path/to/dashboard/conf/application.properties`)
- `hpc.yml` (path: `/path/to/dashboard/conf/hpc.yml`)

### **Build and Run the Application with Docker**

1. Build the application JAR:
   ```bash
   mvn clean package
   ```

2. Build the Docker image:
   ```bash
   docker build -t sage-data-commons-dashboard:latest --build-arg APP_JAR=target/<<sage-data-commons-dashboard jar file>> .
   ```

3. Run the Docker container:
   ```bash
   docker run --detach --name sage-data-commons-dashboard -p 9090:8080 \
     -v /path/to/dashboard/conf:/opt/app/conf \
     -v /path/to/dashboard/data:/opt/app/data \
     sage-data-commons-dashboard:latest
   ```

4. Access the app at:
   ```
   http://localhost:9090
   ```

---

## **Using Docker Compose**

### **Preferred Method**

### **Prerequisites**

- Ensure **Docker** is installed:
    - [Download Docker](https://www.docker.com/products/docker-desktop/).

---

### **Setup**

1. **Create docker compose env file:**

   Sample env file:

   ```
       HOST_DASHBOARD_CONF_DIR=/path/to/dashboard/conf
       HOST_DASHBOARD_DATA_DIR=/path/to/dashboard/data
   ```

---

### **Configure Docker in IntelliJ IDEA**

To integrate Docker (and Docker Compose) with IntelliJ:

1. Open IntelliJ Settings:
    - Navigate to **Build, Execution, Deployment > Docker**.
2. Add a new Docker configuration:
    - Choose **Docker for [environment]** (e.g., Docker Desktop).
    - Specify the Docker connection type:
        - For local install: `Unix Socket` (e.g., `unix:///var/run/docker.sock`).
        - For remote: Enter the Docker daemon host URL.
3. After configuration, IntelliJ can manage Docker and Docker Compose projects.

---

### **Create a Docker-Compose Run Configuration in IntelliJ**

1. Go to **Run > Edit Configurations...**.
2. Click the `+` button in the top-left corner and select **Docker-Compose**.
3. See the name, for example, `Docker Compose: Sage Dashboard`.
3. Under **Run**, set the following options (see Modify options if not visible):
    - **Compose files:** Specify `./docker-compose.yml`.
    - **Environment variables file:** Specify path to your env file.
4. Under **docker compose up**, add the following options (see Modify options):
    - **Remove Orphans**
    - **Attach to: None**
    - **Build: Always**
    - **Recreate containers: All**
5. Under **Before launch**, add the following Run Maven Goal:
    - **package -DskipTests -P development-docker**
6. Save the configuration.

---

### **Run Docker Compose from IntelliJ**

1. From the **Run Configurations** dropdown in the top-right corner of IntelliJ, select your new Docker Compose configuration (e.g., `Docker Compose: Sage Dashboard`).
2. Click the **Run** button (green arrow).
3. IntelliJ will run the `docker-compose up` command, displaying output similar to:
   ```plaintext
   ✔ Container sage-data-commons-dashboard  Started
   ```

4. Validate the application:
    - Open your browser and visit:
      ```
      http://localhost:9090
      ```

---

### **Optional: Debugging with Docker Compose**

#### **Set Up Remote Debugging in IntelliJ**

To enable debugging for your Docker Compose setup:

1. Open IntelliJ IDEA and create a **Remote JVM Debug** configuration:
    - Navigate to **Run > Edit Configurations...**.
    - Click the `+` button and select **Remote JVM Debug**.
    - Set the following parameters:
        - **Debugger mode:** `Attach to remote JVM`.
        - **Host:** `localhost`.
        - **Port:** `10005` (as specified in `docker-compose.yml`).
    - Save the configuration (e.g., name it `Docker Debug`).

2. Add breakpoints in your code where needed.

3. Select the **Docker Debug** configuration and click the **Debug** button.

4. IntelliJ will attach to the running JVM in the Docker container, allowing you to debug your application live.

---

### **Stopping the Application**

To bring down the Docker Compose services:

1. In IntelliJ, click the red **Down** button in the Console view.
2. On the command line, run the following in the repository toplevel directory:
   ```bash
   docker compose down
   ```

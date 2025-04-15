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

#### Ensure you have the following configuration files ready:
1. `queueapp.properties`:
   Located at `/path/to/dashboard/conf/queueapp.properties`.

2. `hpc.yml`:
   Located at `/path/to/dashboard/conf/hpc.yml`.

3. Test data files:
   Located at `/path/to/dashboard/data` (e.g., JSON files such as `casper_qstat_jobs.json`).

Sample contents for `queueapp.properties`:

```properties
# Path to json data files (local or container-based paths)
dashboard.queue.file.path=/path/to/dashboard/data

# Scheduler tasks (in milliseconds, e.g., 60000 = 60 seconds)
scheduler.fixedDelay=60000

# HTML status page refresh interval (in seconds)
hpc.page.refresh.interval=60

casper.qstat.jobs.json=casper_qstat_jobs.json
casper.qstat.jobs.txt=casper_qstat_jobs.txt
derecho.qstat.jobs.json=derecho_qstat_jobs.json
derecho.qstat.queue.txt=derecho_qstat_queue.txt

allowed.origins="http://localhost:8080,https://localhost:8080"
```

---

### **Clone the Project**

Run the following command to clone the GitHub repository locally:

```bash
git clone https://github.com/NCAR/sage-data-commons-dashboard.git
```

---

### **Run the Application Locally**

#### Step 1: Open Project in IntelliJ IDEA
1. Launch IntelliJ IDEA.
2. Navigate to **File > Open...** and select the root folder of the cloned repository (`sage-data-commons-dashboard`).
3. IntelliJ will detect the Maven project and import dependencies from `pom.xml`.

#### Step 2: Configure IntelliJ SDK
1. Go to **File > Project Structure > Project**.
2. Set the SDK to **JDK 21** (or higher).
3. Set the language level to match **JDK 21** or the project requirement.

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
- `queueapp.properties` (path: `/path/to/dashboard/conf/queueapp.properties`)
- `hpc.yml` (path: `/path/to/dashboard/conf/hpc.yml`)

### **Create a Dockerfile**

Add the following file to the root directory of the project:

```dockerfile
# Use the official OpenJDK image from Docker Hub
FROM eclipse-temurin:21

# Add application JAR to the container
ARG JAR_FILE=target/sage-data-commons-dashboard-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /app.jar

# Set Spring configuration location
ENV SPRING_CONFIG_LOCATION=file:/usr/local/dashboard/conf/queueapp.properties

# Run the application
ENTRYPOINT ["java", "-Dspring.config.location=${SPRING_CONFIG_LOCATION}", "-jar", "/app.jar"]
```

### **Build and Run the Application with Docker**

1. Build the application JAR:
   ```bash
   mvn clean package
   ```

2. Build the Docker image:
   ```bash
   docker build -t sage-data-commons-dashboard .
   ```

3. Run the Docker container:
   ```bash
   docker run --detach --name sage-data-commons-dashboard -p 9090:8080 \
     -v /path/to/queueapp.properties:/usr/local/dashboard/conf/queueapp.properties \
     sage-data-commons-dashboard
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

1. **Create `docker-compose.yml`:**
   Place the `docker-compose.yml` file in the same directory as the `Dockerfile` (e.g., `/path/to/IdeaProjects/sage-data-commons-dashboard`).

   Example `docker-compose.yml`:

   ```yaml
   version: "3.9"  # Use the latest Docker Compose version

   services:
     sage-data-commons-dashboard:
       image: sage-data-commons-dashboard:latest  # Replace with your image name
       container_name: sage-data-commons-dashboard
       ports:
         - "9090:8080"  # Map port 9090 on the host to 8080 in the container
       volumes:
         - ${QUEUEAPP_PROPERTIES_FILE}:/usr/local/dashboard/queueapp.properties  # Dynamic volume for the properties file
       environment:
         SPRING_CONFIG_LOCATION: "file:/usr/local/dashboard/queueapp.properties"  # Environment variable for Spring
       restart: unless-stopped
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
3. Set up the following:
    - **Name:** For example, `Docker Compose: Sage Dashboard`.
    - **File(s):** Specify your `docker-compose.yml` path.
    - **Services:** Select `sage-data-commons-dashboard`.
4. Add the `CONFIG_PATH` environment variable:
    - Go to the **Environment Variables** field:
        - Add a key-value pair:
          ```plaintext
          Key: CONFIG_PATH
          Value: /Users/your-user/dashboard/conf
          ```
5. Save the configuration.

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

To enable debugging for your Docker Compose setup:

#### **Step 1: Update the `Dockerfile`**

Modify the `Dockerfile` to expose a debug port and add JVM debugging options.

```dockerfile
# Expose the debug port inside the container
EXPOSE 5005

# Enable remote debugging for the JVM
ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-Dspring.config.location=${SPRING_CONFIG_LOCATION}", "-jar", "/app.jar"]
```

---

#### **Step 2: Update `docker-compose.yml`**

Add the debug port (`5005:5005`) mapping to your `docker-compose.yml`, along with volume mounts.

```yaml
version: "3.9"

services:
  sage-data-commons-dashboard:
    image: sage-data-commons-dashboard:latest
    container_name: sage-data-commons-dashboard
    ports:
      - "9090:8080"  # Map port 9090 on host to 8080 in the container
      - "5005:5005"  # Debugging
    volumes:
      # Mount the configuration file
      - ${CONFIG_PATH}:/usr/local/dashboard/conf
      # Mount a directory for JSON test files (optional)
      - ${CONFIG_PATH}:/usr/local/dashboard/data
    environment:
      SPRING_CONFIG_LOCATION: "file:/usr/local/dashboard/conf/queueapp.properties"
    restart: unless-stopped
```

---

#### **Step 3: Set Up Remote Debugging in IntelliJ**

1. Open IntelliJ IDEA and create a **Remote JVM Debug** configuration:
    - Navigate to **Run > Edit Configurations...**.
    - Click the `+` button and select **Remote JVM Debug**.
    - Set the following parameters:
        - **Host:** `localhost`.
        - **Port:** `5005` (as specified in `docker-compose.yml`).
    - Save the configuration (e.g., name it `Docker Debug`).

2. Add breakpoints in your code where needed.

3. Select the **Docker Debug** configuration and click the **Debug** button.

4. IntelliJ will attach to the running JVM in the Docker container, allowing you to debug your application live.

---

### **Stopping the Application**

To stop the Docker Compose services:

1. In IntelliJ, click the red **Stop** button in the Console view.
2. Alternatively, stop the services manually by running:
   ```bash
   docker compose -f /path/to/docker-compose.yml -p sage-data-commons-dashboard stop
   ```
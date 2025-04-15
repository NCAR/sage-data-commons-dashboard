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
2. Set the SDK to **JDK 17** (or an appropriate supported version).
3. Set the language level to match **JDK 17** or the project requirement.

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

### **Setup**

1. Create a `docker-compose.yml` file in the project root:

```yaml
version: "3.9"

services:
  sage-data-commons-dashboard:
    image: sage-data-commons-dashboard:latest
    container_name: sage-data-commons-dashboard
    ports:
      - "9090:8080"
    volumes:
      - ${CONFIG_PATH}:/usr/local/dashboard/conf
    environment:
      SPRING_CONFIG_LOCATION: "file:/usr/local/dashboard/conf/queueapp.properties"
    restart: unless-stopped
```

2. Run Docker Compose:
   ```bash
   docker-compose up
   ```

3. Access the app at:
   ```
   http://localhost:9090
   ```

---

## **Optional Debugging with Docker Compose**

- Update the Dockerfile to expose a debug port (5005).
- Update `docker-compose.yml` to map port `5005:5005`.
- Setup IntelliJ remote debug with the debug port (`localhost:5005`).

---

## **Troubleshooting**

- If Maven fails:
  ```bash
  mvn clean install
  ```
- Verify external configuration file paths.
- Ensure Docker is installed and running: `docker --version`.

---

## **License**

Distributed under MIT License. See `LICENSE` for more details.
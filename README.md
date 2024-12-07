# **Sensor Data Monitoring System**

This project demonstrates a distributed system for real-time sensor data monitoring using Spring Boot, MongoDB, WebSocket, and Docker. It includes a WebSocket server to handle sensor data and multiple sensor node clients that simulate real-world sensor nodes.

## 📑 Documentation and Links

`docker pull mihir2109/sensor_data-sensor-client`

`docker pull mihir2109/sensor_data-sensor-server`


## 🎯 **Features**

- **Sensor Node Simulation:** Simulates multiple sensor nodes sending real-time data such as temperature, humidity, and pressure.

- **WebSocket Communication:** Bi-directional communication between the server and sensor nodes.
- **MongoDB Integration:** Stores sensor data in MongoDB for persistence and analysis.
- **Dockerized Architecture:** Simplifies deployment using Docker and Docker Compose.
- **Real-Time Updates:** Pushes updates to clients subscribed via WebSocket.

## 🏗️ **Technologies Used**

- **Backend:** Spring Boot, WebSocket, Hibernate

- **Database:** MongoDB
- **Messaging:** STOMP over WebSocket
- **Client:** Java console application
- **Containerization:** Docker, Docker Compose

## 🏗️ **Project Structure**

```
sensor-data-monitoring/
│
├── sensor-server/
│   ├── src/
│   ├── Dockerfile
│   ├── application.properties
│   └── pom.xml
│
├── sensor-client/
│   ├── src/
│   ├── Dockerfile
│   └── pom.xml
│
├── docker-compose.yml
└── README.md

```

## 🚀 **Getting Started**

### **Pre-requisites**

- Java 17+ (for compiling and running Spring Boot applications)
- Docker and Docker Compose
- Maven (for building the project)

### **Step 1: Clone the Repository**

```bash
git clone https://github.com/mihirh19/sensor_data
cd sensor_data
```

### **Step 2: Configure Environment Variables**

Create a .env file in the root directory with the following:

```.env
MONGO_URI=mongodb://mongo:27017/sensorDB
```

### **Step 3: Build the Docker Images**

```bash
docker-compose build
```

### **Step 4: Start the Application**

```bash
docker-compose up
```

This starts the following services:

- sensor-server on port 8081
- sensor-client (three instances simulating sensor nodes)
- MongoDB on port 27017

### **Step 5: Verify the Setup**

- WebSocket Server: Connect to ws://localhost:8080/sensor-updates using a WebSocket client like Postman or wscat.

- MongoDB Database: Access sensorDB using a MongoDB client like MongoDB Compass

## Author

👤 **Mihir Hadavani**

- Twitter: [@mihirh21](https://twitter.com/mihirh21)
- Github: [@mihirh19](https://github.com/mihirh19)
- LinkedIn: [@mihir-hadavani-996263232](https://linkedin.com/in/mihir-hadavani-996263232)

## Show your support

<a href="https://www.buymeacoffee.com/mihir21"><img src="https://img.buymeacoffee.com/button-api/?text=Buy me a coffee&emoji=☕&slug=mihir21&button_colour=FFDD00&font_colour=000000&font_family=Cookie&outline_colour=000000&coffee_colour=ffffff" /></a>

Give a ⭐️ if this project helped you!

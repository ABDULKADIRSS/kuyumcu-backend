pipeline {
    agent any

    stages {

        stage('Build') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Start Application') {
            steps {
                bat '''
                echo Starting Spring Boot...
                start "" java -jar target\\kuyumcu-backend-0.0.1-SNAPSHOT.jar
                ping 127.0.0.1 -n 16 > nul
                '''
            }
        }

        stage('Selenium Tests') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Deploy with Docker') {
            steps {
                bat '''
                echo Deploying application with Docker...

                "C:\\Program Files\\Docker\\Docker\\resources\\bin\\docker.exe" compose down
                "C:\\Program Files\\Docker\\Docker\\resources\\bin\\docker.exe" compose up -d --build
                '''
            }
        }
    }

    post {
        success {
            echo 'CI/CD BAŞARIYLA TAMAMLANDI'
        }
        failure {
            echo 'PIPELINE HATASI VAR'
        }
    }
}

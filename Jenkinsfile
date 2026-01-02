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
    }

    post {
        success {
            echo 'TÜM TESTLER BAŞARILI'
        }
        failure {
            echo 'SELENIUM HATASI VAR'
        }
    }
}

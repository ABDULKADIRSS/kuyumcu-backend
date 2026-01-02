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
                start cmd /c "java -jar target\\kuyumcu-backend-0.0.1-SNAPSHOT.jar"
                timeout /t 15
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

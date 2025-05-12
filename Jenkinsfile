pipeline {
    agent any

    tools {
        maven 'Maven_3' // Имя, как указано в Jenkins (Manage Jenkins → Global Tool Configuration)
        jdk 'JDK_16'    // Аналогично, имя JDK в Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/ermakov121/fck.git' // Замени на свой URL
            }
        }

        stage('Build and Test') {
            steps {
                sh 'mvn clean test'
            }
        }
    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml' // Покажет отчёты о тестах
        }
    }
}

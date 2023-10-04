pipeline {
    agent any

    environment {
        // Remplacez ces variables par vos informations
        DOCKERHUB_USERNAME = 'zalehamza'
        DOCKERHUB_PASSWORD = 'leil@2023'
        IMAGE_NAME = 'fraud'
        IMAGE_TAG = "3.0.0"
        GITHUB_TOKEN = credentials('zalehamza')
    }

    stages {
        stage('Build Docker Image') {
            steps {
                script {
                    // Construisez l'image Docker
                    // sh "docker build -t $DOCKERHUB_USERNAME/$IMAGE_NAME:$IMAGE_TAG ."
                    sh 'chmod +x ./gradlew'
                    sh "./gradlew bootBuildImage --imageName=$DOCKERHUB_USERNAME/$IMAGE_NAME:$IMAGE_TAG"
                }
            }
        }

         stage('Push to Docker Hub') {
             steps {
                 script {
                     // Authentifiez-vous sur Docker Hub
                     sh "echo $DOCKERHUB_PASSWORD | docker login -u $DOCKERHUB_USERNAME --password-stdin"

                    


                     sh "docker tag $DOCKERHUB_USERNAME/$IMAGE_NAME:$IMAGE_TAG $DOCKERHUB_USERNAME/$IMAGE_NAME:$IMAGE_TAG"


                     // Poussez l'image sur Docker Hub
                     sh "docker push $DOCKERHUB_USERNAME/$IMAGE_NAME:$IMAGE_TAG"
                 }
             }
         }

        stage('Deploy to Kubernetes') {
            steps {
                script {

            sh "rm -rf demo-devops"
            sh 'git clone https://github.com/zalehamza/demo-devops.git'

            dir('demo-devops') {

                sh '''
                        git config --global user.email "zalehamza18@gmail.com"
                        git config --global user.name "zalehamza"

                     '''

                sh """
                    sed -i 's/${IMAGE_NAME}.*/${IMAGE_NAME}:${IMAGE_TAG}/g' deployment.yaml

                """
                sh """
                        git add deployment.yaml
                        git commit -m "Update deployment.yaml"
                        git push https://${GITHUB_TOKEN}@github.com/zalehamza/demo-devops.git
                    """


            }
                }
            }
        }

        stage("Cleanup Workspace") {
            steps {
                cleanWs()
                sh "ls -la"
            }
        }
    }

    // post {
    //     always {
    //         // Déconnexion de Docker Hub après la fin du pipeline
    //         sh 'docker logout'
    //     }
    // }
}
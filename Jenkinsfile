/* groovylint-disable DuplicateMapLiteral, DuplicateStringLiteral, LineLength, NestedBlockDepth */
@Library( 'emst-jenkins-lib' ) _

pipeline {
  agent {
    kubernetes {
      yaml """
apiVersion: v1
kind: Pod
metadata:
  labels:
    some-label: cicd-container
spec:
  containers:
    - name: jnlp
      image: "${DOCKER_REGISTRY}/emst/inbound-agent:4.6.1"
      args: ['\$(JENKINS_SECRET)', '\$(JENKINS_NAME)']
    - name: kaniko
      image: "${DOCKER_REGISTRY}/emst/kaniko-executor:latest"
      volumeMounts:
        - name: pull-secret
          mountPath: "/kaniko/.docker"
          readOnly: true
      command:
        - cat
      tty: true
    - name: helm
      image: "${DOCKER_REGISTRY}/emst/helm:latest"
      command:
        - cat
      tty: true
    - name: openjdk
      image: "${DOCKER_REGISTRY}/emst/openjdk:latest"
      command:
        - cat
      tty: true
    - name: ansible
      image: "${DOCKER_REGISTRY}/emst/ansible:latest"
      command:
        - cat
      tty: true
  volumes:
    - name: pull-secret
      secret:
        secretName:  emst-kubernetes-pull-secret
        items:
          - key: .dockerconfigjson
            path: config.json
"""
    }
  }
  options {
      buildDiscarder(logRotator(numToKeepStr: '5'))
  }
  
  environment { 
      FEATURE_BUGFIX_INSTANCES = 'ocdev'
      PR_INSTANCES = 'ocdev|daidev'
      MASTER_INSTANCES = 'ocdev|daidev'
      MASTER_RELEASE = 'daidev'
      DEPLOYMENT_REPO = 'eMST/emst-k8s-deployment'
  }

  stages {

    stage( 'Checkout' ){
        steps {
            checkout([
                    $class                           : 'GitSCM',
                    branches                         : scm.branches,
                    doGenerateSubmoduleConfigurations: scm.doGenerateSubmoduleConfigurations,
                    extensions                       : [
                            [$class: 'WipeWorkspace'],
                            [$class: 'CloneOption', noTags: false, shallow: false, depth: 0, reference: ''],
                            [$class: 'LocalBranch', localBranch: "**"]
                    ],
                    userRemoteConfigs                : scm.userRemoteConfigs,
            ])

            script{
              env.JOB_ORGA = steps.sh returnStdout: true, script: '''
                echo "${JOB_NAME}" | awk -F '/' '{ printf "%s", $1 }'
              '''
              env.JOB_REPO = steps.sh returnStdout: true, script: '''
                echo "${JOB_NAME}" | awk -F '/' '{ printf "%s", $2 }'
              '''
              if( env.JENKINS_INSTANCE.equals( 'daidev' ) ){
                env.CHART_PATH = '/chartrepo/emst'
              }
              else{
                env.CHART_PATH = ''
              }
            }

            echo "JOB_ORGA: ${env.JOB_ORGA}"
            echo "JOB_REPO: ${env.JOB_REPO}"

        }
    }

    stage ( 'Prepare Build' ){

      parallel{

        stage( 'This is a Feature / Bugfix Build' ){
          when { branch pattern: "bugfix/.*|feature/.*", comparator: "REGEXP"}
          steps{
            container( 'ansible' ){
                withCredentials([usernamePassword(credentialsId: 'emst-github-app',
                          usernameVariable: 'GITHUB_APP',
                          passwordVariable: 'GITHUB_ACCESS_TOKEN')]) {
                script{

                  //Breaking the build if we are not responsible for Features in Bugfixes
                  if( env.FEATURE_BUGFIX_INSTANCES.indexOf( env.JENKINS_INSTANCE ) < 0 ){
                    currentBuild.result = 'ABORTED'
                    error( "Aborting Build" )
                  }

                  env.KANIKO_ARGS = ' --no-push'
                  env.DEPLOYMENT_ENVIRONMENT = ''
                  env.RELEASE_VERSION = "${utils.getNewReleaseVersion( env.JOB_ORGA + '/' + env.JOB_REPO, env.BRANCH_NAME)}-${env.BUILD_ID}"
                  env.DEPLOYMENT_SUFFIX = ''
                }
              }
            }
          }
        }

        stage( 'This is a Pull Request' ){
          when { changeRequest target: 'master' }
          steps{
            container( 'ansible' ){
              script{

                  //Breaking the build if we are not responsible for the PRs
                  if( env.PR_INSTANCES.indexOf( env.JENKINS_INSTANCE ) < 0 ){
                    currentBuild.result = 'ABORTED'
                    error( "Aborting Build" )
                  }

                  withCredentials([usernamePassword(credentialsId: 'emst-github-app', usernameVariable: 'GITHUB_APP', passwordVariable: 'GITHUB_ACCESS_TOKEN')]) {
                    
                    env.PR_SOURCE_BRANCH = utils.getPullRequestSourceByBranchName( "${env.JOB_ORGA}/${env.JOB_REPO}" )
                    env.RELEASE_VERSION = utils.getNewReleaseVersion( "${env.JOB_ORGA}/${env.JOB_REPO}", env.PR_SOURCE_BRANCH ) + "-${env.BRANCH_NAME.toLowerCase()}-${env.BUILD_ID}"
                    env.DEPLOYMENT_ENVIRONMENT = env.JENKINS_INSTANCE
                    env.KANIKO_ARGS = ''
                    env.DEPLOYMENT_SUFFIX = "-${env.BRANCH_NAME}"
                   
                }
              }
            }
          }
        }

        stage( 'This is a Master Build' ){
          when { branch 'master' }
          steps{
            container( 'ansible' ){
              script{

                // If this Jenkins is responsible for PRs ... we undeploy the PR from K8s
                if( env.PR_INSTANCES.indexOf( env.JENKINS_INSTANCE ) >= 0 ){
                  
                    build job: 'eMST/emst-k8s-deployment/master', parameters: [
                      [$class: 'StringParameterValue', name: 'DEPLOYMENT_UNIT', value: env.JOB_REPO],
                      [$class: 'StringParameterValue', name: 'DEPLOYMENT_VERSION', value: ""],
                      [$class: 'StringParameterValue', name: 'DEPLOYMENT_SUFFIX', value: "-pr-${utils.getPullRequestNumberFromMergeCommit()}"],
                      [$class: 'StringParameterValue', name: 'DEPLOYMENT_ENVIRONMENT', value: "${env.JENKINS_INSTANCE}"],
                      [$class: 'StringParameterValue', name: 'DEPLOYMENT_STATE', value: 'absent']
                  ]
                }

                //Breaking the build if we are not responsible for the Master
                if( env.MASTER_INSTANCES.indexOf( env.JENKINS_INSTANCE ) < 0 ){
                  currentBuild.result = 'ABORTED'
                  error( "Deleted Helm deployment ${env.JOB_REPO}-pr-${utils.getPullRequestNumberFromMergeCommit()}" )
                }

                withCredentials([usernamePassword(credentialsId: 'emst-github-app',
                          usernameVariable: 'GITHUB_APP',
                          passwordVariable: 'GITHUB_ACCESS_TOKEN')]) {

                  env.KANIKO_ARGS = ''
                  env.DEPLOYMENT_ENVIRONMENT = env.JENKINS_INSTANCE
                  env.PR_SOURCE_BRANCH = utils.getPullRequestSource( "${env.JOB_ORGA}/${env.JOB_REPO}" )
                  env.RELEASE_VERSION = utils.getNewReleaseVersion( "${env.JOB_ORGA}/${env.JOB_REPO}", env.PR_SOURCE_BRANCH )
                  env.DEPLOYMENT_SUFFIX = ''

                  // There is only one Jenins in charge of making releases!
                  if( MASTER_RELEASE.equals( env.JENKINS_INSTANCE ) ){
                    env.RELEASE_BUILD = 'true'
                    utils.createGitHubRelease( "${env.JOB_ORGA}/${env.JOB_REPO}", env.RELEASE_VERSION )
                    utils.createAnnotatedTag( env.RELEASE_VERSION )
                  }
                }
              }
            }
          }
        }
      }
    }


    stage ( 'Build' ) {
      parallel {
        stage( 'build npm' ) {
          steps {
              container( 'openjdk' ) {
                  script {
                    dir( 'src/main/ui' ) {
                      sh "echo 'registry=${NPM_REPOSITORY}' > .npmrc"
                      sh 'npm i'
                      sh 'npm run build:prod'
                    }
                    sh 'mkdir -p target/classes/static'
                    sh 'cp -r src/main/ui/dist/* target/classes/static/'
                  }
              }
          }
        }
        stage( 'build java' ) {
          steps {
              container( 'openjdk' ) {
                  script {
                    withMaven (
                        mavenSettingsConfig: 'global-maven-settings'
                    ) {
                      sh 'mvn test'
                    }
                  }
              }
          }
        }
      }
    }
    stage( 'package java' ) {
          steps {
            container( 'openjdk' ) {
              script {
                withMaven (mavenSettingsConfig: 'global-maven-settings') {
                  sh 'mvn package -DskipTests'
                }
              }
            }
          }
    }

    stage( 'Push artifact to Maven repository' ){
    
      when {
        beforeAgent true
        branch 'master'
        expression { MASTER_RELEASE.equals( env.JENKINS_INSTANCE ) }
      }
      steps{
        container( 'openjdk' ){

          withCredentials([[$class: 'UsernamePasswordMultiBinding', 
                credentialsId: 'emst_maven_repository', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
            withMaven (
                mavenSettingsConfig: 'global-maven-settings'
            ){
              sh "mvn deploy -Demst_maven.username=${USERNAME} -Demst_maven.password=${USERNAME} -Drepo-url=${MAVEN_ARTIFACTS} -DskipTests "
            }
          } 
        }
      }

    }

    stage( 'Create deployment artifacts' ){
      parallel{
        stage( 'kaniko build' ){
            steps{
                container( 'kaniko' ){
                  sh "sed 's/@container-registry@/${DOCKER_REGISTRY}/g' Dockerfile > ./target/Dockerfile"
                  sh "/kaniko/executor --push-retry 15 --context ./target --dockerfile ./target/Dockerfile --destination ${DOCKER_REGISTRY}/${env.JOB_ORGA.toLowerCase()}/${env.JOB_REPO}:${env.RELEASE_VERSION}"
                }
            }
        }

        stage( 'helm package' ){
            steps{
                container( 'helm' ){
                    sh "helm package ./helm --version=${env.RELEASE_VERSION} --app-version=${env.RELEASE_VERSION}"
                }
            }
        }
      }
    }

    stage( 'Publish Helm Chart' ){
      when { expression { !env.DEPLOYMENT_ENVIRONMENT?.equals( "" ) } }
      steps{
        container( 'helm' ){
          withCredentials([[$class: 'UsernamePasswordMultiBinding',
              credentialsId: 'helm_repository', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
              sh """
                helm repo add chartsink ${env.HELM_REPOSITORY}${env.CHART_PATH} --username=${USERNAME} --password=${PASSWORD}
                helm push ${env.JOB_REPO}-${env.RELEASE_VERSION}.tgz chartsink
              """
          }
        }
      }
    }
    

    stage( 'trigger deployment' ){
      when { expression { !env.DEPLOYMENT_ENVIRONMENT?.equals( "" ) } }
      steps{
        container( 'ansible' ){
          withCredentials([usernamePassword(credentialsId: 'emst-github-app',
                usernameVariable: 'GITHUB_APP',
                passwordVariable: 'GITHUB_ACCESS_TOKEN')]) {
            script{
              if( !env.RELEASE_BUILD ){
                  build job: "${env.DEPLOYMENT_REPO}/master", parameters: [
                    [$class: 'StringParameterValue', name: 'DEPLOYMENT_UNIT', value: "${env.JOB_REPO}"],
                    [$class: 'StringParameterValue', name: 'DEPLOYMENT_VERSION', value: "${env.RELEASE_VERSION}"],
                    [$class: 'StringParameterValue', name: 'DEPLOYMENT_SUFFIX', value: "${env.DEPLOYMENT_SUFFIX}"],
                    [$class: 'StringParameterValue', name: 'DEPLOYMENT_ENVIRONMENT', value: "${env.DEPLOYMENT_ENVIRONMENT}"]
                ]
              }
              else{
                echo "trigger deployment '${env.DEPLOYMENT_ENVIRONMENT}'  ${env.RELEASE_VERSION}"
                utils.updateFileInGitHub( env.JOB_REPO, "${env.RELEASE_VERSION}", utils.getFileFromGitHub( env.DEPLOYMENT_REPO ), env.DEPLOYMENT_REPO )              
                utils.createNextGitHubRelease( env.DEPLOYMENT_REPO, env.PR_SOURCE_BRANCH )
              }
            }
          }
        }
      }
    }
  }
}

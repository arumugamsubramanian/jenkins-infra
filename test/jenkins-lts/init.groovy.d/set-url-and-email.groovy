import jenkins.model.*

def jenkinsLocationConfiguration = JenkinsLocationConfiguration.get()
jenkinsLocationConfiguration.setUrl('https://jenkins.infra.arumugam.com:8443/')
jenkinsLocationConfiguration.setAdminAddress('Jenkins Admin <admin@ci.jenkins.internal>')
jenkinsLocationConfiguration.save()

import json

with open('/var/lib/jenkins/workspace/Allure_TestReport/worker/allure-results/sonarqube-metrics.json', 'r') as f:
    data = json.load(f)

measures = data['component']['measures']
with open('/var/lib/jenkins/workspace/Allure_TestReport/worker/allure-results/environment.properties', 'w') as env_file:
    for measure in measures:
        # Format key=value for the properties file
        env_file.write(f"{measure['metric']}={measure['value']}\n")

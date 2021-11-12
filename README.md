# DevOps Esprit Project
--
[![Image](https://github.com/yankils/Simple-DevOps-Project/blob/master/Devops_course.PNG "DevOps Project - CI/CD with Jenkins Ansible Docker Kubernetes ")]()

<h2>Running CI Server (Jenkins), Nexus, Sonarqube</h2>
<strong>Who all looking to get started with Continuous Integration and Continuous Delivery </strong> 
<strong>path in a scalable manner? If you are then you have found the right place to get started.</strong>


### Technologies used:
By following this repository you can able to setup a DevOps CI/CD Pipeline using
- git
- Jenkins
- Maven
- sonarqube
- Docker 
- Nexus


Reason : Despite all the benfit of jenkins and other tools ... <strong>  it's my educational system chosen </stong>

<hr>
<h2>📣 🔥Steps to install and run project 🔥 📣</h2>

<h3>1 - install git </h3>
<p>link : https://git-scm.com/downloads </p>
<p>to make sure git is working simply open your terminal or cmd and write "git --version"</p>
<img src="https://i.imgur.com/ZyGuNpy.png" data-canonical-src="https://i.imgur.com/ZyGuNpy.png">
<h2>Video</h2>
<a href="https://www.youtube.com/watch?v=nbFwejIsHlY&ab_channel=TheGrokShop"><img src="https://www.sportbusinessmag.com/wp-content/uploads/2021/01/youtube-nouveau-logo.jpg" style="max-width:100%;"></a>
 🚨 <strong>: Add Your Credintial With this command </strong>
<p>• Set a Git username:<p>
  
```bash
  $ git config --global user.name "MoetazBrayek"
```
<p>• Set a Git email:<p>
    
```bash
  $ git config --global user.email "moetaz.brayek@esprit.tn"
```
<hr>
<h3>2 - install maven </h3>
<p> Link : https://maven.apache.org/download.cgi </p>

<img src="https://i.imgur.com/eksLoWb.png" data-canonical-src="https://i.imgur.com/eksLoWb.png">
<h2>Video</h2>

<a href="https://www.youtube.com/watch?v=RfCWg5ay5B0&ab_channel=CodingMagic"><img src="https://www.sportbusinessmag.com/wp-content/uploads/2021/01/youtube-nouveau-logo.jpg" style="max-width:100%;"></a>

 🚨 <strong>: Follow the video and add maven to variable path  </strong>
 <p>to make sure maven is working simply open your terminal or cmd and write </p>
 ```bash
  $ mvn --version
```
```diff
- <p> please don't copy the $ -_- i saw people does this before </p>
-  <h3>Somtimes Your Computer Keep Cashing old env and not reading your modifcation : consider restarting your pc as a solution </h3>
```

<hr>
<h3>3 - install sonarqube </h3>

<p> Link : https://www.sonarqube.org/downloads/ </p>

<p>Edit warpper File and put your jdk path </p>
<img src="https://i.imgur.com/9ZgbIXm.png" data-canonical-src="https://i.imgur.com/9ZgbIXm.png">

 🚨 <strong>: to make sure your sonar is working run the command in same path as your project   </strong>
 ```bash
  $ mvn sonar:sonar -f yourProjectName
```
<img src="https://i.imgur.com/DyGGxe1.png" data-canonical-src="https://i.imgur.com/DyGGxe1.png">

<p>the service will start im port 9000  make sure nothing runing in this port </p>

<p>in case of problem smiply kill the process using by that service </p>
<p>Follow this : https://www.alphr.com/how-to-check-which-ports-open-windows-10-pc/</p>





a barebones hibernate demo

featuring a sample class
this is basically copied from the self-learn section 5 videos

running App will:
(1) delete the existing table 
(2) create a new table
(3) populate the new table

once imported into eclipse
(1) run as Maven clean
(2) run as Java application
	
important things to know:
- there is no need to manually add JAR files to the project
	- maven automatically grabs the JARs specified as dependencies in pom.xml
	
using this for future projects:
- create new maven project
	- use the apache.maven.org	"quickstart" archetype
- copy the dependencies from this pom into the newly created pom
- copy the src/main/resources/hibernate.cfg.xml (keep the directory structure)
- do the fix below



----------------------------------------------------------------------------
possible error: hibernate cannot find the hibernate.cfg.xml file

solution:
have the hibernate.cfg.xml file in the src/main/resources directory
right click the top level folder (hibernateDemo)
	-> build path -> configure build path
click the source tab at the top
	-> add folder -> click the checkbox by "resources" folder
---------------------------------------------------------------------------
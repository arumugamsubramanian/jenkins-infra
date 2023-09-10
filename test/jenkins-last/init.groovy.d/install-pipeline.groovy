import org.jenkinsci.plugins.workflow.libs.*
import jenkins.plugins.git.GitSCMSource

def workspace = System.getenv('GITHUB_WORKSPACE')
assert workspace != null

def lib = new LibraryConfiguration(
    "pipeline",
    new SCMSourceRetriever(new GitSCMSource("file://${workspace}/.tmp-test/.git"))
)

lib.setDefaultVersion("main")

gl = GlobalLibraries.get()
gl.libraries.add(lib)


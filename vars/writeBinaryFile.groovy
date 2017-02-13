#!groovy

import hudson.FilePath;
import jenkins.model.Jenkins;

def call(String path, byte[] data)
{
  if (env['NODE_NAME'] == null) {
        error "envvar NODE_NAME is not set, probably not inside an node {} or running an older version of Jenkins!";
  } else if (env['NODE_NAME'].equals("master")) {
      fp = new FilePath(path);
  } else {
      fp = new FilePath(Jenkins.getInstance().getComputer(env['NODE_NAME']).getChannel(), path);
  }

  if(fp != null)
  {
      fp.copyFrom(new ByteArrayInputStream(data)); //writing to file
  }
}



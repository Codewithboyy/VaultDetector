#!/usr/bin/env sh

# Gradle start up script for POSIX

APP_NAME="Gradle"
APP_BASE_NAME=`basename "$0"`

# Default JVM options
DEFAULT_JVM_OPTS='"-Xmx2g" "-Xms256m"'

warn () {
    echo "$*"
}

die () {
    echo
    echo "$*"
    echo
    exit 1
}

# OS specific support
cygwin=false
msys=false
darwin=false
nonstop=false
case "`uname`" in
  CYGWIN* )
    cygwin=true
    ;;
  Darwin* )
    darwin=true
    ;;
  MINGW* )
    msys=true
    ;;
  NONSTOP* )
    nonstop=true
    ;;
esac

# Locate Gradle wrapper jar
if [ -z "$APP_HOME" ]; then
  APP_HOME=`pwd -P`
fi
CLASSPATH=$APP_HOME/gradle/wrapper/gradle-wrapper.jar

# Determine Java command
if [ -n "$JAVA_HOME" ] ; then
    JAVACMD="$JAVA_HOME/bin/java"
else
    JAVACMD="java"
fi

# Execute
exec "$JAVACMD" $DEFAULT_JVM_OPTS -classpath "\( CLASSPATH" org.gradle.wrapper.GradleWrapperMain " \)@"

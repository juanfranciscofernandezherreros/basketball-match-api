#!/bin/sh
set -eu

BASE_DIR=$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)
URL=$(sed -n 's/^distributionUrl=//p' "$BASE_DIR/.mvn/wrapper/maven-wrapper.properties")
VERSION=3.9.11
MAVEN_HOME="${HOME}/.m2/wrapper/dists/apache-maven-${VERSION}"

if [ ! -x "$MAVEN_HOME/bin/mvn" ]; then
  mkdir -p "$MAVEN_HOME"
  ARCHIVE=$(mktemp)
  curl -fsSL "$URL" -o "$ARCHIVE"
  tar -xzf "$ARCHIVE" -C "$MAVEN_HOME" --strip-components=1
  rm -f "$ARCHIVE"
fi

exec "$MAVEN_HOME/bin/mvn" "$@"

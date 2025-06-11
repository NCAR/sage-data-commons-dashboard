#!/bin/bash

gladedir=/gpfs/csfs1/collections/cdg/catalogs/data-commons-data

if [ -d $HOME/qstat/store ] && [ -d $gladedir ]; then
  cp -p $HOME/qstat/store/*.* $gladedir
fi

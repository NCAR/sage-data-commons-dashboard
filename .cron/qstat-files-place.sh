#!/bin/bash

srcdir=/home/jcunning/qstat/store
desdir=/data/service/data-commons/data
tmpdir=/tmp/qstat.$$.$(date +%s)

mkdir $tmpdir

cp -p $srcdir/*.* $tmpdir
chmod 664 $tmpdir/*.*
chown sage:sage $tmpdir/*.*
mv $tmpdir/*.* $desdir

rmdir $tmpdir


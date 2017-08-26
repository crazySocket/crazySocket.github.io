#!/bin/bash -e
PATH=/usr/bin
tar xf $source
cd hello-1.0
./configure --prefix=$out
make
make install

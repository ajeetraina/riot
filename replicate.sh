#!/bin/bash
. ./config.cfg
riot -s $target_endpoint --auth $auth \
     replicate --cluster -s $source_endpoint



#!/bin/bash
clear && java -version
java -Xms2g -Xmx2g -Xss4m -XX:-TieredCompilation -server Test
echo -e "\n\n\nWith diagnostics:\n\n\n"
java -Xms2g -Xmx2g -Xss4m -XX:-TieredCompilation -XX:+PrintCompilation -XX:+PrintInlining -XX:+TraceLoopOpts -XX:+TraceDeoptimization -server Test

#!/usr/bin/env bash

set -e
set -o pipefail

targets="
ens/ENS
ens/PublicResolver
"

for target in ${targets}; do
    dirName=$(dirname $target)
    fileName=$(basename $target)

    cd $dirName
    echo "Compiling Solidity file ${fileName}.sol:"
    solc --bin --abi --optimize --overwrite ${fileName}.sol -o build/
    echo "Complete"

    echo "Generating web3j bindings"
    web3j solidity generate \
        build/${fileName}.bin \
        build/${fileName}.abi \
        -p com.venachain.ens.contracts.generated \
        -o ../../../../main/java/ > /dev/null
    echo "Complete"

    cd -
done

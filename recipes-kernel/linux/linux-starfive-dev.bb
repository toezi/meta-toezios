require linux-mainline-common.inc
FILESEXTRAPATHS =. "${FILE_DIRNAME}/linux-starfive:"
SUMMARY = "VisionFive dev kernel recipe"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"
KERNEL_VERSION_SANITY_SKIP = "1"

SRCREV = "${AUTOREV}"

# pin srcrev for now to have a fixed target
# release VF2_v3.1.5
SRCREV:visionfive2 = "4964ce0a869e92df26331833894c9d0fd84d80f3"

BRANCH = "visionfive"
BRANCH:visionfive2 = "JH7110_VisionFive2_devel"

FORK ?= "toezi"

REPO ?= "linux"

SRC_URI = "git://github.com/${FORK}/${REPO}.git;protocol=https;branch=${BRANCH} \
           file://0001-riscv-disable-generation-of-unwind-tables.patch \
           file://0001-gcc-plugins-Fix-build-for-upcoming-GCC-release.patch \
           file://0001-riscv-fix-building-external-modules.patch \
           file://modules.cfg \
          "

LINUX_VERSION ?= "6.2.0"

KBUILD_DEFCONFIG:visionfive2 = "starfive_visionfive2_defconfig"

COMPATIBLE_MACHINE = "visionfive2"

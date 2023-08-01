DESCRIPTION = "Fairly minimal image for ToeziOS"
LICENSE = "MIT"

inherit riscv-initramfs-image


# Add an extra 0.5 GB of free space to the root filesystem
IMAGE_ROOTFS_EXTRA_SPACE = "524288"
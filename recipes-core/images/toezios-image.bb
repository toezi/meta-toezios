DESCRIPTION = "Fairly minimal image for ToeziOS"
LICENSE = "MIT"

# Do not pollute the initrd image with rootfs features
IMAGE_FEATURES = ""

export IMAGE_BASENAME = "${MLPREFIX}toezios-image"
IMAGE_LINGUAS = ""


IMAGE_FSTYPES += "${INITRAMFS_FSTYPES}"
inherit core-image

IMAGE_ROOTFS_SIZE = "8192"
IMAGE_ROOTFS_EXTRA_SPACE = "0"

BAD_RECOMMENDATIONS += "busybox-syslog"

# WIC is not compatible with an initramfs image, also enabling WIC would cause
# an circular dependency.
IMAGE_FSTYPES:remove = " wic wic.gz"
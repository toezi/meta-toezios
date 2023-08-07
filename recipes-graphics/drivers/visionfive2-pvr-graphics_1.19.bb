LICENSE="CLOSED"

PROVIDES += "virtual/libgles1 virtual/libgles2 virtual/libgles3"
COMPATIBLE_MACHINE = "jh7110"

require recipes-bsp/common/visionfive2-firmware.inc
inherit update-rc.d

SRC_URI += "\
        file://glesv1_cm.pc \
"
S = "${WORKDIR}/git"

PACKAGES += " \
    ${PN}-firmware \
    ${PN}-tools \
"

do_install () {
    tar xz --no-same-owner -f ${S}/IMG_GPU/out/${IMG_GPU_POWERVR_VERSION}.tar.gz -C ${D}
    mv ${D}/${IMG_GPU_POWERVR_VERSION}/target/* ${D}
    install -d ${D}/usr/include/
    cp -r ${D}/${IMG_GPU_POWERVR_VERSION}/staging/usr/include/drv/ ${D}/usr/include/
    cp -r ${D}/${IMG_GPU_POWERVR_VERSION}/staging/usr/include/GLES/ ${D}/usr/include/
    cp -r ${D}/${IMG_GPU_POWERVR_VERSION}/staging/usr/include/GLES2/ ${D}/usr/include/
    cp -r ${D}/${IMG_GPU_POWERVR_VERSION}/staging/usr/include/GLES3/ ${D}/usr/include/
    install -d ${D}/usr/lib/pkgconfig/
    cp -r ${D}/${IMG_GPU_POWERVR_VERSION}/staging/usr/lib/pkgconfig/* ${D}/usr/lib/pkgconfig/
    install -Dm0644 ${WORKDIR}/glesv1_cm.pc ${D}${libdir}/pkgconfig/glesv1_cm.pc
    # let vulkan-loader from core layer provide libvulkan
    rm -rf ${D}${libdir}/libvulkan*.so* ${D}${libdir}/pkgconfig/vulkan.pc
    # provided via separate arch-independent firmware package
    rm -rf ${D}/lib/firmware
    rmdir ${D}/lib

    # cleanup unused
    rm -rf ${D}/${IMG_GPU_POWERVR_VERSION}
}

INITSCRIPT_NAME = "rc.pvr"

FILES_SOLIBSDEV = ""
FILES:${PN} += " \
    ${libdir}/*.so \
"

FILES:${PN}-tools = " \
    ${prefix}/local/bin/ \
"

FILES:${PN}-firmware = " \
    ${base_libdir}/firmware/ \
"

RDEPENDS:${PN} += " \
    bash \
    libdrm \
    linux-firmware-visionfive2-imggpu \
"

RDEPENDS:${PN}-tools += " \
    python3 \
"

RPROVIDES:${PN} += "libgles3 libgles2 libgles1"

INSANE_SKIP:${PN} += "already-stripped dev-so"
# ignore dependency check for python scripting
INSANE_SKIP:${PN}-tools += "already-stripped file-rdeps"
INSANE_SKIP:${PN}-firmware += "arch"

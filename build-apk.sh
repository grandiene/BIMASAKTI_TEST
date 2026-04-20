#!/bin/bash
set -e

ANDROID_JAR=/usr/lib/android-sdk/platforms/android-23/android.jar
BUILD_TOOLS=/usr/lib/android-sdk/build-tools/29.0.3
MANIFEST=app/src/main/AndroidManifest.xml
OUT=out
APK_UNSIGNED=$OUT/app-unsigned.apk
APK_ALIGNED=$OUT/app-aligned.apk
APK_SIGNED=$OUT/HelloWorld.apk

mkdir -p $OUT/smali/com/bimasaktitest/helloworld

# ── 1. Write Smali (Dalvik bytecode assembly) ────────────────────────────────
cat > $OUT/smali/com/bimasaktitest/helloworld/MainActivity.smali << 'SMALI'
.class public Lcom/bimasaktitest/helloworld/MainActivity;
.super Landroid/app/Activity;
.source "MainActivity.java"

.method public constructor <init>()V
    .registers 1
    invoke-direct {p0}, Landroid/app/Activity;-><init>()V
    return-void
.end method

.method protected onCreate(Landroid/os/Bundle;)V
    .registers 4
    .param p1, "savedInstanceState"

    invoke-super {p0, p1}, Landroid/app/Activity;->onCreate(Landroid/os/Bundle;)V

    # Create TextView
    new-instance v0, Landroid/widget/TextView;
    invoke-direct {v0, p0}, Landroid/widget/TextView;-><init>(Landroid/content/Context;)V

    # setText("Hello World!")
    const-string v1, "Hello World!"
    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    # setTextSize(TypedValue.COMPLEX_UNIT_SP=2, 32f)
    const/4 v1, 0x2
    const v2, 0x42000000
    invoke-virtual {v0, v1, v2}, Landroid/widget/TextView;->setTextSize(IF)V

    # setGravity(Gravity.CENTER=0x11)
    const/16 v1, 0x11
    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setGravity(I)V

    # setContentView(textView)
    invoke-virtual {p0, v0}, Landroid/app/Activity;->setContentView(Landroid/view/View;)V

    return-void
.end method
SMALI

echo "[1/5] Assembling Smali → classes.dex"
smali assemble $OUT/smali -o $OUT/classes.dex

# ── 2. Package manifest + resources into APK ─────────────────────────────────
echo "[2/5] Packaging resources with aapt"
$BUILD_TOOLS/aapt package -f \
    -M $MANIFEST \
    -I $ANDROID_JAR \
    -F $APK_UNSIGNED

# ── 3. Add classes.dex to APK ────────────────────────────────────────────────
echo "[3/5] Adding classes.dex to APK"
cp $APK_UNSIGNED $APK_UNSIGNED.tmp
cd $OUT && zip -j app-unsigned.apk classes.dex && cd ..

# ── 4. Zipalign ──────────────────────────────────────────────────────────────
echo "[4/5] Zipaligning"
$BUILD_TOOLS/zipalign -f 4 $APK_UNSIGNED $APK_ALIGNED

# ── 5. Sign with debug keystore ───────────────────────────────────────────────
echo "[5/5] Signing APK"
if [ ! -f $OUT/debug.keystore ]; then
    keytool -genkeypair -v \
        -keystore $OUT/debug.keystore \
        -alias androiddebugkey \
        -keyalg RSA -keysize 2048 \
        -validity 10000 \
        -storepass android \
        -keypass android \
        -dname "CN=Android Debug,O=Android,C=US" 2>/dev/null
fi

$BUILD_TOOLS/apksigner sign \
    --ks $OUT/debug.keystore \
    --ks-pass pass:android \
    --key-pass pass:android \
    --ks-key-alias androiddebugkey \
    --out $APK_SIGNED \
    $APK_ALIGNED

echo ""
echo "✓ APK built: $APK_SIGNED"
ls -lh $APK_SIGNED

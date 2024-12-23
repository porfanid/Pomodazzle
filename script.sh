for file in *.svg; do
    rsvg-convert "$file" -a -w 1024 -h 1024 >"${file%.svg}-temp.png"
    convert -background none -resize 200x200 -gravity center -extent 200x200 "${file%.svg}-temp.png" "${file%.svg}.png"
    rm "${file%.svg}-temp.png"
done

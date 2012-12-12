landslide --linenos=table --theme=theme pattern-tricks.md
sed -e 's|file:///home/adv/projects/pattern[-_]tricks/||g' < presentation.html > index.html

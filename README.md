# eTaT - Every Thing All the Time

## Purpose

eTaT is a collection viewer for visual material with a heavy focus on seamless 
shifts from overview to details, avoiding context switches and keeping
material context in the form of related material.

eTaT is about exploring collections with only a vague idea of the expected
outcome. Consequently it works poorly for focused search where the user 
expects a specific singular result.

eTaT is an experiment from https://labs.kb.dk by 
Jesper Lauridsen ([@justjspr](https://twitter/justjspr) / 
[jla@kb.dk](email:jla@kb.dk)) & 
Toke Eskildsen ([@TokeEskildsen](https://twitter.com/@TokeEskildsen) / 
[toes@kb.dk](email:toes@kb.dk)) - feedback is welcome.

## Principles

* A complete overview of all material is available at all times in the 
form of a zoomable collage.
* There is only a single view, with high-res renders and metadata for
the individual material becoming gradually available using zoom & pan.
* All material is available all the time: Searching or filtering only changes
the overall layout, it never removes material.
* Visual grouping is the dominant mechanism for accessing subsets of the
collection.
* Material first: Display of the material takes precedence over metadata,
navigation elements and other non-material layout elements.  

For inspiration see [zoom](http://labs.statsbiblioteket.dk/zoom/) and 
[juxta](http://labs.statsbiblioteket.dk/juxta/subject3795/).

## Layout mechanism

* A fixed size rectangle containing the full collage of material. The rectangle
should be as large as possible, preferably the full browser window. See
[Lunch](https://labs.statsbiblioteket.dk/juxta/lunch/) for inspiration.  
* Subsetting the collection by searching results in hits being grouped in
an area in the middle of the collage, with non-matching material being
positioned outside of the area. Higher ranked material is at the center of
the area.
* Groups are repesented with different background colors.    

## Technical notes

* The project aims for collections ranging from thousands of elements to a
 few million.
* The development focus is on full color images.

## Status

Absolutely at the initial idea state.
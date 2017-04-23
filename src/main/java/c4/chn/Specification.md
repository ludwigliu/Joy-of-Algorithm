# Conversion between Arabic and Chinese number

This to explain the Conversion between Arabic and Chinese number.

## From Arabic to Chinese number

As Chinese number is separated by '万', so separate the Arabic number to several sections, each only has 4 digits. Convert each section to coordinate Chinese number and add the weight unit.

Here when does the conversion of each section, K2 uses a so-called 'in-advance-judgement', which assumes that if current value isn't 0, a higher position will have 0, but only does when it is indeed 0.

K1 is just when current section is less than 1000 but not 0, which is that the position of '千' is 0, so there should be a ZERO between this section and left/higher section, and defer it until the process of left/higher section.

## From Chinese to Arabic number

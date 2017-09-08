Android Things demo project which controls WS2812B LED strip. Uses SPI bus for communication with strip. Can be used as a library for other projects.

![](demo.gif?raw=true)


For Raspberry Pi 3 connect strips's D_IN to GPIO10(SPI0_MOSI).

# How it works
It uses blocks of 4 bits in SPI transmission to generate one bit of WS2812B
protocol. Diagram below explains such usage of SPI.

![](diagram.png?raw=true)


# License
Creative Commons CC0, see LICENSE.txt for details. Basically it means that this
project is in the public domain.

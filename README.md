# Introduction

A Minecraft plugin, Make a player suffer increasing damage.
The function is to limit the player's health recovery and defense measures when reaching a certain threshold, preventing 
situations where unlimited health regeneration leads to a draw
You can download the latest version [here](https://www.spigotmc.org/resources/damage-penalty.115162/)

## Functions:
- Set Multipliers to +0.005 upon damage
- Set Multipliers to -0.001 upon per second
- Reset Multipliers to 1.0 upon death
- Eliminate the damage absorption effect given by the Golden Apple (when reaching a certain threshold)
- (Most of the above functions can be customized)

## Installation:
- 1.Stop your server
- 2.Place the plugin jar file in the plugins folder of the root directory
- 3.Start the server
- 4.The plugin is enabled, and you can use/dpe to view command help

## Commands:
- /dpe - display help message
- /dpe reload - reload the config
- /dpe debug - toggle debug mode

## API:
- Support referencing local JARs to use APIs
```plaintext
<pre>
	<dependency>
	<groupId>github.tsffish</groupId>
	<artifactId>damagepenalty</artifactId>
	<version>1.0.2</version>
	<scope>system</scope>
	<systemPath>YourPathHere/DamagePenalty-1.0.2.jar</systemPath>
	</dependency>
</pre>
```
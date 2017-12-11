# ecosystem-simulation
A java program that simulates an ecosystem with plants, sheep, and wolves. The ecosystem consists of soil, plants, sheep, and wolves.

####Soil
The soil starts off dry and has a chance of becoming fertile every turn.
 
####Plants
Plants can only grow on fertil soil. They have a chance of reproducing asexually, and they reproduce in all possible directions each turn.
They have a chance of being poisonous.
They start off with 0 nutrition and gain some each turn until they hit a maximum.
 
####Sheep
The Sheep move and eat plants. If poisoned, they lose an extra health every turn. Eaten plants turn into dry soil.
When two sheep of opposite genders collide and they both have at least 20 health, they have a chance of breeding. Both sheep lose 10 health in the process.
 
####Wolves
The Wolves move and eat sheep If poisoned, they lose an extra health every turn. They can also step on plants.
When two sheep of opposite genders collide and they both have at least 20 health, they have a chance of breeding. Both sheep lose 10 health in the process.
If they do not reproduce, the wolves fight. If the attacker has the same or more health, the othe wolf loses 10 health. Otherwise, the attacker loses 10 health.
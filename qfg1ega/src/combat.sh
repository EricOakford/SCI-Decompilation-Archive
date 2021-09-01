;;; Sierra Script 1.0 - (do not remove this comment)
;Combat Framework defines

;arena spell animations
(define ARENA_MAGIC  146)
(define ARENA_FLAME  147)
(define ARENA_ZAP 	 148)
(define ARENA_DAZZLE 149)
(define ARENA_CALM 	 150)

;arena combat animations
(define ARENA_THRUST 151)
(define ARENA_BLOCK	 152)
(define ARENA_PARRY	 153)
(define ARENA_DODGE	 154)
(define ARENA_PAIN	 155)

;core arena regions, classes and scripts
(define ENCOUNTER 	210) ;Region
(define ARENA 		211) ;Region
(define SKILLED 	212) ;Class
(define WARRIOR 	213) ;Class
(define MONSTER 	214) ;Class
(define CLOSECOMBAT 215) ;Script

;note, the other creatures have their maxHP in a array in RandomEncounters.sc
(define MAX_HP_OGRE 112)
(define MAX_HP_BRUTUS 100)
(define MAX_HP_KOBOLD 67)
(define MAX_HP_BEAR 133)

;Arena Actions (for the 'Skilled' class)
(enum
	ActNone
	ActThrust
	ActSlash
	ActParryUp
	ActParryDown
	ActDodgeLeft
	ActDodgeRight
	ActDuck
	ActLeap
	ActPain	;never used, just a guess
	ActDie
	ActCast
)

;Attack angles (for close combat)
(enum
	AttLeft
	AttRight
	AttStraight
)

;Armor values
(define LEATHER_VALUE 2)
(define CHAIN_VALUE 5)

;Weapon values
(define DAGGER_VALUE 5)
(define SWORD_VALUE 8)

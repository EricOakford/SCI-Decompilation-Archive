;;; Sierra Script 1.0 - (do not remove this comment)
;
; * SCI Game Header
; *************************************************************************
; * Put all the defines specific to your game in here

(include system.sh) (include sci2.sh)
(include flags.sh)

;Inventory items
(enum
	iDagger			;0
	iChest			;1
	iCarrot			;2
	iKey			;3
	iNote			;4
	iMagicRing		;5
	iFourLeafClover	;6
	iCeramicBowl	;7
	iWaterBucket	;8
	iPebbles		;9
	iSlingshot		;10
	iPouch			;11
	iSceptre		;12
	iCheese			;13
	iMagicMirror	;14
	iGoldEgg		;15
	iMagicShield	;16
	iFiddle			;17
	iWalnut			;18
	iMushroom		;19
	iBeans			;20
)

;Who's carrying Graham through the sky
(enum 1
	carrierCONDOR 	;1
	carrierWITCH	;2
)

;Regions

(define	GOAT		600)
(define	TROLL		601)
(define	MENACE		602)
(define	WATER		603)
(define	GARDEN		604)
(define	MOAT		605)
(define	BEANS		606)
(define	STALK		607)
(define	RIVER		608)
(define	CLIMB		609)
(define	CLOUDS		610)
(define	BIRD		611)
(define	WATER_42	612) ;room 42 has both safe and deadly water, so a special region is needed for it
(define	FALLING		613)
(define HALO		616)

;Global stuff
(define KQ_WINDOW 778)

;Menaces
(define SORCERER 800)
(define OGRE 803)
(define DWARF 804)

;Required memory sizes in bytes
(define InvSize 1550)
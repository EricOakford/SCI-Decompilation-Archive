;;; Sierra Script 1.0 - (do not remove this comment)
;**************************************************************
;***
;***	GAME.SH--
;***
;**************************************************************

(include system.sh) (include sci2.sh) ;system and kernel functions
(include verbs.sh) (include talkers.sh)

(define DEBUG		10)
(define DISPOSE		11)
(define COLORINIT	12)
(define LBABOUT		13)
(define LBINIT		14)
(define LBINV		15)
(define LBDOOR		16)
(define LBROOM		17)
(define LBEGO		19)
(define LBCONTROL	24)
(define SPEED		28)
(define WHERETO		29)
(define DEATH		99)

;Icon bar
(enum
	ICON_WALK
	ICON_LOOK
	ICON_DO
	ICON_TALK
	ICON_ASK
	ICON_ITEM
	ICON_INVENTORY
	ICON_CONTROL
)

;Inventory items
(enum
	iCoupon			;0
	iClaimTicket	;1
	iNotebook		;2
	iSandwich		;3
	iBaseball		;4
	iDeskKey		;5
	iPressPass		;6
	iPocketWatch	;7
	iSkeletonKey	;8
	iMeat			;9
	iWireCutters	;10
	iDaggerOfRa		;11
	iWorkBoot		;12
	iSmellingSalts	;13
	iSnakeOil		;14
	iLantern		;15
	iCheese			;16
	iGarter			;17
	iDinoBone		;18
	iSnakeLasso		;19
	iAnkhMedallion	;20
	iPippinPad		;21
	iMagnifier		;22
	iLightBulb		;23
	iwatneyFile		;24
	iWarthogHairs	;25
	iBifocals		;26
	iRedHair		;27
	iWaterGlass		;28
	iCarbonPaper	;29
	iYvetteShoe		;30
	iGrapes			;31
	iEveningGown	;32
	iCharcoal		;33
	iWire			;34
	iMummy			;35
)

;Death reasons
(define deathRUNOVER	9)

;Event flags
(enum
	fIsVGA				;0
)
(define fFlag25			25)
(define fBeenInDirtyTaxi	26)
(define fGotClaimTicket		27)
(define fTalkedToLuigi	28)
(define fDeskUnlocked	29)
(define fEgoSits		30)
(define fRubbedPad		35)
(define fCountessDead	92)
(define fGetPressPass	128)
(define fEndGame		147)
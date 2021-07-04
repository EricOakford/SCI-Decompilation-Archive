;;; Sierra Script 1.0 - (do not remove this comment)
;
; * SCI Game Header
; *************************************************************************
; * Put all the defines specific to your game in here

(include system.sh) (include sci2.sh)

;Script defines
(define KQINIT	10)
(define PROCS	11)
(define KQEGO	13)
(define KQROOM	16)
(define HOTSPOT	17)
(define DEBUG	99)

;Death reasons
(enum
	deathLAVA
)

;Inventory items
(enum
	iComb
	iPeticoat
	iPot
	iStick
	iFlag
	iPuz1
	iSeed
	iBasket
	iHorn
	iSpecs
	iHair
	iBead
	iPuz2
	iPuzzle
	iCornSeed
	iSaltH2O
	iFreshH2O
	iRope
	iPowder
	iCorn
	iPear
	iSaltCrystal
	iTurquoiseShape
	iRat
	iGoldBowl
	iSilSpoon
	iBeetle
	iDragScale
	iPellet
	iShield
	iShieldSpike
	iDragToad
	iMagRope
	iBrassBowl
	iSulfur
	iLantern
	iSpark
	iBigGem
	iHamChisel
	iCrook
	iNectar
	iFeather
	iChinaBird
	iMask
	iNickel
	iBook
	iRubberChicken
	iStatue
	iMoon
	iDiggerHorn
	iBackBone
	iPet
	iDefoliant
	iMagicWand
	iVeil
	iScarab
	iShovel
	iDiggerRat
	iLife
	iFootInBag
	iFlower
	iStocking
	iDevice
	iSling
	iGrape
	iWereSalve
	iPomGranate
	iAmbrosia
	iDreamCatcher
	iBridle
	iTapestry
	iCrystal
	iFemur
	iMedal
	iFirecracker
	iHead
	iFife
	iLastInvItem	;this MUST be last
)
	

;Event flags
(define fScorpionTrapped	20)
(define fIsHiRes	22)
;;; Sierra Script 1.0 - (do not remove this comment)
;
; * SCI Game Header
; *************************************************************************
; * Put all the defines specific to your game in here

(include system.sh) (include sci2.sh)

;Inventory items
(enum 1
	iKey			;1
	iPie			;2
	iNeedle			;3
	iCoin			;4
	iFish			;5
	iBottle			;6
	iStaff			;7
	iShoe			;8
	iHeart			;9
	iHarp			;10
	iGold			;11
	iPuppet			;12
	iPouch			;13
	iEmeralds		;14
	iWheel			;15
	iStick			;16
	iHoney			;17
	iWax			;18
	iLamb			;19
	iRope			;20
	iCrystal		;21
	iHammer			;22
	iShell			;23
	iPeas			;24
	iLocket			;25
	iCloak			;26
	iAmulet			;27
	iWand			;28
	iSled			;29
	iIronBar		;30
	iHook			;31
	iCheese			;32
	iElfShoes		;33
	iTambourine		;34
	iMordackWand	;35
	iHairPin		;36
	iCatFish		;37
	iMongooseSpell	;38
	iBunnySpell		;39
	iRainSpell		;40
	iTigerSpell		;41
)

;Icon bar defines
(enum
	ICON_WALK
	ICON_LOOK
	ICON_DO
	ICON_TALK
	ICON_ITEM
	ICON_INVENTORY
)

;Event flags
(define fWearingCloak	15)
(define fHaveEaten		16)
(define fWitchCastSpell	18)
(define fSnakeGone		47)
(define fCedricWontSaveEgo	55)
(define fCaughtElf		56)
(define fWandRecharged	60)
(define fGaveCatFish	62)
(define fThrewPeas	63)
(define fCastleTimeStopped		64)
(define fHoldingCedric	74)
(define fCaughtByHenchman	77)
(define fWearingAmulet	84)
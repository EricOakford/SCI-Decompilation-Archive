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
(define fTalkedToWitch	19)
(define fBeenInShoeShop	23)
(define fTalkedToShoeMakerWife	24)
(define fGotPie	25)
(define fSawBandits	32)
(define fEnteredCrispinHouse	37)
(define fMetYeti		40)
(define fFoundTemple	43)
(define fGotElfShoes	46)
(define fSnakeGone		47)
(define fFoundCamp		49)
(define fBeenInBakery	50)
(define fBanditsEnterTemple	52)
(define fSawDrunkGuy	53)
(define fCedricInjured	55)	;cleared when Graham picks him up
(define fCaughtElf		56)
(define fDefeatedYeti	57)
(define fTalkedToTailor	58)
(define fWandRecharged	60)
(define fGaveCatFish	62)
(define fThrewPeas	63)
(define fCastleTimeStopped		64)
(define fBeenAtTemple	70)
(define fHoldingCedric	74)
(define fCaughtByHenchman	77)
(define fWearingAmulet	84)
(define fSnakeWarns		87)
(define fWitchHere	90)
(define fFailedBoatCP	94)
(define fTalkedToToyMaker	108)
(define fCassimaCartoon	111)
(define fHermitCartoon	112)
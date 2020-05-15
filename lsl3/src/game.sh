;;; Sierra Script 1.0 - (do not remove this comment)
;
; * SCI Game Header
; *************************************************************************
; * Put all the defines specific to your game in here

(include system.sh) (include sci2.sh)

;doorState
(enum
	doorClosed
	doorOpening
	doorOpen
	doorClosing
)


;Inventory items
(enum 1
	iCreditCard			;1
	iGinsuKnife			;2
	iWood				;3
	iGrass				;4
	iSoap				;5
	iMoney				;6
	iLandDeed			;7
	iBeachTowel			;8
	iSpaKeycard			;9
	iDivorceDecree		;10
	iOrchids			;11
	iPenthouseKey		;12
	iBottleOfWine		;13
	iPanties			;14
	iPantyhose			;15
	iBra				;16
	iDress				;17
	iMagicMarker		;18
	iCoconuts			;19
	iMarijuana			;20
)

;Global Stuff
(define LSL3			0)
(define DEBUG			20) ;this seems to be intended for Al Lowe
(define CHANGE_SCRIPT	21)
(define DEBUG_22		22) ;this seems to be intended for the QA team
(define DYING			40)
(define FALLING			41)
(define LEIING			42)
(define CARVING			43)
(define WEAVING			44)
(define LOCKER			45)
(define INVDESC			46)
(define DOOR			50)
(define AUTODOOR		51)
(define GIRL			70)
(define FACE			71)
(define ROBIN_LARRY		80)
(define ROBIN_PATTI		81)
(define BOSSKEY			90)

;Locales and regions
(define VILLAGE 299)
(define FATCITY 399)
(define CASINO_MIRROR 417)
(define CASINO 499)
(define JUNGLE 599)
(define STUDIO 699)

;Ego statuses
(enum
	egoNORMAL			;0
	egoAUTO				;1
	egoFALLING			;2
	egoDRINKWATER		;3
	egoROLLOUT			;4
	egoNAKED			;5
	egoNAKED_CENSORED	;6
	egoLEISURESUIT		;7
	egoTOWEL			;8
	egoSWEATSUIT		;9
	egoNATIVE			;10
	egoSHOWGIRL			;11
	egoSWIMMING			;12
	egoREMOVEPANTYHOSE	;13
)
(define egoINCOMEDYCLUB 16)
(define egoDEAD 1001)
(define egoDEATHMESSAGE 1002)
(define egoDROWNING 1003)
(define egoSITTING 1004)
(define egoSUNBATHING 1005)

;EO: The following three enums are all original Sierra names found in TEXT.417
;Showroom states
(enum
	SRshowIsOn
	SRshowDone
	SRcherriOnPhone
	SRknowsAboutDeed
	SRcherriBackstage
	SRstageDoorUnlocked
	SRdone
)
;Newspaper states
(enum
	NSnotYet
	NSpComing
	NSshowroom
	NSpHere
	NSlMissing
)
;Lawyer states
(enum
	LSbusy
	LSfree
	LSwaiting4Deed
	LSdeedReady
	LSneeds500
	LSdivorce
	LSWaiting4Divorce
	LSdivorceReady
	LSdone
)

;Event flags
(enum
	;flags 0-2 are unused
	fUnused0					;0
	fUnused1					;1
	fUnused2					;2
	fSaveDisabled				;3
	fAutoSaveDisabled			;4
	fCursorHidden				;5
	fDrankRiverWater			;6
	fDrankFountainWater			;7
	fNotShower					;8
	fMetPatti					;9
	fNotUseSoap					;10
	fGotSuntan					;11
	fLookedInCasinoMirror		;12
	fAlAndBill					;13
	fQAEnabled					;14	;this is tested in an alternate debug script (22) but is never set
	fFlag15						;15 ;this is tested a few times, but is never set
	fLookedInBinoculars			;16
	fOpening200					;17
	fLookedAtPlaque				;18
	fOpening210					;19
	fBrokeUp					;20
	fBackInLeisureSuit			;21
	fFired						;22
	fFlag23						;23 ;set in 260
	fCredits200					;24
	fCredits210					;25
	fCredits213					;26
	fGotCreditCard				;27
	fCredits220					;28
	fCredits250					;29
	fCredits253				 	;30
	fUnused31					;31
	fLookedAtTawni				;32
	fBeenInLockerRoom			;33
	fBeenInTanningRoom			;34
	fScrewedCherri				;35
	fBeenInChipAndDales			;36
	fPassedMaze					;37
	fMetSuzi					;38
	fDriedWithTowel				;39
	fUnlockedLocker				;40
	fWoreSweatsuit				;41
	fFilledBottlwWithWater		;42
	fListenedToComedian			;43
	fLearnedLockerCombination	;44
	fScrewedSuzi				;45
	fGaveTicketToMaitreD		;46
	fGaveMoneyToMaitreD			;47
	fMetCherri					;48
	fShowerOn					;49
	fWet						;50
	fLockerRobbed				;51
	fScrewedBambi				;52
	fBambiGone					;53 	;if you don't meet her after exercising
	fGotOrchide					;54
	fMadeLei					;55
	fMetDale					;56
	fUnused57					;57
	fGotPot						;58
	fWoreGrassSkirt				;59
	fUsedDeodorant				;60
	fMetTawni					;61
	fNotUseDeodorant			;62
	fEnteredElevator			;63
	fBillAndJodi				;64
	fGaveHeadToDoorman			;65
	fGaveWineToPatti			;66
	fGotTipJar					;67
	fBeatFeralPig				;68
	fSkippedLoveScene			;69
	fFoundGymKeyAccidentally	;70
	fMetBambi					;71
	fRemovedPantyhose			;72
	fRemovedBra					;73
	fCoconutsInBra				;74
	fBraPoints					;75
	fCoconutPoints				;76
	fSkippedRafting				;77 ;last flag?
)

;Required memory sizes in bytes
(define GaugeSize 2048)
(define InvSize 1024)
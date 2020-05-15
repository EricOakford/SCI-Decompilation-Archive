;;; Sierra Script 1.0 - (do not remove this comment)
(include system.sh) (include sci2.sh)

; howFast values
(enum
	slow
	medium
	fast
	fastest
)

; Icons
(enum
	ICON_WALK
	ICON_LOOK
	ICON_DO
	ICON_TALK
	ICON_ZIPPER
	ICON_TASTE
	ICON_ITEM
	ICON_INVENTORY
	ICON_CONTROL
	ICON_HELP
)

; Inventory
(enum
	iWallet			;0
	iBreathSpray	;1
	iWatch			;2
	iApple			;3
	iRing			;4
	iWhiskey		;5
	iRemoteControl	;6
	iRose			;7
	iLubber			;8
	iCandy			;9
	iDiscoPass		;10
	iPocketKnife	;11
	iWine			;12
	iMagazine		;13
	iHammer			;14
	iPills			;15
	iRibbon			;16
	iGraffiti		;17
)

; Regions
(define rgSidewalk 700)

; Actual rooms
(define rmOutsideBar 100)
(define rmInsideBar 110)
(define rmHallway 120)
(define rmToilet 130)
(define rmBackroom 140)
(define rmHooker 150)
(define rmAlley 160)
(define rmDarkAlley 170)
(define rmWorkshop 190)

(define rmInsideTaxi 200)

(define rmBlackjack 250)
(define rmSlots 260)

(define rmOutsideCasino 300)
(define rmInsideCasino 310)
(define rmLounge 320)
(define rmElevatorBottom 330)
(define rmElevators 340)
(define rmSecurityDesk 350)
(define rmFaithCloseup 355)
(define rmLivingRoom 360)
(define rmBedroom 370)
(define rmDollCloseup 371)
(define rmEndCredits 375)
(define rmHotTub 380)
(define rmEveCloseup 385)
(define rmHoneymoonSuite 390)

(define rmOutsideChapel 400)
(define rmInsideChapel 410)

(define rmOutside7_11 500)
(define rmKeypad 505)
(define rmInside7_11 510)

(define rmOutsideDisco 600)
(define rmInsideDisco 610)
(define rmFawnCloseup 615)

(define rmTitle 710)
(define rmAgeTest 720)

; Leisure Suit Larry framework scripts
(define DEBUG 800) ;removed from game
(define DISPOSE 801)
(define SPEED 803)
(define ABOUT 811)
(define SPRAY 812)
(define LLROOM 813)
(define LLINV 814)
(define LLEGO 815)
(define LLINIT 816)

; Event Flags
(enum
	fAlleyWindowBroken		;0
	fGambling				;1	;running out of money doesn't result in death until exiting the machine
	fInvitedIntoTub			;2
	fDancedWithFawn			;3
	fChasingDoll			;4
	fSprayDone				;5
	fFollowingEve			;6
	fMetEve					;7
	fUnsuccessfulCeremony	;8
	fFaithGone				;9
	fAskedForMoney			;10
	fMarriedFawn			;11
	fFawnInRoom				;12
	fNoFawnInDisco			;13
	fMakeFawnSplit			;14
	fDiedOfTheClap			;15
	fHookerWindowOpen		;16
	fBubblesOff				;17
	fEveNameKnown			;18
	fHeardSurveyFirstTime	;19
	fMouthSmellsBad			;20
	fPersonComplained		;21
	fFlag22					;22		;cleared when entering the taxi, but is never set
	fClosetOpen				;23
	fOrderedWine			;24
	UNUSED_25				;25
	fPhoneRinging			;26
	fPaidPimp				;27
	fGotPimpToWatchTV		;28
	fDollInflated			;29
	fUnused30				;30
	fMakeFawnDance			;31
	fGaveFawnEverything		;32
	fUnused33				;33
	fEnteredTaxi			;34
	fScoredHooker			;35
	fSeenDeliveryBoyGag		;36
	fSmellsLikeDogPiss		;37
	fStiffedCabbie			;38
	fAnsweredSurvey			;39
	fToiletPaperOnShoe		;40
	fRadioOn				;41
	fTVOn					;42
	fWearingLubber			;43
	fIsVGA					;44
	fLookedAtFawn			;45
	fEndGame				;46
	fUnused47				;47
	
;Puzzle point event flags
	fAnswerPhone			;48
	fSatWithFawn			;49
	fBeenInPenthouse		;50
	fBuyApple				;51
	fGetWhiskey				;52
	fCutRibbon				;53
	fFawnDancePoints		;54
	fOrderedWineFirstTime	;55
	fDialedSierra			;56
	fDialedSurvey			;57
	fScrewDoll				;58
	fGaveEveApple			;59
	fGaveFawnRose			;60
	fGaveFawnRing			;61
	fGaveFawnCandy			;62
	fDiscoCompleted			;63
	fGaveFaithPills			;64
	fGotCandy				;65
	fOpenedClosetFirstTime	;66
	fGetHammer				;67
	fGetKnife				;68
	fBoughtLubber			;69
	fGetMagazine			;70
	fGetMarried				;71
	fGetDiscoPass			;72
	fGetPills				;73
	fPimpDistracted			;74
	fGetRemoteControl		;75
	fGetRibbon				;76
	fGetRing				;77
	fGetRose				;78
	fGotWineBox				;79
	fHeardAjaxAd			;80
	fInflateDollPoints		;81
	fGetPassword			;82
	fEndGamePoints			;83
	fPoopedInCan			;84
	fReadMagazine			;85
	fLookAtPhone			;86
	fRemoveLubber			;87
	fRodeInCab				;88
	fSitAtLounge			;89
	fHookerPoints			;90
	fTalkedToFawn			;91
	fTalkedToFlasher		;92
	fTurnedOnTV				;93
	fTurnedOnRadio			;94
	fShowDiscoPass			;95
	fPutOnLubber			;96
	
	;A few more regular event flags
	fFirstElevatorRide		;97
	DANCERS_AT_CABARET		;98
	fNearBum				;99
	fAppleManOnScreen		;100
)

; Verbs
(enum 8
	verbShowControls	;8
	verbHideControls	;9
	verbZipper			;10
	verbTaste			;11
)